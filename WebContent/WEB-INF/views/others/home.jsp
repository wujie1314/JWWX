<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>更多功能</title>
	
<link rel="stylesheet" type="text/css" href="/css/others/style.css"> 
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>	
<script type="text/javascript" src="/js/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/js/others/wx_sdk.js"></script>
</head>
  <body>
  		<input type="hidden" id="openID" value=<%=request.getSession().getAttribute("othersOpenId")%> /> 
  		<div class="linkNiv" onclick="Personal()" >
  			<span class ="linkName">私人定制</span>	
  		</div>
  		<div class="linkNiv" onclick="lost_good()" >
  			<span class ="linkName">失物招领</span>	
  		</div>
  		<div class="linkNiv" id="BBS"  onclick="BBS()" >
  			<span class ="linkName">论坛</span>	
  		</div>
  		<div class="linkNiv"  onclick="alertRescue()" >
  			<span class ="linkName">报警救援</span>	
  		</div>
  		<!-- <div class="linkNiv" id="ddd" onclick="getLocationdd()" >
  			<span class ="linkName">SDK获取微信当前位置</span>	
  		</div> -->
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
  		function lost_good(){
  			window.location.href = "http://203.93.109.52:10844/jwwx2/weixin/jsp/goodsList.jsp"; //接入   
  		}
  		function alertRescue(){
  			window.location.href = "/alarmRescue/alarmRescueJSP";
  		}
  </script>
</html>
