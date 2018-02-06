<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.11.3.min.js"></script>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<style type="text/css">
</style>

<script type="text/javascript">
	
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

function initImagePreview(){
	 //下面是获取当前页面所有的img的src 转成数组 并且 转换成json格式
	var aa=[];
	var i=0;
	var src=[];
	var json=null;
	aa=$('.wx-switch-img');
	for (i=0;i<aa.length;i++){
		src[i]=aa[i].src;    //把所有的src存到数组里
	}
	var srcList=arrayToJson(src); //转换成json并赋值给srcList
	//下面是点击图片的时候获取当前第几个图片并且启用咱们做的调用微信图片浏览器的函数
	$('.wx-switch-img').click(function(){
		var index = $('.wx-switch-img').index(this);
		imagePreview(srcList[index],srcList);
	});
}
</script>
