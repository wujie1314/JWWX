<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String type = request.getParameter("type");
%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="type" value="<%=type %>"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
<style type="text/css">
body {
	font-family: "微软雅黑";
	background-color: #EFEFEF;
	max-width: 750px;
	margin: 0 auto;
	height: 100%;
	font-size: 15px;
	overflow-y:hidden;
}
.container{
	width: 100%;
	background: #fff;
	position: relative;
}
.top_bar{
	width:100%;
	height: 3.0em;
	background-color: #222d65;
	z-index:999;
	position:absolute;
}
.top_bar .back_img{
	position:absolute;
	margin: 12px 0px 0px 5px;
	width: 20px;
}
.top_bar .title{
	color:#fff; 
	width:100%;
	height: 3.0em;
	line-height: 3.0em;
	text-align:center;
}
.content{
	width:100%;
	position:absolute;
}
.content .iframe{
	border:0px; 
	width: 100%;
	height:800px;
	overflow-y:hidden;
}

</style>
<title>渝中区</title>
</head>
<body>
	<div class="container">
		<div class="top_bar">
			<img onclick="onBack('${type}')" class="back_img" src="${ctx}/image/park/back.png">
			<div id="title" class="title">渝中区</div>
		</div>
		<div class="content" id="content">
			
		</div>
	</div>
</body>
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
$(function(){
	var t = '${type}';
	if(t == 1){//观音桥商圈
		$("#title").html("观音桥商圈");
		$("#content").html("<iframe class=\"iframe\" src=\"http://weixin.cqyzga.gov.cn//Traffic/CarPorts?regionid=jiangbei&code=041EypIg2NepqE06exIg2r4sIg2EypI1&state=1&winzoom=1\"></iframe>");
	} else if(t==2){//杨家坪商圈
		$("#title").html("杨家坪商圈");
		$("#content").html("<iframe class=\"iframe\" src=\"http://weixin.cqyzga.gov.cn//Traffic/CarPorts?regionid=yangjiaping&code=021NErd82zpnZN0Tlwf82Cugd82NErdx&state=1&winzoom=1\"></iframe>");
	} else if(t==3){//大渡口商圈
		$("#title").html("大渡口商圈");
		$("#content").html("<iframe class=\"iframe\" src=\"http://weixin.cqyzga.gov.cn//Traffic/CarPorts?regionid=dadukou&code=021KrkA60nuFpI1btSA60uIwA60KrkAn&state=1&winzoom=1\"></iframe>");
	} else if(t==4){//红崖洞
		$("#title").html("红崖洞");
		$("#content").html("<iframe class=\"iframe\" src=\"http://weixin.cqyzga.gov.cn//Traffic/CarPorts?regionid=jiefangbei&subregion=hongyandong&code=011gxBYb0mg1dw1kVhXb0HTyYb0gxBYj&state=1&winzoom=1\"></iframe>");
	} else if(t==5){//临江门
		$("#title").html("临江门");
		$("#content").html("<iframe class=\"iframe\" src=\"http://weixin.cqyzga.gov.cn//Traffic/CarPorts?regionid=jiefangbei&subregion=linjiangmen&code=041TTt5p0hJT2r1nOy3p0YZm5p0TTt5I&state=1&winzoom=1\"></iframe>");
	} else if(t==6){//较场口
		$("#title").html("较场口");
		$("#content").html("<iframe class=\"iframe\" src=\"http://weixin.cqyzga.gov.cn//Traffic/CarPorts?regionid=jiefangbei&subregion=jiaochangkou&code=021dFfSw04cyMf1H6rSw0jeeSw0dFfS5&state=1&winzoom=1\"></iframe>");
	} else if(t==7){//七星岗
		$("#title").html("七星岗");
		$("#content").html("<iframe class=\"iframe\" src=\"http://weixin.cqyzga.gov.cn//Traffic/CarPorts?regionid=jiefangbei&subregion=qixingang&code=001FFIhg2T28RE0u6Xfg2WHUhg2FFIh5&state=1&winzoom=1\"></iframe>");
	} else if(t==8){//小什字
		$("#title").html("小什字");
		$("#content").html("<iframe class=\"iframe\" src=\"http://weixin.cqyzga.gov.cn//Traffic/CarPorts?regionid=jiefangbei&subregion=xiaoshenzi&code=0412R6vS02UsBU1TF1xS0aDdvS02R6vX&state=1&winzoom=1\"></iframe>");
	}
});
function onBack(type){
	if(type == 4 || type ==5 || type == 6 || type == 7 || type == 8){
		location.href ="${ctx}/parkNextInfo.jsp";	
	} else {
		location.href ="${ctx}/parkInfo.jsp";	
	}
}
function onDetails(type){
	location.href ="${ctx}/parkDetailInfo.jsp?type="+type;
}
</script>
</html>