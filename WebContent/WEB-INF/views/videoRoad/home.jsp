<%@page language="java" contentType="text/html;charset=UTF-8"%>
<%@include file="road_taglibs.jsp"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta content="telephone=no" name="format-detection">
<title>视频截图</title>
<link rel="stylesheet" href="${ctx}/css/road/style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/road/main.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/road/pullToRefresh.css" />
</head>
<body style="background: #fff;">
<div id="wrapper">
	<div class="container" id="container">
		<!-- 
		<div class="loading-warp">
			<div class="box">
				<div>
					<img src="${ctx}/image/road/loading.gif" /> <span class="text">下拉开始刷新</span>
				</div>
			</div>
		</div>
		 -->
		<ul class="wx02 white-bg wx02-first wx001">
			<!-- 城区 -->
			<li onclick="hrefCity()">
				<span class="input-title icon-title" style="background: url(${ctx}/image/road/001-01.png) left center no-repeat;background-size: 35%;">城区</span>
				<span class="no-text jiantou">周一,周二,周三</span>
			</li>
			<!-- 高速 -->
			<li onclick="hrefHighSpeed()">
				<span class="input-title icon-title" style="background: url(${ctx}/image/road/001-02.png) left center no-repeat;background-size: 35%;">高速</span>
				<span class="no-text jiantou">周一,周二,周三</span>
			</li>
			<!-- 车站 -->
			<li onclick="hrefStation()">
				<span class="input-title icon-title" style="background: url(${ctx}/image/road/001-03.png) left center no-repeat;background-size: 35%;">车站</span>
				<span class="no-text jiantou">周一,周二,周三</span>
			</li>
			<!-- 路况 -->
			<li onclick="hrefRoad()">
				<span class="input-title icon-title" style="background: url(${ctx}/image/road/001-04.png) left center no-repeat;background-size: 35%;">路况</span>
				<span class="no-text jiantou">周一,周二,周三</span>
			</li>
			<!-- 订阅-->
			<li onclick="hrefMySubscribe()" style="border-bottom: 1px solid #DCDDDD; padding-bottom: 15px;">
				<span class="input-title icon-title" style="background: url(${ctx}/image/road/001-05.png) left center no-repeat;background-size: 35%;">订阅</span>
				<span class="no-text jiantou">周一,周二,周三</span>
			</li>
			
		</ul>
		<div class="wx01-title white-bg overflow"
			style="margin: 0px; font-size: 18px; padding: 0px; padding-left: 10px;">
			<div class="clear">我的收藏</div>
		</div>
		<div style="background: #fff;" class="overflow wx003">
			<!-- <p class="tips">可通过下拉屏幕刷新数据</p> -->
			<ul class="wx01-second overflow" style="padding-bottom: 0px;" id="myCollent">
				<li style="border: 1px solid #cbcbcb">
					<dl>
						<dt onclick="hrefDetails()" class="overflow" style="position: relative;">
							<img class="big-pic wx-switch-img" style="width: 100%;" src="${ctx}/image/road/05-01.png">
							<img style="position: absolute;right: 5%;top: 5%;width: 17%;" src="${ctx}/image/road/01-xin-icon.png"> 
						</dt>
						<dd style="background-color: #dedede;line-height: 40px">
							<p class="text">长途汽车站-大厅</p>
						</dd>
					</dl>
				</li>
			</ul>
		</div>
	</div>
</div>

	<script type="text/javascript" src="${ctx}/js/road/iscroll.js"></script>
	<script type="text/javascript" src="${ctx}/js/road/pullToRefresh.js"></script>
	<script type="text/javascript">
    
    var openId = '${openId}';
	function loadVideoImg(){
    	$("#myCollent").html('');
    	$.ajax({
			url : "${ctx}/videoRoad/queryList",
			cache : false,
			type : "post",
			dataType : "json",
			data : {
				"openId" : openId,
				"isCollect" : true
			},
			success : function(data) {
				fillAjaxData(data);
			}
		});
    }
	
	function fillAjaxData(data){
		var ul = '';
		$.each(data, function(i,vide) {
			var showNameLength = vide.videShowName.length;
			var showName = vide.videShowName;
			//ul += "<li><dl><dt class=\"overflow\"><img class=\"big-pic wx-switch-img\" src=\""+vide.videUrl+"\"><div class=\"bg\"></div><p class=\"text p-no-newline\">"+showName+"</p></dt><dd onclick='cancelCollent("+vide.videId+")'><img src=\"${ctx}/image/road/01-xin-icon.png\"><span>"+vide.collectNum+"</span></dd></dl></li>";
			ul += "<li style=\"border: 1px solid #cbcbcb;margin-right: 1px;background-color: #dedede;\"><dl><dt class=\"overflow\" style=\"position: relative;height:130px\"><img class=\"big-pic wx-switch-img\" style=\"width: 100%;height:130px;\" src=\""+vide.videUrl+"\"><img onclick='cancelCollent("+vide.videId+")' style=\"position: absolute;right: 5%;top: 5%;width: 17%;\" src=\"${ctx}/image/road/01-xin-icon.png\"> </dt><dd style=\" height:40px; padding: 0px; line-height: 40px\"><p class=\"text p-no-newline\">"+showName+"</p></dd></dl></li>";
		});
		$("#myCollent").html(ul);
		
		initImagePreview();
	}
	
	function hrefCity(){
		location.href="${ctx}/videoRoad/city?openId="+openId;
	}
	function hrefHighSpeed(){
		location.href="${ctx}/videoRoad/high_speed?openId="+openId;
	}
	function hrefStation(){
		location.href="${ctx}/videoRoad/station?openId="+openId;
	}
	function hrefRoad(){
		location.href="${ctx}/videoRoad/road?openId="+openId;
	}
	function hrefMySubscribe(){
		location.href="${ctx}/videoRoad/mySubscribe?openId="+openId;
	}
	
	function hrefDetails(videId){
		location.href="${ctx}/videoRoad/details?openId="+openId+"&videId="+videId;
	}
	
	function cancelCollent(videId){
		$.post("${ctx}/videoRoad/delCollent",
		    {"videId":videId,"openId":openId},
		    function(data) {
		    	//刷新列表 
		    	var content = eval('(' + data + ')');
		    	if("0"==content.code){
		    		loadVideoImg();
		    	} else {
		    		alert(content.msg);
		    	}
		    });
	}
	/*
	refresher.init({
		id : "wrapper",
		pullDownAction : Refresh,
		pullUpAction : Load
	});
	var generatedCount = 0;
	function Refresh() {
		setTimeout(function() {
			loadVideoImg();
			myScroll.refresh();
		}, 1000);
	}

	function Load() {
		setTimeout(function() {
			//loadVideoImg();
			myScroll.refresh();
		}, 1000);
	}
	
	*/
	
	
    $(function(){
    	
        $(".wx02-first li" ).click(function(){
            $(this ).find(".icon-title" ).css('color','#ccc')
        })
        loadVideoImg();
        
     
    })
    
</script>
</body>
</html>
