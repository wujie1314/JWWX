<%@page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="taglibs.jsp"%>
<html>
<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="Keywords">
<meta content="" name="Description">
<title>视频截图</title>
<style type="text/css">

</style>
</head>
<body style="margin: 0px;">
	<div style="margin-top: 10px; width: 100%;">
		<table style="width: 100%; text-align: center;">
			<tr id="tab-title">
				<td id="title-video" width="20%" onclick="changeTab('tab-video');"
					style="cursor: pointer;-webkit-tap-highlight-color:rgba(0,0,0,0);"><b>城区</b></td>
				<td id="title-gs" width="20%" onclick="changeTab('tab-gs');"
					style="cursor: pointer;-webkit-tap-highlight-color:rgba(0,0,0,0);"><b>高速</b></td>
				<td id="title-zd" width="20%" onclick="changeTab('tab-zd');"
					style="cursor: pointer;-webkit-tap-highlight-color:rgba(0,0,0,0);"><b>车站</b></td>
			</tr>
			<tr height="5px" id="tab-all">
				<td id="tab-video"></td>
				<td id="tab-gs"></td>
				<td id="tab-zd"></td>
			</tr>
		</table>
	</div>
	<c:choose>
		<c:when test="${type == -1}">
			<iframe id="video_div_iframe" src="http://dubudu.wxcitycq.com:18080/pubsrv/dubuduapp/TC.do" style="border:0px; width: 100%; overflow-y:hidden;height:100%"></iframe>
		</c:when>
		<c:otherwise>
			<div id="other_div">
				<c:forEach items="${videoList}" var="vide" varStatus="i">
				<div style="margin-top: 5px; width: 100%;">
					<div style="width: 49%; float: left;">
						<div style="width: 100%; margin: 1px; position: relative; height: 150px;">
							<c:choose>
								<c:when test="${type == 2}">
									<a href="${ctx}/videoImg/nextListApp?parentId=${vide.videId }&type=${type}&parentName=${vide.videShowName }&openId=${openId}">
								</c:when>
								<c:when test="${type == 3}">
									<a href="${ctx}/videoImg/nextListApp?parentId=${vide.videId }&type=${type}&parentName=${vide.videShowName }&openId=${openId}">
								</c:when>
								<c:otherwise>
									<a href="${ctx}/videoImg/detailsApp?videId=${vide.videId }&openId=${openId}&type=${vide.videType}">
								</c:otherwise>
							</c:choose>
							<img src="${ctx}${vide.videUrl}" style="position: absolute; z-index: 1; height: 150px; width: 100%;">
							<c:choose>
								<c:when test="${type == 2}">
									<div
										style="background: #000000; position: absolute; z-index: 2; height: 24px; bottom: 20px; width: 100%; filter: alpha(Opacity = 80); -moz-opacity: 0.5; opacity: 0.7;">
										<div style="margin: 3px; color: #FFFFFF">${vide.videShowName }</div>
									</div>
									<div
										style="background: #000000; position: absolute; z-index: 2; height: 20px; bottom: 0px; width: 100%; filter: alpha(Opacity = 80); -moz-opacity: 0.5; opacity: 0.7;">
										<div style="margin: 0px; color: #FFFFFF; font-size: 12px">${vide.videName }</div>
									</div>
								</c:when>
								<c:otherwise>
									<div
										style="background: #000000; position: absolute; z-index: 2; height: 30px; bottom: 0px; width: 100%; filter: alpha(Opacity = 80); -moz-opacity: 0.5; opacity: 0.5;">
										<div style="margin: 3px; color: #FFFFFF">${vide.videShowName }</div>
									</div>
								</c:otherwise>
							</c:choose>
		
							<c:if test="${ vide.isHintImg == 1}">
								<img alt="" src="${ctx }${vide.hintImgUrl}"
									style="height: 150px; width: 100%; position: absolute; z-index: 3;">
							</c:if>
							</a>
						</div>
					</div>
		
					<c:if test="${ !empty  vide.videId1}">
						<div style="width: 49%; float: right;">
							<div
								style="width: 100%; margin: 1px; position: relative; height: 150px;">
								<c:choose>
									<c:when test="${type == 2}">
										<a href="${ctx}/videoImg/nextListApp?parentId=${vide.videId1 }&type=${type}&parentName=${vide.videShowName1 }&openId=${openId}">
									</c:when>
									<c:when test="${type == 3}">
										<a href="${ctx}/videoImg/nextListApp?parentId=${vide.videId1 }&type=${type}&parentName=${vide.videShowName1 }&openId=${openId}">
									</c:when>
									<c:otherwise>
										<a href="${ctx}/videoImg/detailsApp?videId=${vide.videId1 }&openId=${openId}&type=${vide.videType1}">
									</c:otherwise>
								</c:choose>
								<img src="${ctx}${vide.videUrl1 }" style="position: absolute; z-index: 1; height: 150px; width: 100%;">
		
								<c:choose>
									<c:when test="${type == 2}">
										<div style="background: #000000; position: absolute; z-index: 2; height: 24px; bottom: 20px; width: 100%; filter: alpha(Opacity = 80); -moz-opacity: 0.5; opacity: 0.7;">
											<div style="margin: 3px; color: #FFFFFF">${vide.videShowName1 }</div>
										</div>
										<div style="background: #000000; position: absolute; z-index: 2; height: 20px; bottom: 0px; width: 100%; filter: alpha(Opacity = 80); -moz-opacity: 0.5; opacity: 0.7;">
											<div style="margin: 0px; color: #FFFFFF; font-size: 12px">${vide.videName1 }</div>
										</div>
									</c:when>
									<c:otherwise>
										<div style="background: #000000; position: absolute; z-index: 2; height: 30px; bottom: 0px; width: 100%; filter: alpha(Opacity = 80); -moz-opacity: 0.5; opacity: 0.5;">
											<div style="margin: 3px; color: #FFFFFF">${vide.videShowName1 }</div>
										</div>
									</c:otherwise>
								</c:choose>
								<c:if test="${vide.isHintImg1 == 1}">
									<img alt="" src="${ctx }${vide.hintImgUrl1}"
										style="height: 150px; width: 100%; position: absolute; z-index: 3;">
								</c:if>
								</a>
							</div>
						</div>
					</c:if>
		
				</div>
				</c:forEach>
			</div>
		</c:otherwise>
	</c:choose>

