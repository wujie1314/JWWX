<%@page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="taglibs.jsp"%>
<html>
<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="Keywords">
<meta content="" name="Description">
<title>${videoImg.videShowName }</title>
<style type="text/css">
a:hover {
	background-color: #039702;
	color: #FFFFFF;
}
</style>
</head>
<body style="margin: 0px;">
	<div style="width: 100%; position: relative; height: 30px">
		<div style="margin: 5px; width: 49%; float: left;">
			<a class="button_a" href="#" onclick="history.go(-1);return false;">返回</a>
		</div>
		<div style="margin: 5px; float: right; text-align: right;">
			<a class="button_a" href="#" onclick="history.go(0)">刷新</a>
		</div>
	</div>

	<c:set var="divHight" value="250px"/>
	<c:if test="${not empty videoImg.videUrl && not empty videoImg.shortVideUrl}">
		<c:set var="divHight" value="500px"/>
	</c:if>
	<div style="margin-top: 5px; margin-bottom: 5px; width: 100%; height: ${divHight}; position: relative; float: left;">
		<c:if test="${not empty videoImg.videUrl}">
			<img alt="" src="${ctx }${videoImg.videUrl}" height="250px;"
				width="100%; " style="position: absolute">
			<!-- <div style="margin:3px;color:#FFFFFF background:#000000;position:absolute;z-index:2; height:22px;bottom:20px;width:100%;text-align: right;right: 10px;">${videoImg.videShowName }</div> -->
			<c:if test="${videoImg.isHintImg == 1}">
				<img alt="" id="hintImg" src="${ctx }${videoImg.hintImgUrl}?v=1"
					style="height: 250px;; width: 100%; position: absolute; z-index: 1; display: block;">
			</c:if>
		</c:if>
		<c:if test="${not empty videoImg.shortVideUrl}">
			<video style="height: 250px; width: 100%; position: absolute; top: 250px;" >
				<source src="${ctx }${videoImg.shortVideUrl}}" type="video/mp4" />
				Your browser does not support the video tag.
			</video>
		</c:if>
		<c:if test="${empty videoImg.videUrl && empty videoImg.shortVideUrl}">
			<img alt="" src="${ctx }/image/default_pic.jpg" height="250px;"
				 width="100%; " style="position: absolute">
		</c:if>
	</div>
	<div
		style="margin-bottom: 5px; margin-top: 5px; height: 10px; width: 100%;">&nbsp;</div>
	<div
		style="margin-bottom: 5px; margin-top: 5px; width: 100%; background: #F5F5F5;">
		<div style="width: 100%; background: #F5F5F5;">
			<div style="margin: 1px; width: 49%; float: left; height: 30px;">
				<c:if test="${videoImg.isHintImg == 1}">
					<input type="checkbox" id="hintCheckbox" checked="checked"
						onclick="hintDisplay()">
					<a href="#" onclick="hintDisplayA()">显示方向 </a>
					</input>
				</c:if>
			</div>
			<div
				style="margin: 1px; width: 49%; float: right; text-align: right; height: 30px;">
				<div class="bdsharebuttonbox" data-tag="share_1"
					style="float: right; width: 118px;">
					<a class="bds_tsina" data-cmd="tsina"></a>&nbsp;&nbsp; <a
						class="bds_qzone" data-cmd="qzone" href="#"></a>&nbsp;&nbsp; <a
						class="bds_renren" data-cmd="renren"></a>
				</div>
				<!-- <img style="vertical-align: middle; height: 30px;"
					src="/image/tuisong_no.jpg"><img
					style="vertical-align: middle; height: 30px;"
					src="/image/shoucang_yes.jpg"><img
					style="vertical-align: middle; height: 30px;"
					src="/image/shoucang_yes.jpg"> -->
			</div>

			<div
				style="width: 100%; height: 150px; background: #DEDEDE; margin-bottom: 15px; border: 1px solid #CCCCCC">
				<img style="vertical-align: middle; height: 150px; width: 100%;"
					src="/image/cqjt.jpg">
			</div>
		</div>
	</div>
	<script>
		window._bd_share_config = {
			common : {
				bdText : '${videoImg.videShowName }',
				bdDesc : '${videoImg.videShowName }',
				bdUrl : window.location.href,
				bdPic : '${ctx}${videoImg.videUrl}'
			},
			share : [ {
				"bdSize" : 24
			} ]
		}
		var bds_config = {'snsKey':{'weixin':'您的新浪微博的appkey'}};
		with (document)
			0[(getElementsByTagName('head')[0] || body)
					.appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?cdnversion='
					+ ~(-new Date() / 36e5)];
		
		function hintDisplay(){
			if($("#hintCheckbox").prop("checked")){
				$("#hintImg").css("display", "block");
			} else {
				$("#hintImg").css("display", "none");
			}
		}
		function hintDisplayA(){
			if($("#hintCheckbox").prop("checked")){
				$("#hintCheckbox").prop("checked",false);
				$("#hintImg").css("display", "none");
				//if($(this).is(':checked'))$(this).click();
			} else {
				$("#hintCheckbox").prop("checked",true);
				//if($(this).is(':checked'))$(this).click();
				$("#hintImg").css("display", "block");
			}
		}
	</script>
</body>
</html>
