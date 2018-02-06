<%@page language="java" contentType="text/html;charset=UTF-8"%>
<%@include file="taglibs.jsp"%>
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
	<div style="width: 100%; height: 30px;">
		<div style="margin: 5px; width: 49%; float: left;">
			<a class="button_a" href="#" onclick="history.go(-1);return false;">返回</a>
		</div>
		<div style="margin: 5px; float: right; text-align: right;">
			<a class="button_a" href="#" onclick="history.go(0)">刷新</a>
		</div>
	</div>
	<!-- ${ctx}${vide.videUrl } -->
	<c:if test="${videoList== null || fn:length(videoList) == 0}">
	<div>
		<img style="vertical-align: middle; height: 150px; width: 100%;"
			src="/image/cqjt_error.jpg">
	</div>
	</c:if>
	<c:forEach items="${videoList}" var="vide" varStatus="i">
		<div style="margin-top: 5px; margin-bottom: 5px; width: 100%;">
			<div
				style="margin-top: 5px; position: relative; height: 180px; width: 49%; float: left;">
				<div
					style="margin: 1px; position: relative; height: 150px; width: 100%; float: left;">
					<a
						href="/videoImg/details?videId=${vide.videId }&type=${type}&openId=${openId}">
						<img src="${ctx}${vide.videUrl }"
						style="position: absolute; z-index: 1; height: 150px; width: 100%;">
						<div
							style="background: #000000; position: absolute; z-index: 2; height: 30px; bottom: 0px; width: 100%; filter: alpha(Opacity = 80); -moz-opacity: 0.5; opacity: 0.5;">
							<div style="margin: 3px; color: #FFFFFF">${vide.videShowName }</div>
						</div> <c:if test="${vide.isHintImg == 1}">
							<img alt="" src="${ctx }${vide.hintImgUrl}?v=1"
								style="height: 150px; width: 100%; position: absolute; z-index: 1;">
						</c:if>
					</a>
				</div>
				<div
					style="margin-left: 1px; height: 30px; border-bottom: 1px solid #CCCCCC; border-left: 1px solid #CCCCCC; border-right: 1px solid #CCCCCC; width: 100%; float: left;">
					<c:if test="${not empty vide.openId }">
						<span style="float: left; margin: 3px;"> <c:choose>
								<c:when test="${vide.isCollect == 0}">
									<a href="#"
										onclick="certainCollent('${vide.videId}','${vide.openId }')">
										<img style="vertical-align: middle; height: 25px;"
										src="/image/shoucang_no.jpg">
									</a>
								</c:when>
								<c:otherwise>
									<a href="#"
										onclick="cancelCollent('${vide.videId}','${vide.openId }')">
										<img style="vertical-align: middle; height: 25px;"
										src="/image/shoucang_yes.jpg">
									</a>
								</c:otherwise>
							</c:choose> ${vide.collectNum }
						</span>
					</c:if>
					<!-- 
					<span style="float: right; margin: 3px;">
						<img style="vertical-align: middle; height: 25px;" src="/image/tuisong_time.jpg">
					</span>
					 -->
				</div>
			</div>


			<c:if test="${ !empty  vide.videId1}">
				<div
					style="margin-top: 5px; position: relative; height: 180px; width: 49%; float: right;">
					<div
						style="margin: 1px; position: relative; height: 150px; width: 100%; float: right;">
						<a
							href="/videoImg/details?videId=${vide.videId1 }&type=${type}&openId=${openId}">
							<img src="${ctx}${vide.videUrl1 }"
							style="position: absolute; z-index: 1; height: 150px; width: 100%;">
							<div
								style="background: #000000; position: absolute; z-index: 2; height: 30px; bottom: 0px; width: 100%; filter: alpha(Opacity = 80); -moz-opacity: 0.5; opacity: 0.5;">
								<div style="margin: 3px; color: #FFFFFF">${vide.videShowName1 }</div>
							</div> <c:if test="${vide.isHintImg1 == 1}">
								<img alt="" src="${ctx }${vide.hintImgUrl1}?v=1"
									style="height: 150px; width: 100%; position: absolute; z-index: 1;">
							</c:if>
						</a>
					</div>
					<div
						style="margin-left: 1px; height: 30px; border-bottom: 1px solid #CCCCCC; border-left: 1px solid #CCCCCC; border-right: 1px solid #CCCCCC; width: 100%; float: right;">
						<c:if test="${not empty vide.openId }">
							<c:choose>
								<c:when test="${vide.isCollect1 == 0}">
									<a href="#"
										onclick="certainCollent('${vide.videId1}','${vide.openId1 }')">
										<img style="vertical-align: middle; height: 25px;"
										src="/image/shoucang_no.jpg">
									</a>
								</c:when>
								<c:otherwise>
									<a href="#"
										onclick="cancelCollent('${vide.videId1}','${vide.openId1 }')">
										<img style="vertical-align: middle; height: 25px;"
										src="/image/shoucang_yes.jpg">
									</a>
								</c:otherwise>
							</c:choose>
							${vide.collectNum1 }
							</span>
						</c:if>
						<!-- 
							<span style="float: right; margin: 3px;">
							<img style="vertical-align: middle; height: 25px;" src="/image/tuisong_time.jpg">
							</span>
							 -->
					</div>
				</div>
			</c:if>
		</div>
	</c:forEach>
	<div>
		<img style="vertical-align: middle; height: 150px; width: 100%;"
			src="/image/cqjt.jpg">
	</div>
</body>

<script type="text/javascript">
$(function(){
	$("title").html("${parentName}"); 
});

function certainCollent(videId,openId){
	$.post("${ctx}/videoImg/insertCollent",
	    {"videId":videId,"openId":openId},
	    function(data) {
	    	//刷新列表 
	    	var content = eval('(' + data + ')');
	    	if("0"==content.code){
	    		document.location.reload();
	    		//location.href ="${ctx}/parcel/editInfoPage.htm?parcState=0";
	    	} else {
	    		alert(content.msg);
	    	}
	    });
}

function cancelCollent(videId,openId){
	$.post("${ctx}/videoImg/delCollent",
		    {"videId":videId,"openId":openId},
		    function(data) {
		    	//刷新列表 
		    	var content = eval('(' + data + ')');
		    	if("0"==content.code){
		    		document.location.reload();
		    		//location.href ="${ctx}/parcel/editInfoPage.htm?parcState=0";
		    	} else {
		    		alert(content.msg);
		    	}
		    });
}

</script>
</html>
