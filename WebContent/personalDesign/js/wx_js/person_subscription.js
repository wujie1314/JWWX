var jq1=$.noConflict();//解决JQ的冲突
jq1(function () {
	var currYear = (new Date()).getFullYear();	
	var opt={};
	opt.date = {preset : 'date'};
	opt.datetime = {preset : 'datetime'};
	opt.time = {preset : 'time'};
	opt.default1 = {
		theme: 'android-ics light', //皮肤样式
		display: 'modal', //显示方式 
		mode: 'scroller', //日期选择模式
		dateFormat: 'yyyy-mm-dd',
		lang: 'zh',
		/* showNow: true,
		nowText: "今天", */
		width:150,
		height:80,
		startYear: currYear - 50, //开始年份
		endYear: currYear + 10 //结束年份
	};
	$("#DATE").mobiscroll($.extend(opt['date'], opt['default1']));
	$("#TIME").mobiscroll($.extend(opt['time'], opt['default1']));
	
});
$(function() {
	var HEIGHT = $('body').height();
	$(window).resize(function() {
		$('body').height(HEIGHT);
	});

	var subsType = '${road.subsType}';
	if (1 == subsType) {
		checkSubsType(1);
	} else {
		checkSubsType(0);
	}

	if (subsCharacter != "" && subsCharacters.length > 0) {
		showOtherChange(subsCharacters[0]);
		$("#lxfd").val(subsCharacters[0]);
	}

});

function checkSubsType(i) {
	$("option[name='subsType']").each(function() {
		if ($(this).val() == i)
			$(this).click();
	});
}

var subsCharacter = '${road.subsCharacter}';
var subsCharacters = subsCharacter.split(",");
function showOtherChange(value) {
	$.ajax({
		type : "post",
		url : "/psDesign/getRoadStart",
		dataType : "json",
		data : {
			"roadCode" : value
		},
		success : function(data) {
			var _html = "";
			for (var i = 0; i < data.length; i++) {
				_html += "<option value='" + data[i].interflowName + "'>"
						+ data[i].name + "</option>";
			}
			$("#htlj_begin").html(_html);
			$("#htlj_begin").val(subsCharacters[1]);
		}
	});
	$.ajax({
		type : "post",
		url : "/psDesign/getRoadEnd",
		dataType : "json",
		data : {
			"roadCode" : value
		},
		success : function(data) {
			var _html = "";
			for (var i = 0; i < data.length; i++) {
				_html += "<option value='" + data[i].interflowName + "'>"
						+ data[i].name + "</option>";
			}
			$("#htlj_end").html(_html);
			$("#htlj_end").val(subsCharacters[2]);
		}
	});
	$.ajax({
		type : "post",
		url : "/psDesign/getRoadDirection",
		dataType : "json",
		data : {
			"roadCode" : value
		},
		success : function(data) {
			var _html = "";
			for (var i = 0; i < data.length; i++) {
				_html += "<option value='" + data[i].displayName + "'>"
						+ data[i].displayName + "</option>";
			}
			$("#dlfx").html(_html);
			$("#dlfx").val(subsCharacters[3]);
		}
	});
}

function addRoad() {
	var subsId = "${road.subsId}";
	var subsOpenId = "${openId}";
	var subsImg = "";
	var subsRemindDate = $("#subsRemindDate").val();
	var subsRemindTimeHour = $("#subsRemindTimeHour").val();
	var subsRemindHour = $("#subsRemindHour").val();
	var subsRemindMinute = $("#subsRemindMinute").val();
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
	var lxfd = $("#lxfd").val();
	if (lxfd == '') {
		errorMsg("请选择路段名称!");
		return;
	}
	var htlj_begin = $("#htlj_begin").val();
	if (htlj_begin == '') {
		errorMsg("请选择开始路段!");
		return;
	}
	var htlj_end = $("#htlj_end").val();
	if (htlj_end == '') {
		errorMsg("请选择结束路段!");
		return;
	}
	var dlfx = $("#dlfx").val();
	if (dlfx == '') {
		errorMsg("请选择道路方向!");
		return;
	}
	var subsCharacter = lxfd + "," + htlj_begin + "," + htlj_end + "," + dlfx;

	if (subsRemindWeek != "") {
		subsRemindType = 1;
	}

	if (subsId == "") {
		$.post("${ctx}/subscribe/add", {
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
		}, function(data) {
			if ("0" == data.code) {
				document.location.href = "${ctx}/videoImg/list?type=0&openId="
						+ subsOpenId;
			} else {
				errorMsg(data.msg);
			}
		});
	} else {
		$.post("${ctx}/subscribe/edit", {
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
		}, function(data) {
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

$("#randomCode").click(function() {
	$(this).attr("src", "/psDesign/getRandcode?id=" + Math.random());
});
function getinformation(){
	alert("eeeee");
	
	
}




