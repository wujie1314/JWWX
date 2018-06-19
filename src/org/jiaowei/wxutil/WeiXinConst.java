package org.jiaowei.wxutil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jiaowei.entity.AccessTokenEntity;
import org.jiaowei.entity.JsapiTicketEntity;
import org.jiaowei.entity.NavigationMenuEntity;
import org.jiaowei.entity.WeixinAutoRespondEntity;
import org.springframework.web.socket.WebSocketSession;

/**
 * Created by root on 15-11-26.
 */
public class WeiXinConst {
	
	public static Map<String, NavigationMenuEntity> navigationMenu = new HashMap<String, NavigationMenuEntity>();//微信用户进入菜单队列
	
	public static Map<String, List<WeixinAutoRespondEntity>> navAutoMenu = new HashMap<String, List<WeixinAutoRespondEntity>>();//微信用户进入菜单队列

	
	public static synchronized void putMenuMap(String openId, NavigationMenuEntity menuEntity){
		navigationMenu.put(openId, menuEntity);
	}
	
	public static synchronized void removeMenuMap(String openId){
		navigationMenu.remove(openId);
	}
	
    //接入微信平台的token
    public static String token = "jiaoweiwx";
    //接入微信平台的状态
    public static boolean isConnWx = false;

//    微信APPID自已用的测试号
//    public static  String appId ="wx5a95d88c54f71476";
//    public static String appSecret ="d4624c36b6795d1d99dcf0547af5443d";
    

    //甘兆云测试号
//    public static String appId = "wxe66364391f890f05";
//    public static String appSecret = "d4624c36b6795d1d99dcf0547af5443d";

    public static String appId = "wx91478e90ff8f594c";
    public static String appSecret = "675702defa12940c0463be408a10fcbe";
    
    
//    微信APPID正式号
//    public static String appId = "wx915ad295909bf037";
//    public static String appSecret = "b26b2f2432620fb532d117fe6d132afa";

    //保存access_token
    public static AccessTokenEntity accessToken = new AccessTokenEntity();
    public static JsapiTicketEntity jsapiTicket = new JsapiTicketEntity();

    public static Map<Integer ,Map<String,Object>> publicInfos = new HashMap<Integer,Map<String,Object>>();

    // 保存多个公众号的access_token
    public static Map<String ,AccessTokenEntity> accessTokenMap =new HashMap<String,AccessTokenEntity>();
    public static Map<String ,JsapiTicketEntity> jsapiTicketMap = new HashMap<String,JsapiTicketEntity>();

    //用来保存与用户会话的webSocketSession
    public static Map<String, WebSocketSession> webSocketSessionMap = new HashMap<String, WebSocketSession>();
 
    //坐席id对应的sessionId
    public static Map<String, WebSocketSession> userSessionMap = new HashMap<String, WebSocketSession>();
    
    //session对应的openIdLists
    public static Map<String, List<String>> sessionOpenId = new HashMap<String, List<String>>();
    
    
    //保存刚来的等待服务的微信用户
//    public static ConcurrentMap<String, WxStatusTmpTEntity> waitingMap = new ConcurrentHashMap<String, WxStatusTmpTEntity>();

    //正在服务的微信用户和座席对应的信息
//    public static ConcurrentMap<String, WxStatusTmpTEntity> servicingMap = new ConcurrentHashMap<String, WxStatusTmpTEntity>();
    
    //已经结束服务的信息 此队列主要用来评分用
//    public static ConcurrentMap<String, WxStatusTmpTEntity> deletedMap = new ConcurrentHashMap<String, WxStatusTmpTEntity>();
    //在线座席队列
//    public static ConcurrentMap<String, SysUserEntity> onLineCsMap = new ConcurrentHashMap<String, SysUserEntity>();
    //存取座席取的次数
    public static Map<String, Integer> seatsNumMap = new HashMap<String, Integer>();
    //存取座席转交
    public static Map<String, String> transferUserMap = new HashMap<String, String>();
    //存取座席转交
    public static Map<String, String> transferedUserMap = new HashMap<String, String>();
    //存取座席转交
    public static Map<String, String> transferingUserMap = new HashMap<String, String>();
    //存取座席转交
    public static Map<String, String> inviteUserMap = new HashMap<String, String>();
    //存取座席邀请
    public static Map<String, Map<String,String>> servicingYqMap = new HashMap<String, Map<String,String>>();
    //座席群聊
    public static Map<String, Map<String,String>> servicingMsgMap = new HashMap<String, Map<String,String>>();
    //用来保存一个服务在好久没有响应时,重新排队
//    public static final long chatExpire = 2 * 60;
    public static Map<String,String> ipAndAddressMap=new HashMap<String,String>();
    //两分钟退出发給页面字个字符串
    public static String twoMin= "TWO_MIN_END_SERVICE";
    public static Map<String, String> socketMap = new HashMap<String, String>();

    private static Map<String,Object> getPublicInfo(Integer deptID){
        Map<String ,Object> info = publicInfos.get(deptID);
        if(null == info ){
            info = WeiXinOperUtil.getPublicInfoByDeptID(deptID);
            publicInfos.put(deptID,info);
        }
        return  info;
    }


    public static String getAppID(Integer deptID){
        Map<String,Object> info = getPublicInfo(deptID);
        return  info.get("appId").toString();
    }

    public static String getAppSecret(Integer deptID){
        Map<String,Object> info = getPublicInfo(deptID);
       return info.get("appSecret").toString();
    }
}
