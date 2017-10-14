package org.jiaowei.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

public interface WxNavigationMenuService {
	
	/**
	 * 微信用户第一次发送消息
	 * @param map
	 * @param response
	 * @param openId
	 */
	public void sendMsgOneWxHint(Map<String, String> map, HttpServletResponse response, String openId);
	
}
