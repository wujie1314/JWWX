package com.foxconn.socket.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yzh.cqjw.response.GetPagedViolationReportListResponse;
import com.yzh.cqjw.response.GetViolationReportByBatchNoResponse;
import com.yzh.cqjw.response.GetPagedViolationReportListResponse.ReportFile;
import com.yzh.cqjw.response.GetPagedViolationReportListResponse.ViolationReport;
import com.yzh.cqjw.response.GetViolationReportBaseDataResponse;
import com.yzh.cqjw.response.GetViolationReportBaseDataResponse.CommentType;
import com.yzh.cqjw.response.GetViolationReportBaseDataResponse.ViolationType;

public class SocketResultVo {
	
	public Map<String,Object> errorMsg = new HashMap<String, Object>();
	public List<Map<String, Object>> violationType = new ArrayList<Map<String, Object>>();
	public List<Map<String, Object>> commentType = new ArrayList<Map<String, Object>>();
	public List<Map<String, Object>> violationReport = new ArrayList<Map<String, Object>>();
	public String violationTypeVersion;
	public String commentTypeVersion;
	public String pageSize;
	public String pageIndex;
	public String reportUserId;
	public String count;
	
	public String tempId;
	public String violationTime;
	public String violationRoadSectionCode;
	public String violationRoadSectionName;
	public String startFeesStationCode;
	public String startFeesStationName;
	public String endFeesStationCode;
	public String endFeesStationName;
	public String reportAddress;
	public String reportLocation;
	public String plateNumbers;
	public String violationTypeId;
	public String reportInfo;
	public String reportFrom;
	public String isexposure;
	public List<Map<String, Object>> reportFile = new ArrayList<Map<String, Object>>();
	public String reportUserPhone;
	public String reportTime;
	public String userReportStatus;
	public String batchNo;
	public String mediaType;
	public String violationTypeName;
	
	
	/**
	 * 评论
	 * @param message
	 */
	public SocketResultVo(GetViolationReportBaseDataResponse.GetViolationReportBaseDataResponseMessage message){
		if(message != null){
			Map<String,Object> em = new HashMap<String, Object>();
			em.put("errorMsg", message.getErrorMsg().getErrorMsg());
			errorMsg = em;
			
			List<ViolationType> vioList = message.getViolationTypeList();
			if(vioList != null && vioList.size() > 0){
				for (ViolationType vi : vioList) {
					Map<String,Object> violationTypeMap = new HashMap<String, Object>();
					violationTypeMap.put("id", vi.getId());
					violationTypeMap.put("code", vi.getCode());
					violationTypeMap.put("name", vi.getName());
					violationType.add(violationTypeMap);
				}
				
			}
			
			List<CommentType> comList = message.getCommentTypeList();
			if(comList != null && comList.size() > 0){
				for (CommentType co : comList) {
					Map<String,Object> coMap = new HashMap<String, Object>();
					coMap.put("id", co.getId());
					coMap.put("code", co.getCode());
					coMap.put("name", co.getName());
					commentType.add(coMap);
				}
			}
			
			violationTypeVersion = message.getViolationTypeVersion();
			commentTypeVersion = message.getCommentTypeVersion();
		}
	}
	
	
	/**
	 * 列表
	 * @param message
	 */
	public SocketResultVo(GetPagedViolationReportListResponse.GetPagedViolationReportListResponseMessage message){
		if(message != null){
			Map<String,Object> em = new HashMap<String, Object>();
			em.put("errorMsg", message.getErrorMsg().getErrorMsg());
			errorMsg = em;
			
			List<ViolationReport> vioList = message.getViolationReportList();
			if(vioList != null && vioList.size() > 0){
				for (ViolationReport vi : vioList) {
					Map<String,Object> viMap = new HashMap<String, Object>();
					viMap.put("id", vi.getId());
					viMap.put("tempId", vi.getTempId());
					viMap.put("violationRoadSectionCode", vi.getViolationRoadSectionCode());
					viMap.put("violationRoadSectionName", vi.getViolationRoadSectionName());
					viMap.put("startFeesStationName", vi.getStartFeesStationName());
					viMap.put("endFeesStationName", vi.getEndFeesStationName());
					viMap.put("violationTime", vi.getViolationTime());
					viMap.put("reportAddress", vi.getReportAddress());
					viMap.put("reportLocation", vi.getReportLocation());
					viMap.put("plateNumbers", vi.getPlateNumbers());
					viMap.put("violationTypeId", vi.getViolationTypeId());
					viMap.put("reportInfo", vi.getReportInfo());
					viMap.put("reportFrom", vi.getReportFrom());
					viMap.put("isexposure", vi.getIsexposure());
					List<ReportFile> fileList = vi.getReportFileList();
					List<Map<String,Object>> fileMapList = new ArrayList<Map<String, Object>>();
					if(fileList != null && fileList.size() > 0){
						for (ReportFile file : fileList) {
							Map<String,Object> fileMap = new HashMap<String, Object>();
							fileMap.put("fileName", file.getFileName());
							fileMap.put("path", file.getPath());
							fileMap.put("mediaType", file.getMediaType());
							fileMap.put("thumbnailPath", file.getThumbnailPath());
							fileMapList.add(fileMap);
						}
					}
					viMap.put("reportFile", fileMapList);
					viMap.put("reportUserPhone", vi.getReportUserPhone());
					viMap.put("reportTime", vi.getReportTime());
					viMap.put("userReportStatus", vi.getUserReportStatus());
					viMap.put("batchNo", vi.getBatchNo());
					viMap.put("mediaType", vi.getMediaType());
					viMap.put("backOfficeCnt", vi.getBackOfficeCnt());
					viMap.put("viewCnt", vi.getViewCnt());
					viMap.put("topCommentTypeIds", vi.getTopCommentTypeIds());
					violationReport.add(viMap);
				}
			}
			
			pageSize = String.valueOf(message.getPageSize());
			pageIndex = String.valueOf(message.getPageIndex());
			count = String.valueOf(message.getCount());
		}
	}
	
