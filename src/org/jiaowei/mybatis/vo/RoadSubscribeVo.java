package org.jiaowei.mybatis.vo;

import java.util.Date;

import org.jiaowei.util.StringUtil;
public class RoadSubscribeVo {

	private Integer subsId;
	private String subsOpenId;//微信用户Id
	private String subsCharacter;//文字订阅;以逗号分隔
	private String subsImg;//图片订阅;以逗号分隔
	private Date subsCreateTime;//创建时间
	private String subsRemindDate;//提醒日期
	private String subsRemindHour;//提醒小时
	private String subsRemindMinute;//提醒分钟
	private String subsRemindWeek;//提醒周期：0每周日、1每周一、2每周二、3每周三、4每周四、5每周五、6每周六
	private String subsRemindWeekStr;//提醒周期：0每周日、1每周一、2每周二、3每周三、4每周四、5每周五、6每周六
	
	private int subsRemindType;//提醒类型，0不重复、1重复
	private int subsIsStart;//是否启动、0启动、1不启动
	private Date subsSendTimes;//发送时间
	private Date subsSendNextTimes;//下次发送时间
	private String subsTitleName;//文字订阅标题;以逗号分隔
	private String subsRemindTimeHour;//文字订阅标题;以逗号分隔
	private Integer subsType;//订阅类型，0定时推送，1及时推送、
	
	
	public Integer getSubsId() {
		return subsId;
	}
	public void setSubsId(Integer subsId) {
		this.subsId = subsId;
	}
	public String getSubsOpenId() {
		return subsOpenId;
	}
	public void setSubsOpenId(String subsOpenId) {
		this.subsOpenId = subsOpenId;
	}
	public String getSubsCharacter() {
		return subsCharacter;
	}
	public void setSubsCharacter(String subsCharacter) {
		this.subsCharacter = subsCharacter;
	}
	public String getSubsImg() {
		return subsImg;
	}
	public void setSubsImg(String subsImg) {
		this.subsImg = subsImg;
	}
	public Date getSubsCreateTime() {
		return subsCreateTime;
	}
	public void setSubsCreateTime(Date subsCreateTime) {
		this.subsCreateTime = subsCreateTime;
	}
	public String getSubsRemindDate() {
		return subsRemindDate;
	}
	public void setSubsRemindDate(String subsRemindDate) {
		this.subsRemindDate = subsRemindDate;
	}
	public String getSubsRemindHour() {
		return subsRemindHour;
	}
	public void setSubsRemindHour(String subsRemindHour) {
		this.subsRemindHour = subsRemindHour;
	}
	public String getSubsRemindMinute() {
		return subsRemindMinute;
	}
	public void setSubsRemindMinute(String subsRemindMinute) {
		this.subsRemindMinute = subsRemindMinute;
	}
	public String getSubsRemindWeek() {
		return subsRemindWeek;
	}
	public void setSubsRemindWeek(String subsRemindWeek) {
		this.subsRemindWeek = subsRemindWeek;
	}
	public int getSubsRemindType() {
		return subsRemindType;
	}
	public void setSubsRemindType(int subsRemindType) {
		this.subsRemindType = subsRemindType;
	}
	public int getSubsIsStart() {
		return subsIsStart;
	}
	public void setSubsIsStart(int subsIsStart) {
		this.subsIsStart = subsIsStart;
	}
	public Date getSubsSendTimes() {
		return subsSendTimes;
	}
	public void setSubsSendTimes(Date subsSendTimes) {
		this.subsSendTimes = subsSendTimes;
	}
	public Date getSubsSendNextTimes() {
		return subsSendNextTimes;
	}
	public void setSubsSendNextTimes(Date subsSendNextTimes) {
		this.subsSendNextTimes = subsSendNextTimes;
	}
	public String getSubsTitleName() {
		return subsTitleName;
	}
	public void setSubsTitleName(String subsTitleName) {
		this.subsTitleName = subsTitleName;
	}
	public String getSubsRemindTimeHour() {
		return subsRemindTimeHour;
	}
	public void setSubsRemindTimeHour(String subsRemindTimeHour) {
		this.subsRemindTimeHour = subsRemindTimeHour;
	}
	public Integer getSubsType() {
		return subsType;
	}
	public void setSubsType(Integer subsType) {
		this.subsType = subsType;
	}
	public String getSubsRemindWeekStr() {
		if(StringUtil.isNotEmpty(subsRemindWeek)){
			String[] str = subsRemindWeek.split(",");
			subsRemindWeekStr = "周&nbsp;";
			for (String s : str) {
				switch(s){
				case "0":
					subsRemindWeekStr += "日&nbsp;";
				  	break;
				case "1":
					subsRemindWeekStr += "一&nbsp;";
				  	break;
				case "2":
					subsRemindWeekStr += "二&nbsp;";
				  	break;
				case "3":
					subsRemindWeekStr += "三&nbsp;";
				  	break;
				case "4":
					subsRemindWeekStr += "四&nbsp;";
				  	break;
				case "5":
					subsRemindWeekStr += "五&nbsp;";
				  	break;
				case "6":
					subsRemindWeekStr += "六&nbsp;";
				  	break;
				}
			}
			
			subsRemindWeekStr += "重复";
		} else {
			subsRemindWeekStr = "不重复";
		}
			
		return subsRemindWeekStr;
	}
	public void setSubsRemindWeekStr(String subsRemindWeekStr) {
		this.subsRemindWeekStr = subsRemindWeekStr;
	}
		
}
