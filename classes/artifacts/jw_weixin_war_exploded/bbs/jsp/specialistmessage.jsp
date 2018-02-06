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
	<link rel="stylesheet" type="text/css" href="bootstrap-3.3.5-dist/css/bootstrap.min.css">
  </head>
	<body>
		标题<input type="text" class="form-control" style="width: 200px;" id="title">
		内容<textarea class="form-control" rows="3" style="width: 200px;" id="content"></textarea>
		<input type="button"  value="添加附件" onclick="createInput('more');"/>  
		<div id="more"></div> 
		<button onclick="ajaxFileUploadImg()" value="上传">上传</button>
		<script type="text/javascript" src="js/jquery/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="bbs/js/fileUpload/ajaxFileUpload.js"></script>		
		<script type="text/javascript" src="bbs/js/specialistMessage.js"></script>	
	</body>
</html>
