package com.hycen.batteryManage.util;

import com.hycen.batteryManage.common.Constants;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class HttpClientUtils {
    public static CloseableHttpClient createHttpClient(PoolingHttpClientConnectionManager connManager) {
        //HttpHost httpHost = new HttpHost("10.211.55.4", 8888);
        CloseableHttpClient httpClient = HttpClients.custom()
                //.setProxy(httpHost)
                .setConnectionManager(connManager)
                .disableContentCompression()
                .setSSLContext(getSslcontext())
                .setDefaultRequestConfig(getRequestConfig())
                .build();
        return httpClient;
    }
    
    public static PoolingHttpClientConnectionManager createHttpClientConnectionManager() {
        SSLConnectionSocketFactory sslConnectionSocketFactory = null;
        try {
            sslConnectionSocketFactory = new SSLConnectionSocketFactory(SSLContext.getDefault());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
                .register("https", sslConnectionSocketFactory)
                .register("http", new PlainConnectionSocketFactory())
                .build();
        PoolingHttpClientConnectionManager cm =new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        cm.setMaxTotal(500);
        cm.setDefaultMaxPerRoute(500);
        return cm;
    }

    private static RequestConfig getRequestConfig() {
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(Constants.HTTP_CONNECTION_REQUEST_TIMEOUT)
                .setSocketTimeout(Constants.HTTP_SOCKET_TIMEOUT)
                .build();
        return defaultRequestConfig;
    }

    private static SSLContext getSslcontext() {
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("TLS");
            TrustManager tm = new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            sslContext.init(null, new TrustManager[] { tm }, null);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return sslContext;
    }
}
