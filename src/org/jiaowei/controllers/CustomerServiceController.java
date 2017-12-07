package org.jiaowei.controllers;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.jiaowei.entity.CustomerServiceHisEntity;
import org.jiaowei.entity.DepartEntity;
import org.jiaowei.entity.MsgFromCSEntity;
import org.jiaowei.entity.MsgFromCustomerServiceEntity;
import org.jiaowei.entity.MsgFromWxEntity;
import org.jiaowei.entity.NodeEntity;
import org.jiaowei.entity.NoticeEntity;
import org.jiaowei.entity.RoadDlfxEntity;
import org.jiaowei.entity.RoadHtljEntity;
import org.jiaowei.entity.RoadLxfdEntity;
import org.jiaowei.entity.RoadSubscribeEntity;
import org.jiaowei.entity.SeatsEntity;
import org.jiaowei.entity.SysUserEntity;
import org.jiaowei.entity.SysUserRoleEntity;
import org.jiaowei.entity.UsuallyWordEntity;
import org.jiaowei.entity.UsuallyWordTypeEntity;
import org.jiaowei.entity.WeixinUserInfoEntity;
import org.jiaowei.entity.WxEntity;
import org.jiaowei.entity.WxExpressionEntity;
import org.jiaowei.entity.WxSblkEntity;
import org.jiaowei.entity.WxSblkImgEntity;
import org.jiaowei.entity.WxStatusTmpTEntity;
import org.jiaowei.service.CustomerServiceHisService;
import org.jiaowei.service.DepartService;
import org.jiaowei.service.MsgFromCustomerServiceService;
import org.jiaowei.service.SysUserService;
import org.jiaowei.service.WeixinUserInfoService;
import org.jiaowei.service.WxStatusTmpService;
import org.jiaowei.util.DateUtils;
import org.jiaowei.util.FastJsonUtil;
import org.jiaowei.util.JsonUtils;
import org.jiaowei.util.ListUtils;
import org.jiaowei.util.PropertiesUtil;
import org.jiaowei.util.StringUtil;
import org.jiaowei.vo.WeixinUserInfoVO;
import org.jiaowei.wxutil.ApiHttpUtils;
import org.jiaowei.wxutil.NavMenuInitUtils;
import org.jiaowei.wxutil.PastUtil;
import org.jiaowei.wxutil.WeiXinConst;
import org.jiaowei.wxutil.WeiXinOperUtil;
import org.jiaowei.wxutil.WeixinUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foxconn.socket.client.SocketAppPacket;
import com.foxconn.socket.client.SocketClientHandlerListener;
import com.foxconn.socket.client.SocketProtocolCodecFactory;
import com.yzh.cqjw.request.AddViolationReportCommentRequest;
import com.yzh.cqjw.request.AddViolationReportViewRequest;
import com.yzh.cqjw.request.GetPagedViolationReportListRequest;
import com.yzh.cqjw.request.GetViolationReportBaseDataRequest;
import com.yzh.cqjw.request.GetViolationReportByBatchNoRequest;

/**
 * 这个基本是一个工具类,用于各种查询相关的操作
 * Created by alex on 15-12-9.
 */
@Controller
@RequestMapping("/csc")
public class CustomerServiceController {

    private static Logger logger = Logger.getLogger(CustomerServiceController.class);

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private CustomerServiceHisService customerServiceHisService;
    @Autowired
    private DepartService departService;
    @Autowired
    private MsgFromCustomerServiceService msgFromCustomerService;
    @Autowired
    private WxStatusTmpService wxStatusTmpService;
    @Autowired
    private WeixinUserInfoService weixinUserInfoService;
    /**
     * 查询路线路段名称
     * @return
     */
    @RequestMapping(value = "/getRoadLxfd")
    public String getRoadLxfd(HttpServletRequest request,Map<String,Object> map)
    {
    	String openId = request.getParameter("openId");
    	//Map<String,Object> jsonMap=new HashMap<String,Object>();
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
        request.setAttribute("list", list);
		return "roadSubscribe/addText";
    }

    @RequestMapping(value = "/getRoadLxfdList")
    @ResponseBody
    public List<RoadLxfdEntity> getRoadLxfdList(HttpServletRequest request)
    {
    	//Map<String,Object> jsonMap=new HashMap<String,Object>();
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
		return list;
    }
    
