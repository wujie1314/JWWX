package org.jiaowei.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "road_lxfd", schema = "JWWX", catalog = "")
public class RoadLxfdEntity {

	private String oId;
	private String roadCode;
	private String roadCode1;
	private String roadName;
	private String ldName;
	private String startStakeId;
	private String endStakeId;
	private String startName;
	private String endName;
	private String roadType;
	private String roadDir;
	private String descript;
	
	private Object data;
	
	@Transient
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
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
    @Column(name = "ROAD_CODE1")
	public String getRoadCode1() {
		return roadCode1;
	}
	public void setRoadCode1(String roadCode1) {
		this.roadCode1 = roadCode1;
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
    @Column(name = "LD_NAME")
	public String getLdName() {
		return ldName;
	}
	public void setLdName(String ldName) {
		this.ldName = ldName;
	}
	
	@Basic
    @Column(name = "START_STAKEID")
	public String getStartStakeId() {
		return startStakeId;
	}
	public void setStartStakeId(String startStakeId) {
		this.startStakeId = startStakeId;
	}
	
	@Basic
    @Column(name = "END_STAKEID")
	public String getEndStakeId() {
		return endStakeId;
	}
	public void setEndStakeId(String endStakeId) {
		this.endStakeId = endStakeId;
	}
	
	@Basic
    @Column(name = "START_NAME")
	public String getStartName() {
		return startName;
	}
	public void setStartName(String startName) {
		this.startName = startName;
	}
	
	@Basic
    @Column(name = "END_NAME")
	public String getEndName() {
		return endName;
	}
	public void setEndName(String endName) {
		this.endName = endName;
	}
	
	@Basic
    @Column(name = "ROAD_TYPE")
	public String getRoadType() {
		return roadType;
	}
	public void setRoadType(String roadType) {
		this.roadType = roadType;
	}
	
	@Basic
    @Column(name = "ROAD_DIR")
	public String getRoadDir() {
		return roadDir;
	}
	public void setRoadDir(String roadDir) {
		this.roadDir = roadDir;
	}
	
	@Basic
    @Column(name = "DESCRIPT")
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	
	
}
