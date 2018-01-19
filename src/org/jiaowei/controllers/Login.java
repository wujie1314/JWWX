package org.jiaowei.controllers;

import org.apache.log4j.Logger;
import org.jiaowei.entity.DepartEntity;
import org.jiaowei.entity.SysUserEntity;
import org.jiaowei.entity.SysUserRoleEntity;
import org.jiaowei.service.SysUserService;
import org.jiaowei.util.PropertiesUtil;
import org.jiaowei.util.StringUtil;
import org.jiaowei.wxutil.NavMenuInitUtils;
import org.jiaowei.wxutil.WeiXinConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


/** 
 * Created by Administrator on 2015/11/24.
 * 与微信服务器交互
 */
@Controller
public class Login {

    private static Logger logger = Logger.getLogger(Login.class);
    @Autowired
    private SysUserService sysUserService;
    //////////////////////////////////////////////////////////////////////////////////////////
    
    
    @RequestMapping(value="/")
    public String indexTo(){
        return "login";
    }
    
    @RequestMapping(value="/skip/{name}")
    public String indexTo(@PathVariable String name){
    	if(null != name && !name.isEmpty()){
    		return name;
    	}
        return "login";
    }
    
   
    @RequestMapping("/login")
    public  String login(String userName, /*String password,*/Map<String,Object> map,HttpServletRequest request) throws Exception {
    	//更新华南用户名称
        SysUserEntity sysEntity=getHNIIUserInfo(userName);
        InetAddress addr = InetAddress.getLocalHost();
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        	ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        	ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        	ip = request.getRemoteAddr();
        }
        //String ip=addr.getHostAddress();//获得本机IP
        String address=addr.getHostName();//获得本机名称
//        System.out.println("------>ip:"+ip);
//        System.out.println("------>address:"+address);
//        if(StringUtils.isBlank(ip) || !(StringUtils.isNotBlank(ip) && (ip.contains("10.224") || ip.contains("192.168")))){
//        	 logger.info("IP限制.");
//             map.put("erorrInfo","IP限制.");
//             return "error";
//        }
    	if(sysEntity==null /*|| StringUtil.isEmpty(password)*/){
            logger.info("用户名不能为空");
            map.put("erorrInfo","用户名不能为空");
            return "userError";
        }else if(sysEntity.getUserName()==null){
        	 logger.info("用户登录失败");
             map.put("erorrInfo","用户登录失败");
             return "dataError";
        }else{
            List<SysUserEntity> list =sysUserService.findByProperty(SysUserEntity.class,"userId",userName);
            SysUserEntity entity=new SysUserEntity();
            if(null == list || list.size()<1){//新用户
            	entity.setDeptId(new Integer(-1));
            	List<DepartEntity> deList=sysUserService.findByProperty(DepartEntity.class, "jgdm", sysEntity.getDeptId());
            	if(deList!=null&&deList.size()>0){
            		DepartEntity de=deList.get(0);
            		entity.setDeptId(de.getId());
            	}
            	entity.setUserName(sysEntity.getUserName());
            	entity.setUserId(userName);
            	entity.setChangeNum(5);
            	entity.setCustomerNum(5);
            	entity.setDeptIdHn(sysEntity.getDeptId().toString());
            	sysUserService.save(entity);
            }else{//老用户
                entity  = list.get(0);
                //更新用户名称
                if(!"".equals(sysEntity.getUserName())&&!sysEntity.getUserName().equals(entity.getUserName())){
                	entity.setUserName(sysEntity.getUserName());
                	sysUserService.saveOrUpdate(entity);
                }
            }
            //验证用户是否已登录
            //            SysUserEntity entity2=WeiXinConst.onLineCsMap.get(String.valueOf(entity.getId()));
            NavMenuInitUtils.getInstance().userDeptMap.put(""+entity.getId(), entity.getDeptId());
            SysUserEntity entity2=NavMenuInitUtils.getInstance().getSysUserCsEntity(entity.getId()+"");
            String nowIp=WeiXinConst.ipAndAddressMap.get(String.valueOf(entity.getId()));
            String deptAll=",3,4,5,6,12,";
            if(entity2!=null&&nowIp!=null&&!nowIp.toString().equals(ip)){//已登录
            	map.put("erorrInfo","此用户已登录");
                return "error";
            }else if(deptAll.indexOf(","+entity.getDeptId().toString()+",")==-1){
            	map.put("erorrInfo","暂未开通");
                return "deptError";
            }else{//未登录
                WeiXinConst.ipAndAddressMap.put(String.valueOf(entity.getId()), ip);//记录IP
                List<SysUserRoleEntity> sysUserRoleList=sysUserService.findByProperty(SysUserRoleEntity.class,"userId",entity.getId());
                if(sysUserRoleList!=null&&sysUserRoleList.size()>0){
                	entity.setIsAdmin(1);
                }else{
                	entity.setIsAdmin(0);
                }
                map.put("entity",entity);
//                WeiXinConst.onLineCsMap.put(String.valueOf(entity.getId()),entity);
                NavMenuInitUtils.getInstance().putOnLineDeptCsMap(""+entity.getId(), entity);
//                Map map2=NavMenuInitUtils.getInstance().onLineDeptCsMap;
//                Map map3=NavMenuInitUtils.getInstance().onLineDeptCsMap.get(entity.getDeptId());
                return "main";
            }
        }
    }


    
    /**
     * 
     * 登录验证
     * 
     * @author zkl
     * @data 2018年1月19日 下午4:21:21
     * @param ACCOUNT
     * @param PASSWORD
     * @return
     */
    @RequestMapping("/loginVerify")
    @ResponseBody
    public  String login(String ACCOUNT,String PASSWORD){
    	if(ACCOUNT==null||ACCOUNT.isEmpty()){
            logger.info("用户名不能为空");
            return "-9";
        }
    	if(PASSWORD.isEmpty() || PASSWORD==null){
    		logger.info("密码不能为空");
            return "-6";
    	}
    	else{
    		Map<String,String> userMap=getUserInfor(ACCOUNT);
    		if(PASSWORD.equals(userMap.get("PASSWORD"))){
    			logger.info("登录成功");
    			return "1";
    		}
    		else{
    			logger.info("密码错误");
    			return "-2";
    		}
    	}
    	
    }
    
    /**
     * 本地测试登录 
     * 数据库用雅苑的NHII
     * 
     * 
     */
    public Map<String,String> getUserInfor(String userId){
    	Map<String,String> map = new HashMap<String, String>();
    	 Connection conn = null;
         PreparedStatement pre = null;
         ResultSet resultSet = null;
    	try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String jdbcUrl = "jdbc:oracle:thin:@superc102.vicp.cc:1522:jwwx";
            String jdbcUsername = "hnii";
            String jdbcPassword ="wssj";
            conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
//            String sql = " select t1.USERNAME USERNAME, t1.PASSWORD PASSWORD,t1.DEPT DEPT from  HNII_S_USER1@ORCL180.REGRESS.RDBMS.DEV.US.ORACLE.COM  t1 where t1.USERID = ? ";
            String sql = " SELECT t1.USERNAME USERNAME, t1.PASSWORD PASSWORD,t1.DEPT DEPT FROM HNII_S_USER1 t1 WHERE t1.USERID = ?  ";
            pre = conn.prepareStatement(sql);
            pre.setString(1, userId);
            resultSet = pre.executeQuery();
            while(resultSet.next()){
            	map.put("USERNAME", resultSet.getString("USERNAME"));
            	map.put("PASSWORD", resultSet.getString("PASSWORD"));
            	map.put("DEPT", resultSet.getString("DEPT"));
            }
        }catch(Exception e){
        	e.printStackTrace();
        }
    	
    	return map;
    }
    
    

    public SysUserEntity getHNIIUserInfo(String userId) throws Exception {
    	SysUserEntity entity=new SysUserEntity();
        Connection conn = null;
        PreparedStatement pre = null;
        ResultSet resultSet = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String jdbcUrl = PropertiesUtil.getProperty("dbconfig.properties", "jdbc.url");
            String jdbcUsername = PropertiesUtil.getProperty("dbconfig.properties", "jdbc.username");
            String jdbcPassword = PropertiesUtil.getProperty("dbconfig.properties", "jdbc.password");
            conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
//             String sql = " select t1.USERNAME USERNAME, t1.PASSWORD PASSWORD,t1.DEPT DEPT from  HNII_S_USER1@ORCL180.REGRESS.RDBMS.DEV.US.ORACLE.COM  t1 where t1.USERID = ? ";
            String sql = " SELECT t1.USER_NAME USERNAME, t1.DEPT_ID DEPT FROM SYS_USER_T t1 WHERE t1.USER_ID = ? ";
            pre = conn.prepareStatement(sql);
            pre.setString(1, userId);
            resultSet = pre.executeQuery();
            while(resultSet.next()){
            	entity.setUserName(resultSet.getString("USERNAME"));
            	entity.setDeptId(new Integer(resultSet.getString("DEPT")));
            }
        }catch(Exception e){
        	e.printStackTrace();
        }finally {
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
        return entity;
    }
    /**
     * 跳转工单页面
     * @param phone 微信用户电话号码
     * @param agentId 座席ID
     * @return
     */
    @RequestMapping("/work")
    public  String work(@RequestParam String phone,@RequestParam String agentId){
        return "redirect:http://10.224.2.177:7001/WebRoot/jsp/wx/wxdj.jsp?callSeq=12345&callNum="+phone+"&Agentid="+agentId+"&Type=wx&org=5010";//重定向
    }
}
