package org.jiaowei.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.impl.cmd.SaveAttachmentCmd;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jiaowei.common.service.impl.CommonServiceImpl;
import org.jiaowei.entity.BbsUserEntity;
import org.jiaowei.service.mineService;
import org.jiaowei.util.StringUtil;
import org.jiaowei.wxutil.NavMenuInitUtils;
import org.jiaowei.wxutil.WeiXinOperUtil;
import org.springframework.stereotype.Service;

import sun.misc.BASE64Encoder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.apache.commons.codec.binary.Base64;
@Service
public class mineServiceImpl extends CommonServiceImpl implements mineService{
	
	@Override
	public List<Object> init(String openID,int begin, int end){
		String sql = "";
		if(openID != null)
			sql = " SELECT * FROM (SELECT " 
				+ " BBS_TELL.ID AS tellID,"
				+ " BBS_USER.WECHATNAME AS name,"
				+ " BBS_USER.HEADIMAGE,"
				+ "	to_char(BBS_TELL.PUBLISHEDTIME,'YYYY-MM-DD HH:MM') as PUBLISHEDTIME,"
				+ " BBS_TELL.COMMENTSNUMBER,"
				+ " BBS_TELL.TITLE,"
				+ " BBS_TELL.CONTENT,"
				+ " ROWNUM AS rn"
				+ " FROM BBS_TELL,BBS_USER"
				+ " WHERE USERID = (SELECT ID FROM BBS_USER "
				+ " WHERE OPPENID = '"+ openID +"'"
				+ " )AND BBS_USER. ID = BBS_TELL.USERID) a"
				+ " LEFT JOIN (SELECT wm_concat (BBS_PICTURE. PATH) AS path,"
				+ " wm_concat (DISTINCT BBS_PICTURE.TELLID) AS picTellID"
				+ " FROM BBS_PICTURE"
				+ " GROUP BY BBS_PICTURE.TELLID) b"
				+ " ON a.tellID = b.picTellID"
				+ " WHERE rn >= "+ begin +" AND rn < " + end
				+ " ORDER BY PUBLISHEDTIME DESC";
		else
			return null;
		System.out.println(sql);
		List<Object> list = findBySQL(sql);
		return list;
	}
	
	//初始化用户的数据
	@Override
	public String initUser(HttpServletRequest request,String openID){
		JSONObject jsonObject = getWxNickname(openID);
		//System.out.println(jsonObject.get("nickname") + jsonObject.getString("headimgurl"));
		if(jsonObject.get("nickname") != null){
			BbsUserEntity bbs = isExistUser(openID);
			if (bbs != null) {
				return updateUser(request,jsonObject,bbs);
			}
			else {
				return SaveUser(request,jsonObject);
			}
		}
		return "error";
	}
	
	//初始化交通狀況的信息
	@Override
	public List<Object> initTransportation(int begin, int end){
		String sql = "";
		sql = " SELECT * FROM (SELECT " 
				+ " BBS_TELL.ID AS tellID,"
				+ " BBS_USER.WECHATNAME AS name,"
				+ " BBS_USER.HEADIMAGE,"
				+ " to_char(BBS_TELL.PUBLISHEDTIME,'YYYY-MM-DD HH:MM') as PUBLISHEDTIME,"
				+ " BBS_TELL.COMMENTSNUMBER,"
				+ " BBS_TELL.TITLE,"
				+ " BBS_TELL.CONTENT,"
				+ " ROWNUM AS rn"
				+ " FROM BBS_TELL,BBS_USER"
				+ " WHERE BBS_TELL.STATE = '0'"
				+ " AND BBS_USER. ID = BBS_TELL.USERID) a"
				+ " LEFT JOIN (SELECT wm_concat (BBS_PICTURE. PATH) AS path,"
				+ " wm_concat (DISTINCT BBS_PICTURE.TELLID) AS picTellID"
				+ " FROM BBS_PICTURE"
				+ " GROUP BY BBS_PICTURE.TELLID) b"
				+ " ON a.tellID = b.picTellID"
				+ " WHERE rn >= "+ begin +" AND rn < " + end
				+ " ORDER BY PUBLISHEDTIME DESC";
		
		System.out.println(sql);
		List<Object> list = findBySQL(sql);
		return list;
	}
	
	@Override 
	//初始化专家界面信息
	public 	List<Object> initSpecialist(int begin, int end){
		String sql = "";
		sql = " SELECT * FROM (SELECT " 
				+ " BBS_TELL.ID AS tellID,"
				+ " BBS_USER.WECHATNAME AS name,"
				+ " BBS_USER.HEADIMAGE,"
				+ " to_char(BBS_TELL.PUBLISHEDTIME,'YYYY-MM-DD HH:MM') as PUBLISHEDTIME,"
				+ " BBS_TELL.COMMENTSNUMBER,"
				+ " BBS_TELL.TITLE,"
				+ " BBS_TELL.CONTENT,"
				+ " ROWNUM AS rn"
				+ " FROM BBS_TELL,BBS_USER"
				+ " WHERE BBS_TELL.STATE = '1'"
				+ " AND BBS_USER. ID = BBS_TELL.USERID) a"
				+ " LEFT JOIN (SELECT wm_concat (BBS_PICTURE. PATH) AS path,"
				+ " wm_concat (DISTINCT BBS_PICTURE.TELLID) AS picTellID"
				+ " FROM BBS_PICTURE"
				+ " GROUP BY BBS_PICTURE.TELLID) b"
				+ " ON a.tellID = b.picTellID"
				+ " WHERE rn >= "+ begin +" AND rn < " + end
				+ " ORDER BY PUBLISHEDTIME DESC";
		System.out.println(sql);
		List<Object> list = findBySQL(sql);
		return list;
	}

