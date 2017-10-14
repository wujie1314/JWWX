package org.jiaowei.util;

import org.jiaowei.entity.MsgFromWxEntity;
import java.util.Comparator;

/**
 * Created by alex on 15-12-25.
 */
public class ComparatorTime  implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        MsgFromWxEntity entity1 = (MsgFromWxEntity) o1;
        MsgFromWxEntity entity2 = (MsgFromWxEntity) o2;
        int flag=entity1.getCreateTime().compareTo(entity2.getCreateTime());
        if(flag==0){
            return entity1.getCreateTime().compareTo(entity2.getCreateTime());
        }else{
            return flag;
        }
    }
}
