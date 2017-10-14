package org.jiaowei.entity;

import org.springframework.web.socket.WebSocketSession;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by alex on 15-12-7.
 * 此类用于保存session和response,
 * 用于座席和微信用户间通信
 */
public class SessionResponse {

    //消息通道
    private WebSocketSession session;
    private HttpServletResponse response;
    //微信用户编号
    private String wxOpenId;
    //座席名字
    private  String customerName;

    public WebSocketSession getSession() {
        return session;
    }

    public void setSession(WebSocketSession session) {
        this.session = session;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public String getWxOpenId() {
        return wxOpenId;
    }

    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
