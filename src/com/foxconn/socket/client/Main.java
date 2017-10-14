package com.foxconn.socket.client;

import java.net.InetSocketAddress;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.jiaowei.wxutil.WeiXinConst;

import com.yzh.cqjw.request.AddViolationReportCommentRequest;
import com.yzh.cqjw.request.AddViolationReportViewRequest;
import com.yzh.cqjw.request.GetPagedViolationReportListRequest;
import com.yzh.cqjw.request.GetViolationReportBaseDataRequest;
import com.yzh.cqjw.request.GetViolationReportByBatchNoRequest;

/**
 * Created by Jeff on 2016/8/26.
 */
public class Main {
	// 开发环境
	// private static final String ip = "172.2.96.100";
	// 发布环境
	private static final String ip = "203.93.109.52";
	private static final int port = 8899;

	public static void main(String[] args) throws InterruptedException {
		NioSocketConnector connector = new NioSocketConnector();
		connector.getFilterChain().addLast("logger", new LoggingFilter());
		connector.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new SocketProtocolCodecFactory())); // 设置编码过滤器
		connector.setHandler(new SocketClientHandlerListener());// 设置事件处理器
		ConnectFuture cf = connector.connect(new InetSocketAddress(ip, port));// 建立连接
		cf.awaitUninterruptibly();// 等待连接创建完成
		WeiXinConst.socketMap.put(
				"ADD_VIOLATION_REPROT_VIEW" + cf.getSession().getId(),
				null);
		SocketAppPacket packet = new SocketAppPacket(cf.getSession());
		AddViolationReportViewRequest.AddViolationReportViewRequestMessage.Builder builder = AddViolationReportViewRequest.AddViolationReportViewRequestMessage
				.newBuilder();
		builder.setVersion("");
		builder.setUserid(1);
		builder.setReportId(625);
		builder.setUserPhone("");
		builder.setReportBatchNo("3748e7b7db0140eb9213c4e562c147bc");
		if(!"".equals("22")){
			builder.setPlatform("weixin");
		}else{
			builder.setPlatform("App");
		}
		builder.setOpenid("wqwqqww");
		packet.setCommandId(SocketAppPacket.ADD_VIOLATION_REPROT_VIEW);
		packet.setCommandData(builder.build().toByteArray());
		cf.getSession().write(packet);// 发送消息
		while (WeiXinConst.socketMap
				.get("ADD_VIOLATION_REPROT_VIEW"
						+ cf.getSession().getId()) == null) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		String result = WeiXinConst.socketMap
				.get("ADD_VIOLATION_REPROT_VIEW"
						+ cf.getSession().getId());
		System.out.println(result);
		
		// // 消息推送
		//
		// PushRequest.PushRequestMessage.Builder builder =
		// PushRequest.PushRequestMessage.newBuilder();
		// // builder.setUserid(1255);
		// //推送内容
		// builder.setDescription("测试推送消息,,,,,,");
		// //推送类型 0 广播 1 按tag 2 指定设备
		// builder.setType(2);
		// /** 消息类型
		// * 后台审核违规举报 backgroundAuthViolationReport
		// * 执法队审核违规举报 lawUnitAuthViolationReport
		// */
		// builder.setMessageType("backgroundAuthViolationReport");
		// builder.setTitle("标题");
		// //操作员id
		// builder.setOperId(111);
		// //推送平台
		// builder.setPlatform("Android");// Android/IOS
		// //推送的channelId
		// builder.setChannelId("4392933565713759936");
		// //指令
		// packet.setCommandId(SocketAppPacket.COMMAND_PUSH_MESSAGE);
		// //数据
		// packet.setCommandData(builder.build().toByteArray());
		//
		// // ******************************************************
		// logger.info("[request]" + " sending...");
		//
		// cf.getSession().write(packet);// 发送消息

		// //GetPagedViolationReportListRequest
		// GetPagedViolationReportListRequest.GetPagedViolationReportListRequestMessage.Builder
		// builder=
		// GetPagedViolationReportListRequest.GetPagedViolationReportListRequestMessage.newBuilder();
		// builder.setPageIndex(1);
		// builder.setPageSize(25);
		// builder.setVersion("");
		// builder.setIsexposure(1);
		// builder.setDeviceNo("");
		// packet.setCommandId(SocketAppPacket.QUERY_PAGED_VIOLATION_REPROT_LIST);
		// packet.setReceiveTime(System.currentTimeMillis());
		// packet.setCommandData(builder.build().toByteArray());
		// cf.getSession().write(packet);// 发送消息
		// while(WeiXinConst.getPagedViolationReportListRequestMap.get("QUERY_PAGED_VIOLATION_REPROT_LIST"+cf.getSession().getId())==null){
		// Thread.sleep(100);
		// }
		// String
		// result=WeiXinConst.getPagedViolationReportListRequestMap.get("QUERY_PAGED_VIOLATION_REPROT_LIST"+cf.getSession().getId());

	}
}
