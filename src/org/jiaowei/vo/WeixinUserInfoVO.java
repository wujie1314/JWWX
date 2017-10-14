package org.jiaowei.vo;

public class WeixinUserInfoVO {
	
	private Integer id;
	private String openid;
	private String nickname;
	private String isOnLine;
	private String phone;
	private String redBlack;
	private String createTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getIsOnLine() {
		return isOnLine;
	}
	public void setIsOnLine(String isOnLine) {
		this.isOnLine = isOnLine;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRedBlack() {
		return redBlack;
	}
	public void setRedBlack(String redBlack) {
		this.redBlack = redBlack;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}
