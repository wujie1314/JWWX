package org.jiaowei.util;

/**
 * Created by alex on 15-8-5.
 */
public class PageUtil {

    public  static Integer getPageTotal(Long sum,Integer pageSize){
        if(null != sum && sum > 0){
            long temp = sum;
            if(null == pageSize || 0 == pageSize){
                return Integer.MAX_VALUE;
            }else{

                if (temp % pageSize > 0) {
                    return Integer.valueOf((temp / pageSize + 1) + "");
                }else{
                    return Integer.valueOf((temp / pageSize) + "");
                }
            }
        }else{
            return 0;
        }
    }
}
