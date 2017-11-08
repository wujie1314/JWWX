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
@Table(name = "BBS_COMMENTARIES", schema = "JWWX", catalog = "")
public class BbsCommentariesEntity {
	private String id;
	private Timestamp commentsTime;
	private String tellID; //关联User表（说说发布人）
	private String content;
	private String commentsID; //关联user表（评论发布人）
	
	@Id
    @Column(name = "ID")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
    @Basic
    @Column(name = "COMMENTSTIME")
	public Timestamp getCommentsTime() {
		return commentsTime;
	}
	public void setCommentsTime(Timestamp commentsTime) {
		this.commentsTime = commentsTime;
	}
    @Basic
    @Column(name = "TELLID")
	public String getTellID() {
		return tellID;
	}
	public void setTellID(String tellID) {
		this.tellID = tellID;
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
	@Column(name = "COMMENTSID")
	public String getCommentsID() {
		return commentsID;
	}
	public void setCommentsID(String commentsID) {
		this.commentsID = commentsID;
	}
}
