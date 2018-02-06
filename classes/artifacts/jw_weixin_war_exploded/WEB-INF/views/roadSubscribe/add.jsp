<%@page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="../videoImg/taglibs.jsp"%>
<html>
<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="Keywords">
<meta content="" name="Description">
<title>路况订阅-视频截图</title>
<link
	href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="http://netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.min.css"
	rel="stylesheet">
<link href="/js/bootstrap/bootstrap-spinner.css" rel="stylesheet">
<script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<script src="/js/bootstrap/jquery.spinner.min.js"></script>
<style>
body {
	background-color: #fbf9fe;
}

.button_area {
	background-color: #FFFFFF;
	width: 100%;
	height: 40px;
}

.button_left {
	width: 49%;
	float: left;
	margin: 5px
}

.button_right {
	text-align: right;
	float: right;
	margin: 5px
}

.date_area {
	margin-top: 10px;
	background-color: #FFFFFF;
	width: 100%;
	height: 40px;
}

.hour_area {
	margin-top: 10px;
	width: 100%;
	height: 44px;
	text-align: center
}

.hour_left {
	width: 50px;
	height: 50px;
	text-align: right;
	font-size: 28px
}

.hour_center {
	font-size: 24px
}

.hour_right {
	width: 50px;
	height: 50px;
	font-size: 28px
}

.repeat_area {
	background-color: #FFFFFF;
	margin-top: 5px;
	width: 100%;
	height: 40px;
}

.repeat_left {
	float: left;
	width: 49%;
	margin: 5px
}

.repeat_right {
	float: right;
	margin: 5px
}

.content_text_area {
	background-color: #FFFFFF;
	margin-top: 10px;
	width: 100%;
	text-align: center;
	display: block
}

.content_text_table {
	width: 90%;
	text-align: center;
	margin: auto;
	border-collapse: collapse;
}

.content_text_table_tr {
	border-bottom: 1px solid #039702;
}

.content_text_table_tr_td_left {
	text-align: left;
	width: 90%
}

.content_text_table_tr_td_right {
	text-align: right;
	width: 10%
}

.content_img_area_error_msg {
	background-color: #FFFFFF;
	margin-top: 10px;
	margin-bottom: 10px;
	width: 100%;
	text-align: center;
	display: none
}

.content_img_area {
	background-color: #FFFFFF;
	margin-top: 10px;
	margin-bottom: 10px;
	width: 100%;
	display: none
}

.content_img_area_left {
	float: left;
	width: 48%;
	height: 150px;
	margin-bottom: 5px;
	margin-left: 2px;
	margin-right: 2px
}

.content_img_area_right {
	float: right;
	width: 48%;
	height: 150px;
	margin-bottom: 5px;
	margin-left: 2px;
	margin-right: 2px
}

.content_img_area_div {
	position: absolute;
	width: 49%;
	height: 150px;
}

.content_img_area_div_img {
	height: 150px;
	width: 100%;
	z-index: 1
}

.content_img_area_div_reverse {
	background: #000000;
	position: absolute;
	z-index: 2;
	height: 30px;
	bottom: 0px;
	width: 100%;
	filter: alpha(Opacity = 80);
	-moz-opacity: 0.5;
	opacity: 0.5;
}

.content_img_area_div_img_title {
	margin: 3px;
	color: #FFFFFF;
	float: left;
	width: 100%;
	position: absolute
}

.content_img_area_div_img_select {
	margin-right: 8px;
	margin-top: 4px;
	position: absolute;
	z-index: 2;
	width: 100%;
}
</style>
</head>
<body style="margin: 0px;">
	<div class="button_area">
		<div class="button_left">
			<a href="${ctx}/videoImg/list?openId=${openId }&type=0"
				class="weui_btn weui_btn_mini weui_btn_primary">返回</a>
		</div>
		<div class="button_right">
			<a href="#" class="weui_btn weui_btn_mini weui_btn_primary"
				onclick="addRoad();">保存</a>
		</div>
	</div>
	<!-- 
	<div class="date_area">
		<div class="button_left"> 
		  2016-03-04
		</div>
		<div class="button_right">
			<a href="#" class="weui_btn weui_btn_mini weui_btn_primary">日期</a>
		</div>
	</div>
