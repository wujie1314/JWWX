package org.jiaowei.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by alex on 15-12-21.
 */
@Entity
@Table(name = "MSG_FROM_CUSTOMER_SERVICE_T", schema = "JWWX", catalog = "")
public class MsgFromCustomerServiceEntity {
    private Integer id;
    private String fromUser;
    private String toUser;
    private String msgType;
    private String msgContent;
    private Timestamp createTime;
    private String msgJsonResource;
    private String imageUrl;
    private String voiceUrl;
    private String videoUrl;
    private String workUnitId;
    private String workServiceId;
    private Integer isSuccess;

    @Id
    @SequenceGenerator(name="MSG_FROM_CUSTOMER_SERVICE_S",sequenceName="MSG_FROM_CUSTOMER_SERVICE_S",allocationSize = 1)
    @GeneratedValue(generator="MSG_FROM_CUSTOMER_SERVICE_S",strategy=GenerationType.SEQUENCE)
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "FROM_USER")
    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    @Basic
    @Column(name = "TO_USER")
    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    @Basic
    @Column(name = "MSG_TYPE")
    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    @Basic
    @Column(name = "MSG_CONTENT")
    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
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
    @Column(name = "MSG_JSON_RESOURCE")
    public String getMsgJsonResource() {
        return msgJsonResource;
    }

    public void setMsgJsonResource(String msgJsonResource) {
        this.msgJsonResource = msgJsonResource;
    }

    @Basic
    @Column(name = "IMAGE_URL")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Basic
    @Column(name = "VOICE_URL")
    public String getVoiceUrl() {
        return voiceUrl;
    }

    public void setVoiceUrl(String voiceUrl) {
        this.voiceUrl = voiceUrl;
    }

    @Basic
    @Column(name = "VIDEO_URL")
    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @Basic
    @Column(name = "WORK_UNIT_ID")
    public String getWorkUnitId() {
        return workUnitId;
    }

    public void setWorkUnitId(String workUnitId) {
        this.workUnitId = workUnitId;
    }
    
    @Basic
    @Column(name = "WORK_SERVICE_ID")
    public String getWorkServiceId() {
        return workServiceId;
    }

    public void setWorkServiceId(String workServiceId) {
        this.workServiceId = workServiceId;
    }
    
    @Basic
    @Column(name = "IS_SUCCESS")
	public Integer getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(Integer isSuccess) {
		this.isSuccess = isSuccess;
	}
    
    
}
