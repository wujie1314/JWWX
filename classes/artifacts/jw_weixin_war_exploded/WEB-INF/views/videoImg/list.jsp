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
/**
a:hover {
	background-color: #039702;
	color:#FFFFFF;
}
**/
</style>
</head>
<body style="margin: 0px;">
	<div style="margin-top: 10px; width: 100%;">
		<table style="width: 100%; text-align: center;">
			<tr id="tab-title">
				<td id="title-sc" width="20%" onclick="changeTab('tab-sc');"
					style="cursor: pointer;"><b>订阅</b></td>
				<td id="title-rd" width="20%" onclick="changeTab('tab-rd');"
					style="cursor: pointer;"><b>城区</b></td>
				<td id="title-gs" width="20%" onclick="changeTab('tab-gs');"
					style="cursor: pointer;"><b>高速</b></td>
				<td id="title-zd" width="20%" onclick="changeTab('tab-zd');"
					style="cursor: pointer;"><b>车站</b></td>
				<td id="title-yj" width="20%" onclick="changeTab('tab-yj');"
					style="cursor: pointer;"><b>路况</b></td>
			</tr>
			<tr height="5px" id="tab-all">
				<td id="tab-sc"></td>
				<td id="tab-rd"></td>
				<td id="tab-gs"></td>
				<td id="tab-zd"></td>
				<td id="tab-yj"></td>
			</tr>
		</table>
	</div>
	<c:if test="${type == 0 }">
		<%@include file="../roadSubscribe/roadList.jsp"%>
		<div class="top_area" style="margin-top: 5px">
			<div class="top_left">我的收藏</div>
		</div>
	</c:if>
	<!-- 
		<div style="margin: 0px; height:46px;float: left; width: 100%; background: #F5F5F5; text-align: center;">
			<div style="float:left;margin-top:5px;margin-bottom:5px;text-align:right;width:80%;"><input type="text" name="keyword" id="keyword" value="${keyword}" style="width:90%;line-height:32px;height:36px;vertical-align:middle;" /></div>
			<div style="float:right;margin-top:5px;margin-bottom:5px;text-align:left;width:20%;"><a href="#" class="weui_btn weui_btn_mini weui_btn_primary" style="margin-top:4px;margin-left:5px" onclick="searchVideo();">搜索</a></div>
		</div>
		 -->
	<c:forEach items="${videoList}" var="vide" varStatus="i">
		<div style="margin-top: 5px; width: 100%;">
			<div style="width: 49%; float: left;">
				<div
					style="width: 100%; margin: 1px; position: relative; height: 150px;">
					<c:choose>
						<c:when test="${type == 2}">
							<a
								href="/videoImg/nextList?parentId=${vide.videId }&type=${type}&parentName=${vide.videShowName }&openId=${openId}">
						</c:when>
						<c:when test="${type == 3}">
							<a
								href="/videoImg/nextList?parentId=${vide.videId }&type=${type}&parentName=${vide.videShowName }&openId=${openId}">
						</c:when>
						<c:otherwise>
							<a
								href="/videoImg/details?videId=${vide.videId }&openId=${openId}&type=${vide.videType}">
						</c:otherwise>
					</c:choose>
					<img src="${ctx}${vide.videUrl}"
						style="position: absolute; z-index: 1; height: 150px; width: 100%;">
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

				<c:if test="${not empty vide.openId and (type == 0 or type == 4)}">
					<div
						style="height: 30px; border-bottom: 1px solid #CCCCCC; border-left: 1px solid #CCCCCC; border-right: 1px solid #CCCCCC; width: 100%;">
						<span style="float: left; margin: 3px;"> <c:choose>
								<c:when test="${vide.isCollect == 0}">
									<a href="javascript:void(0);"
										onclick="certainCollent('${vide.videId}','${vide.openId }')">
										<img style="vertical-align: middle; height: 25px;"
										src="/image/shoucang_no.jpg">
									</a>
								</c:when>
								<c:otherwise>
									<a href="javascript:void(0);"
										onclick="cancelCollent('${vide.videId}','${vide.openId }')">
										<img style="vertical-align: middle; height: 25px;"
										src="/image/shoucang_yes.jpg">
									</a>
								</c:otherwise>
							</c:choose> ${vide.collectNum }
						</span>
					</div>
				</c:if>
			</div>

			<c:if test="${ !empty  vide.videId1}">
				<div style="width: 49%; float: right;">
					<div
						style="width: 100%; margin: 1px; position: relative; height: 150px;">
						<c:choose>
							<c:when test="${type == 2}">
								<a
									href="/videoImg/nextList?parentId=${vide.videId1 }&type=${type}&parentName=${vide.videShowName1 }&openId=${openId}">
							</c:when>
							<c:when test="${type == 3}">
								<a
									href="/videoImg/nextList?parentId=${vide.videId1 }&type=${type}&parentName=${vide.videShowName1 }&openId=${openId}">
							</c:when>
							<c:otherwise>
								<a
									href="/videoImg/details?videId=${vide.videId1 }&openId=${openId}&type=${vide.videType1}">
							</c:otherwise>
						</c:choose>
						<img src="${ctx}${vide.videUrl1 }"
							style="position: absolute; z-index: 1; height: 150px; width: 100%;">

						<c:choose>
							<c:when test="${type == 2}">
								<div
									style="background: #000000; position: absolute; z-index: 2; height: 24px; bottom: 20px; width: 100%; filter: alpha(Opacity = 80); -moz-opacity: 0.5; opacity: 0.7;">
									<div style="margin: 3px; color: #FFFFFF">${vide.videShowName1 }</div>
								</div>
								<div
									style="background: #000000; position: absolute; z-index: 2; height: 20px; bottom: 0px; width: 100%; filter: alpha(Opacity = 80); -moz-opacity: 0.5; opacity: 0.7;">
									<div style="margin: 0px; color: #FFFFFF; font-size: 12px">${vide.videName1 }</div>
								</div>
							</c:when>
							<c:otherwise>
								<div
									style="background: #000000; position: absolute; z-index: 2; height: 30px; bottom: 0px; width: 100%; filter: alpha(Opacity = 80); -moz-opacity: 0.5; opacity: 0.5;">
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

					<c:if test="${not empty vide.openId1 and (type == 0 or type == 4)}">
						<div
							style="height: 30px; border-bottom: 1px solid #CCCCCC; border-left: 1px solid #CCCCCC; border-right: 1px solid #CCCCCC; width: 100%;">
							<span style="float: left; margin: 3px;"> <c:choose>
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
								</c:choose> ${vide.collectNum1 }
							</span>
						</div>
					</c:if>

				</div>
			</c:if>

		</div>
	</c:forEach>

	<div>
		<img style="vertical-align: middle; height: 150px; width: 100%;"
			src="/image/cqjt.jpg">
	</div>

	<div class="weui_dialog_confirm" id="isDelDialog"
		style="display: none;">
		<div class="weui_mask"></div>
		<div class="weui_dialog" id="weui_dialog">
			<div class="weui_dialog_hd">
				<strong class="weui_dialog_title">确定删除？</strong>
			</div>
			<div class="weui_dialog_ft">
				<a href="javascript:;" class="weui_btn_dialog default"
					onclick="delNo()">取消</a> <a href="javascript:;"
					class="weui_btn_dialog primary" onclick="delOk()">确定</a>
			</div>
		</div>
	</div>
	<div class="weui_dialog_alert" id="errorDialog" style="display: none;">
		<div class="weui_mask"></div>
		<div class="weui_dialog" id="weui_dialog_error">
			<div class="weui_dialog_hd">
				<strong class="weui_dialog_title">错误提示</strong>
			</div>
			<div class="weui_dialog_bd" id="errorMsg"></div>
			<div class="weui_dialog_ft">
				<a href="javascript:;" class="weui_btn_dialog primary"
					onclick="offErrorDialog()">确定</a>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function(){
		var _tmp = $("#tab-all").children();
		$.each(_tmp, function() {
			$(this).css("background", "#FFFFFF");
		});
		var _tmp2 = $("#title-all").children();
		$.each(_tmp2, function() {
			$(this).find("b:first").css("color", "#FFFFFF");
		});
		var type = '${type}';
		if(0 == type){
			$("#tab-sc").css("background", "#039702");
			$("#title-sc").find("b:first").css("color", "#039702");
		} else if(1 == type){
			$("#tab-yj").css("background", "#039702");
			$("#title-yj").find("b:first").css("color", "#039702");
		} else if(2 == type){
			$("#tab-gs").css("background", "#039702");
			$("#title-gs").find("b:first").css("color", "#039702");
		} else if(3 == type){
			$("#tab-zd").css("background", "#039702");
			$("#title-zd").find("b:first").css("color", "#039702");
		} else if(4 == type){
			$("#tab-rd").css("background", "#039702");
			$("#title-rd").find("b:first").css("color", "#039702");
		} else {
			$("#tab-gs").css("background", "#039702");
			$("#title-gs").find("b:first").css("color", "#039702");
		}
	});

	function searchVideo(){
		var type = '${type}';
		var keyword = $("#keyword").val();
		keyword = encodeURI(encodeURI(keyword));
		location.href ="${ctx}/videoImg/list?openId=${openId}&type="+type+"&keyword="+keyword;
	}
	
	function changeTab(id) {
		var type = 0;
		if("tab-sc" == id){
			type = 0;
		} else if("tab-rd" == id){
			type = 4;
		} else if("tab-gs" == id){
			type = 2;
		} else if("tab-zd" == id){
			type = 3;
		} else if("tab-yj" == id){
			type = 1;
			location.href ="${ctx}/roadNews.jsp?openId=${openId}";
			return;
		}
		location.href ="${ctx}/videoImg/list?openId=${openId}&type="+type;
	}
	
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
