<%@page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta content="telephone=no" name="format-detection">
<title>2016年重庆“互联网+”便捷交通</title>
<link rel="stylesheet" href="${ctx}/css/meeting/pcs.css">
<link rel="stylesheet" href="${ctx}/css/weui-master/dist/style/weui.min.css" />
<link rel="stylesheet" href="${ctx}/css/weui-master/dist/example/example.css" />
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.11.3.min.js"></script>
</head>
<body>
<div class="banner"><img src="${ctx}/image/meeting/banner_head.jpg"/></div>
<div class="content">
	<div style="width: 58%;height: 284px; margin:28px auto;text-align: center;">

	    <input id="mobile" name="mobile" style="width:100%; height:45px;border:1px; font-size: inherit; margin:28px auto;" placeholder="请输入报名回执电话号码"/>
	    
		<a href="javascript:;" class="weui_btn weui_btn_primary" style="margin:28px auto;" onclick="onVerify();">确认</a>
	</div>
	
</div>

</body>
</html>

<script type="text/javascript">
function onVerify(){
	var mobile = $("#mobile").val();
	var  openId = '${openId}';
	$.post("${ctx}/meeting/mobileVerify",
		    {"mobile":mobile,"openId":openId},
		    function(data) {
		    	//刷新列表 
		    	var content = eval('(' + data + ')');
		    	if("0"==content.code){
		    		location.href="${ctx}/meeting/home?openId="+openId;
		    	} else {
		    		alert("电话号码输入错误，请重新输入！");
		    	}
		    });
	
}
</script>
