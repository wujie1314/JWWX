var jq1 = $.noConflict();// 解决JQ的冲突
jq1(function() {
	var currYear = (new Date()).getFullYear();
	var opt = {};
	opt.date = {
		preset : 'date'
	};
	opt.datetime = {
		preset : 'datetime'
	};
	opt.time = {
		preset : 'time'
	};
	opt.default1 = {
		theme : 'android-ics light', // 皮肤样式
		display : 'modal', // 显示方式
		mode : 'scroller', // 日期选择模式
		dateFormat : 'yyyy-mm-dd',
		lang : 'zh',
		/*
		 * showNow: true, nowText: "今天",
		 */
		width : 150,
		height : 80,
		startYear : currYear - 50, // 开始年份
		endYear : currYear + 10
	// 结束年份
	};
	$("#DATE").mobiscroll($.extend(opt['date'], opt['default1']));
	$("#TIME").mobiscroll($.extend(opt['time'], opt['default1']));
	type ="公交信息";
	$(".tabbable ul li").click(function() {
		type = $(this).children("a").html();

	})
	

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
function judgetype() {
	var lookType = $("#lookType").find("option:selected").text();
	if (lookType != "请选择订阅类型") {
		if (type == "高速违章") {
			if (lookType == "定时推送") {
				if ($("#DATE").val() != "" && $("#TIME").val() != "") {
					if ($("#license").val() != ""
							&& $("#color").find("option:selected").text() != "请选择牌照颜色") {
						var temper = $("#DATE").val() + " " + $("#TIME").val();
						var date= new Date(Date.parse(temper.replace(/-/g,   "/")));
						var delayTime2=(date - (new Date()));
						getViolationInformation(delayTime2);
					} else
						alert("填写车辆信息");
				} else
					alert("请填写日期");
			} else {
				var delayTime=0;
				getViolationInformation(delayTime);
			}
		} else if (type == "路况信息") {
			var lookType1 = $("#lookType").find("option:selected").text();
			if (lookType1 == "定时推送") {
				if ($("#DATE").val() != "" && $("#TIME").val() != "") {
					var temper1 = $("#DATE").val() + " " + $("#TIME").val();
					var date1= new Date(Date.parse(temper1.replace(/-/g,   "/")));
					var delayTime1=(date1 - (new Date()));
					if($("#ph").find("option:selected").text()!="请选择截图来源"&& $("#section").find("option:selected").text()!="请选择路段"){
						getTrafficByPicture(delayTime1);
					}else
					getTraffic(delayTime1);
					
				} else
					alert("请填写日期");
			} else
				var delayTimeg=0;
				getTraffic(delayTimeg);
		}else if(type=="公交信息"){
			var lookType2 = $("#lookType").find("option:selected").text();
			if (lookType2 == "定时推送") {
				if ($("#DATE").val() != "" && $("#TIME").val() != "") {
						var temperB = $("#DATE").val() + " " + $("#TIME").val();
						var dateB= new Date(Date.parse(temperB.replace(/-/g,   "/")));
						var delayTimeB=(dateB - (new Date()));
						getBus(delayTimeB);
				} else
					alert("请填写日期");
			} else {
				var delayTimeB1=0;
				getBus(delayTimeB1);
			}
		}		
	} else
		alert("请选择订阅类型");

}
function getViolationInformation(delayTime1) {
	var parame = {};
	parame.openId =$("#Id").val();
	parame.lookType = $("#lookType").find("option:selected").text();
	parame.DATE = $("#DATE").val();
	parame.TIME = $("#TIME").val();
	parame.license = $("#license").val();
	parame.color = $("#color").find("option:selected").text();
	parame.delayTime =delayTime1;
	if ($("#license").val() != ""
			&& $("#color").find("option:selected").text() != "请选择牌照颜色") {
		$.ajax({
			type : "post",
			url : "/psDesign/getViolationInformation",
			dataType : "json",
			data : parame,
			success : function(data) {
				$("#license").val("");
				var a = document.getElementById("color");
				a.options[0].selected = true;
				var a = document.getElementById("lookType");
				a.options[0].selected = true;
				$("#DATE").val("");
				$("#TIME").val("");
				alert("请求成功违章信息");
			}
		});
	} else
		alert("填写车辆信息");
}
function getBus(delayTimeB){
	var parame = {};
	parame.openId = $("#Id").val();
	parame.lookType = $("#lookType").find("option:selected").text();
	parame.DATE = $("#DATE").val();
	parame.TIME = $("#TIME").val();
	parame.delayTime =delayTimeB;
	$.ajax({
		type : "post",
		url : "/psDesign/getBus",
		dataType : "json",
		data : parame,
		success : function(data) {
			var b = document.getElementById("lookType");
			b.options[0].selected = true;
			$("#DATE").val("");
			$("#TIME").val("");
			alert("请求公交信息成功");

		}
	});
	
}
function getTraffic(delayTimeT) {
	var parame = {};
	parame.openId = $("#Id").val();
	parame.lookType = $("#lookType").find("option:selected").text();
	parame.DATE = $("#DATE").val();
	parame.TIME = $("#TIME").val();
	parame.lxfd = $("#lxfd").find("option:selected").text();
	parame.htlj_begin = $("#htlj_begin").find("option:selected").text();
	parame.htlj_end = $("#htlj_end").find("option:selected").text();
	parame.dlfx = $("#dlfx").find("option:selected").text();
	parame.delayTime =delayTimeT;
	if($("#lxfd").find("option:selected").text()!="请选择线路名称"&& $("#htlj_begin").find("option:selected").text()!="请选择开始路段"&&
			$("#htlj_end").find("option:selected").text()!="请选择结束路段"&& $("#dlfx").find("option:selected").text()!="请选择道路方向"){
	$.ajax({
		type : "post",
		url : "/psDesign/getTraffic",
		dataType : "json",
		data : parame,
		success : function(data) {
			var a = document.getElementById("lxfd");
			a.options[0].selected = true;
			var b = document.getElementById("lookType");
			b.options[0].selected = true;
			var c = document.getElementById("htlj_begin");
			c.options[0].selected = true;
			var d = document.getElementById("htlj_end");
			d.options[0].selected = true;
			var e = document.getElementById("dlfx");
			e.options[0].selected = true;
			$("#DATE").val("");
			$("#TIME").val("");
			alert("请求成功道路信息");

		}
	});
	}else if($("#ph").find("option:selected").text()!="请选择截图来源"&& $("#section").find("option:selected").text()!="请选择路段"){
		getTrafficByPicture(delayTimeT);
	}else alert("请把道路信息填写完整");

}
function getTrafficByPicture(delayTimep){
	var parame = {};
	parame.openId = $("#Id").val();
	parame.lookType = $("#lookType").find("option:selected").text();
	parame.DATE = $("#DATE").val();
	parame.TIME = $("#TIME").val();
	parame.ph = $("#ph").find("option:selected").text();
	parame.section = $("#section").find("option:selected").text();
	parame.delayTime =delayTimep;
	$.ajax({
		type : "post",
		url : "/psDesign/getTrafficByPicture",
		dataType : "json",
		data : parame,
		success : function(data) {
			var a = document.getElementById("lxfd");
			a.options[0].selected = true;
			var c = document.getElementById("ph");
			c.options[0].selected = true;
			var d = document.getElementById("section");
			d.options[0].selected = true;
			$("#DATE").val("");
			$("#TIME").val("");
			alert("请求成功图片");
			


		}
	});
	
}

