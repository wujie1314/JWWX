package org.jiaowei.service.impl;

import java.util.List;
import org.jiaowei.common.service.impl.CommonServiceImpl;
import org.jiaowei.service.ExpertService;
import org.springframework.stereotype.Service;

@Service
public class ExpertServiceImpl extends CommonServiceImpl implements ExpertService{

	@Override
	public List<Object> getExpertInfo() {
		
		return findByHql("From ExpertEntity");
	}

}
