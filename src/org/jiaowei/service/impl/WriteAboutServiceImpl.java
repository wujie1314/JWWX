package org.jiaowei.service.impl;



import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.jiaowei.common.service.impl.CommonServiceImpl;
import org.jiaowei.entity.BbsPictureEntity;
import org.jiaowei.entity.BbsTellEntity;
import org.jiaowei.entity.BbsUserEntity;
import org.jiaowei.service.WriteAboutService;
import org.jiaowei.service.mineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import Decoder.BASE64Decoder;

@Service
public class WriteAboutServiceImpl extends CommonServiceImpl implements WriteAboutService{
	 @Autowired
	 private mineService mineService;
	 
	//保存用户的帖子
	@Override
	public String announce(HttpServletRequest request,List<String> imgFile,String oppennID,String content,String title){
		String BbsTellEntityID = Calendar.getInstance().getTimeInMillis() +""; //说说的主键
		String userID = getUserID(oppennID);
		if (saveWriteAbout(BbsTellEntityID,userID,content,title,"0")) {
			for (int i = 0; i < imgFile.size(); i++) {
				String fileName = savePicture(request,imgFile.get(i));
				savePictrueEntity(BbsTellEntityID,fileName,oppennID);
			}
		}
		return "0";
	}
	
	//保存说说
	public boolean saveWriteAbout(String BbsTellEntityID,String userID,String content,String title,String state){
		BbsTellEntity b = new BbsTellEntity();
		b.setUserID(userID);
		b.setPublishedTime(new Timestamp(System.currentTimeMillis()));
		b.setContent(content);
		b.setCommentsNumber(0);
		b.setId(BbsTellEntityID);
		b.setTitle(title);
		b.setState(state);
		save(b);
		return true;
	}
	//得到uerID
	public String getUserID(String openID){
		String sql = "";
		sql += "SELECT * FROM BBS_USER WHERE BBS_USER.OPPENID = '"+ openID + "'";
		List<BbsUserEntity> list = findBySQL(sql, BbsUserEntity.class);
		
		if(list != null && list.size() > 0){
			return list.get(0).getId();
		}
 		return null;
	}
	//保存图片信息
	public boolean savePictrueEntity(String BbsTellEntityID,String fileName,String userID){
		BbsPictureEntity bp = new BbsPictureEntity();
		String BbsPictureEntityID= Calendar.getInstance().getTimeInMillis()+ ""; //图片的主键
		String imgPath = "/uploads/" + fileName;
		bp.setId(BbsPictureEntityID);
		bp.setFileName(fileName);
		bp.setPath(imgPath);
		bp.setTellId(BbsTellEntityID);
		
	    //使用BASE64对图片文件数据进行解码操作 
	    //int index = imgData.indexOf(";base64,");  
		//String imgDataState = imgData.substring("data:image/".length(), index); //图片的类型
		//bp.setImgData(imgData);
		//bp.setImgDataState(imgDataState);
		save(bp);
		return true;
	}
	/*
	 *保存图片在本地*/
	public String savePicture(HttpServletRequest request,String imgFile) {
		   //在自己的项目中构造出一个用于存放用户照片的文件夹  
	      String imgPath = request.getServletContext().getRealPath("/uploads/");
	      //String imgPath = "D:/uploads/";
	      String fileName = "IMAGE_" + String.valueOf(Calendar.getInstance().getTime().getTime()) + ".jpg";
	      //String fileName = "a.jpg";
	       //如果此文件夹不存在则创建一个  
	       File f = new File(imgPath);  
	       if(!f.exists()){  
	           f.mkdir();  
	       }  
	      //拼接文件名称，不存在就创建  
	       imgPath =  imgPath + fileName;
	         
	       //使用BASE64对图片文件数据进行解码操作 
	       int index = imgFile.indexOf(";base64,");  
	       String base64Str = imgFile.substring(index + ";base64,".length());  
	       BASE64Decoder decoder = new BASE64Decoder();  
	       try {  
	            byte[] b = decoder.decodeBuffer(base64Str);  
	            for(int i=0;i<b.length;++i)  
	            {  
	                if(b[i]<0)  
	                {//调整异常数据  
	                    b[i]+=256;  
	                }  
	            }  
	            OutputStream out = new FileOutputStream(imgPath);      
	            out.write(b);  
	            out.flush();  
	            out.close();  
	       } catch (IOException e) {
	    	   System.out.println(e);
	       }  
	       return fileName;  
	}
	
	//保存专家发布的说说
	@Override
	public String specialist(HttpServletRequest request,List<String> fileNames,String oppennID,String content,
			String name,String title,String userOpenID){
		initSpecialist(oppennID,name); //初始化专家数据
		mineService.initUser(request, userOpenID);//初始化用户
		String userID = getUserID(userOpenID);
		String BbsTellEntityID = Calendar.getInstance().getTimeInMillis() +""; //说说的主键

		if (saveWriteAbout(BbsTellEntityID,userID,content,title,"1")) {
			for (int i = 0; i < fileNames.size(); i++) {
				savePictrueEntity(BbsTellEntityID,fileNames.get(i),oppennID);
			}
		}
		return BbsTellEntityID;
	}
	
	//
	public String initSpecialist(String openID,String name){
		if(openID != null){
			String id = isExistUser(openID);
			if(id == null)
				return SaveUser(openID,name);
			else {
				return id;
			}
			
		}
		return "error";
	}
	
	private String isExistUser(String openID) {
		String sql = "SELECT * FROM BBS_USER WHERE BBS_USER.OPPENID = '" + openID + "'";
		List<BbsUserEntity> list = findBySQL(sql, BbsUserEntity.class);
		if(list != null && list.size() != 0){
			return list.get(0).getId();
		}
		return null;
	}

	//保存用户
	public String SaveUser(String openID,String name){
		BbsUserEntity bus = new BbsUserEntity();
		String id = Calendar.getInstance().getTimeInMillis() +"";
		bus.setId(id);
		bus.setState("1");
		bus.setOppenid(openID);
		bus.setWechatName(name);
		bus.setHeadImage("/uploads/defaultSpecialist.jpg");
		//String headImageData =getImgStr(getImageByUrl(jsonObject.getString("headimgurl"),"D:/uploads",jsonObject.getString("openid")));
		//System.out.println("===" + headImageData);
		//bus.setHeadImage(headImageData);
		save(bus);
		return id;
	}
	
	
}
