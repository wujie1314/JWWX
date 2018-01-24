package org.jiaowei.wxutil;

import java.lang.String;
/**
 * 检查参数SQL注入
 * @author wujie
 *
 */
public class sql_inj {
	public static boolean sql_inj(String str){
String inj_str = "'|and|exec|insert|select|delete|update|count|*|%|chr|mid|master|truncate|char|declare|;|or|-|+|,";
//这里的东西还可以自己添加
String[] inj_stra=inj_str.split("\\|");
for (int i=0 ; i <inj_stra.length ; i++ )
{
if (str.indexOf(inj_stra[i])>=0)
{
return true;
}
}
return false;
}
}