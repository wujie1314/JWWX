package org.jiaowei.mybatis.common;

public class ApiResult {
	public ApiResult(){}
	
	public ApiResult(int code, String msg){
		this.code = code;
		this.msg = msg;
	}
	public ApiResult(int code, String msg,Object data){
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	private int code = 0;
	private String msg = "成功！";
	private Object data;
	
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
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
}
