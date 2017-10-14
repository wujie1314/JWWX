package org.jiaowei.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "road_htlj", schema = "JWWX", catalog = "")
public class RoadDlfxEntity {
	
	private String id;
	private String dictCode;
	private String displayName;
	private String factValue;
	private String insertTime;
	private String nodeOrder;

	@Id
    @Column(name = "ID")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Basic
    @Column(name = "DICT_CODE")
	public String getDictCode() {
		return dictCode;
	}
	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}
	
	@Basic
    @Column(name = "DISPLAY_NAME")
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	@Basic
    @Column(name = "FACT_VALUE")
	public String getFactValue() {
		return factValue;
	}
	public void setFactValue(String factValue) {
		this.factValue = factValue;
	}
	
	@Basic
    @Column(name = "INSERT_TIME")
	public String getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}
	
	@Basic
    @Column(name = "NODE_ORDER")
	public String getNodeOrder() {
		return nodeOrder;
	}
	public void setNodeOrder(String nodeOrder) {
		this.nodeOrder = nodeOrder;
	}
	
	
}
