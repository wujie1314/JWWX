package org.jiaowei.util;

import java.util.ArrayList;
import java.util.List;

import org.jiaowei.entity.MsgFromCustomerServiceEntity;
import org.jiaowei.entity.MsgFromWxEntity;

public class ListUtils {
 
	public static List<Object> getTimeOrderFor2List(List<MsgFromCustomerServiceEntity> list1,List<MsgFromWxEntity> list2){
        List<Object> list = new ArrayList<Object>();
		if (null == list1 || 0 == list1.size()) {
            for (MsgFromWxEntity fromWxEntity : list2) {
                list.add(fromWxEntity);
            }
        } else if (null == list2 || 0 == list2.size()) {
            for (MsgFromCustomerServiceEntity serviceEntity : list1) {
                list.add(serviceEntity);
            }
        } else {
            MsgFromCustomerServiceEntity serviceEntity = null;
            MsgFromWxEntity msgFromWxEntity = null;
            for (int k = 0; k < (list1.size() + list2.size());) {
            	if(list1.size()>0)serviceEntity = list1.get(0);
            	if(list2.size()>0)msgFromWxEntity = list2.get(0);
            	//微信用户发送时间比座席早
            	if (serviceEntity.getCreateTime().getTime() > msgFromWxEntity.getCreateTime().getTime()) {
            		if(list2.size()>0){//微信用户数据未取完，取微信用户数据
                        list.add(msgFromWxEntity);
                        list2.remove(0);
            		}else{//微信用户数据已取完，取座席数据
            			list.add(serviceEntity);
                    	list1.remove(0);
            		}
                }else{//微信用户发送时间比座席晚
                	if(list1.size()>0){//座席数据未取完，取座席用户数据
                    	list.add(serviceEntity);
                    	list1.remove(0);
                	}else{//座席数据已取完，取微信用户数据
                		list.add(msgFromWxEntity);
                        list2.remove(0);
                	}
                }
            }
        }
		return list;
	}
}
