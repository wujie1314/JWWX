<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户登录</title>
    
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
        float: left;
    }
    .logo{
    	width: 100%;
    	height: 20%;
    	margin-top: 33%;
    	margin-left: 5%;
    }
    .logo_1{
    	float:left;
    }
    .logo_2{
    	float:left;
    }

    .rightPart{
        width: 50%;
        height: 500px;
        float: left;
    }
    .inputForm{
    	width: 75%;
        height: 200px;
        margin-top: 28%;
    }
    .btn-default{
        float: left;
    }
    .form-control {
      margin-bottom: 8%;
	  display: block;
	  /*width: 100%;*/
	  height: 40px;
	  padding: 10px 130px;
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
	  padding: 8px 204px;
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
	.toPhoneNum{
		float: left;
	    font-size: 13px;
	    color: #23527c;
	    margin-top: 5px;
	    margin-left: 41%;
	}
	

	</style>
	<div class ="leftPart">
		<div class="logo">
			<div class="logo_1">
				<img src="/image/loginLogo_1.png" width="267" height="120"/>
			</div>
			<div class="logo_2">
				<img src="/image/loginLogo_2.png" width="330" height="120"/>
			</div>
		
		</div>
	
	</div>
	
	<div class="rightPart">
	    <div class="inputForm">
	    	<h3 style="margin-bottom: 6%;width:150px;float:left;">登录账号</h3>
	    	<!-- <p class="toPhoneNum">切换到手机登录</p> -->
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
			  url:'/loginVerify',
			  method: 'POST',
			  data:parame,
			  success:function(o){
				  var result = o;
				  switch (result) {
					case "-9":alert("用户名不能为空"); //userError
						window.location.href = "/skip/userError";
						break;
					case "-6":alert("密码不能为空"); //deptError
						window.location.href = "/skip/dataError";
						break; 
					case "1": //dataError
						window.location.href = "/login?userName="+parame.ACCOUNT;
						break; 
					case "-2":alert("用户登录失败"); //dataError
						window.location.href = "/skip/login";
						break; 
				  }
			  }
			});
		}
    
    </script>
</html>
