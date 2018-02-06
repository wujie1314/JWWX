<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${ctx}/css/weui-master/dist/style/weui.min.css" />
<link rel="stylesheet" href="${ctx}/css/weui-master/dist/example/example.css" />
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.11.3.min.js"></script>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<style type="text/css">
body {
	font-family: "Microsoft YaHei", 微软雅黑, "MicrosoftJhengHei", 华文细黑, STHeiti,
		MingLiu
}

.button_a {
	Display: block;
	Width: 60px;
	Padding: 2px;
	Line-height: 30px;
	Background-color: #04BE02;
	Border: 1px solid #04AB02;
	Color: #FFFFFF;
	Text-decoration: none;
	Text-align: center;
}
</style>

<script type="text/javascript">
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
	function imagePreview(curSrc,srcList) {
					if(!curSrc || !srcList || srcList.length == 0) {
						return;
					}
					WeixinJSBridge.invoke('imagePreview', { 
						'current' : curSrc,
						'urls' : srcList
					});
	};
	 
	(function($){
			var aa=[];
			var i=0;
			var src=[];
			var json=null;
			aa=$('img');
			for (i=0;i<aa.length;i++){
				src[i]=aa[i].src;  
			}
			var srcList=arrayToJson(src); 
			$('img').click(function(){
						var index = $('img').index(this);
						imagePreview(srcList[index],srcList);
					});
					 
	})(jQuery);
	
</script>
