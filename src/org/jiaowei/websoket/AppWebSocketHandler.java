package org.jiaowei.websoket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jiaowei.entity.WxStatusTmpTEntity;
import org.jiaowei.util.StringUtil;
import org.jiaowei.wxutil.NavMenuInitUtils;
import org.jiaowei.wxutil.WeiXinConst;
import org.jiaowei.wxutil.WeiXinOperUtil;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

public class AppWebSocketHandler implements WebSocketHandler {

	private Logger logger = Logger.getLogger(SystemWebSocketHandler.class);

	private static Map<String, WebSocketSession> appWebSocketSessionMap = new HashMap<String, WebSocketSession>();

	@Override
	public void afterConnectionClosed(WebSocketSession awss, CloseStatus cs)
			throws Exception {
		String queryString = awss.getUri().getQuery();
		if (!queryString.contains("openId")) {
			logger.info(String.format("参数有误,方法：%s-%s",
					"afterConnectionEstablished", queryString));
			awss.sendMessage(new TextMessage("传递的参数不正确."));
			awss.close();
			return;
		}
		Map<String, String> pamars = parseQueryString(queryString);
		if (null != appWebSocketSessionMap.get(pamars.get("openId"))) {
			appWebSocketSessionMap.remove(pamars.get("openId"));
		}
		if (awss.isOpen()) {
			awss.close();
		}
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		String queryString = session.getUri().getQuery();
		if (!queryString.contains("openId")) {
			logger.info(String.format("参数有误,方法：%s-%s",
					"afterConnectionEstablished", queryString));
			session.sendMessage(new TextMessage("传递的参数不正确."));
			session.close();
			return;
		}
		Map<String, String> pamars = parseQueryString(queryString);
		if (null != appWebSocketSessionMap.get(pamars.get("openId"))) {
			WebSocketSession oldSession = appWebSocketSessionMap.get(pamars
					.get("openId"));
			if (oldSession.isOpen()) {
				oldSession.close();
			}
		}
		appWebSocketSessionMap.put(pamars.get("openId"), session);
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> wsm)
			throws Exception {
		// TODO Auto-generated method stub
		String message = wsm.getPayload().toString();
		if("HeartBeat".equals(message)){
			session.sendMessage(new TextMessage("HeartBeat"));
		}
	}

	@Override
	public void handleTransportError(WebSocketSession awss, Throwable thrwbl)
			throws Exception {
		String queryString = awss.getUri().getQuery();
		if (!queryString.contains("openId")) {
			logger.info(String.format("参数有误,方法：%s-%s",
					"afterConnectionEstablished", queryString));
			awss.sendMessage(new TextMessage("传递的参数不正确."));
			awss.close();
			return;
		}
		Map<String, String> pamars = parseQueryString(queryString);
		if (null != appWebSocketSessionMap.get(pamars.get("openId"))) {
			appWebSocketSessionMap.remove(pamars.get("openId"));
		}
		if (awss.isOpen()) {
			awss.close();
		}
	}

	@Override
	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return false;
	}

	private Map<String, String> parseQueryString(String queryStr) {
		Map<String, String> map = new HashMap<String, String>();
		if (!StringUtil.isEmpty(queryStr)) {
			if (queryStr.contains("&")) {
				String[] tmp = queryStr.split("&");
				for (String tmp1 : tmp) {
					if (tmp1.contains("=")) {
						String[] tmp2 = tmp1.split("=");
						if (tmp2.length > 1)
							map.put(tmp2[0], tmp2[1]);
					}
				}
			} else if (queryStr.contains("=")) {
				String[] tmp2 = queryStr.split("=");
				if (tmp2.length > 1)
					map.put(tmp2[0], tmp2[1]);
			}
		}
		return map;
	}
	
	public static void sendMsgToApp(String openId, String content){
		WebSocketSession session = appWebSocketSessionMap.get(openId);
		try {
			if(null != session && session.isOpen()){
				session.sendMessage(new TextMessage(content));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
