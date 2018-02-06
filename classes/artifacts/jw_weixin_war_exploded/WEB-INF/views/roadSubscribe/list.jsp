<%@page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="../videoImg/taglibs.jsp"%>
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
	margin: 5px;
	font-size: 20px
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
		<div class="top_left">路况订阅</div>
		<div class="top_right">
			<a href="${ctx }/subscribe/initAdd?openId=${openId }" title="新增">
				<img src="${ctx }/image/add-img.png" class="top_right_img" />
			</a>
		</div>
	</div>
	<c:forEach items="${subscribeList}" var="subs" varStatus="i">
		<a href="${ctx }/subscribe/initEdit?id=${subs.subsId }">
			<div class="content_text_area" id="textDiv">
				<table class="content_text_table">
					<tr>
						<td class="content_text_table_tr_td_left" style="font-size: 16px">${subs.subsTitleName }</td>
						<td class="content_text_table_tr_td_right" rowspan="3"><a
							href="#" onclick="isDel(${subs.subsId })"> <img
								src="${ctx }/image/delete.png"
								class="content_text_table_tr_td_right_img" /></td>
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
	</c:forEach>
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
	var id = '';
	function isDel(subsId){
		id = subsId;
		$("#weui_dialog").width("30%");
		$("#isDelDialog").css("display", "block");
	}
	function delOk(){
		$.post("${ctx}/subscribe/del",
	    	    {"id":id
	    		},
	    	    function(data) {
	    	    	if("0"==data.code){
	    	        	document.location.href="${ctx}/subscribe/list?openId=${openId}";
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
		$("#weui_dialog_error").width("30%");
		$("#errorDialog").css("display", "block");
	}
	function offErrorDialog(){
		$("#errorDialog").css("display", "none");
	}
</script>
</html>
