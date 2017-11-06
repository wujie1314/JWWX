package org.jiaowei.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "BBS_USER", schema = "JWWX", catalog = "")
public class BbsUserEntity {
	private String id;
	private String oppenid;
	private String state;
	private String wechatName; //微信的用户名
	
	@Id
    @Column(name = "ID")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Basic
    @Column(name = "OPPENID")	
	public String getOppenid() {
		return oppenid;
	}
	public void setOppenid(String oppenid) {
		this.oppenid = oppenid;
	}
	
	@Basic
    @Column(name = "STATE")
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	@Basic
    @Column(name = "WECHATNAME")
	public String getWechatName() {
		return wechatName;
	}
	public void setWechatName(String wechatName) {
		this.wechatName = wechatName;
	}
}
