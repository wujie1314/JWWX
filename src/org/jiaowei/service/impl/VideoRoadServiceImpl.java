package org.jiaowei.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jiaowei.common.service.impl.CommonServiceImpl;
import org.jiaowei.service.VideoRoadService;
import org.jiaowei.util.CommonConstantUtils;
import org.jiaowei.util.StringUtil;
import org.springframework.stereotype.Service;

@Service("videoRoadServiceImpl")
public class VideoRoadServiceImpl  extends CommonServiceImpl implements VideoRoadService {
	private static Logger logger = Logger.getLogger(VideoRoadServiceImpl.class);
	private static String VIDEO_ROAD_IMG_PATH = CommonConstantUtils.getVideoRoadImgPath();
	@Override
	public List<Object[]> queryVideoImgObjectList(Integer videType,
			String keyword, Long parentId, Integer isHot, Long videId,
			String openId, String isCollect, String videIds) {
		
		List<Object[]> objList = null;
		try {
			StringBuffer sql = new StringBuffer("select videId,videShowName,videUrl,videDiagramUrl,videOldUrl,videType,collWxOpenId,videName from video_img left join video_img_collect on videId = collvideId and collWxOpenId=:collWxOpenId where 1=1");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("collWxOpenId", openId);
			if(videId != null){
				sql.append(" and videId = :videId ");
				params.put("videId", videId);
			}
			if(videIds != null){
				sql.append(" and videId in( "+videIds+" )");
			}
			if(videType != null){
				sql.append(" and videtype = :videType ");
				params.put("videType", videType);
			}
			if(parentId != null){
				if(parentId == -1){
					sql.append(" and videparentid is null ");
				}else if(parentId == -2){
				}else {
					sql.append(" and videparentid = :parentId ");
					params.put("parentId", parentId);
				}
			}
			if(isHot != null){
				sql.append(" and videIsHot = :isHot");
				params.put("isHot", isHot);
			}
			if(StringUtil.isNotEmpty(keyword)){
				sql.append(" and videshowname like :keyword");
				params.put("keyword", "%"+keyword+"%");
			}
			if(isCollect != null){
				sql.append(" and videId in (select collvideid from video_img_collect where collWxOpenId = :collWxOpenId)");
				params.put("collWxOpenId", openId);
			}
			sql.append(" order by videOrder desc, videId");
//			System.out.println("sql:"+sql.toString());
			objList = findBySQL(sql.toString(), params);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("check videoImgList is error", e);
		}
		return objList;
	}

	@Override
	public List<Map<String, Object>> queryVideoImgListMap(Integer videType,
			String keyword, Long parentId, Integer isHot, Long videId,
			String openId, String isCollect) {
		List<Map<String,Object>> videoList = new ArrayList<Map<String,Object>>();
		List<Object[]> objList = queryVideoImgObjectList(videType, keyword, parentId, isHot, videId, openId, isCollect,null);
		if(objList != null && objList.size() > 0){
			parentId = parentId == null ? 0 : parentId;
			for (int i=0;i<objList.size();i++) {
				Object[] obj=objList.get(i);
				String returnStr[]={"TEST","TEST"};
				String type="";
				String oldUrl = String.valueOf(obj[4]);//原来的url只 唯一
				videType=new Integer(String.valueOf(obj[5]));
				
				if(videType==2 && parentId != -1){
					type="10100101012110000008";
					returnStr=readAllFiles(type,oldUrl);
				}else if(videType==3){
					type="10100101012110000020";
					returnStr=readAllFiles(type,oldUrl);
				}else if(videType==4){
					type="10100101012110000015";
					returnStr=readAllFiles(type,oldUrl);
				}
				if("NO".equals(returnStr[0])){
					objList.remove(i);
					i--;
				}else{
					Map<String,Object> map = new HashMap<String, Object>();
					map.put("videId", obj[0]);
					map.put("videShowName", obj[1]);
					if(videType==2 && parentId==-1){//高速一级图片已经固定死
						map.put("videUrl", VIDEO_ROAD_IMG_PATH+obj[2]);
					} else{
						map.put("videUrl", VIDEO_ROAD_IMG_PATH+"/WS/"+type+"/"+returnStr[0]+"/"+returnStr[1]);
					}
					int collent = 0;
					if(StringUtil.isNotEmpty(obj[6])){
						collent = 1;
					}
					map.put("isCollect", collent);
					map.put("openId", openId);
					map.put("collectNum", queryCollentNum(obj[0]));
					map.put("videDiagramUrl", obj[3]);
					map.put("videType", obj[5]);
					map.put("videName", obj[7]);
					if(isHintImg(type, oldUrl)){
						map.put("isHintImg", 1);
						map.put("hintImgUrl", VIDEO_ROAD_IMG_PATH+"/hint/"+type+"/"+oldUrl+"/"+oldUrl+".png");
					}
					videoList.add(map);
				}
			}
		}
		return videoList;
	}

	@Override
	public List<Map<String, Object>> queryVideoImgListMap(String type,
			String keyword, Long parentId, String openId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> queryVideoImgListMap(String isCollect,
			String videIds, String openId) {
		// TODO Auto-generated method stub
		return null;
	}

	private String[] readAllFiles(String type,String url){
		String resultStr[]={"NO","NO"};
		String path="";
		String[] urls=url.split(",");
		for(int j=0;j<urls.length;j++){
			url=urls[j];
			String filePath = CommonConstantUtils.getVideoImgUrl().replace("type", type).replace("url", url);
			File file = new File(filePath);
			if(file.exists()){
				File[] files = file.listFiles();
				for(int i=0; i<files.length; i++){
					String str = files[i].getName();
					if(str.contains(".jpg")){
						path=files[i].getName();
						resultStr[0]=url;
						resultStr[1]=path;
						return resultStr;
					}
				}
			}
		}
		return resultStr;
	}
	
	private int queryCollentNum(Object videId){
    	int result = 0;
		try {
			StringBuffer sql = new StringBuffer("select count(1) from video_img_collect t where t.collvideid = :videId");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("videId", videId);
			List<Object[]> objList = findBySQL(sql.toString(), params);
			if(objList != null && objList.size() > 0){
				result = Integer.valueOf(""+objList.get(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("check videoImgList is error", e);
		}
		return result;
    }
	
	private boolean isHintImg(String type, String url){
		String hintImgUrl = CommonConstantUtils.getVideoHintImgUrl().replace("type", type).replaceAll("url", url);
		File file = new File(hintImgUrl);
		boolean result = false;
		if(file.exists()){
			result = true;
		}
		
		return result;
	}
}
