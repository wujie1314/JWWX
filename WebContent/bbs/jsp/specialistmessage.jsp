<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'specialistmessage.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="bootstrap-3.3.5-dist/css/bootstrap.min.css">
  </head>
  
  <body>
		<textarea class="form-control" id="divText"></textarea>
  		<input type="file" id="file"/>  
  		<div class="imageDiv" id="imageDiv">
  		
  		</div>
  		<button onclick="announce()">发表</button>
  </body>
  	<script type="text/javascript" src="js/jquery/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="bbs/js/dist/lrz.all.bundle.js"></script>
	<script type="text/javascript" src="bbs/js/specialistMessage.js"></script>
</html>