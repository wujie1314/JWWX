<%@page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="../road_taglibs.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta content="telephone=no" name="format-detection">
<title>文字路况</title>
<link rel="stylesheet" href="${ctx}/css/road/style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/road/main.css" />
<style>
		.sub-title {
			color: #999999;
			font-size: 14px;
		}
		.wx005 li{
			display: inline-block;
			width: 48.5%;
			background: #47CF2D;
			padding: 5px 0px;
			text-align: center;
			color: #272727;
			margin-left: -0.5%;
			font-size: 18px;
			border-radius: 0px;
			border-bottom: 4px solid #dcdcdc;
			background: #FFF;
		}
		.wx005 li:first-child{
			margin-left: 0px;
			background: #FFF;
		}
		.wx005-second input[type="submit"] {
		    width: 24%;
		    height: 39px;
		    color: #fff;
		    background: url("/image/road/005-01.png") 10% center no-repeat #263584;
		    background-size: 32%;
		    padding-left: 15px;
		    border-radius: 5px;
		}
		
	</style>
</head>
<body style="background: #fff;">
	<div class="container">
		<ul class="wx005 ">
            <li id="high" onclick="loadHighRoad()">高速</li>
            <li id="gsgd" onclick="loadGsgdRoad()">国道</li>
        </ul>
        <div class="wx005 wx005-second" style="padding-bottom: 0px;">
            <input type="text" id="search" placeholder="请输入关键词"><input type="submit" onclick="searchRoadNews()" value="搜索">
        </div>
        <ul class="wx01-banner wx005-third" id="myCollent" style="margin: 10px 0px;">
            <li>
				<img class="pic" style="float:left" src="images/01.png"/>
				<div class="middle">
					<p class="title">G5二广高速 <span class="tag">2</span></p>
					<p class="sub-title">牡丹园-万州-巫山-湖北方向</p>
					<div class="sub-title" style="height:100px">文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介文字简介
					</div>
				</div>
            </li>
		</ul>
	</div>
	<%@ include file="../alert.jsp"%>
	<script type="text/javascript" src="${ctx}/js/road/touche.js"></script>
	<script type="text/javascript" src="${ctx}/js/road/p-pull-refresh.js"></script>
	<script type="text/javascript">
    var requestType = 0;
    var openId = '${openId}';
	function loadHighRoad(){
		$(".wx005 li").each(function(){
			$(this).css({"border-bottom":"4px solid #dcdcdc","color":"#272727"});
		});
		$("#high").css({"border-bottom":"4px solid #263584","color":"#263584"});
		requestType = 0;
    	$("#myCollent").html('');
    	showLoad();
    	$.ajax({
			url : "${ctx}/csc/queryHighRoadList",
			cache : false,
			type : "post",
			dataType : "json",
			data : {
				"type" : 0,
				"openId" : openId
			},
			success : function(data) {
				fillAjaxData(data);
				hideLoad();
			}
		});
    }
	
	function fillAjaxData(data){
		var ul = '';
		$("#myCollent").html(ul);
		var count = 0;
		for(var i=0;i<data.length;i++){
			var lxfdName=data[i].roadCode1;
			var lxfdNextName = data[i].roadName+data[i].ldName
			var lxfdDescripts=data[i].descript;
			var contentList = data[i].data;
			var num = contentList.length;
			if(num > 0){
				var contDiv = "";
				for (var j = 0; j < contentList.length; j++) {
					contDiv += "<div class=\"sub-title\">&nbsp;&nbsp;&nbsp;&nbsp;"+contentList[j]+"</div>";
				}
				ul += "<li id=\"div"+lxfdName+lxfdNextName+"\"><img class=\"pic\" style=\"float:left;height: 36px;width:36px\" src=\"/image/gslk/"+data[i].roadCode1+".png\"/><div class=\"middle\" style=\"width: 80%;\"><p class=\"title\">"+lxfdName+lxfdNextName+" <span class=\"tag\">"+num+"</span></p><p class=\"sub-title\">"+lxfdDescripts+"</p>"+contDiv+"</div></li>";
				count++;
			//} else {
				//ul += "<li id=\"div"+lxfdName+lxfdNextName+"\"><img class=\"pic\" style=\"float:left;height: 36px;width:36px\" src=\"/image/gslk/"+data[i].roadCode1+".png\"/><div class=\"middle\" style=\"width: 80%;\"><p class=\"title\">"+lxfdName+lxfdNextName+" <span class=\"tag\">"+num+"</span></p><p class=\"sub-title\">"+lxfdDescripts+"</p><div class=\"sub-title\"></div></div></li>";
			}
		}
		if(count==0){
			ul += "<li><p class=\"title\">暂高速路况信息！ </p></li>";
		}
		$("#myCollent").html(ul);
	}
	
	function loadGsgdRoad(){
		$(".wx005 li").each(function(){
			$(this).css({"border-bottom":"4px solid #dcdcdc","color":"#272727"});
		});
		$("#gsgd").css({"border-bottom":"4px solid #263584","color":"#263584"});
		requestType = 1;
		$("#myCollent").html('');
		showLoad();
		$.ajax({
			type : "post",
			url : "/csc/getGSGDRoadNews",
			dataType : "json",
			data : {},
			success : function(data) {
				var ul = '';
				var tempContents=data.data;
				if(tempContents.length==0){
					ul += "<li><p class=\"title\">暂无国省干道路况信息！ </p></li>";
				}else{
					for (var i = 0; i < tempContents.length; i++) {
						ul += "<li id=\"div"+tempContents[i].location+"\"><p class=\"title\">"+(i + 1)+"、"+tempContents[i].location+" </p><p class=\"sub-title\">&nbsp;</p><div class=\"sub-title\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+ tempContents[i].content +"</div></li>";
					}
				}
				$("#myCollent").html(ul);
				hideLoad();
			}
		});
	}
	
	function searchRoadNews(){
		var searchName=$("#search").val();
		if(searchName!=""){
			$("li[id^='div']").each(function(){
				if($(this).html().indexOf(searchName)!=-1){
					$(this).show();
				}else{
					$(this).hide();
				}
			});
		}else{
			$("li[id^='div']").each(function(){
				$(this).show();
			});
		}
	}

	
    $(function(){
    	loadHighRoad();
    })
    
</script>
</body>
</html>
