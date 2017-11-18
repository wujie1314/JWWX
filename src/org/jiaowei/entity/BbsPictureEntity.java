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
@Table(name = "BBS_PICTURE", schema = "JWWX", catalog = "")
public class BbsPictureEntity {
	private String id;
	private String tellId;
	private String path;
	private String fileName;

	@Id
    @Column(name = "ID")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Basic
    @Column(name = "TELLID")
	public String getTellId() {
		return tellId;
	}
	public void setTellId(String tellId) {
		this.tellId = tellId;
	}
	@Basic
    @Column(name = "FILENAME")
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@Basic
	@Column(name = "PATH")
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
