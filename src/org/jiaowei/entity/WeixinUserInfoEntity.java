package org.jiaowei.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by alex on 15-12-21.
 */
@Entity
@Table(name = "WEIXIN_USER_INFO_T", schema = "JWWX", catalog = "")
public class WeixinUserInfoEntity {
    private Integer id;
    private String wxOpenId;
    private String nickname;
    private String sex;
    private String city;
    private String province;
    private String country;
    private String headImgUrl;
    private String headImg;
    private Timestamp subscribeTime;
    private String remark;
    private String groupId;
    private String tel;
    private String name;
    private Timestamp unsubscribeTime;
    private Integer userStatus;
    private Integer subscribleTimes;
    private String phone;
    private String redBlack;
    @Id
    @SequenceGenerator(name="WEIXIN_USER_INFO_S",sequenceName="WEIXIN_USER_INFO_S",allocationSize = 1)
    @GeneratedValue(generator="WEIXIN_USER_INFO_S",strategy=GenerationType.SEQUENCE)
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "WX_ID")
    public String getWxOpenId() {
        return wxOpenId;
    }

    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }

    @Basic
    @Column(name = "NICKNAME")
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Basic
    @Column(name = "SEX")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "CITY")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "PROVINCE")
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Basic
    @Column(name = "COUNTRY")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "HEADIMGURL")
    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }
    
    @Basic
    @Column(name = "HEADIMG")
    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }
    
    @Basic
    @Column(name = "SUBSCRIBE_TIME")
    public Timestamp getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(Timestamp subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    @Basic
    @Column(name = "REMARK")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "GROUP_ID")
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Basic
    @Column(name = "TEL")
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
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
    @Column(name = "UNSUBSCRIBE_TIME")
    public Timestamp getUnsubscribeTime() {
        return unsubscribeTime;
    }

    public void setUnsubscribeTime(Timestamp unsubscribeTime) {
        this.unsubscribeTime = unsubscribeTime;
    }

    @Basic
    @Column(name = "USER_STATUS")
    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    @Basic
    @Column(name = "SUBSCRIBLE_TIMES")
    public Integer getSubscribleTimes() {
        return subscribleTimes;
    }

    public void setSubscribleTimes(Integer subscribleTimes) {
        this.subscribleTimes = subscribleTimes;
    }

    @Basic
    @Column(name = "PHONE")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

    @Basic
    @Column(name = "RED_BLACK")
	public String getRedBlack() {
		return redBlack;
	}

	public void setRedBlack(String redBlack) {
		this.redBlack = redBlack;
	}
    
}
