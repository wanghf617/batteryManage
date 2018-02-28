package com.hycen.batteryManage.util;

import org.apache.commons.codec.binary.Hex;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Map;
import java.util.TreeMap;

public class EncodeUtils {
    public static String md5(String data) {
        return md5(data.getBytes());
    }

    public static String md5(byte[] data) {
        return getMessageDigest(data, "MD5");
    }

    public static String sha1(String data) {
        return sha1(data.getBytes());
    }

    public static String sha1(byte[] data) {
        return getMessageDigest(data, "SHA-1");
    }

    public static String sha256(byte[] data) {
        return getMessageDigest(data, "SHA-256");
    }

    public static String getMessageDigest(String data, String algorithm) {
        return getMessageDigest(data.getBytes(), algorithm);
    }

    public static String getMessageDigest(byte[] data, String algorithm) {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance(algorithm);
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        byte[] hash = messageDigest.digest(data);
        return Hex.encodeHexString(hash);
    }

    public static String simpleEncode(final String src) {
        return new String(simpleEncode(src.getBytes()));
    }

    // 加salt是为了让编码的数据发生一点变化
    public static String simpleEncode(final String src, final int saltLen) {
        return new String(simpleEncode(src.getBytes(), saltLen));
    }

    public static byte[] simpleEncode(final byte[] src) {
        byte[] dst = Base64.getEncoder().encode(src); // 先base64
        for (int i = 0; i < dst.length; i++) {
            // 只处理字母
            if (dst[i] >= 65 && dst[i] <= 90) {
                dst[i] += 32; // 字母颠倒大小写
                if (dst[i] % 2 != 0) {
                    dst[i]++;
                }
                else {
                    dst[i]--;
                }
            }
            else if (dst[i] >= 97 && dst[i] <= 122) {
                dst[i] -= 32; // 字母颠倒大小写
                if (dst[i] % 2 != 0) {
                    dst[i]++;
                }
                else {
                    dst[i]--;
                }
            }
        }
        return dst;
    }

    // 加salt是为了让编码的数据发生一点变化
    public static byte[] simpleEncode(final byte[] src, final int saltLen) {
        if (saltLen < 1) {
            return simpleEncode(src);
        }

        byte[] saltLenBuffer = new byte[4];
        saltLenBuffer[0] = (byte) (saltLen >> 24 & 0x000000ff);
        saltLenBuffer[1] = (byte) (saltLen >> 16 & 0x000000ff);
        saltLenBuffer[2] = (byte) (saltLen >> 8 & 0x000000ff);
        saltLenBuffer[3] = (byte) (saltLen & 0x000000ff);

        String salt1 = Utils.randString(saltLen);
        String salt2 = Utils.randString(saltLen);

        ByteArrayOutputStream srcDataStream = new ByteArrayOutputStream();
        try {
            srcDataStream.write(saltLenBuffer);
            srcDataStream.write(salt1.getBytes());
            srcDataStream.write(src);
            srcDataStream.write(salt2.getBytes());
        }
        catch (IOException e) {
            return null;
        }
        finally {
            try {
                srcDataStream.close();
            }
            catch (IOException e) {

            }
        }

        byte[] srcData = srcDataStream.toByteArray();
        byte[] encodeData = simpleEncode(srcData);

        byte[] data = new byte[encodeData.length + 4];
        System.arraycopy("tlAs".getBytes(), 0, data, 0, 4); // salt颠倒过来
        System.arraycopy(encodeData, 0, data, 4, encodeData.length);
        return data;
    }

    public static String simpleDecode(final String src) {
        return new String(simpleDecode(src.getBytes()));
    }

    public static byte[] simpleDecode(final byte[] src) {
        // 检测是否带了salt
        boolean hasSalt = false;
        byte[] saltHeaderBuffer = { src[0], src[1], src[2], src[3] };
        if (new String(saltHeaderBuffer).equals("tlAs")) {
            hasSalt = true;
        }

        byte[] srcData = null;
        if (hasSalt) {
            srcData = new byte[src.length - 4];
            System.arraycopy(src, 4, srcData, 0, srcData.length);
        }
        else {
            srcData = src;
        }

        // 先把srcData解码出来
        byte[] decodeData = new byte[srcData.length];
        for (int i = 0; i < srcData.length; i++) {
            decodeData[i] = srcData[i];
            // 只处理字母
            if (decodeData[i] >= 65 && decodeData[i] <= 90) {
                if (decodeData[i] % 2 != 0) {
                    decodeData[i]++;
                }
                else {
                    decodeData[i]--;
                }
                decodeData[i] += 32; // 字母颠倒大小写
            }
            else if (decodeData[i] >= 97 && decodeData[i] <= 122) {
                if (decodeData[i] % 2 != 0) {
                    decodeData[i]++;
                }
                else {
                    decodeData[i]--;
                }
                decodeData[i] -= 32; // 字母颠倒大小写
            }
        }
        // 再base64 decode
        decodeData = Base64.getDecoder().decode(decodeData);

        if (!hasSalt) {
            return decodeData;
        }

        // 去掉salt
        byte[] saltLenBuffer = { decodeData[0], decodeData[1], decodeData[2], decodeData[3] };
        int saltLen = saltLenBuffer[0] * 256 * 256 * 256 + saltLenBuffer[1] * 256 * 256 + saltLenBuffer[2] * 256 + saltLenBuffer[3];

        byte[] data = new byte[decodeData.length - 4 - saltLen * 2]; // 数据是一个int的salt长度+salt+真正的数据+salt
        System.arraycopy(decodeData, 4 + saltLen, data, 0, data.length);

        return data;
    }

    public static boolean verRsaSign(Map<String, String> params, String sign, String publicKey) throws InvalidKeyException, InvalidKeySpecException, SignatureException {
        StringBuffer data = new StringBuffer(params.size());
        TreeMap<String, String> sortParams = new TreeMap<String, String>(params);
        for (String name : sortParams.keySet()) {
            String value = sortParams.get(name);
            data.append(name + "=" + value);
        }
        return verRsaSign(data.toString(), sign, publicKey);
    }

    public static boolean verRsaSign(String data, String sign, String publicKey) throws InvalidKeyException, InvalidKeySpecException, SignatureException {
        return verRsaSign(data.getBytes(), sign, publicKey);
    }

    public static boolean verRsaSign(byte[] data, String sign, String publicKey) throws InvalidKeySpecException, InvalidKeyException, SignatureException {
        byte[] publicKeyData = Base64.getDecoder().decode(publicKey.getBytes());
        byte[] signData = Base64.getDecoder().decode(sign.getBytes());

        KeyFactory keyFactory;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        PublicKey pubKey;
        try {
            pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(publicKeyData));
        }
        catch (InvalidKeySpecException e) {
            throw e;
        }

        Signature signature;
        try {
            signature = Signature.getInstance("SHA1withRSA");
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        try {
            signature.initVerify(pubKey);
            signature.update(data);
            return signature.verify(signData);
        }
        catch (InvalidKeyException e) {
            throw e;
        }
        catch (SignatureException e) {
            throw e;
        }
    }
}
