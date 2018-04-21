package org.jiaowei.controllers;
import org.jiaowei.wxutil.WeiXinOperUtil;


/**
 * 菜单创建
 * @author wujie
 * 作用：菜单初始化
 * 参数：publicId 公众号ID
 */
public class MenuCreate {

	public static void main(String[] args) {
		
		
		
		//createMenu("gh_1ba95266f60c", WeiXinOperUtil.menu1());
		createMenu("gh_2ed2a7b99aa4", WeiXinOperUtil.menu1());
		

//		for (int i = 0; i < args.length; i++) {
//			createMenu(args[i], WeiXinOperUtil.menu1());
//		}https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx67deab6f932b1573&secret=ac10ad4be495c8703d1fc78702103a3f
	//https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx915ad295909bf037&secret=b26b2f2432620fb532d117fe6d132afa
	}

	public static void createMenu(String publicId, String menuString) {
		WeiXinOperUtil.createWxMenu(menuString,
				WeiXinOperUtil.getAccessToken(publicId));
	}

}
