package org.jiaowei.entity;

/**
 * 座席实体表
 * @author acer
 *
 */
public class SeatsEntity {

	private String seatsId;//座席ID
	private Integer seatsServiceMaxNum;//座席服务最大数
	private Integer seatsServiceNum;//座席正在服务数
	private Double seatsServiceFreeNum = 0.0d;//空闲服务数比例
	private int allotType = 0; //0:未分配，1已分配
	
	
	
	public int getAllotType() {
		return allotType;
	}
	public void setAllotType(int allotType) {
		this.allotType = allotType;
	}
	public String getSeatsId() {
		return seatsId;
	}
	public void setSeatsId(String seatsId) {
		this.seatsId = seatsId;
	}
	public Integer getSeatsServiceMaxNum() {
		return seatsServiceMaxNum;
	}
	public void setSeatsServiceMaxNum(Integer seatsServiceMaxNum) {
		this.seatsServiceMaxNum = seatsServiceMaxNum;
	}
	public Integer getSeatsServiceNum() {
		return seatsServiceNum;
	}
	public void setSeatsServiceNum(Integer seatsServiceNum) {
		this.seatsServiceNum = seatsServiceNum;
	}
	public Double getSeatsServiceFreeNum() {
		return seatsServiceFreeNum;
	}
	public void setSeatsServiceFreeNum(Double seatsServiceFreeNum) {
		this.seatsServiceFreeNum = seatsServiceFreeNum;
	}
	
}
