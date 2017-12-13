package org.jiaowei.controllers.console;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.jiaowei.mybatis.common.ApiResult;
import org.jiaowei.mybatis.common.UserSession;
import org.jiaowei.mybatis.vo.UserVo;
import org.jiaowei.util.ApiUtils;
import org.jiaowei.util.JsonUtils;
public class BaseController {
	protected Log log = LogFactory.getLog(getClass());
	public static final String REDIRECT = "redirect:";

	protected final Logger logger = Logger.getLogger(BaseController.class);
	
    /**
     * 返回用户ID
     *
     * @return
     */
    public String getUserId(HttpServletRequest request) {
    	UserVo user = getUser(request);
        return null != user ? user.getUserId() : null;
    }
    /**
     * 返回用户
     * @param request
     * @return
     */
    public UserVo getUser(HttpServletRequest request){
    	return (UserVo)request.getSession().getAttribute(UserSession.USER);
    }
    
    /**
     * 编码参数(针对中文及特殊字符)
     * @param value
     * @return
     */
    public String encodeString(String value) {
    	String parmValue = null;
    	try {
    		parmValue = URLEncoder.encode(value, "utf-8");
		} catch (UnsupportedEncodingException e) {
			log.error(e);
		}
    	return parmValue;
    }
    
	
	/**
	 * 跳转页面
	 * @param url
	 * @return
	 */
	public String redirect(String url) {
		return REDIRECT + url;
	}
	
	/**
	 * String 转换 int
	 * @param s
	 * @param defaultValue
	 * @return
	 */
	public static Integer parseInt(String s, Integer defaultValue){
		if(StringUtils.isNotBlank(s)){
			defaultValue = Integer.parseInt(s);
		}
		return defaultValue;
	}
	
	public static Integer parseInteger(String s){
		return parseInt(s, null);
	}
	public static int parseInt(String s){
		return parseInt(s, 0);
	}
	
	
	/**
	 * 参数转换INT
	 * @param request
	 * @param paramValue
	 * @param defaultValue
	 * @return
	 */
	public static Integer parseInteger(HttpServletRequest request, String paramValue,Integer defaultValue){
		String s = request.getParameter(paramValue);
		if(StringUtils.isNotBlank(s)){
			defaultValue = Integer.parseInt(s);
		}
		return defaultValue;
	}
	
	/**
	 * 参数转换INT 默认值null
	 * @param request
	 * @param paramValue
	 * @return
	 */
	public static Integer parseInteger(HttpServletRequest request, String paramValue){
		return parseInteger(request, paramValue, null);
	}
	/**
	 * 参数转换INT 默认值0
	 * @param request
	 * @param paramValue
	 * @return
	 */
	public static int parseInt(HttpServletRequest request, String paramValue){
		return parseInteger(request, paramValue, 0);
	}
	
	public static String parseString(HttpServletRequest request, String paramValue,String defaultValue){
		String s = request.getParameter(paramValue);
		if(StringUtils.isNotBlank(s)){
			defaultValue = s.trim();
		}
		return defaultValue;
	}
	
	/**
	 * 参数转换String 默认null
	 * @param request
	 * @param paramValue
	 * @return
	 */
	public static String parseString(HttpServletRequest request, String paramValue){
		return parseString(request, paramValue, null);
	}
	
	public static Date parseDate(HttpServletRequest request, String paramValue){
		Date result = null;
		String s = request.getParameter(paramValue);
		if(StringUtils.isNotBlank(s)){
			result = ApiUtils.parseDate(s);
		}
		return result;
	}
	public static Date parseEndDate(HttpServletRequest request, String paramValue){
		Date result = null;
		String s = request.getParameter(paramValue);
		if(StringUtils.isNotBlank(s)){
			result = ApiUtils.parseDateTime(s+" 23:59:59");
		}
		return result;
	}
	
	/**
	 * 参数转换INT 默认值0
	 * @param request
	 * @param paramValue
	 * @return
	 */
	public static Long parseLong(HttpServletRequest request, String paramValue){
		return parseLong(request, paramValue, null);
	}
	
	public static Long parseLong(HttpServletRequest request, String paramValue,Long defaultValue){
		String s = request.getParameter(paramValue);
		if(StringUtils.isNotBlank(s)){
			defaultValue = Long.parseLong(s);
		}
		return defaultValue;
	}
	
	public static Long parseLong(String paramValue){
		Long result = null;
		if(StringUtils.isNotBlank(paramValue)){
			result = Long.parseLong(paramValue);
		}
		return result;
	}
	/**
	 * 参数转换INT 默认值0
	 * @param request
	 * @param paramValue
	 * @return
	 */
	public static Short parseShort(HttpServletRequest request, String paramValue){
		return parseShort(request, paramValue, null);
	}
	
	public static Short parseShort(HttpServletRequest request, String paramValue,Short defaultValue){
		String s = request.getParameter(paramValue);
		if(StringUtils.isNotBlank(s)){
			defaultValue = Short.parseShort(s);
		}
		return defaultValue;
	}
	
	protected String resultValue(ApiResult result) {
		return JsonUtils.object2json(result);
	}
	
	protected void resultJson(HttpServletResponse response, int code, String msg){
		resultJson(response, code, msg, null);
	}
	protected void resultJson(HttpServletResponse response, int code, String msg, Object data){
		ApiResult result = new ApiResult();
		result.setCode(code);
		result.setMsg(msg); 
		result.setData(data);
		response.setCharacterEncoding("UTF-8"); //设置编码格式
		response.setContentType("text/html");   //设置数据格式
		try {
			PrintWriter out = response.getWriter();//获取写入对象
			String json = JsonUtils.object2json(result);
//			System.out.println("resultJson:"+json);
			out.print(json); //将json数据写入流中
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e);
		} 
		
	}
}
