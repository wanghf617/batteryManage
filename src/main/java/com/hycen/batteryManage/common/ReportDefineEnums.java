package com.hycen.batteryManage.common;

/**
 * Excel导出的列定义
 * Created by wanghf on 2017/5/10.
 */
public class ReportDefineEnums {

    /**
     * 员工数据需要导出的列定义
     * name 为对应数据对象的列名
     * title 为excel的标题
     * colWidth 导出excel列的宽度
     */
    public enum EMPLOYEE_LIST {
        empCode("工号", 8), fullName("姓名", 16), sex("性别", 8), email("电子邮件", 30), mobileTele("手机号码", 12),
        depName("部门", 20), postId("职务", 20)
        ;

        public String title;
        public int colWidth;
        EMPLOYEE_LIST(String title, int colWidth) {
            this.title = title;
            this.colWidth = colWidth;
        }
    }

    public enum ERRNO_LIST {
        libName("返回编码", 8), libEngname("返回消息", 20), libDesc("消息描述", 40)
        ;

        public String title;
        public int colWidth;
        ERRNO_LIST(String title, int colWidth) {
            this.title = title;
            this.colWidth = colWidth;
        }
    }
}
