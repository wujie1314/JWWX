package org.jiaowei.alarmRescue.Impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.jiaowei.alarmRescue.controller.MysqlConn;
import org.jiaowei.alarmRescue.service.IAlarmRescueService;
import org.springframework.stereotype.Service;

@Service("alarmRescue")
public class AlarmRescueImpl implements IAlarmRescueService {
	@Override
	public Map<String, Object> getAlarmInfo(String ID, String currentLocation,
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
													// Date()为获取当前系统时间，也可使用当前时间戳
		/*
		 * if(ID != null && !ID.isEmpty()) map.put("update_time", date); else
		 * map.put("create_time", date);
		 */
		map.put("create_time", date);

		// 获取桩号
		String getLXBHInfoUrl = "http://restapi.amap.com/v3/geocode/regeo?output=xml&location=%s&key=56548ad2ea98a8269c616522f1d49223&radius=1000&extensions=all";
		// 外网
		// String lxbhUrl =
		// "http://203.93.109.50:8096/TGISServer_1/rest/base/stakeConvertService/getZHByXY?tableName=TR_ROAD_ROADINFO&qdzhField=start_stakeid&zdzhField=end_stakeid&idField=ROAD_CODE&lxbh=";
		// 内网
		// String
		// lxbhUrl="http://10.224.5.162:8096/TGISServer_1/rest/base/stakeConvertService/getZHByXY?tableName=TR_ROAD_ROADINFO&qdzhField=start_stakeid&zdzhField=end_stakeid&idField=ROAD_CODE&lxbh=";

		// 外网
		//String lxbhUrl = "http://113.207.109.5:9080/CQJTGISAPI/getDistanceInPolyline1.do?fileName=";
		// 内网
		 String lxbhUrl = "http://10.224.5.48:8080/CQJTGISAPI/getDistanceInPolyline1.do?fileName=";
		
		String reportLocation = longitude + "," + latitude;
		String urlRequest = String.format(getLXBHInfoUrl, reportLocation);
		String XmlResponse = getHttpResponse(urlRequest);

		String LXBH = GetLXBH(XmlResponse);// 获取高速公路编号
		String[] LXBHS = LXBH.split(",");
		char initial = LXBHS[0].charAt(0);
		// 如果有多个高速编号则取第一个
		if (LXBHS[0] == "" || !((initial >= 'A' && initial <= 'Z') || (initial >= 'a' && initial <= 'z'))) {
			System.out.println("====>高速编号为空");
			map.put("MILEAGE", "无桩号");
		} else {
			String param = LXBHS[0] + "&lat=" + latitude + "&lng=" + longitude
					+ "&deviation=1000";
			String mileage = sendGet(lxbhUrl, param);
			JSONObject json = JSONObject.fromObject(mileage);
			mileage = json.getString("data");
			String[] mileageArr = mileage.split("[.]");
			int m;
			if (mileageArr[1].length() > 3)
				m = Integer.parseInt(mileageArr[1].substring(0, 3));
			else
				m = Integer.parseInt(mileageArr[1]);
			String mileageInfo = LXBHS[0] + "高速" + mileageArr[0] + "KM+" + m
					+ "M";
			map.put("MILEAGE", mileageInfo);
		}
		MysqlConn.dataManipulation(map);
		return null;
	}

	public String getHttpResponse(String allConfigUrl) {
		System.out.println(allConfigUrl);
		BufferedReader in = null;
		StringBuffer result = null;
		try {
			URI uri = new URI(allConfigUrl);
			URL url = uri.toURL();
			URLConnection connection = url.openConnection();
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			connection.setRequestProperty("Charset", "utf-8");
			connection.connect();
			result = new StringBuffer();
			// 读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
			return result.toString();
		} catch (Exception e) {
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
		return null;
	}

	public String GetLXBH(String protocolXML) {

		String LXBHS = "";
		try {
			Document doc = (Document) DocumentHelper.parseText(protocolXML);
			Element response = doc.getRootElement();
			Element regeocode = response.element("regeocode");
			if (regeocode == null) {
				return null;
			}
			Element roads = regeocode.element("roads");
			if (roads == null) {
				return null;
			}
			List road = roads.elements("road");
			String LXBH = "";
			for (int i = 0; i < road.size(); i++) {
				Element ele = (Element) road.get(i);
				LXBH = ele.element("name").getText();
				String name = LXBH;
				LXBH = LXBH.replaceAll("[^a-z^A-Z^0-9]", "");
				if (LXBH == "" || LXBH.isEmpty()) {
					if (LXBHS.isEmpty() || LXBHS == "") {
						LXBHS += MysqlConn.searchRoadNum(name);
					} else {
						LXBHS += "," + MysqlConn.searchRoadNum(name);
					}
				} else {
					if (LXBH == null || LXBH.isEmpty()) {
						continue;
					}
					if (LXBHS.isEmpty() || LXBHS == "") {
						LXBHS += LXBH;
					} else {
						LXBHS += "," + LXBH;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return LXBHS;
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
