<%@page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="road_taglibs.jsp"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta content="telephone=no" name="format-detection">
<title>订阅-高速路况</title>
<link rel="stylesheet" href="${ctx}/css/weui-master/dist/style/weui.min.css" />
<link rel="stylesheet" href="${ctx}/css/weui-master/dist/example/example.css" />
<link rel="stylesheet" href="${ctx}/css/road/style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/road/main.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/road/lCalendar.css" />
</head>
<body style="background: #fff;">
<div id="wrapper">
	<div class="container" id="container" style="overflow: visible;">
        <ul class="wx02 white-bg wx02-first">
			<li>
              	<span class="input-title">订阅类型</span>
              	<span class="sub-text input-radio">
              		<input type="radio" id="c_0" name="subsType" value="0">
                  	<label for="c_0" class="open-label" id="subsType0"></label>定时推送
              	</span>
              	<span class="sub-text input-radio">
              		<input type="radio" checked id="c_1" name="subsType" value="1">
                  	<label for="c_1" class="close-label" id="subsType1"></label>
                  	及时推送
                </span>
          	</li>
          	<li>
     			<span class="input-title">订阅日期</span>
     			<input type="button" value="${road.subsRemindDate }" name="subsRemindDate" id="subsRemindDate" data-lcalendar="2011-01-1,2019-12-31"/>
 			</li>
			<li style="border-bottom: 1px solid #DCDDDD;">
		    	<span class="input-title">推送时间</span>
		        <input type="button" value="${road.subsRemindTimeHour }" name="subsRemindTimeHour" id="subsRemindTimeHour">
		    </li>
		</ul>
	 	<ul class="wx02 white-bg wx02-second" onclick="showWeekDialog()" style="margin-top: 0px;">
	     	<li >
	         	<span class="input-title">每周重复<p id="week" style="font-size: 12px"></p></span>
	         	<span class="sub-text jiantou" id="isRepeat">从不</span>
	     	</li>
	 	</ul>
		<ul class="wx02 white-bg wx02-third overflow" style="margin-top: 0px;">
   			<li>
       			<span class="input-title">路线名称</span>
		        <select onchange="showOtherChange(this.value);" name="lxfd" id="lxfd">
		            <option value="">请选择路线名称</option>
		            <c:forEach items="${sectionList}" var="sect" varStatus="i">
		            	<option value="${sect.roadCode }">${sect.roadCode }${sect.roadName }${sect.ldName }</option>
		            </c:forEach>
		        </select>
   			</li>
   			<li>
		        <span class="input-title">开始路段</span>
		        <select name="htlj_begin" id="htlj_begin">
		            <option value="">请选择开始路段</option>
		        </select>
		    </li>
   			<li class="overflow" style="clear: both;">
	            <span class="input-title">结束路段</span>
	            <select name="htlj_end" id="htlj_end">
	                <option value="">请选择结束路段</option>
	            </select>
	        </li>
       		<li>
           		<span class="input-title">道路方向</span>
           		<select name="dlfx" id="dlfx">
	                <option value="">请选择道路方向</option>
	            </select>
       		</li>
	        <li class="last overflow" onclick="addRoad()">
	            <button class="overflow save">保存</button>
	        </li>
	        <li class="last overflow" onclick="onBack()">
	            <button class="overflow save">返回</button>
	        </li>
    	</ul>
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

<script type="text/javascript" src="${ctx}/js/road/lCalendar.js"></script>
<script type="text/javascript">
    
    var openId = '${openId}';
	function errorMsg(msg){
		$("#errorMsg").html(msg);
		$("#errorDialog").css("display", "block");
	}
	function offErrorDialog(){
		$("#errorDialog").css("display", "none");
	}
	
	var subsRemindWeek = "";
	var calendar = new lCalendar();
    calendar.init({
        'trigger': '#subsRemindDate',
        'type': 'date'
    });

    var calendartime = new lCalendar();
    calendartime.init({
        'trigger': '#subsRemindTimeHour',
        'type': 'time'
    });
    
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
	
	var subsCharacter='${road.subsCharacter}'; 
	var subsCharacters=subsCharacter.split(",");
	
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
	var subsOpenId = "${openId}";
	function addRoad() {
		var subsId = "${road.subsId}";
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
		if (htlj_begin == '' || htlj_begin == null) {
			errorMsg("请选择开始路段!");
			return;
		}
		var htlj_end=$("#htlj_end").val();
		if (htlj_end == '' || htlj_end == null) {
			errorMsg("请选择结束路段!");
			return;
		}
		
		var dlfx=$("#dlfx").val();
		if (dlfx == '' || dlfx == null) {
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
									document.location.href = "${ctx}/videoRoad/mySubscribe?&openId=" + subsOpenId;
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
									document.location.href = "${ctx}/videoRoad/mySubscribe?&openId=" + subsOpenId;
								} else {
									errorMsg(data.msg);
								}
							});
		}

	}
	
	function onBack(){
		document.location.href = "${ctx}/videoRoad/mySubscribe?&openId=" + subsOpenId;
	}
	
	function selectWeek() {
		if (subsRemindWeek != "") {
			var s = subsRemindWeek.split(",");
			$.each(s, function(n, value) {
				$("#w" + value).attr("checked", true);
			});
		}
	}
	
	function checkSubsType(i){
		$("#"+"subsType"+i).click();
		/**
		$("input[name='subsType']").each(function(){
			alert($(this).val());
			if($(this).val()==i){
				$(this).click();
				$(".wx02 li label" ).removeClass('open-label' ).addClass('close-label')
            	$(this ).addClass('open-label' ).removeClass('close-label')
			}
		});
		*/
	}
	
    $(function(){
    	$(".wx02 li label" ).click(function(){
            $(".wx02 li label" ).removeClass('open-label' ).addClass('close-label')
            $(this ).addClass('open-label' ).removeClass('close-label')

        })
        
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
        if(subsCharacter!=""&&subsCharacters.length>0){
    		showOtherChange(subsCharacters[0]);
    		$("#lxfd").val(subsCharacters[0]);
        }
    })
    
</script>
</body>
</html>
