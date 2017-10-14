package org.jiaowei.mybatis.common;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class PageVo<T> {

	private int recoredCount;//总记录数
	private int pageCount = 1;//总页数
	private int pageIndex = 1;//当前页
	private int limitBegin = 0;//开始条数
	private int limitCount = 20;//没有显示条数
	private int limitEnd = 20;//最后索引
	
	private int code = 0;
	private String msg = "成功";
	
	private Map<String, Object> params = new HashMap<String, Object>();
	private Object data;
	
	public void put(String key, Object value){
		params.put(key, value);
	}

	public int getLimitCount() {
		return limitCount;
	}

	public void setLimitCount(int limitCount) {
		this.limitCount = limitCount;
	}

	public void setPage(int rcount, int pagei, int limitCount) {
		if (limitCount > 0) {
			this.limitCount = limitCount;
		}
		setPage(rcount, pagei);
	}

	public void setPage(int rcount, int pagei) {

		if (rcount >= 1) {
			this.recoredCount = rcount;
		}
		if (this.recoredCount > 0) {
			this.pageCount = this.recoredCount / limitCount;
		}
		if (this.recoredCount % limitCount != 0) {
			this.pageCount++;
		}
		this.pageIndex = pagei;

		if (pagei < 1) {
			this.pageIndex = 1;
		}
		if (this.pageIndex > this.pageCount) {
			this.pageIndex = this.pageCount;
		}

		this.limitBegin = (this.pageIndex - 1) * limitCount;
		this.limitEnd = this.limitBegin + this.limitCount;
	}

	public int getRecoredCount() {
		return recoredCount;
	}

	public void setRecoredCount(int recoredCount) {
		this.recoredCount = recoredCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getLimitBegin() {
		return limitBegin;
	}

	public void setLimitBegin(int limitBegin) {
		this.limitBegin = limitBegin;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public int getLimitEnd() {
		return limitEnd;
	}

	public void setLimitEnd(int limitEnd) {
		this.limitEnd = limitEnd;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
