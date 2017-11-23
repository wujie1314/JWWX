package org.jiaowei.controllers;

import java.awt.Robot;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jiaowei.entity.RoadDlfxEntity;
import org.jiaowei.entity.RoadHtljEntity;
import org.jiaowei.entity.RoadLxfdEntity;
import org.jiaowei.entity.RoadSubscribeEntity;
import org.jiaowei.service.SysUserService;
import org.jiaowei.util.CommonConstantUtils;
import org.jiaowei.util.StringUtil;
import org.jiaowei.wxutil.NavMenuInitUtils;
import org.jiaowei.wxutil.WeiXinOperUtil;
import org.jiaowei.wxutil.WeixinUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.print.resources.serviceui;


@Controller
@RequestMapping("/psDesign")
public class PersonalDesignController {
	@Autowired
    private SysUserService sysUserService;
	
	/**
     * 查询路线路段名称
     * @return
     */
    @RequestMapping(value = "/getRoadName")
    public String getRoadLxfd(HttpServletRequest request,Map<String,Object> map,HttpServletResponse response)
    { 
    	String openId = request.getParameter("openID");
    	System.out.println(openId+".....");
    	StringBuffer sql=new StringBuffer();
    	sql.append(" SELECT ROAD_CODE,ROAD_NAME,LD_NAME,ROAD_DIR,START_NAME,END_NAME,DESCRIPT,ROAD_CODE1");
    	sql.append(" FROM ROAD_LXFD");
    	sql.append(" ORDER BY ROAD_CODE1 ASC");
    	List<Object> nodeList=sysUserService.findBySQL(sql.toString());
    	List<RoadLxfdEntity> list=new ArrayList<RoadLxfdEntity>();
    	try{
        	for(int i=0;i<nodeList.size();i++){
        		RoadLxfdEntity entity=new RoadLxfdEntity();
        		Object[] obj=(Object[])nodeList.get(i);
        		entity.setRoadCode(StringUtil.nullObject2String(obj[0]));
        		entity.setRoadName(StringUtil.nullObject2String(obj[1]));
        		entity.setLdName(StringUtil.nullObject2String(obj[2]));
        		entity.setRoadDir(StringUtil.nullObject2String(obj[3]));
        		entity.setStartName(StringUtil.nullObject2String(obj[4]));
        		entity.setEndName(StringUtil.nullObject2String(obj[5]));
        		entity.setDescript(StringUtil.nullObject2String(obj[6]));
        		entity.setRoadCode1(StringUtil.nullObject2String(obj[7]));
        		list.add(entity);
        	}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	RoadSubscribeEntity road = new RoadSubscribeEntity();
		road.setSubsRemindDate(WeixinUtils.parseDateStr(WeixinUtils.getNowDate()));
		road.setSubsRemindTimeHour("09:00");
		//查询文字列表
		map.put("openId", openId);
		map.put("road", road);
		request.getSession().setAttribute("openId", openId);
        request.setAttribute("list", list);
        System.out.println("*********"+request.getSession().getAttribute("openId"));
		return "personalDesign/person_subscription";
    }
    
    @RequestMapping(value = "/getRoadStart")
    @ResponseBody
    public List<RoadHtljEntity> getRoadHtljBegin(String roadCode)
    {
    	StringBuffer sql=new StringBuffer();
    	sql.append(" SELECT NAME,ROAD_CODE,INTERFLOW_NAME");
    	sql.append(" FROM ROAD_HTLJ");
    	sql.append(" WHERE ROAD_CODE='"+roadCode+"'");
    	sql.append(" ORDER BY TO_NUMBER(INTERFLOW_POS) ASC");
    	List<Object> nodeList=sysUserService.findBySQL(sql.toString());
    	List<RoadHtljEntity> list=new ArrayList<RoadHtljEntity>();
    	try{
        	for(int i=0;i<nodeList.size();i++){
        		RoadHtljEntity entity=new RoadHtljEntity();
        		Object[] obj=(Object[])nodeList.get(i);
        		entity.setName(StringUtil.nullObject2String(obj[0]));
        		entity.setRoadCode(StringUtil.nullObject2String(obj[1]));
        		entity.setInterflowName(StringUtil.nullObject2String(obj[2]));
        		list.add(entity);
        	}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
        return list;
    }
    @RequestMapping(value = "/getRoadEnd")
    @ResponseBody
    public List<RoadHtljEntity> getRoadHtljEnd(String roadCode)
    {
    	StringBuffer sql=new StringBuffer();
    	sql.append(" SELECT NAME,ROAD_CODE,INTERFLOW_NAME");
    	sql.append(" FROM ROAD_HTLJ");
    	sql.append(" WHERE ROAD_CODE='"+roadCode+"'");
    	sql.append(" ORDER BY TO_NUMBER(INTERFLOW_POS) ASC");
    	List<Object> nodeList=sysUserService.findBySQL(sql.toString());
    	List<RoadHtljEntity> list=new ArrayList<RoadHtljEntity>();
    	try{
        	for(int i=0;i<nodeList.size();i++){
        		RoadHtljEntity entity=new RoadHtljEntity();
        		Object[] obj=(Object[])nodeList.get(i);
        		entity.setName(StringUtil.nullObject2String(obj[0]));
        		entity.setRoadCode(StringUtil.nullObject2String(obj[1]));
        		entity.setInterflowName(StringUtil.nullObject2String(obj[2]));
        		list.add(entity);
        	}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
        return list;
    }
    
    @RequestMapping(value = "/getRoadDirection")
    @ResponseBody
    public List<RoadDlfxEntity> getRoadDlfx(String roadCode)
    {
    	StringBuffer sql=new StringBuffer();
    	sql.append(" SELECT rd.DISPLAY_NAME,rd.DICT_CODE");
    	sql.append(" FROM ROAD_DLFX rd,ROAD_LXFD rl");
    	sql.append(" WHERE rd.DICT_CODE=rl.ROAD_DIR AND rl.ROAD_CODE='"+roadCode+"'");
    	sql.append(" ORDER BY FACT_VALUE ASC");
    	List<Object> nodeList=sysUserService.findBySQL(sql.toString());
    	List<RoadDlfxEntity> list=new ArrayList<RoadDlfxEntity>();
    	try{
        	for(int i=0;i<nodeList.size();i++){
        		RoadDlfxEntity entity=new RoadDlfxEntity();
        		Object[] obj=(Object[])nodeList.get(i);
        		entity.setDisplayName(StringUtil.nullObject2String(obj[0]));
        		entity.setDictCode(StringUtil.nullObject2String(obj[1]));
        		list.add(entity);
        	}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
        return list;
    }
    @RequestMapping(value = "/getViolationInformation")
    @ResponseBody
    public void getViolationInformation(String openId,String lookType,String DATE,String TIME,String license,String color,String delayTime){
    	String message = null;
    	int delayTimeNUM=Integer.valueOf(delayTime);
    	if(license.equals("123")){
    		message="你闯了红灯，罚款500";
    	}else{
    		message="你的表现很好，无违章";
    	}
    	System.out.println("小朋友你违章"+openId);
    	if(delayTimeNUM>=0){
    	 Timer timer = new Timer();
         timer.schedule(new PersonalDesignController().new Task(openId,message),delayTimeNUM);
    	}
    	 /*  String userJsonContent = String.format("{\"touser\":\"%s\",\"msgtype\":\"text\",\"text\":{\"content\":\"%s\"}}",
    			   openId, String.format(message));
    	String publicID = NavMenuInitUtils.getInstance().userPublicIdMap.get(openId); //通过微信openid获取对应的公众号
		//发送給用户
		// 这里有点问题 获取不到对应的公众号accessToken
		WeiXinOperUtil.sendMsgToWx(WeiXinOperUtil.getAccessToken(publicID), userJsonContent);*/
    	
    }
    @RequestMapping(value = "/getTraffic")
    @ResponseBody
    public void getTraffic(String openId,String lookType,String DATE,
    		String TIME,String lxfd,String htlj_begin,String htlj_end,String dlfx,String delayTime){
    	String message = null;
    	int delayTimeNUM=Integer.valueOf(delayTime);
    	if(lxfd.equals("G42沪蓉高速")){
    		message="10月26日9时21分，G42沪蓉高速垫忠段上行方向明月山隧道因施工养护，上行方向正线封闭，下行方向单道双通。"
    				+ "长20米、宽3.2米、高4.2米以上超限车辆禁止通行。预计11月25日结束。";
    	}else if(lxfd.equals("G50沪渝高手")){
    		message="  6月12日17时24分，G50沪渝高速垫忠段下行方向谭家寨隧道封闭施工，"
    				+ "上行方向单洞双通，双向长20米、宽3.2米、高4.2米以上超限车辆禁止通行。预计12月31日施工结束。";
    	}else{
    		message="道路通畅可以放心通行";
    	}
    	System.out.println("道路信息文字");
    	if(delayTimeNUM>=0){
    	Timer timer = new Timer();
        timer.schedule(new PersonalDesignController().new Task(openId,message),delayTimeNUM);
    	}
    	
    	
    }
    @RequestMapping(value = "/getTrafficByPicture")
    @ResponseBody
    public void getTrafficByPicture(String openId,String lookType,String DATE,
    		String TIME,String ph,String section,String delayTime){
    	String message=null;
    	int delayTimeNUM=Integer.valueOf(delayTime);
    	if(section.equals("G75渝黔段")){
    		message="http://www.cq96096.cn/videoImg/nextList?parentId=1002&type=2&openId=oPxXujqkhacHTudxFlVug9QIt_4s";
    		
    	}else if(section.equals("G65渝湘段")){
    	 message = "http://www.cq96096.cn/videoImg/nextList?parentId=1003&type=2&openId=oPxXujqkhacHTudxFlVug9QIt_4s";
    	}else if(section.equals("G50沪渝高速")){
    		message="http://www.cq96096.cn/videoImg/nextList?parentId=1008&type=2&openId=oPxXujqkhacHTudxFlVug9QIt_4s";	
    	}else {
			message="http://www.cq96096.cn/videoImg/list?code=003DrKMk1I9lzl0jeYNk1GmyMk1DrKMX&state=123";
		}
    	System.out.println("道路信息截图");
    	if(delayTimeNUM>=0){
        	Timer timer = new Timer();
            timer.schedule(new PersonalDesignController().new Task(openId,message),delayTimeNUM);
        	}
        	
    	
    }
    @RequestMapping(value = "/getBus")
    @ResponseBody
    public void getBus(String openId,String lookType,String DATE,
    		String TIME,String delayTime){
    	String message=null;
    	int delayTimeNUM=Integer.valueOf(delayTime);
    	message="http://web.chelaile.net.cn/wwd/index?src=webapp_weixin_chongqing";
    	System.out.println("公交信息");
    	if(delayTimeNUM>=0){
        	Timer timer = new Timer();
            timer.schedule(new PersonalDesignController().new Task(openId,message),delayTimeNUM);
        	}
        	
    	
    }
    class Task extends TimerTask {
        private String openId;
        private String message;
 
        public  Task(String openId,String message) {
            this.openId = openId;
            this.message=message;
        }
       
 
        @Override
        public void run() {
        	System.out.println(".........."+openId);
        	 String userJsonContent = String.format("{\"touser\":\"%s\",\"msgtype\":\"text\",\"text\":{\"content\":\"%s\"}}",
      			   openId, String.format("私人定制消息："+message));
      	String publicID = NavMenuInitUtils.getInstance().userPublicIdMap.get(openId); //通过微信openid获取对应的公众号
  		//发送給用户
  		// 这里有点问题 获取不到对应的公众号accessToken
	  		if(!openId.subSequence(0, 3).equals("app")){
				WeiXinOperUtil.sendMsgToWx(WeiXinOperUtil.getAccessToken(publicID), userJsonContent);
			}   
        }
 
    }
    public static String uploadMedia(String accessToken, String type, String mediaFileUrl) {
    	String MEDIA_URL = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
        StringBuffer resultStr = null;
        //拼装url地址
        String mediaStr = MEDIA_URL.replace("ACCESS_TOKEN", accessToken).replace("TYPE", type);
        System.out.println("mediaStr:" + mediaStr);
        URL mediaUrl;
        try {
            String boundary = "----WebKitFormBoundaryOYXo8heIv9pgpGjT";
            URL url = new URL(mediaStr);
            HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
            //让输入输出流开启
            urlConn.setDoInput(true);
            urlConn.setDoOutput(true);
            //使用post方式请求的时候必须关闭缓存
            urlConn.setUseCaches(false);
            //设置请求头的Content-Type属性
            urlConn.setRequestProperty("Content-Type", "multipart/form-data; boundary="+boundary);
            urlConn.setRequestMethod("POST");
            //获取输出流，使用输出流拼接请求体
            OutputStream out = urlConn.getOutputStream();

            //读取文件的数据,构建一个GET请求，然后读取指定地址中的数据
            mediaUrl = new URL(mediaFileUrl);
            HttpURLConnection mediaConn = (HttpURLConnection)mediaUrl.openConnection();
            //设置请求方式
            mediaConn.setRequestMethod("GET");
            //设置可以打开输入流
            mediaConn.setDoInput(true);
            //获取传输的数据类型
            String contentType = mediaConn.getHeaderField("Content-Type");
            //将获取大到的类型转换成扩展名
            String fileExt = judgeType(contentType);
            //获取输入流，从mediaURL里面读取数据
            InputStream in = mediaConn.getInputStream();
            BufferedInputStream bufferedIn = new BufferedInputStream(in);
            //数据读取到这个数组里面
            byte[] bytes = new byte[1024];
            int size = 0;
            //使用outputStream流输出信息到请求体当中去
            out.write(("--"+boundary+"\r\n").getBytes());
            out.write(("Content-Disposition: form-data; name=\"media\";\r\n"
                    + "filename=\""+(new Date().getTime())+fileExt+"\"\r\n"
                            + "Content-Type: "+contentType+"\r\n\r\n").getBytes());
            while( (size = bufferedIn.read(bytes)) != -1) {
                out.write(bytes, 0, size);
            }
            //切记，这里的换行符不能少，否则将会报41005错误
            out.write(("\r\n--"+boundary+"--\r\n").getBytes());

            bufferedIn.close();
            in.close();
            mediaConn.disconnect();

            InputStream resultIn = urlConn.getInputStream();
            InputStreamReader reader = new InputStreamReader(resultIn);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String tempStr = null;
            resultStr = new StringBuffer(); 
            while((tempStr = bufferedReader.readLine()) != null) {
                resultStr.append(tempStr);
            }
            bufferedReader.close();
            reader.close();
            resultIn.close();
            urlConn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultStr.toString();
    }
    /**
     * 通过传过来的contentType判断是哪一种类型
     * @param contentType 获取来自连接的contentType
     * @return
     */
    public static String judgeType(String contentType) {
        String fileExt = "";
        if("image/jpeg".equals(contentType)) {
            fileExt = ".jpg";
        } else if("audio/mpeg".equals(contentType)) {
            fileExt = ".mp3";
        } else if("audio/amr".equals(contentType)) {
            fileExt = ".amr";
        } else if("video/mp4".equals(contentType)) {
            fileExt = ".mp4";
        } else if("video/mpeg4".equals(contentType)) {
            fileExt = ".mp4";
        }
        return fileExt;
    }

    
    
    
    
    
    
  //生成图片验证码
//  		@RequestMapping("/getRandcode")
//  		@ResponseBody
//  		public void getRandcode(HttpServletRequest request,
//  				HttpServletResponse response) {
//  			RandomValidateCode randCode = new RandomValidateCode();
//  			randCode.getRandcode(request, response);
//  		}
}
