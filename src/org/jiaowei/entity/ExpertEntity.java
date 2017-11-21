package org.jiaowei.entity;

import javax.persistence.*;

@Entity
@Table(name = "EXPERT_INFO", schema = "JWWX", catalog = "")
public class ExpertEntity {
	private String id;
	private String expertName;
	private int phone;
	private String introduce;
	
	@Id
	@Column(name = "ID")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Basic
	@Column(name = "EXPERT_NAME")
	public String getExpertName() {
		return expertName;
	}
	public void setExpertName(String expertName) {
		this.expertName = expertName;
	}
	
	@Basic
	@Column(name = "PHONE")
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	
	@Basic
	@Column(name = "INTRODUCE")
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
}
