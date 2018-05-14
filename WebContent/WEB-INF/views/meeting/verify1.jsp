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
<body>
<div class="banner"><img src="${ctx}/image/meeting/banner_head1.jpg?timeStamp=201805122228"/></div>
<div class="content">	
	<ul>
       	<li onclick="hrefAgenda()"><div class="li_cont bac2"> <img src="${ctx}/image/meeting/icon1.png"/><p>会议议程</p></div></li>
      	<li onclick="hrefSeats()" class="bac"><div class="li_cont bac4"><img src="${ctx}/image/meeting/icon3.png"/><p>参会名单</p></div></li>
      	<li onclick="hrefIntroduce()"><div class="li_cont bac1"><img src="${ctx}/image/meeting/icon2.png"/><p>项目推介</p></div></li>
      	<li onclick="hrefVideo()" class="bac"><div class="li_cont bac3"><img src="${ctx}/image/meeting/icon5.png"/><p>视频影片</p></div></li>
        <li onclick="hrefDatum()"><div class="li_cont bac5"><img src="${ctx}/image/meeting/icon4.png"/><p>现场导图</p></div></li>
      	<li onclick="hrefServiceCenter()" class="bac"><div class="li_cont bac6"><img src="${ctx}/image/meeting/icon6.png"/><p>服务中心</p></div></li>
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
