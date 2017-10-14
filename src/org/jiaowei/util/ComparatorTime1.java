package org.jiaowei.util;

import org.jiaowei.entity.MsgFromCustomerServiceEntity;
import org.jiaowei.entity.MsgFromWxEntity;

import java.util.Comparator;

/**
 * Created by alex on 15-12-25.
 */
public class ComparatorTime1 implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        MsgFromCustomerServiceEntity entity1 = (MsgFromCustomerServiceEntity) o1;
        MsgFromCustomerServiceEntity entity2 = (MsgFromCustomerServiceEntity) o2;
        int flag=entity1.getCreateTime().compareTo(entity2.getCreateTime());
        if(flag==0){
            return entity1.getCreateTime().compareTo(entity2.getCreateTime());
        }else{
            return flag;
        }
    }
}