    @RequestMapping(value = "/queryHighRoadList")
    @ResponseBody
    public List<RoadLxfdEntity> queryHighRoadList(HttpServletRequest request){
    	//Map<String,Object> jsonMap=new HashMap<String,Object>();
    	StringBuffer sql=new StringBuffer();
    	sql.append(" SELECT ROAD_CODE,ROAD_NAME,LD_NAME,ROAD_DIR,START_NAME,END_NAME,DESCRIPT,ROAD_CODE1");
    	sql.append(" FROM ROAD_LXFD");
    	sql.append(" ORDER BY ROAD_CODE1 ASC");
    	List<Object> nodeList=sysUserService.findBySQL(sql.toString());
    	List<RoadLxfdEntity> list=new ArrayList<RoadLxfdEntity>();
    	try{
    		String url = "http://10.224.5.164/cqjt/getRoadNews?type=6&location=500000&limit=100";
            StringBuilder json = new StringBuilder();
            try {
                URL oracle = new URL(url);
                URLConnection yc = oracle.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(),"utf-8"));
                String inputLine = null;
                while ( (inputLine = in.readLine()) != null) {
                    json.append(inputLine);
                }
                in.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Map<String,Object> objMap = JsonUtils.jsonToMap(json.toString());
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
        		setRoadLxfdEntity(objMap, entity);
        		list.add(entity);
        	}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
		return list;
    }
    
    @SuppressWarnings("unchecked")
	private void setRoadLxfdEntity(Map<String,Object> objMap, RoadLxfdEntity entity){
    	List<String> result = new ArrayList<String>();
    	if(objMap != null && objMap.size() > 0){
    		String name = entity.getRoadCode()+entity.getRoadName()+entity.getLdName();
    		List<Map<String, Object>> mapList =  (List<Map<String, Object>>) objMap.get("data");
    		for (Map<String, Object> map : mapList) {
				String flag = (String) map.get("roadLineName");
				if(name.equals(flag.trim())){
					result.add(String.valueOf(map.get("content")));
				}
			}
    	}
    	entity.setData(result);
    }
    
    @RequestMapping(value = "/getRoadHtljBegin")
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
    @RequestMapping(value = "/getRoadHtljEnd")
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
    
    @RequestMapping(value = "/getRoadDlfx")
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
    /**
     * 根据开始和结束节点查询订阅信息
     *     目前只是按照节点匹配，无顺序对应
     * @param startNode 开始节点
     * @param endNode   结束节点
     * @return
     */
    @RequestMapping(value = "/getTextRoadNews")
    @ResponseBody
    public List<JSONObject> getTextRoadNews(String startNode,String endNode)
    {
    	String id="G85,永川互通,邮亭互通,下行（出城）";
    	String[] ids=id.split(",");
    	List<JSONObject> list=new ArrayList<JSONObject>();
        String url = "http://10.224.5.164/cqjt/getRoadNews?type=6&location=500000&limit=100";
    	//String url = "http://localhost:8080/cqjw/getRoadNews?type=6&location=500000&limit=100";
        StringBuilder json = new StringBuilder();
        try {
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(),"utf-8"));
            String inputLine = null;
            while ( (inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jObject=JSONObject.parseObject(json.toString());
        JSONArray jsonArray = JSONObject.parseArray(jObject.get("data").toString());
        for(int i=0;i<jsonArray.size();i++){
        	JSONObject j=JSONObject.parseObject(jsonArray.get(i).toString());
        	String betweenNode=(String) j.get("betweenNode");
        	if(betweenNode.indexOf(ids[1].replace("互通",""))!=-1&&betweenNode.indexOf(ids[2].replace("互通",""))!=-1){
        		list.add(j);
    		}
        }
        return list;
    }
    @RequestMapping(value = "/deleteWorkTab")
    @ResponseBody
    public void deleteWorkTab(String id,String userId)
    {
        String url = "http://10.224.2.177:7001/WebRoot/v/wx!delete?user="+userId+"&myid="+id;
        try {
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            yc.getInputStream();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 查询所有节点名称（trunk为路段名称、name为互通立交高速路收费站点）
     * @return
     */
    @RequestMapping(value = "/getNodeName")
    @ResponseBody
    public String getNodeName()
    {
    	Map<String,Object> jsonMap=new HashMap<String,Object>();
    	StringBuffer sql=new StringBuffer();
    	sql.append(" SELECT ID,NAME,CODE,TRUNK,NO,\"ORDER\",NODE,DESCRIPT,TRUNK_NO ");
    	sql.append(" FROM NODE_T");
    	sql.append(" ORDER BY TRUNK_NO ASC,\"ORDER\" ASC");
    	List<Object> nodeList=sysUserService.findBySQL(sql.toString());
    	List<NodeEntity> list=new ArrayList<NodeEntity>();
    	for(int i=0;i<nodeList.size();i++){
    		NodeEntity entity=new NodeEntity();
    		Object[] obj=(Object[])nodeList.get(i);
    		entity.setId(new Integer(obj[0].toString()));
    		entity.setName(StringUtil.nullObject2String(obj[1]));
    		entity.setCode(StringUtil.nullObject2String(obj[2]));
    		entity.setTrunk(StringUtil.nullObject2String(obj[3]));
    		entity.setNo(StringUtil.nullObject2String(obj[4]));
    		entity.setOrder(Double.valueOf(obj[5].toString()));
    		entity.setNode(obj[6].toString());
    		entity.setDescript(StringUtil.nullObject2String(obj[7]));
    		entity.setTrunkNo(new Integer(obj[8].toString()));
    		list.add(entity);
    	}
        jsonMap.put("data", list);
        return JSON.toJSONString(jsonMap);
//        String url = "http://10.224.5.164/cqjt/getNodeName";
//        //String url = "http://localhost:8080/cqjw/getNodeName";
//        StringBuilder json = new StringBuilder();
//        try {
//            URL oracle = new URL(url);
//            URLConnection yc = oracle.openConnection();
//            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(),"utf-8"));
//            String inputLine = null;
//            while ( (inputLine = in.readLine()) != null) {
//                json.append(inputLine);
//            }
//            in.close();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return JSONObject.parseObject(json.toString());
    }
    @RequestMapping("/replaceQQFace")
    @ResponseBody
    public String replaceQQFace(){
    	String hql="FROM WxExpressionEntity";
    	List<WxExpressionEntity> list=sysUserService.findByHql(hql);
    	return JSON.toJSONString(list);
    }
    @RequestMapping("/sendQQFace")
    @ResponseBody
    public String sendQQFace(int id,String userid,String openid){
    	WxExpressionEntity weEntity=sysUserService.get(WxExpressionEntity.class, id);
    	return JSON.toJSONString(weEntity);
    }
    
    @RequestMapping("/reportSblkInfo")
    @ResponseBody
    public String reportSblkInfo(String openid,int id){
    	Map<String,String> map=new HashMap<String,String>();
    	WxSblkEntity wsEntity=sysUserService.get(WxSblkEntity.class, id);
    	String reportMan=StringUtil.nullObject2String(wsEntity.getReportMan());
    	if(reportMan.indexOf(openid)!=-1){
    		map.put("status", "GOT");
    	}else{
        	wsEntity.setReportMan(reportMan+","+openid);
        	wsEntity.setReportNum(wsEntity.getReportNum()+1);
        	sysUserService.saveOrUpdate(wsEntity);
    		map.put("status", "OK");
    	}
        return JSON.toJSONString(map);
    }
    
    @RequestMapping("/detailSblkInfo")
    public String detailSblkInfo(HttpServletRequest request,String openid,int id){
    	WxSblkEntity wsEntity=sysUserService.get(WxSblkEntity.class, id);
    	String hql=" From WxSblkImgEntity WHERE sblkId="+wsEntity.getId();
    	List<WxSblkImgEntity> list=new ArrayList<WxSblkImgEntity>();
    	list=sysUserService.findByHql(hql);
    	String urls="";
    	for(int i=0;i<list.size();i++){
    		WxSblkImgEntity wsi=list.get(i);
    		if(i<list.size()-1){
    			urls+="'/upload/"+wsi.getImageSrc()+".jpg',";
    		}else{
    			urls+="'/upload/"+wsi.getImageSrc()+".jpg'";
    		}
    	}
    	request.setAttribute("openid", openid);
    	request.setAttribute("list", list);
    	request.setAttribute("urls", urls);
        request.setAttribute("wsEntity", wsEntity);
//        String jsapi_ticket =  WeiXinOperUtil.getJsApiTicket();
		Integer deptId = NavMenuInitUtils.getInstance().userDeptMap.get(openid);
		String jsapi_ticket = WeiXinOperUtil.getJsApiTicket(deptId); // deptId 已改
        String url = PastUtil.getUrl(request);
        Map<String, String> params = PastUtil.sign(jsapi_ticket, url);
       	Integer deptID = NavMenuInitUtils.getInstance().userDeptMap.get(openid);
        params.put("appid", WeiXinConst.getAppID(deptID)); // appId 修改？？？？ openid 已修改
        params.put("openid", openid);
        request.setAttribute("sign", params);
        return "detailRoadNews";
    }
    
    @RequestMapping("/getSblkInfo")
    @ResponseBody
    public String getSblkInfo(int page,int pageSize){
    	Map<String,Object> jsonMap=new HashMap<String,Object>();
    	StringBuffer sql=new StringBuffer();
    	sql.append(" FROM(");
    	sql.append(" SELECT ID,TITLE,CONTENT,LONGITUDE,LATITUDE,CREATE_TIME,REPORT_NUM,REPORT_MAN,CREATE_MAN,ROWNUM RN FROM(");
    	sql.append(" SELECT *"); 
    	sql.append(" FROM WX_SBLK_T WHERE REPORT_NUM<2 AND CREATE_TIME>=(sysdate-2/24)"); 
    	sql.append(" ORDER BY CREATE_TIME DESC");
    	sql.append(" )A");
    	sql.append(" ) B");
    	String listSql="SELECT * "+sql.toString()+" WHERE RN BETWEEN "+((page-1)*pageSize+1)+" AND "+page*pageSize;
    	String countSql="SELECT COUNT(*) "+sql.toString();
    	List<Object> sblkList=sysUserService.findBySQL(listSql);
    	List<WxSblkEntity> list=new ArrayList<WxSblkEntity>();
    	for(int i=0;i<sblkList.size();i++){
    		WxSblkEntity entity=new WxSblkEntity();
    		Object[] obj=(Object[])sblkList.get(i);
    		entity.setId(new Integer(obj[0].toString()));
    		entity.setTitle(StringUtil.nullObject2String(obj[1]));
    		entity.setContent(StringUtil.nullObject2String(obj[2]));
    		entity.setLongitude(StringUtil.nullObject2String(obj[3]));
    		entity.setLatitude(StringUtil.nullObject2String(obj[4]));
    		entity.setCreateTime(Timestamp.valueOf(obj[5].toString()));
    		entity.setReportNum(new Integer(obj[6].toString()));
    		entity.setReportMan(StringUtil.nullObject2String(obj[7]));
    		entity.setCreateMan(StringUtil.nullObject2String(obj[8]));
    		list.add(entity);
    	}
        List<Object> countList=msgFromCustomerService.findBySQL(countSql);
        jsonMap.put("total", countList.get(0)); 
        jsonMap.put("data", list);
        return JSON.toJSONString(jsonMap);
    }
    
    @RequestMapping("/saveSblkInfo")
    @ResponseBody
    public String saveSblkInfo(WxSblkEntity wsEntity,String imageSrc){
    	String strResult="";
    	wsEntity.setCreateTime(DateUtils.getTimestamp());
    	wsEntity.setReportNum(0);
    	sysUserService.save(wsEntity);
    	if(null!=imageSrc&&!"".equals(imageSrc)){
    		String[] images=imageSrc.split(",");
    		for(int i=0;i<images.length-1;i++){
    			WxSblkImgEntity wsiEntity=new WxSblkImgEntity();
    			wsiEntity.setImageSrc(images[i]);
    			wsiEntity.setSblkId(wsEntity.getId());
    	    	sysUserService.save(wsiEntity);
    		}
    	}
        return strResult;
    }
    
    @RequestMapping("/getImageSrc")
    @ResponseBody
    public String getImageSrc(HttpServletRequest request,String serverId) throws IOException{
    	String strResult=WeiXinOperUtil.downloadImageFromWx(serverId, request);
        return strResult;
    }
    
    @RequestMapping("/wxSign")
    public String wxSign(HttpServletRequest request,String openid){
    	Integer deptId = NavMenuInitUtils.getInstance().userDeptMap.get(openid);
        String jsapi_ticket = WeiXinOperUtil.getJsApiTicket(deptId); // deptId已改
        String url = PastUtil.getUrl(request);
        Map<String, String> params = PastUtil.sign(jsapi_ticket, url);

        params.put("appid", WeiXinConst.getAppID(deptId)); // appId 修改？？？？ openid 已修改
        params.put("openid", openid);
        request.setAttribute("sign", params);
        return "addRoadNews";
    }

    @RequestMapping("/wxSignature")
    @ResponseBody
    public String wxSignature(HttpServletRequest request){
        String jsapi_ticket = WeiXinOperUtil.getJsApiTicket();
        String url = PastUtil.getUrl(request);
        Map<String, String> params = PastUtil.sign(jsapi_ticket, url);
        params.put("appid", WeiXinConst.appId); // appId 修改？？？？
        request.setAttribute("sign", params);
        String result = JSON.toJSONString(params);
		return result;
    }
    @RequestMapping("/showMsgTextMore")
    @ResponseBody
    public String showMsgTextMore(String fromUser,String toUser,String beginTime,int pageSize){
    	List<MsgFromCSEntity> list = new ArrayList<MsgFromCSEntity>();
    	StringBuffer sql=new StringBuffer();
    	sql.append(" FROM (");
    	sql.append(" SELECT FROM_USER,TO_USER,CONTENT,CREATE_TIME"
    			  +" FROM MSG_FROM_CS_T");
    	if(("ALL").equals(toUser)){
    		sql.append(" WHERE TO_USER='"+toUser+"' AND CREATE_TIME<to_timestamp('"+beginTime+"','yyyy-mm-dd hh24:mi:ss:ff') Order By CREATE_TIME DESC");
    	}else{
    		sql.append(" WHERE (TO_USER='"+toUser+"' AND FROM_USER='"+fromUser+"' OR TO_USER='"+fromUser+"' AND FROM_USER='"+toUser+"') AND CREATE_TIME<to_timestamp('"+beginTime+"','yyyy-mm-dd hh24:mi:ss:ff') Order By CREATE_TIME DESC");
    	}
    	sql.append(" ) A");
    	String listSql="SELECT FROM_USER,TO_USER,CONTENT,CREATE_TIME"+sql.toString()+" WHERE ROWNUM BETWEEN 0 AND "+pageSize;
    	List<Object> userList=msgFromCustomerService.findBySQL(listSql);
    	for(int i=0;i<userList.size();i++){
    		MsgFromCSEntity entity=new MsgFromCSEntity();
    		Object[] obj=(Object[])userList.get(i);
    		entity.setFromUser(obj[0].toString());
    		entity.setToUser(obj[1].toString());
    		entity.setContent(obj[2].toString());
    		entity.setCreateTime(Timestamp.valueOf(obj[3].toString()));
    		list.add(entity);
    	}
        String result = JSON.toJSONString(list); 
    	return result;
    }
    
    @RequestMapping("/pushMsgText")
    @ResponseBody
    public String pushMsgText(String fromUser,String toUser,String endTime){
    	List<Object> list = new ArrayList<Object>();
    	String hql="";
    	if("ALL".equals(toUser)){
    		hql="FROM MsgFromCSEntity WHERE toUser='"+toUser+"' AND createTime>to_timestamp('"+endTime+"','yyyy-mm-dd hh24:mi:ss:ff') Order By createTime ASC";
    	}
    	else{
    		hql="FROM MsgFromCSEntity WHERE (toUser='"+toUser+"' AND fromUser='"+fromUser+"' OR toUser='"+fromUser+"' AND fromUser='"+toUser+"') AND createTime>to_timestamp('"+endTime+"','yyyy-mm-dd hh24:mi:ss:ff') Order By createTime ASC";
        }
    	list=sysUserService.findByHql(hql);
        String result = JSON.toJSONString(list); 
    	return result;
    }
    @RequestMapping("/getMsgText")
    @ResponseBody
    public String getMsgText(String fromUser,String toUser){
    	List<Object> list = new ArrayList<Object>();
    	String hql="";
    	if("ALL".equals(toUser)){
    		hql="FROM MsgFromCSEntity WHERE toUser='"+toUser+"' AND createTime>=to_timestamp('"+DateUtils.formatDate()+"','yyyy-mm-dd hh24:mi:ss:ff') Order By createTime ASC";
    	}else{
    		//清除未读消息
    		String hql2="FROM MsgFromCSEntity WHERE fromUser='"+toUser+"' AND toUser='"+fromUser+"' AND readed=0";
    		List<MsgFromCSEntity> msgList=sysUserService.findByHql(hql2);
    		for(int i=0;i<msgList.size();i++){
    			MsgFromCSEntity entity=msgList.get(i);
    			entity.setReaded(1);
        		sysUserService.saveOrUpdate(entity);
    		}
    		hql="FROM MsgFromCSEntity WHERE (toUser='"+toUser+"' AND fromUser='"+fromUser+"' OR toUser='"+fromUser+"' AND fromUser='"+toUser+"') AND createTime>to_timestamp('"+DateUtils.formatDate()+"','yyyy-mm-dd hh24:mi:ss:ff') Order By createTime ASC";
        }
    	list=sysUserService.findByHql(hql);
        String result = JSON.toJSONString(list); 
    	return result;
    }
    @RequestMapping("/sendMsgText")
    @ResponseBody
    public String sendMsgText(MsgFromCSEntity entity){
    	entity.setCreateTime(new Timestamp(System.currentTimeMillis()));
    	entity.setReaded(0);
    	sysUserService.save(entity);
    	return JSON.toJSONString(entity);
    }

    @RequestMapping(value = "/geocoder")
    @ResponseBody
    public JSONObject geocoder(String latitude,String longitude)
    {
//    	String location=WeixinUtils.getBaiDuLocationXY(longitude,latitude);
//      String url = "http://api.map.baidu.com/geocoder?location="+location+"&output=json&key=28bcdd84fae25699606ffad27f8da77b";
    	String url = "http://api.map.baidu.com/geocoder?location="+latitude+","+longitude+"&output=json&key=28bcdd84fae25699606ffad27f8da77b";
        StringBuilder json = new StringBuilder();
        try {
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(),"utf-8"));
            String inputLine = null;
            while ( (inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(json.toString());
    }
    @RequestMapping(value = "/getRoadNews")
    @ResponseBody
    public JSONObject getRoadNews()
    {
        String url = "http://10.224.5.164/cqjt/getRoadNews?type=6&location=500000&limit=100";
        StringBuilder json = new StringBuilder();
        try {
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(),"utf-8"));
            String inputLine = null;
            while ( (inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(json.toString());
    }

    @RequestMapping(value = "/getGSGDRoadNews")
    @ResponseBody
    public JSONObject getGSGDRoadNews()
    {
        String url = "http://10.224.5.164/cqjt/getRoadNews?type=7&location=500000&limit=100";
        StringBuilder json = new StringBuilder();
        try {
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(),"utf-8"));
            String inputLine = null;
            while ( (inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(json.toString());
    }
    
    @RequestMapping("/getSysUserInfo")
    @ResponseBody
    public String getSysUserInfo(){
    	List<Object> list = new ArrayList<Object>();
    	String hql="FROM SysUserEntity WHERE deptId IN(3,5,6,12)"; // 标记
    	list=sysUserService.findByHql(hql);
        String result = JSON.toJSONString(list); 
    	return result;
    }
    @RequestMapping("/changeBusyOrFree")
    @ResponseBody
    public String changeBusyOrFree(String userId){
//        Set<String> keys = WeiXinConst.servicingMap.keySet();
    	List<SysUserEntity> list = new ArrayList<SysUserEntity>();
    	String hql="FROM SysUserEntity WHERE deptId IN(3,5,6,12)"; // 标记
    	list=sysUserService.findByHql(hql);
    	WxStatusTmpTEntity entity=new WxStatusTmpTEntity();
    	for(int j=0;j<list.size();j++){
    		//繁忙状态
    		SysUserEntity sEntity=list.get(j);
    		int num=0;
    		Map<String, WxStatusTmpTEntity> map=NavMenuInitUtils.getInstance().serviceMap.get(sEntity.getDeptId());
            if(map!=null){
    		Set<String> keys = map.keySet();
	        	for (String openId : keys) {
	//            	entity = WeiXinConst.servicingMap.get(openId);
	            	entity = NavMenuInitUtils.getInstance().getServiceEntity(openId);
	        		if(entity.getCsId().equals(sEntity.getId().toString())){
	        			num++;
	        		}
	            }
            }
        	Set<String> keys2=WeiXinConst.servicingYqMap.keySet();
            for(String openId2:keys2){
            	Set<String> keys3=WeiXinConst.servicingYqMap.get(openId2).keySet();
            	for(String csId:keys3){
		        	if(csId.equals(sEntity.getId().toString())){
		    			num++;
		    		}
            	}
            }
            Map<String, SysUserEntity> map2=NavMenuInitUtils.getInstance().onLineDeptCsMap.get(sEntity.getDeptId());
//          if(WeiXinConst.onLineCsMap.get(sEntity.getId().toString()) != null&&num<sEntity.getCustomerNum()){
            if(map2!=null&&map2.get(sEntity.getId().toString()) != null&&num<sEntity.getCustomerNum()){
            	sEntity.setIsBusy(0);
            }else{
            	sEntity.setIsBusy(1);
            }
    		//聊天信息提示
    		String hql2="FROM MsgFromCSEntity WHERE fromUser='"+sEntity.getUserId()+"' AND toUser='"+userId+"' AND readed=0";
    		List<MsgFromCSEntity> msgList=sysUserService.findByHql(hql2);
    		sEntity.setReaded(msgList.size());
    	}
        String result = JSON.toJSONString(list); 
    	return result;
    }	
    
    @RequestMapping("/editUsersInfo")
    @ResponseBody
    public void editUsersInfo(String openid,String phone,String redBlack){
    	List<WeixinUserInfoEntity> list=sysUserService.findByProperty(WeixinUserInfoEntity.class, "wxOpenId", openid);
    	WeixinUserInfoEntity entity=list.get(0);
    	entity.setPhone(phone);
    	entity.setRedBlack(redBlack);
    	sysUserService.saveOrUpdate(entity);
    }
    /**
     * 获取全部微信用户消息
     * @return
     */
    @RequestMapping("/getAllUsers")
    @ResponseBody
    public String getAllUsers(int rows,int page,String openid,String nickname,String searchType){
    	List<Object> list = new ArrayList<Object>();
    	StringBuffer sql=new StringBuffer();
    	sql.append(" FROM (");
    	sql.append(" SELECT WX_ID,NICKNAME,PHONE,RED_BLACK,CREATE_TIME,ROWNUM RN FROM (");
    	sql.append(" SELECT  WX_ID,NICKNAME,PHONE,RED_BLACK,MAX(CREATE_TIME) CREATE_TIME");
    	sql.append(" FROM (");
    	sql.append(" SELECT  WX_ID,NICKNAME,PHONE,RED_BLACK,CREATE_TIME");
    	sql.append(" FROM WEIXIN_USER_INFO_T,MSG_FROM_WX_T");
    	sql.append(" WHERE WX_ID = FROM_USER ");
    	if(("Lately").equals(searchType))sql.append(" AND CREATE_TIME>=(SYSDATE-3)");
    	sql.append(" ) A ");
    	sql.append(" WHERE 1=1 ");
    	if(!"".equals(openid)){
    		sql.append(" AND LOWER(WX_ID) LIKE '%"+openid.toLowerCase()+"%'");
    	}
    	if(!"".equals(nickname)){
    		sql.append(" AND LOWER(NICKNAME) LIKE '%"+nickname.toLowerCase()+"%'");
    	}
    	sql.append(" GROUP BY WX_ID,NICKNAME,PHONE,RED_BLACK");
    	sql.append(" ORDER BY CREATE_TIME DESC");
    	sql.append(" ) B ");
    	sql.append(" ) C ");
    	String countSql="SELECT COUNT(*) "+sql.toString();
    	String listSql="SELECT * "+sql.toString()+" WHERE RN BETWEEN "+((page-1)*rows+1)+" AND "+page*rows+" ORDER BY CREATE_TIME DESC";
    	List<Object> userList=msgFromCustomerService.findBySQL(listSql);
//    	Set<String> keys = WeiXinConst.servicingMap.keySet();
    	Set<String> keys = NavMenuInitUtils.getInstance().getServiceMap(openid).keySet();
        for (String openId : keys) {
        	for(int i=0;i<userList.size();i++){
        		Object[] entity=(Object[])userList.get(i);
        		WeixinUserInfoVO vo=new WeixinUserInfoVO();
        		if(openId.equals(entity[0].toString())){
        			vo.setNickname(StringUtil.nullObject2String(entity[1]));
        			vo.setOpenid(StringUtil.nullObject2String(entity[0]));
        			vo.setPhone(StringUtil.nullObject2String(entity[2]));
        			vo.setRedBlack(StringUtil.nullObject2String(entity[3]));
        			vo.setCreateTime(StringUtil.nullObject2String(entity[4]).substring(0,16));
        			vo.setIsOnLine("服务中");
        			list.add(vo);
        			userList.remove(i);
        			i--;
        		}
        	}
        }
//        keys = WeiXinConst.waitingMap.keySet();
        keys = NavMenuInitUtils.getInstance().getWaitMap(openid).keySet();
        for (String openId : keys) {
        	for(int i=0;i<userList.size();i++){
        		Object[] entity=(Object[])userList.get(i);
        		WeixinUserInfoVO vo=new WeixinUserInfoVO();
        		if(openId.equals(entity[0].toString())){
        			vo.setNickname(StringUtil.nullObject2String(entity[1]));
        			vo.setOpenid(StringUtil.nullObject2String(entity[0]));
        			vo.setPhone(StringUtil.nullObject2String(entity[2]));
        			vo.setRedBlack(StringUtil.nullObject2String(entity[3]));
        			vo.setCreateTime(StringUtil.nullObject2String(entity[4]).substring(0,16));
        			vo.setIsOnLine("服务中");
        			list.add(vo);
        			userList.remove(i);
        			i--;
        		}
        	}
        }
        for(int i=0;i<userList.size();i++){
        	Object[] entity=(Object[])userList.get(i);
    		WeixinUserInfoVO vo=new WeixinUserInfoVO();
			vo.setNickname(StringUtil.nullObject2String(entity[1]));
			vo.setOpenid(StringUtil.nullObject2String(entity[0]));
			vo.setPhone(StringUtil.nullObject2String(entity[2]));
			vo.setRedBlack(StringUtil.nullObject2String(entity[3]));
			vo.setCreateTime(StringUtil.nullObject2String(entity[4]).substring(0,16));
			vo.setIsOnLine("已离线");
			list.add(vo);
        }
        Map<String, Object> jsonMap = new HashMap<String, Object>(); 
        List<Object> countList=msgFromCustomerService.findBySQL(countSql);
        jsonMap.put("total", countList.get(0)); 
        jsonMap.put("rows", list);
        String result = JSON.toJSONString(jsonMap); 
    	return result;
    }

    /**
     * 获取历史消息
     * @return
     */
    @RequestMapping("/getHisMsgOneDay")
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
     * 检查用户是否还是在服务的队列中
     *
     * @param openId
     * @return ok：表示还在等待服务的队列中  error:表示不在服务队列中
     */
    @RequestMapping("/getUserStatus")
    @ResponseBody
    public Map<String, Object> getUserStatus(String openId,String csId) {
        Map<String, Object> map = new HashMap<String, Object>();
//        WxStatusTmpTEntity wstEntity = WeiXinConst.servicingMap.get(openId);
        WxStatusTmpTEntity wstEntity = NavMenuInitUtils.getInstance().getServiceEntity(openId);
        
        if (null != wstEntity&&csId.equals(wstEntity.getCsId())) {
        	System.out.println("ok");
        	map.put("status", "OK");
        } else {
            map.put("status", "END");
        }
        return map;
    }
    /**
     * 检查用户是否还是在等待的队列中  为了解决页面中微信用户重名的问题
     *
     * @param openId
     * @return ok：表示还在等待服务的队列中  error:表示不在服务队列中
     */
    @RequestMapping("/chkUserStatus")
    @ResponseBody
    public Map<String, Object> checkWxUserStatus(String openId) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("wxOpenid", openId);
        map1.put("serviceStatus", 1);
        List<WxStatusTmpTEntity> list = wxStatusTmpService.findByMap(WxStatusTmpTEntity.class, map);
        if (null != list && list.size() > 0) {
            map.put("status", "ok");
        } else {
            map.put("status", "error");
        }
        return map;
    }

    /**
     * 根据工单号获取附件列表
     *
     * @param workUnitId
     * @return
     */
    @RequestMapping("/getAttachment")
    @ResponseBody
    public List<Object> getAttachment(String workUnitId) {
        List<Object> list = new ArrayList<Object>();
        if (StringUtil.isEmpty(workUnitId)) {
            return null;
        }
        String workServiceId=workUnitId.substring(0,workUnitId.length()-2);
        String hql = "FROM MsgFromCustomerServiceEntity where workServiceId='"+workServiceId+"' order by id";
        List<MsgFromCustomerServiceEntity> list1 = msgFromCustomerService.findByHql(hql);
        String hql1 = "FROM MsgFromWxEntity where workServiceId='"+workServiceId+"' order by id";
        List<MsgFromWxEntity> list2 = msgFromCustomerService.findByHql(hql1);
        list=ListUtils.getTimeOrderFor2List(list1,list2);
        return list;
    }


    /**
     * 打包附件
     *
     * @param jsonStr 需要打包成附件的座席的消息编号和微信用户发来的消息编号
     *                格式如下:[{"type":"1","workunitId":"5","csMsgIds":"1,2,3","wxMsgIds":"1,2,3"}{...}...]
     *                <p>
     *                **********************************************************
     *                字段说明 *"type"操作类型,1添加成附件  2取消已经添加的附件*
     *                *"workunitId"工单编号                      *
     *                *"csMsgIds"座席发送的消息编号的集合           *
     *                *"wxMsgIds"微信用户发送的消息编号的集合        *
     *                **********************************************************
     * @return
     */
    @RequestMapping("/addAttachment")
    @ResponseBody
    public Map<String, Object> addAttachment(String jsonStr) {
        Map<String, Object> map = new HashMap<String, Object>();
        JSONArray array = JSONObject.parseArray(jsonStr);
        if (null != array) {
            String workunitId = null;
            String csMsgIds = null;
            String[] csMsgIdsArr = null;
            String wxMsgIds = null;
            String[] wxMsgIdsArr = null;
            for (int i = 0; i < array.size(); i++) {
                JSONObject obj = (JSONObject) array.get(i);
                workunitId = obj.get("workunitId").toString();
                csMsgIds = obj.get("csMsgIds").toString();
                if (!StringUtil.isEmpty(csMsgIds)) {
                    csMsgIdsArr = csMsgIds.split(",");
                }
                wxMsgIds = obj.get("wxMsgIds").toString();
                if (!StringUtil.isEmpty(wxMsgIds)) {
                    wxMsgIdsArr = wxMsgIds.split(",");
                }
                if ("1".equals(obj.get("type"))) {
                    //增加附件
                    if (null != csMsgIdsArr) {
                        for (String id : csMsgIdsArr) {
                            MsgFromCustomerServiceEntity entity = msgFromCustomerService.get(MsgFromCustomerServiceEntity.class, Integer.valueOf(id));
                            entity.setWorkUnitId(workunitId);
                            msgFromCustomerService.saveOrUpdate(entity);
                        }
                    }
                    if (null != wxMsgIdsArr) {
                        for (String id : wxMsgIdsArr) {
                            List<MsgFromWxEntity> list = msgFromCustomerService.findByProperty(MsgFromWxEntity.class, "msgId", id);
                            if (null != list && list.size() > 0) {
                                MsgFromWxEntity entity = list.get(0);
                                entity.setWorkUnitNum(workunitId);
                                msgFromCustomerService.saveOrUpdate(entity);
                            }
                        }
                    }
                } else if ("2".equals(obj.get("type"))) {
                    //删除附件
                    if (null != csMsgIdsArr) {
                        for (String id : csMsgIdsArr) {
                            MsgFromCustomerServiceEntity entity = msgFromCustomerService.get(MsgFromCustomerServiceEntity.class, Integer.valueOf(id));
                            entity.setWorkUnitId(null);
                            msgFromCustomerService.saveOrUpdate(entity);
                        }
                    }
                    if (null != wxMsgIdsArr) {
                        for (String id : wxMsgIdsArr) {
                            List<MsgFromWxEntity> list = msgFromCustomerService.findByProperty(MsgFromWxEntity.class, "msgId", id);
                            if (null != list && list.size() > 0) {
                                MsgFromWxEntity entity = list.get(0);
                                entity.setWorkUnitNum(null);
                                msgFromCustomerService.saveOrUpdate(entity);
                            }
                        }
                    }
                }
            }
        }
        map.put("status", "OK");
        return map;
    }
    @RequestMapping("/addServiceId")
    @ResponseBody
    public Map<String, Object> addServiceId(String msgId,String serviceId) {
        Map<String, Object> map = new HashMap<String, Object>();
        MsgFromWxEntity entity = msgFromCustomerService.get(MsgFromWxEntity.class, Integer.valueOf(msgId));
        entity.setWorkServiceId(serviceId);
        msgFromCustomerService.saveOrUpdate(entity);
        map.put("status", "OK");
        return map;
    }
    /**
     * 获取挂起工单
     *
     * @param openId 　微信用户的openID
     * @throws Exception
     * @return　 json格式的字符串数組
     */
    @RequestMapping(value = "/getWorkUnitSuspended", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getWorkUnitSuspended(String openId) throws Exception {
        Connection conn = null;
        PreparedStatement pre = null;
        ResultSet resultSet = null;
        //去掉前边4位
        openId = openId.substring(4, openId.length() - 1);
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String jdbcUrl = PropertiesUtil.getProperty("dbconfig.properties", "jdbc.url");
            String jdbcUsername = PropertiesUtil.getProperty("dbconfig.properties", "jdbc.username");
            String jdbcPassword = PropertiesUtil.getProperty("dbconfig.properties", "jdbc.password");
            conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
            String sql = " select t1.* from  ALARM_GQ@ORCL180.REGRESS.RDBMS.DEV.US.ORACLE.COM  t1 where t1.COMPUTERID like ? ";
            pre = conn.prepareStatement(sql);
            pre.setString(1, openId + "%");
            resultSet = pre.executeQuery();
            String tmp = FastJsonUtil.resultSetToJson(resultSet);
            return tmp;
        } finally {
            try {
                if (null != resultSet) {
                    resultSet.close();
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
    }


    /**
     * 获取历史工单
     *
     * @param openId 　微信用户的openID
     * @param start  分页数据的开始
     * @param end    　　　分页数据的结束
     * @throws Exception
     * @return　 json格式的字符串数組
     */
    @RequestMapping(value = "/getWorkUnitHis", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getWorkUnitHis(String openId, int start, int end) throws Exception {
        Connection conn = null;
        PreparedStatement pre = null;
        ResultSet resultSet = null;
        //去掉前边4位
        openId = openId.substring(4, openId.length());
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String jdbcUrl = PropertiesUtil.getProperty("dbconfig.properties", "jdbc.url");
            String jdbcUsername = PropertiesUtil.getProperty("dbconfig.properties", "jdbc.username");
            String jdbcPassword = PropertiesUtil.getProperty("dbconfig.properties", "jdbc.password");
            conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
            String sql = "select t2.* from \n" +
                    " (select ROWNUM as rowno,t1.* from HNII_ALARM_SERVICE@ORCL180.REGRESS.RDBMS.DEV.US.ORACLE.COM  t1 where t1.COMPUTERID like ? )  t2  \n" +
                    " where t2.rowno BETWEEN ? and ? ";
            pre = conn.prepareStatement(sql);
            pre.setString(1, openId + "%");
            pre.setInt(2, start);
            pre.setInt(3, end);
            resultSet = pre.executeQuery();
            String tmp = FastJsonUtil.resultSetToJson(resultSet);
            return tmp;
        } finally {
            try {
                if (null != resultSet) {
                    resultSet.close();
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
    }

    /**
     * 获取系统常用语
     * @return
     */
    private String querySystemMsg(){
    	String result = new String();
    	List<UsuallyWordEntity> uwList=sysUserService.findByProperty(UsuallyWordEntity.class, "isSystem", 1);
    	if(uwList != null && uwList.size() > 0){
    		for (UsuallyWordEntity uw : uwList) {
				result += uw.getUsuallyWords() + ",";
			}
    	}
    	return result;
    }
    
    /**
     * 保存座席发送的信息到数据庫
     *
     * @param entity
     * @return
     */
    @RequestMapping("/saveMsgFromCS")
    @ResponseBody
    public MsgFromCustomerServiceEntity saveMsgFromCustomerServiceEntity(MsgFromCustomerServiceEntity entity) {
        try {
//            WxStatusTmpTEntity wstEntity = WeiXinConst.servicingMap.get(entity.getToUser());
            WxStatusTmpTEntity wstEntity = NavMenuInitUtils.getInstance().getServiceEntity(entity.getToUser());
            if(null == wstEntity){//判断是否断开服务
            	entity.setId(0);
                return  entity;
            }
            entity.setCreateTime(new Timestamp(System.currentTimeMillis()));
            //TODO:常用语不计时
            boolean isTimes = querySystemMsg().contains(entity.getMsgContent());
            if(wstEntity.getSendType() == 1 && !isTimes){  //记录用户发送消息的时间，如果座席未回复消息，则不计时
            	wstEntity.setServiceOffHintNum(0);//重置座席退出时发送提示的次数
            	wstEntity.setSendType(0);
            	wstEntity.setIsInitiative(0);
            	wstEntity.setTwoSecFlag(false);
            	wstEntity.setChatOvertime(System.currentTimeMillis() / 1000);
            }
            entity.setIsSuccess(0);
            msgFromCustomerService.save(entity);
        } catch (Exception e) {
        	e.printStackTrace();
            logger.info(e.getMessage());
        }
        sendMsgToCSFromCS(entity);
        return entity;
    }

    public void sendMsgToCSFromCS(MsgFromCustomerServiceEntity entity){
    	//发送给被邀请者
        if(WeiXinConst.servicingYqMap.get(entity.getToUser())!=null){
        	Map<String,String> map2=WeiXinConst.servicingYqMap.get(entity.getToUser());
        	Set<String> keys = map2.keySet();
    			for (String key : keys) {
        		WebSocketSession session=WeiXinConst.webSocketSessionMap.get(map2.get(key));
        		try {
        			Map<String,Object> map=new HashMap<String,Object>();
        			map.put("data", entity);
        			map.put("Flag","CsToCs");
					if(session!=null)session.sendMessage(new TextMessage(JSON.toJSONString(map)));
				} catch (Exception e) {
					e.printStackTrace();
				}
        	}
        }
        //发送给邀请者
//    	WxStatusTmpTEntity tEntity=WeiXinConst.servicingMap.get(entity.getToUser());
    	WxStatusTmpTEntity tEntity=NavMenuInitUtils.getInstance().getServiceEntity(entity.getToUser());
        if(tEntity!=null){
        	WebSocketSession session=WeiXinConst.webSocketSessionMap.get(tEntity.getSessionId());
        	try {
    			Map<String,Object> map=new HashMap<String,Object>();
    			map.put("data", entity);
    			map.put("Flag","CsToCs");
				if(session!=null)session.sendMessage(new TextMessage(JSON.toJSONString(map)));
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
    }
    /**
     * 根据部门编号获取部门下边的的所有员工
     *
     * @param id 部门编号
     * @return
     */
    @RequestMapping("/getDepartAllEmp/{id}")
    @ResponseBody
    public List<SysUserEntity> getDepartAllEmp(@PathVariable("id") int id) {
        List<SysUserEntity> list = sysUserService.findByProperty(SysUserEntity.class, "deptId", id);
        return list;
    }

    /**
     * 根据部门编号获取部门信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/getDepartById/{id}")
    @ResponseBody
    public DepartEntity getDepartById(@PathVariable("id") int id) {
        DepartEntity entity = departService.get(DepartEntity.class, id);
        return entity;
    }

    /**
     * 获取所有部门信息
     *
     * @return
     */
    @RequestMapping("/getAllDepart")
    @ResponseBody
    public List<DepartEntity> getAllDepart() {
        List<DepartEntity> list = departService.loadAll(DepartEntity.class);
        return list;
    }

    /**
     * 修改座席能同时跳转数
     *
     * @param csId 座席编号
     * @param num  座席能同时接待的微信人数
     */
    @RequestMapping("/updateChangNum/{csid}")
    @ResponseBody
    public Map<String, Object> updateCsWxChg(@PathVariable("csid") int csId, int num) {
        Map<String, Object> map = new HashMap<String, Object>();
        SysUserEntity entity = sysUserService.get(SysUserEntity.class, csId);
        entity.setChangeNum(num);
        sysUserService.saveOrUpdate(entity);
        map.put("status", "OK");
        return map;
    }

    /**
     * 修改座席能同时接待的微信用户数
     *
     * @param csId 座席编号
     * @param num  座席能同时接待的微信人数
     */
    @RequestMapping("/updateCsWxNum/{csid}")
    @ResponseBody
    public Map<String, Object> updateCsWxNum(@PathVariable("csid") int csId, int num) {
        Map<String, Object> map = new HashMap<String, Object>();
        SysUserEntity entity = sysUserService.get(SysUserEntity.class, csId);
        entity.setCustomerNum(num);
        sysUserService.saveOrUpdate(entity);
        SysUserEntity user = NavMenuInitUtils.getInstance().getSysUserCsEntity(""+csId);
        if(user != null){
        	user.setCustomerNum(num);
        }
        map.put("status", "OK");
        return map;
    }

    /**
     * 修改座席常用语
     *
     * @param csId 座席编号
     * @param word  座席常用语
     */
    @RequestMapping("/updateUsuallyWord")
    @ResponseBody
    public Map<String, Object> updateUsuallyWord(int id,String word,int isSystem,String title,int type) {
        Map<String, Object> map = new HashMap<String, Object>();
        UsuallyWordEntity entity = sysUserService.get(UsuallyWordEntity.class, id);
        entity.setUsuallyWords(word);
        entity.setIsSystem(isSystem);
        entity.setTitle(title);
        entity.setType(type);
        sysUserService.saveOrUpdate(entity);
        map.put("status", "OK");
        return map;
    }
    /**
     * 删除座席常用语
     *
     * @param csId 座席编号
     * @param word  座席常用语
     */
    @RequestMapping("/delUsuallyWord")
    @ResponseBody
    public Map<String, Object> delUsuallyWord(int id) {
        Map<String, Object> map = new HashMap<String, Object>();
        sysUserService.deleteEntityById(UsuallyWordEntity.class, id);
        map.put("status", "OK");
        return map;
    }
    @RequestMapping("/getUsuallyWords")
    @ResponseBody
    public List<UsuallyWordEntity> getUsuallyWords(int csId) {
    	List<UsuallyWordEntity> uwList=new ArrayList<UsuallyWordEntity>();
    	uwList=sysUserService.findByProperty(UsuallyWordEntity.class, "creator", csId+"");
        return uwList;
    }

    @RequestMapping("/selectUsuallyWords")
    @ResponseBody
    public List<UsuallyWordEntity> selectUsuallyWords(int csId) {
    	List<UsuallyWordEntity> uwList=new ArrayList<UsuallyWordEntity>();
    	//查询出系统管理员定义常用语
    	uwList=sysUserService.findByProperty(UsuallyWordEntity.class, "creator", "1");//系统的管理员
    	if(csId!=1){
    		//查询出分中心管理员定义常用语
    		List<SysUserEntity> sysUserList = sysUserService.findByProperty(SysUserEntity.class, "id", csId); //部门管理员
    		int userId=0;
    		if(sysUserList!=null){
    			SysUserEntity s=sysUserList.get(0);
    			int deptId=s.getDeptId();
    			userId=s.getId().intValue();
    			List<SysUserRoleEntity> sysUserRoleList=sysUserService.findByProperty(SysUserRoleEntity.class, "deptId", deptId);
    			if(sysUserRoleList!=null){
    				SysUserRoleEntity sysUserRoleEntity=sysUserRoleList.get(0);
    				int adminUserId=sysUserRoleEntity.getUserId();
    	        	List<UsuallyWordEntity> uwList2=new ArrayList<UsuallyWordEntity>();
    	        	uwList2=sysUserService.findByProperty(UsuallyWordEntity.class, "creator", adminUserId+"");
    	        	for(int i=0;i<uwList2.size();i++){
    	        		uwList.add(uwList2.get(i));
    	        	}
    			}
    		}
    		if(userId==csId){
        		//查询出自己定义常用语
            	List<UsuallyWordEntity> uwList3=new ArrayList<UsuallyWordEntity>();
            	uwList3=sysUserService.findByProperty(UsuallyWordEntity.class, "creator", csId+"");
            	for(int i=0;i<uwList3.size();i++){
            		uwList.add(uwList3.get(i));
            	}
    		}
    	}
        return uwList;
    }
    /**
     * 保存常用语类型
     * 
     * @param deptId  部门ID
     * @param name    类型名称
     * @return
     */
    @RequestMapping("/saveUsuallyWordType")
    @ResponseBody
    public Map<String, Object> saveUsuallyWordType(int deptId,String name) {
        Map<String, Object> map = new HashMap<String, Object>();
        UsuallyWordTypeEntity entity = new UsuallyWordTypeEntity();
        entity.setDeptId(deptId);
        entity.setName(name);
        try {
        	sysUserService.saveOrUpdate(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
        map.put("status", "OK");
        return map;
    }
    @RequestMapping("/getUsuallyWordType")
    @ResponseBody
    public List<UsuallyWordTypeEntity> getUsuallyWordType(int deptId) {
    	List<UsuallyWordTypeEntity> uwList=new ArrayList<UsuallyWordTypeEntity>();
    	uwList=sysUserService.findByProperty(UsuallyWordTypeEntity.class, "deptId", deptId);
    	List<UsuallyWordTypeEntity> uwList2=sysUserService.findByProperty(UsuallyWordTypeEntity.class, "deptId", 4);
    	Iterator<UsuallyWordTypeEntity> it=uwList2.iterator();
    	while(it.hasNext()){
    		uwList.add(it.next());
    	}
        return uwList;
    }
    /**
     * 保存座席常用语
     *
     * @param csId 坐席ID
     * @param word 常用语
     * @param isSystem 是否系统常用语
     * @param title 标题
     * @return
     */
    @RequestMapping("/saveUsuallyWord")
    @ResponseBody
    public Map<String, Object> saveUsuallyWord(int csId,String word,int isSystem,String title,int type) {
        Map<String, Object> map = new HashMap<String, Object>();
        UsuallyWordEntity entity = new UsuallyWordEntity();
        entity.setUsuallyWords(word);
        entity.setCreator(csId+"");
        entity.setIsSystem(isSystem);
        entity.setTitle(title);
        entity.setType(type);
        entity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        sysUserService.saveOrUpdate(entity);
        map.put("status", "OK");
        return map;
    }
    
    @RequestMapping("/saveNotice")
    @ResponseBody
    public Map<String, Object> saveNotice(String title,String content,String beginTime,String endTime,int deptId) {
        Map<String, Object> map = new HashMap<String, Object>();
        NoticeEntity entity = new NoticeEntity();
        entity.setBeginTime(Timestamp.valueOf(beginTime+" 00:00:00"));
        entity.setEndTime(Timestamp.valueOf(endTime+" 00:00:00"));
        entity.setContent(content);
        entity.setTitle(title);
        entity.setDeleted(0);
        entity.setDeptId(deptId);
        sysUserService.saveOrUpdate(entity);
        map.put("status", "OK");
        return map;
    }
    @RequestMapping("/editNotice")
    @ResponseBody
    public Map<String, Object> editNotice(String id,String title,String content,String beginTime,String endTime) {
        Map<String, Object> map = new HashMap<String, Object>();
        NoticeEntity entity = new NoticeEntity();
        entity.setId(new Integer(id));
        entity.setBeginTime(Timestamp.valueOf(beginTime+" 00:00:00"));
        entity.setEndTime(Timestamp.valueOf(endTime+" 00:00:00"));
        entity.setContent(content);
        entity.setTitle(title);
        entity.setDeleted(0);
        sysUserService.saveOrUpdate(entity);
        map.put("status", "OK");
        return map;
    }
    @RequestMapping("/delNotice")
    @ResponseBody
    public Map<String, Object> delNotice(String id) {
        Map<String, Object> map = new HashMap<String, Object>();
        NoticeEntity entity = new NoticeEntity();
        entity.setId(new Integer(id));
        sysUserService.delete(entity);
        map.put("status", "OK");
        return map;
    }
    @RequestMapping("/getNotice")
    @ResponseBody
    public List<NoticeEntity> getNotice(int deptId) {
    	List<NoticeEntity> nList=new ArrayList<NoticeEntity>();
    	String nowTime=new Timestamp(System.currentTimeMillis()).toString();
        String hql = "FROM NoticeEntity where deptId in(4,"+deptId+") and beginTime<=to_timestamp('"+nowTime+"','yyyy-mm-dd hh24:mi:ss:ff') and endTime>=to_timestamp('"+nowTime+"','yyyy-mm-dd hh24:mi:ss:ff') order by endTime desc";
        nList = sysUserService.findByHql(hql);
        return nList;
    }
    /**
     * 结束服务
     *
     * @param csId     座席编号
     * @param wxOpenId 微信的openId
     */
    @RequestMapping("/endServicing/{csid}/{wxOpenId}")
    @ResponseBody
    public Map<String, Object> closeServicing(@PathVariable("csid") int csId,
                                              @PathVariable("wxOpenId") String wxOpenId) {
    	
        Map<String, Object> map1 = new HashMap<String, Object>();
        SysUserEntity userEntity = sysUserService.get(SysUserEntity.class, csId);
        //关闭服务状态
//        WxStatusTmpTEntity entity = WeiXinConst.servicingMap.get(wxOpenId);

        WxStatusTmpTEntity entity = NavMenuInitUtils.getInstance().getServiceEntity(wxOpenId);
//		Integer deptID = NavMenuInitUtils.getInstance().userDeptMap.get(wxOpenId); // 加入的
		String publiicID = NavMenuInitUtils.getInstance().userPublicIdMap.get(wxOpenId);
        if(null == entity){
        	Map<String,String> map2=WeiXinConst.servicingYqMap.get(wxOpenId);
        	if(map2!=null){
                WeiXinConst.webSocketSessionMap.remove(map2.get(csId+""));
            	map2.remove(csId+"");
        	}
            map1.put("status", "END");
            return  map1;
        }else if(!entity.getCsId().equals(csId+"")){//群聊退出
        	Map<String,String> map2=WeiXinConst.servicingYqMap.get(wxOpenId);
        	if(map2!=null){
                WeiXinConst.webSocketSessionMap.remove(map2.get(csId+""));
            	map2.remove(csId+"");
        	}
        	map1.put("status", "END");
            return  map1;
        }else{
        	//删除自己的webSocketSessionMap
            String sessionId = entity.getSessionId();
            if (!StringUtil.isEmpty(sessionId)) {
                WeiXinConst.webSocketSessionMap.remove(sessionId);
            }
            //删除邀请的webSocketSessionMap
            Map<String,String> map2=WeiXinConst.servicingYqMap.get(wxOpenId);
            if(map2!=null){
                Set<String> keys=map2.keySet();
                for(String key:keys){
                	WeiXinConst.webSocketSessionMap.remove(map2.get(key));
                }
            }
            //删除邀请的servicingYqMap
            WeiXinConst.servicingYqMap.remove(wxOpenId);
            //删除servicingMap
//            WeiXinConst.servicingMap.remove(wxOpenId);
//            NavMenuInitUtils.getInstance().removeServiceMap(wxOpenId); 标记
            entity.setServiceStatus(3);

            entity.setLastChatTime(System.currentTimeMillis()/1000);
//            WeiXinConst.waitingMap.remove(wxOpenId);
            //????????????????????//
            ConcurrentMap<String, Integer> userD = NavMenuInitUtils.getInstance().userDeptMap;
            Integer deptId = userD.get(csId+"");
            
            NavMenuInitUtils.getInstance().removeWaitMap(deptId,wxOpenId);
            NavMenuInitUtils.getInstance().removeServiceMap(deptId,wxOpenId);
            if(entity.getIsInitiative() == 1){
            	//主动拉人不发送消息
            } else {
//            	WeiXinConst.deletedMap.put(wxOpenId, entity);
            	 NavMenuInitUtils.getInstance().putRemoveMap(wxOpenId, entity);
                 String jsonContent = String.format("{\"touser\":\"%s\",\"msgtype\":\"text\",\"text\":{\"content\":\"%s\"}}",
                         wxOpenId, "感谢您对重庆交通的支持，刚才为您服务的是"+userEntity.getUserId()+"号座席，请您为她的服务评分。\n" +
                                 "【1】非常满意 \n" +
                                 "【2】满意 \n" +
                                 "【3】不满意");
                 
                 String returnStr = "";
                 if(!wxOpenId.subSequence(0, 3).equals("app")){
                	  returnStr = WeiXinOperUtil.sendMsgToWx(WeiXinOperUtil.getAccessToken(publiicID), jsonContent);// 获取对应accessToken  已改
                 }
                 wxStatusTmpService.saveMsgDatebase(entity, "感谢您对重庆交通的支持，刚才为您服务的是"+userEntity.getUserId()+"号座席，请您为她的服务评分。\n" +
                         "【1】非常满意 \n" +
                         "【2】满意 \n" +
                         "【3】不满意",  entity.getWxOpenid());
                 logger.info(returnStr);
            }
           

            List<CustomerServiceHisEntity> list = customerServiceHisService.findByProperty(CustomerServiceHisEntity.class,"sessionId",entity.getMyTimestamp());
            if(null != list && list.size()>0){
                CustomerServiceHisEntity hisEntity = list.get(0);
                hisEntity.setEndTime(new Timestamp(System.currentTimeMillis()));
                hisEntity.setServiceStatus(0);
                customerServiceHisService.saveOrUpdate(hisEntity);
            }

            //WeiXinConst.onLineCsMap.remove(csId);
            map1.put("status", "OK");
            return map1;
        }
    }

    /**
     * 获取座席的基本信息
     *
     * @param id 座席编号
     * @return
     */
    @RequestMapping("/getCsUserInfoById/{id}")
    @ResponseBody
    public SysUserEntity getSysUserInfo(@PathVariable("id") int id) {
        SysUserEntity userEntity = sysUserService.get(SysUserEntity.class, id);
        DepartEntity departEntity = departService.get(DepartEntity.class, userEntity.getDeptId());
        if (null != departEntity)
            userEntity.setDeptName(departEntity.getDwqc());
        return userEntity;
    }

    /**
     * 获取座席的基本信息
     *
     * @param name 座席名称
     * @return
     */
    @RequestMapping("/getCsUserInfoByName/{name}")
    @ResponseBody
    public SysUserEntity getSysUserInfoByName(@PathVariable("name") String name) {
        List<SysUserEntity> list = sysUserService.findByProperty(SysUserEntity.class, "userName", name);
        SysUserEntity entity = new SysUserEntity();
        if (null != list && list.size() > 0) {
            entity = list.get(0);
            DepartEntity departEntity = departService.get(DepartEntity.class, entity.getDeptId());
            if (null != departEntity)
                entity.setDeptName(departEntity.getDwqc());
        }
        return entity;
    }

    /**
     * 获取座席的状态
     *
     * @param id
     * @return 返回一个工作的百分比
     */
    @RequestMapping("/getCsUserStatus/{id}")
    @ResponseBody
    public Map<String, String> getSysUserStatus(@PathVariable("id") int id) {
        Map<String, String> map = new HashMap<String, String>();
        SysUserEntity userEntity = sysUserService.get(SysUserEntity.class, id);

        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("csId", id);
        map1.put("serviceStatus", 1);
        List<CustomerServiceHisEntity> list = customerServiceHisService.findByMap(CustomerServiceHisEntity.class, map1);
        int workingStatus = list.size() / (userEntity.getCustomerNum().intValue()) * 100;
        map.put("workingStatus", workingStatus + "%");
        return map;
    }


    /**
     * 获取所有等待服务的微信用户
     *seatId 对应华南ID 1005
     * @return
     */
    @RequestMapping("/getAllWaitWx")
    @ResponseBody
    public Map<String,Object> getWaitWx(HttpServletRequest request) {
        String seatId = request.getParameter("seatId");
        Map<String,Object> map=new HashMap<String,Object>();
//        map.put("userNum",WeiXinConst.waitingMap.size()+"");
//        map.put("sysNum",WeiXinConst.onLineCsMap.size()+"");
        
        String allReceiveNum = "0";
        int receiveNum = 0;
        int seatState= 0;
        
        int userNum = 0;//微信等待数
        int sysNum = 0;//座席在线数
        if(StringUtil.isNotEmpty(seatId)){
        	List<SysUserEntity> list = sysUserService.findByProperty(SysUserEntity.class, "userId", seatId);
        	if(list != null && list.size() > 0){
        		SysUserEntity user  = list.get(0);
            	if(user != null){
            		userNum = NavMenuInitUtils.getInstance().getWaitMap(""+user.getId()).size();
            		sysNum = NavMenuInitUtils.getInstance().getOnLineDeptCsMap(""+user.getId()).size();
            		allReceiveNum = user.getCustomerNum() +"";
//            		Set<String> keys = WeiXinConst.servicingMap.keySet();
            		Set<String> keys = NavMenuInitUtils.getInstance().getServiceMap(""+user.getId()).keySet();
            		if(keys !=null && keys.size() > 0){
            			for (String key : keys) {
//            				WxStatusTmpTEntity tmp = WeiXinConst.servicingMap.get(key);
            				WxStatusTmpTEntity tmp = NavMenuInitUtils.getInstance().getServiceEntity(key);
            				if(tmp.getServiceStatus() == 2 && key.equals(tmp.getCsId())){
            					receiveNum += 1;
            				}
    					}
            		}
//            		if(WeiXinConst.onLineCsMap.get(""+user.getId()) != null){
            		if(NavMenuInitUtils.getInstance().getSysUserCsEntity(""+user.getId()) != null){
                		seatState = 1;
                	}
            	}
        	}
        }
        map.put("userNum",userNum);
        map.put("sysNum",sysNum);
        map.put("allReceiveNum",allReceiveNum);
        map.put("receiveNum",receiveNum);
        map.put("seatState", seatState);
        return map;
    }
    
    @RequestMapping("/transferUserBegin")
    @ResponseBody
    public String transferUserBegin(String seatId,String csId,String openId) {
        Map<String,String> map=new HashMap<String,String>();
    	WeiXinConst.transferingUserMap.put(openId,seatId);
    	WeiXinConst.transferUserMap.put(csId, openId);
		map.put("status", "OK");
		return JSON.toJSONString(map);
    }

    @RequestMapping("/inviteUserBegin")
    @ResponseBody
    public String inviteUserBegin(String csId,String openId) {
    	WeiXinConst.inviteUserMap.put(csId, openId);
        Map<String,String> map=new HashMap<String,String>();
    	if(WeiXinConst.servicingYqMap.get(openId)==null){
    		Map<String,String> map2=new HashMap<String,String>();
    		WeiXinConst.servicingYqMap.put(openId, map2);
    	}
		map.put("status", "OK");
		return JSON.toJSONString(map);
    }
    @RequestMapping("/transferUserEnd")
    @ResponseBody
    public String transferUserEnd(String csId,String typeName) {
        Map<String,String> map=new HashMap<String,String>();
    	WeiXinConst.transferUserMap.remove(csId);
		map.put("status", "OK");
		return JSON.toJSONString(map);
    }

    @RequestMapping("/getOnLineCsInfo")
    @ResponseBody
    public String getOnLineCsInfo(String userId) {
        List<SysUserEntity> list=new ArrayList<SysUserEntity>();
//        Set<String> keys = WeiXinConst.onLineCsMap.keySet();
        Set<String> keys = NavMenuInitUtils.getInstance().getOnLineDeptCsMap(userId).keySet();
    	List<SysUserEntity> list1 = new ArrayList<SysUserEntity>();
    	String hql="FROM SysUserEntity WHERE userId ='"+userId+"'";
    	list1=sysUserService.findByHql(hql);
    	int deptId=list1.get(0).getDeptId();
		for(String seatId:keys){
//			SysUserEntity entity=WeiXinConst.onLineCsMap.get(seatId);
			SysUserEntity entity=NavMenuInitUtils.getInstance().getSysUserCsEntity(seatId);
			if(entity.getDeptId().intValue()==deptId||entity.getUserId().equals("admin")){//同一单位
		    	WxStatusTmpTEntity wentity=new WxStatusTmpTEntity();
	    		int num=0;
//	    		Set<String> keys4 = WeiXinConst.servicingMap.keySet();
	            Set<String> keys4 = NavMenuInitUtils.getInstance().getServiceMap(seatId).keySet();
	        	for (String openId : keys4) {
//	        		wentity = WeiXinConst.servicingMap.get(openId);
	        		wentity = NavMenuInitUtils.getInstance().getServiceEntity(openId);
	        		if(wentity.getCsId().equals(seatId)){
	        			num++;
	        		}
	            }
	        	Set<String> keys2=WeiXinConst.servicingYqMap.keySet();
	            for(String openId2:keys2){
	            	Set<String> keys3=WeiXinConst.servicingYqMap.get(openId2).keySet();
	            	for(String csId:keys3){
			        	if(csId.equals(seatId)){
			    			num++;
			    		}
	            	}
	            }
	            entity.setChangeNum(num);
				list.add(entity);
			}
		}
		return JSON.toJSONString(list);
    }
    
    @RequestMapping("/sendBeatStatus")
    @ResponseBody
    public void sendBeatStatus(String seatId,String status) {
//    	SysUserEntity sysUser = WeiXinConst.onLineCsMap.get(seatId);
		SysUserEntity sysUser = NavMenuInitUtils.getInstance().getSysUserCsEntity(seatId);
		if(sysUser==null){
	        SysUserEntity entity = sysUserService.get(SysUserEntity.class, new Integer(seatId));
	        entity.setStatus(status);
//	        WeiXinConst.onLineCsMap.put(String.valueOf(entity.getId()),entity);
	        NavMenuInitUtils.getInstance().putOnLineDeptCsMap(String.valueOf(entity.getId()),entity);
		}else{
			sysUser.setStatus(status);
		}
    }
    /**
     * sendBeat
     *转交用户
     * @return
     */
    @RequestMapping("/sendBeat")
    @ResponseBody
    public String sendBeat(HttpServletRequest request) {
        Map<String,String> map=new HashMap<String,String>();
    	String seatId = request.getParameter("seatId");
//    	SysUserEntity sysUser = WeiXinConst.onLineCsMap.get(seatId);
		SysUserEntity sysUser = NavMenuInitUtils.getInstance().getSysUserCsEntity(seatId);
		if(sysUser==null){
	        SysUserEntity entity = sysUserService.get(SysUserEntity.class, new Integer(seatId));
//	        WeiXinConst.onLineCsMap.put(String.valueOf(entity.getId()),entity);
	        if(entity != null){
	        	sysUser = entity;
	        	NavMenuInitUtils.getInstance().userDeptMap.put(entity.getId() + "", entity.getDeptId());
	        	NavMenuInitUtils.getInstance().putOnLineDeptCsMap(String.valueOf(entity.getId()),entity);
	        } else {
	        	sysUser = new SysUserEntity();
	        }
		}
		sysUser.setHeatTimes(System.currentTimeMillis());
		String openId=WeiXinConst.transferUserMap.get(seatId);
    	if(openId!=null){
    		map.put("status", "Transfer");
    		map.put("openId", openId);
    		WeiXinConst.transferedUserMap.put(openId,WeiXinConst.transferingUserMap.get(openId));
    		WeiXinConst.transferingUserMap.remove(openId);
        	WeiXinConst.transferUserMap.remove(seatId);
    		//System.out.println("keys.size=========================="+WeiXinConst.transferUserMap.get(seatId));
        	//System.out.println("keys.size=========================="+WeiXinConst.transferedUserMap.keySet().size());
    	}else if(WeiXinConst.inviteUserMap.get(seatId)!=null){
    		map.put("status", "Invite");
    		map.put("openId", WeiXinConst.inviteUserMap.get(seatId));
    		WeiXinConst.inviteUserMap.remove(seatId);
    	}else{
    		map.put("status", "NO");
    	}
    	map.put("wxOpenId", "");
    	Set<String> keys=WeiXinConst.transferedUserMap.keySet();
    	for (String wxOpenId : keys) {
    	    if(seatId.equals(WeiXinConst.transferedUserMap.get(wxOpenId))){
        		map.put("wxOpenId", wxOpenId);	
            	WeiXinConst.transferedUserMap.remove(wxOpenId);
        	}
        }
		//聊天信息提示
		String sql="SELECT * FROM MSG_FROM_CS_T WHERE TO_USER='ALL' AND CREATE_TIME>(SELECT MAX(CREATE_TIME) FROM MSG_FROM_CS_T WHERE FROM_USER='"+sysUser.getUserId()+"')";
		List<Object> list=sysUserService.findBySQL(sql);
		map.put("msgNum", list.size()+"");
    	return JSON.toJSONString(map);
    }

    /**
     * 根据openId返回微信用户的详细信息
     *
     * @param openId 微信消息返回FromUserName
     * @return
     */
    @RequestMapping("/getUserInfo")
    @ResponseBody
    public String getUserInfo(String openId) {
    	String  publicID = NavMenuInitUtils.getInstance().userPublicIdMap.get(openId);
//    	Integer deptID = NavMenuInitUtils.getInstance().userDeptMap.get(openId); // 加入的
        return WeiXinOperUtil.getUserInfo(WeiXinOperUtil.getAccessToken(publicID), openId);// 需要修改的地方，需要获取对应的AccessToken
    }
    /**
     * 选择分配微信用户给座席
     *
     * @param openId  微信用户
     * @param csId 座席编号
     * @return 1、如果openId在服务队列中，返回空列表
     *         2、按照正常流程返回
     */
    @RequestMapping("/reAllocateWxUser")
    @ResponseBody
    public List<String> reAllocateWxUser(HttpServletRequest request,int csId, String openId,String typeName) throws Exception{
        List<String> returnList = new ArrayList<String>();
//        WxStatusTmpTEntity entity =  WeiXinConst.servicingMap.get(openId);
        WxStatusTmpTEntity entity =  NavMenuInitUtils.getInstance().getServiceEntity(openId);

        String publicID = NavMenuInitUtils.getInstance().userPublicIdMap.get(openId);
//        Integer deptID = NavMenuInitUtils.getInstance().userDeptMap.get(openId); // 加入的
        if(entity ==null||"Transfer".equals(typeName)||"Invite".equals(typeName)){
        	//在服务队列中，返回空
        	String userInfo = WeiXinOperUtil.getUserInfo(WeiXinOperUtil.getAccessToken(publicID), openId);// 需要获取对应的AccessToken　已改
        	if(StringUtil.isNotEmpty(userInfo) && !userInfo.contains("errcode")){
        		entity = new WxStatusTmpTEntity();
            	entity.setWxOpenid(openId);
            	entity.setCreateTime(new Timestamp(System.currentTimeMillis()));
            	entity.setServiceStatus(1);
                entity.setCsId(String.valueOf(csId));
                entity.setAllocTime(System.currentTimeMillis());//设置分配时间
                entity.setIntoWaitingMapTime(System.currentTimeMillis()/1000);//重置等待时间
                entity.setWaitingHintNum(0);//重置等待发送消息次数
            	if(!"Invite".equals(typeName)){
	            	List<SysUserEntity> sysUserList = sysUserService.findByProperty(SysUserEntity.class, "id", csId);
	        		if(sysUserList!=null){
	        			SysUserEntity s=sysUserList.get(0);
	        			int deptId=s.getDeptId();
	        			NavMenuInitUtils.getInstance().userDeptMap.put(openId, deptId);
	        		}
//            		WeiXinConst.servicingMap.put(openId, entity);
            		NavMenuInitUtils.getInstance().putServiceMap(openId, entity);
            	}
//            	WeiXinConst.waitingMap.remove(openId);
//            	WeiXinConst.deletedMap.remove(openId);
            	NavMenuInitUtils.getInstance().removeWaitMap(openId);
            	NavMenuInitUtils.getInstance().removeRemoveMap(openId);
            	returnList.add(userInfo);
        	}       	
        }
        //returnList.add("{\"city\":null,\"country\":\"中国\",\"groupId\":\"0\",\"headImgUrl\":\"\",\"id\":3,\"name\":null,\"nickname\":\"reborn\",\"province\":\"重庆\",\"remark\":null,\"sex\":\"1\",\"subscribeTime\":\"2015-12-21 17:12:29\",\"subscribleTimes\":2,\"tel\":null,\"unsubscribeTime\":\"2015-12-21 17:12:16\",\"userStatus\":1,\"openid\":\"oPxXujmpaI1Q-c1OFIDl-nIvpUCA\"}");
        return headImgUrlInList(request,returnList);
   }

    @RequestMapping("/getUsersInfo")
    @ResponseBody
    private List<String> getUsersInfo(String openId){
    	List<String> returnList = new ArrayList<String>();
//    	WxStatusTmpTEntity entity=WeiXinConst.servicingMap.get(openId);
    	WxStatusTmpTEntity entity=NavMenuInitUtils.getInstance().getServiceEntity(openId);
    	returnList.add(entity.getCsId());
    	if(WeiXinConst.servicingYqMap.get(openId)!=null){
        	Set<String> keys = WeiXinConst.servicingYqMap.get(openId).keySet();
            for (String id : keys) {
            	returnList.add(id);
            }
    	}
    	return returnList;
    }
    /**
     * 分配微信用户给座席
     *
     * @param num  能同时接待的人数
     * @param csId 座席编号
     * @return
     */
    @RequestMapping("/allocateWxUser")
    @ResponseBody
    public List<String> allocateWxUser(HttpServletRequest request,int csId, int num) throws Exception{
        List<String> returnList = new ArrayList<String>();
        ConcurrentMap<String, WxStatusTmpTEntity> serviceMap = NavMenuInitUtils.getInstance().getServiceMap(""+csId);
        for (String openId : serviceMap.keySet()) {
        	WxStatusTmpTEntity temp = serviceMap.get(openId);
			if(temp != null && (""+csId).equals(temp.getCsId()) && temp.getServiceStatus() == 1){
				String publicID = NavMenuInitUtils.getInstance().userPublicIdMap.get(openId);
//				Integer deptID = NavMenuInitUtils.getInstance().userDeptMap.get(openId); //加入的
				String userInfo = WeiXinOperUtil.getUserInfo(WeiXinOperUtil.getAccessToken(publicID), temp.getWxOpenid());// 需要获取对应的AccessToken 已改
				if(temp.getBeginTimestamp() == null ){
					userInfo = userInfo.replace("}", ",\"begin\":"+0+"}");
				}
				else{
					System.out.println(" =================   "+temp.getBeginTimestamp());
					userInfo = userInfo.replace("}", ",\"begin\":"+temp.getBeginTimestamp()+"}");
				}
				returnList.add(userInfo);
//				returnList.add("beginTime :")
			}
		}
//		returnList.add("{\"city\":null,\"country\":\"中国\",\"groupId\":\"0\",\"headImgUrl\":\"\",\"id\":3,\"name\":null,\"nickname\":\"reborn\",\"province\":\"重庆\",\"remark\":null,\"sex\":\"1\",\"subscribeTime\":\"2015-12-21 17:12:29\",\"subscribleTimes\":2,\"tel\":null,\"unsubscribeTime\":\"2015-12-21 17:12:16\",\"userStatus\":1,\"openid\":\"oPxXujmpaI1Q-c1OFIDl-nIvpUCA\"}");
        return headImgUrlInList(request,returnList);
    }
//    /**
//     * 分配微信用户给座席
//     *
//     * @param num  能同时接待的人数
//     * @param csId 座席编号
//     * @return
//     */
//    @RequestMapping("/allocateWxUser")
//    @ResponseBody
//    public List<String> allocateWxUser(HttpServletRequest request,int csId, int num) throws Exception{
//    	System.out.println("---=========================-------->csId:"+csId+",num:"+num);
//    	List<String> returnList = new ArrayList<String>();
//    	SysUserEntity entity1 = sysUserService.get(SysUserEntity.class, csId);//查询坐席是否在数据库中登记
//    	if (null == entity1) {//没有登记
//    		return returnList;//返回空
//    	}
////        String userId = entity1.getUserId();//获取与华南系统用户公用ID
////        String firstChar = userId.substring(0, 1);
////        if (!"1".equals(firstChar)) {//只能市委使用座席，区县不能用座席
////            return returnList;
////        }
//    	Map<String, SeatsEntity> seatsMap = new HashMap<String, SeatsEntity>();
//    	//获取在线座席列表
////        Set<String> csKeys = WeiXinConst.onLineCsMap.keySet();
//    	Set<String> csKeys = NavMenuInitUtils.getInstance().getOnLineDeptCsMap(""+csId).keySet();
//    	if(csKeys != null){
////        	System.out.println("----------csKeys>"+csKeys.size());
//    		for (String key : csKeys) {
////        		SysUserEntity sysUser = WeiXinConst.onLineCsMap.get(key);
//    			SysUserEntity sysUser = NavMenuInitUtils.getInstance().getSysUserCsEntity(key);
//    			SeatsEntity seatsEntity = new SeatsEntity();
//    			seatsEntity.setSeatsId(""+sysUser.getId());
//    			seatsMap.put(""+sysUser.getId(), seatsEntity);
//    		}
//    	}
//    	//先读取服务列表状态为1的等待用户
////        Set<String> serviceKeys = WeiXinConst.servicingMap.keySet();
//    	Set<String> serviceKeys = NavMenuInitUtils.getInstance().getServiceMap(""+csId).keySet();
//    	if(serviceKeys != null){
////        	System.out.println("-------->serviceKeys:"+serviceKeys.size());
//    		for (String openId : serviceKeys) {
////        		WxStatusTmpTEntity entity = WeiXinConst.servicingMap.get(openId);
//    			WxStatusTmpTEntity entity = NavMenuInitUtils.getInstance().getServiceEntity(openId);
//    			//2、通道已建立，正在聊天；1、通道未建立，已经分配，坐席没有点击建立通道
//    			if (2 == entity.getServiceStatus().intValue()||1 == entity.getServiceStatus().intValue()) {//正在通话的客户及座席
//    				//获取坐席服务数量seatsEntity
//    				SeatsEntity seatsEntity = seatsMap.get(entity.getCsId());
//    				if(seatsEntity == null){
//    					seatsEntity = new SeatsEntity();
//    				}
//    				seatsEntity.setSeatsId(entity.getCsId());
//    				seatsEntity.setSeatsServiceNum(seatsEntity.getSeatsServiceNum() + 1);
//    				seatsMap.put(entity.getCsId(), seatsEntity);
//    			}
//    		}
//    	} 
//    	//TODO:座席排序
//    	if(!getCsOrder(""+csId, seatsMap)){
//    		return returnList;
//    	}
////        System.out.println("end--------------------------------------------------------------------");
//    	//读取等待列表的用户
////        Set<String> keys = WeiXinConst.waitingMap.keySet();
//    	Set<String> keys = NavMenuInitUtils.getInstance().getWaitMap(""+csId).keySet();
////        System.out.println("end-------keys.size()-----------:"+keys.size());
//    	for (String openId : keys) {
//    		if (returnList.size() >= num) {
//    			break;
//    		}
////            WxStatusTmpTEntity entity = WeiXinConst.waitingMap.get(openId);
//    		WxStatusTmpTEntity entity = NavMenuInitUtils.getInstance().getWaitEntity(openId);
//    		System.out.println("------>entity:"+entity);
//    		if (0 == entity.getServiceStatus().intValue()) {
//    			String userInfo = WeiXinOperUtil.getUserInfo(WeiXinOperUtil.getAccessToken(), entity.getWxOpenid());
//    			if (!StringUtil.isEmpty(userInfo)  && !userInfo.contains("errcode")) {
//    				returnList.add(userInfo);
//    				//删除等待队列
////                    WeiXinConst.waitingMap.remove(openId);
//    				NavMenuInitUtils.getInstance().removeWaitMap(openId);
//    				entity.setServiceStatus(1);
//    				entity.setCsId(String.valueOf(csId));
//    				entity.setAllocTime(System.currentTimeMillis());//设置分配时间
//    				entity.setIntoWaitingMapTime(System.currentTimeMillis()/1000);//重置等待时间
//    				entity.setWaitingHintNum(0);//重置等待发送消息次数
//    				//添加服务队列
//    				System.out.println("---->openId:"+openId);
////                    WeiXinConst.servicingMap.put(openId, entity);
//    				NavMenuInitUtils.getInstance().putServiceMap(openId, entity);
//    				System.out.println("---->servicingMap.size:"+NavMenuInitUtils.getInstance().getServiceMap(openId).size());
//    				
//    				//发送用户提示消息
//    				String userJsonContent = String.format("{\"touser\":\"%s\",\"msgtype\":\"text\",\"text\":{\"content\":\"%s\"}}",
//    						entity.getWxOpenid(), String.format(CommonConstantUtils.allotSysHint()));
//    				//发送給用户
//    				WeiXinOperUtil.sendMsgToWx(WeiXinOperUtil.getAccessToken(), userJsonContent);
//    				wxStatusTmpService.saveMsgDatebase(entity, CommonConstantUtils.allotSysHint(),  entity.getWxOpenid());
//    				return headImgUrlInList(request,returnList);
//    			}
//    		}
//    	}
////		returnList.add("{\"city\":null,\"country\":\"中国\",\"groupId\":\"0\",\"headImgUrl\":\"\",\"id\":3,\"name\":null,\"nickname\":\"reborn\",\"province\":\"重庆\",\"remark\":null,\"sex\":\"1\",\"subscribeTime\":\"2015-12-21 17:12:29\",\"subscribleTimes\":2,\"tel\":null,\"unsubscribeTime\":\"2015-12-21 17:12:16\",\"userStatus\":1,\"openid\":\"oPxXujmpaI1Q-c1OFIDl-nIvpUCA\"}");
//    	return returnList;
//    }
    
    /**
     * 座席排序
     * @param csId
     * @return
     */
    private boolean getCsOrder(String csId, Map<String, SeatsEntity> seatsMap){
    	boolean result  = false;
    	try {
    		List<SeatsEntity> serviceCustList = new ArrayList<SeatsEntity>();
//    		System.out.println("serviceCustList------>"+serviceCustList.size());
        	//查询所有座席配置接待人数
        	for (String seatsId : seatsMap.keySet()) {
//        		List<SysUserEntity> sysUserList = sysUserService.findByProperty(SysUserEntity.class, "userId", seatsId);
//        		if(sysUserList != null && sysUserList.size() > 0){
        			SysUserEntity sysUser = sysUserService.get(SysUserEntity.class, Integer.parseInt(seatsId));
        			SeatsEntity seatsEntity = seatsMap.get(seatsId);
//            		System.out.println("----->seatsId:"+seatsId);
//            		System.out.println("----->userId:"+sysUser.getUserId());
//            		System.out.println("----->getCustomerNum:"+sysUser.getCustomerNum());
            		seatsEntity.setSeatsServiceMaxNum(sysUser.getCustomerNum());
            		double freeNum = (double)(sysUser.getCustomerNum() - seatsEntity.getSeatsServiceNum()) / (double)sysUser.getCustomerNum();
//            		System.out.println("----->freeNum:"+freeNum+",getSeatsServiceNum:"+seatsEntity.getSeatsServiceNum());
            		seatsEntity.setSeatsServiceFreeNum(freeNum);
            		serviceCustList.add(seatsEntity);
//        		}
    		}
        	
        	if(serviceCustList != null && serviceCustList.size() > 0){
        		Collections.sort(serviceCustList, new Comparator<SeatsEntity>() {
                    public int compare(SeatsEntity arg0, SeatsEntity arg1) {//降序排序
                        return arg1.getSeatsServiceFreeNum().compareTo(arg0.getSeatsServiceFreeNum());
                    }
                });
//        		System.out.println("serviceCustList:"+FastJsonUtil.toJSON(serviceCustList));
        		for (SeatsEntity seats : serviceCustList) {
        			//TODO:修改排序
            		if(seats != null){
            			if(seats.getSeatsServiceFreeNum() > 0){
            				if(seats.getSeatsId().equals(csId)){
                				result = true;
                				WeiXinConst.seatsNumMap.put(seats.getSeatsId(), 0);
            					break;
                			} else {
                				Integer seatsNum = WeiXinConst.seatsNumMap.get(seats.getSeatsId());
                				if(seatsNum != null && seatsNum > 0){
                					WeiXinConst.seatsNumMap.put(seats.getSeatsId(), seatsNum + 1);
                					continue;
                				} else {
                					WeiXinConst.seatsNumMap.put(seats.getSeatsId(), 1);
                					break;
                				}
                			}
            			} else {
            				continue;
            			}
            		}
				}
        		
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return result;
    }
	public List<String> headImgUrlInList(HttpServletRequest request,List<String> list){
		//头像标记
		List<String> rList=new ArrayList<String>();
		if(list != null && list.size() > 0){
			System.out.println(list.get(0));
			JSONObject obj = JSON.parseObject(list.get(0));
	        List<WeixinUserInfoEntity> wxList = weixinUserInfoService.findByProperty(WeixinUserInfoEntity.class, "wxOpenId", obj.getString("openid"));
	        WeixinUserInfoEntity weixinUserInfoEntity = wxList.get(0);
	        if(weixinUserInfoEntity.getHeadImg()!=null&&!"".equals(weixinUserInfoEntity.getHeadImg())){
	        	obj.remove("headimgurl");
	        	obj.put("headimgurl", weixinUserInfoEntity.getHeadImg());
	        }else if(!"".equals(obj.getString("headimgurl"))){
				String str=saveToFile(request,obj.getString("headimgurl"));
				//保存图片到数据库
	        	obj.remove("headimgurl");
				obj.put("headimgurl", str);
				weixinUserInfoEntity.setHeadImg(str);
				weixinUserInfoService.saveOrUpdate(weixinUserInfoEntity);
			}
	        obj.put("phone", StringUtil.nullObject2String(weixinUserInfoEntity.getPhone()));
	        rList.add(obj.toJSONString());
		}
		return rList;
	}
	public String saveToFile(HttpServletRequest request,String destUrl) {
	    FileOutputStream fos = null;
	    BufferedInputStream bis = null;
	    HttpURLConnection httpUrl = null;
	    URL url = null;
	    int BUFFER_SIZE = 1024;
	    byte[] buf = new byte[BUFFER_SIZE];
	    int size = 0;
	    try {
		    url = new URL(destUrl);
		    httpUrl = (HttpURLConnection) url.openConnection();
		    httpUrl.connect();
		    bis = new BufferedInputStream(httpUrl.getInputStream());
		    String path = request.getRealPath("/") + File.separator + "image"+ File.separator + "users";
            //String path = "D:\\image";
            File file = new File(path);
            if (!file.exists()) {
                file.mkdir();
            }
            String imageName=UUID.randomUUID().toString().replace("-", "").substring(0,10);
            String imagePath = File.separator + imageName + ".jpg";
            file = new File(path + imagePath);
		    fos = new FileOutputStream(file);
		    while ((size = bis.read(buf)) != -1) {
		    	fos.write(buf, 0, size);
		    }
		    fos.flush();
		    return  "/image/users/"+imageName+".jpg";
	    } catch (IOException e) {
	    } finally {
		    try {
			    fos.close();
			    bis.close();
			    httpUrl.disconnect();
		    } catch (Exception e) {
		    } 
	    }
	    return null;
    }
//    public static void main(String[] args) {
//    	List<SeatsEntity> list = new ArrayList<SeatsEntity>();
//    	SeatsEntity s1 = new SeatsEntity();
//    	s1.setSeatsId("1");
//    	s1.setSeatsServiceFreeNum(0.7);
//    	list.add(s1);
//    	SeatsEntity s2 = new SeatsEntity();
//    	s2.setSeatsId("2");
//    	s2.setSeatsServiceFreeNum(0.6);
//    	list.add(s2);
//    	SeatsEntity s3 = new SeatsEntity();
//    	s3.setSeatsId("3");
//    	s3.setSeatsServiceFreeNum(0.9);
//    	list.add(s3);
//    	SeatsEntity s4 = new SeatsEntity();
//    	s4.setSeatsId("4");
//    	s4.setSeatsServiceFreeNum(0.7);
//    	list.add(s4);
//    	
//    	Collections.sort(list, new Comparator<SeatsEntity>() {
//            public int compare(SeatsEntity arg0, SeatsEntity arg1) {
//            	int ret = arg1.getSeatsServiceFreeNum().compareTo(arg0.getSeatsServiceFreeNum());
//            	System.out.println("ret:"+ret);
//                return ret;
//            }
//        });
//    	
//    	for (SeatsEntity seatsEntity : list) {
//			System.out.println("seatsId:" + seatsEntity.getSeatsId() + ", freeNum:" + seatsEntity.getSeatsServiceFreeNum());
//		}
//	}
	

    @RequestMapping(value = "/getViolationInfo")
    @ResponseBody 
    public JSONObject getViolationInfo(String plateNo,String colorNo)
    {
        StringBuilder json = new StringBuilder();
        try {
    		System.out.println(plateNo);
    		plateNo = URLDecoder.decode(plateNo, "UTF-8"); 
        	String url = "http://10.224.5.164/cqjt/getViolationInfo?plateNo="+plateNo+"&colorNo="+colorNo;
    		System.out.println(plateNo);
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(),"utf-8"));
            String inputLine = null;
            while ( (inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jObject=JSONObject.parseObject(json.toString());
        return jObject;
    }

	//开发环境
   // private static final String ip = "172.2.96.100";
    //发布环境
    private static final String ip = "203.93.109.52";
    private static final int port = 8899;
    
    public String getOAuthOpenId(String appid, String secret, String code ) {
        String openId = null;
        String o_auth_openid_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        String requestUrl = o_auth_openid_url.replace("APPID", appid).replace("SECRET", secret).replace("CODE", code);
//        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx915ad295909bf037&secret=b26b2f2432620fb532d117fe6d132afa&code=031b09dca914ed8273b9f83ebf6298eh&grant_type=authorization_code";
        ApiHttpUtils client = new ApiHttpUtils(requestUrl);
		try {
			String result = client.doGet();
			ObjectMapper mapper = new ObjectMapper();
		    WxEntity wx =  mapper.readValue(result, WxEntity.class);
		    openId = wx.getOpenid();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("---client-----error>",e);
		}
        return openId;
    }
    @RequestMapping("/getViolationReportInit")
	public  String queryList(HttpServletRequest request,Map<String,Object> map){
		String openId = request.getParameter("openId");
		String code = request.getParameter("code");
		if(StringUtil.isNotEmpty(code)){
			//获取openId
			openId = getOAuthOpenId(WeiXinConst.appId, WeiXinConst.appSecret, code); // appId 修改？？？？ opendId
		}
		map.put("openId", openId);
		return "videoImg/list";
	}
    @RequestMapping(value = "/getViolationReportList")
    @ResponseBody 
    public JSONObject getViolationReportList(int pageIndex,int pageSize)
    {
		pageIndex=1;
    	pageSize=10;
		NioSocketConnector connector = new NioSocketConnector();
		connector.getFilterChain().addLast("logger", new LoggingFilter());
		connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new SocketProtocolCodecFactory())); // 设置编码过滤器
		connector.setHandler(new SocketClientHandlerListener());// 设置事件处理器
		ConnectFuture cf = connector.connect(new InetSocketAddress(ip, port));// 建立连接
		cf.awaitUninterruptibly();// 等待连接创建完成
		WeiXinConst.socketMap.put(
				"GET_VIOLATION_REPROT_LIST" + cf.getSession().getId(),
				null);
		SocketAppPacket packet = new SocketAppPacket(cf.getSession());
		GetPagedViolationReportListRequest.GetPagedViolationReportListRequestMessage.Builder builder=
				GetPagedViolationReportListRequest.GetPagedViolationReportListRequestMessage.newBuilder();
		builder.setPageIndex(pageIndex);
		builder.setPageSize(pageSize);
		//builder.setVersion("");
		builder.setIsexposure(1);
		//builder.setDeviceNo("");
		//指令
		packet.setCommandId(SocketAppPacket.GET_VIOLATION_REPROT_LIST);
		packet.setReceiveTime(System.currentTimeMillis());
		//数据
		packet.setCommandData(builder.build().toByteArray());
		cf.getSession().write(packet);// 发送消息
		int n=0;
		while (WeiXinConst.socketMap
				.get("GET_VIOLATION_REPROT_LIST"
						+ cf.getSession().getId()) == null&&n<10) {
			try {
				Thread.sleep(100);
				n++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		String result=WeiXinConst.socketMap.get("GET_VIOLATION_REPROT_LIST"+cf.getSession().getId());
		JSONObject jObject=JSONObject.parseObject(result);
        return jObject;
    }

    @RequestMapping(value = "/addViolationReportView")
    @ResponseBody 
    public JSONObject addViolationReportView(int id,String openId,int userId,String userPhone,String batchNo,String platform,String deviceNo)
    {
		NioSocketConnector connector = new NioSocketConnector();
		connector.getFilterChain().addLast("logger", new LoggingFilter());
		connector.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new SocketProtocolCodecFactory())); // 设置编码过滤器
		connector.setHandler(new SocketClientHandlerListener());// 设置事件处理器
		ConnectFuture cf = connector.connect(new InetSocketAddress(ip, port));// 建立连接
		cf.awaitUninterruptibly();// 等待连接创建完成
		WeiXinConst.socketMap.put(
				"ADD_VIOLATION_REPROT_VIEW" + cf.getSession().getId(),
				null);
		SocketAppPacket packet = new SocketAppPacket(cf.getSession());
		AddViolationReportViewRequest.AddViolationReportViewRequestMessage.Builder builder = AddViolationReportViewRequest.AddViolationReportViewRequestMessage
				.newBuilder();
		builder.setVersion("");
		builder.setUserid(userId);
		builder.setReportId(id);
		builder.setUserPhone(userPhone);
		builder.setDeviceNo(deviceNo);
		builder.setReportBatchNo(batchNo);
		if(!"".equals(openId)){
			builder.setPlatform("weixin");
		}else{
			builder.setPlatform(platform);
		}
		builder.setOpenid(openId);
		packet.setCommandId(SocketAppPacket.ADD_VIOLATION_REPROT_VIEW);
		packet.setCommandData(builder.build().toByteArray());
		cf.getSession().write(packet);// 发送消息
		int n=0;
		while (WeiXinConst.socketMap
				.get("ADD_VIOLATION_REPROT_VIEW"
						+ cf.getSession().getId()) == null&&n<10) {
			try {
				Thread.sleep(100);
				n++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		String result = WeiXinConst.socketMap
				.get("ADD_VIOLATION_REPROT_VIEW"
						+ cf.getSession().getId());
		JSONObject jObject=JSONObject.parseObject(result);
        return jObject;
    }
    @RequestMapping(value = "/getViolationReportByBatchNo")
    @ResponseBody 
    public JSONObject getViolationReportByBatchNo(int id,String batchNo)
    {
    	NioSocketConnector connector = new NioSocketConnector();
		connector.getFilterChain().addLast("logger", new LoggingFilter());
		connector.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new SocketProtocolCodecFactory())); // 设置编码过滤器
		connector.setHandler(new SocketClientHandlerListener());// 设置事件处理器
		ConnectFuture cf = connector.connect(new InetSocketAddress(ip, port));// 建立连接
		cf.awaitUninterruptibly();// 等待连接创建完成
		WeiXinConst.socketMap.put(
				"GET_VIOLATION_REPROT_BY_BATCH_NO" + cf.getSession().getId(),
				null);
		SocketAppPacket packet = new SocketAppPacket(cf.getSession());
		GetViolationReportByBatchNoRequest.GetViolationReportByBatchNoRequestMessage.Builder builder = GetViolationReportByBatchNoRequest.GetViolationReportByBatchNoRequestMessage
				.newBuilder();
		builder.setVersion("");
		builder.setBatchNo(batchNo);
		builder.setId(id);
		packet.setCommandId(SocketAppPacket.GET_VIOLATION_REPROT_BY_BATCH_NO);
		packet.setCommandData(builder.build().toByteArray());
		cf.getSession().write(packet);// 发送消息
		int n=0;
		while (WeiXinConst.socketMap
				.get("GET_VIOLATION_REPROT_BY_BATCH_NO"
						+ cf.getSession().getId()) == null&&n<10) {
			try {
				Thread.sleep(100);
				n++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		String result = WeiXinConst.socketMap
				.get("GET_VIOLATION_REPROT_BY_BATCH_NO"
						+ cf.getSession().getId());
		JSONObject jObject=JSONObject.parseObject(result);
        return jObject;
    }
    @RequestMapping(value = "/getViolationReportBaseData")
    @ResponseBody 
    public JSONObject getViolationReportBaseData()
    {
    	NioSocketConnector connector = new NioSocketConnector();
		connector.getFilterChain().addLast("logger", new LoggingFilter());
		connector.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new SocketProtocolCodecFactory())); // 设置编码过滤器
		connector.setHandler(new SocketClientHandlerListener());// 设置事件处理器
		ConnectFuture cf = connector.connect(new InetSocketAddress(ip, port));// 建立连接
		cf.awaitUninterruptibly();// 等待连接创建完成
		WeiXinConst.socketMap.put(
				"GET_VIOLATION_REPROT_BASE_DATA" + cf.getSession().getId(),
				null);
		SocketAppPacket packet = new SocketAppPacket(cf.getSession());
		GetViolationReportBaseDataRequest.GetViolationReportBaseDataRequestMessage.Builder builder = GetViolationReportBaseDataRequest.GetViolationReportBaseDataRequestMessage
				.newBuilder();
		builder.setVersion("");
		packet.setCommandId(SocketAppPacket.GET_VIOLATION_REPROT_BASE_DATA);
		packet.setCommandData(builder.build().toByteArray());
		cf.getSession().write(packet);// 发送消息
		int n=0;
		while (WeiXinConst.socketMap
				.get("GET_VIOLATION_REPROT_BASE_DATA"
						+ cf.getSession().getId()) == null&&n<10) {
			try {
				Thread.sleep(100);
				n++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		String result = WeiXinConst.socketMap
				.get("GET_VIOLATION_REPROT_BASE_DATA"
						+ cf.getSession().getId());
		System.out.println("=========================================================");
		System.out.println(result);
		System.out.println("=========================================================");
		JSONObject jObject=JSONObject.parseObject(result);
        return jObject;
    }
    @RequestMapping(value = "/addViolationReportComment")
    @ResponseBody 
    public JSONObject addViolationReportComment(int id,String openId,int userId,String userPhone,String batchNo,String commentTypeIds)
    {
    	NioSocketConnector connector = new NioSocketConnector();
		connector.getFilterChain().addLast("logger", new LoggingFilter());
		connector.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new SocketProtocolCodecFactory())); // 设置编码过滤器
		connector.setHandler(new SocketClientHandlerListener());// 设置事件处理器
		ConnectFuture cf = connector.connect(new InetSocketAddress(ip, port));// 建立连接
		cf.awaitUninterruptibly();// 等待连接创建完成
		WeiXinConst.socketMap.put(
				"ADD_VIOLATION_REPROT_COMMENT" + cf.getSession().getId(),
				null);
		SocketAppPacket packet = new SocketAppPacket(cf.getSession());
		AddViolationReportCommentRequest.AddViolationReportCommentRequestMessage.Builder builder = AddViolationReportCommentRequest.AddViolationReportCommentRequestMessage
				.newBuilder();
		builder.setVersion("");
		builder.setUserPhone(userPhone);
		builder.setReportBatchNo(batchNo);
		builder.setReportId(id);
		builder.setUserid(userId);
		builder.setOpenid(openId);
		builder.setCommentTypeIds(commentTypeIds);
		packet.setCommandId(SocketAppPacket.ADD_VIOLATION_REPROT_COMMENT);
		packet.setCommandData(builder.build().toByteArray());
		cf.getSession().write(packet);// 发送消息
		int n=0;
		while (WeiXinConst.socketMap
				.get("ADD_VIOLATION_REPROT_COMMENT"
						+ cf.getSession().getId()) == null&&n<10) {
			try {
				Thread.sleep(100);
				n++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		String result = WeiXinConst.socketMap
				.get("ADD_VIOLATION_REPROT_COMMENT"
						+ cf.getSession().getId());
		JSONObject jObject=JSONObject.parseObject(result);
        return jObject;
    }
}

