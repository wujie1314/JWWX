package org.jiaowei.service.impl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jiaowei.common.service.impl.CommonServiceImpl;
import org.jiaowei.entity.BbsCommentariesEntity;
import org.jiaowei.entity.BbsTellEntity;
import org.jiaowei.entity.BbsUserEntity;
import org.jiaowei.service.particularsService;
import org.springframework.stereotype.Service;

@Service
public class particularsServiceImpl extends CommonServiceImpl implements particularsService  {
	
	@Override
	
	public Map<String, Object> init(String tellID){
		String tellSql = "SELECT BBS_TELL.CONTENT, "
					+ " to_char(BBS_TELL.PUBLISHEDTIME,'YYYY-MM-DD HH24:MI:SS') AS PUBLISHEDTIME, " 
					+ " BBS_TELL.COMMENTSNUMBER, "
					+ " BBS_USER.WECHATNAME, "
					+ " BBS_USER.HEADIMAGE "
					+ " FROM BBS_TELL,BBS_USER WHERE BBS_TELL.ID = '"+ tellID +"' AND BBS_TELL.USERID = BBS_USER.ID ";
		String picSell = "SELECT BBS_PICTURE.IMGDATA FROM BBS_PICTURE WHERE BBS_PICTURE.TELLID = '"+ tellID +"'";
		String commentSql = "SELECT to_char(BBS_COMMENTARIES.COMMENTSTIME,'YYYY-MM-DD HH24:MI:SS') AS COMMENTSTIME,BBS_COMMENTARIES.CONTENT,BBS_USER.HEADIMAGE,BBS_USER.WECHATNAME "
					+ "FROM BBS_COMMENTARIES,BBS_USER WHERE BBS_COMMENTARIES.TELLID = '"+ tellID+"' AND BBS_COMMENTARIES.COMMENTSID = BBS_USER.ID";
		List<Object> tellList = findBySQL(tellSql);
		List<Object> picList = findBySQL(picSell);
		List<Object> commentList = findBySQL(commentSql);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tell", tellList);
		map.put("pic", picList);
		map.put("comment", commentList);

		return map;
	}
	
	@Override 
	public  Map<String, Object> review(String tellID,String reviewData, String openID){
		BbsCommentariesEntity BCE = new BbsCommentariesEntity();
		Map<String, Object> map = new HashMap<>();
		String commentSID = getReviewID(openID);
		
		if(commentSID == null){
			map.put("result", "error");
			return map;
		}
		BCE.setCommentsID(commentSID);
		BCE.setTellID(tellID);
		BCE.setContent(reviewData);
		BCE.setId(Calendar.getInstance().getTimeInMillis() +"");
		BCE.setCommentsTime(new Timestamp(System.currentTimeMillis()));
		save(BCE);
		updateTell(tellID);
		map.put("result", "success");
		return map;
	}
	public void updateTell(String tellID){
		String sql = " SELECT * FROM BBS_TELL WHERE BBS_TELL.ID = '"+ tellID +"'";
		List<BbsTellEntity> list = findBySQL(sql, BbsTellEntity.class);
		BbsTellEntity btl = new BbsTellEntity();
		if(list != null)
			if(list.size() > 0){
				btl = list.get(0);
				btl.setCommentsNumber(btl.getCommentsNumber() + 1);
				save(btl);
			}
	}
	
	public String getReviewID(String openID){
		String sql = "";
		sql += "SELECT * FROM BBS_USER WHERE BBS_USER.OPPENID = '"+ openID + "'";
		List<BbsUserEntity> list = findBySQL(sql, BbsUserEntity.class);
		
		if(list != null && list.size() > 0){
			return list.get(0).getId();
		}
 		return null;
	}
}
