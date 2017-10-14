package org.jiaowei.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "road_htlj", schema = "JWWX", catalog = "")
public class RoadHtljEntity {

	private String oId;
	private String name;
	private String interflowCodeBm;
	private String roadBm;
	private String interflowName;
	private String roadName;
	private String roadLineName;
	private String roadCode;
	private String status;
	private String interflowPos;
	private String lont;
	private String lat;
	
	@Id
    @Column(name = "OID")
	public String getoId() {
		return oId;
	}
	public void setoId(String oId) {
		this.oId = oId;
	}
	
	@Basic
    @Column(name = "ROAD_CODE")
	public String getRoadCode() {
		return roadCode;
	}
	public void setRoadCode(String roadCode) {
		this.roadCode = roadCode;
	}
	
	@Basic
    @Column(name = "ROAD_NAME")
	public String getRoadName() {
		return roadName;
	}
	public void setRoadName(String roadName) {
		this.roadName = roadName;
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
    @Column(name = "INTERFLOW_CODE_BM")
	public String getInterflowCodeBm() {
		return interflowCodeBm;
	}
	public void setInterflowCodeBm(String interflowCodeBm) {
		this.interflowCodeBm = interflowCodeBm;
	}
	
	@Basic
    @Column(name = "ROAD_BM")
	public String getRoadBm() {
		return roadBm;
	}
	public void setRoadBm(String roadBm) {
		this.roadBm = roadBm;
	}
	
	@Basic
    @Column(name = "INTERFLOW_NAME")
	public String getInterflowName() {
		return interflowName;
	}
	public void setInterflowName(String interflowName) {
		this.interflowName = interflowName;
	}
	
	@Basic
    @Column(name = "ROAD_LINE_NAME")
	public String getRoadLineName() {
		return roadLineName;
	}
	public void setRoadLineName(String roadLineName) {
		this.roadLineName = roadLineName;
	}
	
	@Basic
    @Column(name = "STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Basic
    @Column(name = "INTERFLOW_POS")
	public String getInterflowPos() {
		return interflowPos;
	}
	public void setInterflowPos(String interflowPos) {
		this.interflowPos = interflowPos;
	}
	
	@Basic
    @Column(name = "LONT")
	public String getLont() {
		return lont;
	}
	public void setLont(String lont) {
		this.lont = lont;
	}
	
	@Basic
    @Column(name = "LAT")
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	
}
