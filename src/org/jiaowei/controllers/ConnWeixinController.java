package org.jiaowei.controllers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.sql.OffsetDST;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jiaowei.entity.MsgFromWxEntity;
import org.jiaowei.entity.NavigationMenuEntity;
import org.jiaowei.entity.WeixinPublicInfoEntity;
import org.jiaowei.entity.WeixinUserInfoEntity;
import org.jiaowei.entity.WxStatusTmpTEntity;
import org.jiaowei.service.CustomerServiceHisService;
import org.jiaowei.service.MsgFromWxService;
import org.jiaowei.service.NavMenuService;
import org.jiaowei.service.WeixinPublicInfoService;
import org.jiaowei.service.WeixinUserInfoService;
import org.jiaowei.service.WxNavigationMenuService;
import org.jiaowei.service.WxStatusTmpService;
import org.jiaowei.util.DateUtils;
import org.jiaowei.util.MyBeanUtils;
import org.jiaowei.wxutil.MsgTypeEnum;
import org.jiaowei.wxutil.WeiXinConst;
import org.jiaowei.wxutil.WeiXinOperUtil;
import org.jiaowei.wxutil.WeixinUtils;
import org.jiaowei.wxutil.XmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * Created by root on 15-11-26.
 */
@Controller
public class ConnWeixinController {

    private static Logger logger = Logger.getLogger(ConnWeixinController.class);
    @Autowired
    private MsgFromWxService msgFromWxService;
    @Autowired
    private WeixinUserInfoService weixinUserInfoService;
    @Autowired
    private CustomerServiceHisService customerServiceHisService;
    @Autowired
    private WxStatusTmpService wxStatusTmpService;
    
    @Autowired
    private WxNavigationMenuService wxNavigationMenuService;
    
    @Autowired
    private NavMenuService navMenuService;
    
    @Autowired
    private WeixinPublicInfoService weixinPublicInfoService;
    /**
     * 连接到微信服务器
     *
     * @param response
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     */
    @RequestMapping(value = "/connWX", method = RequestMethod.GET)
    public void connWX(HttpServletResponse response,HttpServletRequest request,
                       String signature,
                       String timestamp,
                       String nonce,
                       String echostr) {
        System.out.println("URL" + request.getRequestURL());
        System.out.println("URI" + request.getRequestURI());
        System.out.println(request.getQueryString());
        if (WeiXinOperUtil.connWx(signature, timestamp, nonce)) {
            WeiXinOperUtil.sendMsgToWX(response, echostr);
        }
    }

