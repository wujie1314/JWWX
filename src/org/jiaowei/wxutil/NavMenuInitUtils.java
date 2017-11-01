package org.jiaowei.wxutil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.log4j.Logger;
import org.jiaowei.controllers.ConnWeixinController;
import org.jiaowei.entity.NavMenuEntity;
import org.jiaowei.entity.SysUserEntity;
import org.jiaowei.entity.WxStatusTmpTEntity;

import activiti.SeatW;
import activiti.WaitQ;

public class NavMenuInitUtils {
	
	private NavMenuInitUtils(){
		initNavMenu();
	}
	
	private static final NavMenuInitUtils instance = new NavMenuInitUtils();
	
	public static NavMenuInitUtils getInstance() {
		return instance;
	}
	
	public static void main(String[] args) {
		
		/*List<String> list = new ArrayList<String>();
		list.get(0);*/
		
		List<SysUserEntity> result = new ArrayList<SysUserEntity>();
		
		SysUserEntity entity1 = new SysUserEntity();
		entity1.setId(1);
		entity1.setCustomerNum(5);
		entity1.setServiceFreeNum(0.5);
		result.add(entity1);
		
		SysUserEntity entity2 = new SysUserEntity();
		entity2.setId(2);
		entity2.setCustomerNum(4);
		result.add(entity2);
		
		SysUserEntity entity3 = new SysUserEntity();
		entity3.setId(3);
		entity3.setCustomerNum(3);
		result.add(entity3);
		
		SysUserEntity entity4 = new SysUserEntity();
		entity4.setId(4);
		entity4.setCustomerNum(1);
		result.add(entity4);
		

		SysUserEntity entity5 = new SysUserEntity();
		entity5.setId(5);
		entity5.setCustomerNum(5);
		entity5.setServiceFreeNum(0.8);
		result.add(entity5);
		
		Collections.sort(result, new Comparator<SysUserEntity>() {
            public int compare(SysUserEntity arg0, SysUserEntity arg1) {//降序排序
            	int flag = arg1.getCustomerNum().compareTo(arg0.getCustomerNum());//先根据总服务数排序
            	if(flag == 0){
            		flag = arg1.getServiceFreeNum().compareTo(arg0.getServiceFreeNum());//在根据空闲率排序
            	}
                return flag;
            }
        });
		
		for (SysUserEntity sysUserEntity : result) {
			System.out.println(sysUserEntity.getId());
		}
	}
	
	private static Logger logger = Logger.getLogger(ConnWeixinController.class);
	public  Map<String,NavMenuEntity> navMenuMap = new HashMap<String,NavMenuEntity>();
	public  ConcurrentMap<Integer, ConcurrentMap<String, WxStatusTmpTEntity>> waitMap = new ConcurrentHashMap<Integer, ConcurrentMap<String, WxStatusTmpTEntity>>();
	public  ConcurrentMap<Integer, ConcurrentMap<String, WxStatusTmpTEntity>> serviceMap = new ConcurrentHashMap<Integer, ConcurrentMap<String, WxStatusTmpTEntity>>();
	public  ConcurrentMap<Integer, ConcurrentMap<String, WxStatusTmpTEntity>> removeMap = new ConcurrentHashMap<Integer, ConcurrentMap<String, WxStatusTmpTEntity>>();
	
	
	/**
	 */
	public ConcurrentMap<String, String> userPublicIdMap = new ConcurrentHashMap<String,String>();
	
	/**
	 * 用户部门，一个用户只能对于一个部门
	 */
	public  ConcurrentMap<String, Integer> userDeptMap = new ConcurrentHashMap<String, Integer>();
	
	//在线坐席
	public  ConcurrentMap<Integer, ConcurrentMap<String, SysUserEntity>> onLineDeptCsMap = new ConcurrentHashMap<Integer, ConcurrentMap<String, SysUserEntity>>();
	