-->
	<div class="date_area">
		<div class="weui_cell">
			<div class="weui_cell_hd">
				<label for="" class="weui_label">日期</label>
			</div>
			<div class="weui_cell_bd weui_cell_primary">
				<input class="weui_input" type="date"
					style="line-height: 1.41176471;" value="${road.subsRemindDate }"
					name="subsRemindDate" id="subsRemindDate" />
			</div>
		</div>
	</div>
	<div class="date_area">
		<div class="weui_cell">
			<div class="weui_cell_hd">
				<label for="" class="weui_label">时间</label>
			</div>
			<div class="weui_cell_bd weui_cell_primary">
				<input class="weui_input" type="time"
					style="line-height: 1.41176471;"
					value="${road.subsRemindTimeHour }" name="subsRemindTimeHour"
					id="subsRemindTimeHour" />
			</div>
		</div>
	</div>
	<!-- 
	<div class="hour_area">
	<div style="position: relative;text-align: center;">
	    <div style="float:left;width:15%;">&nbsp;</div>
	    <div class="input-group spinner" data-trigger="spinner" style="float:left;width:30%;">
	       	<div class="input-group-addon" id="spinner">
	           	<a href="javascript:;" class="spin-up" data-spin="up"><i class="icon-sort-up"></i></a>
	           	<a href="javascript:;" class="spin-down" data-spin="down"><i class="icon-sort-down"></i></a>
	       	</div>
	       	<input type="text" class="form-control" name="subsRemindHour"  id="subsRemindHour" style="line-height:50px;height:56px;font-size:24px;" value="${road.subsRemindHour == null ? 10 : road.subsRemindHour}" data-max="23" data-min="0" data-step="1">
	    </div>
	    <div style="float:left;">
	    	<img style="vertical-align:middle;height:56px;margin-left:15px;margin-right:15px;" id="voiceOk" src='/image/maohao.png'>
	    </div>
	    <div class="input-group spinner" data-trigger="spinner" style="float:left;width:30%;">
			<input type="text" class="form-control" name="subsRemindMinute" id="subsRemindMinute" value="${road.subsRemindMinute == null ? 10 : road.subsRemindMinute }" style="line-height:50px;height:56px;font-size:24px;" data-max="59" data-min="0" data-step="1">
	       	<div class="input-group-addon">
	           	<a href="javascript:;" class="spin-up" data-spin="up"><i class="icon-sort-up"></i></a>
	           	<a href="javascript:;" class="spin-down" data-spin="down"><i class="icon-sort-down"></i></a>
	       	</div>
	    </div>
    </div>
    </div>
     -->
	<div class="weui_cells weui_cells_access"
		style="position: relative; width: 100%;">
		<a class="weui_cell" href="javascript:;" onclick="showWeekDialog()">
			<div class="weui_cell_bd weui_cell_primary">
				<p>每周重复</p>
				<!-- 周日，周一，周二，周三，周四，周五，周六 -->
				<div id="week" style="font-size: 12px;"></div>
			</div>
			<div class="weui_cell_ft" id="isRepeat">从不</div>
		</a>
	</div>

	<!-- 
	<div style="margin-top: 10px; width: 100%;">
		<table style="width: 100%; text-align: center;">
			<tr id="tab-title">
				<td id="title-text" width="20%" onclick="changeTab('tab-text');" style="cursor: pointer;"><b>文字</b></td>
				<td id="title-img" width="20%" onclick="changeTab('tab-img');" style="cursor: pointer;"><b>图片</b></td>
			</tr>
			<tr height="5px" id="tab-all">
				<td id="tab-text"></td>
				<td id="tab-img"></td>
			</tr>
		</table>
	</div>

	<div class="content_text_area" id="textDiv">
		<table class="content_text_table">
			<tr class="content_text_table_tr">
				<td class="content_text_table_tr_td_left">标题</td>
				<td class="content_text_table_tr_td_right">全选<input type="checkbox" value=""/></td>
			</tr>
			<tr class="content_text_table_tr">
				<td class="content_text_table_tr_td_left">G85高速</td>
				<td class="content_text_table_tr_td_right"><input type="checkbox" value=""></input></td>
			</tr>
			<tr class="content_text_table_tr">
				<td class="content_text_table_tr_td_left">G85高速</td>
				<td class="content_text_table_tr_td_right"><input type="checkbox" value=""></input></td>
			</tr>
			<tr class="content_text_table_tr">
				<td class="content_text_table_tr_td_left">G85高速</td>
				<td class="content_text_table_tr_td_right"><input type="checkbox" value=""></input></td>
			</tr>
			<tr class="content_text_table_tr">
				<td class="content_text_table_tr_td_left">G85高速</td>
				<td class="content_text_table_tr_td_right"><input type="checkbox" value=""></input></td>
			</tr>
		</table>
	</div>
	 -->
	<div class="content_img_area" id="imgDiv" style="display: block;">
		<c:if test="${empty  videoList}">
			<div class="content_img_area_error_msg">很抱歉，您现不能订阅，请收藏后，再订阅！</div>
		</c:if>
		<c:forEach items="${videoList}" var="vide" varStatus="i">
			<c:choose>
				<c:when test="${(i.index+1)%2==0 }">
					<div class="content_img_area_right">
				</c:when>
				<c:otherwise>
					<div class="content_img_area_left">
				</c:otherwise>
			</c:choose>
			<div class="content_img_area_div">
				<img src="${ctx}${vide.videUrl}" class="content_img_area_div_img">
				<div class="content_img_area_div_reverse">
					<div class="content_img_area_div_img_title">${vide.videShowName }</div>
					<div class="content_img_area_div_img_select">
						<input type="checkbox" name="imgCheckbox" value="${vide.videId }"
							id="img${vide.videId }" style="float: right; margin-right: 8px"></input>
					</div>
				</div>
			</div>
	</div>
	</c:forEach>
	</div>

	<div>
		<table class="content_text_table">
			<tr>
				<td></td>
				<td></td>
			</tr>
		</table>
	</div>

	<div style="margin-top: 10px; height: 42px">
		<a href="javascript:;" class="weui_btn weui_btn_primary"
			onclick="addRoad();">保存</a>
	</div>

	<div class="weui_dialog_confirm" id="weekDialog" style="display: none;">
		<div class="weui_mask"></div>
		<div class="weui_dialog">
			<div class="weui_dialog_hd">
				<strong class="weui_dialog_title">每周重复</strong>
			</div>
			<div class="weui_dialog_bd">
				<%@include file="weekRepeat.jsp"%>
			</div>
			<div class="weui_dialog_ft">
				<a href="javascript:;" class="weui_btn_dialog default"
					onclick="notShowWeekDialog()">取消</a> <a href="javascript:;"
					class="weui_btn_dialog primary" onclick="confirmIsRepeat()">确定</a>
			</div>
		</div>
	</div>

	<div class="weui_dialog_alert" id="errorDialog" style="display: none;">
		<div class="weui_mask"></div>
		<div class="weui_dialog" id="weui_dialog">
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
var subsRemindWeek = "";


