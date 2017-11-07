<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>我的</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width" />
	<link rel="stylesheet" type="text/css" href="bbs/css/mine.css">
    <link rel="stylesheet" type="text/css" href="bootstrap-3.3.5-dist/css/bootstrap.min.css">
  </head>
  
  <body style="background-color: #605b5b33;">
  	<div class="menu">
		<div class="container">
			<div class="row">
				<ul class="nav">
		  			<li role="presentation" id="expertsReply"><a onclick="goExpert()">专家解答</a></li>
		  			<li role="presentation" id="trafficInfo"><a onclick="goHighwayCondition()">路况信息</a></li>
		  			<li role="presentation" id="myPostings"><a onclick="goMine()">我的</a></li>
				</ul>
			</div>
		</div>
	</div>
  	<div id="allInvitation"></div>
	  <div class="postPlus">
			<img src="bbs/images/plus.png" onclick="goAddTell()">
		</div>
  </body>

	<script type="text/javascript" src="js/jquery/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="bbs/js/commonality.js"></script>
	<script type="text/javascript" src="bbs/js/mine.js"></script>
	<script type="text/javascript" src="bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
</html>
