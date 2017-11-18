package org.jiaowei.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jiaowei.common.service.CommonService;

public interface WriteAboutService extends CommonService{

	String specialist(HttpServletRequest request, List<String> imgFile,
			String oppennID, String content, String name, String title);

	String announce(HttpServletRequest request, List<String> imgFile,
			String oppennID, String content, String title);

}
