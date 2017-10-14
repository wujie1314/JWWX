package org.jiaowei.entity;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "road_send_message", schema = "JWWX", catalog = "")
public class RoadSendMessageEntity {
	private String messId;
	private String messName;
	private String messOpenId;
	private Timestamp messSendTimes;

	
	@Id
    @Column(name = "MESSID")
	public String getMessId() {
		return messId;
	}
	public void setMessId(String messId) {
		this.messId = messId;
	}
	@Basic
    @Column(name = "MESSNAME")
	public String getMessName() {
		return messName;
	}
	public void setMessName(String messName) {
		this.messName = messName;
	}
	@Basic
    @Column(name = "MESSOPENID")
	public String getMessOpenId() {
		return messOpenId;
	}
	public void setMessOpenId(String messOpenId) {
		this.messOpenId = messOpenId;
	}
	@Basic
    @Column(name = "MESSSENDTIMES")
	public Timestamp getMessSendTimes() {
		return messSendTimes;
	}
	public void setMessSendTimes(Timestamp messSendTimes) {
		this.messSendTimes = messSendTimes;
	}
	
}
