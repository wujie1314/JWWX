<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
<title>随手拍活动</title>
<style>
	body {
		font-family: "微软雅黑";
		background-color: #EFEFEF;
		max-width: 640px;
		margin: 0 auto;
		height: 100%;
		font-size: 15px;
		
	}
	.container{
		width: 100%;
		background: #fff;
	}
	a {
		text-decoration: none;
		color: #555555;
		outline:none;
	}
	.overflow {
	    height: auto;
	    overflow: hidden;
	}
	img {
		width:100%;
		display:block;
	}
	a:focus {  outline:none;   -moz-outline:none; cursor:pointer;}
	.cursor {
		cursor:pointer;
	}
</style>
</head>
<body>
	<div class="container">
		<img src="${ctx}/image/activity/activity_01.jpg"/>
		<img onclick="reportedMsg()" class="cursor" src="${ctx}/image/activity/activity_02.gif"/>
		<img src="${ctx}/image/activity/activity_03.jpg"/>
		<img src="${ctx}/image/activity/activity_04.jpg"/>
		<img src="${ctx}/image/activity/activity_05.jpg"/>
	</div>
</body>
<script type="text/javascript">
function reportedMsg(){
	window.location.href="${ctx}/activity/reportedMsg";
}
	
</script>
</html>