	//判断用户是否存在
	public BbsUserEntity isExistUser(String openID){
		String sql = "SELECT * FROM BBS_USER WHERE OPPENID = '" + openID + "'";
		System.out.println(sql);
		List<BbsUserEntity> bu = findBySQL(sql, BbsUserEntity.class);
		if(bu == null || bu.size() == 0){
			return null;
		}
		return (BbsUserEntity)bu.get(0);
	}
	
	//保存用户
	public String SaveUser(HttpServletRequest request,JSONObject jsonObject){
		BbsUserEntity bus = new BbsUserEntity();
		bus.setId(Calendar.getInstance().getTimeInMillis() +"");
		bus.setState("0");
		bus.setOppenid(jsonObject.getString("openid"));
		bus.setWechatName(jsonObject.getString("nickname"));
		String headImage =getImageByUrl(request,jsonObject.getString("headimgurl"),"/uploads/",jsonObject.getString("openid"));
		//System.out.println("===" + headImageData);
		bus.setHeadImage(headImage);
		save(bus);
		return "success";
	}
	
	//更新用户信息
	public String updateUser(HttpServletRequest request,JSONObject jsonObject,BbsUserEntity bus){
		bus.setOppenid(jsonObject.getString("openid"));
		bus.setWechatName(jsonObject.getString("nickname"));
		String headImageData =getImageByUrl(request,jsonObject.getString("headimgurl"),"/uploads/",jsonObject.getString("openid"));
		bus.setHeadImage(headImageData);
		saveOrUpdate(bus);	
		return "success";
	}
	
    /**
     * 根据微信openId返回用户昵称
     *
     * @param openId
     * @return
     */
    public JSONObject getWxNickname(String openId) {
        if (StringUtil.isEmpty(openId)) {
            //logger.info(String.format("方法名：%s,参数：%s-%s,发生了异常", "getWxNickname", "openId", openId));
            return null;
        }
        String publicID = NavMenuInitUtils.getInstance().userPublicIdMap.get(openId);
        String tmp = getUserInfo(WeiXinOperUtil.getAccessToken(publicID), openId);// 这里的方法没有用到
        if (!StringUtil.isEmpty(tmp)) {
            JSONObject jsonObject = JSON.parseObject(tmp);
            return jsonObject;
        } else {
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
    public  String getUserInfo(String accessToken, String openId) {
        if (StringUtil.isEmpty(openId) || StringUtil.isEmpty(accessToken))
            return null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String host = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + accessToken + "&openid=" + openId + "&lang=zh_CN";
        System.out.println(host);
        HttpGet httpGet = new HttpGet(host);
        try {
            CloseableHttpResponse response = httpclient.execute(httpGet);
            String returnString = EntityUtils.toString(response.getEntity(), "UTF-8");
            //logger.info("根据openId查询用户的基本信息的返回值：" + returnString);
            return returnString;
        } catch (Exception e) {
            //logger.info(String.format("方法名：%s,参数：%s-%s,%s-%s,发生了异常:%s", "getUserInfo", "accessToken", accessToken, "openId", openId, e.getMessage()));
            return null;
        }
    }
    
    /** 
     * @Title: GetImageStrFromUrl 
     * @Description: TODO(将一张网络图片转化成Base64字符串) 
     * @param imgURL 网络资源位置 
     * @return Base64字符串 
     */ 
	public String GetImageStrFromUrl(String imgURL) {  
        byte[] data = null;  
        try {  
            // 创建URL  
            URL url = new URL(imgURL);  
            // 创建链接  
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();  
            conn.setRequestMethod("GET");  
            conn.setConnectTimeout(5 * 1000);  
            InputStream inStream = conn.getInputStream();  
            data = new byte[inStream.available()];  
            inStream.read(data);  
            inStream.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        // 对字节数组Base64编码  
        BASE64Encoder encoder = new BASE64Encoder();  
        // 返回Base64编码过的字节数组字符串  
        return encoder.encode(data);  
    }  
    
    /**

    * 图片下载到本地服务器
	*保存base64编码
	*返回图片存储地址
    */

    public String getImageByUrl(HttpServletRequest request,String imageurl, String savepath, String name) {
    try {
    	// 构造URL
	    URL url = new URL(imageurl);
	    // 打开连接
	    URLConnection con = url.openConnection();
	    // 输入流
	    InputStream is = con.getInputStream();
	    // 1K的数据缓冲
	    byte[] bs = new byte[1024];
	    // 读取到的数据长度
	    int len;
	    String imgPath = request.getServletContext().getRealPath(savepath);

	    File filePath = new File(imgPath);
	    if (!filePath.exists()) {
	    		filePath.mkdirs();
	    	}

	    // 输出的文件流
	    OutputStream os = new FileOutputStream(imgPath + name + ".jpg");
	    // 开始读取
	    while ((len = is.read(bs)) != -1) {
	    	os.write(bs, 0, len);
	    }
	    // 完毕，关闭所有链接
	    os.close();
	    is.close();
	    return savepath + name + ".jpg" ;
    } catch (Exception e) {
    	return savepath + "default.jpg";
    }
    }
    /**
     * 将图片转换成Base64编码
     * @param imgFile 待处理图片
     * @return
     */
    public String getImgStr(String imgFile){
        //将图片文件转化为字节数组字符串，并对其进行Base64编码处理  
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try 
        {
            in = new FileInputStream(imgFile);        
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        return new String(Base64.encodeBase64(data));
    }
}
