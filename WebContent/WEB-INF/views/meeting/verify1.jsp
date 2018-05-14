<%@page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta content="telephone=no" name="format-detection">
<title>2018年重庆交通重点项目招商推介会</title>
<link rel="stylesheet" href="${ctx}/css/meeting/pcs.css">
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.11.3.min.js"></script>
</head>
<style>
ul li img{
	width: 100%;
}

.content ul li{
	width: 46%;
}
</style>
<body>
<div class="banner"><img src="${ctx}/image/meeting/banner_head1.jpg?timeStamp=20180514001"/></div>
<div class="content">	
	<ul>
       	<li onclick="hrefAgenda()"><img src="${ctx}/image/meeting/icon_yicheng.png?timeStamp=20180514001"/></li>
      	<li onclick="hrefSeats()" class="bac"><img src="${ctx}/image/meeting/icon_mingdan.png?timeStamp=20180514001"/></li>
      	<li onclick="hrefIntroduce()"><img src="${ctx}/image/meeting/icon_tuijie.png?timeStamp=20180514001"/></li>
      	<li onclick="hrefVideo()" class="bac"><img src="${ctx}/image/meeting/icon_video.png?timeStamp=20180514001"/></li>
        <li onclick="hrefDatum()"><img src="${ctx}/image/meeting/icon_daoshi.png?timeStamp=20180514001"/></li>
      	<li onclick="hrefServiceCenter()" class="bac"><img src="${ctx}/image/meeting/icon_server.png?timeStamp=20180514001"/></li>
    </ul>
	
</div>

</body>
</html>

<script type="text/javascript">

function hrefIntroduce(){
	location.href="${ctx}/meeting/toPage?page=Introduce";
}
function hrefAgenda(){
	location.href="${ctx}/meeting/toPage?page=Agenda";
}
function hrefSeats(){
	location.href="${ctx}/meeting/toPage?page=Seats";
}
function hrefVideo(){
	location.href="${ctx}/meeting/toPage?page=Video";
}
function hrefDatum(){
	location.href="${ctx}/meeting/toPage?page=Datum";
}
function hrefServiceCenter(){
	location.href="${ctx}/meeting/toPage?page=Service";
}

</script>