	/**
	 * 记录座席一轮是否分配，0未分配，1已分配
	 */
	public ConcurrentMap<Integer, Integer> onlineCsAllotMap = new ConcurrentHashMap<Integer, Integer>();
	
	
	
	
	public  ConcurrentMap<String, WxStatusTmpTEntity> getServiceMap(String openId){
		Integer deptId = userDeptMap.get(openId);
		ConcurrentMap<String, WxStatusTmpTEntity> result = new ConcurrentHashMap<String, WxStatusTmpTEntity>();
		if(deptId != null){
			result = serviceMap.get(deptId);
		}
		if(result == null){
			result = new ConcurrentHashMap<String, WxStatusTmpTEntity>();
		}
		return result;
	}
	public  ConcurrentMap<String, WxStatusTmpTEntity> getServiceMap(Integer deptId){
		ConcurrentMap<String, WxStatusTmpTEntity> result = new ConcurrentHashMap<String, WxStatusTmpTEntity>();
		if(deptId != null){
			result = serviceMap.get(deptId);
		}
		if(result == null){
			result = new ConcurrentHashMap<String, WxStatusTmpTEntity>();
		}
		return result;
	}
	public  ConcurrentMap<String, SysUserEntity> getOnLineDeptCsMap(Integer deptId){
		ConcurrentMap<String, SysUserEntity> result = new ConcurrentHashMap<String, SysUserEntity>();
		if(deptId != null){
			result = onLineDeptCsMap.get(deptId);
		}
		if(result == null){
			result = new ConcurrentHashMap<String, SysUserEntity>();
		}
		return result;
	}
	public  ConcurrentMap<String, SysUserEntity> getOnLineDeptCsMap(String openId){
		Integer deptId = userDeptMap.get(openId);
		
		ConcurrentMap<String, SysUserEntity> result = new ConcurrentHashMap<String, SysUserEntity>();
		if(deptId != null){
			result = onLineDeptCsMap.get(deptId);
		}
		if(result == null){
			result = new ConcurrentHashMap<String, SysUserEntity>();
		}
		return result;
	}
	public  ConcurrentMap<String, WxStatusTmpTEntity> getRemoveMap(String openId){
		Integer deptId = userDeptMap.get(openId);
		ConcurrentMap<String, WxStatusTmpTEntity> result = new ConcurrentHashMap<String, WxStatusTmpTEntity>();
		if(deptId != null){
			result = removeMap.get(deptId);
		}
		if(result == null){
			result = new ConcurrentHashMap<String, WxStatusTmpTEntity>();
		}
		return result;
	}
	
