package org.jiaowei.entity;

public class NavMenuEntity {

	private String navmId;//主键，-1第一次发送消息，0人工服务器，1路口截图。。。
	private String navmKeyValue;//导航键盘值
	private String navmKeyName;//导航键盘名称，如：【1】高速路况查询
	private Integer navmLevel;//等级
	private Integer navmDeptId;//部门ID
	private Integer navmType;//类型：0人工服务，1普通消息，2url消息
	private String navmMsgTitle;//发送消息标题
	private String navmMsgDesc;//发送消息描述，如果是普通消息，则为发送内容
	private String navmMsgUrl;//发送消息url
	public String getNavmId() {
		return navmId;
	}
	public void setNavmId(String navmId) {
		this.navmId = navmId;
	}
	public String getNavmKeyValue() {
		return navmKeyValue;
	}
	public void setNavmKeyValue(String navmKeyValue) {
		this.navmKeyValue = navmKeyValue;
	}
	public String getNavmKeyName() {
		return navmKeyName;
	}
	public void setNavmKeyName(String navmKeyName) {
		this.navmKeyName = navmKeyName;
	}
	public Integer getNavmLevel() {
		return navmLevel;
	}
	public void setNavmLevel(Integer navmLevel) {
		this.navmLevel = navmLevel;
	}
	public Integer getNavmDeptId() {
		return navmDeptId;
	}
	public void setNavmDeptId(Integer navmDeptId) {
		this.navmDeptId = navmDeptId;
	}
	public Integer getNavmType() {
		return navmType;
	}
	public void setNavmType(Integer navmType) {
		this.navmType = navmType;
	}
	public String getNavmMsgTitle() {
		return navmMsgTitle;
	}
	public void setNavmMsgTitle(String navmMsgTitle) {
		this.navmMsgTitle = navmMsgTitle;
	}
	public String getNavmMsgDesc() {
		return navmMsgDesc;
	}
	public void setNavmMsgDesc(String navmMsgDesc) {
		this.navmMsgDesc = navmMsgDesc;
	}
	public String getNavmMsgUrl() {
		return navmMsgUrl;
	}
	public void setNavmMsgUrl(String navmMsgUrl) {
		this.navmMsgUrl = navmMsgUrl;
	}
	
}
