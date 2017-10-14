/**
 * 
 */
package org.jiaowei.wxutil;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * HttpClient 4.0使用
 * @author mx.li
 * @date 2013-11-4 下午4:12:26
 */
public class ApiHttpUtils {

	private HttpClient client;
	private StringBuffer url = new StringBuffer();
	private String encoding = "UTF-8";
	private List<NameValuePair> params = new ArrayList<NameValuePair>();

	
	 public ApiHttpUtils(String url){
		  this.url.append(url);
		  client = new DefaultHttpClient(); 
		  HttpParams params = client.getParams();
		  params.setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, encoding);
		  params.setParameter(CoreProtocolPNames.HTTP_ELEMENT_CHARSET, encoding);
		  params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
	}

	public String doPost() throws Exception{
		 String result = null;
		 try {
			 HttpPost post = new HttpPost(this.url.toString());
		     post.setEntity(new UrlEncodedFormEntity(params, encoding));
		     result = getRespone(post);
		 } finally {
			 client.getConnectionManager().shutdown();
		 }
		 return result;
	}
	
	public String doPostFile(File file) throws Exception{
		 String result = null;
		 try {
			 HttpPost post = new HttpPost(this.url.toString());
//		     post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			 //文件
			 ContentBody cbFile = new FileBody(file);
			 MultipartEntity mpEntity = new MultipartEntity(); 
//			 InputStream in = new ByteArrayInputStream("abcd".getBytes());
//			 InputStreamBody isBody =  new InputStreamBody(in);
			 mpEntity.addPart("file",cbFile);
		     post.setEntity(mpEntity);
		     result = getRespone(post);
		 } finally {
			 client.getConnectionManager().shutdown();
		 }
		 return result;
	}
	
	public String doPostByte(String json) throws Exception{
		 String result = null;
		 try {
			 HttpPost post = new HttpPost(this.url.toString());
			 MultipartEntity mpEntity = new MultipartEntity(); 
			 InputStream in = new ByteArrayInputStream(json.getBytes("utf-8"));
			 InputStreamBody isBody =  new InputStreamBody(in, "file.txt");
			 ByteArrayBody baBody = new ByteArrayBody(json.getBytes("utf-8"), "test");
			 
			 
			 mpEntity.addPart("file", baBody);
		     post.setEntity(mpEntity);
		     result = getRespone(post);
		 } finally {
			 client.getConnectionManager().shutdown();
		 }
		 return result;
	}
	
	public void setParams(String key, Object value){
		 if(value != null){
			 params.add(new BasicNameValuePair(key, value.toString()));
		 }
	}
		 
	public void setInitUrl(String newUrl){
		 url.append(newUrl);
	}
		 
	public String doGet() throws Exception{
		 String result = "";
		 try {
			 int count = 0;
			 for (NameValuePair nv : params) {
				 if(count == 0){
					 url.append("?");
				 } else {
					 url.append("&");
				 }
				 String key = nv.getName();
				 String value = nv.getValue();
				 url.append(key);
				 url.append("=");
				 url.append(value);
				 count++;
			 }
			 HttpGet get = new HttpGet(this.url.toString());
			 result = getRespone(get);
		 } finally {
			 client.getConnectionManager().shutdown();
		 }
		 return result;
	}
	
	private String getRespone(HttpUriRequest req) throws Exception{
		String result = "";
		// 查看默认request头部信息
		// 以下这条如果不加会发现无论你设置Accept-Charset为gbk还是utf-8，他都会默认返回gb2312（本例针对google.cn来说）
		req.setHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.2)");
		// 用逗号分隔显示可以同时接受多种编码
		req.setHeader("Accept-Language", "zh-cn,zh;q=0.5");
		req.setHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");
		// 验证头部信息设置生效
		HttpResponse response = client.execute(req);
		HttpEntity entity = response.getEntity();
	 	if (entity != null) {
	 		try {
	 			result = EntityUtils.toString(entity, encoding);
	            EntityUtils.consume(entity);
	 		} catch (RuntimeException ex) {
	            throw ex;
	        } catch (Exception ex) {
	            throw ex;
	        } 
	 	}
	    return result;
	}
		 
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	
	
	/**
	 * @author mx.li
	 * @date 2013-11-4 下午4:12:27
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		ApiHttpUtils client = new ApiHttpUtils(CommonConstant.getCmsUrl());
//		String pwd = MD5.MD5Encode(CommonConstant.getCmsUser()+ CommonConstant.getCmsPassword());
//		Date date = ApiUtils.getNowDateTime();
//		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHH");
//		String dateStr = format.format(date);
//		System.out.println("dateStr:"+dateStr);
//		String token  = MD5.MD5Encode(CommonConstant.getCmsT()+":"+pwd+ ":"+dateStr);
//		System.out.println("token:"+token);
//		client.setParams("token", token);
//		client.setParams("mobile", "13628478730");
//		client.setParams("content", "text");
//		client.setParams("date", ApiUtils.getNowDateTime());
//		try {
//			String result = client.doPost();
//			System.out.println("-------->result:"+result);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		ApiHttpUtils client = new ApiHttpUtils("http://222.180.162.222:7777/smsproxy/sendv1");
//		String pwd = MD5.MD5Encode("cq123456"+ "ZZ9ulwB");
//		Date date = ApiUtils.getNowDateTime();
//		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHH");
//		String dateStr = format.format(date);
//		System.out.println("dateStr:"+dateStr);
//		String token  = MD5.MD5Encode("7wl2GD0SYk"+":"+pwd+ ":"+"2015120814");
//		System.out.println("token:"+token);
//		client.setParams("token", token);
//		client.setParams("mobile", "13628478730");
//		client.setParams("content", "您好！请到叠彩城8栋1楼重庆有线社区服务站领取您的快递包裹，编号为（1）为了节省您的时间请报您收到的编号,谢谢!【重庆有线】");
//		client.setParams("date", ApiUtils.getNowDateTime());
//		try {
//			String result = client.doPost();
//			System.out.println("-------->result:"+result);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		ApiHttpUtils client = new ApiHttpUtils("http://117.59.224.246:8080/parcel/api/smsRecvStatus");
//		client.setParams("msg", "<?xml version=\"1.0\" encoding=\"utf-8\" ?><returnsms><statusbox><mobile>13628478730</mobile><taskid>1512095837214109</taskid><status>10</status><receivetime>2015-12-01 11:51:36</receivetime><errorcode>DELIVRD</errorcode></statusbox></returnsms>");
//		try {
//			String result = client.doPost();
//			System.out.println("-------->result:"+result);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		ApiHttpUtils client = new ApiHttpUtils("http://117.59.224.246:8080/parcel/api/smsRecvStatus");
		client.testsendTextByOpenids();
	}
	
	public void testsendTextByOpenids(){
        //String urlstr ="https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=ACCESS_TOKEN"; //群发图文消息
        String urlstr ="https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN"; //发送客服图文消息
        urlstr =urlstr.replace("ACCESS_TOKEN", "s_w_BIwYN9-5Jn4YUJo7M1NsyXdgfGNV8OmPIyoWdwkK5S39wTfJAzc_AYGaOJGscXLTxHLbcZMfYPSno0VOLFaK5CvnXRSp4naDES_2uFAlh2UweQ2Him6i1gIExMZQREGcCDANAC");
        String reqjson =sendWxKefuMsg("oPxXujrm8YDbeFxJ2R1srro6RIY4","路况订阅通知","您的路况订阅已发出，请点击详情查看！","http://cq96096.cn/subscribe/details?subsId=27", null);
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
            os.write(reqjson.getBytes("UTF-8"));//传入参数    
            os.flush();
            os.close();
            
            InputStream is =conn.getInputStream();
            int size =is.available();
            byte[] jsonBytes =new byte[size];
            is.read(jsonBytes);
            String message=new String(jsonBytes,"UTF-8");
         
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
	
    //创建发送的数据
    private String createGroupText(String openId){
         JSONObject gjson =new JSONObject();
         //JSONObject json = getUserOpenids(array.get(3).toString()); //array参数是用户组所有的用户,该方法打印array其中一个用户的详细信息
         gjson.put("touser", openId);
         gjson.put("msgtype", "text");
         JSONObject text =new JSONObject();
         text.put("content", "123213");
         gjson.put("text", text);
         
        return gjson.toString();
    }
    //创建发送的数据
    private String createGroupImgText(String openId){
    	JSONObject gjson =new JSONObject();
    	//JSONObject json = getUserOpenids(array.get(3).toString()); //array参数是用户组所有的用户,该方法打印array其中一个用户的详细信息
    	gjson.put("touser", openId);
    	gjson.put("msgtype", "news");
    	JSONObject news =new JSONObject();
    	JSONArray articles =new JSONArray();
    	JSONObject list =new JSONObject();
    	list.put("title","title"); //标题
    	list.put("description","description"); //描述
    	list.put("url","http://www.cq96096.cn/videoImg/details?videId=10044&type=2&openId=oPxXujrm8YDbeFxJ2R1srro6RIY4"); //点击图文链接跳转的地址
    	list.put("picurl","http://www.cq96096.cn/WS/10100101012110000008/9923/1_20160316_152319(10002444).jpg"); //图文链接的图片
    	articles.add(list);
    	news.put("articles", articles);
    	JSONObject text =new JSONObject();
    	gjson.put("text", text);
    	gjson.put("news", news);
    	
    	return gjson.toString();
    }

    public static String sendWxKefuMsg(String openId, String title, String desc, String url, String imgUrl){
    	String context = "{\"touser\":\""+openId+"\",\"msgtype\":\"news\",\"news\":{\"articles\": [{\"title\":\""+title+"\",\"description\":\""+desc+"\",\"url\":\""+url+"\"}]}}";
    	return context;
    }
	public static void doPostByte(String url,String json){
//		HttpClient httpClient = new DefaultHttpClient();  
//		//httpClient.getState().setCookiePolicy(CookiePolicy.COMPATIBILITY);  
//		System.setProperty("apache.commons.httpclient.cookiespec","COMPATIBILITY");  
//		HttpPost postMethod = new HttpPost(url);  
//		InputStream  in = new ByteArrayInputStream(json.toString().getBytes());  
//		
//		MultipartEntityBuilder mEntityBuilder = MultipartEntityBuilder.create();
//		//byte[] postBody
//		mEntityBuilder.addBinaryBody(postName, postBody);
//		//提交文件
//		//File file = new File("test");
//		//mEntityBuilder.addBinaryBody("name", file);
//		mEntityBuilder.addTextBody("name", "Value");
//
//		postMethod.setEntity(entity)
//		postMethod.setRequestBody(in);  
//		HttpClientParams params = new HttpClientParams();  
//		params.setConnectionManagerTimeout(10000L);  
//		httpClient.setParams(params);  
//		try {  
//			httpClient.executeMethod(postMethod);  
//			//获取二进制的byte流  
//			byte[] b = postMethod.getResponseBody();  
//			String str = new String(b,"UTF-8");  
//		}catch (Exception e) {  
//			System.out.println(e.getMessage()+","+e.getStackTrace());  
//		}finally{  
//			postMethod.releaseConnection();  
//		}  

	}
	
}
