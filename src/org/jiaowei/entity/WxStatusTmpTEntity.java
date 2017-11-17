package org.jiaowei.entity;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created by alex on 15-12-31.
 */
@Entity
@Table(name = "WX_STATUS_TMP_T", schema = "JWWX", catalog = "")
public class WxStatusTmpTEntity {
    private Integer id;
    private String wxOpenid;//微信ID
    private String csId;//座席ID
    private Timestamp createTime;//
    private Timestamp startTime;
    private Timestamp endTime;
    private String sessionId;//socketID
    private Long allocTime;//分配时间
    /**
     * 服务状态
     *0:  表示微信用户 将到达，没有拉入座席
     *1：表示微信用户 已经接入到座席 通道未建立
     *2：表示微信用户 已经接入到座席 通道建立
     *3：表示已删除，关闭通道
     *4：表示在留言队列中
     */
    private Integer serviceStatus = 0;
    
    private Long lastChatTime = 0l;//微信用户发送最后一条消息时间
    private Long lastCSTime = 0l;//座席发送最后一条时间
    private Boolean twoSecFlag=false;//２分钟到５分钟发送的信息
    
    private Long intoWaitingMapTime = 0l;//进入等待队列的时间
    private Long intoWaitingTime = 0l;//进入等待队列的时间,排队用
    private Long chatOvertime = 0l;//聊天超时时间
    private Integer sendType = 0;//0:用户发的，1座席发的
    private Integer serviceHintNum = 0;//服务状态中 断开提示的次数
    private Integer waitingHintNum = 0;//服务状态中 断开提示的次数
    private Integer userOffHintNum = 0;//用户断开前提示次数
    private Integer serviceOffHintNum = 0;//座席断开前提示次数
    private Integer isInitiative = 0; //是否座席主动拉入，1是，则不计微信客户时间
    private String msgServiceId;//聊天记录中的serviceId，附件用
    
    // 新增进入留言状态
    private boolean message = false;
    // 进入消息留言计时
    private Long beginTimestamp;
    
    // 留言结束时间
    private Long messageEndTime;
   
	public Long getMessageEndTime() {
		return messageEndTime;
	}

	public void setMessageEndTime(Long messageEndTime) {
		this.messageEndTime = messageEndTime;
	}

	public Long getBeginTimestamp() {
		return beginTimestamp;
	}

	public void setBeginTimestamp(Long beginTimestamp) {
		this.beginTimestamp = beginTimestamp;
	}

	@Transient
    public Long getIntoWaitingTime() {
		return intoWaitingTime;
	}

	public void setIntoWaitingTime(Long intoWaitingTime) {
		this.intoWaitingTime = intoWaitingTime;
	}

	@Transient
    public String getMsgServiceId() {
		return msgServiceId;
	}

	public void setMsgServiceId(String msgServiceId) {
		this.msgServiceId = msgServiceId;
	}

	@Transient
    public Integer getIsInitiative() {
		return isInitiative;
	}

	public void setIsInitiative(Integer isInitiative) {
		this.isInitiative = isInitiative;
	}

	@Transient
    public Integer getUserOffHintNum() {
		return userOffHintNum;
	}

	public void setUserOffHintNum(Integer userOffHintNum) {
		this.userOffHintNum = userOffHintNum;
	}

	@Transient
	public Integer getServiceOffHintNum() {
		return serviceOffHintNum;
	}

	public void setServiceOffHintNum(Integer serviceOffHintNum) {
		this.serviceOffHintNum = serviceOffHintNum;
	}

	@Transient
    public Long getChatOvertime() {
		return chatOvertime;
	}

	public void setChatOvertime(Long chatOvertime) {
		this.chatOvertime = chatOvertime;
	}

	@Transient
    public Integer getSendType() {
		return sendType;
	}

	public void setSendType(Integer sendType) {
		this.sendType = sendType;
	}

	@Transient
    public Integer getWaitingHintNum() {
		return waitingHintNum;
	}

	public void setWaitingHintNum(Integer waitingHintNum) {
		this.waitingHintNum = waitingHintNum;
	}

	@Transient
    public Integer getServiceHintNum() {
		return serviceHintNum;
	}

	public void setServiceHintNum(Integer serviceHintNum) {
		this.serviceHintNum = serviceHintNum;
	}

	@Transient
    public Long getAllocTime() {
		return allocTime;
	}

	public void setAllocTime(Long allocTime) {
		this.allocTime = allocTime;
	}

	public Long getLastCSTime() {
		return lastCSTime;
	}

	public void setLastCSTime(Long lastCSTime) {
		this.lastCSTime = lastCSTime;
	}

	/**
     * 这个字段用来保存当前时间,目的在于查询服务历史,评分用
     */
    private String myTimestamp;

    @Id
    @SequenceGenerator(name="WX_STATUS_TMP_S",sequenceName="WX_STATUS_TMP_S",allocationSize = 1)
    @GeneratedValue(generator="WX_STATUS_TMP_S",strategy=GenerationType.SEQUENCE)
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "WX_OPENID")
    public String getWxOpenid() {
        return wxOpenid;
    }

    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid;
    }

    @Basic
    @Column(name = "CS_ID")
    public String getCsId() {
        return csId;
    }

    public void setCsId(String csId) {
        this.csId = csId;
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
    @Column(name = "START_TIME")
    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
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
    @Column(name = "SERVICE_STATUS")
    public Integer getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(Integer serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    @Basic
    @Column(name = "LAST_CHAT_TIME")
    public Long getLastChatTime() {
        return lastChatTime;
    }

    public void setLastChatTime(Long lastChatTime) {
        this.lastChatTime = lastChatTime;
    }

    @Basic
    @Column(name = "SESSION_ID")
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Transient
    public String getMyTimestamp() {
        return myTimestamp;
    }

    public void setMyTimestamp(String myTimestamp) {
        this.myTimestamp = myTimestamp;
    }
    @Transient
    public Long getIntoWaitingMapTime() {
        return intoWaitingMapTime;
    }

    public void setIntoWaitingMapTime(Long intoWaitingMapTime) {
        this.intoWaitingMapTime = intoWaitingMapTime;
    }

    @Transient
    public Boolean getTwoSecFlag() {
        return twoSecFlag;
    }

    public void setTwoSecFlag(Boolean twoSecFlag) {
        this.twoSecFlag = twoSecFlag;
    }
    
    public boolean isMessage() {
		return message;
	}

	public void setMessage(boolean message) {
		this.message = message;
	}
}