    /**
     * 接收来自微信服务器的消息
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/connWX", method = RequestMethod.POST)
    public void getMsg(HttpServletRequest request,
                       HttpServletResponse response, @RequestParam(value = "url",required = false)String url) {



    	try {
    		Map<String, String> map = null;
            map = WeiXinOperUtil.receiveMsgFromWX(request);
            if (null != map) {
                WeiXinOperUtil.sendMsgToWX(response, "");
            }
            if (null != url){
                if(!map.get("MsgType").equals("text") && !map.get("MsgType").equals("voice") &&!map.get("MsgType").equals("image") ){
                    WeiXinOperUtil.wxpost(url,request,response);
                    return ;
                }
            }

            // 判断来自那个微信公平号
            String toUserName = map.get("ToUserName");
            String msgTypeString = map.get("MsgType").toLowerCase().trim();
            String openId = map.get("FromUserName");
            //检查当前用户是否在用户表中
            checkUser(toUserName,openId);
            //处理消息
            if (msgTypeString.equals(MsgTypeEnum.EVENT.getValue().toLowerCase().trim())) {
                //返回的消息是事件
                if ("subscribe".equals(map.get("Event"))) {
                    //1)订阅事件
                    subcript(map, response);
                } else if ("unsubscribe".equals(map.get("Event"))) {
                    //2)退订事件
                    unsubscript(map);
                } else if ("CLICK".equals(map.get("Event"))) {
                    //3)点击人工服务
                	if ("MAN_SERVICE".equals(map.get("EventKey"))){
                		NavigationMenuEntity menuEntity = new NavigationMenuEntity();
            			menuEntity.setMenuKey("0-0");
            			menuEntity.setOpenId(openId);
            			menuEntity.setDate(WeixinUtils.getNowDateTime());
            			WeiXinConst.navigationMenu.put(openId, menuEntity);
                		navMenuService.sendMenuWxHint(map, response, openId, "0-0", menuEntity,"");
                		
                	} else if("USER_COLLECT".equals(map.get("EventKey"))){
                		try {
                			logger.error("================>getRequestDispatcher start");
                			response.sendRedirect("http://cq96096.cn/videoImg/list?type=2&openId="+openId);
                			logger.error("================>getRequestDispatcher end");
    					} catch (Exception e) {
    						e.printStackTrace();
    						logger.error("================>getRequestDispatcher error:",e);
    					}//这是内部跳转
                	}
                }
            } else {
            	try{
            		navMenuService.sendCustomerService(map, response, request);
            	}catch(Exception e){
            		e.printStackTrace();
            		logger.error("================>sendCustomerService error:", e);
            	}
            }
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("connWX is error：", e);
		}
        
    }

    /**
     * 创建菜单
     */
    public void createMenu(String publicID) {
    	String menuString = " {\n" +
                "     \"button\":[\n" +
                "      {\n" +
                "           \"name\":\"出行服务\",\n" +
                "           \"sub_button\":[\n" +
                "            {\n" +
                "               \"type\":\"view\",\n" +
                "               \"name\":\"实时公交查询\",\n" +
                "               \"url\":\"http://web.chelaile.net.cn/wwd/index?src=webapp_weixin_chongqing\"\n" +
                "            },\n" +
                "           {\n" +
                "               \"type\":\"view\",\n" +
                "               \"name\":\"长途汽车购票\",\n" +
                "               \"url\":\"http://wap.96096kp.com/wapsite/control/searchStation?from=singlemessage\"\n" +
                "            },\n" +
				"            {\t\n" +
				"               \"type\":\"view\",\n" +
				"               \"name\":\"旅游专车购票\",\n" +
				"               \"url\":\"http://wx.iu73.com/wxWeb/rwyBusCollectsNew.aspx?winzoom=1\"\n" +
				"            },\n" +
                "            {\n" +
                "               \"type\":\"view\",\n" +
                "               \"name\":\"火车班次查询\",\n" +
                "               \"url\":\"http://mobile.12306.cn/weixin/wxcore/init\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"type\":\"view\",\n" +
                "               \"name\":\"航班动态信息\",\n" +
                "               \"url\":\"http://m.veryzhun.com/flight/?token=5cf2036c3db9fe08a7ee0c9b2077d37d\"\n" +
                "            }]\n" +
                "       },\n" +
                "      {\n" +
                "           \"name\":\"违章缴费\",\n" +
                "           \"sub_button\":[\n" +
                			
                "            {\n" +
                "               \"type\":\"view\",\n" +
                "               \"name\":\"高速违章查询\",\n" +
                "               \"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx5f87341a9a4c9a1a&redirect_uri=http://cqjtzf.cn/wx/ViolationQueries/Index?para=44*gh_8c194f2bc799&response_type=code&scope=snsapi_base&state=1&from=groupmessage&connect_redirect=1#wechat_redirect\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"type\":\"view\",\n" +
                "               \"name\":\"市内违章查询\",\n" +
                "               \"url\":\"http://www.weiwubao.com/traffic/800066/add_bind/?btype=3&client=h7pGTzBsa0PWb7aNeZ8UmXxs5ciaZ3LYpxeS790SNwC\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"type\":\"view\",\n" +
                "               \"name\":\"市内违章缴费\",\n" +
                "               \"url\":\"https://tts.yiji.com/tts/index.html?outOrderNo=201607111106110475&partner=CQJS&notifyUrl=800066.wap.weiwubao.com/site/index/callback\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"type\":\"view\",\n" +
                "               \"name\":\"ETC 消费查询\",\n" +
                "               \"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx698f18eaeb3c7a6e&redirect_uri=http://cqetcweixin.u-road.com/CQETCWechatAPIServer/index.php/cqetcserver/etcbind&response_type=code&scope=snsapi_base&state=123#wechat_redirect\"\n" +
                "            }]\n" +
                "       },\n" +
                
                "      {\n" +
                "           \"name\":\"路况及活动\",\n" +
                "           \"sub_button\":[\n" +
//                "            {\n" +
//                "               \"type\":\"view\",\n" +
//                "               \"name\":\"曝光台\",\n" +
//                "               \"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx915ad295909bf037&redirect_uri=http%3a%2f%2fwww.cq96096.cn%2fcsc%2fgetViolationReportInit&response_type=code&scope=snsapi_base&state=123&connect_redirect=1#wechat_redirect\"\n" +
//                "            },\n" +
                "            {\n" +
                "               \"type\":\"view\",\n" +
                "               \"name\":\"实时路况\",\n" +
                "               \"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx915ad295909bf037&redirect_uri=http%3a%2f%2fwww.cq96096.cn%2fvideoImg%2flist&response_type=code&scope=snsapi_base&state=123&connect_redirect=1#wechat_redirect\"\n" +
                "            },\n" +
				"            {\t\n" +
				"               \"type\":\"view\",\n" +
				"               \"name\":\"重庆交通APP\",\n" +
				"               \"url\":\"http://cx.cqjt.gov.cn/index/UnderConstructionP.html\"\n" +
				"            },\n" +
                "            {\n" +
                "               \"type\":\"view\",\n" +
                "               \"name\":\"奖品码兑换\",\n" +
                "               \"url\":\"http://www.twzgo.com/mobile/Activity/jwcj\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"type\":\"click\",\n" +
                "               \"name\":\"96096人工服务\",\n" +
                "               \"key\":\"MAN_SERVICE\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"type\":\"view\",\n" +
                "               \"name\":\"论坛\",\n" +
                "               \"url\":\"http://lls2015.free.ngrok.cc/bbs/jsp/trafficInformation.jsp\"\n" +
                "            }]\n" +
                "       }]\n" +
                " }";
    	System.err.println(menuString);
        WeiXinOperUtil.createWxMenu(menuString, WeiXinOperUtil.getAccessToken(publicID)); // 菜单 这里不需要更改
    }
//    /**
//     * 创建菜单
//     */
//    public void createMenu(String openId) {
//    	String menuString = " {\n" +
//    			"     \"button\":[\n" +
//    			"      {\n" +
//    			"           \"name\":\"出行服务\",\n" +
//    			"           \"sub_button\":[\n" +
//    			"            {\n" +
//    			"               \"type\":\"view\",\n" +
//    			"               \"name\":\"实时公交查询\",\n" +
//    			"               \"url\":\"http://web.chelaile.net.cn/wwd/index?src=webapp_weixin_chongqing\"\n" +
//    			"            },\n" +
//    			"           {\n" +
//    			"               \"type\":\"view\",\n" +
//    			"               \"name\":\"长途汽车购票\",\n" +
//    			"               \"url\":\"http://wxm.pmpsys.cn/wx-073f946c-0273/index.jspx\"\n" +
//    			"            },\n" +
//    			"            {\t\n" +
//    			"               \"type\":\"view\",\n" +
//    			"               \"name\":\"旅游专车购票\",\n" +
//    			"               \"url\":\"http://wx.iu73.com/wxWeb/rwyBusList.aspx\"\n" +
//    			"            },\n" +
//    			"            {\n" +
//    			"               \"type\":\"view\",\n" +
//    			"               \"name\":\"火车班次查询\",\n" +
//    			"               \"url\":\"http://mobile.12306.cn/weixin/wxcore/init\"\n" +
//    			"            },\n" +
//    			"            {\n" +
//    			"               \"type\":\"view\",\n" +
//    			"               \"name\":\"航班动态信息\",\n" +
//    			"               \"url\":\"http://m.veryzhun.com/flight/?token=5cf2036c3db9fe08a7ee0c9b2077d37d\"\n" +
//    			"            }]\n" +
//    			"       },\n" +
//    			"      {\n" +
//    			"           \"name\":\"实时路况\",\n" +
//    			"           \"sub_button\":[\n" +
//    			
//                "            {\n" +
//                "               \"type\":\"view\",\n" +
//                "               \"name\":\"实时路况截图\",\n" +
//                "               \"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx915ad295909bf037&redirect_uri=http%3a%2f%2fwww.cq96096.cn%2fvideoImg%2flist&response_type=code&scope=snsapi_base&state=123&connect_redirect=1#wechat_redirect\"\n" +
//                "            },\n" +
//                "            {\n" +
//                "               \"type\":\"view\",\n" +
//                "               \"name\":\"驾照扣分查询\",\n" +
//                "               \"url\":\"http://www.weiwubao.com/traffic/800066/add_bind/?btype=3&client=h7pGTzBsa0PWb7aNeZ8UmXxs5ciaZ3LYpxeS790SNwC\"\n" +
//                "            },\n" +
//                "            {\n" +
//                "               \"type\":\"view\",\n" +
//                "               \"name\":\"车辆违章查询\",\n" +
//                "               \"url\":\"http://www.weiwubao.com/traffic/800066/add_bind/?btype=2&client=7a8la2HzL8EffuEVB9zbnF6FeY-yPHUrDl9saQT1hih\"\n" +
//                "            },\n" +
//                "            {\n" +
//                "               \"type\":\"view\",\n" +
//                "               \"name\":\"ETC 消费查询\",\n" +
//                "               \"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx698f18eaeb3c7a6e&redirect_uri=http://cqetcweixin.u-road.com/CQETCWechatAPIServer/index.php/cqetcserver/etcbind&response_type=code&scope=snsapi_base&state=123#wechat_redirect\"\n" +
//                "            },\n" +
//                "           {\t\n" +
//                "               \"type\":\"view\",\n" +
//                "               \"name\":\"罚款网上缴费\",\n" +
//                "               \"url\":\"https://tts.yiji.com/tts/index.html?outOrderNo=201607111106110475&partner=CQJS&notifyUrl=800066.wap.weiwubao.com/site/index/callback\"\n" +
//                "            }]\n" +
//                "       },\n" +
//                
//                "      {\n" +
//                "           \"name\":\"春运活动\",\n" +
//                "           \"sub_button\":[\n" +
//                "            {\t\n" +
//                "               \"type\":\"view\",\n" +
//                "               \"name\":\"随手拍活动\",\n" +
//                "               \"url\":\"http://www.cq96096.cn/activity/home\"\n" +
//                "            },\n" +
//                "            {\n" +
//                "               \"type\":\"view\",\n" +
//                "               \"name\":\"奖品码兑换\",\n" +
//                "               \"url\":\"http://www.twzgo.com/mobile/Activity/jwcj\"\n" +
//                "            },\n" +
//                "            {\n" +
//                "               \"type\":\"click\",\n" +
//                "               \"name\":\"96096人工服务\",\n" +
//                "               \"key\":\"MAN_SERVICE\"\n" +
//                "            }]\n" +
//                "       }]\n" +
//                " }";
//    	WeiXinOperUtil.createWxMenu(menuString, WeiXinOperUtil.getAccessToken());
//    }
//    /**
//     * 创建菜单
//     */
//    public void createMenu(String openId) {
//    	String menuString = " {\n" +
//                "     \"button\":[\n" +
//                "      {\n" +
//                "           \"name\":\"出行服务\",\n" +
//                "           \"sub_button\":[\n" +
//                "            {\n" +
//                "               \"type\":\"view\",\n" +
//                "               \"name\":\"实时公交查询\",\n" +
//                "               \"url\":\"http://web.chelaile.net.cn/wwd/index?src=webapp_weixin_chongqing\"\n" +
//                "            },\n" +
//                "           {\n" +
//                "               \"type\":\"view\",\n" +
//                "               \"name\":\"长途汽车购票\",\n" +
//                "               \"url\":\"http://wxm.pmpsys.cn/wx-073f946c-0273/index.jspx\"\n" +
//                "            },\n" +
//				"            {\t\n" +
//				"               \"type\":\"view\",\n" +
//				"               \"name\":\"旅游专车购票\",\n" +
//				"               \"url\":\"http://wx.iu73.com/wxWeb/rwyBusList.aspx\"\n" +
//				"            },\n" +
//                "            {\n" +
//                "               \"type\":\"view\",\n" +
//                "               \"name\":\"火车班次查询\",\n" +
//                "               \"url\":\"http://mobile.12306.cn/weixin/wxcore/init\"\n" +
//                "            },\n" +
//                "            {\n" +
//                "               \"type\":\"view\",\n" +
//                "               \"name\":\"航班动态信息\",\n" +
//                "               \"url\":\"http://m.veryzhun.com/flight/?token=5cf2036c3db9fe08a7ee0c9b2077d37d\"\n" +
//                "            }]\n" +
//                "       },\n" +
//                "      {\n" +
//                "           \"name\":\"违章缴费\",\n" +
//                "           \"sub_button\":[\n" +
//
//                "            {\n" +
//                "               \"type\":\"view\",\n" +
//                "               \"name\":\"驾照扣分查询\",\n" +
//                "               \"url\":\"http://www.weiwubao.com/traffic/800066/add_bind/?btype=3&client=h7pGTzBsa0PWb7aNeZ8UmXxs5ciaZ3LYpxeS790SNwC\"\n" +
//                "            },\n" +
//                "            {\n" +
//                "               \"type\":\"view\",\n" +
//                "               \"name\":\"车辆违章查询\",\n" +
//                "               \"url\":\"http://www.weiwubao.com/traffic/800066/add_bind/?btype=2&client=7a8la2HzL8EffuEVB9zbnF6FeY-yPHUrDl9saQT1hih\"\n" +
//                "            },\n" +
//                "            {\n" +
//                "               \"type\":\"view\",\n" +
//                "               \"name\":\"ETC 消费查询\",\n" +
//                "               \"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx698f18eaeb3c7a6e&redirect_uri=http://cqetcweixin.u-road.com/CQETCWechatAPIServer/index.php/cqetcserver/etcbind&response_type=code&scope=snsapi_base&state=123#wechat_redirect\"\n" +
//                "            },\n" +
//                "           {\t\n" +
//                "               \"type\":\"view\",\n" +
//                "               \"name\":\"罚款网上缴费\",\n" +
//                "               \"url\":\"https://tts.yiji.com/tts/index.html?outOrderNo=201607111106110475&partner=CQJS&notifyUrl=800066.wap.weiwubao.com/site/index/callback\"\n" +
//                "            }]\n" +
//                "       },\n" +
//                
//                "      {\n" +
//                "           \"name\":\"路况截图\",\n" +
//                "           \"sub_button\":[\n" +
//				"            {\t\n" +
//				"               \"type\":\"view\",\n" +
//				"               \"name\":\"实时路况截图\",\n" +
//				"               \"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx915ad295909bf037&redirect_uri=http%3a%2f%2fwww.cq96096.cn%2fvideoImg%2flist&response_type=code&scope=snsapi_base&state=123&connect_redirect=1#wechat_redirect\"\n" +
//				"            },\n" +
//                "            {\n" +
//                "               \"type\":\"view\",\n" +
//                "               \"name\":\"的士失物招领\",\n" +
//                "               \"url\":\"http://mobile.cq.qq.com/taxi/pub/collect/index\"\n" +
//                "            },\n" +
//                "            {\n" +
//                "               \"type\":\"click\",\n" +
//                "               \"name\":\"96096人工服务\",\n" +
//                "               \"key\":\"MAN_SERVICE\"\n" +
//                "            }]\n" +
//                "       }]\n" +
//                " }";
//        WeiXinOperUtil.createWxMenu(menuString, WeiXinOperUtil.getAccessToken());
//    }
    /**
     * 订阅公众号
     *
     * @param map
     */
    private void subcript(Map<String, String> map, HttpServletResponse response) {
        if (null == map || map.size() < 1)
            return;
        String openId = map.get("FromUserName");
        String publicID = map.get("ToUserName");
        createMenu(publicID);
        List<WeixinUserInfoEntity> list = weixinUserInfoService.findByProperty(WeixinUserInfoEntity.class, "wxOpenId", openId);
        if (null == list || 0 == list.size()) {
            String userInfo = WeiXinOperUtil.getUserInfo(WeiXinOperUtil.getAccessToken(map.get("ToUserName")), openId);
            WeixinUserInfoEntity weixinUserInfoEntity = JSON.parseObject(userInfo, WeixinUserInfoEntity.class);
            weixinUserInfoEntity.setSubscribeTime(new Timestamp(System.currentTimeMillis()));
            weixinUserInfoEntity.setSubscribleTimes(1);
            weixinUserInfoEntity.setUserStatus(1);
            weixinUserInfoEntity.setWxOpenId(openId);
            weixinUserInfoService.save(weixinUserInfoEntity);
            logger.info(String.format("用户：%s在%s订阅公众号.", weixinUserInfoEntity.getNickname(), DateUtils.date2Str(DateUtils.datetimeFormat)));
        } else if (list.size() > 0) {
            WeixinUserInfoEntity weixinUserInfoEntity = list.get(0);
            weixinUserInfoEntity.setSubscribeTime(new Timestamp(System.currentTimeMillis()));
            weixinUserInfoEntity.setSubscribleTimes(weixinUserInfoEntity.getSubscribleTimes() + 1);
            weixinUserInfoEntity.setUserStatus(1);
            weixinUserInfoService.saveOrUpdate(weixinUserInfoEntity);
            logger.info(String.format("用户：在%s再次订阅公众号.", weixinUserInfoEntity.getNickname(), DateUtils.date2Str(DateUtils.datetimeFormat)));
        }
        NavigationMenuEntity entity = new NavigationMenuEntity();
        navMenuService.sendMenuWxHint(map, response, openId, "-1", entity, "感谢您关注重庆交通微信公众号，这里有全面的出行服务信息，权威的交通资讯发布。\n ");
    }

