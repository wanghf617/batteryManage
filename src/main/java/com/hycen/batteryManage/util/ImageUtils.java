package com.hycen.batteryManage.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xian jie on 17-7-10.
 */
public class ImageUtils {

    private static final int JAVA_DEFAULT_DPI = 72;

    /**
     * createBlankImage
     *
     * @param width
     * @param height
     * @param bgColor
     * @param outputFormat
     * @return
     * @throws IOException
     */
    public static byte[] createBlankImage(final int width, final int height, final Integer bgColor, final IMAGE_TYPE outputFormat) throws IOException {
        BufferedImage bufferedImage = createBlankImage(width, height, bgColor);
        return bufferedImage2ImageData(bufferedImage, outputFormat);
    }

    /**
     * createBlankImage
     *
     * @param width
     * @param height
     * @param bgColor
     * @return
     */
    public static BufferedImage createBlankImage(final int width, final int height, final Integer bgColor) {
        Canvas canvas = new Canvas(width, height, bgColor);
        return canvas.getCanvasImage();
    }

    /**
     * 转换格式
     *
     * @param imageData
     * @param toType
     * @return
     * @throws IOException
     */
    public static byte[] convertImageType(final byte[] imageData, final IMAGE_TYPE toType) throws IOException {
        return convertImageType(imageData, toType, 0xffffff);
    }

    /**
     * 转换格式
     *
     * @param imageData
     * @param toType
     * @param bgColor
     * @return
     * @throws IOException
     */
    public static byte[] convertImageType(final byte[] imageData, final IMAGE_TYPE toType, final Integer bgColor) throws IOException {
        // 把 data 读到 BufferedImage 里
        BufferedImage bufferedImage = imageData2BufferedImage(imageData);
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        Canvas canvas = new Canvas(width, height, bgColor);
        canvas.drawImage(bufferedImage, 0, 0);
        bufferedImage = canvas.getCanvasImage();
        return bufferedImage2ImageData(bufferedImage, toType.value());
    }

    /**
     * 获取图片长宽尺寸
     *
     * @param imageData
     * @return
     */
    public static Map<String, Integer> getImageSize(final byte[] imageData) {
        Image image = new ImageIcon(imageData).getImage();
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        HashMap<String, Integer> item = new HashMap<>();
        item.put("width", width);
        item.put("height", height);
        return item;
    }

    /**
     * 设置背景色
     *
     * @param imageData
     * @param outputFormat
     * @param bgColor
     * @return
     * @throws IOException
     */
    public static byte[] setBackGround(final byte[] imageData, final IMAGE_TYPE outputFormat, final Integer bgColor) throws IOException {
        BufferedImage bufferedImage = imageData2BufferedImage(imageData);
        bufferedImage = setBackGround(bufferedImage, bgColor);
        return bufferedImage2ImageData(bufferedImage, outputFormat);
    }

    /**
     * 设置背景色
     *
     * @param imageData
     * @param bgColor
     * @return
     */
    public static BufferedImage setBackGround(final BufferedImage imageData, final Integer bgColor) {
        int width = imageData.getWidth(null);
        int height = imageData.getHeight(null);
        Canvas canvas = new Canvas(width, height, bgColor);
        canvas.drawImage(imageData, 0, 0);
        return canvas.getCanvasImage();
    }

    /**
     * 合并图片
     *
     * @param frontImage
     * @param backImage
     * @param outputFormat
     * @param x
     * @param y
     * @return
     * @throws IOException
     */
    public static byte[] mergeImage(final byte[] frontImage, final byte[] backImage, final IMAGE_TYPE outputFormat, final int x, final int y) throws IOException {
        BufferedImage fImage = imageData2BufferedImage(frontImage);
        BufferedImage bImage = imageData2BufferedImage(backImage);
        bImage = mergeImage(fImage, bImage, x, y);
        return bufferedImage2ImageData(bImage, outputFormat);
    }

    /**
     * 合并图片
     *
     * @param frontImage
     * @param backImage
     * @param x
     * @param y
     * @return
     */
    public static BufferedImage mergeImage(final BufferedImage frontImage, final BufferedImage backImage, final int x, final int y) {
        int width = backImage.getWidth(null);
        int height = backImage.getHeight(null);
        Canvas canvas = new Canvas(width, height, null);
        canvas.drawImage(backImage, 0, 0);
        canvas.drawImage(frontImage, x, y);
        return canvas.getCanvasImage();
    }

