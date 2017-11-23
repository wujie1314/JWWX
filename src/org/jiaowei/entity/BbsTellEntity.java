package org.jiaowei.entity;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.mysql.fabric.xmlrpc.base.Data;

@Entity
@Table(name = "BBS_TELL", schema = "JWWX", catalog = "")
public class BbsTellEntity {
	private String id;
	private String content;
	private String userID;
	private String title;
	private String state;
	private Timestamp publishedTime;
	private Integer commentsNumber;
	
	@Id
    @Column(name = "ID")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
    @Column(name = "USERID")
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	@Basic
    @Column(name = "TITLE")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Basic
	@Column(name  = "PUBLISHEDTIME")
	public Timestamp getPublishedTime() {
		return publishedTime;
	}
	public void setPublishedTime(Timestamp publishedTime) {
		this.publishedTime = publishedTime;
	}
	@Basic
	@Column(name = "COMMENTSNUMBER")
	public Integer getCommentsNumber() {
		return commentsNumber;
	}
	public void setCommentsNumber(Integer commentsNumber) {
		this.commentsNumber = commentsNumber;
	}
	
	@Basic
	@Column(name = "STATE")
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
