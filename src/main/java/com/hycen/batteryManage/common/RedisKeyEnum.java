package com.hycen.batteryManage.common;

public enum RedisKeyEnum {

    USER_ID, //用户信息
    TOKEN, //用户访问token
    SESSION_ID, //SESSION_ID
    LOCK_ACCOUNT, //云文件用户关联
    QRCODE_GENERAL, //二维码
    QRCODE_IMAGE,//二维码图片
    NEXT_NOTICE_LEFT_SECOND, //通知剩余时间
    VALIDATE_TIME, //校验时间
    IMAGE_CAPTCHA_CODE, //图形验证码
    VERIFY_CODE_SENDTO, //短信验证码发送目标
    VERIFY_CODE, //短信验证码key

    WX_ACCOUNT, //微信服务号
    WX_ACCOUNT_ACCESSTOKEN, //微信账号访问token
    WX_ACCOUNT_JSTICKET, //微信js-ticket
    WX_SESSION_OPENID, //微信接口访问的openid
    WX_ACCOUNT_OAUTH_TOKEN, // 微信OAUTH token
    WX_BASE_MSG,
    WX_TEXT_MSG,
    WX_NEWS_MSG,
    WX_TEMPLATE_MSG,

    SNS_USER_ONLINE, //IM用户在线状态

    EXCEPTION_LIMIT, //异常限制

    //微信相关
    MP_ACCESS_TOKEN_KEY,
    MP_JSAPI_TICKET_KEY,
    MP_CARDAPI_TICKET_KEY,

    WX_CP_ACCESS_TOKEN ,
    WX_CP_ACCESS_TOKEN_EXPIRES_TIME ,
    WX_CP_JS_API_TICKET ,
    WX_CP_JS_API_TICKET_EXPIRES_TIME ,
    ;

    private static final String APP_KEY = "weixin_"; //app redis key 前缀

    public String getCompositeKey(Object obj) {
        StringBuilder sb = new StringBuilder(APP_KEY+this.name());
        if (obj != null) {
            sb.append(':').append(obj);
        }
        return sb.toString();
    }

}
