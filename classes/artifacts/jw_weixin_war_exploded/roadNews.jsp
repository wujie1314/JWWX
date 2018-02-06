<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String openid=request.getParameter("openId");
%>
<script type="text/javascript" src="/js/jquery/jquery-1.7.2.min.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="Keywords">
<meta content="" name="Description">
<title>路况信息</title>
<style type="text/css">
body {
	font-family: "Microsoft YaHei", 微软雅黑, "MicrosoftJhengHei", 华文细黑, STHeiti,
		MingLiu
}

.button_a {
	Display: block;
	Width: 60px;
	Padding: 2px;
	Line-height: 20px;
	Background-color: #04BE02;
	Border: 1px solid #04AB02;
	Color: #FFFFFF;
	Text-decoration: none;
	Text-align: center;
}

.button_b {
	Width: 60px;
	Height: 40px;
	Padding: 2px;
	Line-height: 36px;
	Background-color: #04BE02;
	Border: 1px solid #04AB02;
	Color: #FFFFFF;
	Font-size: 25px;
	Text-align: center;
	Vertical-align: middle;
}

a:hover {
	background-color: #006400;
	color: #FFFFFF;
}
</style>
</head>
<body>
	<div style="margin-top: 10px; width: 100%; height: 30px;">
		<table style="width: 100%;">
			<tr>
				<td width="20%" align="left"><a class="button_a"
					href="javasrcipt:void(0);" onclick="history.go(-1)">返回</a></td>
				<td width="20%" align="center"><a class="button_a" id="GSLK"
					href="#" onclick="showGSRoadNews();">高速</a></td>
				<td width="20%" align="center"><a class="button_a" id="GSGD"
					href="#" onclick="showGSGDRoadNews();">国道</a></td>
				<td width="20%" align="center"><a class="button_a" id="SBLK"
					href="#" onclick="showSBLKRoadNews();">报路况</a></td>
				<td width="20%" align="right"><a class="button_a" href="#"
					onclick="history.go(0)">刷新</a></td>
			</tr>
		</table>
	</div>
	<div id="roadCondition"
		style="margin-top: 10px; width: 100%; position: relative;"></div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		getRoadLxfd();
		$("#GSLK").click();
	});
	function showGSRoadNews() {
		$(".button_a").each(function(){
			$(this).css("background-color","#04BE02");
		});
		$("#GSLK").css("background-color","#006400");
		$.ajax({
			type : "post",
			url : "/csc/getRoadNews",
			dataType : "json",
			data : {},
			success : function(data) {
				var tempContents=data.data;
				if (tempContents.length != 0) {
					var num=0;
					for (var i = 0; i < tempContents.length; i++) {
						if($("#"+tempContents[i].roadLineName).html()!=null){
							$("#"+tempContents[i].roadLineName).append(
								"<br><font color='#555555  '>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
									+ tempContents[i].content + "</font>");
							$("#font"+tempContents[i].roadLineName).html((parseInt($("#font"+tempContents[i].roadLineName).html())+1));
						}else{
							$("#div"+tempContents[i].roadLineName).append(
								"<div id="+tempContents[i].roadLineName+" style='padding:4px;display:none;'><font color='#555555  '>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
									+ tempContents[i].content + "</font></div>");
							$("#temp"+tempContents[i].roadLineName).append(
									"<font color='#555555  '>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
									+ tempContents[i].content.substring(0,40) + "...</font>");	
							$("#div"+tempContents[i].roadLineName).append("<b><font id='font"+tempContents[i].roadLineName+"' style='position:absolute;top:0px;right:0px;color:red;'>1</font></b>");
						}
					}
				} else {
					$("#roadCondition").append("<div>暂无高速路况信息！</div>");
				}
			}
		});
	}
	function showGSGDRoadNews() {
		$(".button_a").each(function(){
			$(this).css("background-color","#04BE02");
		});
		$("#GSGD").css("background-color","#006400");
		$.ajax({
			type : "post",
			url : "/csc/getGSGDRoadNews",
			dataType : "json",
			data : {},
			success : function(data) {
				$("#roadCondition").html("");
				var tempContents=data.data;
				if(tempContents.length==0){
					$("#roadCondition").append("<div>暂无国省干道路况信息！</div>");
				}else{
					for (var i = 0; i < tempContents.length; i++) {
						$("#roadCondition").append(
								"<div style='padding:4px;'><b>" + (i + 1) + "、"
										+ tempContents[i].location + "</b></div>");
						$("#roadCondition").append(
								"<div style='padding:4px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
										+ tempContents[i].content + "</div>");
					}
				}
			}
		});
	}
	//var lxfdNames=new Array();
	//var lxfdDescripts=new Array();
	function getRoadLxfd() {
		$.ajax({
			type : "post",
			url : "/csc/getRoadLxfdList",
			dataType : "json",
			async: false,
			data : {},
			success : function(data) {
				$("#roadCondition").html("");
				$("#roadCondition").append("<div style='text-align:center;'><input type='text' name='search' value='' id='search' style='line-height:30px;height:36px;width:70%;vertical-align:middle;'/>"
				+"&nbsp;<a id='searchBtn' href='#' onclick='searchRoadNews();'>搜索</a>"
				+"</div>");
				for(var i=0;i<data.length;i++){
					var lxfdName=data[i].roadCode1+data[i].roadName+data[i].ldName;
					var lxfdDescripts=data[i].descript;
					$("#roadCondition").append("<hr id='hr"+lxfdName+"' style='border:none;border-top:1px solid #eeeeee;height:1px;'>");
					$("#roadCondition").append(
							"<div id='div"+lxfdName+"' onclick=showRoadNews('"+lxfdName+"') style='padding:4px;position:relative;'>"
							+"<div style='position:absolute;top:4px;left:0px;'><img src='/image/gslk/"+data[i].roadCode1+".png' style='height:36px;'></div>"
							+"<div style='margin-left:36px;'><b><font size=4>"+lxfdName +"</font><br><font size=2>"+lxfdDescripts+"</font></b></div></div>");
					$("#roadCondition").append(
							"<div id='temp"+lxfdName+"' style='padding:4px;position:relative;'></div>");
				}
			}
		});
	}
	function showSBLKRoadNews() {
		$(".button_a").each(function(){
			$(this).css("background-color","#04BE02");
		});
		$("#SBLK").css("background-color","#006400");
		window.location.href="reportRoadNews.jsp?openId=<%=openid%>";
	}
	function showRoadNews(name){
		if($("#"+name).css("display")=="none"){
			$("#"+name).show();
			$("#temp"+name).hide();
			//$("#img"+name).attr("src","/image/up.jpg");
		}
		else if($("#"+name).css("display")=="block"){
			$("#"+name).hide();
			$("#temp"+name).show();
			//$("#img"+name).attr("src","/image/down.jpg");
		}
	}
	function searchRoadNews(){
		var searchName=$("#search").val();
		if(searchName!=""){
			$("div[id^='div']").each(function(){
				if($(this).html().indexOf(searchName)!=-1){
					$(this).show();
					$("#temp"+$(this).attr("id").substring(3)).show();
					$("#hr"+$(this).attr("id").substring(3)).show();
				}else{
					$(this).hide();
					$("#temp"+$(this).attr("id").substring(3)).hide();
					$("#hr"+$(this).attr("id").substring(3)).hide();
				}
			});
		}else{
			$("div[id^='div']").each(function(){
				$(this).show();
				$("#temp"+$(this).attr("id").substring(3)).show();
				$("#hr"+$(this).attr("id").substring(3)).show();
			});
		}
	}
</script>
</html>