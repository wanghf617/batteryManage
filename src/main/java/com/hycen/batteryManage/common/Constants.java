package com.hycen.batteryManage.common;

import java.io.File;

public class Constants {

    public static final String TOKEN = "access_token";

    public static final String APPLICATION_NAME = "BatteryManageService";

    public static final String STORAGE_LOCATION = "storage.location";

    public static final String BESTSIGN_APPSECRET = "fi8YzULhEvfGdfJmN5iqStBrm2XH5xnHCH6R0uQe2LNvzlwVYjDCLdo5vt8R2G7W";
    /**
     * 短信验证码过期时间（单位秒）
     */
    public static final int SMS_CODE_EXPIRE_SECONDS = 180;

    /**
     * 验证码验证通过过期时间（单位秒）
     */
    public static final int CAPTCHA_PASS_EXPIRE_SECONDS = 180;

    /**
     * http连接超时过期时间（单位毫秒）
     */
    public static final int HTTP_CONNECTION_REQUEST_TIMEOUT = 3000;

    /**
     * http socket连接超时过期时间（单位毫秒）
     */
    public static final int HTTP_SOCKET_TIMEOUT = 3000;


    /**
     * 图片验证码过期时间（单位秒）
     */
    public static final int IMAGE_CAPTCHA_EXPIRE_SECONDS = 120;

    /**
     * 默认登录密码
     */
    public static final String DEFATL_LOGIN_PASSWORD = "123456";

    public static final int USER_LOGIN_EXPIRE_SECONDS = 4*60*60;//登录信息保存4小时

    private static String tempDir = "";

    public static String getTempDir() {
        if (tempDir == null || tempDir.length() < 1) {
            tempDir = getDefaultTempDir();
            return tempDir;
        }
        return tempDir;
    }

    private static String getDefaultTempDir() {
        String tempDirRootPath = null;
        if (System.getProperty("catalina.home") != null && System.getProperty("catalina.home").length() > 0) {
            File tempDirRoot = new File(System.getProperty("catalina.home") + File.separator + "temp");
            if (tempDirRoot.exists()) {
                tempDirRootPath = tempDirRoot.getPath();
            }
        }
        if (tempDirRootPath == null) {
            tempDirRootPath = System.getProperty("java.io.tmpdir");
        }

        File tempDir = new File(tempDirRootPath + File.separator + "sdk");
        if (!tempDir.exists()) {
            tempDir.mkdir();
            tempDir = new File(tempDirRootPath + File.separator + "sdk");
            if (tempDir.exists()) {
                return tempDir.getAbsolutePath();
            }
        }
        else {
            return tempDir.getAbsolutePath();
        }

        return "/tmp/";
    }
}
