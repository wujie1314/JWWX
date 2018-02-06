<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>websocket 测试页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<button onclick="websocketTest()">点</button>
  	
  </body>
<script type="text/javascript" src="/js/websocket/swfobject.js"></script>
<script type="text/javascript" src="/js/websocket/web_socket.js"></script>
 <script type="text/javascript">

function websocketTest(){
	alert(host);
	var  tmp = basePath +"/chatSocket";
	tmp = tmp.replace("http","ws");
	var ws= new WebSocket(encodeURI(tmp));
	
	alert(tmp);
	
	ws.onopen = function() {
			alert("open");
	};
	ws.onerror = function(e){
		alert(e);
	};
}

WEB_SOCKET_DEBUG = true;
WEB_SOCKET_SWF_LOCATION = "/js/websocket/WebSocketMain.swf";
WEB_SOCKET_SUPPRESS_CROSS_DOMAIN_SWF_ERROR = true;
var host = window.location.host.split(":")[0];
var basePath = '<%=basePath%>';
/* basePath+"/crossdomain.xml" */
/* "xmlsocket://" + host + ":10844" */
	 try {
		WebSocket.loadFlashPolicyFile("xmlsocket://" + host + ":10844");
	} catch (e) {
	} 
 </script>
</html>
