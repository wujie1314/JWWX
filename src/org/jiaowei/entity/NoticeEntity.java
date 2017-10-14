package org.jiaowei.entity;

import javax.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "NOTICE_T", schema = "JWWX", catalog = "")
public class NoticeEntity {
    private Integer id;
    private String content;
    private String title;
    private Timestamp beginTime;
    private Timestamp endTime;
    private Integer deleted;
    private Integer deptId;
    
    @Id
    @SequenceGenerator(name="NOTICE_S",sequenceName="NOTICE_S",allocationSize = 1)
    @GeneratedValue(generator="NOTICE_S",strategy=GenerationType.SEQUENCE)
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    @Column(name = "CONTENT")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

    @Basic
    @Column(name = "BEGIN_TIME")
	public Timestamp getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Timestamp beginTime) {
		this.beginTime = beginTime;
	}

    @Basic
    @Column(name = "END_TIME")
	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

    @Basic
    @Column(name = "DELETED")
	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
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
