package com.hycen.batteryManage.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * 页面跳转
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminPageController {


    /**
     * 跳转到登录页面
     */
    @RequestMapping(value = "/**", method = RequestMethod.GET)
    public String Index() {
        return "/admin/index";
    }

    /**
     * 跳转到登录页面
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String toLoginPage() {
        return "/admin/login";
    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String toIndex(){
        return "/admin/index";
    }

    @RequestMapping(value = "/main",method = RequestMethod.GET)
    public String toMain(){
        return "/admin/main";
    }

    @RequestMapping(value = "/menuManage",method = RequestMethod.GET)
    public String toMenuManage(){
        return "/admin/menuManage";
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String toTest(){
        return "/admin/test";
    }
    /**
     * 跳转到form页面
     */
    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String toFormPage() {
        return "/admin/form";
    }
    /**
     *跳转电池管理页面
     */
    @RequestMapping(value = "/batteryManage", method = RequestMethod.GET)
    public String toBatteryManagePage(){
        return "/admin/batteryManage";
    }
}
