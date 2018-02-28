package com.hycen.batteryManage.common;

public class Enums {

	/**
	 * 电池状态
	 *
	 * @author Administrator 0:未启用，1：出租中，2：待出租，3：已报废
	 */
	public enum BATTERY_STATUS {
		No_Enable(0, "未启用"), Lease(1, "出租中"), Need_Lease(2,"待出租"), Discarded(3,"已报废");

		public int value;
		public String valueName;

		private BATTERY_STATUS(int value, String valueName) {
			this.value = value;
			this.valueName = valueName;
		}


		public static String valueOf(int a) {

			String tmp = "";
			for (int i = 0; i < values().length; i++) {
				if (values()[i].value == a) {
					tmp = values()[i].valueName;
					break;
				}
			}
			return tmp;
		}

		public static String getSelectAndText(String addStr) {
			String tmp = "";
			if (addStr != null && addStr.length() > 0) {
				tmp = addStr + "|";
			}
			for (int i = 0; i < values().length; i++) {
				String vn = values()[i].valueName;
				int key = values()[i].value;
				tmp += key + "," + vn + "|";
			}
			if (tmp != null && tmp.length() > 0)
				return tmp.substring(0, tmp.length() - 1);
			else
				return tmp;
		}
	}
	
	/**
	 * 功能菜单是否有效
	 * 
	 * @author Administrator 1 有效 2无效
	 */
	public enum SYS_ISACTION {
		Vaild(1, "有效"), No_Vaild(2, "无效");

		public int value;
		public String valueName;

		private SYS_ISACTION(int value, String valueName) {
			this.value = value;
			this.valueName = valueName;
		}
		
		
		public static String valueOf(int a) {
			
			String tmp = "";
			for (int i = 0; i < values().length; i++) {
				if (values()[i].value == a) {
					tmp = values()[i].valueName;
					break;
				}
			}
			return tmp;
		}

		public static String getSelectAndText(String addStr) {
			String tmp = "";
			if (addStr != null && addStr.length() > 0) {
				tmp = addStr + "|";
			}
			for (int i = 0; i < values().length; i++) {
				String vn = values()[i].valueName;
				int key = values()[i].value;
				tmp += key + "," + vn + "|";
			}
			if (tmp != null && tmp.length() > 0)
				return tmp.substring(0, tmp.length() - 1);
			else
				return tmp;
		}
	}

	
	/**
	 * 系统异常状态
	 * 
	 * @author Administrator 1 未处理 2已处理
	 */
	public enum SYS_EXCEPTION_STATUS {
		Vaild(1, "未处理"), No_Vaild(2, "已处理");

		public int value;
		public String valueName;

		private SYS_EXCEPTION_STATUS(int value, String valueName) {
			this.value = value;
			this.valueName = valueName;
		}

		public static String valueOf(int a) {
			String tmp = "";
			for (int i = 0; i < values().length; i++) {
				if (values()[i].value == a) {
					tmp = values()[i].valueName;
					break;
				}
			}
			return tmp;
		}

		public static String getSelectAndText(String addStr) {
			String tmp = "";
			if (addStr != null && addStr.length() > 0) {
				tmp = addStr + "|";
			}
			for (int i = 0; i < values().length; i++) {
				String vn = values()[i].valueName;
				int key = values()[i].value;
				tmp += key + "," + vn + "|";
			}
			if (tmp != null && tmp.length() > 0)
				return tmp.substring(0, tmp.length() - 1);
			else
				return tmp;
		}
	}
	
	/**
	 * 系统运行参数
	 * 
	 * @author Administrator 1 普通文本框 2数字输入框 3下拉框
	 */
	public enum SYS_PARAM_TYPE {
		TEXT(1, "普通文本框"), NUM(2, "数字输入框"),SELECT(3,"下拉选择框");

		public int value;
		public String valueName;

		private SYS_PARAM_TYPE(int value, String valueName) {
			this.value = value;
			this.valueName = valueName;
		}

		public static String valueOf(int a) {
			String tmp = "";
			for (int i = 0; i < values().length; i++) {
				if (values()[i].value == a) {
					tmp = values()[i].valueName;
					break;
				}
			}
			return tmp;
		}

