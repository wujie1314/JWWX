<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

    <script type="text/javascript" src="js/jquery/jquery-1.7.2.min.js"></script>
  </head>
  
  <body>
    <style type="text/css">
    *{
        margin:0;
        padding:0;
    }
    body{
        width: 100%;
        height: 500px;
        display:inline;
    }
    .leftPart{
        width: 50%;
        height: 500px;
        background-color: #d58512;
        float: left;
    }

    .rightPart{
        width: 50%;
        height: 500px;
        background-color: #23527c;
        float: left;
    }
    .form-control{
        float: left;
    }
    .btn-default{
        float: left;
    }
	</style>
	<div class ="leftPart">
	
	</div>
	
	<div class="rightPart">
	    <div class="inputForm">
	        <input type="text" class="form-control" id="ACCOUNT" placeholder="请输入账号">
	        <input type="password" class="form-control" id="PASSWORD" placeholder="请输入密码">
	        <button type="submit" class="btn btn-default" id="confirmLogin " onclick="enter()">登录</button>
	    </div>
	
	</div>

  </body>
  <script>
    	function enter() {
			var parame = {};
			parame.ACCOUNT = $('#ACCOUNT').val();
			parame.PASSWORD = $('#PASSWORD').val();
			
			$.ajax({
			  url:'login',
			  data:parame,
			  success:function(o){
				 /*  var result = eval("(" + o + ")");
				  switch (result) {
					case "1":
						window.location.href = "index.jsp";
						break;
					case "-1":
						alert("用户名或密码错误");
						break;
					default:
						break; 
				  }*/
			  }
			});
		}
    
    </script>
</html>
