package com.hycen.batteryManage.exception;


/**
 * App 错误异常编码定义
 * 错误编码为6位数字，前两位23表示App，
 * 中间两位表示模块编号：00-运行环境  01-用户模块  02-通知模块  03-文档模块  04-签署模块 06-认证  07-第三方平台
 * 后两位表示错误序列
 * @author hshao
 * @date 2017-12-26
 * @since 3.0
 */
public enum BizExceptionCode {
    //系统保留编码
    CODE_100000(100000, "操作成功"),
    CODE_100001(100001, "系统繁忙，请稍候再试"),
    CODE_110001(110001, "通行证编号不能为空"),
    CODE_110002(110002, "用户登录信息已失效，请重新登录"),
    CODE_110003(110003, "请求参数格式不正确"),
    CODE_110004(110004, "请求参数不能为空"),
    CODE_110005(110005, "通行证编号无效"),
    CODE_110007(110007, "签名不匹配"),
    CODE_110008(110008, "无操作权限"),
    CODE_110009(110009, "参数错误"),
    CODE_110000(110000, "未知错误"),
    CODE_110010(110010, "本地存储未配置，无法保存图片，请联系系统管理员"),
    CODE_110011(110011, "本地存储路径没有配置，无法保存图片，请在配置文件中增加"),
    CODE_110012(110012, "mongodb的url没有配置，无法保存图片，请在配置文件中增加"),

    //用户模块
    CODE_120001(120001, "新密码与确认密码不一致"),
    CODE_120002(120002, "用户名和密码不能为空"),
    CODE_120003(120003, "用户名或者密码不正确"),
    CODE_120004(120004, "账号不存在"),
    CODE_120005(120005, "账号未激活，请重新注册激活"),
    CODE_120006(120006, "账号无效"),
    CODE_120007(120007, "账号已锁定，请1小时后重试"),
    CODE_120008(120008, "账号已冻结，无法登陆"),
    CODE_120009(120009, "账号异常，请联系系统维护人员"),
    CODE_120010(120010, "短信发送失败，请稍后重试"),
    CODE_120011(120011, "短信发送间隔过短，请稍后重试"),
    CODE_120012(120012, "短信验证码输入错误，请确认"),
    CODE_120013(120013, "手机号码不能为空"),
    CODE_120014(120014, "登录密码不能为空"),
    CODE_120015(120015, "验证码不能为空"),
    CODE_120016(120016, "验证码不正确"),
    CODE_120017(120017, "验证码错误次数太多"),
    CODE_120018(120018, "登录账号已存在"),
    CODE_120019(120019, "原密码不正确"),

    //权限模块
    CODE_130001(130001, "当前菜单存在子菜单，不允许删除"),
    CODE_130002(130002, "当前菜单已是叶子节点，无法支持增加子节点"),
    CODE_130003(130003, "当前角色编码已存在，请重新输入"),
    CODE_130004(130004, "超级管理员角色不允许修改"),
    CODE_130005(130005, "当前角色已被删除，请确认"),

    //HR
    CODE_140101(140101, "当前部门存在子部门，不允许删除"),
    CODE_140102(140102, "当前部门存在人员，不允许删除"),

    CODE_140201(140201, "当前岗位存在子岗位，不允许删除"),
    CODE_140202(140202, "当前岗位存在人员，不允许删除"),

    CODE_140301(140301, "工号已存在"),
    CODE_140302(140302, "用户组已存在"),
    CODE_140303(140303, "数据导入异常"),

    CODE_150101(150101, "该角色已经绑定用户数据，请取消绑定后再执行删除操作"),

    //微信
    CODE_160101(160101, "当前微信公众号编码已存在，请重新填写"),
    CODE_160102(160102, "微信API调用异常"),
    CODE_160201(160201, "微信公众号一级菜单不能超过3个"),
    CODE_160202(160202, "微信公众号二级菜单不能超过5个"),
    CODE_160301(160301, "模板消息参数不能为空"),

    //工作流
    CODE_170101(170101, "工作流定义异常"),

    //系统设置
    CODE_190101(190101, "当前字典存在子业务字典，不允许删除"),

    CODE_190201(190201,"请上传图片文件"),
    CODE_190202(190202, "没有选择文件"),
    CODE_190203(190203, "上传文件过大，文件大小不能超过5MB"),
    CODE_190204(190204, "上传文件内容不能为空"),
    CODE_190205(190205,"请上传Excel文件"),


    //redis监控
    CODE_190401(190401, "redis初始化异常"),
    CODE_190402(190402, "redis连接异常"),
    CODE_190403(190403, "redis方法不支持异常"),

    //api-demo
    CODE_200101(200101, "接口定义不存在"),

    ;

    BizExceptionCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;

    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static BizExceptionCode getByCode(int code) {
        return valueOf("CODE_"+code);
    }
}
