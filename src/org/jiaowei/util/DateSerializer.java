package org.jiaowei.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 此类用于springmvc 返回日期类型时的格式化
 * @author Administrator
 *
 */
public class DateSerializer extends JsonSerializer<Date> {
	 @Override
	  public void serialize(Date value, JsonGenerator jgen,SerializerProvider provider)
	    throws IOException,JsonProcessingException {
	   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   String formattedDate = formatter.format(value);
	   jgen.writeString(formattedDate);
	  }

}