    /**
     * 缩放图片
     *
     * @param imageData
     * @param width
     * @param height
     * @param soften
     * @param bgColor
     * @return
     * @throws IOException
     */
    public static byte[] scaleImage(final byte[] imageData, final IMAGE_TYPE outputFormat, final int width, final int height, final boolean smooth, final boolean soften, final Integer bgColor) throws IOException {
        BufferedImage inputImage = imageData2BufferedImage(imageData);
        BufferedImage scaledImage = scaleImage(inputImage, width, height, smooth, soften, bgColor);
        return bufferedImage2ImageData(scaledImage, outputFormat);
    }

    /**
     * 缩放图片
     *
     * @param imageData
     * @param width
     * @param height
     * @param smooth
     * @param soften
     * @param bgColor
     * @return
     */
    public static BufferedImage scaleImage(final BufferedImage imageData, final int width, final int height, final boolean smooth, final boolean soften, final Integer bgColor) {
        Image originalImage = new ImageIcon(imageData).getImage();
        ;
        Image scaledImage = null; // 缩放后的图片

        // 原始尺寸
        int originalWidth = originalImage.getWidth(null);
        int originalHeight = originalImage.getHeight(null);

        // 画布尺寸
        int canvasWidth = width;
        int canvasHeight = height;

        // 缩放后的尺寸;
        int scaledWidth;
        int scaledHeight;
        boolean scaled;

        // 缩放:
        if (originalWidth == canvasWidth && originalHeight == canvasHeight) {
            // 没有任何缩放
            scaledImage = originalImage;
            scaledWidth = originalWidth;
            scaledHeight = originalHeight;
            scaled = false;
        }
        else {
            // 缩放原图片. 得到缩放后的图片. 如果是横图片，以宽度为标准缩放; 竖图片以高为标准缩放
            // 保证缩放后长宽不会超过画布大小
            int hints = smooth ? Image.SCALE_SMOOTH : Image.SCALE_DEFAULT;
            if (originalWidth >= originalHeight) {
                scaledImage = originalImage.getScaledInstance(canvasWidth, canvasWidth * originalHeight / originalWidth, hints);
            }
            else {
                scaledImage = originalImage.getScaledInstance(canvasHeight * originalWidth / originalHeight, height, hints);
            }
            // This code ensures that all the pixels in the image are loaded.
            scaledImage = new ImageIcon(scaledImage).getImage();
            scaledWidth = scaledImage.getWidth(null);
            scaledHeight = scaledImage.getHeight(null);
            scaled = true;
        }

        // 计算绘制坐标:
        int x = 0;
        int y = 0;
        if (scaledWidth >= scaledHeight) {
            if (scaledHeight < canvasHeight) {
                y = (canvasHeight - scaledHeight) / 2;
            }
        }
        else {
            if (scaledWidth < canvasWidth) {
                x = (canvasWidth - scaledWidth) / 2;
            }
        }

        // 把缩放后的图像画到背景画布上
        Canvas canvas = new Canvas(canvasWidth, canvasHeight, bgColor);
        canvas.drawImage(scaledImage, x, y);
        BufferedImage canvasImage = canvas.getCanvasImage();

        if (scaled && soften) {
            canvasImage = softenImage(canvasImage);
        }

        return canvasImage;
    }

    /**
     * 拷贝图片某区域
     *
     * @param imageData
     * @param startX
     * @param startY
     * @param width
     * @param height
     * @param bgColor
     * @return
     * @throws IOException
     */
    public static byte[] copyImage(final byte[] imageData, final IMAGE_TYPE outputFormat, int startX, int startY, int width, int height, final Integer bgColor) throws IOException {
        BufferedImage bufferedImage = imageData2BufferedImage(imageData);
        bufferedImage = copyImage(bufferedImage, startX, startY, width, height, bgColor);
        return bufferedImage2ImageData(bufferedImage, outputFormat);
    }

    /**
     * 拷贝图片某区域
     *
     * @param imageData
     * @param startX
     * @param startY
     * @param width
     * @param height
     * @param bgColor
     * @return
     */
    public static BufferedImage copyImage(final BufferedImage imageData, int startX, int startY, int width, int height, final Integer bgColor) {
        // 原始尺寸
        int originalWidth = imageData.getWidth(null);
        int originalHeight = imageData.getHeight(null);

        if (startX > originalWidth - 1) {
            startX = originalWidth - 1;
        }
        if (startX < 0) {
            startX = 0;
        }

        if (startY > originalHeight - 1) {
            startY = originalHeight - 1;
        }
        if (startY < 0) {
            startY = 0;
        }

        int endX = startX + width;
        if (endX > originalWidth - 1) {
            endX = originalWidth - 1;
        }

        int endY = startY + height;
        if (endY > originalHeight - 1) {
            endY = originalHeight - 1;
        }

        width = endX - startX;
        if (width < 1) {
            width = 1;
        }

        height = endY - startY;
        if (height < 1) {
            height = 1;
        }

        BufferedImage subImage = imageData.getSubimage(startX, startY, width, height);
        Canvas canvas = new Canvas(width, height, bgColor);
        canvas.drawImage(subImage, 0, 0);
        return canvas.getCanvasImage();
    }

