package org.jiaowei.service;

import java.util.List;

import org.jiaowei.common.service.CommonService;

public interface WriteAboutService extends CommonService{

	String announce(List<String> imgFile, String oppennID, String content);

}
