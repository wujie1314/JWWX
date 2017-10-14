<%@page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ include file="road_taglibs.jsp"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta content="telephone=no" name="format-detection">
<title>订阅-视频截图</title>
<link rel="stylesheet" href="${ctx}/css/weui-master/dist/style/weui.min.css" />
<link rel="stylesheet" href="${ctx}/css/weui-master/dist/example/example.css" />
<link rel="stylesheet" href="${ctx}/css/road/style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/road/main.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/road/lCalendar.css" />
</head>
<body style="background: #EFEFEF;">
<div id="wrapper">
	<div class="content" id="container">
        <ul class="wx02 white-bg wx02-first">
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
	         	<span class="input-title" id="week">每周重复<font style="font-size: 14px"></font></span>
	         	<span class="sub-text jiantou" id="isRepeat">从不</span>
	     	</li>
	 	</ul>
	 	
		<ul class="wx02 white-bg wx05-four overflow">
			<c:if test="${empty  videoList}">
				<p>很抱歉，您现不能订阅，请收藏后，再订阅！</p>
			</c:if>
			<c:forEach items="${videoList}" var="vide" varStatus="i">
        	<li onclick="checkBoxChange('${vide.videId }')">
              	<div class="top">
              		<input type="checkbox" name="imgCheckbox" value="${vide.videId }" id="checkBox${vide.videId }" style="display: none;"></input>
                  	<img class="pic" src="${vide.videUrl}">
                  	<img class="icon" id="checkImg${vide.videId }" src="${ctx}/image/road/wx02-input-icon-3.png">
              	</div>
              	<p class="p-no-newline">${vide.videShowName }</p>
              	<!-- 
              	<c:if test="${fn:length(vide.videShowName)>8}">  
                    <p style="overflow: hidden;text-overflow: ellipsis;">${fn:substring(vide.videShowName, 0, 8)}...</p>
                </c:if>  
                <c:if test="${fn:length(vide.videShowName)<=8 }">  
                	<p>${vide.videShowName }</p>  
                </c:if>  
                 -->
          	</li>
			</c:forEach>
          	
     	 </ul>
      
		<ul class="wx02 white-bg wx02-third overflow" style="margin-top: 0px;">
	      	<li class="overflow" onclick="onSave()">
	            <button class="overflow save">保存</button>
	        </li>
	        <li class="overflow" onclick="onBack()">
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
			$("#week").html("每周重复");
			$("#isRepeat").html("从不");
		} else {
			var s = subsRemindWeek.split(",");
			var week = "";
			$.each(s, function(n, value) {
				switch (value) {
				case "0":
					week += "日&nbsp;";
					break;
				case "1":
					week += "一&nbsp;";
					break;
				case "2":
					week += "二&nbsp;";
					break;
				case "3":
					week += "三&nbsp;";
					break;
				case "4":
					week += "四&nbsp;";
					break;
				case "5":
					week += "五&nbsp;";
					break;
				case "6":
					week += "六&nbsp;";
					break;
				}
			});
			week = week.substring(0, week.length - 1);
			$("#week").html("每周重复&nbsp;&nbsp;&nbsp;&nbsp;<font style=\"font-size: 14px\">周&nbsp;"+week+"</font>");
			$("#isRepeat").html("重复");
		}
	}
	
	function onBack(){
		document.location.href = "${ctx}/videoRoad/mySubscribe?&openId=" + openId;
	}
	
	function onSave(){
		
	}
	function selectWeek() {
		if (subsRemindWeek != "") {
			var s = subsRemindWeek.split(",");
			$.each(s, function(n, value) {
				$("#w" + value).attr("checked", true);
			});
		}
	}
	
	function onSave(){
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
		    	    		onBack();
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
		    	    		onBack();
		    	    	} else {
		    	    		errorMsg(data.msg);
		    	    	}
		    		}
		    	);
		}
		
	}

	function selectImg(){
		var img = '${road.subsImg}';
		if(img != ""){
			var s = img.split(",");
			 $.each(s,function(n,value) { 
				 $("#checkImg"+value).click();
			 });
		}
	}
	
	function checkBoxChange(videId){
		if($("#checkImg"+videId).attr('src')=='${ctx}/image/road/wx02-input-icon-1.png'){
            $("#checkImg"+videId).attr('src','${ctx}/image/road/wx02-input-icon-3.png');
            $("#checkBox"+videId).attr("checked",false);
        }else if($("#checkImg"+videId).attr('src')=='${ctx}/image/road/wx02-input-icon-3.png'){
            $("#checkImg"+videId).attr('src','${ctx}/image/road/wx02-input-icon-1.png');
            $("#checkBox"+videId).attr("checked",true);
        }
	}
    $(function(){
    	$('.wx05-four .top .icon' ).click(function(){
			var checkImgId = $(this ).attr("id");
            var checkId = checkImgId.replace("checkImg","checkBox");
            if($(this ).attr('src')=='${ctx}/image/road/wx02-input-icon-1.png'){
                $(this ).attr('src','${ctx}/image/road/wx02-input-icon-3.png');
                $("#"+checkId).attr("checked",false);
            }else if($(this ).attr('src')=='${ctx}/image/road/wx02-input-icon-3.png'){
                $(this ).attr('src','${ctx}/image/road/wx02-input-icon-1.png');
                $("#"+checkId).attr("checked",true);
            }
        });
		//初始化数据
		subsRemindWeek = '${road.subsRemindWeek}';
		//初始化每周重复
		eidtRepeat();
		//初始化星期是否选择
		selectWeek();
		selectImg();
		
    })
    
</script>
</body>
</html>
