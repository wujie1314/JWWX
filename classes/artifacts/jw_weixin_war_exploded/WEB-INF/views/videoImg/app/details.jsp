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
	<div
		style="margin-top: 5px; margin-bottom: 5px; width: 100%; height: 250px; position: relative; float: left;">
		<img alt="" src="${ctx }${videoImg.videUrl}" height="250px;"
			width="100%; " style="position: absolute">
		<!-- <div style="margin:3px;color:#FFFFFF background:#000000;position:absolute;z-index:2; height:22px;bottom:20px;width:100%;text-align: right;right: 10px;">${videoImg.videShowName }</div> -->
		<c:if test="${videoImg.isHintImg == 1}">
			<img alt="" id="hintImg" src="${ctx }${videoImg.hintImgUrl}?v=1"
				style="height: 250px;; width: 100%; position: absolute; z-index: 1; display: block;">
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
				$("#hintCheckbox").attr("checked",false);
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
