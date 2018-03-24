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
		
		
		
		createMenu("gh_f689874cef4f", WeiXinOperUtil.menu1());
		createMenu("gh_2ed2a7b99aa4", WeiXinOperUtil.menu1());
		

//		for (int i = 0; i < args.length; i++) {
//			createMenu(args[i], WeiXinOperUtil.menu1());
//		}
	}

	public static void createMenu(String publicId, String menuString) {
		WeiXinOperUtil.createWxMenu(menuString,
				WeiXinOperUtil.getAccessToken(publicId)); // 菜单 这里不需要更改
	}

}
