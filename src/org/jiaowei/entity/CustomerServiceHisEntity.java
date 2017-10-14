package org.jiaowei.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by alex on 15-12-21.
 */
@Entity
@Table(name = "CUSTOMER_SERVICE_HIS", schema = "JWWX", catalog = "")
public class CustomerServiceHisEntity {
    private Integer id;
    private Integer csId;
    private String csName;
    private String wxOpenid;
    private String wxNickname;
    private Timestamp startTime;
    private Timestamp endTime;
    private String sessionId;
    /**
     * 微信用户评价
     */
    private String wxComment;
    /**
     * 当前的服务状态
     * 1：正在服务
     * 0：已经服务完成
     */
    private Integer serviceStatus;

    @Id
    @SequenceGenerator(name="CUSTOMER_SERVICE_HIS_S",sequenceName="CUSTOMER_SERVICE_HIS_S",allocationSize = 1)
    @GeneratedValue(generator="CUSTOMER_SERVICE_HIS_S",strategy=GenerationType.SEQUENCE)
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "CS_ID")
    public Integer getCsId() {
        return csId;
    }

    public void setCsId(Integer csId) {
        this.csId = csId;
    }

    @Basic
    @Column(name = "CS_NAME")
    public String getCsName() {
        return csName;
    }

    public void setCsName(String csName) {
        this.csName = csName;
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
    @Column(name = "WX_NICKNAME")
    public String getWxNickname() {
        return wxNickname;
    }

    public void setWxNickname(String wxNickname) {
        this.wxNickname = wxNickname;
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
    @Column(name = "WX_COMMENT")
    public String getWxComment() {
        return wxComment;
    }

    public void setWxComment(String wxComment) {
        this.wxComment = wxComment;
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
    @Column(name = "WS_SESSIONID")
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
