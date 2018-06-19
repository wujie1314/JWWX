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
<script type="text/javascript" src="${ctx}/js/jquery/video.js"></script>
</head>
<body>
<div class="banner"><img src="${ctx}/image/meeting/banner_head1.jpg?timeStamp=20180516001"/></div>
<div class="content">
	<img id="movieImg" style="width: 90%;margin: 5% 5%;" src="${ctx}/image/meeting/movie.jpg?timeStamp=201805122227"/>
	<video id="movieVideo" style="width: 90%;margin: 5% 5%;object-fit:fill;display: none;" 
		controls preload="auto" 
		x-webkit-airplay="true" webkit-playsinline="true" playsinline="true" 
		x5-video-player-type="h5" x5-video-orientation="h5" x5-video-player-fullscreen="true" >
		<source src="movie.ogg" type="video/ogg">
	  	<source src="/upload/meeting/movie.mp4" type="video/mp4">
	  	视频出差
	</video>
	
</div>

</body>
</html>

<script type="text/javascript">
	$(function(){
		$("#movieImg").on("click",function(){
			$("#movieImg").hide();
			$("#movieVideo").show();
		 	$('#movieVideo').get(0).play();
		});
	})

</script>
