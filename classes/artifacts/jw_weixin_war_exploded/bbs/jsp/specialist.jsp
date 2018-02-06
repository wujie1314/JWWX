<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <base href="<%=basePath%>">
    
    <title>专家评论</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
	<meta name="apple-mobile-web-app-capable" content="yes">	
	<link rel="stylesheet" type="text/css" href="bbs/css/mine.css">
    <link rel="stylesheet" type="text/css" href="bootstrap-3.3.5-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="bbs/css/xiala/mescroll.css">
	<link rel="stylesheet" href="bbs/css/xiala/mescroll-option.css">
  </head>
  
  <body>
  	<div class="all">
  	<div class="menu">
		<div class="container">
			<div class="row">
				<ul class="nav">
		  			<li role="presentation" id="expertsReply" style="text-decoration: none;background-color: #eee;width: 33.4%;"><a style="border-bottom: solid 4px #7AA4D6;color: #7AA4D6;">专家解答</a></li>
		  			<li role="presentation" id="trafficInfo"><a onclick="goHighwayCondition()">路况信息</a></li>
		  			<li role="presentation" id="myPostings"><a onclick="goMine()">我的</a></li>
				</ul>
			</div>
		</div>
	</div>
	<p id="downloadTip" class="download-tip">10条新信息</p>
	  	<!--下拉刷新回调的提示-->
	<!--滑动区域-->
	<div id="mescroll" class="mescroll">
  		<div id="allInvitation"></div>
	</div>
	  <div class="postPlus">
			<img src="bbs/images/plus.png" onclick="goAddTell()">
		</div>
  </body>
  	<script src="bbs/js/xiala/mescroll.js" type="text/javascript" charset="utf-8"></script>
	<script src="bbs/js/xiala/mescroll-option.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript" src="js/jquery/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="bbs/js/commonality.js"></script>
	<script type="text/javascript" src="bbs/js/specialist.js"></script>
	<script type="text/javascript" src="bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
</html>