	/**
	 * 详情
	 * @param message
	 */
	public SocketResultVo(GetViolationReportByBatchNoResponse.GetViolationReportByBatchNoResponseMessage message){
		if(message != null){
			Map<String,Object> em = new HashMap<String, Object>();
			em.put("errorMsg", message.getErrorMsg().getErrorMsg());
			errorMsg = em;
			
			tempId = message.getTempId();
			violationTime = message.getViolationTime();
			violationRoadSectionCode = message.getViolationRoadSectionCode();
			violationRoadSectionName = message.getViolationRoadSectionName();
			startFeesStationCode = message.getStartFeesStationCode();
			startFeesStationName = message.getStartFeesStationName();
			endFeesStationCode = message.getEndFeesStationCode();
			endFeesStationName = message.getEndFeesStationName();
			reportAddress = message.getReportAddress();
			reportLocation = message.getReportLocation();
			plateNumbers = message.getPlateNumbers();
			violationTypeId = String.valueOf(message.getViolationTypeId());
			reportInfo = message.getReportInfo();
			reportFrom = message.getReportFrom();
			isexposure = String.valueOf(message.getIsexposure());
			List<com.yzh.cqjw.response.GetViolationReportByBatchNoResponse.ReportFile> fileList = message.getReportFileList();
			if(fileList != null && fileList.size() > 0){
				for (com.yzh.cqjw.response.GetViolationReportByBatchNoResponse.ReportFile file : fileList) {
					Map<String,Object> fileMap = new HashMap<String, Object>();
					fileMap.put("fileName", file.getFileName());
					fileMap.put("path", file.getPath());
					fileMap.put("mediaType", file.getMediaType());
					fileMap.put("thumbnailPath", file.getThumbnailPath());
					reportFile.add(fileMap);
				}
			}
			reportUserPhone = message.getReportUserPhone();
			reportTime = message.getReportTime();
			userReportStatus = message.getUserReportStatus();
			batchNo = message.getBatchNo();
			mediaType = message.getMediaType();
			violationTypeName = message.getViolationTypeName();
		}
	}
	
	public Map<String, Object> getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(Map<String, Object> errorMsg) {
		this.errorMsg = errorMsg;
	}
	public List<Map<String, Object>> getViolationType() {
		return violationType;
	}
	public void setViolationType(List<Map<String, Object>> violationType) {
		this.violationType = violationType;
	}
	public List<Map<String, Object>> getCommentType() {
		return commentType;
	}
	public void setCommentType(List<Map<String, Object>> commentType) {
		this.commentType = commentType;
	}
	public String getViolationTypeVersion() {
		return violationTypeVersion;
	}
	public void setViolationTypeVersion(String violationTypeVersion) {
		this.violationTypeVersion = violationTypeVersion;
	}
	public String getCommentTypeVersion() {
		return commentTypeVersion;
	}
	public void setCommentTypeVersion(String commentTypeVersion) {
		this.commentTypeVersion = commentTypeVersion;
	}