    /**
     * soften image
     *
     * @param imageData
     * @return
     */
    public static BufferedImage softenImage(final BufferedImage imageData) {
        float softenFactor = 0.05f;
        float[] softenArray = { 0, softenFactor, 0, softenFactor, 1 - (softenFactor * 4), softenFactor, 0, softenFactor, 0 };
        Kernel kernel = new Kernel(3, 3, softenArray);
        ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
        return cOp.filter(imageData, null);
    }



    /**
     * 透明化处理图片数据(只能输出成png)
     *
     * @param imageData
     * @param transparentColor
     * @param tolerance
     * @return
     * @throws IOException
     */
    public static byte[] transparent(final byte[] imageData, final int transparentColor, final int tolerance) throws IOException {
        int[] transparentColors = { transparentColor };
        return transparent(imageData, transparentColors, tolerance);
    }

    /**
     * 透明化处理图片数据(只能输出成png)
     *
     * @param imageData
     * @param transparentColors
     * @param tolerance
     * @return
     * @throws IOException
     */
    public static byte[] transparent(final byte[] imageData, final int[] transparentColors, final int tolerance) throws IOException {
        BufferedImage bufferedImage = imageData2BufferedImage(imageData);
        bufferedImage = transparent(bufferedImage, transparentColors, tolerance);
        return bufferedImage2ImageData(bufferedImage, IMAGE_TYPE.PNG);
    }

    /**
     * 透明化处理
     *
     * @param imageData
     * @param transparentColor 透明色
     * @param tolerance 透明色容差
     * @return
     */
    public static BufferedImage transparent(final BufferedImage imageData, final int transparentColor, final int tolerance) {
        int[] transparentColors = { transparentColor };
        return processAlpha(imageData, transparentColors, tolerance, PROCESS_ALPHA_MODEL.TRANSPARENT);
    }

    /**
     * 透明化处理
     *
     * @param imageData
     * @param transparentColors 透明色
     * @param tolerance 颜色容差
     * @return
     */
    public static BufferedImage transparent(final BufferedImage imageData, final int[] transparentColors, final int tolerance) {
        return processAlpha(imageData, transparentColors, tolerance, PROCESS_ALPHA_MODEL.TRANSPARENT);
    }

    /**
     * 保留颜色. 其他的都设为透明.处理图片数据(只能输出成png)
     *
     * @param imageData
     * @param holdColor
     * @param tolerance
     * @return
     * @throws IOException
     */
    public static byte[] holdColor(final byte[] imageData, final int holdColor, final int tolerance) throws IOException {
        int[] holdColors = { holdColor };
        return holdColor(imageData, holdColors, tolerance);
    }

    /**
     * 保留颜色. 其他的都设为透明.处理图片数据(只能输出成png)
     *
     * @param imageData
     * @param holdColors
     * @param tolerance
     * @return
     * @throws IOException
     */
    public static byte[] holdColor(final byte[] imageData, final int[] holdColors, final int tolerance) throws IOException {
        BufferedImage bufferedImage = imageData2BufferedImage(imageData);
        bufferedImage = holdColor(bufferedImage, holdColors, tolerance);
        return bufferedImage2ImageData(bufferedImage, IMAGE_TYPE.PNG);
    }

    /**
     * 保留颜色. 其他的都设为透明
     *
     * @param imageData
     * @param holdColor 要保留的颜色
     * @param tolerance 颜色容差
     * @return
     */
    public static BufferedImage holdColor(final BufferedImage imageData, final int holdColor, final int tolerance) {
        int[] holdColors = { holdColor };
        return processAlpha(imageData, holdColors, tolerance, PROCESS_ALPHA_MODEL.HOLD);
    }

    /**
     * 保留颜色. 其他的都设为透明
     *
     * @param imageData
     * @param holdColors 要保留的颜色
     * @param tolerance 颜色容差
     * @return
     */
    public static BufferedImage holdColor(final BufferedImage imageData, final int[] holdColors, final int tolerance) {
        return processAlpha(imageData, holdColors, tolerance, PROCESS_ALPHA_MODEL.HOLD);
    }

