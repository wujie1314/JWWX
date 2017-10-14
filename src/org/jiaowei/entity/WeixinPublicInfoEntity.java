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
	
	@Id
    @Column(name = "id")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Basic
    @Column(name = "addId")
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	
	@Basic
    @Column(name = "appSecret")
	public String getAppSecret() {
		return appSecret;
	}
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
	
	@Basic
    @Column(name = "token")
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	@Basic
    @Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
