package org.jiaowei.service.impl;

import org.jiaowei.common.service.impl.CommonServiceImpl;
import org.jiaowei.entity.WeixinPublicInfoEntity;
import org.jiaowei.service.WeixinPublicInfoService;
import org.jiaowei.wxutil.WeiXinOperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class WeixinPublicInfoServiceImpl extends CommonServiceImpl implements WeixinPublicInfoService {


    @Override
    public Map<String, Object> getPublicInfoById(String public_Id) {
      /*  StringBuffer sql = new StringBuffer();
        sql.append("SELECT * FROM \"PUBLIC_INFO\" where PUBLIC_INFO.\"id\" ='"+public_Id+"' ");
        System.out.println(sql);
        List<Map<String,Object>> result = findBySQL(sql.toString());
        if(null == result || result.size() != 1){
            return null;
        }
        System.err.println(result.get(0) + "=               ================" + result);;*/
        Map<String,Object> result =  WeiXinOperUtil.getPublicInfoById(public_Id);
        return result;
    }

    @Override
    public Map<String, Object> getPublicInfoByDeptID(Integer deptID) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT * FROM \"PUBLIC_INFO\" where PUBLIC_INFO.\"dept_ID\" ='"+deptID+"' ");
        System.out.println(sql);
        List<Map<String,Object>> result = findBySQL(sql.toString());
        if(null == result || result.size() != 1){
            return null;
        }
        return result.get(0);
    }
}
