package org.jiaowei.service.impl;

import java.sql.Timestamp;

import org.apache.log4j.Logger;
import org.jiaowei.common.service.impl.CommonServiceImpl;
import org.jiaowei.controllers.AutoSchedule;
import org.jiaowei.entity.MsgFromWxEntity;
import org.jiaowei.entity.WxStatusTmpTEntity;
import org.jiaowei.service.MsgFromWxService;
import org.jiaowei.service.WxStatusTmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by alex on 15-12-31.
 */
@Service
public class WxStatusTmpServiceImpl extends CommonServiceImpl implements WxStatusTmpService {

    @Autowired
    private MsgFromWxService msgFromWxService;
    private static Logger logger = Logger.getLogger(WxStatusTmpServiceImpl.class);
	
	@Override
	public void saveMsgDatebase(WxStatusTmpTEntity entity, String content,
			String wxOpenId) {
		// TODO Auto-generated method stub
		try {
       	 //保存信息到数据库
           MsgFromWxEntity msgFromWxEntity = new MsgFromWxEntity();
           msgFromWxEntity.setContent(content);
           msgFromWxEntity.setFromUserName(wxOpenId);
           msgFromWxEntity.setMsgType("autotext");
           msgFromWxEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
           if(entity != null){
        	   msgFromWxEntity.setToUserName(entity.getCsId());
        	   msgFromWxEntity.setWorkServiceId(entity.getMsgServiceId());
           }
           msgFromWxService.save(msgFromWxEntity);
       } catch (Exception e) {
           logger.error("MsgFromWxEntity->save发生了异常：" + e.getMessage());
       }
		
	}
	
	
}
