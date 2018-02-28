package com.hycen.batteryManage.vo.user;

import com.hycen.batteryManage.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;

public class UserVo extends BaseVO {

    @Setter
    @Getter
    long userid;

    @Setter
    @Getter
    String username;

    @Setter
    @Getter
    String password;

    @Setter
    @Getter
    String alias;

    @Setter
    @Getter
    String email;

    @Setter
    @Getter
    String lastLogin;

    @Setter
    @Getter
    Integer status;

    @Setter
    @Getter
    String indate;

    @Setter
    @Getter
    String salt;

}
