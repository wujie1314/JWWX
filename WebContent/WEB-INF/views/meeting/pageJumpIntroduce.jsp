<%@page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta content="telephone=no" name="format-detection">
<title>2018年重庆交通重点项目招商推介会</title>
<link rel="stylesheet" href="${ctx}/css/meeting/pcs.css">
<link rel="stylesheet" href="${ctx}/css/weui-master/dist/style/weui.min.css" />
<link rel="stylesheet" href="${ctx}/css/weui-master/dist/example/example.css" />
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.11.3.min.js"></script>
</head>
<style>

</style>
<body>

<div class="content">
	<img id="imgId" width="100%" src="${ctx}/image/meeting/tuijie/tuijie1.jpg?timestamp=201805130251"/>
</div>
<div style="text-align: center;">
	<span id="lastPage" style="border: 1px solid #D0CFCF;margin: 20px;" onclick="lastPage();" >上一页</span>
	<input id ="pageIndex" value="1" style="width: 40px;font-size: 16px;text-align: right;" type="number">/8&nbsp;页 
	<span onclick="jumpPage()" style="border: 1px solid #D0CFCF;margin: 20px;">跳转</span>
	<span id="nextPage" onclick="nextPage()" style="border: 1px solid #D0CFCF;margin: 20px;">下一页</span></div>
</body>
</html>

<script type="text/javascript">
//这个是调用微信图片浏览器的函数 
var page = 1;
var url = window.location.protocol+"//"+ window.location.host;
function imagePreview(curSrc,srcList) {
	//这个检测是否参数为空
	if(!curSrc || !srcList || srcList.length == 0) {
		return;
	}
	//这个使用了微信浏览器提供的JsAPI 调用微信图片浏览器
	WeixinJSBridge.invoke('imagePreview', { 
		'current' : curSrc,
		'urls' : srcList
	});
};

function nextPage(){
	page++;
	if(page > 8){
		page--;
		return;
	}
	$("#pageIndex").val(page);
	$("#imgId").attr('src',""); 
	$("#imgId").attr('src',url + "/image/meeting/tuijie/tuijie" + page + ".jpg?timestamp=201805120259"); 
}

function lastPage(){
	page--;
	if(page < 1){
		page++;
		return;
	}
	$("#pageIndex").val(page);
	$("#imgId").attr('src',""); 
	$("#imgId").attr('src',url + "/image/meeting/tuijie/tuijie" + page + ".jpg?timestamp=201805120259"); 
}
function jumpPage(){
	page = $("#pageIndex").val();
	if(isNaN(page)){
		page = 1;
	}else{
		if(page < 1){
			page = 1;
		}
		if(page > 8){
			page = 8;
		}
	}
	$("#pageIndex").val(page);
	$("#imgId").attr('src',url + "/image/meeting/tuijie/tuijie" + page + ".jpg?timestamp=201805120259"); 
}

$(function(){
	$("#imgId").on("click", function(){
  	   var nowImgurl = this.src;
  	   var imgs = [nowImgurl];
     	WeixinJSBridge.invoke("imagePreview",{
       "urls":imgs,
       "current":nowImgurl
       })
    });
	
	$("#imgId").on('swiperight', function (e) {
		nextPage();
    });
})
</script>
