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
        float: left;
    }
    .inputForm{
    	width: 50%;
        height: 200px;
        margin-top: 30%;
    }
    .btn-default{
        float: left;
    }
    .form-control {
      margin-bottom: 5%;
	  display: block;
	  /*width: 100%;*/
	  height: 34px;
	  padding: 6px 50px;
	  font-size: 14px;
	  line-height: 1.42857143;
	  color: #555;
	  background-color: #fff;
	  background-image: none;
	  border: 1px solid #ccc;
	  border-radius: 4px;
	  -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	          box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	  -webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow ease-in-out .15s;
	       -o-transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
	          transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
	}
	.btn{
	  color: #fff;
	  background-color: #23527c;
	  border-color: #ccc;
	  display: inline-block;
	  padding: 5px 124px;
	  margin-bottom: 0;
	  font-size: 14px;
	  font-weight: normal;
	  line-height: 1.42857143;
	  text-align: center;
	  white-space: nowrap;
	  vertical-align: middle;
	  -ms-touch-action: manipulation;
	      touch-action: manipulation;
	  cursor: pointer;
	  -webkit-user-select: none;
	     -moz-user-select: none;
	      -ms-user-select: none;
	          user-select: none;
	  background-image: none;
	  border: 1px solid transparent;
	  border-radius: 4px;
	}

	</style>
	<div class ="leftPart">
	
	</div>
	
	<div class="rightPart">
	    <div class="inputForm">
	    	<h4 style="margin-bottom: 5%;">登录账号</h4>
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