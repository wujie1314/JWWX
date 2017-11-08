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
    public WeixinPublicInfoEntity getPublicInfoById(String public_Id) {
        List<WeixinPublicInfoEntity> result = findByHql("FROM WeixinPublicInfoEntity  p WHERE p.id = '"+public_Id+"'");
        if(result == null ){
            System.out.println("没有找到对应的公众号ID");
            return null;
        }
        else if (result.size() != 1){
            System.out.println("公众号不唯一");
            return null;
        }
        else{
            return result.get(0);
        }

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