		public static String getSelectAndText(String addStr) {
			String tmp = "";
			if (addStr != null && addStr.length() > 0) {
				tmp = addStr + "|";
			}
			for (int i = 0; i < values().length; i++) {
				String vn = values()[i].valueName;
				int key = values()[i].value;
				tmp += key + "," + vn + "|";
			}
			if (tmp != null && tmp.length() > 0)
				return tmp.substring(0, tmp.length() - 1);
			else
				return tmp;
		}
	}
	
	public enum HRM_EMPLOYEE_SEX{
		Man(1,"男"),Woman(0,"女");
		
		public int value;
		public String valueName;
		
		private HRM_EMPLOYEE_SEX(int value, String valueName) {
			this.value = value;
			this.valueName = valueName;
		}

		public static String valueOf(int a) {
			String tmp = "";
			for (int i = 0; i < values().length; i++) {
				if (values()[i].value == a) {
					tmp = values()[i].valueName;
					break;
				}
			}
			return tmp;
		}

		public static String getSelectAndText(String addStr) {
			String tmp = "";
			if (addStr != null && addStr.length() > 0) {
				tmp = addStr + "|";
			}
			for (int i = 0; i < values().length; i++) {
				String vn = values()[i].valueName;
				int key = values()[i].value;
				tmp += key + "," + vn + "|";
			}
			if (tmp != null && tmp.length() > 0)
				return tmp.substring(0, tmp.length() - 1);
			else
				return tmp;
		}
	}
	
	/**
	 * 功能菜单是否有效
	 * 
	 * @author Administrator 1 是 2否
	 */
	public enum SYS_ISEDIT{
		EDIT(1, "是"), No_EDIT(2, "否");

		public int value;
		public String valueName;

		private SYS_ISEDIT(int value, String valueName) {
			this.value = value;
			this.valueName = valueName;
		}

		public static String valueOf(int a) {
			String tmp = "";
			for (int i = 0; i < values().length; i++) {
				if (values()[i].value == a) {
					tmp = values()[i].valueName;
					break;
				}
			}
			return tmp;
		}

		public static String getSelectAndText(String addStr) {
			String tmp = "";
			if (addStr != null && addStr.length() > 0) {
				tmp = addStr + "|";
			}
			for (int i = 0; i < values().length; i++) {
				String vn = values()[i].valueName;
				int key = values()[i].value;
				tmp += key + "," + vn + "|";
			}
			if (tmp != null && tmp.length() > 0)
				return tmp.substring(0, tmp.length() - 1);
			else
				return tmp;
		}
	}


	/**
	 * 人员状态
	 * 
	 * @author Administrator 1试用 2正常 3离职
	 */
	public enum HRM_EMPLOYEE_STATUS {
		Trial(1, "试用"), Official(2, "正式"),Separation(3,"离职");

		public int value;
		public String valueName;

		private HRM_EMPLOYEE_STATUS(int value, String valueName) {
			this.value = value;
			this.valueName = valueName;
		}

		public static String valueOf(int a) {
			String tmp = "";
			for (int i = 0; i < values().length; i++) {
				if (values()[i].value == a) {
					tmp = values()[i].valueName;
					break;
				}
			}
			return tmp;
		}
		public static String getSelectAndText(String addStr) {
			String tmp = "";
			if (addStr != null && addStr.length() > 0) {
				tmp = addStr + "|";
			}
			for (int i = 0; i < values().length; i++) {
				String vn = values()[i].valueName;
				int key = values()[i].value;
				tmp += key + "," + vn + "|";
			}
			if (tmp != null && tmp.length() > 0)
				return tmp.substring(0, tmp.length() - 1);
			else
				return tmp;
		}
	}
	
	/**
	 * 角色绑定类型
	 * 
	 * @author Administrator 1 用户 2 部门 3 岗位 4 用户组
	 */
	public enum SYS_ROLE_BIND_TYPE {
		BIND_USER(1, "用户"), BIND_DEPT(2, "部门"), BIND_POST(3, "岗位"), BIND_GROUP(4, "用户组");

		public int value;
		public String valueName;

		private SYS_ROLE_BIND_TYPE(int value, String valueName) {
			this.value = value;
			this.valueName = valueName;
		}

		public static String valueOf(int a) {
			String tmp = "";
			for (int i = 0; i < values().length; i++) {
				if (values()[i].value == a) {
					tmp = values()[i].valueName;
					break;
				}
			}
			return tmp;
		}

