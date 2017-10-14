package com.foxconn.socket.client;

import org.apache.mina.core.session.IoSession;

import java.util.List;

public class SocketAppPacket {
	public static final int COMMAND_PUSH_MESSAGE = 0x0000002e;//推送通知
	public static final int GET_VIOLATION_REPROT_BASE_DATA = 0x0000002f; 
	public static final int GET_VIOLATION_REPROT_LIST = 0x00000033; 
	public static final int GET_VIOLATION_REPROT_BY_BATCH_NO = 0x00000032; 
	public static final int ADD_VIOLATION_REPROT_COMMENT = 0x00000034; 
	public static final int ADD_VIOLATION_REPROT_VIEW = 0x00000035;
	private IoSession fromClient = null;

	/**
	 * @return the fromClient
	 */
	public IoSession getFromClient() {
		return fromClient;
	}

	public SocketAppPacket(IoSession channel) {
		this.fromClient = channel;
	}

	String packetType;

	public String getPacketType() {
		return packetType;
	}

	/**
	 * @param packetType
	 *            the packetType to set
	 */
	private void setPacketType(String packetType) {
		this.packetType = packetType;
	}


	private int commandId = 0;

	/**
	 * @return the commandId
	 */
	public int getCommandId() {
		return commandId;
	}

	/**
	 * @param commandId
	 *            the commandId to set
	 */
	public void setCommandId(int commandId) {
		this.commandId = commandId;

//		String typeString = "0x" + Integer.toHexString(commandId).toUpperCase() + "_";
//		switch (commandId) {
//		// 最新版本检查
//		case SocketAppPacket.COMMAND_ID_GET_APP_VERSION:
//			typeString += "GET_APP_VERSION";
//			break;
//		// 用户登录
//		case SocketAppPacket.COMMAND_ID_USER_LOGIIN:
//			typeString += "USER_LOGIN";
//			break;
//		default:
//			typeString += "UNKNOWN";
//			break;
//		}
//
//		setPacketType(typeString);
	}

	private byte[] commandData = null;
	/**
	 * @return the commandData
	 */
	public byte[] getCommandData() {
		return commandData;
	}

	/**
	 * @param commandData
	 *            the commandData to set
	 */
	public void setCommandData(byte[] commandData) {
		this.commandData = commandData;
	}

	long receiveTime = 0;

	/**
	 * @return the receiveTime
	 */
	public long getReceiveTime() {
		return receiveTime;
	}

	/**
	 * @param receiveTime
	 *            the receiveTime to set
	 */
	public void setReceiveTime(long receiveTime) {
		this.receiveTime = receiveTime;
	}

}
