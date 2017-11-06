package org.jiaowei.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jiaowei.entity.NavigationMenuEntity;
import org.jiaowei.entity.WxStatusTmpTEntity;
import org.springframework.web.socket.WebSocketSession;

public interface NavMenuService {

	/**
	 * 微信用户第一次发送消息
	 * @param map
	 * @param response
	 * @param openId
	 */
	public void sendMsgOneWxHint(Map<String, String> map, HttpServletResponse response, String openId);
	
	/**
	 * 发送消息
	 * @param map
	 * @param openId
	 * @param defaultContent
	 * @param isOffSocket
	 * @param tmp
	 * @return
	 */
	public boolean sendMsgWxHint(Map<String, String> map, String openId, String defaultContent, boolean isOffSocket, WxStatusTmpTEntity tmp);
	
	/**
	 * 发送消息给坐席
	 * @param sessionId
	 * @param content
	 * @param wxOpenId
	 * @param isOffSocket
	 */
	public void sendMsgToService(String sessionId, String content, String wxOpenId, boolean isOffSocket);
	
	/**
	 * 处理微信用户发送的消息
	 * @param map
	 * @param response
	 * @param request
	 */
	public void sendCustomerService(Map<String, String> map, HttpServletResponse response, HttpServletRequest request);
	
	/**
	 * 保存微信用户发送的信息到数据库
	 * @param map
	 * @param request
	 * @param tmp
	 */
	public void saveMsgFromWeixin(Map<String, String> map, HttpServletRequest request, WxStatusTmpTEntity tmp);
	
	/**
	 * 微信用户发送所有分类消息给坐席
	 * @param request
	 * @param map
	 * @param session
	 */
	public void sendMsgToCS(HttpServletRequest request, Map<String, String> map, WebSocketSession session);
	
	/**
	 * 处理评分
	 * @param map
	 * @param entity
	 */
	public void comment(Map<String, String> map, WxStatusTmpTEntity entity);
	
	/**
	 * 发送人工服
	 * @param response
	 * @param map
	 * @param openId
	 * @param deptId
	 */
	public void manualService(HttpServletResponse response, Map<String, String> map, String openId, Integer deptId);
	
	/**
	 * 进入人工服务菜单
	 * @param map
	 * @param response
	 * @param openId
	 * @param menuKey
	 * @param menuEntity
	 */
	public void sendMenuWxHint(Map<String, String> map, HttpServletResponse response, String openId, String menuKey, NavigationMenuEntity menuEntity, String beforeContent);
	
	
	/**
	 * 自动回复消息
	 * @author zkl
	 * @data 2017年11月4日 下午3:14:31
	 */
	public void sendMsgOneWxHintOverride(Map<String, String> map,HttpServletResponse response, String openId);
}
