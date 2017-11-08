package org.jiaowei.entity;

import javax.persistence.*;

@Entity
@Table(name = "PUBLIC_INFO", schema = "JWWX", catalog = "")
public class WeixinPublicInfoEntity {
	private String id ;
	private String appId;
	private String appSecret;
	private String token;
	private String name;
	private Integer deptId;
	
	@Id
    @Column(name = "ID")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Basic
    @Column(name = "APP_ID")
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	
	@Basic
    @Column(name = "APP_SECRET")
	public String getAppSecret() {
		return appSecret;
	}
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
	
	@Basic
    @Column(name = "TOKEN")
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	@Basic
    @Column(name = "NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Basic
    @Column(name = "DEPT_ID")
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	
}
