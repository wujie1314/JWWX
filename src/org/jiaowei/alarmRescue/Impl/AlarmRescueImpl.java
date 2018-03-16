package org.jiaowei.alarmRescue.Impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
import org.jiaowei.alarmRescue.controller.MysqlConn;
import org.jiaowei.alarmRescue.service.IAlarmRescueService;
import org.springframework.stereotype.Service;

@Service("alarmRescue")
public class AlarmRescueImpl implements IAlarmRescueService {
	@Override
	public String getAlarmInfo(String ID, String currentLocation,
			String longitude, String latitude, String startPosition,
			String endPosition, String phoneNum, String repairReason) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		String content_desc = "方向:" + startPosition + "-->" + endPosition
				+ "  维修原因:" + repairReason;
		String contact_way = phoneNum;
		if (repairReason.equals("报警")) {
			map.put("type", "报警");
		} else if (repairReason.equals("求助")) {
			map.put("type", 13);
		}
		map.put("ID", ID);
		map.put("currentLocation", currentLocation);
		map.put("content_desc", content_desc);
		map.put("contact_way", contact_way);
		map.put("longitude", longitude);
		map.put("latitude", latitude);

		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");// 设置日期格式
		String date = dateFormat.format(new Date());// new

		map.put("create_time", date);
		boolean bolResult = false;
		try {
			if (longitude == "" || longitude.isEmpty() || longitude == null
					|| latitude == "" || latitude.isEmpty() || latitude == null) {
				return "false";
			} else {
				// 外网 经纬度查询高速公路编号
			//	String getLXBHInfoUrl = "http://113.207.109.5:9080/CQJTGISAPI/coordinateToRoadCode.do?lat=";
				// 内网 经纬度查询高速公路编号
				 String getLXBHInfoUrl = "http://10.224.5.48:8080/CQJTGISAPI/coordinateToRoadCode.do?lat=";

			String jwd = latitude + "&lng=" + longitude;
			String result = sendGet(getLXBHInfoUrl, jwd);
			JSONObject jsonRoadBH = JSONObject.fromObject(result);
			String roadBH = jsonRoadBH.getString("data");// 高速公路编号

			// 外网 经纬度转桩号
			//String lxbhUrl = "http://113.207.109.5:9080/CQJTGISAPI/getDistanceInPolyline1.do?fileName=";
			// 内网 经纬度转桩号
			 String lxbhUrl = "http://10.224.5.48:8080/CQJTGISAPI/getDistanceInPolyline1.do?fileName=";
			
				String param = roadBH + "&lat=" + latitude + "&lng="
						+ longitude + "&deviation=1000";
				String mileage = sendGet(lxbhUrl, param);
				JSONObject json = JSONObject.fromObject(mileage);
				mileage = json.getString("data");
				String[] mileageArr = mileage.split("[.]");
				int m;
				if (mileageArr[1].length() > 3)
					m = Integer.parseInt(mileageArr[1].substring(0, 3));
				else
					m = Integer.parseInt(mileageArr[1]);
				String mileageInfo = roadBH + "  " + mileageArr[0] + "KM+" + m
						+ "M";
				map.put("MILEAGE", mileageInfo);
				bolResult = MysqlConn.dataManipulation(map);
			}
		} catch (IllegalStateException e) {
			bolResult = false;
			e.printStackTrace();
		} catch (Exception e) {
			bolResult = false;
			e.printStackTrace();
		}
		if (bolResult) {
			return "true";
		} else
			return "false";
	}

	/**
	 * 发送http的get请求
	 */
	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + param;
			System.out.println(urlNameString);
			URL realUrl = new URL(urlNameString);
			URLConnection connection = realUrl.openConnection();
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			connection.connect();
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

}