    /**
     * 图片字节数组转BufferedImage
     *
     * @param imageData
     * @return
     * @throws IOException
     */
    public static BufferedImage imageData2BufferedImage(final byte[] imageData) throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(imageData);
        try {
            return ImageIO.read(inputStream);
        }
        catch (IOException e) {
            throw e;
        }
        finally {
            try {
                inputStream.close();
            }
            catch (IOException e) {

            }
        }
    }

    /**
     * BufferedImage转字节数组
     *
     * @param imageData
     * @param imageType
     * @return
     * @throws IOException
     */
    private static byte[] bufferedImage2ImageData(final BufferedImage imageData, final IMAGE_TYPE imageType) throws IOException {
        return bufferedImage2ImageData(imageData, imageType.value());
    }

    /**
     * 压缩png数据
     *
     * @param imageData
     * @return
     * @throws IOException
     */
    /*
     * public static byte[] pngCompress(final byte[] imageData) throws
     * IOException { ByteArrayInputStream inputStream = new
     * ByteArrayInputStream(imageData); ByteArrayOutputStream outputStream = new
     * ByteArrayOutputStream(); IOException exception = null; try {
     * PngCompressor.compress(inputStream, outputStream); } catch (IOException
     * e) { exception = e; } finally { try { inputStream.close(); } catch
     * (IOException e) {
     *
     * } } if (exception != null) { try { outputStream.close(); } catch
     * (IOException e) { ; } throw exception; }
     *
     * try { outputStream.flush(); return outputStream.toByteArray(); } catch
     * (IOException e) { throw e; } finally { try { outputStream.close(); }
     * catch (IOException e) { ; } } }
     */

    /**
     * BufferedImage转字节数组
     *
     * @param imageData
     * @param imageType
     * @return
     * @throws IOException
     */
    private static byte[] bufferedImage2ImageData(final BufferedImage imageData, final String imageType) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(imageData, imageType, outputStream);
            outputStream.flush();
            byte[] data = outputStream.toByteArray();
            return data;
        }
        catch (IOException e) {
            throw e;
        }
        finally {
            try {
                outputStream.close();
            }
            catch (IOException e) {

            }
        }
    }

    /**
     * 处理alpha透明. 有两种处理方法, 一种是范围内的颜色设置成透明; 另一种相反, 不在范围内的颜色设置成透明
     *
     * @param imageData
     * @param compareColors
     * @param tolerance
     * @param mode
     * @return
     */
    private static BufferedImage processAlpha(final BufferedImage imageData, final int[] compareColors, final int tolerance, final PROCESS_ALPHA_MODEL mode) {
        ImageIcon imageIcon = new ImageIcon(imageData);
        BufferedImage bufferedImage = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight(), BufferedImage.TYPE_4BYTE_ABGR);

        Graphics2D g2D = (Graphics2D) bufferedImage.getGraphics();
        g2D.drawImage(imageIcon.getImage(), 0, 0, imageIcon.getImageObserver());

        Raster raster = bufferedImage.getData();
        ColorModel colorModel = bufferedImage.getColorModel();

        int alpha = 0;
        int rgb = 0;

        int startY = bufferedImage.getMinY();
        int endY = bufferedImage.getHeight();
        int startX = bufferedImage.getMinX();
        int endX = bufferedImage.getWidth();

        for (int y = startY; y < endY; y++) {
            for (int x = startX; x < endX; x++) {
                // 获取当前像素的alpha值
                Object dataElements = null;
                dataElements = raster.getDataElements(x, y, dataElements);
                int currentAlpha = colorModel.getAlpha(dataElements);

                // 如果当前像素已经是透明的, 就不用去处理了
                if (currentAlpha == 0) {
                    continue;
                }

                rgb = bufferedImage.getRGB(x, y);

                // 判断当前像素颜色值是不是在范围内
                boolean isInRange = false;
                for (int i = 0; i < compareColors.length; i++) {
                    int compareColor = compareColors[i];
                    if (isColorInRange(rgb, compareColor, tolerance)) {
                        isInRange = true;
                        break;
                    }
                }
                // 决定当前使用alpha值
                if (mode == PROCESS_ALPHA_MODEL.TRANSPARENT) {
                    alpha = isInRange ? 0 : currentAlpha;
                }
                else {
                    alpha = isInRange ? currentAlpha : 0;
                }

                rgb = (alpha << 24) | (rgb & 0x00ffffff);
                bufferedImage.setRGB(x, y, rgb);
            }
        }
        g2D.drawImage(bufferedImage, 0, 0, imageIcon.getImageObserver());

        return bufferedImage;
    }

    /**
     * 测试一个color是否在比较范围内
     *
     * @param testColor
     * @param compareColor
     * @param tolerance
     * @return
     */
    private static boolean isColorInRange(final int testColor, final int compareColor, final int tolerance) {
        // 要检测的颜色的3分量
        int[] testRGB = new int[3];
        testRGB[0] = (testColor & 0xff0000) >> 16; // red
        testRGB[1] = (testColor & 0x00ff00) >> 8; // green
        testRGB[2] = (testColor & 0x0000ff); // blue

        // 把对照颜色也分成3分量
        int[] compareRGB = new int[3];
        compareRGB[0] = (compareColor & 0xff0000) >> 16;
        compareRGB[1] = (compareColor & 0x00ff00) >> 8;
        compareRGB[2] = (compareColor & 0x0000ff);

        // 如果要检测的颜色等于要比较的颜色, 或者在要比较的颜色的一个正负容差范围内, 就返回True
        // 算出3分量各自的范围
        int[] compareRGBMin = new int[3];
        int[] compareRGBMax = new int[3];
        int x = (tolerance == 0) ? 0 : (int) ((tolerance / 100f) * 255);
        for (int i = 0; i < compareRGB.length; i++) {
            compareRGBMin[i] = compareRGB[i];
            compareRGBMax[i] = compareRGB[i];
            if (tolerance != 0) {
                compareRGBMin[i] -= x;
                compareRGBMax[i] += x;
                if (compareRGBMin[i] < 0) {
                    compareRGBMin[i] = 0;
                }
                if (compareRGBMax[i] > 255) {
                    compareRGBMax[i] = 255;
                }
            }

            if (testRGB[i] < compareRGBMin[i] || testRGB[i] > compareRGBMax[i]) {
                return false;
            }
        }

        return true;
    }

    /**
     * 生成带有签名框企个人图片
     *
     * @param sourceImage
     * @param borderImg
     * @param uid
     * @return
     */
    public static byte[] drawResultImageForPerson(byte[] sourceImage, byte[] borderImg, String uid) throws IOException, NoSuchAlgorithmException {
        ByteArrayInputStream borderInputStream = new ByteArrayInputStream(borderImg);
        ByteArrayInputStream sourceInputStream = new ByteArrayInputStream(sourceImage);

        BufferedImage borderImage = ImageIO.read(borderInputStream);
        String hash = EncodeUtils.md5(EncodeUtils.sha256(sourceImage) + uid);
        // 获得签章的图形对象
        BufferedImage sourceBufferedImage = ImageIO.read(sourceInputStream);
        // 定义新的图像
        BufferedImage result = new BufferedImage(borderImage.getWidth(), borderImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D resultImage = result.createGraphics();
        // 设置字体颜色
        resultImage.setBackground(Color.BLACK);
        resultImage.setColor(Color.BLACK);
        // 放入边框图像
        resultImage.drawImage(borderImage.getScaledInstance(borderImage.getWidth(), borderImage.getHeight(), Image.SCALE_SMOOTH), 70, 0, null, null);
        // 将签名图像放入这个文件夹中去,并调整大小
        resultImage.drawImage(sourceBufferedImage.getScaledInstance(300, 140, Image.SCALE_SMOOTH), 180, 65, null);
        // MD5值的放置
        Font f = new Font("微软雅黑", Font.CENTER_BASELINE, 28);
        resultImage.setFont(f);
        // 写入MD5值
        resultImage.drawString(hash.substring(0, 16), 195, 242);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(result, "png", out);
        byte[] fileByte = out.toByteArray();
        out.close();
        borderInputStream.close();
        sourceInputStream.close();
        return fileByte;
    }

    /**
     * 生成带有签名框企业公章图片
     *
     * @param sealImage 企业公章图片
     * @param borderImg 企业边框
     * @param uid 用户id
     * @return
     */
    public static byte[] drawResultImageForEnterprise(byte[] sealImage, byte[] borderImg, String uid) throws IOException, NoSuchAlgorithmException {
        ByteArrayInputStream borderInputStream = new ByteArrayInputStream(borderImg); // 将b作为输入流
        ByteArrayInputStream sourceInputStream = new ByteArrayInputStream(sealImage); // 将b作为输入流

        BufferedImage borderImage = ImageIO.read(borderInputStream);
        String fileMD5 = EncodeUtils.sha256(sealImage);
        String MD5 = EncodeUtils.md5(fileMD5 + uid);
        // 获得签章的图形对象
        BufferedImage sourceBufferedImage = ImageIO.read(sourceInputStream);
        // 定义新的图像
        BufferedImage result = new BufferedImage(borderImage.getWidth() + 50, borderImage.getHeight() - 20, BufferedImage.TYPE_INT_ARGB);
        Graphics2D resultImage = result.createGraphics();
        // 设置字体颜色
        resultImage.setBackground(Color.BLACK);
        resultImage.setColor(Color.BLACK);
        // MD5值的放置
        Font f = new Font("微软雅黑", Font.CENTER_BASELINE, 28);
        resultImage.setFont(f);
        // 放入边框图像
        resultImage.drawImage(borderImage.getScaledInstance(borderImage.getWidth(), borderImage.getHeight(), Image.SCALE_SMOOTH), 70, 0, null, null);
        // 放入公章图像并调整大小
        resultImage.drawImage(sourceBufferedImage.getScaledInstance(300, 300, Image.SCALE_SMOOTH), 190, 65, null);
        // 写入MD5值
        resultImage.drawString(MD5.substring(0, 16), 195, 413);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(result, "png", out);
        byte[] fileByte = out.toByteArray();
        out.close();
        borderInputStream.close();
        sourceInputStream.close();
        return fileByte;
    }

    /**
     * 生成带有签名框和二维码个人图片
     *
     * @param sourceImage
     * @param borderImg
     * @param qrCode
     * @param uid
     * @return
     */
    public static byte[] drawResultImageForPerson(byte[] sourceImage, byte[] borderImg, byte[] qrCode, String uid) throws IOException, NoSuchAlgorithmException {
        ByteArrayInputStream borderInputStream = new ByteArrayInputStream(borderImg); // 将b作为输入流；
        ByteArrayInputStream sourceInputStream = new ByteArrayInputStream(sourceImage); // 将b作为输入流；
        ByteArrayInputStream qrCodeInputStream = new ByteArrayInputStream(qrCode); // 将b作为输入流；
        // 获取边框的图像对象
        BufferedImage borderImage = ImageIO.read(borderInputStream);
        String fileMD5 = EncodeUtils.sha256(sourceImage);
        String MD5 = EncodeUtils.md5(fileMD5 + uid);
        // 获得签章的图形对象
        BufferedImage sourceBufferedImage = ImageIO.read(sourceInputStream);
        // 定义新的图像
        BufferedImage result = new BufferedImage(borderImage.getWidth(), borderImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D resultImage = result.createGraphics();
        // 设置字体颜色
        resultImage.setBackground(Color.BLACK);
        resultImage.setColor(Color.BLACK);
        // 设置字体
        Font f = new Font("微软雅黑", Font.CENTER_BASELINE, 28);
        resultImage.setFont(f);
        // 放入边框图像
        resultImage.drawImage(borderImage.getScaledInstance(borderImage.getWidth(), borderImage.getHeight(), Image.SCALE_SMOOTH), 70, 0, null, null);
        // 将签名图像放入这个文件夹中去,并调整大小
        resultImage.drawImage(sourceBufferedImage.getScaledInstance(300, 140, Image.SCALE_SMOOTH), 180, 65, null);
        BufferedImage qrImage = ImageIO.read(qrCodeInputStream);
        // 将qr图像放入这个文件夹中去,并调整大小
        resultImage.drawImage(qrImage.getScaledInstance(150, 150, Image.SCALE_SMOOTH), 29, 55, null);

        // 写入MD5值
        resultImage.drawString(MD5.substring(0, 16), 195, 242);
        // MD5值不写如公章图片中
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(result, "png", out);
        byte[] fileByte = out.toByteArray();
        out.close();
        borderInputStream.close();
        sourceInputStream.close();
        qrCodeInputStream.close();
        return fileByte;
    }

    /**
     * 生成带有签名框和二维码企业公章图片
     *
     * @param sealImage
     * @param borderImg
     * @param qrCode
     * @param uid
     * @return
     */
    public static byte[] drawResultImageForEnterprise(byte[] sealImage, byte[] borderImg, byte[] qrCode, String uid) throws IOException, NoSuchAlgorithmException {
        ByteArrayInputStream borderInputStream = new ByteArrayInputStream(borderImg);
        ByteArrayInputStream sourceInputStream = new ByteArrayInputStream(sealImage);
        ByteArrayInputStream qrCodeInputStream = new ByteArrayInputStream(qrCode);

        // 获取边框的图像对象
        BufferedImage borderImage = ImageIO.read(borderInputStream);
        String fileMD5 = EncodeUtils.sha256(sealImage);
        String MD5 = EncodeUtils.md5(fileMD5 + uid);
        // 获得签章的图形对象
        BufferedImage sourceBufferedImage = ImageIO.read(sourceInputStream);
        // 定义新的图像
        BufferedImage result = new BufferedImage(borderImage.getWidth() + 50, borderImage.getHeight() - 20, BufferedImage.TYPE_INT_ARGB);
        Graphics2D resultImage = result.createGraphics();
        // 放入公章图像并调整大小
        resultImage.drawImage(sourceBufferedImage.getScaledInstance(300, 300, Image.SCALE_SMOOTH), 190, 65, null);
        // 设置字体颜色
        resultImage.setBackground(Color.BLACK);
        resultImage.setColor(Color.BLACK);
        // 设置字体
        Font f = new Font("微软雅黑", Font.CENTER_BASELINE, 28);
        resultImage.setFont(f);
        // 放入边框图像
        resultImage.drawImage(borderImage, 70, 0, null, null);
        BufferedImage qrImage = ImageIO.read(qrCodeInputStream);
        // 放入qr图像并调整大小
        resultImage.drawImage(qrImage.getScaledInstance(150, 150, Image.SCALE_SMOOTH), 30, 145, null);
        // 写入MD5值
        resultImage.drawString(MD5.substring(0, 16), 195, 413);
        // MD5值不写如公章图片中
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(result, "png", out);
        byte[] fileByte = out.toByteArray();
        out.close();
        borderInputStream.close();
        sourceInputStream.close();
        qrCodeInputStream.close();
        return fileByte;
    }

    /**
     * 生成带有透明白边企业公章图片
     *
     * @param sealImg
     * @return
     */
    public static byte[] drawResultImageForEnterprise(byte[] sealImg) throws IOException, NoSuchAlgorithmException {
        ByteArrayInputStream sealImage = new ByteArrayInputStream(sealImg);
        // 获得签章的图形对象
        BufferedImage sourceBufferedImage = ImageIO.read(sealImage);
        // 定义新的图像
        BufferedImage result = new BufferedImage(545, 442, BufferedImage.TYPE_INT_ARGB);
        Graphics2D resultImage = result.createGraphics();
        // 放入公章图像并调整大小
        resultImage.drawImage(sourceBufferedImage.getScaledInstance(300, 300, Image.SCALE_SMOOTH), 190, 65, null);
        // 设置字体颜色
        resultImage.setBackground(Color.BLACK);
        resultImage.setColor(Color.BLACK);
        // 设置字体
        Font f = new Font("微软雅黑", Font.CENTER_BASELINE, 28);
        resultImage.setFont(f);
        // 写入MD5值
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(result, "png", out);
        byte[] fileBytes = out.toByteArray();
        out.close();
        sealImage.close();
        return fileBytes;
    }

    /**
     * 生成带有透明白边个人签名图片
     *
     * @param sealImg
     * @return
     */
    public static byte[] drawResultImageForPerson(byte[] sealImg) throws IOException, NoSuchAlgorithmException {
        ByteArrayInputStream sealImage = new ByteArrayInputStream(sealImg);
        // 获得签章的图形对象
        BufferedImage sourceBufferedImage = ImageIO.read(sealImage);
        // 定义新的图像
        BufferedImage result = new BufferedImage(545, 442, BufferedImage.TYPE_INT_ARGB);
        Graphics2D resultImage = result.createGraphics();
        // 放入公章图像并调整大小
        resultImage.drawImage(sourceBufferedImage.getScaledInstance(300, 300, Image.SCALE_SMOOTH), 180, 65, null);
        // 设置字体颜色
        resultImage.setBackground(Color.BLACK);
        resultImage.setColor(Color.BLACK);
        // 设置字体
        Font f = new Font("微软雅黑", Font.CENTER_BASELINE, 28);
        resultImage.setFont(f);
        // 写入MD5值
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(result, "png", out);
        byte[] fileBytes = out.toByteArray();
        out.close();
        sealImage.close();
        return fileBytes;
    }

    /**
     * 用名称创建签名图片
     *
     * @param name
     * @return
     */
    public static byte[] graphicsGeneration(String name) {
        byte[] b = null;
        // 定义一个数组，根据名字的长度来决定图片生成的大小
        int imageXs[] = { 0, 100, 60, 40, 15, 5, 10, 5, 5, 5, 5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3 };
        int imageYs[] = { 0, 115, 105, 95, 95, 95, 95, 85, 85, 85, 85, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40 };
        // 根据字的大小生成图片大小
        int fontSizes[] = { 0, 100, 85, 75, 65, 55, 45, 40, 35, 30, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28 };
        int imageWidth = 300;// 图片的宽度
        int imageHeight = 150;// 图片的高度
        Font font = new Font("宋体", Font.CENTER_BASELINE, fontSizes[name.length()]);

        BufferedImage result = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = result.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, imageWidth, imageHeight);// 填充整个屏幕
        graphics.setColor(Color.BLACK);
        graphics.setFont(font);
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 1.0f));
        // 超过10个字换行显示
        if (name.length() <= 10) {
            graphics.drawString(name, imageXs[name.length()], imageYs[name.length()]);
        }
        else {// 字数大于10
            StringBuilder sb = new StringBuilder(name);
            if (name.length() % 2 == 0) {// 偶数
                sb.insert(10, "\n");
            }
            if (name.length() % 2 != 0) {// 奇数
                sb.insert(9, "\n");
            }
            for (String line : sb.toString().split("\n"))
                graphics.drawString(line, imageXs[name.length()], imageYs[name.length()] += graphics.getFontMetrics().getHeight());
        }
        // 将签名图像放入这个文件夹中去,并调整大小
        graphics.drawImage(result.getScaledInstance(result.getWidth(), result.getHeight(), Image.SCALE_SMOOTH), 0, 0, null);
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(result, "png", out);
            b = out.toByteArray();
            out.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }

    public static byte[] makefile(byte[] imageData) {
        try {
            // 获取边框的图像对象
            ByteArrayInputStream in = new ByteArrayInputStream(imageData); // 将b作为输入流；
            BufferedImage signImage = ImageIO.read(in);
            // 定义新的图像
            BufferedImage result = new BufferedImage(signImage.getWidth(), signImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D resultImage = result.createGraphics();

            // 将签名图像放入这个文件夹中去,并调整大小
            resultImage.drawImage(signImage.getScaledInstance(signImage.getWidth(), signImage.getHeight(), Image.SCALE_SMOOTH), 0, 0, null);
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            ImageIO.write(result, "png", out);

            byte[] fileByte = out.toByteArray();
            out.close();
            in.close();
            return fileByte;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] convert(byte[] imageData) {
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(imageData); // 将b作为输入流；
            BufferedImage image = ImageIO.read(in);
            ImageIcon imageIcon = new ImageIcon(image);
            BufferedImage bufferedImage = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight(), BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D g2D = (Graphics2D) bufferedImage.getGraphics();
            g2D.drawImage(imageIcon.getImage(), 0, 0, imageIcon.getImageObserver());

            Raster raster = bufferedImage.getData();
            ColorModel colorModel = bufferedImage.getColorModel();

            int alpha = 0;
            for (int y = bufferedImage.getMinY(); y < bufferedImage.getHeight(); y++) {
                for (int x = bufferedImage.getMinX(); x < bufferedImage.getWidth(); x++) {
                    // 获取当前像素的alpha值
                    Object dataElements = null;
                    dataElements = raster.getDataElements(x, y, dataElements);
                    int currentAlpha = colorModel.getAlpha(dataElements);

                    // 如果当前像素已经是透明的, 就不用去处理了
                    if (currentAlpha == 0) {
                        continue;
                    }

                    int rgb = bufferedImage.getRGB(x, y);
                    if (colorInRangeForUser(rgb))
                        alpha = 0;
                    else {
                        alpha = currentAlpha;
                    }
                    rgb = (alpha << 24) | (rgb & 0x00ffffff);
                    bufferedImage.setRGB(x, y, rgb);
                }
            }
            g2D.drawImage(bufferedImage, 0, 0, imageIcon.getImageObserver());

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", out);

            byte[] fileByte = out.toByteArray();
            out.close();
            in.close();
            return fileByte;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean colorInRangeForUser(int color) {
        int red = (color & 16711680) >> 16;
        int green = (color & '\uff00') >> 8;
        int blue = color & 255;
        return red >= 230 && green >= 230 && blue >= 230;
    }

    private enum PROCESS_ALPHA_MODEL {
        TRANSPARENT, HOLD
    }

    public enum IMAGE_TYPE {
        JPEG("jpeg"), PNG("png"), BMP("bmp"), GIF("gif");

        private String value = "";

        private IMAGE_TYPE(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }

    private static class Canvas {
        private BufferedImage canvasImage = null;
        private Graphics2D canvasGraphics = null;
        private Integer bgColor = null;
        private boolean isSetAlphaComposite = false;
        private int width = 0;
        private int height = 0;

        public Canvas(int width, int height, Integer bgColor) {
            this.width = width;
            this.height = height;
            this.bgColor = bgColor;
            init();
        }

        public BufferedImage getCanvasImage() {
            canvasGraphics.dispose();
            return canvasImage;
        }

        public void drawImage(Image image, int x, int y) {
            if ((bgColor == null || bgColor < 0) && !isSetAlphaComposite) {
                AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC, 1.0f);
                canvasGraphics.setComposite(ac);
                isSetAlphaComposite = true;
            }
            ImageIcon imageIcon = new ImageIcon(image);
            imageIcon.getImageObserver();
            canvasGraphics.drawImage(image, x, y, imageIcon.getImageObserver());
            // canvasGraph.dispose();
        }

        private void init() {
            canvasImage = null;
            canvasGraphics = null;
            isSetAlphaComposite = false;

            if (bgColor != null && bgColor >= 0) {
                canvasImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                canvasGraphics = canvasImage.createGraphics();

                Color color = new Color(bgColor);
                canvasGraphics.setColor(color);
                canvasGraphics.fillRect(0, 0, width, height);
            }
            else {
                canvasImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                canvasGraphics = canvasImage.createGraphics();

                AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC, 0);
                canvasGraphics.setComposite(ac);
                canvasGraphics.fillRect(0, 0, width, height);
            }
        }
    }
}
