<%@page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta content="telephone=no" name="format-detection">
<title>2016年重庆“互联网+”便捷交通</title>
<link rel="stylesheet" href="${ctx}/css/meeting/pcs.css">
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.11.3.min.js"></script>
</head>
<body>
<div class="banner"><img src="${ctx}/image/meeting/banner_head.jpg"/></div>
<div class="content">	
	<ul>
    	<li onclick="hrefIntroduce()"><div class="li_cont bac1"><img src="${ctx}/image/meeting/icon2.png"/><p>会议介绍</p></div></li>
       	<li onclick="hrefAgenda()" class="bac"><div class="li_cont bac2"> <img src="${ctx}/image/meeting/icon1.png"/><p>会议仪程</p></div></li>
        <li onclick="hrefSeats()"><div class="li_cont bac3"><img src="${ctx}/image/meeting/icon4.png"/><p>座次表</p></div></li>
      	<li onclick="hrefUser()" class="bac"><div class="li_cont bac4"><img src="${ctx}/image/meeting/icon3.png"/><p>参会名册</p></div></li>
        <li onclick="hrefDatum()"><div class="li_cont bac5"><img src="${ctx}/image/meeting/icon5.png"/><p>资料下载</p></div></li>
      	<li onclick="hrefServiceCenter()" class="bac"><div class="li_cont bac6"><img src="${ctx}/image/meeting/icon6.png"/><p>服务中心</p></div></li>
    </ul>
	
</div>

</body>
</html>

<script type="text/javascript">
function onVerify(){
	var mobile = $("#mobile").val();
	$.ajax({
		url : "${ctx}/videoRoad/queryList",
		cache : false,
		type : "post",
		dataType : "json",
		data : {
			"mobile" : mobile
		},
		success : function(data) {
			if(data){
				if(data.code == 0){
					location.href="${ctx}/meeting/home?openId="+openId;
				} else {
					alert("电话号码输入错误");
				}
			}
		}
	});
}

function hrefIntroduce(){
	location.href="${ctx}/meeting/introduce";
}
function hrefAgenda(){
	location.href="${ctx}/meeting/agenda";
}
function hrefSeats(){
	location.href="${ctx}/meeting/seats";
}
function hrefUser(){
	location.href="${ctx}/meeting/user";
}
function hrefDatum(){
	location.href="${ctx}/meeting/datum";
}
function hrefServiceCenter(){
	location.href="${ctx}/meeting/serviceCenter";
}

</script>
