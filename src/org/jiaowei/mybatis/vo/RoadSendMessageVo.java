package org.jiaowei.mybatis.vo;

import java.util.Date;

public class RoadSendMessageVo {

	private String messId;
	private String messName;
	private String messOpenId;
	private Date messSendTimes;
	
	public String getMessId() {
		return messId;
	}
	public void setMessId(String messId) {
		this.messId = messId;
	}
	public String getMessName() {
		return messName;
	}
	public void setMessName(String messName) {
		this.messName = messName;
	}
	public String getMessOpenId() {
		return messOpenId;
	}
	public void setMessOpenId(String messOpenId) {
		this.messOpenId = messOpenId;
	}
	public Date getMessSendTimes() {
		return messSendTimes;
	}
	public void setMessSendTimes(Date messSendTimes) {
		this.messSendTimes = messSendTimes;
	}
	
	
}