		public static String getSelectAndText(String addStr) {
			String tmp = "";
			if (addStr != null && addStr.length() > 0) {
				tmp = addStr + "|";
			}
			for (int i = 0; i < values().length; i++) {
				String vn = values()[i].valueName;
				int key = values()[i].value;
				tmp += key + "," + vn + "|";
			}
			if (tmp != null && tmp.length() > 0)
				return tmp.substring(0, tmp.length() - 1);
			else
				return tmp;
		}
	}
	
	/**
	 * 用户类型
	 * 
	 * @author Administrator 1 普通用户 2 测试账户 3 系统管理账户
	 */
	public enum SYS_USER_TYPE {
		DEFAULT(1, "普通"), TSET(2, "测试账户"), SYSTEM(3, "系统管理");

		public int value;
		public String valueName;

		private SYS_USER_TYPE(int value, String valueName) {
			this.value = value;
			this.valueName = valueName;
		}

		public static String valueOf(int a) {
			String tmp = "";
			for (int i = 0; i < values().length; i++) {
				if (values()[i].value == a) {
					tmp = values()[i].valueName;
					break;
				}
			}
			return tmp;
		}

		public static String getSelectAndText(String addStr) {
			String tmp = "";
			if (addStr != null && addStr.length() > 0) {
				tmp = addStr + "|";
			}
			if (tmp != null && tmp.length() > 0)
				return tmp.substring(0, tmp.length() - 1);
			else
				return tmp;
		}
	}

	/** 
	 * 菜单等级   -1 顶级，1 一级，2 二级，3 三级
	 * 
	 * @author Administrator
	 */
	public enum SYS_METHOD_LEVEL {
		TOP(-1, "顶级"), ONE(1, "一级"), TWO(2, "二级"), THREE(3, "三级");

		public int value;
		public String valueName;

		private SYS_METHOD_LEVEL(int value, String valueName) {
			this.value = value;
			this.valueName = valueName;
		}

		public static String valueOf(int a) {
			String tmp = "";
			for (int i = 0; i < values().length; i++) {
				if (values()[i].value == a) {
					tmp = values()[i].valueName;
					break;
				}
			}
			return tmp;
		}

		public static String getSelectAndText(String addStr) {
			String tmp = "";
			if (addStr != null && addStr.length() > 0) {
				tmp = addStr + "|";
			}
			for (int i = 0; i < values().length; i++) {
				String vn = values()[i].valueName;
				int key = values()[i].value;
				tmp += key + "," + vn + "|";
			}
			if (tmp != null && tmp.length() > 0)
				return tmp.substring(0, tmp.length() - 1);
			else
				return tmp;
		}
	}

	/**
	 * 树显示顺序移动
	 * @author peng.ning
	 * @date   Jul 6, 2010
	 */
	public enum Tree_MOVE_Type{
		MOVE_UP(1,"向上移动"),MOVE_DOWN(2,"向下移动");
		public int value;
		public String valueName;

		private Tree_MOVE_Type(int value, String valueName) {
			this.value = value;
			this.valueName = valueName;
		}

		public static String valueOf(int a) {
			String tmp = "";
			for (int i = 0; i < values().length; i++) {
				if (values()[i].value == a) {
					tmp = values()[i].valueName;
					break;
				}
			}
			return tmp;
		}
	}
	
	public enum USER_AUTHLEVEL{
		NO(0,"未审核"),PASS(1,"已审核");
		
		public int value;
		public String valueName;
		
		private USER_AUTHLEVEL(int value, String valueName) {
			this.value = value;
			this.valueName = valueName;
		}

		public static String valueOf(int a) {
			String tmp = "";
			for (int i = 0; i < values().length; i++) {
				if (values()[i].value == a) {
					tmp = values()[i].valueName;
					break;
				}
			}
			return tmp;
		}

		public static String getSelectAndText(String addStr) {
			String tmp = "";
			if (addStr != null && addStr.length() > 0) {
				tmp = addStr + "|";
			}
			for (int i = 0; i < values().length; i++) {
				String vn = values()[i].valueName;
				int key = values()[i].value;
				tmp += key + "," + vn + "|";
			}
			if (tmp != null && tmp.length() > 0)
				return tmp.substring(0, tmp.length() - 1);
			else
				return tmp;
		}
	}
	
	
	public enum USER_AUTHSTATUS{
		WAIT(0,"待审核"),PASS(1,"审核通过"),ERROR(2,"审核时系统发生错误"),REJECT(4,"审核驳回");
		
		public int value;
		public String valueName;
		
		private USER_AUTHSTATUS(int value, String valueName) {
			this.value = value;
			this.valueName = valueName;
		}

