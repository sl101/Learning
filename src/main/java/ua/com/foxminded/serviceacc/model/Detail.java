package ua.com.foxminded.serviceacc.model;

import ua.com.foxminded.serviceacc.model.enums.DetailType;

public class Detail {
	private String value;
	private DetailType detaileType;
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public DetailType getDetaileType() {
		return detaileType;
	}
	public void setDetaileType(DetailType detaileType) {
		this.detaileType = detaileType;
	}
	
}
