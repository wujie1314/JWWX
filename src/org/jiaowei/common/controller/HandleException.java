package org.jiaowei.common.controller;

import org.apache.log4j.Logger;
import org.jiaowei.util.FastJsonUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by alex on 15-5-5.
 * 这是一个用于处于异常的类
 *
 */
@ControllerAdvice
public class HandleException {

    private  static Logger logger =Logger.getLogger(HandleException.class);

//    @ExceptionHandler({RuntimeException.class})
//    public ModelAndView hanleEx(RuntimeException ex) {
//        ModelAndView mv = new ModelAndView("error");
//        mv.addObject("ex", ex);
//        return mv;
//    }

    @ExceptionHandler({Exception.class})
    public void hanleEx(Exception ex,
                        HttpServletResponse response) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("status","error");
        map.put("errorMsg","发生异常了：["+ex.getMessage()+"]");
        writeMsgToClient(response, FastJsonUtil.toJson(map));
    }

    public void writeMsgToClient(HttpServletResponse response, String msg) {
        try {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(msg);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
        	// 隐藏了一个bug hehe
//        	e.printStackTrace();
            logger.error("向客户端写错误信息发生了异常:" +e.getMessage());
        }
    }
}
