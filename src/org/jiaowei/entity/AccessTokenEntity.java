package org.jiaowei.entity;

/**
 * Created by alex on 15-12-1.
 * 用于保存access_token
 */
public class AccessTokenEntity {
    /**
     * 获得access_token的时间
     */
    private Long obtainTime;
    /**
     * 获得的token
     */
    private String accessToken;
    /**
     * 多久过期
     */
    private Long expireIn;

    public Long getObtainTime() {
        return obtainTime;
    }

    public void setObtainTime(Long obtainTime) {
        this.obtainTime = obtainTime;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(Long expireIn) {
        this.expireIn = expireIn;
    }

}
