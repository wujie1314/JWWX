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

<div class="banner"><img src="${ctx}/image/meeting/banner_head.jpg?timeStamp=201805031352"/></div>

<div class="content">
	<div width="100%" heigh="50" style="text-align: center;margin: 20px;font-weight:bold;font-size: 100%">请点击图片查看大图</div>
	<img width="100%" id="huodongtu" src="${ctx}/image/meeting/huodongtu.png"/>
	<img width="100%" id="daoshitu" src="${ctx}/image/meeting/daoshitu.png"/>
</div>

</body>
</html>

<script type="text/javascript">
//这个是调用微信图片浏览器的函数 
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
 
//下面这个函数用来转换数组到json格式
function arrayToJson(o) { 
	var r = []; 
	if (typeof o == "string") return "\"" + o.replace(/([\'\"\\])/g, "\\$1").replace(/(\n)/g, "\\n").replace(/(\r)/g, "\\r").replace(/(\t)/g, "\\t") + "\""; 
	if (typeof o == "object") { 
	if (!o.sort) { 
	for (var i in o) 
	r.push(i + ":" + arrayToJson(o[i])); 
	if (!!document.all && !/^\n?function\s*toString\(\)\s*\{\n?\s*\[native code\]\n?\s*\}\n?\s*$/.test(o.toString)) { 
	r.push("toString:" + o.toString.toString()); 
	} 
	r = "{" + r.join() + "}"; 
	} else { 
	for (var i = 0; i < o.length; i++) { 
	r.push(arrayToJson(o[i])); 
	} 
	r = "[" + r.join() + "]"; 
	} 
	return r; 
	} 
	return o.toString(); 
}
(function($){
	//下面是获取当前页面所有的img的src 转成数组 并且 转换成json格式
	var aa=[];
	var i=0;
	var src=[];
	var json=null;
	aa = [$('#huodongtu'), $('#daoshitu')];
	for (i=0;i<aa.length;i++){
		src[i]=aa[i][0].src;    //把所有的src存到数组里
	}
	var srcList=arrayToJson(src); //转换成json并赋值给srcList
	//下面是点击图片的时候获取当前第几个图片并且启用咱们做的调用微信图片浏览器的函数
	$('#huodongtu').click(function(){
		var index = $('#huodongtu').index(this);
		imagePreview(src[index],src);
	});
	
	$('#daoshitu').click(function(){
		var index = $('#daoshitu').index(this);
		imagePreview(src[index],src);
	});
				 
})(jQuery);
</script>