	public List<Map<String, Object>> getViolationReport() {
		return violationReport;
	}


	public void setViolationReport(List<Map<String, Object>> violationReport) {
		this.violationReport = violationReport;
	}


	public String getPageSize() {
		return pageSize;
	}


	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}


	public String getPageIndex() {
		return pageIndex;
	}


	public void setPageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
	}


	public String getReportUserId() {
		return reportUserId;
	}


	public void setReportUserId(String reportUserId) {
		this.reportUserId = reportUserId;
	}


	public String getCount() {
		return count;
	}


	public void setCount(String count) {
		this.count = count;
	}


	public String getTempId() {
		return tempId;
	}


	public void setTempId(String tempId) {
		this.tempId = tempId;
	}


	public String getViolationTime() {
		return violationTime;
	}


	public void setViolationTime(String violationTime) {
		this.violationTime = violationTime;
	}


	public String getViolationRoadSectionCode() {
		return violationRoadSectionCode;
	}


	public void setViolationRoadSectionCode(String violationRoadSectionCode) {
		this.violationRoadSectionCode = violationRoadSectionCode;
	}


	public String getViolationRoadSectionName() {
		return violationRoadSectionName;
	}


	public void setViolationRoadSectionName(String violationRoadSectionName) {
		this.violationRoadSectionName = violationRoadSectionName;
	}


	public String getStartFeesStationCode() {
		return startFeesStationCode;
	}


	public void setStartFeesStationCode(String startFeesStationCode) {
		this.startFeesStationCode = startFeesStationCode;
	}


	public String getStartFeesStationName() {
		return startFeesStationName;
	}


	public void setStartFeesStationName(String startFeesStationName) {
		this.startFeesStationName = startFeesStationName;
	}


	public String getEndFeesStationCode() {
		return endFeesStationCode;
	}


	public void setEndFeesStationCode(String endFeesStationCode) {
		this.endFeesStationCode = endFeesStationCode;
	}


	public String getEndFeesStationName() {
		return endFeesStationName;
	}


	public void setEndFeesStationName(String endFeesStationName) {
		this.endFeesStationName = endFeesStationName;
	}


	public String getReportAddress() {
		return reportAddress;
	}


	public void setReportAddress(String reportAddress) {
		this.reportAddress = reportAddress;
	}


	public String getReportLocation() {
		return reportLocation;
	}


	public void setReportLocation(String reportLocation) {
		this.reportLocation = reportLocation;
	}


	public String getPlateNumbers() {
		return plateNumbers;
	}


	public void setPlateNumbers(String plateNumbers) {
		this.plateNumbers = plateNumbers;
	}


	public String getViolationTypeId() {
		return violationTypeId;
	}


	public void setViolationTypeId(String violationTypeId) {
		this.violationTypeId = violationTypeId;
	}


	public String getReportInfo() {
		return reportInfo;
	}


	public void setReportInfo(String reportInfo) {
		this.reportInfo = reportInfo;
	}


	public String getReportFrom() {
		return reportFrom;
	}


	public void setReportFrom(String reportFrom) {
		this.reportFrom = reportFrom;
	}


	public String getIsexposure() {
		return isexposure;
	}


	public void setIsexposure(String isexposure) {
		this.isexposure = isexposure;
	}


	public List<Map<String, Object>> getReportFile() {
		return reportFile;
	}


	public void setReportFile(List<Map<String, Object>> reportFile) {
		this.reportFile = reportFile;
	}


	public String getReportUserPhone() {
		return reportUserPhone;
	}


	public void setReportUserPhone(String reportUserPhone) {
		this.reportUserPhone = reportUserPhone;
	}


	public String getReportTime() {
		return reportTime;
	}


	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}


	public String getUserReportStatus() {
		return userReportStatus;
	}


	public void setUserReportStatus(String userReportStatus) {
		this.userReportStatus = userReportStatus;
	}


	public String getBatchNo() {
		return batchNo;
	}


	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}


	public String getMediaType() {
		return mediaType;
	}


	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}


	public String getViolationTypeName() {
		return violationTypeName;
	}


	public void setViolationTypeName(String violationTypeName) {
		this.violationTypeName = violationTypeName;
	}
	
}
