<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html>
<head>
<base href="<%=basePath%>">

<meta charset="UTF-8">
<title>报警救援</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta name="viewport" content="width=device-width, initial-scale=0.35, minimum-scale=0.35, maximum-scale=0.35, user-scalable=no" >  
<!--引用高德地图API-->
<script type="text/javascript"
	src="https://webapi.amap.com/maps?v=1.4.0&key=d34f6dedd69d3b336dd569ea989a6234"></script>
<link rel="stylesheet" type="text/css"
	href="alarmRescue/css/alarmRescue.css">
<link rel="stylesheet" type="text/css"
	href="alarmRescue/css/bootstrap.css">
<script src="alarmRescue/js/jquery-2.1.1.min.js"></script>
<script src="alarmRescue/js/bootstrap.js"></script>
<link rel="stylesheet" type="text/css"
	href="alarmRescue/css/fileinput.min.css">
<script src="alarmRescue/js/fileinput.min.js"></script>
<script src="alarmRescue/js/zh.js"></script>
<link rel="stylesheet" type="text/css"
	href="alarmRescue/css/toastr.css">
<script src="alarmRescue/js/toastr.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="alarmRescue/css/jquery.shCircleLoader.css">
<script src="alarmRescue/js/jquery.shCircleLoader.js"></script>
</head>

<body>
	<div class="container">
	<div id = "prompt">提示:若定位偏差较大,请打开百度或高德地图后再重新加载页面获取准确定位</div>
		<div class="row clearfix map">
			<div class="col-xs-12 col-md-12 col-lg-12 column" id="dituContent"></div>
		</div>
		<div class="Relocation">
	<!-- 		<div id="showRepairFactory">
				<img src="alarmRescue/img/icon_repair.png" />
			</div>
			 -->
			<div id="relocation" onclick="againReload();">
				<img src="alarmRescue/img/icon_site.png" />
			</div>
		</div>

		<div class="row clearfix foot">
			<div class="col-xs-12 col-md-12 col-lg-12 column contentFoot">
				<div class="row clearfix ">
					<div class="col-xs-2 col-md-2 col-lg-2 column">
						<label class="labelInfo">当前位置:</label>
					</div>
					<div class="col-xs-10 col-md-10 col-lg-10 column">
						<label id="currentLocation"></label>
					</div>
				</div>
				<hr />
				<div class="row clearfix">
					<div class="col-xs-2 col-md-2 col-lg-2 column">
						<label class="labelInfo">行驶方向:</label>
					</div>
					<div class="col-xs-10 col-md-10 col-lg-10 column location" style="display:flex;flex-direction:row;">
						<input name="direction" type="text" placeholder="请输入起点"
							id="startPosition" />
						<hr
							style="width:8% !important;border:none;margin:auto !important;display:flex;border-top:5px solid #333;" />
						<input name="direction" type="text" placeholder="请输入终点"
							id="endPosition" />
					</div>

				</div>
				<hr />
				<div class="row clearfix">
					<div class="col-xs-2 col-md-2 col-lg-2 column">
						<label class="labelInfo">联系电话:</label>
					</div>
					<div class="col-xs-10 col-md-10 col-lg-10 column tel">
						<input name="phone" type="text" placeholder="请输入联系电话"
							id="phoneNum"></input> 
			<div id="frame">
						<!--  <img src="alarmRescue/img/icon_message.png" id="message" />-->	
							<img src="alarmRescue/img/icon_phone.png" id="callPhone"/>				
			</div>
					</div>
				</div>
				<hr />
				<div class="row clearfix">
					<div class="col-xs-2 col-md-2 col-lg-2 column">
						<label class="labelInfo">维修原因:</label>
					</div>
					<div class="col-xs-10 col-md-10 col-lg-10 column emergency">
						<label> <input name="repairReason" type="radio"
							id="emergency1" checked="checked" name="报警" value="报警" />报警
						</label> <label style="margin-left:5% !important;"> <input
							name="repairReason" type="radio" name="求助" value="求助"
							id="emergency2">求助
						</label>
					</div>
				</div>
				<hr />
				
		 	<div class="row clearfix">
					<div class="col-xs-2 col-md-2 col-lg-2 column">
						<label class="labelInfo">文件上传:</label>
					</div>
					<div class="col-xs-10 col-md-10 col-lg-10 column file">
						<input type="file" id="file" name="file" class="file" multiple="multiple" data-show-caption="false" accept="video/*,image/*" />
					</div>
				</div>   
				<hr />
				<div class="row clearfix submitBut">
					<label id="pastDue">该工单已过期</label>
					<div class="col-xs-12 col-md-12 col-lg-12 column submit">
						<input type="button" value="提交" id="submitButton"
							onclick="submit();">
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

<script src="alarmRescue/js/alarmRescue.js"></script>
</html>