package org.jiaowei.controllers.console;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.jiaowei.mybatis.common.PageVo;
import org.jiaowei.mybatis.service.ReportedMsgService;
import org.jiaowei.mybatis.vo.ReportedMsgVo;
import org.jiaowei.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 活动页
 * @author limingxing
 *
 */
@Controller
@RequestMapping("/console/activity.htm")
public class ActivityManager extends BaseController{

	private static Logger logger = Logger.getLogger(ActivityManager.class);
    @Autowired
    private ReportedMsgService reportedMsgService;
    
    
    /**
     * 活动首页首页
     * @param request
     * @param map
     * @return
     */
    @RequestMapping(params = "method=list")
	public  String home(HttpServletRequest request,Map<String,Object> map){
		return "console/activity/list";
	}
	
    @RequestMapping(params = "method=queryPageList" , produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryPageList(ModelMap model, HttpServletRequest request){
    	PageVo<ReportedMsgVo> page = new PageVo<ReportedMsgVo>();
    	try {
    		Integer  repoState = parseInteger(request,"repoState");
    		String  searcKeyword = parseString(request,"searcKeyword");
    		int  pageIndex = parseInt(request,"pageIndex");//开始页
    		int  limitCount = parseInt(request,"limitCount");//开始页
    		
    		System.out.println("repoState:"+repoState);
    		System.out.println("searcKeyword:"+searcKeyword);
    		System.out.println("pageIndex:"+pageIndex);
    		System.out.println("limitCount:"+limitCount);
    		Map<String,Object> param = new HashMap<String,Object>();
    		param.put("repoState", repoState);
    		param.put("searcKeyword", searcKeyword);
    		int count = reportedMsgService.queryCount(param);
    		page.setPage(count, pageIndex, limitCount);
    		param.put("limitBegin", page.getLimitBegin());
    		param.put("limitEnd", page.getLimitEnd());
    		List<ReportedMsgVo> category = reportedMsgService.queryPageList(param);
    		page.setData(category);
		} catch (Exception e) {
			logger.error("查询错误", e);
		}
    	String result = JsonUtils.object2json(page);
    	System.out.println("--->"+result);
		return JsonUtils.object2json(page);
	}

	/**
     * 修改状态
     * @return
     */
    @RequestMapping(params = "method=updateState" , produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateState(HttpServletRequest request, ReportedMsgVo vo) {
		int code = 0;
		String msg = "成功！";
		try {
			reportedMsgService.update(vo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("修改错误", e);
			code = 1;
			msg = "失败！";
		}
        return "{'code':'"+code+"','msg':'"+msg+"'}";
    }
    /**
     * 删除
     * @return
     */
    @RequestMapping(params = "method=del" , produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String del(HttpServletRequest request) {
    	int code = 0;
    	String msg = "成功！";
    	try {
    		Long  id = parseLong(request,"id");
    		reportedMsgService.delete(id);
    	} catch (Exception e) {
    		e.printStackTrace();
    		logger.error("删除错误", e);
    		code = 1;
    		msg = "失败！";
    	}
    	return "{'code':'"+code+"','msg':'"+msg+"'}";
    }
    
}