	public  ConcurrentMap<String, WxStatusTmpTEntity> getWaitMap(String openId){
		Integer deptId = userDeptMap.get(openId);
		ConcurrentMap<String, WxStatusTmpTEntity> result = new ConcurrentHashMap<String, WxStatusTmpTEntity>();
		if(deptId != null){
			result = waitMap.get(deptId);
			if(result == null){
				result = new ConcurrentHashMap<String, WxStatusTmpTEntity>();
			}
		}
		return result;
	}
	public  WxStatusTmpTEntity getWaitEntity(String openId){
		WxStatusTmpTEntity result = null;
		try {
			ConcurrentMap<String, WxStatusTmpTEntity> deptMap = waitMap.get(userDeptMap.get(openId));
			if(deptMap != null){
				result = deptMap.get(openId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public  WxStatusTmpTEntity getServiceEntity(String openId){
		WxStatusTmpTEntity result = null;
		try {
			Integer userDept=userDeptMap.get(openId);
			if(userDept!=null){
				ConcurrentMap<String, WxStatusTmpTEntity> deptMap = serviceMap.get(userDept);
				if(deptMap != null){
					result = deptMap.get(openId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public  WxStatusTmpTEntity getRemoveEntity(String openId){
		WxStatusTmpTEntity result = null;
		try {
			Integer deptId = userDeptMap.get(openId);
			if(deptId != null){
				ConcurrentMap<String, WxStatusTmpTEntity> deptMap = removeMap.get(deptId);
				if(deptMap != null){
					result = deptMap.get(openId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	public  SysUserEntity getSysUserCsEntity(String openId){
		SysUserEntity result = null;
		try {
			Integer deptId = userDeptMap.get(openId);
			if(deptId != null){
				ConcurrentMap<String, SysUserEntity> deptMap = onLineDeptCsMap.get(deptId);
				if(deptMap != null){
					result = deptMap.get(openId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public  void putOnLineDeptCsMap(Integer deptId, String userId, SysUserEntity user){
		ConcurrentMap<String, SysUserEntity> userMap = onLineDeptCsMap.get(deptId);
		if(userMap == null){
			userMap = new ConcurrentHashMap<String, SysUserEntity>();
			onLineDeptCsMap.put(deptId, userMap);
		}
		userMap.put(userId, user);
	}
	public  void putOnLineDeptCsMap(String userId, SysUserEntity user){
		Integer deptId = userDeptMap.get(userId);
		ConcurrentMap<String, SysUserEntity> userMap = onLineDeptCsMap.get(deptId);
		if(userMap == null){
			userMap = new ConcurrentHashMap<String, SysUserEntity>();
			onLineDeptCsMap.put(deptId, userMap);
		}
		userMap.put(userId, user);
	}
	
	public  void removeOnlineDeptCsMap(Integer deptId, String userId){
		ConcurrentMap<String, SysUserEntity> userMap = onLineDeptCsMap.get(deptId);
		if(userMap != null){
			userMap.remove(userId);
		}
	}
	
	public  void removeOnlineDeptCsMap(String userId){
		ConcurrentMap<String, SysUserEntity> userMap = onLineDeptCsMap.get(userDeptMap.get(userId));
		if(userMap != null){
			userMap.remove(userId);
		}
	}
	

	public  void putWaitMap(Integer deptId, String openId, WxStatusTmpTEntity temp){
		ConcurrentMap<String, WxStatusTmpTEntity> openMap = waitMap.get(deptId);
		if(openMap == null){
			openMap = new ConcurrentHashMap<String, WxStatusTmpTEntity>();
			waitMap.put(deptId, openMap);
		}
		openMap.put(openId, temp);
	}
	public  void removeWaitMap(Integer deptId, String openId){
		ConcurrentMap<String, WxStatusTmpTEntity> openMap = waitMap.get(deptId);
		if(openMap != null){
			openMap.remove(openId);
			WaitQ waitQ=new WaitQ();
			waitQ.completetask();
		}
	}
	public  void removeWaitMap(String openId){
		ConcurrentMap<String, WxStatusTmpTEntity> openMap = waitMap.get(userDeptMap.get(openId));
		if(openMap != null){
			openMap.remove(openId);
			WaitQ waitQ=new WaitQ();
			waitQ.completetask();
		}
	}
	
	public  void putServiceMap(Integer deptId, String openId, WxStatusTmpTEntity temp){
		ConcurrentMap<String, WxStatusTmpTEntity> openMap = serviceMap.get(deptId);
		if(openMap == null){
			openMap = new ConcurrentHashMap<String, WxStatusTmpTEntity>();
			serviceMap.put(deptId, openMap);
		}
		openMap.put(openId, temp);
	}
	/**
	 * 设置服务队列
	 * @param openId
	 * @param temp
	 */
	public  void putServiceMap(String openId, WxStatusTmpTEntity temp){
		Integer deptId = userDeptMap.get(openId);
		ConcurrentMap<String, WxStatusTmpTEntity> openMap = serviceMap.get(deptId);
		if(openMap == null){
			openMap = new ConcurrentHashMap<String, WxStatusTmpTEntity>();
			serviceMap.put(deptId, openMap);
		}
		openMap.put(openId, temp);
	}
	/**
	 * 删除服务队列微信用户
	 * @param deptId
	 * @param openId
	 */
	public  void removeServiceMap(Integer deptId, String openId){
		ConcurrentMap<String, WxStatusTmpTEntity> openMap = serviceMap.get(deptId);
		if(openMap != null){
			openMap.remove(openId);
			SeatW seatW=new SeatW();
			seatW.completetask();
		}
	}
	/**
	 * 删除服务队列微信用户
	 * @param openId
	 */
	public  void removeServiceMap(String openId){
		ConcurrentMap<String, WxStatusTmpTEntity> openMap = serviceMap.get(userDeptMap.get(openId));
		if(openMap != null){
			openMap.remove(openId);
			SeatW seatW=new SeatW();
			seatW.completetask();
		}
	}
	/**
	 * 设置离去队列
	 * @param deptId
	 * @param openId
	 * @param temp
	 */
	public  void putRemoveMap(Integer deptId, String openId, WxStatusTmpTEntity temp){
		ConcurrentMap<String, WxStatusTmpTEntity> openMap = removeMap.get(deptId);
		if(openMap == null){
			openMap = new ConcurrentHashMap<String, WxStatusTmpTEntity>();
			removeMap.put(deptId, openMap);
		}
		openMap.put(openId, temp);
	}
	/**
	 * 设置离去队列
	 * @param openId
	 * @param temp
	 */
	public  void putRemoveMap(String openId, WxStatusTmpTEntity temp){
		Integer deptId = userDeptMap.get(openId);
		ConcurrentMap<String, WxStatusTmpTEntity> openMap = removeMap.get(deptId);
		if(openMap == null){
			openMap = new ConcurrentHashMap<String, WxStatusTmpTEntity>();
			removeMap.put(deptId, openMap);
		}
		openMap.put(openId, temp);
	}
	/**
	 * 删除离去队列微信用户
	 * @param deptId
	 * @param openId
	 */
	public  void removeRemoveMap(Integer deptId, String openId){
		ConcurrentMap<String, WxStatusTmpTEntity> openMap = removeMap.get(deptId);
		if(openMap != null){
			openMap.remove(openId);
		}
	}
	/**
	 * 删除离去队列微信用户
	 * @param openId
	 */
	public  void removeRemoveMap(String openId){
		ConcurrentMap<String, WxStatusTmpTEntity> openMap = removeMap.get(userDeptMap.get(openId));
		if(openMap != null){
			openMap.remove(openId);
		}
	}
	
	/**
     * 检查用户是否已经在服务状态
     *
     * @param openId
     * @return
     */
    public  WxStatusTmpTEntity getTmpTEntity(String openId) {
    	Integer deptId = userDeptMap.get(openId);
    	if(deptId == null){
    		return null;
    	}
        List<WxStatusTmpTEntity> list = new ArrayList<WxStatusTmpTEntity>();
        ConcurrentMap<String, WxStatusTmpTEntity> waitDeptMap = waitMap.get(deptId);
        ConcurrentMap<String, WxStatusTmpTEntity> serviceDeptMap = serviceMap.get(deptId);
        ConcurrentMap<String, WxStatusTmpTEntity> removeDeptMap = removeMap.get(deptId);
        
        if(waitDeptMap != null){
        	WxStatusTmpTEntity waitTemp = waitDeptMap.get(openId);
        	if(waitTemp != null){
        		list.add(waitTemp);
        	}
        }
        if(serviceDeptMap != null){
        	WxStatusTmpTEntity serviceTemp = serviceDeptMap.get(openId);
        	if(serviceTemp != null){
        		list.add(serviceTemp);
        	}
        }
        if(removeDeptMap != null){
        	WxStatusTmpTEntity removeTemp = removeDeptMap.get(openId);
        	if(removeTemp != null){
        		list.add(removeTemp);
        	}
        }
       
        WxStatusTmpTEntity result = null;
        if (list.size() > 1) {
            logger.info("排队的map数据发生了异常.size>1");
        } else if(list.size()  == 1){
        	result = list.get(0);
        }
        return result;
    }
	
    /**
     * 初始化导航菜单
     */
	public  void initNavMenu(){
		String navmId = null;//主键，-1第一次发送消息，0人工服务器，1路口截图。。。
		String navmKeyValue = null;//导航键盘值
		String navmKeyName = null;//导航键盘名称，如：【1】高速路况查询
		Integer navmLevel = null;//等级
		Integer navmDeptId = null;//部门ID
		Integer navmType = null;//类型：0人工服务，1普通消息，2url消息
		String navmMsgTitle = null;//发送消息标题
		String navmMsgDesc = null;//发送消息描述，如果是普通消息，则为发送内容
		String navmMsgUrl = null;//发送消息url
		
		//高速路况查询
		navmId = "0-交流会";
		navmKeyValue = "交流会";
		navmKeyName = "【1】交流会";
		navmLevel = 1;
		navmDeptId = null;
		navmType  = 3;
		navmMsgTitle = "交流会";
		navmMsgDesc = "交流会详情，请点击";
		navmMsgUrl = "http://cq96096.cn//meeting/verify?openId=openIdReplaceAll";
		setNavMenuEntity(navmId, navmKeyValue, navmKeyName, navmLevel,
				navmDeptId, navmType, navmMsgTitle, navmMsgDesc, navmMsgUrl);
		
		//高速路况查询
		navmId = "0-路况订阅";
		navmKeyValue = "路况订阅";
		navmKeyName = "【1】路况订阅";
		navmLevel = 1;
		navmDeptId = null;
		navmType  = 2;
		navmMsgTitle = "路况订阅";
		navmMsgDesc = "路况订阅详情，请点击";
		navmMsgUrl = "http://cq96096.cn/videoRoad/home?openId=openIdReplaceAll";
		setNavMenuEntity(navmId, navmKeyValue, navmKeyName, navmLevel,
				navmDeptId, navmType, navmMsgTitle, navmMsgDesc, navmMsgUrl);
		
		//第一次发送消息
		navmId = "-1";
		navmKeyValue = "-1";
		navmKeyName = "订阅";
		navmLevel = -1;
		navmDeptId = null;
		navmType  = 1;
		navmMsgTitle = null;
		navmMsgDesc = "重庆交通APP由重庆市交通委员会官方出品，旨在为您提供便捷出行信息服务。全面的路况信息、高速公路视频截图，城区重点路段实时视频，公交到站信息、失物招领、旅游巴士及游轮购票、航班到离港信息、火车票信息、自驾游攻略，安装一个APP全部搞定。\n体验请戳→<a href=\"http://mp.weixin.qq.com/s/WAjJufx05qoqz4dsJgrTqQ\">打开链接</a>";
		navmMsgUrl = null;
		setNavMenuEntity(navmId, navmKeyValue, navmKeyName, navmLevel,
				navmDeptId, navmType, navmMsgTitle, navmMsgDesc, navmMsgUrl);
		
		//第一次发送消息
		navmId = "0";
		navmKeyValue = "0";
		navmKeyName = "首次加载";
		navmLevel = -1;
		navmDeptId = null;
		navmType  = 1;
		navmMsgTitle = null;
//		navmMsgDesc = "请选择服务项：\n<a href=\"http://cq96096.cn/videoImg/list?type=2&openId=openIdReplaceAll\">【1】高速路况查询</a>"
//				+ "\n<a href=\"http://wxm.pmpsys.cn/wx-073f946c-0273/index.jspx\">【2】长途汽车购票</a>"
//				+ "\n<a href=\"http://web.chelaile.net.cn/wwd/index?src=webapp_weixin_chongqing\">【3】公交车查询</a>"
//				+ "\n<a href=\"###\">【4】轨道信息</a>"
//				+ "\n<a href=\"http://mobile.12306.cn/weixin/wxcore/init\">【5】火车票查询</a>"
//				+ "\n<a href=\"http://m.veryzhun.com/flight/?token=5cf2036c3db9fe08a7ee0c9b2077d37d\">【6】航班信息查询</a>"
//				+ "\n<a href=\"###\">【7】其他信息查询</a>"
//				+ "\n【8】高速公路事故报警"
//				+ "\n【0】人工服务";
		navmMsgDesc = "请选择服务项：\n<a href=\"http://cq96096.cn/videoImg/list?type=2&openId=openIdReplaceAll\">【1】高速路况查询</a>"
				+ "\n<a href=\"http://wxm.pmpsys.cn/wx-073f946c-0273/index.jspx\">【2】长途汽车订票或查询</a>"
				+ "\n【0】人工服务";
		navmMsgUrl = null;
		setNavMenuEntity(navmId, navmKeyValue, navmKeyName, navmLevel,
				navmDeptId, navmType, navmMsgTitle, navmMsgDesc, navmMsgUrl);
		//高速路况查询
		navmId = "0-1";
		navmKeyValue = "1";
		navmKeyName = "【1】高速路况查询";
		navmLevel = 1;
		navmDeptId = null;
		navmType  = 2;
		navmMsgTitle = "路况与截图";
		navmMsgDesc = "获取路况与截图信息，请点击";
		navmMsgUrl = "http://cq96096.cn/videoImg/list?type=2&openId=openIdReplaceAll";
		setNavMenuEntity(navmId, navmKeyValue, navmKeyName, navmLevel,
				navmDeptId, navmType, navmMsgTitle, navmMsgDesc, navmMsgUrl);
		//高速路况查询
		navmId = "0-2";
		navmKeyValue = "2";
		navmKeyName = "长途汽车订票或查询";
		navmLevel = 1;
		navmDeptId = null;
		navmType  = 2;
		navmMsgTitle = "长途汽车订票或查询";
		navmMsgDesc = "长途汽车订票或查询，请点击";
		navmMsgUrl = "http://wxm.pmpsys.cn/wx-073f946c-0273/index.jspx";
		setNavMenuEntity(navmId, navmKeyValue, navmKeyName, navmLevel,
				navmDeptId, navmType, navmMsgTitle, navmMsgDesc, navmMsgUrl);
		
		//人工服务
//		navmId = "0-0";
//		
//		navmKeyValue = "0";
//		navmKeyName = "【0】其它";
//		navmLevel = 2;
//		navmDeptId = 5;
//		navmType  = 0;
//		navmMsgTitle = null;
//		navmMsgDesc = null;
//		navmMsgUrl = null;
//		setNavMenuEntity(navmId, navmKeyValue, navmKeyName, navmLevel,
//				navmDeptId, navmType, navmMsgTitle, navmMsgDesc, navmMsgUrl);
		
		navmId = "0-0";
		navmKeyValue = "0";
		navmKeyName = "首次加载";
		navmLevel = -1;
		navmDeptId = null;
		navmType  = 1;
		navmMsgTitle = null;
		navmMsgDesc = "请选择服务项：\n【1】公交咨询与投诉"
				+ "\n【2】出租车咨询、寻物及投诉"
				+ "\n【0】其它服务";
		navmMsgUrl = null;
		setNavMenuEntity(navmId, navmKeyValue, navmKeyName, navmLevel,
				navmDeptId, navmType, navmMsgTitle, navmMsgDesc, navmMsgUrl);
		
		navmId = "0-0-1";
		navmKeyValue = "1";
		navmKeyName = "【2】公交咨询与投诉";
		navmLevel = 2;
		navmDeptId = 12;
		navmType  = 0;
		navmMsgTitle = null;
		navmMsgDesc = null;
		navmMsgUrl = null;
		setNavMenuEntity(navmId, navmKeyValue, navmKeyName, navmLevel,
				navmDeptId, navmType, navmMsgTitle, navmMsgDesc, navmMsgUrl);
		
		//人工服务：出租车咨询、寻物及投诉
		navmId = "0-0-2";
		navmKeyValue = "2";
		navmKeyName = "【1】出租车咨询、寻物及投诉";
		navmLevel = 2;
		navmDeptId = 6;
		navmType  = 0;
		navmMsgTitle = null;
		navmMsgDesc = null;
		navmMsgUrl = null;
		setNavMenuEntity(navmId, navmKeyValue, navmKeyName, navmLevel,
				navmDeptId, navmType, navmMsgTitle, navmMsgDesc, navmMsgUrl);
		
		navmId = "0-0-0";
		navmKeyValue = "1";
		navmKeyName = "【0】其它服务";
		navmLevel = 2;
		navmDeptId = null; // 这里被我改了 5 改成了null
		navmType  = 0;
		navmMsgTitle = null;
		navmMsgDesc = null;
		navmMsgUrl = null;
		setNavMenuEntity(navmId, navmKeyValue, navmKeyName, navmLevel,
				navmDeptId, navmType, navmMsgTitle, navmMsgDesc, navmMsgUrl);
		
//		//公交车查询
//		navmId = "0-3";
//		navmKeyValue = "3";
//		navmKeyName = "公交车查询";
//		navmLevel = 1;
//		navmDeptId = null;
//		navmType  = 2;
//		navmMsgTitle = "公交车查询";
//		navmMsgDesc = "公交车查询，请点击";
//		navmMsgUrl = "http://web.chelaile.net.cn/wwd/index?src=webapp_weixin_chongqing";
//		setNavMenuEntity(navmId, navmKeyValue, navmKeyName, navmLevel,
//				navmDeptId, navmType, navmMsgTitle, navmMsgDesc, navmMsgUrl);
//		//轨道信息
//		navmId = "0-4";
//		navmKeyValue = "4";
//		navmKeyName = "轨道信息";
//		navmLevel = 1;
//		navmDeptId = null;
//		navmType  = 1;
//		navmMsgTitle = "轨道信息";
//		navmMsgDesc = "正在建设中";
//		navmMsgUrl = "http://web.chelaile.net.cn/wwd/index?src=webapp_weixin_chongqing";
//		setNavMenuEntity(navmId, navmKeyValue, navmKeyName, navmLevel,
//				navmDeptId, navmType, navmMsgTitle, navmMsgDesc, navmMsgUrl);
//		//火车票查询
//		navmId = "0-5";
//		navmKeyValue = "5";
//		navmKeyName = "火车票查询";
//		navmLevel = 1;
//		navmDeptId = null;
//		navmType  = 2;
//		navmMsgTitle = "火车票查询";
//		navmMsgDesc = "火车票查询，请点击";
//		navmMsgUrl = "http://mobile.12306.cn/weixin/wxcore/init";
//		setNavMenuEntity(navmId, navmKeyValue, navmKeyName, navmLevel,
//				navmDeptId, navmType, navmMsgTitle, navmMsgDesc, navmMsgUrl);
//		//航班信息查询
//		navmId = "0-6";
//		navmKeyValue = "6";
//		navmKeyName = "航班信息查询";
//		navmLevel = 1;
//		navmDeptId = null;
//		navmType  = 2;
//		navmMsgTitle = "航班信息查询";
//		navmMsgDesc = "航班信息查询，请点击";
//		navmMsgUrl = "http://m.veryzhun.com/flight/?token=5cf2036c3db9fe08a7ee0c9b2077d37d";
//		setNavMenuEntity(navmId, navmKeyValue, navmKeyName, navmLevel,
//				navmDeptId, navmType, navmMsgTitle, navmMsgDesc, navmMsgUrl);
//		
//		//航班信息查询
//		navmId = "0-7";
//		navmKeyValue = "7";
//		navmKeyName = "其他信息查询";
//		navmLevel = 1;
//		navmDeptId = null;
//		navmType  = 1;
//		navmMsgTitle = "其他信息查询";
//		navmMsgDesc = "正在建设中...";
//		navmMsgUrl = "http://m.veryzhun.com/flight/?token=5cf2036c3db9fe08a7ee0c9b2077d37d";
//		setNavMenuEntity(navmId, navmKeyValue, navmKeyName, navmLevel,
//				navmDeptId, navmType, navmMsgTitle, navmMsgDesc, navmMsgUrl);
//		//人工服务
//		navmId = "0-0";
//		navmKeyValue = "0";
//		navmKeyName = "人工服务";
//		navmLevel = 1;
//		navmDeptId = null;
//		navmType  = 1;
//		navmMsgTitle = null;
//		navmMsgDesc = "请选择服务项：\n【1】出租车咨询、寻物及投诉"
//				+ "\n【2】公交咨询与投诉"
//				+ "\n【0】其他"
//				+ "\n【*】返回上层菜单"
//				+ "\n【#】返回首层菜单";
//		navmMsgUrl = null;
//		setNavMenuEntity(navmId, navmKeyValue, navmKeyName, navmLevel,
//				navmDeptId, navmType, navmMsgTitle, navmMsgDesc, navmMsgUrl);
//		//人工服务：出租车咨询、寻物及投诉
//		navmId = "0-0-1";
//		navmKeyValue = "1";
//		navmKeyName = "【1】出租车咨询、寻物及投诉";
//		navmLevel = 2;
//		navmDeptId = 6;
//		navmType  = 0;
//		navmMsgTitle = null;
//		navmMsgDesc = null;
//		navmMsgUrl = null;
//		setNavMenuEntity(navmId, navmKeyValue, navmKeyName, navmLevel,
//				navmDeptId, navmType, navmMsgTitle, navmMsgDesc, navmMsgUrl);
//		//人工服务：出租车咨询、寻物及投诉
//		navmId = "0-0-2";
//		navmKeyValue = "2";
//		navmKeyName = "【2】公交咨询与投诉";
//		navmLevel = 2;
//		navmDeptId = 12;
//		navmType  = 0;
//		navmMsgTitle = null;
//		navmMsgDesc = null;
//		navmMsgUrl = null;
//		setNavMenuEntity(navmId, navmKeyValue, navmKeyName, navmLevel,
//				navmDeptId, navmType, navmMsgTitle, navmMsgDesc, navmMsgUrl);
//		navmId = "0-0-0";
//		navmKeyValue = "0";
//		navmKeyName = "【0】其它";
//		navmLevel = 2;
//		navmDeptId = 5;
//		navmType  = 0;
//		navmMsgTitle = null;
//		navmMsgDesc = null;
//		navmMsgUrl = null;
//		setNavMenuEntity(navmId, navmKeyValue, navmKeyName, navmLevel,
//				navmDeptId, navmType, navmMsgTitle, navmMsgDesc, navmMsgUrl);
	}

	private  void setNavMenuEntity(String navmId, String navmKeyValue,
			String navmKeyName, Integer navmLevel, Integer navmDeptId,
			Integer navmType, String navmMsgTitle, String navmMsgDesc,
			String navmMsgUrl) {
		NavMenuEntity nav = new NavMenuEntity();
		nav.setNavmId(navmId);
		nav.setNavmKeyValue(navmKeyValue);
		nav.setNavmKeyName(navmKeyName);
		nav.setNavmLevel(navmLevel);
		nav.setNavmDeptId(navmDeptId);
		nav.setNavmType(navmType);
		nav.setNavmMsgTitle(navmMsgTitle);
		nav.setNavmMsgDesc(navmMsgDesc);
		nav.setNavmMsgUrl(navmMsgUrl);
		navMenuMap.put(navmId, nav);
	}

	public List<SysUserEntity> getSysUserOrder(Integer deptId){
		ConcurrentMap<String, SysUserEntity> deptCsMap = getOnLineDeptCsMap(deptId);
		List<SysUserEntity> result = new ArrayList<SysUserEntity>();
		if(deptCsMap.size() >0){
			//计算座席服务数
			Map<String, Integer> seatMap = new HashMap<String,Integer>();
			ConcurrentMap<String, WxStatusTmpTEntity> serviceMap = getServiceMap(deptId);
			for (String openId : serviceMap.keySet()) {
				WxStatusTmpTEntity entity = serviceMap.get(openId);
	        	//2、通道已建立，正在聊天；1、通道未建立，已经分配，坐席没有点击建立通道
	        	 if (2 == entity.getServiceStatus().intValue()||1 == entity.getServiceStatus().intValue()) {//正在通话的客户及座席
	        		 Integer seat = seatMap.get(entity.getCsId());
	        		 if(seat == null){
	        			 seat = 0;
	        		 }
	        		 seat ++;
	        		 seatMap.put(entity.getCsId(), seat);
	        	 }
			}
			
			//计算座席空闲率
			for (String csId : deptCsMap.keySet()) {
				SysUserEntity user = deptCsMap.get(csId);
				Integer serviceNum = seatMap.get(csId);
				if(serviceNum == null){
					serviceNum = 0;
				}
				user.setServiceCustomerNum(serviceNum);
				//计算空闲率
	   		 	double freeNum = (double)(user.getCustomerNum() - user.getServiceCustomerNum()) / (double)user.getCustomerNum();
	   		 	user.setServiceFreeNum(freeNum);
				result.add(user);
			}

			Collections.sort(result, new Comparator<SysUserEntity>() {
	            public int compare(SysUserEntity arg0, SysUserEntity arg1) {//降序排序 || 现在改成升序排序
	            	int flag = arg1.getCustomerNum().compareTo(arg0.getCustomerNum());//先根据总服务数排序
	            	if(flag == 0){
	            		flag = arg1.getServiceFreeNum().compareTo(arg0.getServiceFreeNum());//在根据空闲率排序
	            	}
	                return flag;
	            }
	        });
		}
		
		return result;
	}
	
	public Integer getAllotCsId(List<SysUserEntity> userList, int count){
		Integer result = null;
		if(userList != null && userList.size() > 0 && count < 4){
			for (SysUserEntity user : userList) {
				Integer csId = user.getId();
				
				Integer isAllot = onlineCsAllotMap.get(csId);
				if(isAllot == null){
					isAllot = 0;
				}
				if(isAllot == 0 && user.getServiceFreeNum() > 0 && !"关闭服务".equals(user.getStatus())){
					result = csId;
					onlineCsAllotMap.put(csId, 1);
					
					
					
					break;
				}
			}
			count++;
			if(result == null){
				onlineCsAllotMap.clear();
				result =getAllotCsId(userList, count);
			}
		}
		return result;
	}
	public boolean weixinAllotCs(WxStatusTmpTEntity entity, List<SysUserEntity> userList){
		boolean result = false;
		if(userList.size() >0){
			String openId = entity.getWxOpenid();
			int count  = 0;
			Integer allotCsId = getAllotCsId(userList, count);
			if(allotCsId != null){
				//删除等待队列
				removeWaitMap(openId);
		        entity.setServiceStatus(1);
		        entity.setCsId(String.valueOf(allotCsId));
		        entity.setAllocTime(System.currentTimeMillis());//设置分配时间
		        entity.setIntoWaitingMapTime(System.currentTimeMillis()/1000);//重置等待时间
		        entity.setWaitingHintNum(0);//重置等待发送消息次数
		        //添加服务队列
		        putServiceMap(openId, entity);
		        result = true;
			}

		}
		return result;
	}
	
	/**
	 * 等待队列排队
	 * @param deptId
	 * @return
	 */
	public List<WxStatusTmpTEntity> getWaitTempEntityOrder(Integer deptId){
		List<WxStatusTmpTEntity> result = new ArrayList<WxStatusTmpTEntity>();
		Map<String, WxStatusTmpTEntity> map=this.waitMap.get(deptId);
		if(map != null){
			for (String openId : map.keySet()) {
				result.add(map.get(openId));
			}
			
			Collections.sort(result, new Comparator<WxStatusTmpTEntity>() {
	            public int compare(WxStatusTmpTEntity arg0, WxStatusTmpTEntity arg1) {
	            	int flag = arg1.getIntoWaitingTime().compareTo(arg0.getIntoWaitingTime());
	                return flag;
	            }
	        });
		}
		return result;
	}
	
	/**
	 * 通过部门ID和微信用户ID查询当前等待用户前面的排队数量
	 * @param deptId 部门ID
	 * @param openId 微信用户ID
	 * @return 当前等待用户前面的排队数量
	 */
	public int getFrontWaitNum(String openId){
		int count=0;
		Map<String, WxStatusTmpTEntity> map=this.waitMap.get(userDeptMap.get(openId));
		int comeTime=map.get(openId).getIntoWaitingTime().intValue();
		if(map!=null){
			for(String key:map.keySet()){
				WxStatusTmpTEntity ws=map.get(key);
				if(ws.getIntoWaitingTime().intValue()< comeTime){
					count++;
				}
			}
		}
		return count;
	}
}
