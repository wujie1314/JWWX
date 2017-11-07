<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>其他页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript" src="js/jquery/jquery-1.7.2.min.js"></script>
  </head>
  
  <body>
  	<label>openId</label>
  	<input type="text" id="openID" value=<%=request.getSession().getAttribute("othersOpenId")%> />
  	<a>私人定制</a>
  	<a>失物招领</a>
  	<a id="BBS" onclick="BBS()">论坛</a>
  </body>
  
  <script type="text/javascript">
  		function BBS(){
  			var openID = $("#openID").val();
  			window.location.href = "bbs/jsp/mine.jsp?openID=" + openID;  
  		}
  </script>
</html>
