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
<title>商圈停车</title>
</head>
<body>
	<div class="container">
		<div class="top_bar">
			<img class="back_img" src="${ctx}/image/park/back.png" onclick="onBack()"/>
			<div class="title">商圈停车</div>
		</div>
		<img src="${ctx}/image/park/background_top.png"/>
		<div style="width: 80%; margin:0 auto;">
			<img onclick="onDetails(0)" style="margin:30px 0px 20px 0px;" src="${ctx}/image/park/area_01.png"/>
			<img onclick="onDetails(1)" style="margin:10px 0px 20px 0px;" src="${ctx}/image/park/area_02.png"/>
			<img onclick="onDetails(2)" style="margin:10px 0px 20px 0px;" src="${ctx}/image/park/area_03.png"/>
			<img onclick="onDetails(3)" style="margin:10px 0px 20px 0px;" src="${ctx}/image/park/area_04.png"/>
		</div>
		
		<!-- 
		<div>
			<p>系统正在维护中，暂时无法提供服务，敬请谅解！</p>
		</div>
		 -->
	</div>
</body>
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	function onBack(){
		window.phone.onBack(); 
	}
	
	function onDetails(type){
		if(type == 0){
			location.href ="${ctx}/parkNextInfo.jsp";			
		} else {
			location.href ="${ctx}/parkDetailInfo.jsp?type="+type;
		}
	}
</script>
</html>