package com.hycen.batteryManage.controller.itemPackages;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hycen.batteryManage.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/package")
public class packagesController extends BaseController {

    @RequestMapping(value = "/query",method = RequestMethod.POST)
    public JSONObject queryHouses() {

        JSONArray packages = new JSONArray();
        JSONObject p1 = new JSONObject();
        p1.put("id","1");
        p1.put("name","4050套餐");

        JSONObject p2 = new JSONObject();
        p2.put("id","2");
        p2.put("name", "5050套餐");

        packages.add(p1);
        packages.add(p2);

        JSONObject result = new JSONObject();
        result.put("code",  0);
        result.put("msg", "query package suc");
        result.put("data", packages);
        result.put("count", 2);

        return result;

    }

}
