package org.jiaowei.entity;

/**
 * Created by alex on 15-12-1.
 * 用于保存access_token
 */
public class JsapiTicketEntity {
    /**
     * 获得access_token的时间
     */
    private Long obtainTime;
    /**
     * 获得的token
     */
    private String jsapiTicket;
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

    public Long getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(Long expireIn) {
        this.expireIn = expireIn;
    }

	public String getJsapiTicket() {
		return jsapiTicket;
	}

	public void setJsapiTicket(String jsapiTicket) {
		this.jsapiTicket = jsapiTicket;
	}

    
}
