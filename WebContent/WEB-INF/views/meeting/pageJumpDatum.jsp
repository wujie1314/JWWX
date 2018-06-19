<%@page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta content="telephone=no" name="format-detection">
<title>2018年重庆市交通委员会运行监测与应急调度系统综合演练观摩</title>
<link rel="stylesheet" href="${ctx}/css/meeting/pcs.css">
<link rel="stylesheet" href="${ctx}/css/weui-master/dist/style/weui.min.css" />
<link rel="stylesheet" href="${ctx}/css/weui-master/dist/example/example.css" />
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.11.3.min.js"></script>
</head>
<body>

<div class="banner"><img src="${ctx}/image/meeting/banner_head1.jpg?timeStamp=20180516001"/></div>

<div class="content">
	<div width="100%" heigh="50" style="text-align: center;margin: 20px;font-size: 150%">雾都宾馆三楼总平面图</div>
	<img style="width: 90%;margin: 5%;" id="huodongtu" src="${ctx}/image/meeting/tuijie/daotu.jpg?timeStamp=20180516001"/>
</div>

</body>
</html>

<script type="text/javascript">
(function($){
	$("#huodongtu").on("click", function(){
	  	   var nowImgurl = this.src;
	  	   var imgs = [nowImgurl];
	     	WeixinJSBridge.invoke("imagePreview",{
	       "urls":imgs,
	       "current":nowImgurl
	       })
	    });
				 
})(jQuery);
</script>
