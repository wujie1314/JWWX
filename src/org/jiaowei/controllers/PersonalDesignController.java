package org.jiaowei.controllers;

import java.awt.Robot;
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
    		message="http://www.cq96096.cn/videoImg/nextList?parentId=1003&type=2&openId=oPxXujqkhacHTudxFlVug9QIt_4s";	
    	}else {
			message="http://www.cq96096.cn/videoImg/nextList?parentId=1009&type=2&openId=oPxXujqkhacHTudxFlVug9QIt_4s";
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
    
    
    
    
    
    
  //生成图片验证码
//  		@RequestMapping("/getRandcode")
//  		@ResponseBody
//  		public void getRandcode(HttpServletRequest request,
//  				HttpServletResponse response) {
//  			RandomValidateCode randCode = new RandomValidateCode();
//  			randCode.getRandcode(request, response);
//  		}
}