    /**
     * 退订公众号
     *
     * @param map
     */
    private void unsubscript(Map<String, String> map) {
        if (null == map || map.size() < 1)
            return;
        String openId = map.get("FromUserName");
        List<WeixinUserInfoEntity> list = weixinUserInfoService.findByProperty(WeixinUserInfoEntity.class, "wxOpenId", openId);
        if (null != list && list.size() > 0) {
            WeixinUserInfoEntity weixinUserInfoEntity = list.get(0);
            weixinUserInfoEntity.setUnsubscribeTime(new Timestamp(System.currentTimeMillis()));
            weixinUserInfoEntity.setUserStatus(2);
            weixinUserInfoService.saveOrUpdate(weixinUserInfoEntity);
            logger.info(String.format("用户：%s在%s取消订阅公众号.", list.get(0).getNickname(), DateUtils.date2Str(DateUtils.datetimeFormat)));
        }
    }

    
    /**
     * 接收来自微信服务器的消息
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/manualService", method = RequestMethod.GET)
    @ResponseBody
    public void manualService(HttpServletRequest request, HttpServletResponse response) {
    	Map<String, String> map = WeiXinOperUtil.receiveMsgFromWX(request);
    	String openId = request.getParameter("openId");
    	Integer deptId = null;
    	if(StringUtils.isNotBlank(request.getParameter("deptId"))){
    		deptId = Integer.parseInt(request.getParameter("deptId"));
    	}
    	//相当于用户输入了0,在服务中，则不能进入人工服务，包括评分
//		manualService(response, map, openId);
		navMenuService.manualService(response, map, openId, deptId);
    }

    
  
    

    private void checkUser(String publicID,String openId) {
    	try {
    		List<WeixinUserInfoEntity> list = weixinUserInfoService.findByProperty(WeixinUserInfoEntity.class, "wxOpenId", openId);
            if (null == list || 0 == list.size()) {
                String userInfo = WeiXinOperUtil.getUserInfo(WeiXinOperUtil.getAccessToken(publicID), openId);
                WeixinUserInfoEntity weixinUserInfoEntity = JSON.parseObject(userInfo, WeixinUserInfoEntity.class);
                weixinUserInfoEntity.setSubscribeTime(new Timestamp(System.currentTimeMillis()));
                weixinUserInfoEntity.setSubscribleTimes(1);
                weixinUserInfoEntity.setUserStatus(1);
                weixinUserInfoEntity.setWxOpenId(openId);
                weixinUserInfoService.save(weixinUserInfoEntity);
            }else{
                WeixinUserInfoEntity weixinUserInfoEntity = list.get(0);
                String userInfo = WeiXinOperUtil.getUserInfo(WeiXinOperUtil.getAccessToken(publicID), openId);
                WeixinUserInfoEntity weixinUserInfoEntity2 = JSON.parseObject(userInfo, WeixinUserInfoEntity.class);
                if(weixinUserInfoEntity2 !=null && StringUtils.isNotBlank(weixinUserInfoEntity2.getHeadImgUrl())){
                	if(weixinUserInfoEntity2.getHeadImgUrl().equals(weixinUserInfoEntity.getHeadImgUrl())){
                    	weixinUserInfoEntity.setHeadImgUrl(weixinUserInfoEntity2.getHeadImgUrl());
                    	weixinUserInfoEntity.setHeadImg(null);//需要更新头像
                    }
                }
                weixinUserInfoEntity.setNickname(weixinUserInfoEntity2.getNickname());//更新昵称
                weixinUserInfoEntity.setProvince(weixinUserInfoEntity2.getProvince());//更新省
                weixinUserInfoEntity.setCity(weixinUserInfoEntity2.getCity());//更新市
                weixinUserInfoEntity.setCountry(weixinUserInfoEntity2.getCountry());//更新县
                weixinUserInfoService.saveOrUpdate(weixinUserInfoEntity);
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }

    /**
     * 从缓存中获取tmepEntity
     * @param openId
     * @return
     */
//    public static WxStatusTmpTEntity getTempMemery(String openId){
//    	WxStatusTmpTEntity tempEntity = null;
//    	if(null != WeiXinConst.waitingMap.get(openId)){
//    		tempEntity = WeiXinConst.waitingMap.get(openId);
//    	} else if(null != WeiXinConst.servicingMap.get(openId)){
//    		tempEntity = WeiXinConst.servicingMap.get(openId);
//    	} else if(null != WeiXinConst.deletedMap.get(openId)){
//    		tempEntity = WeiXinConst.deletedMap.get(openId);
//    	}
//    	return tempEntity;
//    }
    
