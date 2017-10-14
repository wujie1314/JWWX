<%@page language="java" contentType="text/html;charset=UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="org.jiaowei.entity.RoadLxfdEntity"%>
<%@ include file="../videoImg/taglibs.jsp"%>
<html>
<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="Keywords">
<meta content="" name="Description">
<title>路况订阅-高速路况</title>
<link rel="stylesheet" type="text/css" href="demo.css">
<style>
* {
	padding: 0;
	margin: 0;
}

.content {
	margin: 0px auto 0;
	width: 300px;
	min-height: 500px;
}

.form-group {
	width: 100%;
	float: left;
	margin: 5px 0;
}

label {
	margin-left: 15px;
	margin-top: 10px;
	margin-bottom: 10px;
	font-size: 15px;
	float: left;
}

.field-input, select {
	width: calc(100% - 20px);
	float: left;
	padding: 8px;
	font-family: inherit;
}
</style>

<!--必要样式-->
<link rel="stylesheet" href="pickout.css">
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
	background-color: #FFFFFF;
}

.button_area {
	background-color: #FFFFFF;
	width: 100%;
	height: 30px;
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
	margin-top: 0px;
	background-color: #FFFFFF;
	width: 100%;
	height: 30px;
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

.checkbox {
	width: 16px;
	height: 16px;
	float: left;
}
</style>
</head>
<body style="margin: 0px;">
	<div class="container">
		<div class="content">

			<!-- <label for="dlfx">订阅类型:</label>
			<select name="subsType" id="subsType" class="city pickout">
				<option value="0" ${road.subsType == 0? 'selected="selected"':'' }>定时推送</option>
				<option value="1" ${road.subsType == 0? 'selected="selected"':'' }>及时推送</option>
			</select> -->
			<div class="date_area">
				<div class="weui_cell">
					<div class="weui_cell_hd">
						<label for="" class="weui_label"
							style="font-size: 16px; width: 80px">订阅类型</label>
					</div>
					<div class="weui_cell_bd weui_cell_primary">
						<input type="radio" name="subsType" value="0" /> <span
							onclick="checkSubsType(0);" style="font-size: 16px;">定时推送</span>&nbsp;&nbsp;
						<input type="radio" name="subsType" value="1" }/> <span
							onclick="checkSubsType(1);" style="font-size: 16px;"> 及时推送</span>
					</div>
				</div>
			</div>
			<div class="date_area">
				<div class="weui_cell">
					<div class="weui_cell_hd">
						<label for="" class="weui_label" style="font-size: 16px;">日期</label>
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
					<div class="weui_cell_hd" style="font-size: 16px;">
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
			<div class="weui_cells weui_cells_access"
				style="position: relative; width: 100%;">
				<a class="weui_cell" href="javascript:;" onclick="showWeekDialog()">
					<div class="weui_cell_bd weui_cell_primary">
						<p>每周重复</p>
						<!-- 周日，周一，周二，周三，周四，周五，周六 -->
						<div id="week" style="font-size: 16px;"></div>
					</div>
					<div class="weui_cell_ft" id="isRepeat" style="font-size: 16px;">从不</div>
				</a>
			</div>
			<form action="#" method="post">
				<div class="form-group">
					<label for="lxfd">路线名称:</label> <select
						onchange="showOtherChange(this.value);" width="300px" name="lxfd"
						id="lxfd" class="city pickout">
						<option value="">请选择路线名称</option>
						<%
							List list = (List) request.getAttribute("list");
							for (int i = 0; i < list.size(); i++) {
								RoadLxfdEntity rEntity = (RoadLxfdEntity) list.get(i);
								out.println("<option data-icon='&spades;' value='"
										+ rEntity.getRoadCode() + "'>" + rEntity.getRoadCode()
										+ rEntity.getRoadName() + rEntity.getLdName()
										//+ "<br>"
										//+ "（"+rEntity.getStartName()+"-"+rEntity.getEndName()+ "）"
										+ "</option>");
							}
						%>
					</select> <label for="htlj_begin">开始路段:</label> <select name="htlj_begin"
						width="300px" id="htlj_begin" class="city pickout">
						<option value="">请选择路线名称</option>
					</select> <label for="htlj_end">结束路段:</label> <select name="htlj_end"
						id="htlj_end" class="city pickout">
						<option value="">请选择路线名称</option>
					</select> <label for="dlfx">道路方向:</label> <select name="dlfx" id="dlfx"
						class="city pickout">
						<option value="">请选择路线名称</option>
					</select>
				</div>
			</form>
			<div style="margin-top: 10px; height: 42px">
				<a href="javascript:;" class="weui_btn weui_btn_primary"
					onclick="addRoad();">保存</a>
			</div>
		</div>
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
	function showList(code) {
		//var n=$("div[name*='N']");
		//$.each(n,function(){
		//	$(this).css("display", "none");
		//});
		var m = $("div[name*='" + code + "']");
		$.each(m, function() {
			if ($(this).css("display") == "none")
				$(this).css("display", "block");
			else
				$(this).css("display", "none");
		});
	}

	function selectImg() {
		var img = '${road.subsCharacter}';
		if (img != "") {
			var s = img.split(",");
			$.each(s, function(n, value) {
				$("#s" + value).attr("checked", true);
			});
		}
	}

	function selectWeek() {
		if (subsRemindWeek != "") {
			var s = subsRemindWeek.split(",");
			$.each(s, function(n, value) {
				$("#w" + value).attr("checked", true);
			});
		}
	}

	function changeTab(id) {
		var type = 0;
		if ("tab-text" == id) {
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

	function showWeekDialog() {
		$("#weekDialog").css("display", "block");
	}
	function notShowWeekDialog() {
		$("#weekDialog").css("display", "none");
	}

	//确定重复
	function confirmIsRepeat() {
		notShowWeekDialog();
		subsRemindWeek = "";
		var arrChk = $("input[name='checkbox1']:checked");
		$(arrChk).each(function() {
			subsRemindWeek += this.value + ",";
		});
		if (subsRemindWeek != "") {
			subsRemindWeek = subsRemindWeek.substring(0,
					subsRemindWeek.length - 1);
		}
		eidtRepeat();
	}

	function eidtRepeat() {
		//alert("subsRemindWeek:"+subsRemindWeek);
		if (subsRemindWeek == "") {
			$("#week").html("");
			$("#isRepeat").html("从不");
		} else {
			var s = subsRemindWeek.split(",");
			var week = "";
			$.each(s, function(n, value) {
				switch (value) {
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

	function addRoad() {
		var subsId = "${road.subsId}";
		var subsOpenId = "${openId}";
		var subsImg = "";
		var subsRemindDate = $("#subsRemindDate").val();
		var subsRemindTimeHour = $("#subsRemindTimeHour").val();
		var subsRemindHour = $("#subsRemindHour").val();
		var subsRemindMinute = $("#subsRemindMinute").val();
		//var subsType = $("#subsType").val();
		var subsType = $('input[name="subsType"]:checked ').val();
		var subsRemindType = 0;
		var subsIsStart = 0;
		var subsTitleName = '文字';
		if (subsRemindDate == '') {
			errorMsg("请选择提醒日期!");
			return;
		}
		if (subsRemindTimeHour == '') {
			errorMsg("请选择提醒时间!");
			return;
		}
		var lxfd=$("#lxfd").val();
		if (lxfd == '') {
			errorMsg("请选择路段名称!");
			return;
		}
		var htlj_begin=$("#htlj_begin").val();
		if (htlj_begin == '') {
			errorMsg("请选择开始路段!");
			return;
		}
		var htlj_end=$("#htlj_end").val();
		if (htlj_end == '') {
			errorMsg("请选择结束路段!");
			return;
		}
		var dlfx=$("#dlfx").val();
		if (dlfx == '') {
			errorMsg("请选择道路方向!");
			return;
		}
		var subsCharacter=lxfd+","+htlj_begin+","+htlj_end+","+dlfx;
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
		if (subsRemindWeek != "") {
			subsRemindType = 1;
		}

		if (subsId == "") {
			$
					.post(
							"${ctx}/subscribe/add",
							{
								"subsOpenId" : subsOpenId,
								"subsCharacter" : subsCharacter,
								"subsImg" : subsImg,
								"subsRemindDate" : subsRemindDate,
								"subsRemindTimeHour" : subsRemindTimeHour,
								"subsRemindHour" : subsRemindHour,
								"subsRemindMinute" : subsRemindMinute,
								"subsRemindWeek" : subsRemindWeek,
								"subsRemindType" : subsRemindType,
								"subsIsStart" : subsIsStart,
								"subsTitleName" : subsTitleName,
								"subsType" : subsType
							},
							function(data) {
								if ("0" == data.code) {
									document.location.href = "${ctx}/videoImg/list?type=0&openId="
											+ subsOpenId;
								} else {
									errorMsg(data.msg);
								}
							});
		} else {
			$
					.post(
							"${ctx}/subscribe/edit",
							{
								"subsId" : subsId,
								"subsOpenId" : subsOpenId,
								"subsCharacter" : subsCharacter,
								"subsImg" : subsImg,
								"subsRemindDate" : subsRemindDate,
								"subsRemindTimeHour" : subsRemindTimeHour,
								"subsRemindHour" : subsRemindHour,
								"subsRemindMinute" : subsRemindMinute,
								"subsRemindWeek" : subsRemindWeek,
								"subsRemindType" : subsRemindType,
								"subsIsStart" : subsIsStart,
								"subsTitleName" : subsTitleName,
								"subsType" : subsType
							},
							function(data) {
								if ("0" == data.code) {
									document.location.href = "${ctx}/videoImg/list?type=0&openId="
											+ subsOpenId;
								} else {
									errorMsg(data.msg);
								}
							});
		}

	}

	function errorMsg(msg) {
		$("#errorMsg").html(msg);
		$("#weui_dialog").width("30%");
		$("#errorDialog").css("display", "block");
	}
	function offErrorDialog() {
		$("#errorDialog").css("display", "none");
	}

	var subsCharacter='${road.subsCharacter}'; 
	var subsCharacters=subsCharacter.split(",");
	$(function() {
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
		var subsType = '${road.subsType}';
		if(1==subsType){
			checkSubsType(1);
		} else {
			checkSubsType(0);
		}
		//初始化数据
		subsRemindWeek = '${road.subsRemindWeek}';
		//初始化每周重复
		eidtRepeat();
		//初始化星期是否选择
		selectWeek();
		//初始化图片
		selectImg();
        if(subsCharacter!=""&&subsCharacters.length>0){
    		showOtherChange(subsCharacters[0]);
    		$("#lxfd").val(subsCharacters[0]);
        }
	});
	function showOtherChange(value){
	    $.ajax({
			type : "post",
			url : "/csc/getRoadHtljBegin",
			dataType : "json",
			data : {
				"roadCode":value
			},
			success : function(data) {
				var _html="";
				for(var i=0;i<data.length;i++){
					_html+="<option value='"+data[i].interflowName+"'>"+data[i].name+"</option>";
				}
				$("#htlj_begin").html(_html);
	    		$("#htlj_begin").val(subsCharacters[1]); 
	        }
		});
	    $.ajax({
			type : "post",
			url : "/csc/getRoadHtljEnd",
			dataType : "json",
			data : {
				"roadCode":value
			},
			success : function(data) {
				var _html="";
				for(var i=0;i<data.length;i++){
					_html+="<option value='"+data[i].interflowName+"'>"+data[i].name+"</option>";
				}
				$("#htlj_end").html(_html);
	    		$("#htlj_end").val(subsCharacters[2]); 
	        }
		});
	    $.ajax({
			type : "post",
			url : "/csc/getRoadDlfx",
			dataType : "json",
			data : {
				"roadCode":value
			},
			success : function(data) {
				var _html="";
				for(var i=0;i<data.length;i++){
					_html+="<option value='"+data[i].displayName+"'>"+data[i].displayName+"</option>";
				}
				$("#dlfx").html(_html);
	    		$("#dlfx").val(subsCharacters[3]); 
	        }
		});
	}
	function checkSubsType(i){
		$("input[name='subsType']").each(function(){
			if($(this).val()==i)$(this).click();
		});
	}
</script>
</html>
