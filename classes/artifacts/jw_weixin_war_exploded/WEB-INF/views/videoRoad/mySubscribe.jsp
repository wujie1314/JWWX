<%@page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="road_taglibs.jsp"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta content="telephone=no" name="format-detection">
<title>我的订阅</title>
<link rel="stylesheet" href="${ctx}/css/road/style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/road/main.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/road/pullToRefresh.css" />
<link rel="stylesheet" href="${ctx}/css/weui-master/dist/style/weui.min.css" />
<link rel="stylesheet" href="${ctx}/css/weui-master/dist/example/example.css" />
</head>
<body style="background: #EFEFEF;">
<div id="wrapper">
	<div class="content" id="container">
        <div class="wx01-title overflow">
            <div class="clear" style="font-size: 18px;">我的订阅<!-- <font size="2px" color="red">(先收藏后订阅)</font> --></div>
            <div class="button" onclick="hrefSubscribeText()">高速路况</div>
            <div class="button" onclick="hrefSubscribeImg()">视频截图</div>
        </div>
        <ul class="wx01-banner" style="margin: 15px 0px;" id="myCollent">
	        <li>
	            <div class="middle">
	                <p class="title">G5二广高速</p>
	                <p class="sub-title">沙坪坝-上行(出城)</p>
	                <p class="time">09:00</p>
	                <p class="week sub-title">周一周二周五</p>
	            </div>
	            <div class="operte" style="float: right;">
	                <img style="width: 24%;float: right;margin-top: 28px;" src="${ctx}/image/road/01-del.png"/>
	            </div>
	        </li>
	    </ul>
		
	</div>
</div>

<div class="weui_dialog_confirm" id="isDelDialog" style="display: none;">
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

	<script type="text/javascript" src="${ctx}/js/road/iscroll.js"></script>
	<script type="text/javascript" src="${ctx}/js/road/pullToRefresh.js"></script>
	<script type="text/javascript">
    
    var openId = '${openId}';
	function loadMySubscribe(){
    	$("#myCollent").html('');
    	$.ajax({
			url : "${ctx}/videoRoad/queryMySubscribe",
			cache : false,
			type : "post",
			dataType : "json",
			data : {
				"openId" : openId
			},
			success : function(data) {
				var ul = '';
				$.each(data, function(i,subs) {
					var subsTitleName = subs.subsTitleName;
					var subsId = subs.subsId;
					var subsCharacter = subs.subsCharacter;
					var subsRemindHour = subs.subsRemindHour;
					var subsRemindMinute = subs.subsRemindMinute;
					var subsRemindType = subs.subsRemindType;
					var subsRemindDate = subs.subsRemindDate;
					var subsRemindWeekStr = subs.subsRemindWeekStr;
					var type = 0;
					
					var remind = '';
					if(subsRemindType == 1){
						remind = subsRemindWeekStr;
					} else {
						remind = subsRemindDate+ "&nbsp;&nbsp;" + subsRemindWeekStr;
					}
					if("文字"==subsTitleName){
						ul += "<li><div onclick=\"editMySubscribe('"+subsId+"','"+type+"')\" style=\"width:93%\" class=\"middle\"><p class=\"title\">"+subsTitleName+"</p><p class=\"sub-title\">"+subsCharacter+"</p><p class=\"time\">"+subsRemindHour+":"+subsRemindMinute+"</p><p class=\"week sub-title\">"+remind+"</p></div><img onclick=\"isDel('"+subsId+"')\" style=\"width: 6%;float: right;margin-top: 28px;\" src=\"${ctx}/image/road/01-del.png\"/></li>";
					} else {
						type = 1;
						ul += "<li><div onclick=\"editMySubscribe('"+subsId+"','"+type+"')\" style=\"width:93%\" class=\"middle\"><p class=\"title\">"+subsTitleName+"</p><p class=\"time\">"+subsRemindHour+":"+subsRemindMinute+"</p><p class=\"week sub-title\">"+remind+"</p></div><img onclick=\"isDel('"+subsId+"')\" style=\"width: 6%;float: right;margin-top: 28px;\" src=\"${ctx}/image/road/01-del.png\"/></li>";
					}
					
				});
				$("#myCollent").html(ul);
			}
		});
    }
	
	function hrefSubscribeText(){
		location.href="${ctx}/videoRoad/addTextSubscribe?openId="+openId;
	}
	function hrefSubscribeImg(){
		location.href="${ctx}/videoRoad/saveImgSubscribe?openId="+openId;
	}
	
	
	var id = '';
	function isDel(subsId){
		id = subsId;
		$("#isDelDialog").css("display", "block");
	}
	function delOk(){
		$.post("${ctx}/subscribe/del",
    	    {"id":id},
    	    function(data) {
    	    	$("#isDelDialog").css("display", "none");
    	    	if("0"==data.code){
    	    		loadMySubscribe();
    	    	} else {
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
		$("#errorDialog").css("display", "block");
	}
	function offErrorDialog(){
		$("#errorDialog").css("display", "none");
	}
	
	function editMySubscribe(id,type){
		//type = 0文字，1图片
		if(type == 1){
			location.href="${ctx}/videoRoad/saveImgSubscribe?openId="+openId+"&id="+id;
		} else {
			location.href="${ctx}/videoRoad/addTextSubscribe?openId="+openId+"&id="+id;
		}
	}
	
    $(function(){
    	loadMySubscribe();
    })
    
</script>
</body>
</html>
