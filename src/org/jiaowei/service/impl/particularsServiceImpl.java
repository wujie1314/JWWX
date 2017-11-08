package org.jiaowei.service.impl;

import java.util.Map;

import org.jiaowei.common.service.impl.CommonServiceImpl;
import org.jiaowei.service.particularsService;
import org.springframework.stereotype.Service;

@Service
public class particularsServiceImpl extends CommonServiceImpl implements particularsService  {
	
	@Override
	public Map<String, Object> init(String tellID){
		String tellSql = "SELECT BBS_TELL.CONTENT, "
					+ "BBS_TELL.PUBLISHEDTIME, "
					+ "BBS_TELL.COMMENTSNUMBER, "
					+ "BBS_USER.WECHATNAME, "
					+ "BBS_USER.HEADIMAGE "
					+ "FROM BBS_TELL,BBS_USER WHERE BBS_TELL.ID = '"+ tellID +"'";
		String picSell = "SELECT IMMEDIATE FROM BBS_PICTURE WHERE BBS_PICTURE.TELLID = '"+ tellID +"'";
		String commentSql = "";
		return null;
	}
}
