package com.foxconn.socket.client;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.jiaowei.util.JsonUtils;
import org.jiaowei.wxutil.WeiXinConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.protobuf.format.JsonFormat;
import com.yzh.cqjw.response.AddViolationReportCommentResponse;
import com.yzh.cqjw.response.AddViolationReportViewResponse;
import com.yzh.cqjw.response.GetPagedViolationReportListResponse;
import com.yzh.cqjw.response.GetViolationReportBaseDataResponse;
import com.yzh.cqjw.response.GetViolationReportByBatchNoResponse;
import com.yzh.cqjw.response.PushResponse;

/**
 * 采用MINA的通信客户端
 * 
 * @author Tom Xu
 * @version Revision 1.0
 */

public class SocketClientHandlerListener extends IoHandlerAdapter {
	private static final Logger logger = LoggerFactory
			.getLogger(SocketClientHandlerListener.class);

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		super.exceptionCaught(session, cause);
		session.closeNow();
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		super.messageReceived(session, message);
		SocketAppPacket packet = (SocketAppPacket) message;
		JsonFormat jsonFormat = new JsonFormat();
		switch (packet.getCommandId()) {
		case SocketAppPacket.COMMAND_PUSH_MESSAGE:
			PushResponse.PushResponseMessage pushResponseMessage = PushResponse.PushResponseMessage
					.parseFrom(packet.getCommandData());
			logger.info("[response]"+ jsonFormat.printToString(pushResponseMessage));
			break;
		case SocketAppPacket.GET_VIOLATION_REPROT_LIST:
			GetPagedViolationReportListResponse.GetPagedViolationReportListResponseMessage getPagedViolationReportListResponseMessage = GetPagedViolationReportListResponse.GetPagedViolationReportListResponseMessage
					.parseFrom(packet.getCommandData());
//			WeiXinConst.socketMap.put("GET_VIOLATION_REPROT_LIST" + session.getId(),jsonFormat.printToString(getPagedViolationReportListResponseMessage));
			WeiXinConst.socketMap.put("GET_VIOLATION_REPROT_LIST" + session.getId(),formatJson(new SocketResultVo(getPagedViolationReportListResponseMessage)));
//			WeiXinConst.socketMap.put("GET_VIOLATION_REPROT_LIST" + session.getId(), new SocketResultVo(getPagedViolationReportListResponseMessage));
			System.out.println("----------->返回值1："+jsonFormat.printToString(getPagedViolationReportListResponseMessage));
			System.out.println("----------->返回值1："+formatJson(new SocketResultVo(getPagedViolationReportListResponseMessage)));
			break;
		case SocketAppPacket.GET_VIOLATION_REPROT_BY_BATCH_NO:
			GetViolationReportByBatchNoResponse.GetViolationReportByBatchNoResponseMessage getViolationReportByBatchNoMessage = GetViolationReportByBatchNoResponse.GetViolationReportByBatchNoResponseMessage
					.parseFrom(packet.getCommandData());
//			WeiXinConst.socketMap.put("GET_VIOLATION_REPROT_BY_BATCH_NO" + session.getId(),jsonFormat.printToString(getViolationReportByBatchNoMessage));
			WeiXinConst.socketMap.put("GET_VIOLATION_REPROT_BY_BATCH_NO" + session.getId(),formatJson(new SocketResultVo(getViolationReportByBatchNoMessage)));
//			System.out.println("----------->返回值2："+jsonFormat.printToString(getViolationReportByBatchNoMessage));
			System.out.println("----------->返回值2："+formatJson(new SocketResultVo(getViolationReportByBatchNoMessage)));
			break;
		case SocketAppPacket.GET_VIOLATION_REPROT_BASE_DATA:
			GetViolationReportBaseDataResponse.GetViolationReportBaseDataResponseMessage getViolationReportBaseDataMessage = GetViolationReportBaseDataResponse.GetViolationReportBaseDataResponseMessage
					.parseFrom(packet.getCommandData());
//			WeiXinConst.socketMap.put("GET_VIOLATION_REPROT_BASE_DATA" + session.getId(),jsonFormat.printToString(getViolationReportBaseDataMessage));
			WeiXinConst.socketMap.put("GET_VIOLATION_REPROT_BASE_DATA" + session.getId(),formatJson(new SocketResultVo(getViolationReportBaseDataMessage)));
//			System.out.println("----------->返回值3："+jsonFormat.printToString(getViolationReportBaseDataMessage));
			System.out.println("----------->返回值3："+formatJson(new SocketResultVo(getViolationReportBaseDataMessage)));
			break;
		case SocketAppPacket.ADD_VIOLATION_REPROT_COMMENT:
			AddViolationReportCommentResponse.AddViolationReportCommentResponseMessage addViolationReportCommentMessage = AddViolationReportCommentResponse.AddViolationReportCommentResponseMessage
					.parseFrom(packet.getCommandData());
			WeiXinConst.socketMap.put("ADD_VIOLATION_REPROT_COMMENT" + session.getId(),jsonFormat.printToString(addViolationReportCommentMessage));
			System.out.println("----------->返回值4："+jsonFormat.printToString(addViolationReportCommentMessage));
			break;
		case SocketAppPacket.ADD_VIOLATION_REPROT_VIEW:
			AddViolationReportViewResponse.AddViolationReportViewResponseMessage addViolationReportViewMessage = AddViolationReportViewResponse.AddViolationReportViewResponseMessage
					.parseFrom(packet.getCommandData());
			WeiXinConst.socketMap.put(
					"ADD_VIOLATION_REPROT_VIEW" + session.getId(),
					jsonFormat
							.printToString(addViolationReportViewMessage));
			System.out.println("----------->返回值5："+jsonFormat.printToString(addViolationReportViewMessage));
			break;
		default:
			logger.info("[response] client received.");
			break;
		}
		session.closeNow();
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		super.sessionClosed(session);
		session.closeNow();

	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {

		super.sessionIdle(session, status);
	}
	
	public String formatJson(SocketResultVo vo){
		return JsonUtils.object2json(vo);
	}
	
}