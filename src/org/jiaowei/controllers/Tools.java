package org.jiaowei.controllers;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.http.HttpServletRequest;

import org.jiaowei.wxutil.WeiXinConst;

import org.apache.commons.lang3.StringUtils;
import org.jiaowei.entity.NavMenuEntity;
import org.jiaowei.entity.NavigationMenuEntity;
import org.jiaowei.entity.SysUserEntity;
import org.jiaowei.entity.WxStatusTmpTEntity;
import org.jiaowei.wxutil.NavMenuInitUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by alex on 16-1-3.
 */
@Controller
public class Tools {

    @RequestMapping("/getWt")
    @ResponseBody
    public ConcurrentMap<Integer, ConcurrentMap<String,WxStatusTmpTEntity>> getAllWaitingUser(HttpServletRequest request){
//        return WeiXinConst.waitingMap;
    	ConcurrentMap<Integer, ConcurrentMap<String,WxStatusTmpTEntity>> result=  new ConcurrentHashMap<Integer, ConcurrentMap<String,WxStatusTmpTEntity>>();
    	ConcurrentMap<String, WxStatusTmpTEntity> dept = new ConcurrentHashMap<String, WxStatusTmpTEntity>();
    	String deptId = request.getParameter("deptId");
    	if(StringUtils.isNotBlank(deptId)){
    		dept = NavMenuInitUtils.getInstance().waitMap.get(deptId);
    		result.put(Integer.parseInt(deptId), dept);
    	} else {
//    		for (Integer key : NavMenuInitUtils.getInstance().waitMap.keySet()) {
//    			ConcurrentMap<String, WxStatusTmpTEntity> temp = NavMenuInitUtils.getInstance().waitMap.get(key);
//    			if(temp != null){
//    				for (String openId : temp.keySet()) {
//    					result.put(openId, temp.get(openId));
//					}
//    			}
//			}
    		result = NavMenuInitUtils.getInstance().waitMap;
    	}
    	return result;
    }

    @RequestMapping("/getSv")
    @ResponseBody
    public ConcurrentMap<Integer, ConcurrentMap<String,WxStatusTmpTEntity>> getAllServicingUser(HttpServletRequest request){
    	ConcurrentMap<Integer, ConcurrentMap<String,WxStatusTmpTEntity>> result=  new ConcurrentHashMap<Integer, ConcurrentMap<String,WxStatusTmpTEntity>>();
    	ConcurrentMap<String, WxStatusTmpTEntity> dept = new ConcurrentHashMap<String, WxStatusTmpTEntity>();
    
    	String deptId = request.getParameter("deptId");
    	if(StringUtils.isNotBlank(deptId)){
    		dept = NavMenuInitUtils.getInstance().serviceMap.get(deptId);
    		result.put(Integer.parseInt(deptId), dept);
    	} else {
    		result = NavMenuInitUtils.getInstance().serviceMap;
    	}
    	return result;
    }

    
    @RequestMapping("/getDel")
    @ResponseBody
    public ConcurrentMap<Integer, ConcurrentMap<String,WxStatusTmpTEntity>> getAllDeletedUser(HttpServletRequest request){
//        return WeiXinConst.deletedMap;
    	ConcurrentMap<Integer, ConcurrentMap<String,WxStatusTmpTEntity>> result=  new ConcurrentHashMap<Integer, ConcurrentMap<String,WxStatusTmpTEntity>>();
    	ConcurrentMap<String, WxStatusTmpTEntity> dept = new ConcurrentHashMap<String, WxStatusTmpTEntity>();
    	String deptId = request.getParameter("deptId");
    	if(StringUtils.isNotBlank(deptId)){
    		dept = NavMenuInitUtils.getInstance().removeMap.get(deptId);
    		result.put(Integer.parseInt(deptId), dept);
    	} else {
    		result = NavMenuInitUtils.getInstance().removeMap;
    	}
    	return result;
    }
    
