package org.jiaowei.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jiaowei.entity.MsgFromCustomerServiceEntity;
import org.jiaowei.entity.MsgFromWxEntity;
import org.jiaowei.entity.WeixinAutoRespondEntity;
import org.jiaowei.service.MsgFromCustomerServiceService;
import org.jiaowei.service.NavMenuService;
import org.jiaowei.service.WeixinAutoRespondService;
import org.jiaowei.util.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OthersController {

	@Autowired
    private MsgFromCustomerServiceService msgFromCustomerService;
	
    @Autowired
    private NavMenuService navMenuService;
	    
    @Autowired
    private WeixinAutoRespondService autoRespondService;
	
	@RequestMapping("/others/home")
	public String othersList(HttpServletRequest request,  HttpServletResponse response){
		

		return "others/home";
	}
	
	 /**
     * 获取历史消息
     * @return
     */
    @RequestMapping(value="others/appmes",method=RequestMethod.POST)
    @ResponseBody
    public String receiveAppMessage(@RequestBody Map<String, String> map, HttpServletResponse response, HttpServletRequest request) {
    	 String msgType = map.get("MsgType"), content = map.get("Content");
//		navMenuService.sendCustomerService(map, response, request);
    	 try {
    		 List<WeixinAutoRespondEntity> result = autoRespondService.getRespondMes(content);
    		 String returnString = "请选择服务项 : \n";
    		 for (int i = 0; i < result.size(); i++) {
    			returnString += "【"+(i + 1)+"】"+result.get(i).getContent()+"\n";
    		 }
    		 returnString +=  "【0】人工服务";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "-1";
    }
	
	
	 /**
     * 获取历史消息
     * @return
     */
    @RequestMapping(value="others/getMsgOneDay",method=RequestMethod.GET)
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
     * 获取历史消息
     * @return
     */
    @RequestMapping(value="others/getMesQueue",method=RequestMethod.GET)
    @ResponseBody
    public List<Object> getMsgFromQueue(String openid,String bTime,String eTime,int begin,int end) {
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
}
