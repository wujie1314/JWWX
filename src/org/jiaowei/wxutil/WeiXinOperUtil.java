package org.jiaowei.wxutil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mysql.jdbc.Statement;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.jiaowei.entity.AccessTokenEntity;
import org.jiaowei.entity.JsapiTicketEntity;
import org.jiaowei.service.MsgFromWxService;
import org.jiaowei.service.WeixinPublicInfoService;
import org.jiaowei.service.impl.WeixinPublicInfoServiceImpl;
import org.jiaowei.util.FastJsonUtil;
import org.jiaowei.util.PropertiesUtil;
import org.jiaowei.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by alex on 15-11-30.
 * 微信基本操作的工具类
 */
public class WeiXinOperUtil {

    private static Logger logger = Logger.getLogger(WeiXinOperUtil.class);
    @Autowired
    private  MsgFromWxService msgFromWxService;
    @Autowired
    private   WeixinPublicInfoServiceImpl weixinPublicInfoService ;
    
    /**
     * 连接到微信服务器
     *
     * @param signature
     * @param timestamp
     * @param nonce
     */
    public static Boolean connWx(String signature,
                                 String timestamp,
                                 String nonce) {
        Boolean flag = false;
        String[] arr = new String[]{WeiXinConst.token, timestamp, nonce};
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        MessageDigest md = null;
        
        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            logger.info("接入微信平台发生异常:" + e.getMessage());
        }
        byte[] digest = md.digest(content.toString().getBytes());
        String tmpStr = StringUtil.byteToStr(digest);
        if (tmpStr.equals(signature.toUpperCase())) {
            logger.info("接入微信平台成功.");
            WeiXinConst.isConnWx = true;
            flag = true;
        }
        return flag;
    }

    /**
     * 接收来自微信服务器的消息
     *
     * @param request
     * @return
     */
    public static Map<String, String> receiveMsgFromWX(HttpServletRequest request) {
        Map<String, String> map = null;
        try {
            map = XmlUtil.parseXml(request);
            String publicId = map.get("ToUserName");
            Map<String,Object> publicMap = WeiXinOperUtil.getPublicInfoById(publicId);

            logger.info("收到来自微信("+publicMap.get("id")+")-----"+publicMap.get("name")+" 发来的消息:" + FastJsonUtil.toJson(map));
        } catch (Exception e) {
            logger.info("解析用户发过来的XML异常：" + e.getMessage());
        }
        return map;
    }

    /**
     * 发送消息到服务器
     *
     * @param response
     * @param msgXml
     * @return
     * @throws IOException
     */
    public static Boolean sendMsgToWX(HttpServletResponse response, String msgXml){
        response.setContentType("text/xml; charset=UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
        	e.printStackTrace();
            logger.info(String.format("回复信息给腾讯服务器发生异常:%s", e.getMessage()));
        }
        writer.print(msgXml);
        writer.flush();
//        writer.close();
        return true;
    }
    
	/**
	 * 发送用户
	 * @param entity
	 * @param content
	 * @param wxOpenId
	 */
	public static void sendMsgWx(String content, String wxOpenId){
		System.out.println("- weixin--->:wxOpenId:"+wxOpenId+",content:"+content);
		String userJsonContent = String.format("{\"touser\":\"%s\",\"msgtype\":\"text\",\"text\":{\"content\":\"%s\"}}",
		        wxOpenId, String.format(content));
		//发送給用户
        String publicID = NavMenuInitUtils.getInstance().userPublicIdMap.get(wxOpenId);
//        Integer deptID = NavMenuInitUtils.getInstance().userDeptMap.get(wxOpenId); // 加入的
		WeiXinOperUtil.sendMsgToWx(WeiXinOperUtil.getAccessToken(publicID), userJsonContent);// 获取对应accessToken  已改
	}
    
