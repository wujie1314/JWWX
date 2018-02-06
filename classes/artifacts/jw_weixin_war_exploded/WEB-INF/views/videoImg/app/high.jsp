<%@page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="taglibs.jsp"%>
<html>
<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="Keywords">
<meta content="" name="Description">
<title>高速截图</title>
<style type="text/css">

</style>
</head>
<body style="margin: 0px;">
	<div style="width: 100%; position: relative; height: 30px">
		<div style="margin: 5px; width: 100%; float: left;">
			<a class="button_a" style="width:22%" href="#" onclick="goBack();return false;">返回</a>
		</div>
	</div>
	<c:forEach items="${videoList}" var="vide" varStatus="i">
		<div style="margin-top: 5px;margin-bottom: 5px; width: 100%;">
			<div style="width: 49%; float: left;">
				<div style="width: 100%; margin: 1px; position: relative; height: 150px;">
					<a href="${ctx}/videoImg/nextListApp?parentId=${vide.videId }&type=${type}&parentName=${vide.videShowName }">
						<img src="${ctx}${vide.videUrl}" style="position: absolute; z-index: 1; height: 150px; width: 100%;">
						<div style="background: #000000; position: absolute; z-index: 2; height: 24px; bottom: 20px; width: 100%; filter: alpha(Opacity = 80); -moz-opacity: 0.5; opacity: 0.7;">
							<div style="margin: 3px; color: #FFFFFF">${vide.videShowName }</div>
						</div>
						<div style="background: #000000; position: absolute; z-index: 2; height: 20px; bottom: 0px; width: 100%; filter: alpha(Opacity = 80); -moz-opacity: 0.5; opacity: 0.7;">
							<div style="margin: 0px; color: #FFFFFF; font-size: 12px">${vide.videName }</div>
						</div>
					</a>
				</div>
			</div>

			<c:if test="${ !empty  vide.videId1}">
				<div style="width: 49%; float: right;">
					<div style="width: 100%; margin: 1px; position: relative; height: 150px;">
						<a href="${ctx}/videoImg/nextListApp?parentId=${vide.videId1 }&type=${type}&parentName=${vide.videShowName1 }">
						
							<img src="${ctx}${vide.videUrl1 }" style="position: absolute; z-index: 1; height: 150px; width: 100%;">
							<div style="background: #000000; position: absolute; z-index: 2; height: 24px; bottom: 20px; width: 100%; filter: alpha(Opacity = 80); -moz-opacity: 0.5; opacity: 0.7;">
								<div style="margin: 3px; color: #FFFFFF">${vide.videShowName1 }</div>
							</div>
							<div style="background: #000000; position: absolute; z-index: 2; height: 20px; bottom: 0px; width: 100%; filter: alpha(Opacity = 80); -moz-opacity: 0.5; opacity: 0.7;">
								<div style="margin: 0px; color: #FFFFFF; font-size: 12px">${vide.videName1 }</div>
							</div>
						</a>
					</div>
				</div>
			</c:if>

		</div>
	</c:forEach>
	
</body>
<script type="text/javascript">
	function goBack(){
		window.history.back();
	}

</script>
</html>
