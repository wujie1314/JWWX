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
	
<link rel="stylesheet" type="text/css" href="/css/others/style.css"> 
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>	
<script type="text/javascript" src="/js/jquery/jquery-1.7.2.min.js"></script>

<script type="text/javascript" src="/js/others/wx_sdk.js"></script>
</head>
  <body>
  	<label>openId</label>
  		<input type="text" id="openID" value=<%=request.getSession().getAttribute("othersOpenId")%> />
  		<button  class = "btn btn_primary" onclick="Personal()" >私人定制</button>
  		<button class = "btn btn_primary" >失物招领</button>
		<button class = "btn btn_primary"  id="BBS" onclick="BBS()" >论坛</button>
  		<button  class = "btn btn_primary" id="ddd" onclick="getLocationdd()">SDK获取微信当前位置</button>
  </body>
  <script type="text/javascript">
  		function BBS(){
  			var openID = $("#openID").val();
  			window.location.href = "bbs/jsp/mine.jsp?openID=" + openID;  
  		}
  		function Personal(){
  			var openID = $("#openID").val();
  			window.location.href = "/psDesign/getRoadName?openID=" + openID;  
  		}
  </script>
</html>
