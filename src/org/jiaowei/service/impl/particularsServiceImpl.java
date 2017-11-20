package org.jiaowei.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jiaowei.common.service.impl.CommonServiceImpl;
import org.jiaowei.entity.BbsCommentariesEntity;
import org.jiaowei.entity.BbsTellEntity;
import org.jiaowei.entity.BbsUserEntity;
import org.jiaowei.service.particularsService;
import org.jiaowei.util.StringUtil;
import org.jiaowei.wxutil.NavMenuInitUtils;
import org.jiaowei.wxutil.WeiXinOperUtil;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Service
public class particularsServiceImpl extends CommonServiceImpl implements particularsService  {
	
	@Override
	
	public Map<String, Object> init(String tellID){
		String tellSql = "SELECT BBS_TELL.CONTENT, "
					+ " to_char(BBS_TELL.PUBLISHEDTIME,'YYYY-MM-DD HH24:MI:SS') AS PUBLISHEDTIME, " 
					+ " BBS_TELL.COMMENTSNUMBER, "
					+ " BBS_USER.WECHATNAME, "
					+ " BBS_USER.HEADIMAGE, "
					+ " BBS_TELL.TITLE "
					+ " FROM BBS_TELL,BBS_USER WHERE BBS_TELL.ID = '"+ tellID +"' AND BBS_TELL.USERID = BBS_USER.ID ";
		String picSell = "SELECT BBS_PICTURE.PATH FROM BBS_PICTURE WHERE BBS_PICTURE.TELLID = '"+ tellID +"'";
		String commentSql = "SELECT to_char(BBS_COMMENTARIES.COMMENTSTIME,'YYYY-MM-DD HH24:MI:SS') AS COMMENTSTIME,BBS_COMMENTARIES.CONTENT,BBS_USER.HEADIMAGE,BBS_USER.WECHATNAME "
					+ "FROM BBS_COMMENTARIES,BBS_USER WHERE BBS_COMMENTARIES.TELLID = '"+ tellID+"' AND BBS_COMMENTARIES.COMMENTSID = BBS_USER.ID";
		System.out.println(tellSql);
		List<Object> tellList = findBySQL(tellSql);
		List<Object> picList = findBySQL(picSell);
		List<Object> commentList = findBySQL(commentSql);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tell", tellList);
		map.put("pic", picList);
		map.put("comment", commentList);

		return map;
	}
	
	@Override 
	public  Map<String, Object> review(HttpServletRequest request,String tellID,String reviewData, String openID){
		BbsCommentariesEntity BCE = new BbsCommentariesEntity();
		Map<String, Object> map = new HashMap<>();
		String commentSID = getReviewID(openID);
		
		if(commentSID == null){
			JSONObject jsonObject = getWxNickname(openID);
			commentSID = SaveUser(request,jsonObject);
		}
		BCE.setCommentsID(commentSID);
		BCE.setTellID(tellID);
		BCE.setContent(reviewData);
		BCE.setId(Calendar.getInstance().getTimeInMillis() +"");
		BCE.setCommentsTime(new Timestamp(System.currentTimeMillis()));
		save(BCE);
		updateTell(tellID);
		map.put("result", "success");
		return map;
	}
	public void updateTell(String tellID){
		String sql = " SELECT * FROM BBS_TELL WHERE BBS_TELL.ID = '"+ tellID +"'";
		List<BbsTellEntity> list = findBySQL(sql, BbsTellEntity.class);
		BbsTellEntity btl = new BbsTellEntity();
		if(list != null)
			if(list.size() > 0){
				btl = list.get(0);
				btl.setCommentsNumber(btl.getCommentsNumber() + 1);
				save(btl);
			}
	}
	
	//保存用户
	public String SaveUser(HttpServletRequest request,JSONObject jsonObject){
		BbsUserEntity bus = new BbsUserEntity();
		String userID = Calendar.getInstance().getTimeInMillis() +"";
		bus.setId(userID);
		bus.setState("0");
		bus.setOppenid(jsonObject.getString("openid"));
		bus.setWechatName(jsonObject.getString("nickname"));
		String headImage =getImageByUrl(request,jsonObject.getString("headimgurl"),"/uploads/",jsonObject.getString("openid"));
		//System.out.println("===" + headImageData);
		bus.setHeadImage(headImage);
		save(bus);
		return userID;
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

    * 图片下载到本地服务器
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
	
	public String getReviewID(String openID){
		String sql = "";
		sql += "SELECT * FROM BBS_USER WHERE BBS_USER.OPPENID = '"+ openID + "'";
		List<BbsUserEntity> list = findBySQL(sql, BbsUserEntity.class);
		
		if(list != null && list.size() > 0){
			return list.get(0).getId();
		}
 		return null;
	}
}
