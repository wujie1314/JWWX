package org.jiaowei.entity;

import java.util.Date;

public class NavigationMenuEntity {
	private String openId;
	private int state=1; //1一层、2、二层、3三层
	private Date date;//进入菜单服务时间
	private String menuKey;
	
	public String getMenuKey() {
		return menuKey;
	}
	public void setMenuKey(String menuKey) {
		this.menuKey = menuKey;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