</body>
<script type="text/javascript">
	$(function(){
		var _tmp = $("#tab-all").children();
		$.each(_tmp, function() {
			$(this).css("background", "#dcdcdc");
		});
		var _tmp2 = $("#title-all").children();
		$.each(_tmp2, function() {
			$(this).find("b:first").css("color", "#dcdcdc");
		});
		var type = '${type}';
		if(2 == type){
			$("#tab-gs").css("background", "#263584");
			$("#title-gs").find("b:first").css("color", "#263584");
		} else if(3 == type){
			$("#tab-zd").css("background", "#263584");
			$("#title-zd").find("b:first").css("color", "#263584");
		} else if(4 == type){
			$("#tab-rd").css("background", "#263584");
			$("#title-rd").find("b:first").css("color", "#263584");
		} else if(-1 == type){
			$("#tab-video").css("background", "#263584");
			$("#title-video").find("b:first").css("color", "#263584");
		} else {
			$("#tab-gs").css("background", "#263584");
			$("#title-gs").find("b:first").css("color", "#263584");
		}
	});

	function searchVideo(){
		var type = '${type}';
		var keyword = $("#keyword").val();
		keyword = encodeURI(encodeURI(keyword));
		location.href ="${ctx}/videoImg/listapp?type="+type+"&keyword="+keyword;
	}
	
	function videoDisplyShow(){
		$("#video_div").show();
		$("#other_div").hide();
	}
	function videoDisplyHide(){
		$("#video_div").hide();
		$("#other_div").show();
	}
	
	function changeTab(id) {
		var type = 2;
		if("tab-rd" == id){
			type = 4;
		} else if("tab-gs" == id){
			type = 2;
		} else if("tab-zd" == id){
			type = 3;
		} else if("tab-video"==id){
			type = -1;
		}
		
		location.href ="${ctx}/videoImg/listapp?type="+type;
		/**
		if(type == -1){
			var _tmp = $("#tab-all").children();
			$.each(_tmp, function() {
				$(this).css("background", "#dcdcdc");
			});
			var _tmp2 = $("#tab-title").children();
			$.each(_tmp2, function() {
				$(this).find("b:first").css("color", "#000000");
			});
			$("#tab-video").css("background", "#263584");
			$("#title-video").find("b:first").css("color", "#263584");
			videoDisplyShow();
		} else {
			location.href ="${ctx}/videoImg/listapp?type="+type;
		}
		**/
	}
	

</script>
</html>
