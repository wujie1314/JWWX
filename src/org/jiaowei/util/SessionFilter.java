package org.jiaowei.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class SessionFilter implements Filter {
	 @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	HttpServletRequest hrequest = (HttpServletRequest)request;
    	String requestUserId = hrequest.getQueryString();//得到请求的数据
    	/*if (hrequest.getSession().getAttribute(requestUserId.substring(9)).equals(requestUserId.substring(9))) {*/
    	if(hrequest.getSession().getAttribute(requestUserId.substring(9))!=null){
    		chain.doFilter(request, response);
        }
    	//若session中不存在请求中的账号，则跳回登录界面
    	else{
    		request.getRequestDispatcher("/").forward(request, response);
    	}
    }

    @Override
    public void destroy() {

    }
}
