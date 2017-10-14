package org.jiaowei.util;

import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;

/**
 * 公共数据使用
 * @author mx.li
 * @Date 2016-01-12
 */
public class CommonConstantUtils {
	private static ResourceBundle rb;
	static {
		rb = ResourceBundle.getBundle("weixin");
	}
	
	/**
	 * 用户输入0是提示用户信息
	 * @return
	 */
	public static String input0SysHint() {
		return iso2utf8(rb.getString("input.0.sys.hint"));
	}
	/**
	 * 等待服务中第一次超时时间默认30秒
	 * @return
	 */
	public static int wait1Times() {
		// String smsContent=new String(CommonConstant.getSmsContent().getBytes("ISO-8859-1"),"utf-8");
		String times = rb.getString("wait.1.times"); 
		return Integer.parseInt(times);
	}
	/**
	 * 等待服务中第一次超时提示信息
	 * @return
	 */
	public static String wait1SysHint() {
		return iso2utf8(rb.getString("wait.1.sys.hint"));
	}
	/**
	 * 等待服务中第二次超时时间默认30秒
	 * @return
	 */
	public static int wait2Times() {
		String times = rb.getString("wait.2.times");
		return Integer.parseInt(times);
	}
	/**
	 * 等待服务中第二次超时提示信息
	 * @return
	 */
	public static String wait2SysHint() {
		return iso2utf8(rb.getString("wait.2.sys.hint"));
	}
	/**
	 * 等待服务中第三次超时时间默认30秒
	 * @return
	 */
	public static int wait3Times() {
		String times = rb.getString("wait.3.times");
		return Integer.parseInt(times);
	}
	/**
	 * 等待服务中第二次超时提示信息
	 * @return
	 */
	public static String wait3SysHint() {
		return iso2utf8(rb.getString("wait.3.sys.hint"));
	}
	/**
	 * 分配时提示用户信息
	 * @return
	 */
	public static String allotSysHint() {
		return iso2utf8(rb.getString("allot.sys.hint"));
	}
	
	/**
	 * 分配后未建立通道时第一次提示超时时间
	 * @return
	 */
	public static int serviceState11Times() {
		String times = rb.getString("service.state.1.1.times");
		return Integer.parseInt(times);
	}
	/**
	 * 分配后未建立通道时第一次提示
	 * @return
	 */
	public static String serviceState11SysHint() {
		return iso2utf8(rb.getString("service.state.1.1.sys.hint"));
	}
	/**
	 * 分配后未建立通道时第二次提示超时时间
	 * @return
	 */
	public static int serviceState12Times() {
		String times = rb.getString("service.state.1.2.times");
		return Integer.parseInt(times);
	}
	/**
	 * 分配后未建立通道时第二次提示
	 * @return
	 */
	public static String serviceState12SysHint() {
		return iso2utf8(rb.getString("service.state.1.2.sys.hint"));
	}
	/**
	 * 分配后未建立通道时第三次提示超时时间
	 * @return
	 */
	public static int serviceState13Times() {
		String times = rb.getString("service.state.1.3.times");
		return Integer.parseInt(times);
	}
	/**
	 * 分配后未建立通道时第三次提示
	 * @return
	 */
	public static String serviceState13SysHint() {
		return iso2utf8(rb.getString("service.state.1.3.sys.hint"));
	}
	
	
	/**
	 * 服务中用户退出时友情提示步长,开始时间，则步长等于定时器的扫描的时长
	 * @return
	 */
	public static int serviceState2User11Times() {
		String times = rb.getString("service.state.2.user.1.1.times");
		return Integer.parseInt(times);
	}
	
	/**
	 * 服务中用户退出时友情提示步长，结束时间，则步长等于定时器的扫描的时长
	 * @return
	 */
	public static int serviceState2User12Times() {
		String times = rb.getString("service.state.2.user.1.2.times");
		return Integer.parseInt(times);
	}
	/**
	 * 服务中用户退出时友情提示
	 * @return
	 */
	public static String serviceState2User1Hint() {
		return iso2utf8(rb.getString("service.state.2.user.1.sys.hint"));
	}
	
	/**
	 * 服务中用户超时时长
	 * @return
	 */
	public static int serviceState2UserTimes() {
		String times = rb.getString("service.state.2.user.times");
		return Integer.parseInt(times);
	}
	
	/**
	 * 服务中用户超时提示信息
	 * @return
	 */
	public static String serviceState2UserHint() {
		return iso2utf8(rb.getString("service.state.2.user.sys.hint"));
	}
	
	/**
	 * 服务中第一次提示超时时间
	 * @return
	 */
	public static int serviceState21Times() {
		String times = rb.getString("service.state.2.1.times");
		return Integer.parseInt(times);
	}
	/**
	 * 服务中第一次提示超时提示
	 * @return
	 */
	public static String serviceState21SysHint() {
		return iso2utf8(rb.getString("service.state.2.1.sys.hint"));
	}
	/**
	 * 服务中第二次提示超时时间
	 * @return
	 */
	public static int serviceState22Times() {
		String times = rb.getString("service.state.2.2.times");
		return Integer.parseInt(times);
	}
	/**
	 * 服务中第二次提示超时提示
	 * @return
	 */
	public static String serviceState22SysHint() {
		return iso2utf8(rb.getString("service.state.2.2.sys.hint"));
	}
	/**
	 * 服务中第三次提示超时时间
	 * @return
	 */
	public static int serviceState23Times() {
		String times = rb.getString("service.state.2.3.times");
		return Integer.parseInt(times);
	}
	/**
	 * 服务中第三次提示超时提示
	 * @return
	 */
	public static String serviceState23SysHint() {
		return iso2utf8(rb.getString("service.state.2.3.sys.hint"));
	}
	
	/**
	 * 服务中用户退出时友情提示步长,开始时间，则步长等于定时器的扫描的时长
	 * @return
	 */
	public static int serviceState231Times() {
		String times = rb.getString("service.state.2.3.1.times");
		return Integer.parseInt(times);
	}
	
	/**
	 * 服务中用户退出时友情提示步长，结束时间，则步长等于定时器的扫描的时长
	 * @return
	 */
	public static int serviceState232Times() {
		String times = rb.getString("service.state.2.3.2.times");
		return Integer.parseInt(times);
	}
	/**
	 * 服务中用户退出时友情提示
	 * @return
	 */
	public static String serviceState231SysHint() {
		return iso2utf8(rb.getString("service.state.2.3.1.sys.hint"));
	}
	
	/**
	 * 服务中断，提示座席信息
	 * @return
	 */
	public static String sessionFinishSysHint() {
		return iso2utf8(rb.getString("session.finish.sys.hint"));
	}
	
	/**
	 *主动拉人时间，结束时间，则步长等于定时器的扫描的时长
	 * @return
	 */
	public static int serviceInitiativeFinishTimes() {
		String times = rb.getString("initiative.finish.times");
		return Integer.parseInt(times);
	}
	
	public static String iso2utf8(String content){
		try {
			content = new String(content.getBytes("ISO-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return content;
	}
	
	/**
	 * 路况截图图片地址
	 * @return
	 */
	public static String getVideoImgUrl() {
		return rb.getString("video_img_url");
	}
	/**
	 * 示意图图片地址
	 * @return
	 */
	public static String getVideoHintImgUrl() {
		return rb.getString("video_hint_img_url");
	}
	/**
	 * 示意图图片地址
	 * @return
	 */
	public static String getVideoRoadImgPath() {
		return rb.getString("video_road_img_path");
	}

	
}
