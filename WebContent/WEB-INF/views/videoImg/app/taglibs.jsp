<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.11.3.min.js"></script>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<style type="text/css">
body {
	font-family: "Microsoft YaHei", 微软雅黑, "MicrosoftJhengHei", 华文细黑, STHeiti, MingLiu
}
.button_a {
	display: block;
	width: 45%;
	padding: 2px;
	height: 30px;
	Line-height: 30px;
	border-radius: 5px;
	Background-color: #263584;
	border:0px;
	Color: #FFFFFF;
	Text-decoration: none;
	Text-align: center;
}
</style>
<script type="text/javascript">

</script>
