package com.hycen.batteryManage.model;

import lombok.Data;

/**
 * Created by hshao on 2018/2/27.
 */

@Data
public class BatteryModel {
    Integer id;
    String item_code;
    String item_name;
    String item_house;
    String item_status;
    String item_brand;
    String intime;
    String remake;
    String item_package;
    String updatetime;
}
