package com.hycen.batteryManage.service;

import com.hycen.batteryManage.dao.BatteryMapper;
import com.hycen.batteryManage.util.CustomDateUtils;
import com.hycen.batteryManage.util.Utils;
import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hshao on 2018/2/27.
 */
@Service
public class BatteryService {
    private static Logger logger = LoggerFactory.getLogger(BatteryService.class);

    @Autowired
    private BatteryMapper batteryDao;

    public boolean setBattery(Integer id, String item_code, String item_name, String item_house, String item_status,
                              String item_brand, String remark, String item_package){
        String inTime = Utils.getDateTime();
        boolean ret = false;
        if (id == 0){
            logger.info(item_name);
            ret = batteryDao.addBattery(item_code, item_name, item_house, item_status, item_brand, item_package,
                    inTime,remark);
        }else{
            ret = batteryDao.updateBattery(item_code, item_name, item_house, item_status, item_brand, item_package, remark, id);
        }

        return ret;
    }

}
