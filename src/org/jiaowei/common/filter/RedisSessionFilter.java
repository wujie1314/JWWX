//package org.jiaowei.common.filter;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
///**
// * Created by alex on 15-8-3.
// * 功能：自动延时redis  sessionId的过期时间
// */
//public class RedisSessionFilter implements Filter{
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
////    @Override
////    public void doFilter(ServletRequest servletRequest,
////                         ServletResponse servletResponse,
////                         FilterChain filterChain) throws IOException, ServletException {
////
////        HttpServletRequest request = (HttpServletRequest) servletRequest;
////        String sessionId = request.getSession().getId();
////        if(RedisUtil.exist(sessionId))
////        RedisUtil.expire(sessionId, SystemConst.SESSION_EXPIRE);
////        filterChain.doFilter(servletRequest,servletResponse);
////    }
//
////    @Override
////    public void destroy() {
////
////    }
//}