function selectImg(){
	var img = '${road.subsImg}';
	if(img != ""){
		var s = img.split(",");
		 $.each(s,function(n,value) { 
			 $("#img"+value).attr("checked",true);
		 });
	}
}

function selectWeek(){
	if(subsRemindWeek != ""){
		var s = subsRemindWeek.split(",");
		 $.each(s,function(n,value) { 
			 $("#w"+value).attr("checked",true);
		 });
	}
}

function changeTab(id) {
	var type = 0;
	if("tab-text" == id){
		type = 0;
		$("#tab-text").css("background", "#039702");
		$("#tab-img").css("background", "#FFFFFF");
		$("#title-img").find("b:first").css("color", "#039702");
		$("#imgDiv").css("display", "none");
		$("#textDiv").css("display", "block");
	} else {
		type = 1;
		$("#tab-text").css("background", "#FFFFFF");
		$("#tab-img").css("background", "#039702");
		$("#title-text").find("b:first").css("color", "#039702");
		$("#imgDiv").css("display", "block");
		$("#textDiv").css("display", "none");
	}
	//location.href ="${ctx}/videoImg/list?&openId=${openId}&type="+type;
}

function showWeekDialog(){
	$("#weekDialog").css("display", "block");
}
function notShowWeekDialog(){
	$("#weekDialog").css("display", "none");
}


//确定重复
function confirmIsRepeat(){
	notShowWeekDialog();
	subsRemindWeek="";
	var arrChk=$("input[name='checkbox1']:checked");
    $(arrChk).each(function(){
    	subsRemindWeek+=this.value+",";
    }); 
    if(subsRemindWeek != ""){
    	subsRemindWeek = subsRemindWeek.substring(0, subsRemindWeek.length - 1);
    }
    eidtRepeat();
}