//	public static void saveMsgDatebase(WxStatusTmpTEntity entity, String content, String wxOpenId){
//        try {
//       	 //保存信息到数据库
//           MsgFromWxEntity msgFromWxEntity = new MsgFromWxEntity();
//           msgFromWxEntity.setContent(content);
//           msgFromWxEntity.setFromUserName(wxOpenId);
//           msgFromWxEntity.setMsgType("text");
//           msgFromWxEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
//           msgFromWxEntity.setToUserName(entity.getCsId());
//           msgFromWxEntity.setWorkServiceId(entity.getMsgServiceId());
//           msgFromWxService.save(msgFromWxEntity);
//       } catch (Exception e) {
//           logger.error("MsgFromWxEntity->save发生了异常：" + e.getMessage());
//       }
//       
//	}

	
	 public static Map<String, Object> getPublicInfoById(String public_Id) {
		 
		 	List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		 	Connection conn = null;
		 	PreparedStatement pre = null;
	        ResultSet rs = null;
	        try {
	            Class.forName("oracle.jdbc.driver.OracleDriver");
	            String jdbcUrl = PropertiesUtil.getProperty("dbconfig.properties", "jdbc.url");
	            String jdbcUsername = PropertiesUtil.getProperty("dbconfig.properties", "jdbc.username");
	            String jdbcPassword = PropertiesUtil.getProperty("dbconfig.properties", "jdbc.password");
	            conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
	            
	            String sql = "SELECT * FROM \"PUBLIC_INFO\" where PUBLIC_INFO.\"id\" ='"+public_Id+"' ";
	            pre = conn.prepareStatement(sql);
        		rs = pre.executeQuery();
        		
	        	ResultSetMetaData data = (ResultSetMetaData) rs.getMetaData();
	        	int count = data.getColumnCount();
	            while(rs.next()){
	            	Map<String, Object> temp = new HashMap<String, Object>();
					for (int i = 1; i <= count; i++) {
						// 获取指定列的表目录名称
						String label = data.getColumnLabel(i);
						// 以 Java 编程语言中 Object 的形式获取此 ResultSet 对象的当前行中指定列的值
						Object object = rs.getObject(i);
						// 把数据库中的字段名和值对应为一个map对象中的一个键值对
						temp.put(label, object);

					}
					result.add(temp);
	            }
	        }catch(Exception e){
	        	e.printStackTrace();
	        }finally {
	            try {
	                if (null != rs) {
	                	rs.close();
	                }
	                if (null != pre) {
	                	pre.close();
	                }
	                if (null != conn) {
	                    conn.close();
	                }
	            } catch (SQLException e) {
	                logger.info(e.getMessage());
	            }
	        }
	      
        	if(null == result || result.size() != 1){
                return null;
            }
            return result.get(0);
	    }
	 
	 public static Map<String, Object> getPublicInfoByDeptID(Integer deptID) {
		 
		 	List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		 	Connection conn = null;
		 	PreparedStatement pre = null;
	        ResultSet rs = null;
	        try {
	            Class.forName("oracle.jdbc.driver.OracleDriver");
	            String jdbcUrl = PropertiesUtil.getProperty("dbconfig.properties", "jdbc.url");
	            String jdbcUsername = PropertiesUtil.getProperty("dbconfig.properties", "jdbc.username");
	            String jdbcPassword = PropertiesUtil.getProperty("dbconfig.properties", "jdbc.password");
	            conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
	            String sql = "SELECT * FROM \"PUBLIC_INFO\" where PUBLIC_INFO.\"dept_ID\" ='"+deptID+"' ";
	            pre = conn.prepareStatement(sql);
        		rs = pre.executeQuery();
        		
	        	ResultSetMetaData data = (ResultSetMetaData) rs.getMetaData();
	        	int count = data.getColumnCount();
	            while(rs.next()){
	            	Map<String, Object> temp = new HashMap<String, Object>();
					for (int i = 1; i <= count; i++) {
						// 获取指定列的表目录名称
						String label = data.getColumnLabel(i);
						// 以 Java 编程语言中 Object 的形式获取此 ResultSet 对象的当前行中指定列的值
						Object object = rs.getObject(i);
						// 把数据库中的字段名和值对应为一个map对象中的一个键值对
						temp.put(label, object);

					}
					result.add(temp);
	            }
	        }catch(Exception e){
	        	e.printStackTrace();
	        }finally {
	            try {
	                if (null != rs) {
	                	rs.close();
	                }
	                if (null != pre) {
	                	pre.close();
	                }
	                if (null != conn) {
	                    conn.close();
	                }
	            } catch (SQLException e) {
	                logger.info(e.getMessage());
	            }
	        }
	      
     	if(null == result || result.size() != 1){
             return null;
         }
         return result.get(0);
	    }
    /**
     * @description
     *
     * @param publicID 公众号ID
     * @auther zkl
     * @create 20:07 2017/9/20
     * @retuan 返回该公众号的 AccessToken;
     *
     **/
    public static String getAccessToken(String publicID) {
        Long currentTime = System.currentTimeMillis() / 1000;
        
        AccessTokenEntity accessTokenEntity = WeiXinConst.accessTokenMap.get(publicID);

        Map<String, Object> publicInfo = getPublicInfoById(publicID);
        if(publicInfo == null){
        	System.out.println("===============================================================");
        	System.out.println("数据库没有对应的公众号");
        }
        
        return  accessToken(accessTokenEntity,publicInfo);
    }
    /**
     * @description
     *
     * @param deptID 部门号
     * @auther zkl
     * @create 17:47 2017/9/20
     * @retuan 返回该公众号的 AccessToken;
     *
     **/
    public static String getAccessToken(Integer deptID){

        Map<String,Object> publicInfo = getPublicInfoByDeptID(deptID);

        AccessTokenEntity accessTokenEntity = WeiXinConst.accessTokenMap.get(publicInfo.get("id").toString());

        return  accessToken(accessTokenEntity,publicInfo);

    }

    /**
     * @description
     *
     * @param accessTokenEntity 实体类
     * @param publicInfo 公众号的基本信息， app,appSecret,id等
     * @auther zkl
     * @create 20:08 2017/9/20
     * @retuan 返回该公众号的 AccessToken;
     *
     **/
    public static String accessToken(AccessTokenEntity accessTokenEn,Map<String,Object> publicInfo){

        Long currentTime = System.currentTimeMillis() / 1000;
        AccessTokenEntity accessTokenEntity = accessTokenEn;

        WeiXinConst.publicInfos.put(Integer.valueOf(publicInfo.get("dept_ID").toString()),publicInfo);

        if ((null != accessTokenEntity)
                && (null != accessTokenEntity.getExpireIn())
                && (null != accessTokenEntity.getObtainTime())
                && (currentTime - accessTokenEntity.getExpireIn()) < (accessTokenEntity.getObtainTime())) {
            return accessTokenEntity.getAccessToken();
        } else {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            String host = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
                    + publicInfo.get("appId") + "&secret=" + publicInfo.get("appSecret");
            System.out.println(host);
            HttpGet httpGet = new HttpGet(host);
            CloseableHttpResponse response = null;
            String accessToken = null;
            try {
                response = httpclient.execute(httpGet);
                String source = EntityUtils.toString(response.getEntity());
                accessToken = FastJsonUtil.getValue(source, "access_token");
                String expiresIn = FastJsonUtil.getValue(source, "expires_in");
                if(null == accessTokenEntity){
                    accessTokenEntity = new AccessTokenEntity();
                }
                accessTokenEntity.setAccessToken(accessToken);
                accessTokenEntity.setObtainTime(System.currentTimeMillis() / 1000);
                accessTokenEntity.setExpireIn(Long.valueOf(expiresIn));
                WeiXinConst.accessTokenMap.put(publicInfo.get("id").toString(),accessTokenEntity);
            } catch (IOException e) {
                e.printStackTrace();
                logger.info("获取accessToken发生了异常:" + e.getMessage());
            }
            return accessToken;
        }
    }

    /**
     * @description
     *
     * @param publicID  公众号ID
     * @auther zkl
     * @create 20:47 2017/9/20
     * @return 调用微信JS接口的临时票据
     *
     **/

    public static String getJsApiTicket(String publicID){
        Map<String,Object> publicInfo =getPublicInfoById(publicID);
        JsapiTicketEntity jsapiTicketEntity = WeiXinConst.jsapiTicketMap.get(publicInfo.get("id").toString());

        return  jsApiTicket(jsapiTicketEntity,publicInfo);
    }

    /**
     * @description
     *
     * @param deptID 部门号
     * @auther zkl
     * @create 20:42 2017/9/20
     * @return 调用微信JS接口的临时票据
     *
     **/
    public static String getJsApiTicket(Integer deptID) {


        Map<String,Object> publicInfo = getPublicInfoByDeptID(deptID);
        JsapiTicketEntity jsapiTicketEntity = WeiXinConst.jsapiTicketMap.get(publicInfo.get("id").toString());

        return jsApiTicket(jsapiTicketEntity,publicInfo);
    }


    /**
     *
     *
     * @param jsapiTicket 实体类
     * @param publicInfo  公众号的基本信息， app,appSecret,id等
     * @param access_token 接口访问凭证
     * @return 调用微信JS接口的临时票据
     */
    public static String jsApiTicket(JsapiTicketEntity jsapiTicket, Map<String,Object> publicInfo) {
        Long currentTime = System.currentTimeMillis() / 1000;
        JsapiTicketEntity jsapiTicketEntity = jsapiTicket;
        if ((null != jsapiTicketEntity)
                && (null != jsapiTicketEntity.getExpireIn())
                && (null != jsapiTicketEntity.getObtainTime())
                && (currentTime - jsapiTicketEntity.getExpireIn()) < (jsapiTicketEntity.getObtainTime())) {

            return jsapiTicketEntity.getJsapiTicket();
        } else {
            String accessToken=WeiXinOperUtil.getAccessToken(publicInfo.get("id").toString());
            CloseableHttpClient httpclient = HttpClients.createDefault();
            String host = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+accessToken+"&type=jsapi";
            HttpGet httpGet = new HttpGet(host);
            CloseableHttpResponse response = null;
            String ticket = null;
            try {
                response = httpclient.execute(httpGet);

                String source = EntityUtils.toString(response.getEntity());

                ticket= FastJsonUtil.getValue(source, "ticket");

                String expiresIn = FastJsonUtil.getValue(source, "expires_in");
                if(null == jsapiTicketEntity){
                    jsapiTicketEntity = new JsapiTicketEntity();
                }
                jsapiTicketEntity.setJsapiTicket(ticket);
                jsapiTicketEntity.setObtainTime(System.currentTimeMillis() / 1000);
                jsapiTicketEntity.setExpireIn(Long.valueOf(expiresIn));
                WeiXinConst.jsapiTicketMap.put(publicInfo.get("id").toString(),jsapiTicketEntity);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ticket;
        }
    }

    /**
     * 获取AccessToken
     *
     * @return
     */
    public static String getAccessToken() {
        Long currentTime = System.currentTimeMillis() / 1000;
        if ((null != WeiXinConst.accessToken)
                && (null != WeiXinConst.accessToken.getExpireIn())
                && (null != WeiXinConst.accessToken.getObtainTime())
                && (currentTime - WeiXinConst.accessToken.getExpireIn()) < (WeiXinConst.accessToken.getObtainTime())) {
            return WeiXinConst.accessToken.getAccessToken();
        } else {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            String host = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
                    + WeiXinConst.appId + "&secret=" + WeiXinConst.appSecret;
            HttpGet httpGet = new HttpGet(host);
            CloseableHttpResponse response = null;
            String accessToken = null;
            try {
                response = httpclient.execute(httpGet);
                String source = EntityUtils.toString(response.getEntity());
                accessToken = FastJsonUtil.getValue(source, "access_token");
                String expiresIn = FastJsonUtil.getValue(source, "expires_in");
                WeiXinConst.accessToken.setAccessToken(accessToken);
                WeiXinConst.accessToken.setObtainTime(System.currentTimeMillis() / 1000);
                WeiXinConst.accessToken.setExpireIn(Long.valueOf(expiresIn));
                WeiXinConst.accessTokenMap.put("gh_6119413afa1b",WeiXinConst.accessToken);
            } catch (IOException e) {
            	e.printStackTrace();
                logger.info("获取accessToken发生了异常:" + e.getMessage());
            }
            return accessToken;
        }
    }

    /**
     * 调用微信JS接口的临时票据
     * 
     * @param access_token 接口访问凭证
     * @return
     */
    public static String getJsApiTicket() {
        Long currentTime = System.currentTimeMillis() / 1000;
        if ((null != WeiXinConst.jsapiTicket)
                && (null != WeiXinConst.jsapiTicket.getExpireIn())
                && (null != WeiXinConst.jsapiTicket.getObtainTime())
                && (currentTime - WeiXinConst.jsapiTicket.getExpireIn()) < (WeiXinConst.jsapiTicket.getObtainTime())) {
        } else {
        	String accessToken=WeiXinOperUtil.getAccessToken(); // 获取静态的accessToken
            CloseableHttpClient httpclient = HttpClients.createDefault();
            String host = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+accessToken+"&type=jsapi";
            HttpGet httpGet = new HttpGet(host);
            CloseableHttpResponse response = null;
            try {
                response = httpclient.execute(httpGet);
                String source = EntityUtils.toString(response.getEntity());
                String ticket= FastJsonUtil.getValue(source, "ticket");
                String expiresIn = FastJsonUtil.getValue(source, "expires_in");
                WeiXinConst.jsapiTicket.setJsapiTicket(ticket);
                WeiXinConst.jsapiTicket.setObtainTime(System.currentTimeMillis() / 1000);
                WeiXinConst.jsapiTicket.setExpireIn(Long.valueOf(expiresIn));
                WeiXinConst.jsapiTicketMap.put("gh_6119413afa1b",WeiXinConst.jsapiTicket);
            } catch (IOException e) {
            	e.printStackTrace();
            }
        }
        return WeiXinConst.jsapiTicket.getJsapiTicket();
    }
    /**
     * 删除微信菜单
     *
     * @param accessToken
     * @return
     */
    public static Boolean deleteWxMenu(String accessToken) {
        if (StringUtil.isEmpty(accessToken)) {
            logger.info(String.format("方法名：%s,参数：%s-%s", "deleteWxMenu", "accessToken", accessToken));
            return false;
        } else {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            String host = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=" + accessToken;
            HttpGet httpGet = new HttpGet(host);
            try {
                CloseableHttpResponse response = httpclient.execute(httpGet);
                logger.info("删除菜单的返回值：" + EntityUtils.toString(response.getEntity(), "UTF-8"));
                return true;
            } catch (Exception e) {
                logger.info(String.format("方法名：%s,参数：%s-%s,发生了异常:%s", "deleteWxMenu", "accessToken", accessToken, e.getMessage()));
                return false;
            }
        }
    }

    /**
     * 创建菜单
     *
     * @param jsonMenuString
     * @return
     */
    public static Boolean createWxMenu(String jsonMenuString, String accessToken) {
        if (StringUtil.isEmpty(jsonMenuString) || StringUtil.isEmpty(accessToken)) {
            logger.info(String.format("方法名：%s,参数1：%s-%s,参数2：%s-%s", "createWxMenu",
                    "jsonMenuString", jsonMenuString, "accessToken", accessToken));
            return false;
        }
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String host = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + accessToken;
        HttpPost post = new HttpPost(host);
        try {
            StringEntity stringEntity = new StringEntity(jsonMenuString, "UTF-8");
            post.setEntity(stringEntity);
            CloseableHttpResponse response = httpclient.execute(post);
            String returnString = EntityUtils.toString(response.getEntity(), "UTF-8");
            logger.info(String.format("创建菜单返回的字符串：%s", returnString));
            if (!"0".equals(FastJsonUtil.getValue(returnString, "errcode"))) {
                return false;
            }
            return true;
        } catch (Exception e) {
            logger.info(String.format("发生了异常：%s", e.getMessage()));
            return false;
        }
    }

    /**
     * 查询自定义菜单
     *
     * @param accessToken
     * @return
     */
    public static String getMenu(String accessToken) {
        if (StringUtil.isEmpty(accessToken)) {
            logger.info(String.format("方法名：%s,参数1：%s-%s", "getMenu", "accessToken", accessToken));
            return null;
        }
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String host = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=" + accessToken;
        HttpGet httpGet = new HttpGet(host);
        try {
            CloseableHttpResponse response = httpclient.execute(httpGet);
            String returnString = EntityUtils.toString(response.getEntity(), "UTF-8");
            logger.info("查询菜单的返回值：" + returnString);
            return returnString;
        } catch (Exception e) {
            logger.info(String.format("方法名：%s,参数：%s-%s,发生了异常:%s", "getMenu", "accessToken", accessToken, e.getMessage()));
            return null;
        }
    }
    /**
     * 根据openId返回用户的基本信息
     *
     * @param accessToken
     * @param openId
     * @return
     */
    public static String getUserInfo(String accessToken, String openId) {
        if (StringUtil.isEmpty(openId) || StringUtil.isEmpty(accessToken))
            return null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String host = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + accessToken + "&openid=" + openId + "&lang=zh_CN";
        System.out.println(host);
        HttpGet httpGet = new HttpGet(host);
        try {
            CloseableHttpResponse response = httpclient.execute(httpGet);
            String returnString = EntityUtils.toString(response.getEntity(), "UTF-8");
            logger.info("根据openId查询用户的基本信息的返回值：" + returnString);
            return returnString;
        } catch (Exception e) {
            logger.info(String.format("方法名：%s,参数：%s-%s,%s-%s,发生了异常:%s", "getUserInfo", "accessToken", accessToken, "openId", openId, e.getMessage()));
            return null;
        }
    }

    /**
     * 批量获取用户基本信息
     *
     * @param accessToken
     * @param jsonUsers
     * @return
     */
    public static String batchGetUserInfo(String accessToken, String jsonUsers) {
        if (StringUtil.isEmpty(accessToken) || StringUtil.isEmpty(jsonUsers))
            return null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String host = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=" + accessToken;
        HttpPost httpPost = new HttpPost(host);
        try {
            StringEntity entity = new StringEntity(jsonUsers, "UTF-8");
            System.out.println(jsonUsers);
            httpPost.setEntity(entity);
            CloseableHttpResponse response = httpclient.execute(httpPost);
            String returnString = EntityUtils.toString(response.getEntity(), "UTF-8");
            logger.info("指量查询用户的基本信息的返回值：" + returnString);
            return returnString;
        } catch (Exception e) {
            logger.info(String.format("方法名：%s,参数：%s-%s,%s-%s,发生了异常:%s", "batchGetUserInfo", "accessToken", accessToken, "jsonUsers", jsonUsers, e.getMessage()));
            return null;
        }
    }


    /**
     * 通过座席接口发信息给微信用户
     *
     * @param accessToken
     * @param jsonContent
     * @return
     */
    public static String sendMsgToWx(String accessToken, String jsonContent) {
        if (StringUtil.isEmpty(accessToken) || StringUtil.isEmpty(jsonContent))
            return null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String host = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + accessToken;
        try {
        	HttpPost httpPost = new HttpPost(host);
            StringEntity entity = new StringEntity(jsonContent, "UTF-8");
            httpPost.setEntity(entity);
            CloseableHttpResponse response = httpclient.execute(httpPost);
            String returnString = EntityUtils.toString(response.getEntity(), "UTF-8");
            logger.info("通过座席接口发信息给用户的返回值 ：" + returnString);
            return returnString;
        } catch (Exception e) {
            logger.info(String.format("方法名：%s,参数：%s-%s,%s-%s,发生了异常:%s", "sendMsgToWx", "accessToken", accessToken, "jsonContent", jsonContent, e.getMessage()));
            return null;
        }
    }

    /**
     * 根据微信openId返回用户昵称
     *
     * @param openId
     * @return
     */
    public static String getWxNickname(String openId) {
        if (StringUtil.isEmpty(openId)) {
            logger.info(String.format("方法名：%s,参数：%s-%s,发生了异常", "getWxNickname", "openId", openId));
            return null;
        }
        String tmp = getUserInfo(WeiXinOperUtil.getAccessToken(), openId);// 这里的方法没有用到
        if (!StringUtil.isEmpty(tmp)) {
            JSONObject jsonObject = JSON.parseObject(tmp);
            return (String) jsonObject.get("nickname");
        } else {
            return null;
        }
    }

    /**
     * @description
     * 从腾讯服务器上下载图片到本地
     *
     *
     * @auther zkl
     * @create 11:21 2017/9/23
     *
     **/
    public static String downloadImageFromWx(Map<String,String> map, HttpServletRequest request) throws IOException {
        String mediaId = map.get("MediaId").toString();

        String url = "http://api.weixin.qq.com/cgi-bin/media/get?access_token=" + WeiXinOperUtil.getAccessToken(map.get("ToUserName").toString()) + "&media_id=" + mediaId; // 从腾讯服务器上下载图片到本地
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        response = httpclient.execute(httpGet);
        String contentType = response.getEntity().getContentType().toString().trim();
        if (!StringUtil.isEmpty(contentType)) {
            contentType = contentType.split(":")[1].trim();
            if ("image/jpeg".equals(contentType)) {
                HttpEntity entity = response.getEntity();
                String path = request.getRealPath("/") + File.separator + "upload";
//                String path = "/workspace/idea/weixin/src/main/webapp"+File.separator + "upload";
                File file = new File(path);
                if (!file.exists()) {
                    file.mkdir();
                }
                String imagePath = File.separator + mediaId + ".jpg";
                file = new File(path + imagePath);
                FileOutputStream fileout = new FileOutputStream(file);
                entity.writeTo(fileout);
                fileout.close();
                return "/upload" + imagePath;
            } else if ("audio/amr".equals(contentType)) {
                HttpEntity entity = response.getEntity();
//                String path = request.getRealPath("/") + File.separator + "upload"; // 四个斜杠,找不到路径了
                String path = request.getRealPath("/") + "upload"; 
//                String path = "/workspace/idea/weixin/src/main/webapp"+File.separator + "upload";
                File file = new File(path);
                if (!file.exists()) {
                    file.mkdir();
                }
                String imagePath = File.separator + mediaId + ".amr";
                file = new File(path + imagePath);
                FileOutputStream fileout = new FileOutputStream(file);
                entity.writeTo(fileout);
                fileout.close();

                //转换文件格式
//                String cmd =String.format("ffmpeg -i %s %s",path +File.separator + imagePath, path+File.separator+mediaId+".mp3");
                String cmd =String.format("ffmpeg -i %s %s",path + imagePath, path+mediaId+".mp3");
                
                Process process = Runtime.getRuntime().exec("cmd /c start"+cmd+" exit");

                return "/upload/" + mediaId + ".mp3";
            } else if ("video/mpeg4".equals(contentType)) {
                HttpEntity entity = response.getEntity();
                String path = request.getRealPath("/") + File.separator + "upload";
//                String path = "/workspace/idea/weixin/src/main/webapp"+File.separator + "upload";
                File file = new File(path);
                if (!file.exists()) {
                    file.mkdir();
                }
                String filePath = File.separator + mediaId + ".mp4";
                file = new File(path + filePath);
                FileOutputStream fileout = new FileOutputStream(file);
                entity.writeTo(fileout);
                fileout.close();
//                //转换文件格式
//                String cmd =String.format("ffmpeg -i %s %s",path +File.separator + imagePath, path+File.separator+mediaId+".mp3");
//                System.out.println("cmd is : " + cmd);
//                Process process = Runtime.getRuntime().exec(cmd);
                return "/upload/" + mediaId + ".mp4";
            }else {
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity);
            }
        }
        return null;
    }

    /**
     * 从腾讯服务器上下载图片到本地
     *
     * @param mediaId
     * @param request
     *
     * @throws IOException
     */
    public static String downloadImageFromWx(String mediaId, HttpServletRequest request) throws IOException {
        String url = "http://api.weixin.qq.com/cgi-bin/media/get?access_token=" + WeiXinOperUtil.getAccessToken() + "&media_id=" + mediaId; // 从腾讯服务器上下载图片到本地
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        response = httpclient.execute(httpGet);
        String contentType = response.getEntity().getContentType().toString().trim();
        if (!StringUtil.isEmpty(contentType)) {
            contentType = contentType.split(":")[1].trim();
            if ("image/jpeg".equals(contentType)) {
                HttpEntity entity = response.getEntity();
                String path = request.getRealPath("/") + File.separator + "upload";
//                String path = "/workspace/idea/weixin/src/main/webapp"+File.separator + "upload";
                File file = new File(path);
                if (!file.exists()) {
                    file.mkdir();
                }
                String imagePath = File.separator + mediaId + ".jpg";
                file = new File(path + imagePath);
                FileOutputStream fileout = new FileOutputStream(file);
                entity.writeTo(fileout);
                fileout.close();
                return "/upload" + imagePath;
            } else if ("audio/amr".equals(contentType)) {
                HttpEntity entity = response.getEntity();
                String path = request.getRealPath("/") + File.separator + "upload";
//                String path = "/workspace/idea/weixin/src/main/webapp"+File.separator + "upload";
                File file = new File(path);
                if (!file.exists()) {
                    file.mkdir();
                }
                String imagePath = File.separator + mediaId + ".amr";
                file = new File(path + imagePath);
                FileOutputStream fileout = new FileOutputStream(file);
                entity.writeTo(fileout);
                fileout.close();

                //转换文件格式
                String cmd =String.format("ffmpeg -i %s %s",path +File.separator + imagePath, path+File.separator+mediaId+".mp3");
                Process process = Runtime.getRuntime().exec(cmd);

                return "/upload/" + mediaId + ".mp3";
            } else if ("video/mpeg4".equals(contentType)) {
                HttpEntity entity = response.getEntity();
                String path = request.getRealPath("/") + File.separator + "upload";
//                String path = "/workspace/idea/weixin/src/main/webapp"+File.separator + "upload";
                File file = new File(path);
                if (!file.exists()) {
                    file.mkdir();
                }
                String filePath = File.separator + mediaId + ".mp4";
                file = new File(path + filePath);
                FileOutputStream fileout = new FileOutputStream(file);
                entity.writeTo(fileout);
                fileout.close();
//                //转换文件格式
//                String cmd =String.format("ffmpeg -i %s %s",path +File.separator + imagePath, path+File.separator+mediaId+".mp3");
//                System.out.println("cmd is : " + cmd);
//                Process process = Runtime.getRuntime().exec(cmd);
                return "/upload/" + mediaId + ".mp4";
            }else {
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity);
            }
        }
        return null;
    }

    /**
     * 上传临时素材
     * @param file 需要上传的文件
     * @throws IOException
     */
    public static String uploadMedia(File file, String mediaType) throws IOException {
        String url ="https://api.weixin.qq.com/cgi-bin/media/upload?access_token=" + WeiXinOperUtil.getAccessToken() + "&type=" + mediaType;// 传临时素材
        String DEFAULT_USER_AGENT = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.146 Safari/537.36";
        String DEFAULT_CHARSET = "UTF-8";
        URL urlGet = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) urlGet.openConnection();
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setUseCaches(false);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("connection", "Keep-Alive");
        conn.setRequestProperty("user-agent", DEFAULT_USER_AGENT);
        conn.setRequestProperty("Charsert", "UTF-8");
        // 定义数据分隔线
        String BOUNDARY = "----WebKitFormBoundaryiDGnV9zdZA1eM1yL";
        conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

        OutputStream out = new DataOutputStream(conn.getOutputStream());
        // 定义最后数据分隔线
        StringBuilder mediaData = new StringBuilder();
        mediaData.append("--").append(BOUNDARY).append("\r\n");
        mediaData.append("Content-Disposition: form-data;name=\"media\";filename=\""+ file.getName() + "\"\r\n");
        mediaData.append("Content-Type:application/octet-stream\r\n\r\n");
        byte[] mediaDatas = mediaData.toString().getBytes();
        out.write(mediaDatas);
        DataInputStream fs = new DataInputStream(new FileInputStream(file));
        int bytes = 0;
        byte[] bufferOut = new byte[1024];
        while ((bytes = fs.read(bufferOut)) != -1) {
            out.write(bufferOut, 0, bytes);
        }
        closeQuietly(fs);
        // 多个文件时，二个文件之间加入这个
        out.write("\r\n".getBytes());
        byte[] end_data = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
        out.write(end_data);
        out.flush();
        closeQuietly(out);

        // 定义BufferedReader输入流来读取URL的响应
        InputStream in = conn.getInputStream();
        BufferedReader read = new BufferedReader(new InputStreamReader(in, DEFAULT_CHARSET));
        String valueString = null;
        StringBuffer bufferRes = null;
        bufferRes = new StringBuffer();
        while ((valueString = read.readLine()) != null){
            bufferRes.append(valueString);
        }
        closeQuietly(in);
        // 关闭连接
        if (conn != null) {
            conn.disconnect();
        }
        return bufferRes.toString();
    }


    /**
     * closeQuietly
     * @param closeable
     */
    public static void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException ioe) {
            // ignore
        }
    }
    
    /**
     * 发送客服图文消息
     * @param openId
     * @param title
     * @param desc
     * @param url
     * @param imgUrl
     */
    public static String sendWxKefuMsg(String token, String openId, String title, String desc, String url){
    	String urlstr ="https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN"; //发送客服图文消息
//        urlstr =urlstr.replace("ACCESS_TOKEN", "s_w_BIwYN9-5Jn4YUJo7M1NsyXdgfGNV8OmPIyoWdwkK5S39wTfJAzc_AYGaOJGscXLTxHLbcZMfYPSno0VOLFaK5CvnXRSp4naDES_2uFAlh2UweQ2Him6i1gIExMZQREGcCDANAC");
        urlstr =urlstr.replace("ACCESS_TOKEN", token);
        String context = "{\"touser\":\""+openId+"\",\"msgtype\":\"news\",\"news\":{\"articles\": [{\"title\":\""+title+"\",\"description\":\""+desc+"\",\"url\":\""+url+"\"}]}}";
        try {
            URL httpclient =new URL(urlstr);
            HttpURLConnection conn =(HttpURLConnection) httpclient.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(2000);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");    
            conn.setDoOutput(true);        
            conn.setDoInput(true);
            conn.connect();
            OutputStream os= conn.getOutputStream();    
            
            os.write(context.getBytes("UTF-8"));//传入参数    
            os.flush();
            os.close();
            InputStream is =conn.getInputStream();
            int size =is.available();
            byte[] jsonBytes =new byte[size];
            is.read(jsonBytes);
            String message=new String(jsonBytes,"UTF-8");
            System.out.println("testsendTextByOpenids:"+message);
//            logger.error("--->路况订阅："+context+"\n,结果："+message);
         
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
    	return context;
    }
    /**
     * 发送客服图片消息
     * @param openId
     * @param title
     * @param desc
     * @param url
     * @param imgUrl
     */
    public static String sendWxKefuImgMsg(String token, String openId, String title, String desc, String url){
    	String urlstr ="https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN"; //发送客服图文消息
//        urlstr =urlstr.replace("ACCESS_TOKEN", "s_w_BIwYN9-5Jn4YUJo7M1NsyXdgfGNV8OmPIyoWdwkK5S39wTfJAzc_AYGaOJGscXLTxHLbcZMfYPSno0VOLFaK5CvnXRSp4naDES_2uFAlh2UweQ2Him6i1gIExMZQREGcCDANAC");
    	urlstr =urlstr.replace("ACCESS_TOKEN", token);
    	String context = "{\"touser\":\""+openId+"\",\"msgtype\":\"image\",\"image\":{\"media_id\": \"KzjbShpbrf-VR_xLSQ2i77zk8Nw7s7865_yw64dN5Mp7bH6vV8eGejKlEJon2CeZ\",\"title\":\"test\"}}";
    	try {
    		URL httpclient =new URL(urlstr);
    		HttpURLConnection conn =(HttpURLConnection) httpclient.openConnection();
    		conn.setConnectTimeout(5000);
    		conn.setReadTimeout(2000);
    		conn.setRequestMethod("POST");
    		conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");    
    		conn.setDoOutput(true);        
    		conn.setDoInput(true);
    		conn.connect();
    		OutputStream os= conn.getOutputStream();    
    		
    		os.write(context.getBytes("UTF-8"));//传入参数    
    		os.flush();
    		os.close();
    		InputStream is =conn.getInputStream();
    		int size =is.available();
    		byte[] jsonBytes =new byte[size];
    		is.read(jsonBytes);
    		String message=new String(jsonBytes,"UTF-8");
    		System.out.println("testsendTextByOpenids:"+message);
//    		logger.error("--->路况订阅："+context+"\n,结果："+message);
    		
    	} catch (MalformedURLException e) {
    		e.printStackTrace();
    	} catch (IOException e) {
    		e.printStackTrace();
    	} 
    	return context;
    }
    
    /**
     * 发送客服图片消息
     * @param openId
     * @param title
     * @param desc
     * @param url
     * @param imgUrl
     */
    public static String sendWxKefuImgsMsg(String token, String openId, String title, String desc, String url,String picUrl){
    	String urlstr ="https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN"; //发送客服图文消息
//        urlstr =urlstr.replace("ACCESS_TOKEN", "s_w_BIwYN9-5Jn4YUJo7M1NsyXdgfGNV8OmPIyoWdwkK5S39wTfJAzc_AYGaOJGscXLTxHLbcZMfYPSno0VOLFaK5CvnXRSp4naDES_2uFAlh2UweQ2Him6i1gIExMZQREGcCDANAC");
    	urlstr =urlstr.replace("ACCESS_TOKEN", token);
    	String context = "{\"touser\":\""+openId+"\",\"msgtype\":\"news\",\"news\":{\"articles\": [{\"title\":\""+title+"\",\"description\":\""+desc+"\",\"url\":\""+url+"\",\"picurl\":\""+picUrl+"\"}]}}";
    	try {
    		URL httpclient =new URL(urlstr);
    		HttpURLConnection conn =(HttpURLConnection) httpclient.openConnection();
    		conn.setConnectTimeout(5000);
    		conn.setReadTimeout(2000);
    		conn.setRequestMethod("POST");
    		conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");    
    		conn.setDoOutput(true);        
    		conn.setDoInput(true);
    		conn.connect();
    		OutputStream os= conn.getOutputStream();    
    		
    		os.write(context.getBytes("UTF-8"));//传入参数    
    		os.flush();
    		os.close();
    		InputStream is =conn.getInputStream();
    		int size =is.available();
    		byte[] jsonBytes =new byte[size];
    		is.read(jsonBytes);
    		String message=new String(jsonBytes,"UTF-8");
    		System.out.println("testsendTextByOpenids:"+message);
//    		logger.error("--->路况订阅："+context+"\n,结果："+message);
    		
    	} catch (MalformedURLException e) {
    		e.printStackTrace();
    	} catch (IOException e) {
    		e.printStackTrace();
    	} 
    	return context;
    }
    /**
     * 发送客服图片消息
     * @param openId
     * @param title
     * @param desc
     * @param url
     * @param imgUrl
     */
    public static String sendWxKefuImgsMsg(String token, String context){
    	String urlstr ="https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN"; //发送客服图文消息
//        urlstr =urlstr.replace("ACCESS_TOKEN", "s_w_BIwYN9-5Jn4YUJo7M1NsyXdgfGNV8OmPIyoWdwkK5S39wTfJAzc_AYGaOJGscXLTxHLbcZMfYPSno0VOLFaK5CvnXRSp4naDES_2uFAlh2UweQ2Him6i1gIExMZQREGcCDANAC");
    	System.out.println("---->urlstr:"+urlstr);
    	System.out.println("---->token:"+token);
    	urlstr =urlstr.replace("ACCESS_TOKEN", token);
    	try {
    		URL httpclient =new URL(urlstr);
    		HttpURLConnection conn =(HttpURLConnection) httpclient.openConnection();
    		conn.setConnectTimeout(5000);
    		conn.setReadTimeout(2000);
    		conn.setRequestMethod("POST");
    		conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");    
    		conn.setDoOutput(true);        
    		conn.setDoInput(true);
    		conn.connect();
    		OutputStream os= conn.getOutputStream();    
    		System.out.println("ccccc:"+context);
    		
    		os.write(context.getBytes("UTF-8"));//传入参数    
    		os.flush();
    		os.close();
    		InputStream is =conn.getInputStream();
    		int size =is.available();
    		byte[] jsonBytes =new byte[size];
    		is.read(jsonBytes);
    		String message=new String(jsonBytes,"UTF-8");
    		System.out.println("testsendTextByOpenids:"+message);
    		logger.error("--->路况订阅："+context+"\n,结果："+message);
    		
    	} catch (MalformedURLException e) {
    		e.printStackTrace();
    	} catch (IOException e) {
    		e.printStackTrace();
    	} 
    	return context;
    }

    /**
     * @description
     * 微信多公众号 转发请求
     *
     * @auther zkl
     * @create 16:42 2017/9/17
     *
     **/
    public static String wxpost(String url , HttpServletRequest request,HttpServletResponse response) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        InputStream inputStream  = null;
        try {


            inputStream  = request.getInputStream();

            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = inputStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            outSteam.close();
            inputStream.close();
            String param = outSteam.toString();
            System.out.println(param);
            StringEntity stringEntity = new StringEntity(param,"UTF-8");

            post.setEntity(stringEntity);


            CloseableHttpResponse r1 = httpclient.execute(post);

            String result = EntityUtils.toString(r1.getEntity(), "UTF-8");

            System.err.println(result);
            //
            sendMsgToWX(response,result);
//            String result = EntityUtils.toString(response.getEntity(), "UTF-8");
//            logger.info(String.format("创建菜单返回的字符串：%s", result));
        } catch (Exception e) {
            e.printStackTrace();
//            logger.info(String.format("发生了异常：%s", e.getMessage()));
        }
        return null;
    }
    
    public static void main(String[] args) {
    	File file = new File("E:\\123456.png");
    	String result;
		try {
			result = uploadMedia(file, "image");
//			System.out.println("---->"+result);
			//oHMgqxIFpc9zoGQQbBsrI9KcWJrk 邓科
			//oHMgqxMPkOAH7CzAmPISCkC9oI-4
			
//			String userJsonContent = String.format("{\"touser\":\"%s\",\"msgtype\":\"text\",\"text\":{\"content\":\"%s\"}}",
//                    "oHMgqxIFpc9zoGQQbBsrI9KcWJrk", "路况订阅");
//			//发送給用户
//			WeiXinOperUtil.sendMsgToWx(getAccessToken(), userJsonContent);
////			System.out.println("---->"+result);
//			userJsonContent = String.format("{\"touser\":\"%s\",\"msgtype\":\"image\",\"image\":{\"media_id\":\"%s\"}}",
//                    "oHMgqxIFpc9zoGQQbBsrI9KcWJrk", "KzjbShpbrf-VR_xLSQ2i77zk8Nw7s7865_yw64dN5Mp7bH6vV8eGejKlEJon2CeZ");
			String userJsonContent =String.format("{\"touser\":\"%s\",\"msgtype\":\"image\",\"image\":[{\"media_id\":\"%s\"},{\"media_id\":\"%s\"}]}",
                  "oHMgqxIFpc9zoGQQbBsrI9KcWJrk", "KzjbShpbrf-VR_xLSQ2i77zk8Nw7s7865_yw64dN5Mp7bH6vV8eGejKlEJon2CeZ", "KzjbShpbrf-VR_xLSQ2i77zk8Nw7s7865_yw64dN5Mp7bH6vV8eGejKlEJon2CeZ");
			//发送給用户
			WeiXinOperUtil.sendMsgToWx(getAccessToken(), userJsonContent);// 不需要更改的
//			sendWxKefuImgsMsg(getAccessToken(), "oHMgqxMPkOAH7CzAmPISCkC9oI-4", null, null, "http://www.cq96096.cn/WS/10100101012110000008/15634/1_20160926_172942(00003501).jpg", "http://www.cq96096.cn/WS/10100101012110000008/15634/1_20160926_172942(00003501).jpg");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}