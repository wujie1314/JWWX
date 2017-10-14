package org.jiaowei.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "video_img", schema = "JWWX", catalog = "")
public class VideoImgEntity {
	private Long videId;
	private String videOldName;
	private String videOldId;
	private String videOldUrl;
	private String videOldPath;
	private String videGroupName;
	private String videGroupId;
//	private String videSectionName;//路段名称
//	private String videSectionShowName;//路段显示名称，一级页面显示名称
	private String videName;//视频名称，或者图片名称
	private String videShowName;//视频显示名称，二级页面
	private String videMarker1;//标识物1,视频所在路段的一端的标志物称谓
	private String videMarker2;//标识物2,视频所在路段的另外一端的标志物称谓
	private Integer videDirection = 0;//方向，0、标识物1向标识物2方向。1、标识物2向标识物1方向。
	private String videNo;//桩号/门牌号，公路桩号
	private String videLongitude;//经度
	private String videLatitude;//纬度
	private String videUrl;//视频显示图片地址
	private String videDiagramUrl;//方向示意图
	private Long videParentId;//父类ID
	private Integer videIsHot;//热点：0不是，1是
	private Integer videType;//0高速，1站台
	
	private Integer isHintImg = 0;  //0没有方向示意图，1有示意图
	private String hintImgUrl;//示意图rul地址
	
	
	 public String getHintImgUrl() {
		return hintImgUrl;
	}
	public void setHintImgUrl(String hintImgUrl) {
		this.hintImgUrl = hintImgUrl;
	}
	@Transient
	public Integer getIsHintImg() {
		return isHintImg;
	}
	public void setIsHintImg(Integer isHintImg) {
		this.isHintImg = isHintImg;
	}
	@Id
//    @SequenceGenerator(name="video_img",sequenceName="video_img",allocationSize = 1)
//    @GeneratedValue(generator="video_img",strategy=GenerationType.SEQUENCE)
    @Column(name = "videId")
	public Long getVideId() {
		return videId;
	}
	public void setVideId(Long videId) {
		this.videId = videId;
	}
	@Basic
    @Column(name = "videOldName")
	public String getVideOldName() {
		return videOldName;
	}
	public void setVideOldName(String videOldName) {
		this.videOldName = videOldName;
	}
	
	@Basic
    @Column(name = "videOldId")
	public String getVideOldId() {
		return videOldId;
	}
	public void setVideOldId(String videOldId) {
		this.videOldId = videOldId;
	}
	
	@Basic
    @Column(name = "videOldUrl")
	public String getVideOldUrl() {
		return videOldUrl;
	}
	public void setVideOldUrl(String videOldUrl) {
		this.videOldUrl = videOldUrl;
	}
	
	@Basic
    @Column(name = "videOldPath")
	public String getVideOldPath() {
		return videOldPath;
	}
	public void setVideOldPath(String videOldPath) {
		this.videOldPath = videOldPath;
	}
	
	@Basic
    @Column(name = "videGroupName")
	public String getVideGroupName() {
		return videGroupName;
	}
	public void setVideGroupName(String videGroupName) {
		this.videGroupName = videGroupName;
	}
	
	@Basic
    @Column(name = "videGroupId")
	public String getVideGroupId() {
		return videGroupId;
	}
	public void setVideGroupId(String videGroupId) {
		this.videGroupId = videGroupId;
	}
	
//	@Basic
//    @Column(name = "videSectionName")
//	public String getVideSectionName() {
//		return videSectionName;
//	}
//	public void setVideSectionName(String videSectionName) {
//		this.videSectionName = videSectionName;
//	}
//	
//	@Basic
//    @Column(name = "videSectionShowName")
//	public String getVideSectionShowName() {
//		return videSectionShowName;
//	}
//	public void setVideSectionShowName(String videSectionShowName) {
//		this.videSectionShowName = videSectionShowName;
//	}
	
	@Basic
    @Column(name = "videName")
	public String getVideName() {
		return videName;
	}
	public void setVideName(String videName) {
		this.videName = videName;
	}
	
	@Basic
    @Column(name = "videShowName")
	public String getVideShowName() {
		return videShowName;
	}
	public void setVideShowName(String videShowName) {
		this.videShowName = videShowName;
	}
	
	@Basic
    @Column(name = "videMarker1")
	public String getVideMarker1() {
		return videMarker1;
	}
	public void setVideMarker1(String videMarker1) {
		this.videMarker1 = videMarker1;
	}
	
	@Basic
    @Column(name = "videMarker2")
	public String getVideMarker2() {
		return videMarker2;
	}
	public void setVideMarker2(String videMarker2) {
		this.videMarker2 = videMarker2;
	}
	
	@Basic
    @Column(name = "videDirection")
	public Integer getVideDirection() {
		return videDirection;
	}
	public void setVideDirection(Integer videDirection) {
		this.videDirection = videDirection;
	}
	
	@Basic
    @Column(name = "videNo")
	public String getVideNo() {
		return videNo;
	}
	public void setVideNo(String videNo) {
		this.videNo = videNo;
	}
	
	@Basic
    @Column(name = "videLongitude")
	public String getVideLongitude() {
		return videLongitude;
	}
	public void setVideLongitude(String videLongitude) {
		this.videLongitude = videLongitude;
	}
	
	@Basic
    @Column(name = "videLatitude")
	public String getVideLatitude() {
		return videLatitude;
	}
	public void setVideLatitude(String videLatitude) {
		this.videLatitude = videLatitude;
	}
	
	@Basic
    @Column(name = "videUrl")
	public String getVideUrl() {
		return videUrl;
	}
	public void setVideUrl(String videUrl) {
		this.videUrl = videUrl;
	}
	
	@Basic
    @Column(name = "videDiagramUrl")
	public String getVideDiagramUrl() {
		return videDiagramUrl;
	}
	public void setVideDiagramUrl(String videDiagramUrl) {
		this.videDiagramUrl = videDiagramUrl;
	}
	
	@Basic
    @Column(name = "videParentId")
	public Long getVideParentId() {
		return videParentId;
	}
	public void setVideParentId(Long videParentId) {
		this.videParentId = videParentId;
	}
	
	@Basic
    @Column(name = "videIsHot")
	public Integer getVideIsHot() {
		return videIsHot;
	}
	public void setVideIsHot(Integer videIsHot) {
		this.videIsHot = videIsHot;
	}
	@Basic
    @Column(name = "videType")
	public Integer getVideType() {
		return videType;
	}
	public void setVideType(Integer videType) {
		this.videType = videType;
	}
	
	
}