    /**
     * 检查用户是否已经在服务状态
     *
     * @param openId
     * @return
     */
//    public static List<WxStatusTmpTEntity> checkUserInMemery(String openId) {
//
//        List<WxStatusTmpTEntity> list = new ArrayList<WxStatusTmpTEntity>();
//        if (null != WeiXinConst.waitingMap.get(openId)) {
//            list.add(WeiXinConst.waitingMap.get(openId));
//        }
//        if (null != WeiXinConst.servicingMap.get(openId)) {
//            list.add(WeiXinConst.servicingMap.get(openId));
//        }
//        if (null != WeiXinConst.deletedMap.get(openId)) {
//            list.add(WeiXinConst.deletedMap.get(openId));
//        }
//        if (list.size() > 1) {
//            logger.info("排队的map数据发生了异常.size>1");
//            return new ArrayList<WxStatusTmpTEntity>();
//        }
//        return list;
//    }

    /**
     * 微信用户評分 
     *
     * @param map
     * @param entity
     */
//    private void comment(Map<String, String> map, WxStatusTmpTEntity entity) {
//        if (null == entity
//                || 0 == map.size()
//                || StringUtil.isEmpty(entity.getMyTimestamp())) {
//            return;
//        }
//        List<CustomerServiceHisEntity> li = customerServiceHisService.findByProperty(CustomerServiceHisEntity.class, "sessionId", entity.getMyTimestamp());
//        CustomerServiceHisEntity hisEntity = null;
//        if (null != li && li.size() > 0) {
//            hisEntity = li.get(0);
//            if ("text".equals(map.get("MsgType"))
//                    && ("1".equals(map.get("Content")) ||  "【1】".equals(map.get("Content")) )
//                    && 3 == entity.getServiceStatus()
//                    && null != hisEntity) {
//                WeiXinConst.servicingMap.remove(entity.getWxOpenid());
//                hisEntity.setWxComment("1");
//                customerServiceHisService.saveOrUpdate(hisEntity);
//            } else if ("text".equals(map.get("MsgType"))
//                    && ("2".equals(map.get("Content")) || "【2】".equals(map.get("Content")) )
//                    && 3 == entity.getServiceStatus()
//                    && null != hisEntity) {
//                WeiXinConst.servicingMap.remove(entity.getWxOpenid());
//                hisEntity.setWxComment("2");
//                customerServiceHisService.saveOrUpdate(hisEntity);
//            }/* else if ("text".equals(map.get("MsgType"))
//                    && "3".equals(map.get("Content"))
//                    && 3 == entity.getServiceStatus()
//                    && null != hisEntity) {
//                WeiXinConst.servicingMap.remove(entity.getWxOpenid());
//                hisEntity.setWxComment("3");
//                customerServiceHisService.saveOrUpdate(hisEntity);
//            } */
//        }
//    }
    
