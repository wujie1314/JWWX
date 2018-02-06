<%@page language="java" contentType="text/html;charset=UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=0">
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
}

.top_left {
	width: 49%;
	float: left;
	margin: 5px
}

.top_right {
	text-align: right;
	float: right;
	margin: 0px
}

.top_right_img {
	width: 30px;
	height: 30px
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
	width: 20px;
	height: 20px
}
</style>
</head>
<body style="margin: 0px;">
	<div class="top_area">
		<div class="top_left">
			我的订阅<font size="2px" color="red">(先收藏后订阅)</font>
		</div>
		<div class="top_right" style="margin-top: 5px; margin-right: 5px">
			<a href="${ctx }/subscribe/initAdd?openId=${openId }"
				class="weui_btn weui_btn_mini weui_btn_primary">视频截图</a>
		</div>
		<div class="top_right" style="margin-top: 5px; margin-right: 5px">
			<a href="${ctx }/csc/getRoadLxfd?openId=${openId }"
				class="weui_btn weui_btn_mini weui_btn_primary">高速路况</a>
		</div>
	</div>
	<c:forEach items="${subscribeList}" var="subs" varStatus="i">
		<c:if test="${subs.subsTitleName == '文字' }">
			<a href="${ctx }/subscribe/initEditText?id=${subs.subsId }">
				<div class="content_text_area" id="textDiv">
					<table class="content_text_table">
						<tr>
							<td class="content_text_table_tr_td_left" style="font-size: 16px">${subs.subsCharacter }</td>
							<td class="content_text_table_tr_td_right" rowspan="3"><a
								href="#" onclick="isDel(${subs.subsId })"> <img
									src="${ctx }/image/delete.png"
									class="content_text_table_tr_td_right_img" />
							</a></td>
						</tr>
						<tr>
							<td class="content_text_table_tr_td_left" style="font-size: 20px">${subs.subsRemindHour }
								: ${subs.subsRemindMinute }</td>
						</tr>
						<tr>
							<td class="content_text_table_tr_td_left" style="font-size: 14px">${subs.subsRemindType == 1 ? '' : subs.subsRemindDate }
								${subs.subsRemindWeekStr }</td>
						</tr>
					</table>
				</div>
			</a>
		</c:if>
		<c:if test="${subs.subsTitleName == '图片' }">
			<a href="${ctx }/subscribe/initEdit?id=${subs.subsId }">
				<div class="content_text_area" id="textDiv">
					<table class="content_text_table">
						<tr>
							<td class="content_text_table_tr_td_left" style="font-size: 16px">${subs.subsTitleName }</td>
							<td class="content_text_table_tr_td_right" rowspan="3"><a
								href="#" onclick="isDel(${subs.subsId })"> <img
									src="${ctx }/image/delete.png"
									class="content_text_table_tr_td_right_img" />
							</a></td>
						</tr>
						<tr>
							<td class="content_text_table_tr_td_left" style="font-size: 20px">${subs.subsRemindHour }
								: ${subs.subsRemindMinute }</td>
						</tr>
						<tr>
							<td class="content_text_table_tr_td_left" style="font-size: 14px">${subs.subsRemindType == 1 ? '' : subs.subsRemindDate }
								${subs.subsRemindWeekStr }</td>
						</tr>
					</table>
				</div>
			</a>
		</c:if>
	</c:forEach>

</body>

<script type="text/javascript">
	var id = '';
	function isDel(subsId){
		id = subsId;
		//$("#weui_dialog").width("40%");
		$("#isDelDialog").css("display", "block");
	}
	function delOk(){
		$.post("${ctx}/subscribe/del",
	    	    {"id":id
	    		},
	    	    function(data) {
	    	    	if("0"==data.code){
	    	        	document.location.href="${ctx}/videoImg/list?openId=${openId}&type=0";
	    	    	} else {
	    	    		$("#isDelDialog").css("display", "none");
	    	    		errorMsg(data.msg);
	    	    	}
	    		}
	    	);
		
	}
	function delNo(){
		$("#isDelDialog").css("display", "none");
	}
	function errorMsg(msg){
		$("#errorMsg").html(msg);
		//$("#weui_dialog_error").width("40%");
		$("#errorDialog").css("display", "block");
	}
	function offErrorDialog(){
		$("#errorDialog").css("display", "none");
	}
</script>
</html>
