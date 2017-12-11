package cn.nilaile.ssm.enums;

public enum CommonEnum {

	ACTIVE_NOT("未激活","2001"),
	ACTIVE_YES("激活","2002"),
	STAFF_YES("是管理","1001"),
	STAFF_NOT("非管理","1002");
	
	private String name;
	private String value;
	
	private CommonEnum(String name,String value){
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
