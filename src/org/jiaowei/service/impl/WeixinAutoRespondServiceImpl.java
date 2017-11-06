package org.jiaowei.service.impl;

import java.util.List;

import org.jiaowei.common.service.impl.CommonServiceImpl;
import org.jiaowei.entity.WeixinAutoRespondEntity;
import org.jiaowei.service.WeixinAutoRespondService;
import org.jiaowei.service.WeixinUserInfoService;
import org.springframework.stereotype.Service;


@Service
public class WeixinAutoRespondServiceImpl extends CommonServiceImpl implements WeixinAutoRespondService{

	@Override
	public List<WeixinAutoRespondEntity> getRespondMes(String content) {
		
//		List<WeixinAutoRespondEntity> result = findByHql(" FROM WeixinAutoRespondEntity a WHERE a.contentCode = 0 AND a.content LIKE '%"+content+"%'");
		List<WeixinAutoRespondEntity> result = findBySQL("SELECT * FROM AUTO_RESPOND WHERE AUTO_RESPOND.CONTENT_CODE IN("
				+ " SELECT "
				+ "	b.JUNIOR_ID "
				+ " FROM "
				+ "			( SELECT "
				+ "					wm_concat (A .\"CONTENT\") AS CTT, "
				+ "					A .JUNIOR_ID "
				+ "			  FROM "
				+ "					AUTO_RESPOND A "
				+ "			  WHERE "
				+ " 				A .CONTENT_CODE = 0 "
				+ "			  GROUP BY "
				+ "					A .JUNIOR_ID "
				+ "			) b "
				+ " WHERE "
				+ "		b.CTT LIKE '%"+content+"%'"
						+ "	) ",WeixinAutoRespondEntity.class);
		return result;
	}

	@Override
	public List<WeixinAutoRespondEntity> getRespondMenu() {
		List<WeixinAutoRespondEntity> result = findByHql(" FROM WeixinAutoRespondEntity a WHERE a.contentCode = 0");
		return result;
	}

	@Override
	public List<WeixinAutoRespondEntity> getJuniorMenu(String juniorID) {
		List<WeixinAutoRespondEntity> result = findByHql(" FROM WeixinAutoRespondEntity a WHERE a.contentCode = '"+juniorID+"'");
		return result;
	}
	
}
