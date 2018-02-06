<%@page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="taglibs.jsp"%>
<html>
<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="Keywords">
<meta content="" name="Description">
<title>${parendName }</title>
<style type="text/css">

</style>
</head>
<body style="margin: 0px;">
	<div style="width: 100%; position: relative; height: 30px">
		<div style="margin: 5px; width: 45%; float: left;">
			<a class="button_a" href="#" onclick="history.go(-1);return false;">返回</a>
		</div>
		<div style="margin: 5px;width: 45%; float: right; text-align: right;">
			<a class="button_a" style="float: right;" href="#" onclick="history.go(0)">刷新</a>
		</div>
	</div>
	<!-- ${ctx}${vide.videUrl } -->
	<c:forEach items="${videoList}" var="vide" varStatus="i">
		<div style="margin-top: 5px; margin-bottom: 5px; width: 100%;">
			<div style="margin-top: 5px; position: relative; height: 180px; width: 49%; float: left;">
				<a href="${ctx }/videoImg/detailsApp?videId=${vide.videId }&type=${type}&openId=${openId}">
					<img src="${ctx}${vide.videUrl }" style="position: absolute; z-index: 1; height: 150px; width: 100%;">
					<div style="background: #000000; position: absolute; z-index: 2; height: 30px; bottom: 0px; width: 100%; filter: alpha(Opacity = 80); -moz-opacity: 0.5; opacity: 0.5;">
						<div style="margin: 3px; color: #FFFFFF">${vide.videShowName }</div>
					</div> 
					<c:if test="${vide.isHintImg == 1}">
						<img alt="" src="${ctx }${vide.hintImgUrl}?v=1" style="height: 150px; width: 100%; position: absolute; z-index: 1;">
					</c:if>
				</a>
			</div>


			<c:if test="${ !empty  vide.videId1}">
				<div style="margin-top: 5px; position: relative; height: 180px; width: 49%; float: right;">
					<a href="${ctx }/videoImg/detailsApp?videId=${vide.videId1 }&type=${type}&openId=${openId}">
						<img src="${ctx}${vide.videUrl1 }" style="position: absolute; z-index: 1; height: 150px; width: 100%;">
						<div style="background: #000000; position: absolute; z-index: 2; height: 30px; bottom: 0px; width: 100%; filter: alpha(Opacity = 80); -moz-opacity: 0.5; opacity: 0.5;">
							<div style="margin: 3px; color: #FFFFFF">${vide.videShowName1 }</div>
						</div> 
						<c:if test="${vide.isHintImg1 == 1}">
							<img alt="" src="${ctx }${vide.hintImgUrl1}?v=1" style="height: 150px; width: 100%; position: absolute; z-index: 1;">
						</c:if>
					</a>
				</div>
			</c:if>
		</div>
	</c:forEach>
</body>

<script type="text/javascript">
$(function(){
	$("title").html("${parentName}"); 
});


</script>
</html>
