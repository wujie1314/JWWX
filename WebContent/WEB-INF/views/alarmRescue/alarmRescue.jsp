<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<meta charset="UTF-8">
<title>报警救援</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta name="keywords" content="百度地图,百度地图API，百度地图自定义工具，百度地图所见即所得工具" />
<meta name="description" content="百度地图API自定义地图，帮助用户在可视化操作下生成百度地图" />
<!--引用百度地图API-->
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=37CVkrQsay3OgzWeXHC6TuWrGAdFZa8C
"></script>
<link rel="stylesheet" type="text/css" href="alarmRescue/css/alarmRescue.css">

<script src="alarmRescue/js/jquery-2.1.1.min.js"></script>

</head>

<body>
	<!--百度地图容器-->
	
		<div id="dituContent"></div>
		
		<div class="contentChoice">
			<div class="repairDepot">
				<div class="repairFrame">
					<label class="repairDepotName">喜车郎汽修</label>
				</div>
			</div>

			<div class="repairDepot">
				<div class="repairFrame">
					<label class="repairDepotName">重庆达仁轮毅修复中心</label>
				</div>
			</div>

			<div class="repairDepot">
				<div class="repairFrame">
					<label class="repairDepotName">恒远路奥汽修</label>
				</div>
			</div>
			
			<div class="repairDepot">
				<div class="repairFrame">
					<label class="repairDepotName">万达汽修</label>
				</div>
			</div>
			
			<div class="repairDepot">
				<div class="repairFrame">
					<label class="repairDepotName">4S汽修</label>
				</div>
			</div>
			
			<div class="repairDepot">
				<div class="repairFrame">
					<label class="repairDepotName">破车汽修</label>
				</div>
			</div>
			
			<div class="repairDepot">
				<div class="repairFrame">
					<label class="repairDepotName">骏腾汽修</label>
				</div>
			</div>
			
			<div class="repairDepot">
				<div class="repairFrame">
					<label class="repairDepotName">思铂利 汽修</label>
				</div>
			</div>
			
		</div>

		<div class="Relocation">

			<div id="relocation" onclick="createMap();">
				<img src="alarmRescue/img/icon_site.png" />
			</div>

		</div>

		<div class="contnetFoot">
			<div class="currentPosition">
				<label class="labelInfo">当前位置:</label> <label id="currentLocation">G50s</label>
			</div>
			<hr />
			<!--  	<div class="drivingDirection">
		<label class="labelInfo">行驶方向:</label>
		<div class="choiceDirretion">
			<label><input name="direction" type="radio" value="上行" />上行</label> 
			<label><input name="direction" type="radio" value="下行" />下行</label> 
			<label><input name="direction" type="radio" value="双向" />双向</label> 
		</div>
	</div> -->
			<div class="phoneNumber">
				<label class="labelInfo">联系电话:</label> <input name="phone"
					type="text" placeholder="请输入联系电话" id="phoneNum" />
			</div>
			<hr />

			<div id="phone" onclick="callPhone();">
				<img src="alarmRescue/img/phone.png" />
			</div>

			<div class="repairReason">
				<label class="labelInfo">维修原因:</label>
				<div class="emergency">
					<label><input name="repairReason" type="checkbox"
						value="爆胎" id="emergency1" />爆胎</label> <label><input
						name="repairReason" type="checkbox" value="没油" id="emergency2" />没油</label>
					<label><input name="repairReason" type="checkbox"
						value="拖车" id="emergency3" />拖车</label>
				</div>

			</div>

			<div class="submit">
				<input type="button" value="提交" id="submitButton"
					onclick="submit();">
			</div>
		</div>

</body>
<!-- 百度地图 -->
<script src="alarmRescue/js/alarmRescue.js"></script>
</html>