		public static String valueOf(int a) {
			String tmp = "";
			for (int i = 0; i < values().length; i++) {
				if (values()[i].value == a) {
					tmp = values()[i].valueName;
					break;
				}
			}
			return tmp;
		}

		public static String getSelectAndText(String addStr) {
			String tmp = "";
			if (addStr != null && addStr.length() > 0) {
				tmp = addStr + "|";
			}
			for (int i = 0; i < values().length; i++) {
				String vn = values()[i].valueName;
				int key = values()[i].value;
				tmp += key + "," + vn + "|";
			}
			if (tmp != null && tmp.length() > 0)
				return tmp.substring(0, tmp.length() - 1);
			else
				return tmp;
		}
	}
	
	public enum USER_STATUS{
		NOACTIVE(0,"已注册未激活"),ACTIVE(1,"已激活"),LOGOFF(2,"已注销"),LOCKING(3,"已激活暂时锁定"),FROZEN(4,"账户冻结");
		
		public int value;
		public String valueName;
		
		private USER_STATUS(int value, String valueName) {
			this.value = value;
			this.valueName = valueName;
		}

		public static String valueOf(int a) {
			String tmp = "";
			for (int i = 0; i < values().length; i++) {
				if (values()[i].value == a) {
					tmp = values()[i].valueName;
					break;
				}
			}
			return tmp;
		}

		public static String getSelectAndText(String addStr) {
			String tmp = "";
			if (addStr != null && addStr.length() > 0) {
				tmp = addStr + "|";
			}
			for (int i = 0; i < values().length; i++) {
				String vn = values()[i].valueName;
				int key = values()[i].value;
				tmp += key + "," + vn + "|";
			}
			if (tmp != null && tmp.length() > 0)
				return tmp.substring(0, tmp.length() - 1);
			else
				return tmp;
		}
	}
	
	public enum LOGIN_ACCOUNT_TYPE{
		SYSTEM((byte)1,"工号"), EMAIL((byte)2,"邮箱"), PHONE((byte)3,"手机"), BIM((byte)4,"BIM"), CUSTOM((byte)5,"自定义");
		
		public byte value;
		public String valueName;
		
		private LOGIN_ACCOUNT_TYPE(byte value, String valueName) {
			this.value = value;
			this.valueName = valueName;
		}

		public static String valueOf(int a) {
			String tmp = "";
			for (int i = 0; i < values().length; i++) {
				if (values()[i].value == a) {
					tmp = values()[i].valueName;
					break;
				}
			}
			return tmp;
		}
	}
	
	public enum MSG_SEND_STATUS{
		UNSEND(0,"待发送"),SUCCESS(1,"发送成功"),FAIL(2,"发送失败");
		
		public int value;
		public String valueName;
		
		private MSG_SEND_STATUS(int value, String valueName) {
			this.value = value;
			this.valueName = valueName;
		}

		public static String valueOf(int a) {
			String tmp = "";
			for (int i = 0; i < values().length; i++) {
				if (values()[i].value == a) {
					tmp = values()[i].valueName;
					break;
				}
			}
			return tmp;
		}

		public static String getSelectAndText(String addStr) {
			String tmp = "";
			if (addStr != null && addStr.length() > 0) {
				tmp = addStr + "|";
			}
			for (int i = 0; i < values().length; i++) {
				String vn = values()[i].valueName;
				int key = values()[i].value;
				tmp += key + "," + vn + "|";
			}
			if (tmp != null && tmp.length() > 0)
				return tmp.substring(0, tmp.length() - 1);
			else
				return tmp;
		}
	}
	
	/**
	 * 文件类型枚举值
	 * @author wanghf
	 *
	 */
	public enum OPER_TYPE {
		VIEW(":view","查看"),
		ADD(":add","新增"),
		EDIT(":edit","修改"),
		DELETE(":delete","删除");
		
		public String value;
		public String valueName;
		
		OPER_TYPE(String value, String valueName) {
			this.value = value;
			this.valueName = valueName;
		}	

		public String getValue() {
			return value;
		}
		
		public String getValueName() {
			return valueName;
		}
		
		public static String valueOf(int a) {
			String tmp = "";
			for (int i = 0; i < values().length; i++) {
				if (Integer.parseInt(values()[i].value) == a) {
					tmp = values()[i].valueName;
					break;
				}
			}
			return tmp;
		}
	}
}