    @RequestMapping("/getCs")
    @ResponseBody
    public ConcurrentMap<Integer, ConcurrentMap<String, SysUserEntity>> getAllSysUserCs(HttpServletRequest request){
//        return WeiXinConst.deletedMap;
    	ConcurrentMap<Integer, ConcurrentMap<String, SysUserEntity>> result = new ConcurrentHashMap<Integer, ConcurrentMap<String, SysUserEntity>>();
    	ConcurrentMap<String, SysUserEntity> dept = new ConcurrentHashMap<String, SysUserEntity>();
    	String deptId = request.getParameter("deptId");
    	if(StringUtils.isNotBlank(deptId)){
    		dept = NavMenuInitUtils.getInstance().onLineDeptCsMap.get(deptId);
    		result.put(Integer.parseInt(deptId), dept);
    	} else {
    		result = NavMenuInitUtils.getInstance().onLineDeptCsMap;
    	}
    	return result;
    }
    
    @RequestMapping("/getUserDept")
    @ResponseBody
    public ConcurrentMap<String, Integer> getUserDeptMap(HttpServletRequest request){
	  	return NavMenuInitUtils.getInstance().userDeptMap;
    }
    @RequestMapping("/delUserDept")
    @ResponseBody
    public String delUserDeptMap(HttpServletRequest request){
    	NavMenuInitUtils.getInstance().userDeptMap.clear();
    	return "{'code':'0','msg':'删除成功！'}";
    }
    
    @RequestMapping("/getNavMenuMap")
    @ResponseBody
    public Map<String,NavMenuEntity> getNavMenuMap(HttpServletRequest request){
    	return NavMenuInitUtils.getInstance().navMenuMap;
    }
    @RequestMapping("/delNavMenuMap")
    @ResponseBody
    public String delNavMenuMap(HttpServletRequest request){
    	NavMenuInitUtils.getInstance().navMenuMap.clear();
    	return "{'code':'0','msg':'删除成功！'}";
    }
    
    
    @RequestMapping("/getNavigationMenu")
    @ResponseBody
    public Map<String, NavigationMenuEntity> getNavigationMenu(HttpServletRequest request){
    	return WeiXinConst.navigationMenu;
    }
    @RequestMapping("/delNavigationMenu")
    @ResponseBody
    public String delNavigationMenu(HttpServletRequest request){
    	WeiXinConst.navigationMenu.clear();
    	return "{'code':'0','msg':'删除成功！'}";
    }
    /**
     * 删除等待队列
     */
    @RequestMapping("/delWaitingMap")
    @ResponseBody
    public String delWaitingMap(HttpServletRequest request){
//    	WeiXinConst.waitingMap.clear();
    	String deptId = request.getParameter("deptId");
    	if(StringUtils.isNotBlank(deptId)){
    		NavMenuInitUtils.getInstance().waitMap.remove(deptId);
    	} else {
    		NavMenuInitUtils.getInstance().waitMap.clear();
    	}
    	return "{'code':'0','msg':'删除成功！'}";
    }
    /**
     * 删除服务队列
     */
    @RequestMapping("/delServicingMap")
    @ResponseBody
    public String delServicingMap(HttpServletRequest request){
//    	WeiXinConst.servicingMap.clear();
    	String deptId = request.getParameter("deptId");
    	if(StringUtils.isNotBlank(deptId)){
    		NavMenuInitUtils.getInstance().serviceMap.remove(deptId);
    	} else {
    		NavMenuInitUtils.getInstance().serviceMap.clear();
    	}
    	return "{'code':'0','msg':'删除成功！'}";
    }
    /*
     * 删除删除队列
     */
    @RequestMapping("/deletedMap")
    @ResponseBody
    public String deletedMap(HttpServletRequest request){
//    	WeiXinConst.deletedMap.clear();
    	String deptId = request.getParameter("deptId");
    	if(StringUtils.isNotBlank(deptId)){
    		NavMenuInitUtils.getInstance().removeMap.remove(deptId);
    	} else {
    		NavMenuInitUtils.getInstance().removeMap.clear();
    	}
    	return "{'code':'0','msg':'删除成功！'}";
    }
    /*
     * 删除删除队列
     */
    @RequestMapping("/delRemoveMap")
    @ResponseBody
    public String delRemoveMap(HttpServletRequest request){
//    	WeiXinConst.deletedMap.clear();
    	String deptId = request.getParameter("deptId");
    	if(StringUtils.isNotBlank(deptId)){
    		NavMenuInitUtils.getInstance().onLineDeptCsMap.remove(deptId);
    	} else {
    		NavMenuInitUtils.getInstance().onLineDeptCsMap.clear();
    	}
    	return "{'code':'0','msg':'删除成功！'}";
    }
    
    
}