    /**
     * 微信用户第一次进来系统提示
     */
//    private void sendMsgOneWxHint(Map<String, String> map, HttpServletResponse response, String openId){
//    	if ("text".equals(map.get("MsgType")) && ("0".equals(map.get("Content")) || "【0】".equals(map.get("Content")))) {
//            manualService(response, map, openId);
//        } else if ("text".equals(map.get("MsgType")) && ( "1".equals(map.get("Content")) || "【1】".equals(map.get("Content")) )) {
//            String returnStr = XmlUtil.gen1ArticlesResponseMsg(map, "路况与截图", "获取路况与截图信息，请点击", "http://cq96096.cn/videoImg/list?type=2&openId="+openId);
//            WeiXinOperUtil.sendMsgToWX(response, returnStr);
//        } else if ("text".equals(map.get("MsgType")) && ("2".equals(map.get("Content")) || "【2】".equals(map.get("Content")))) {
//            String returnStr = XmlUtil.gen1ArticlesResponseMsg(map, "长途汽车订票或查询", "长途汽车订票或查询，请点击", "http://wxm.pmpsys.cn/wx-073f946c-0273/index.jspx");
//            WeiXinOperUtil.sendMsgToWX(response, returnStr);
//        } else {
//        	sendOtherMsg(map, response, openId, null);
//        }
//    }
    
//    private void sendOtherMsg(Map<String, String> map, HttpServletResponse response, String openId, String beforeContent){
//    	String gs="<a href=\"http://cq96096.cn/videoImg/list?type=2&openId="+openId+"\">【1】获取路况与截图信息</a>";
//    	String ct="<a href=\"http://wxm.pmpsys.cn/wx-073f946c-0273/index.jspx\">【2】长途汽车订票或查询</a>";
//    	String rg="【0】人工服务";
//    	
//    	if(beforeContent == null){
//    		beforeContent = "";
//    	}
//        String returnStr = XmlUtil.genTextResponseMsg(map, beforeContent+"请选择服务项：\n"+gs+"\n" +
//                ct+"\n" +rg);
//        WeiXinOperUtil.sendMsgToWX(response, returnStr);
//        wxStatusTmpService.saveMsgDatebase(null, beforeContent+"请选择服务项：\n"+gs+"\n" +ct+"\n" +rg, openId);
//    }
}