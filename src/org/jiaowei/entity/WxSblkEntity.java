package org.jiaowei.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "WX_SBLK_T", schema = "JWWX", catalog = "")
public class WxSblkEntity {
    private Integer id;
    private String title;
    private String content;
    private String longitude;
    private String latitude;
    private Timestamp createTime;
    private String createMan;
    private String reportMan;
    private Integer reportNum;
    private String address;
    @Id
    @SequenceGenerator(name="WX_SBLK_S",sequenceName="WX_SBLK_S",allocationSize = 1)
    @GeneratedValue(generator="WX_SBLK_S",strategy=GenerationType.SEQUENCE)
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
    @Column(name = "LONGITUDE")
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

    @Basic
    @Column(name = "LATITUDE")
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

    @Basic
    @Column(name = "CREATE_TIME")
	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

    @Basic
    @Column(name = "CREATE_MAN")
	public String getCreateMan() {
		return createMan;
	}

	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}

    @Basic
    @Column(name = "REPORT_MAN")
	public String getReportMan() {
		return reportMan;
	}

	public void setReportMan(String reportMan) {
		this.reportMan = reportMan;
	}

    @Basic
    @Column(name = "REPORT_NUM")
	public Integer getReportNum() {
		return reportNum;
	}

	public void setReportNum(Integer reportNum) {
		this.reportNum = reportNum;
	}

    @Basic
    @Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
