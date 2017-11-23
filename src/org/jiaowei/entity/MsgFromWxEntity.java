package org.jiaowei.entity;

import javax.persistence.*;

import java.sql.Timestamp;

/**
 * Created by alex on 15-12-21.
 */
@Entity
@Table(name = "MSG_FROM_WX_T", schema = "JWWX", catalog = "")
public class MsgFromWxEntity {
    private Integer id;
    private String msgId;
    private String fromUserName;
    private String msgType;
    private String toUserName;
    private String content;
    private String msgSource;
    private Timestamp createTime;
    private String imageUrl;
    private String voiceUrl;
    private String videoUrl;
    private String workUnitNum;
    private Integer readFlag;
    private Timestamp readTime;
    private String workServiceId;
    private String locationX;
    private String locationY;
    private String lable;
    private Integer scale;
    
    @Id
    @SequenceGenerator(name="MSG_FROM_WX_SQEN",sequenceName="MSG_FROM_WX_S",allocationSize = 1)
    @GeneratedValue(generator="MSG_FROM_WX_SQEN",strategy= GenerationType.SEQUENCE)
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "MSG_ID")
    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    @Basic
    @Column(name = "FROM_USER")
    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
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
    @Column(name = "TO_USER")
    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    @Basic
    @Column(name = "MSG_CONTENT")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "MSG_SOURCE")
    public String getMsgSource() {
        return msgSource;
    }

    public void setMsgSource(String msgSource) {
        this.msgSource = msgSource;
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
    @Column(name = "WORK_UNIT_NUM")
    public String getWorkUnitNum() {
        return workUnitNum;
    }

    public void setWorkUnitNum(String workUnitNum) {
        this.workUnitNum = workUnitNum;
    }

    @Transient
    public Integer getReadFlag() {
        return readFlag;
    }

    public void setReadFlag(Integer readFlag) {
        this.readFlag = readFlag;
    }

    @Transient
    public Timestamp getReadTime() {
        return readTime;
    }

    public void setReadTime(Timestamp readTime) {
        this.readTime = readTime;
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
    @Column(name = "LOCATION_X")
	public String getLocationX() {
		return locationX;
	}

	public void setLocationX(String locationX) {
		this.locationX = locationX;
	}

	@Basic
	@Column(name = "LOCATION_Y")
	public String getLocationY() {
		return locationY;
	}

	public void setLocationY(String locationY) {
		this.locationY = locationY;
	}

	@Basic
	@Column(name = "LABEL")
	public String getLable() {
		return lable;
	}

	public void setLable(String lable) {
		this.lable = lable;
	}

	@Basic
	@Column(name = "SCALE")
	public Integer getScale() {
		return scale;
	}

	public void setScale(Integer scale) {
		this.scale = scale;
	}
}
