package org.jiaowei.entity;

import javax.persistence.*;

@Entity
@Table(name = "AUTO_RESPOND", schema = "JWWX", catalog = "")
public class WeixinAutoRespondEntity {
	private String id ;
	private String contentCode;
	private String content;
	private String juniorID;
	private Integer deptID;
	private String url;
	
	@Id
    @Column(name = "ID")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Basic
    @Column(name = "CONTENT_CODE")
	public String getContentCode() {
		return contentCode;
	}
	public void setContentCode(String contentCode) {
		this.contentCode = contentCode;
	}
	
	@Basic
    @Column(name = "CONTENT")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Basic
    @Column(name = "JUNIOR_ID")
	public String getJuniorID() {
		return juniorID;
	}
	public void setJuniorID(String juniorID) {
		this.juniorID = juniorID;
	}
	
	@Basic
    @Column(name = "DEPT_ID")
	public Integer getDeptID() {
		return deptID;
	}
	public void setDeptID(Integer deptID) {
		this.deptID = deptID;
	}
	
	@Basic
    @Column(name = "URL")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	

}
