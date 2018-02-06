<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
<style type="text/css">
body {
		font-family: "微软雅黑";
		background-color: #EFEFEF;
		max-width: 750px;
		margin: 0 auto;
		height: 100%;
		font-size: 15px;
		background: #fff;
	}
	.container{
		width: 100%;
		text-align:center;
	}
	
	.top_bar{
		width:100%;
		height: 3.0em;
		background-color: #222d65;
		z-index:999;
	}
	.top_bar .back_img{
		position:absolute;
		margin: 12px 0px 0px 5px;
		width: 20px;
	}
	.top_bar .title{
		color:#fff; 
		width:100%;
		height: 3.0em;
		line-height: 3.0em;
		text-align:center;
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
	a:focus {  
		outline:none;   
		-moz-outline:none; 
		cursor:pointer;
	}
</style>
<title>渝中区</title>
</head>
<body>
	<div class="container">
		<div class="top_bar">
			<img onclick="onBack()" class="back_img" src="${ctx}/image/park/back.png">
			<div class="title">渝中区</div>
		</div>
		<img src="${ctx}/image/park/background_top.png"/>
		<div style="width: 80%; margin:0 auto;">
			<img onclick="onDetails(4)" style="margin:30px 0px 20px 0px;" src="${ctx}/image/park/area_address_02.png"/>
			<img onclick="onDetails(5)" style="margin:10px 0px 20px 0px;" src="${ctx}/image/park/area_address_03.png"/>
			<img onclick="onDetails(6)" style="margin:10px 0px 20px 0px;" src="${ctx}/image/park/area_address_04.png"/>
			<img onclick="onDetails(7)" style="margin:10px 0px 20px 0px;" src="${ctx}/image/park/area_address_05.png"/>
			<img onclick="onDetails(8)" style="margin:10px 0px 20px 0px;" src="${ctx}/image/park/area_address_06.png"/>
		</div>
	</div>
</body>
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
function onBack(){
	location.href ="${ctx}/parkInfo.jsp";	
}
function onDetails(type){
	location.href ="${ctx}/parkDetailInfo.jsp?type="+type;
}
</script>
</html>