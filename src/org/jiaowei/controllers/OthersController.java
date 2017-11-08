package org.jiaowei.controllers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jiaowei.entity.MsgFromCustomerServiceEntity;
import org.jiaowei.entity.MsgFromWxEntity;
import org.jiaowei.entity.WeixinPublicInfoEntity;
import org.jiaowei.service.MsgFromCustomerServiceService;
import org.jiaowei.service.NavMenuService;
import org.jiaowei.service.WeixinAutoRespondService;
import org.jiaowei.service.WeixinPublicInfoService;
import org.jiaowei.util.ListUtils;
import org.jiaowei.wxutil.NavMenuInitUtils;
import org.jiaowei.wxutil.WeiXinOperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OthersController {

	@Autowired
    private MsgFromCustomerServiceService msgFromCustomerService;
	
    @Autowired
    private NavMenuService navMenuService;
	    
    @Autowired
    private WeixinAutoRespondService autoRespondService;
    
    @Autowired
    private WeixinPublicInfoService publicInfoService;
	
	@RequestMapping("/others/home")
	public String othersList(String openId,HttpServletRequest request,  HttpServletResponse response){
		System.out.println(openId);
		request.getSession().setAttribute("othersOpenId", openId);
		
		return "others/home";
	}
	
	 /**
     * 获取历史消息
     * @return
     */
    @RequestMapping(value="others/appmes",method=RequestMethod.POST)
    @ResponseBody
    public String receiveAppMessage(@RequestBody Map<String, String> map, HttpServletResponse response, HttpServletRequest request) {
    	 String msgType = map.get("MsgType"), content = map.get("Content");
		navMenuService.sendCustomerService(map, response, request);
		return "-1";
    }
	
	
	 /**
     * 获取历史消息
     * @return
     */
    @RequestMapping(value="others/getMsgOneDay",method=RequestMethod.GET)
    @ResponseBody
    public List<Object> getHisMsgOneDay(String openid,String bTime,String eTime,int begin,int end) {
    	 List<Object> list = new ArrayList<Object>();
         String hql2 = "FROM MsgFromWxEntity where fromUserName='"+openid+"' and createTime<=to_timestamp('"+eTime+"','yyyy-mm-dd hh24:mi:ss:ff') and createTime>=to_timestamp('"+bTime+"','yyyy-mm-dd hh24:mi:ss:ff') order by id";
         List<MsgFromWxEntity> list2 = msgFromCustomerService.findByHql(hql2);
         String hql1 = "FROM MsgFromCustomerServiceEntity where toUser='"+openid+"' and createTime<=to_timestamp('"+eTime+"','yyyy-mm-dd hh24:mi:ss:ff') and createTime>=to_timestamp('"+bTime+"','yyyy-mm-dd hh24:mi:ss:ff') order by id";
         List<MsgFromCustomerServiceEntity> list1 = msgFromCustomerService.findByHql(hql1);
         List<Object> allList=ListUtils.getTimeOrderFor2List(list1,list2);
         for(int i=allList.size()-begin;i>0&&i>allList.size()-end;i--){
         	list.add(allList.get(i-1));
         }
         return list;
    }
    
    /**
     * 获取历史消息
     * @return
     */
    @RequestMapping(value="others/getMesQueue",method=RequestMethod.GET)
    @ResponseBody
    public List<Object> getMsgFromQueue(String openid,String bTime,String eTime,int begin,int end) {
    	 List<Object> list = new ArrayList<Object>();
         String hql2 = "FROM MsgFromWxEntity where fromUserName='"+openid+"' and createTime<=to_timestamp('"+eTime+"','yyyy-mm-dd hh24:mi:ss:ff') and createTime>=to_timestamp('"+bTime+"','yyyy-mm-dd hh24:mi:ss:ff') order by id";
         List<MsgFromWxEntity> list2 = msgFromCustomerService.findByHql(hql2);
         String hql1 = "FROM MsgFromCustomerServiceEntity where toUser='"+openid+"' and createTime<=to_timestamp('"+eTime+"','yyyy-mm-dd hh24:mi:ss:ff') and createTime>=to_timestamp('"+bTime+"','yyyy-mm-dd hh24:mi:ss:ff') order by id";
         List<MsgFromCustomerServiceEntity> list1 = msgFromCustomerService.findByHql(hql1);
         List<Object> allList=ListUtils.getTimeOrderFor2List(list1,list2);
         for(int i=allList.size()-begin;i>0&&i>allList.size()-end;i--){
         	list.add(allList.get(i-1));
         }
         return list;
    }
    
    
    /**
     * @description
     *
     * 获取wxjs-SDK 
     * @auther zkl
     * @create 16:19 2017/9/3
     *
     **/
    @RequestMapping(value = "others/getWxConfig",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> getWxConfig(String url,String openId){

    	String publicID = NavMenuInitUtils.getInstance().userPublicIdMap.get(openId);
        Map<String,String> config = new HashMap<String,String>();
        WeixinPublicInfoEntity publicInfoEntity =  publicInfoService.getPublicInfoById(publicID);
        
        System.out.println(publicInfoEntity);
        String jsapi_ticket = WeiXinOperUtil.getJsApiTicket(publicID);
        String timestamp = Long.toString(System.currentTimeMillis() / 1000);
        String nonceStr = UUID.randomUUID().toString();
        String signature = getSignature(jsapi_ticket, nonceStr, timestamp,url);

        config.put("appId",publicInfoEntity.getAppId());
        config.put("timestamp",timestamp);
        config.put("nonceStr",nonceStr);
        config.put("signature",signature);
        config.put("jsapi_ticket",jsapi_ticket);

        return config;
    }
    
    /**
     * @description
     *
     * 验证签名。
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @auther zkl
     * @create 17:17 2017/9/27
     *
     **/
    public static String getSignature(String jsapi_ticket, String nonceStr, String timestamp, String url) {

        String signature = "jsapi_ticket="+jsapi_ticket+"&noncestr="+nonceStr+"&timestamp="+timestamp+"&url="+url;

        System.err.println(signature);


        return getSignature(signature);
    }

    public static String getSignature(String signature) {

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] bytes = md.digest(signature.getBytes());

        return bytes2hex03(bytes);
    }


    /**
     * @description
     *
     * 将字节数组转换为十六进制字符串
     *
     * @auther zkl
     * @create 17:18 2017/9/27
     *
     **/
    public static String bytes2hex03(byte[] bytes)
    {
        final String HEX = "0123456789abcdef";
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b : bytes)
        {
            // 取出这个字节的高4位，然后与0x0f与运算，得到一个0-15之间的数据，通过HEX.charAt(0-15)即为16进制数
            sb.append(HEX.charAt((b >> 4) & 0x0f));
            // 取出这个字节的低位，与0x0f与运算，得到一个0-15之间的数据，通过HEX.charAt(0-15)即为16进制数
            sb.append(HEX.charAt(b & 0x0f));
        }

        return sb.toString();
    }
}
