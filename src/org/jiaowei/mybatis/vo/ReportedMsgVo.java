package org.jiaowei.mybatis.vo;

import java.util.Date;

import org.jiaowei.util.ApiUtils;

public class ReportedMsgVo {

	private Long repoId;
	private String repoUserName;
	private String repoUserPhone;
	private String repoDetails;
	private Date repoCreateTime;
	private int repoType;//0微信、1APP
	private String repoImgUrl;
	private int repoState;//0未中奖、1中奖
	private String repoCreateTimeStr;
	private String repoTypeStr;
	private String repoStateStr;
	
	public String getRepoCreateTimeStr() {
		if(repoCreateTime != null){
			repoCreateTimeStr = ApiUtils.formatDateTime(repoCreateTime);
		}
		return repoCreateTimeStr;
	}
	public void setRepoCreateTimeStr(String repoCreateTimeStr) {
		this.repoCreateTimeStr = repoCreateTimeStr;
	}
	public String getRepoTypeStr() {
		if(repoType == 0){
			repoTypeStr = "微信";
		} else if(repoType == 1){
			repoTypeStr = "安卓";
		} else if(repoType == 2){
			repoTypeStr = "IOS";
		} else {
			repoTypeStr = "微信";
		}
		return repoTypeStr;
	}
	public void setRepoTypeStr(String repoTypeStr) {
		this.repoTypeStr = repoTypeStr;
	}
	public String getRepoStateStr() {
		if(repoState == 0){
			repoStateStr = "未中奖";
		} else {
			repoStateStr = "中奖";
		}
		return repoStateStr;
	}
	public void setRepoStateStr(String repoStateStr) {
		this.repoStateStr = repoStateStr;
	}
	public int getRepoState() {
		return repoState;
	}
	public void setRepoState(int repoState) {
		this.repoState = repoState;
	}
	public Long getRepoId() {
		return repoId;
	}
	public void setRepoId(Long repoId) {
		this.repoId = repoId;
	}
	public String getRepoUserName() {
		return repoUserName;
	}
	public void setRepoUserName(String repoUserName) {
		this.repoUserName = repoUserName;
	}
	public String getRepoUserPhone() {
		return repoUserPhone;
	}
	public void setRepoUserPhone(String repoUserPhone) {
		this.repoUserPhone = repoUserPhone;
	}
	public String getRepoDetails() {
		return repoDetails;
	}
	public void setRepoDetails(String repoDetails) {
		this.repoDetails = repoDetails;
	}
	public Date getRepoCreateTime() {
		return repoCreateTime;
	}
	public void setRepoCreateTime(Date repoCreateTime) {
		this.repoCreateTime = repoCreateTime;
	}
	public int getRepoType() {
		return repoType;
	}
	public void setRepoType(int repoType) {
		this.repoType = repoType;
	}
	public String getRepoImgUrl() {
		return repoImgUrl;
	}
	public void setRepoImgUrl(String repoImgUrl) {
		this.repoImgUrl = repoImgUrl;
	}
	
	
}
