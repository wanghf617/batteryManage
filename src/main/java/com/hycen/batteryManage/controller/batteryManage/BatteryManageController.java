package com.hycen.batteryManage.controller.batteryManage;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hycen.batteryManage.common.annotation.SystemControllerLog;
import com.hycen.batteryManage.common.rule.ParamsChecker;
import com.hycen.batteryManage.controller.BaseController;
import com.hycen.batteryManage.exception.BizException;
import com.hycen.batteryManage.service.BatteryService;
import com.hycen.batteryManage.vo.ResultVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/10/29.
 */
@RestController
@RequestMapping("/battery")
public class BatteryManageController extends BaseController {

    @Resource
    BatteryService batteryService;


    @RequestMapping(value = "/query",method = RequestMethod.POST)
    public JSONObject queryBatterys(){
        JSONArray batterys = new JSONArray();
        JSONObject battery1 = new JSONObject();
        battery1.put("id","1");
        battery1.put("item_code","SH001");
        battery1.put("item_name", "4050电池");
        battery1.put("item_brand", "春兰");
        battery1.put("item_status", "1");
        battery1.put("item_house", "总部");
        battery1.put("intime", "2018-02-26 10:12:13");
        battery1.put("package", "4050套餐");
        battery1.put("remake", "备注说明");

        JSONObject battery2 = new JSONObject();
        battery2.put("id","2");
        battery2.put("item_code","SH002");
        battery2.put("item_name", "4051电池");
        battery2.put("item_brand", "联合");
        battery2.put("item_status", "2");
        battery2.put("item_house", "杭州");
        battery2.put("intime", "2018-02-25 00:12:13");
        battery2.put("package", "5050套餐");
        battery2.put("remake", "备注说明");

        batterys.add(battery1);
        batterys.add(battery2);

        JSONObject result = new JSONObject();
        result.put("code",  0);
        result.put("msg", "suc");
        result.put("data", batterys);
        result.put("count", 2);

        //"code": 0,
        //    "msg": "",
        //    "count": 1000,
        //    "data":

        return result;
    }


    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @SystemControllerLog(description = "/battery/edit")
    public ResultVO editBattery(String id, String item_code, String item_name, String item_brand, String item_status,
                                String item_house, String item_package, String remark) throws BizException {


        ResultVO resultVO = new ResultVO();

        ParamsChecker.checkIsInteger(id, "no id");
        ParamsChecker.checkNotBlank(item_code, "no battery code");
        ParamsChecker.checkNotBlank(item_name, "no item_name");
        ParamsChecker.checkNotBlank(item_brand, "no item_brand");
        ParamsChecker.checkNotBlank(item_status, "no item_status");
        ParamsChecker.checkNotBlank(item_house, "no item_house");
        ParamsChecker.checkNotBlank(item_package, "no item_package");


        boolean ret = batteryService.setBattery(Integer.parseInt(id), item_code, item_name, item_house, item_status,
                item_brand, remark, item_package);

        if (ret){
            resultVO.setSucessRepmsg();

        }else{
            resultVO.setFailRepmsg();
        }


        return resultVO;
    }


}