function eidtRepeat(){
	//alert("subsRemindWeek:"+subsRemindWeek);
	if(subsRemindWeek == ""){
    	$("#week").html("");
    	$("#isRepeat").html("从不");
    } else {
    	var s = subsRemindWeek.split(",");
    	var week = "";
    	 $.each(s,function(n,value) { 
    		switch(value){
    			case "0":
    				week += "周日，";
    			  	break;
    			case "1":
    				week += "周一，";
    			  	break;
    			case "2":
    				week += "周二，";
    			  	break;
    			case "3":
    				week += "周三，";
    			  	break;
    			case "4":
    				week += "周四，";
    			  	break;
    			case "5":
    				week += "周五，";
    			  	break;
    			case "6":
    				week += "周六，";
    			  	break;
    		}
    	});
    	week = week.substring(0, week.length - 1);
    	$("#week").html(week);
    	$("#isRepeat").html("重复");
    }
}

	function addRoad(){
		var subsId = "${road.subsId}";
		var subsOpenId="${openId}";
		var subsCharacter = "";
		var subsImg = "";
		var subsRemindDate = $("#subsRemindDate").val();
		var subsRemindTimeHour = $("#subsRemindTimeHour").val();
		var subsRemindHour = $("#subsRemindHour").val();
		var subsRemindMinute = $("#subsRemindMinute").val();
		var subsRemindType = 0;
		var subsIsStart = 0;
		var arrChk=$("input[name='imgCheckbox']:checked");
		var subsTitleName = '图片';
	    $(arrChk).each(function(){
	    	subsImg+=this.value+",";
	    }); 
	    if(subsCharacter != '' && subsImg != ''){
	    	subsTitleName = "图片、文字";
	    } else if(subsCharacter != ''){
	    	subsTitleName = "文字";
	    }
	    if(subsRemindDate == ''){
	    	errorMsg("请选择提醒日期!");
			return;
	    }
	    if(subsRemindTimeHour == ''){
	    	errorMsg("请选择提醒时间!");
			return;
	    }

		if(subsCharacter == '' && subsImg == ''){
			errorMsg("请选择文字路况或者图片路况!");
			return;
		}
	    /*
		if(isNaN(subsRemindHour)){
			errorMsg("时间输入错误!");
			return;
		}
		if(isNaN(subsRemindMinute)){
			errorMsg("时间输入错误!");
			return;
		}
		*/
		if(subsRemindWeek != ""){
			subsRemindType = 1;
		}
		
		if(subsId == ""){
			$.post("${ctx}/subscribe/add",
		    	    {"subsOpenId":subsOpenId
		    		,"subsCharacter": subsCharacter
		    		,"subsImg": subsImg
		    		,"subsRemindDate": subsRemindDate
		    		,"subsRemindTimeHour": subsRemindTimeHour
		    		,"subsRemindHour": subsRemindHour
		    		,"subsRemindMinute": subsRemindMinute
		    		,"subsRemindWeek": subsRemindWeek
		    		,"subsRemindType":subsRemindType
		    		,"subsIsStart":subsIsStart
		    		,"subsTitleName":subsTitleName
		    		},
		    	    function(data) {
		    	    	if("0"==data.code){
		    	        	document.location.href="${ctx}/videoImg/list?type=0&openId="+subsOpenId;
		    	    	} else {
		    	    		errorMsg(data.msg);
		    	    	}
		    		}
		    	);
		} else {
			$.post("${ctx}/subscribe/edit",
		    	    {"subsId":subsId
					,"subsOpenId":subsOpenId
		    		,"subsCharacter": subsCharacter
		    		,"subsImg": subsImg
		    		,"subsRemindDate": subsRemindDate
		    		,"subsRemindTimeHour": subsRemindTimeHour
		    		,"subsRemindHour": subsRemindHour
		    		,"subsRemindMinute": subsRemindMinute
		    		,"subsRemindWeek": subsRemindWeek
		    		,"subsRemindType":subsRemindType
		    		,"subsIsStart":subsIsStart
		    		,"subsTitleName":subsTitleName
		    		},
		    	    function(data) {
		    	    	if("0"==data.code){
		    	    		document.location.href="${ctx}/videoImg/list?type=0&openId="+subsOpenId;
		    	    	} else {
		    	    		errorMsg(data.msg);
		    	    	}
		    		}
		    	);
		}
		
	}
	
	function errorMsg(msg){
		$("#errorMsg").html(msg);
		$("#weui_dialog").width("30%");
		$("#errorDialog").css("display", "block");
	}
	function offErrorDialog(){
		$("#errorDialog").css("display", "none");
	}

	$(function(){
		/**
		var _tmp = $("#tab-all").children();
		$.each(_tmp, function() {
			$(this).css("background", "#FFFFFF");
		});
		var _tmp2 = $("#title-all").children();
		$.each(_tmp2, function() {
			$(this).find("b:first").css("color", "#FFFFFF");
		});
		var type = 0;
		if(0 == type){
			$("#tab-text").css("background", "#039702");
			$("#title-img").find("b:first").css("color", "#039702");
		} else {
			$("#tab-text").css("background", "#039702");
			$("#title-img").find("b:first").css("color", "#039702");
		}
		*/
		//初始化数据
		subsRemindWeek = '${road.subsRemindWeek}';
		//初始化每周重复
		eidtRepeat();
		//初始化星期是否选择
		selectWeek();
		//初始化图片
		selectImg();
	});
</script>
</html>
