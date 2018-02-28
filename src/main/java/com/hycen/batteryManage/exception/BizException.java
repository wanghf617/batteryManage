package com.hycen.batteryManage.exception;

public class BizException extends Exception {

    private BizExceptionCode code;
    private String message;

    public BizException(BizExceptionCode code) {
        this.code = code;
        message = code.getMessage();
    }

    public BizException(BizExceptionCode code, String message) {
        this.code = code;
        this.message = message;
    }

    public void setCode(BizExceptionCode code) {
        this.code = code;
        this.message = code.getMessage();
    }

    public BizExceptionCode getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return this.code + ":" + this.getMessage();
    }
}
