package com.hycen.batteryManage.dao;

import com.hycen.batteryManage.model.BatteryModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BatteryMapper extends BaseMapper<BatteryModel, Long> {

    BatteryModel selectById(@Param("id") Integer id);

    @Select("select * from item_batterys where item_code = #{0}")
    BatteryModel selectByItemCode(String item_code);


    @Select("select * from item_batterys where item_name=#{0} limit 1")
    BatteryModel selectByItemName(String item_status);

   @Insert("insert into item_batterys(item_code,item_name, item_house, item_status, item_brand, item_package, intime, " +
           "remark) values(#{arg0},#{arg1},#{arg2},#{arg3},#{arg4},#{arg5},#{arg6},#{arg7})")
   boolean addBattery(String item_code, String item_name, String item_house, String item_status,
                      String item_brand, String item_package, String in_time, String remark);

   boolean updateBattery(String item_code, String item_name, String item_house, String item_status, String item_brand,
                         String item_package, String remark, Integer id);

}