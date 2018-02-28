package com.hycen.batteryManage.vo.admin;

import lombok.Getter;
import lombok.Setter;

public class AdminUserInfoVo {

    @Setter
    @Getter
    public Integer id;

    @Setter
    @Getter
    public String UserName = "";

    @Setter
    @Getter
    public String Password = "";

    @Setter
    @Getter
    public String LastLogin = "";
}
