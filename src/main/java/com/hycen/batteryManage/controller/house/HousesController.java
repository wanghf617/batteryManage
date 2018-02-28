package com.hycen.batteryManage.controller.house;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hycen.batteryManage.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/house")
public class HousesController extends BaseController {

    @RequestMapping(value = "/query",method = RequestMethod.POST)
    public JSONObject queryHouses() {

        JSONArray houses = new JSONArray();
        JSONObject house1 = new JSONObject();
        house1.put("id","1");
        house1.put("name","总部");

        JSONObject house2 = new JSONObject();
        house2.put("id","2");
        house2.put("name", "杭州");

        houses.add(house1);
        houses.add(house2);

        JSONObject result = new JSONObject();
        result.put("code",  0);
        result.put("msg", "suc");
        result.put("data", houses);
        result.put("count", 2);

        return result;

    }

}
