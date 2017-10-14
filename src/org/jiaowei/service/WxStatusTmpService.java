package org.jiaowei.service;

import org.jiaowei.common.service.CommonService;
import org.jiaowei.entity.WxStatusTmpTEntity;

/**
 * Created by alex on 15-12-31.
 */
public interface WxStatusTmpService extends CommonService{
	
	public void saveMsgDatebase(WxStatusTmpTEntity entity, String content, String wxOpenId);
	
}
