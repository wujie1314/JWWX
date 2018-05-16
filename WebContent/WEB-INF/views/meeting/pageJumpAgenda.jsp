<%@page language="java" contentType="text/html;charset=UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta content="telephone=no" name="format-detection">
<title>2018年重庆交通重点项目招商推介会</title>
<link rel="stylesheet" href="${ctx}/css/meeting/pcs.css">
<%-- <link rel="stylesheet" href="${ctx}/css/weui-master/dist/style/weui.min.css" /> --%>
<link rel="stylesheet" href="${ctx}/css/weui-master/dist/example/example.css" />
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.11.3.min.js"></script>
</head>
<style>
.fbt{
	margin-left: 15%;
	color: #666666;
	font-size: 15px;
}
table{
	border-collapse: collapse;
    border: none;
    width: 98%;
    text-align: left;
    margin: 0px;
    color: #333333;
    font-size: 15px;
}
table th, td{
	border: none;
	vertical-align: top;
	height: 30px;
}
.point{
	
}
.point:before{
	content: "";
    display: inline-block;
    position: absolute;
    width: 20px;
    height: 1px;
    background: #3183d6;
    margin-top: 9px;
    left: 19px;
    z-index: 1;
}
.point:after{
	content: "";
    display: inline-block;
    position: absolute;
    width: 8px;
    height: 8px;
    background-color: #3183d6;
    border-radius: 9px;
    left: 16px;
    margin-top: 6px;
    z-index: 1;
}

.vpoint:after{
	content: "";
    display: inline-block;
    position: absolute;
    width: 1px;
    height: calc(100% - 220px);
    background: #3183d6;
    margin-top: 9px;
    margin-bottom: 10px;
    left: 19px;
    z-index: 1;
}

.pointTd{
}
</style>
<body>
<div class="banner"><img src="${ctx}/image/meeting/banner_head1.jpg?timeStamp=20180516001"/></div>
<div class="content">
	<div width="100%" heigh="50" style="text-align: center;margin: 10px;">会议内容及日程安排</div>
	<div class="fbt" width="100%" heigh="50" >会议时间：2018年5月18日 </div>
	<div class="fbt" width="100%" heigh="50" >会议地点：雾都宾馆A幢3楼大会议室</div>
<!-- 	<div class="fbt" width="100%" heigh="50" >主&nbsp;&nbsp;持&nbsp;人：重庆市政府副秘书长岳顺</div> -->
	<div style="margin: 10px 10%;">
		<table>
			<tr>
				<td class="pointTd"><div class="vpoint"></div></div><div class="point"></div></td>
				<td style="width: 100px;color: #3183d6">上午</td>
				<td></td>
			</tr>
			<tr>
				<td class="pointTd"><div class="point"></div></td>
				<td>09:30~10:00</td>
				<td>签到</td>
			</tr>
			<tr>
				<td class="pointTd"><div class="point"></div></td>
				<td>10:00~10:30</td>
				<td>重庆市交通委员会作项目推介</td>
			</tr>
			<tr>
				<td class="pointTd"><div class="point"></div></td>
				<td>10:30~10:35</td>
				<td>重庆相关区（县）政府代表发言</td>
			</tr>
			<tr>
				<td class="pointTd"><div class="point"></div></td>
				<td>10:35~10:40</td>
				<td>企业代表发言</td>
			</tr>
			<tr>
				<td class="pointTd"><div class="point"></div></td>
				<td>10:40~10:45</td>
				<td>金融单位发言</td>
			</tr>
			<tr>
				<td class="pointTd"><div class="point"></div></td>
				<td>10:45~10:55</td>
				<td>重庆市政府领导讲话</td>
			</tr>
			<tr>
				<td class="pointTd"><div class="point"></div></td>
				<td>10:55~11:10</td>
				<td>①、巫山县与北新路桥签订合作框架协议<br>
					②、市交委与中国铁建、中国交建签订特许经营权协议<br>
					③、市交委与六家银行签订框架协议
				</td>
			</tr>
			<tr>
				<td class="pointTd"><div class="point"></div></td>
				<td>11:30~13:00</td>
				<td>午餐</td>
			</tr>
<!-- 			<tr> -->
<!-- 				<td class="pointTd"><div class="point"></div></td> -->
<!-- 				<td style="color: #3183d6;">下午</td> -->
<!-- 				<td></td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td class="pointTd"><div class="point"></div></td> -->
<!-- 				<td>14:00~16:00</td> -->
<!-- 				<td>答疑会</td> -->
<!-- 			</tr> -->
		</table>
	</div>
</div>

</body>
</html>
