package org.jiaowei.common.filter;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * Created by alex on 15-4-27.
 *
 */
public class SpecialCharInterceptor implements HandlerInterceptor {
    private Logger logger = Logger.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String url = httpServletRequest.getRequestURL().toString();
        String uri = httpServletRequest.getRequestURI();
        Enumeration<String> en =httpServletRequest.getParameterNames();
        while(en.hasMoreElements()){
            String kay = en.nextElement();
            String[] value = httpServletRequest.getParameterValues(kay);
            for(int i =0 ; i<value.length; i++){
//                value[i] = value[i].replace("<","&lt;").replace("\\","\\\\").replace(">","&gt;");
                value[i] = "aaaa";
            }

//            System.out.println("kay : "  + kay);
//            System.out.println("value : " +httpServletRequest.getParameters(kay));
        }

//        Map<String, String[]> map = httpServletRequest.getParameterMap();
//        String kay = null;
//        String[] values = null;
//        for (Iterator<String> it = map.keySet().iterator(); it.hasNext(); ) {
//            kay = it.next();
//            values = map.get(kay);
//            if (null != values && values.length > 0) {
//                for (String s : values) {
//                    if (s.contains("<") || s.contains("(") || s.contains("\\")) {
//                        logger.info("参数[" + kay + " = "+ s + "] 包含特殊字符[<,(,\\...]");
//                        httpServletResponse.sendRedirect("/index-----.jsp.bak");
//                        return false;
//                    }
//                }
//            }
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        Enumeration<String> en =httpServletRequest.getParameterNames();
        while(en.hasMoreElements()){
            String kay = en.nextElement();
            String value = httpServletRequest.getParameter(kay);
//            System.out.println("kay : "  + kay);
//            System.out.println("value : " + value);
        }
//        System.out.println("post handle");

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
//        System.out.println("afterCompletion................");
    }
}
