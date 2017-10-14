package org.jiaowei.wxutil;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestSendWxMsg {

	
	//获取微信返回的access_token
    public static String getAccess_token() {
        String access_token=null;
        StringBuffer action =new StringBuffer();
        action.append("https://api.weixin.QQ.com/cgi-bin/token?grant_type=client_credential")
        .append("&appid=wx915ad295909bf037") //设置服务号的appid
        .append("&secret=b26b2f2432620fb532d117fe6d132afa"); //设置服务号的密匙
        String access_token_url = "2lvHdZKbKoFSaFaEWtRE_VUsaYQySwbA1pJrN69S1HGGhIKRTxy5Gvr-Vkq-ao-lMDtWDMpeN4mu9TwyguihdHe61iAipTbDwJVeAFL_lxyK_PSwU4KCH1aS0sQ3mueNYHRgABAUDN";
        URL url;
        try {
        	//模拟get请求
//            url = new URL(action.toString());
            url = new URL(access_token_url);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();        
            http.setRequestMethod("GET");        
            http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");    
            http.setDoInput(true);
            InputStream is =http.getInputStream();
            int size =is.available();
            byte[] buf=new byte[size];
            is.read(buf);
            String resp =new String(buf,"UTF-8");
            Map<String, Object> jsonMap=readJson2Map(resp);
//            JSONObject json = JSONObject.fromObject(resp);
//            Object object =json.get("access_token");
//            if(object !=null){
//                  access_token =String.valueOf(object);
//            }
            access_token = (String) jsonMap.get("access_token");
            System.out.println("getAccess_token:"+access_token);
            return access_token;
        } catch (MalformedURLException e) {
            e.printStackTrace();
             return access_token;
             
        } catch (IOException e) {
            e.printStackTrace();
             return access_token;
         
        }
    }
	
    public static void main(String[] args) {
		String access_token = getAccess_token();
		System.out.println("access_token:"+access_token);
//    	String json = "{\"address\": \"address2\",\"name\":\"haha2\",\"id\":2,\"email\":\"email2\"}";
//    	readJson2Map(json);
	}
    @SuppressWarnings("unchecked")
	public static Map<String, Object> readJson2Map(String json){
    	ObjectMapper  mapper = new ObjectMapper();
    	Map<String, Object> map = new HashMap<String, Object>();
    	try {
			map = mapper.readValue(json, Map.class);
			for (String s : map.keySet()) {
				System.out.println("key:" + s + ", value:" + map.get(s));
			}
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return map;
    }
    public static void readJson2List(String json) {
//        String json = "[{\"address\": \"address2\",\"name\":\"haha2\",\"id\":2,\"email\":\"email2\"},"+
//                    "{\"address\":\"address\",\"name\":\"haha\",\"id\":1,\"email\":\"email\"}]";
    	ObjectMapper  mapper = new ObjectMapper();
        try {
            List<Map<String, Object>> list = mapper.readValue(json, List.class);
            System.out.println(list.size());
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map = list.get(i);
                Set<String> set = map.keySet();
                for (Iterator<String> it = set.iterator();it.hasNext();) {
                    String key = it.next();
                    System.out.println(key + ":" + map.get(key));
                }
            }
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
