<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>交通论坛</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
   	<div class="menu">
		<div class="container">
			<div class="row">
				<ul class="nav">
		  			<li role="presentation" id="expertsReply" class="col-xs-4"><a href="expertReply.html">专家解答</a></li>
		  			<li role="presentation" id="trafficInfo" class="col-xs-4"><a href="trafficInformation.html">路况信息</a></li>
		  			<li role="presentation" id="myPostings" class="col-xs-4"><a href="Mine.html">我的</a></li>
				</ul>
			</div>
		</div>
	</div>

	<div class="showInfo">
		<div class="container">
			<div class="row">
				<div class="personInfo">
					<div id="photo"></div>
					<div class="nameAndTime">
						<div id="userName"></div>
						<div id="postTime"></div>
					</div>
				</div>
			</div>

			<div class="row">

				<div class="passage_content">
					<p>存放正文内容（路况信息页面）</p>
				</div>

				<div class="passage_picture">
					<div class="picture col-xs-4 col-sm-4"></div>
					<div class="picture col-xs-4 col-sm-4"></div>
					<div class="picture col-xs-4 col-sm-4"></div>
				</div>

				<div class="existedComment">
					<p>评论信息</p>
				</div>
				
				<div class="comment">
					<input type="text" value="发表评论">
				</div>
			</div>
		</div>
	</div>
		<div class="menu">
		<div class="container">
			<div class="row">
				<ul class="nav">
		  			<li role="presentation" id="expertsReply" class="col-xs-4"><a href="expertReply.html">专家解答</a></li>
		  			<li role="presentation" id="trafficInfo" class="col-xs-4"><a href="trafficInformation.html">路况信息</a></li>
		  			<li role="presentation" id="myPostings" class="col-xs-4"><a href="Mine.html">我的</a></li>
				</ul>
			</div>
		</div>
	</div>

	<div class="showInfo">
		<div class="container">
			<div class="row">
				<div class="personInfo">
					<div id="photo"></div>
					<div class="nameAndTime">
						<div id="userName"></div>
						<div id="postTime"></div>
					</div>
				</div>
			</div>

			<div class="row">

				<div class="passage_content">
					<p>存放正文内容(我的页面)</p>
				</div>

				<div class="passage_picture">
					<div class="picture col-xs-4 col-sm-4"></div>
					<div class="picture col-xs-4 col-sm-4"></div>
					<div class="picture col-xs-4 col-sm-4"></div>
				</div>

				<div class="existedComment">
					<p>评论信息</p>
				</div>
			</div>
		</div>
	</div>
	<div class="postPlus">
		<img src="bbs/img/plus.png" onclick="window.location.href='/bbs/jsp/writeAbout.jsp'">
	</div>
  </body>
  	<link rel="stylesheet" type="text/css" href="bbs/css/forum.css">
	<link rel="stylesheet" type="text/css" href="bootstrap-3.3.5-dist/css/bootstrap.min.css">

	<script type="text/javascript" src="js/jquery/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="bbs/js/forum.js"></script>
	<script type="text/javascript" src="bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
</html>
