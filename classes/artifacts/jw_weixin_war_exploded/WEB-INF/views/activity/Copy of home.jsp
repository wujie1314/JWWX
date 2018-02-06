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
	p {
		color: #7D4431;
		font-size: 20px;
		padding: 0px;
		margin-bottom:0px;
		margin-top:8px;
	}
	a {
		text-decoration: none;
		color: #555555;
	}
	.overflow {
	    height: auto;
	    overflow: hidden;
	}
</style>
</head>
<body>
	<div class="container">
		<ul class="overflow" style="margin: 0px;padding:0px;">
			<li style="margin-bottom: -4px;padding:0px;">
				<img style="width:100%;padding:0px;margin: 0px;" src="${ctx}/image/activity/activity_01.jpg"/>
				<!-- 
				<dl>
					<dt class="overflow" style="position: relative;">
						<a href="${ctx}/activity/reportedMsg">
							<img style="position: absolute;left: 0px;z-index: 2;top:248px;" src="${ctx}/image/activity/activity_text.gif"/>
						</a>
					</dt>
					<dd style=" height:40px; padding: 0px; line-height: 40px">
						<p class="text p-no-newline">北城天街上坡方向</p>
					</dd>
				</dl>
				 -->
			</li>
			<li style="margin-bottom: -4px;padding:0px;">
				<a href="${ctx}/activity/reportedMsg">
				<img style="width:100%;padding:0px;margin: 0px;" src="${ctx}/image/activity/activity_02.gif"/>
				</a>
			</li>
			<li style="margin-bottom: -4px;padding:0px;">
				<img style="width:100%;padding:0px;margin: 0px;" src="${ctx}/image/activity/activity_03.jpg"/>
			</li>
			<li style="margin-bottom: -4px;padding:0px;">
				<img style="width:100%;margin: 0px;" src="${ctx}/image/activity/activity_04.jpg"/>
			</li>
			<li style="margin-bottom: -4px;padding:0px;">
				<img style="width:100%;margin: 0px;" src="${ctx}/image/activity/activity_05.jpg"/>
			</li>
		</ul>

	</div>
</body>
</html>