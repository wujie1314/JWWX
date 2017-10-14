package org.jiaowei.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2015/3/23.
 */
public class FastJsonUtil {

    /**
     * 将一个普通的javaBean对象转换为json
     *
     * @param object
     * @return
     */
    private static SerializeConfig mapping = new SerializeConfig();
    /**
     * 默认的日期格式
     */
    private static String dateFormat;

    static {
        dateFormat = "yyyy-MM-dd HH:mm:ss";
    }

    /**
     * 默认的处理时间
     *
     * @param object
     * @return
     */
    public static String toJSON(Object object) {
        String json = JSON.toJSONString(object, SerializerFeature.WriteDateUseDateFormat);
        JSONObject.toJSON(object);
        return json;
    }
    public static String toJSONWithNull(Object object) {
        String json = JSON.toJSONString(object, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteDateUseDateFormat);
        JSONObject.toJSON(object);
        return json;
    }
    /**
     * 将一个对象转换成一个字符串
     *
     * @param dateFormat 日期格式
     * @param object     要转换的对象
     * @return 返回转换后的字符串
     */
    public static String toJSON(String dateFormat, Object object) {
        mapping.put(Date.class, new SimpleDateFormatSerializer(dateFormat));
        return JSON.toJSONString(object, mapping);
    }

    public static  String toJson(Map map){
        String resturnStr = JSONObject.toJSONString(map);
        return resturnStr;
    }

    /**
     *
     * @param source
     * @param key
     * @return
     */
    public static  String getValue(String source, String key){
        JSONObject jsonObject = JSON.parseObject(source);
        return jsonObject.get(key).toString();
    }

    /**
     *将JDBC返回的resultset转換成json
     * @param rs
     * @return
     * @throws SQLException
     * @throws JSONException
     */
    public static String resultSetToJson(ResultSet rs) throws SQLException,JSONException
    {
        // json数组
        JSONArray array = new JSONArray();
        // 获取列数
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        // 遍历ResultSet中的每条数据
        while (rs.next()) {
            JSONObject jsonObj = new JSONObject();
            // 遍历每一列
            for (int i = 1; i <= columnCount; i++) {
                String columnName =metaData.getColumnLabel(i);
                String value = rs.getString(columnName);
                jsonObj.put(columnName, value);
            }
            array.add(jsonObj);
        }
        return array.toString();
    }
}
