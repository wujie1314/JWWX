<%@page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="../videoImg/taglibs.jsp"%>
<html>
<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="Keywords">
<meta content="" name="Description">
<title>路况订阅</title>
<style>
body {
	background-color: #fbf9fe;
}

.top_area {
	background-color: #FFFFFF;
	width: 100%;
	height: 40px;
	margin: 5px
}

.top_left {
	text-align: left;
	float: left;
	margin: 0px
}

.top_right {
	text-align: right;
	float: right;
	margin: 0px
}

.content_text_area {
	background-color: #FFFFFF;
	margin-top: 10px;
	width: 100%;
	text-align: center;
	display: block
}

.content_text_table {
	width: 100%;
	text-align: center;
	margin-left: 5px;
	border-collapse: collapse;
}

.content_text_table_tr_td_left {
	text-align: left;
	width: 90%
}

.content_text_table_tr_td_right {
	text-align: right;
	width: 10%
}

.content_text_table_tr_td_right_img {
	margin-right: 10px;
	width: 200px;
	height: 150px
}
</style>

</head>
<body style="margin: 0px;">
	<div class="top_area">
		<div class="top_left">
			<a href="${ctx}/videoImg/list?openId=${openId }&type=0"
				class="weui_btn weui_btn_mini weui_btn_primary"
				style="margin-top: 8px; margin-right: 8px">我的订阅</a>
		</div>
		<div class="top_right">
			<a href="${ctx }/subscribe/details?subsId=${subsId}"
				class="weui_btn weui_btn_mini weui_btn_primary"
				style="margin-top: 8px; margin-right: 8px">刷新</a>
		</div>
	</div>
	<c:forEach items="${videImgList}" var="vide" varStatus="i">
		<div class="content_text_area" id="textDiv">
			<table class="content_text_table">
				<tr>
					<td class="content_text_table_tr_td_left" style="font-size: 18px">${vide.videShowName }</td>
				</tr>
				<tr>
					<td class="content_text_table_tr_td_left" style="font-size: 16px">
						<a
						href="/videoImg/details?videId=${vide.videId }&type=${vide.videType}&openId=${vide.openId}">
							<img src="${ctx}${vide.videUrl }"
							class="content_text_table_tr_td_right_img" />
					</a>
					</td>
				</tr>
				<tr>
					<td class="content_text_table_tr_td_left" style="font-size: 12px">${dateTime }</td>
				</tr>

			</table>
		</div>
	</c:forEach>

</body>
<script type="text/javascript">

</script>
</html>
