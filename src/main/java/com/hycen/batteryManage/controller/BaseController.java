package com.hycen.batteryManage.controller;

import com.hycen.batteryManage.common.Constants;
import com.hycen.batteryManage.exception.BizException;
import com.hycen.batteryManage.exception.BizExceptionCode;
import com.hycen.batteryManage.util.JwtHelper;
import com.hycen.batteryManage.vo.ResultVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*")
public class BaseController {

    protected Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Resource
    protected HttpSession session;

    @Resource
    protected HttpServletRequest request;

    public String getHeader(String name) throws BizException {
        String headerValue = StringUtils.trim(request.getHeader(name));
        if(Constants.TOKEN.equals(name)){
            if(StringUtils.isNotEmpty(headerValue)){
                headerValue = JwtHelper.verifyAccessTokenFromJWT(headerValue);
            }else{
                throw new BizException(BizExceptionCode.CODE_110002);
            }
        }
        return headerValue;
    }

    public void setNocache(HttpServletResponse response) {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
    }

    public static HttpHeaders getNoCacheHeader(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("CacheControl", "no-cache");
        httpHeaders.set("Pragma", "no-cache");
        httpHeaders.set("Expires", "-1");
        return httpHeaders;
    }

    @ExceptionHandler(value = {BindException.class, BizException.class,IOException.class, Exception.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResultVO handleArithmeticException(Exception ex) {
        ResultVO resultVO = new ResultVO();
        BizException bizException = null;
        if(ex instanceof BizException){
            logger.info("App校验异常:"+ex.getMessage());
            bizException = (BizException)ex;
        }else if(ex instanceof BindException){
            logger.error("数据校验异常",ex.getMessage());
            BindException bindException = (BindException)ex;
            List<ObjectError> errors = bindException.getAllErrors();
            final StringBuilder message = new StringBuilder();

            errors.stream().forEach(e -> {
                message.append(e.getDefaultMessage()).append(",");
            });
            bizException =  new BizException(BizExceptionCode.CODE_110003,message.toString());
        }else if(ex instanceof UnknownAccountException){
            logger.error("账号不存在 "+ex.getMessage());
            bizException = new BizException(BizExceptionCode.CODE_120004, ex.getMessage());
        }else if(ex instanceof IncorrectCredentialsException){
            logger.error("密码不正确 "+ex.getMessage());
            bizException = new BizException(BizExceptionCode.CODE_120003);
        }else if(ex instanceof LockedAccountException){
            logger.error("账号被锁定 "+ex.getMessage());
            bizException = new BizException(BizExceptionCode.CODE_120007);
        }else if(ex instanceof UnauthenticatedException){
            logger.error("未登录: message = "+ex.getMessage());
            bizException = new BizException(BizExceptionCode.CODE_110002);
        }else if(ex instanceof UnauthorizedException || ex instanceof AuthorizationException){
            logger.error("无操作权限: message = "+ex.getMessage());
            bizException = new BizException(BizExceptionCode.CODE_110008);
        }else{
            logger.error("系统异常",ex);
            bizException = new BizException(BizExceptionCode.CODE_100001,ex.getMessage());
        }
        resultVO.setRepmsg(bizException.getCode().getCode(), bizException.getMessage());
        return resultVO;
    }

}