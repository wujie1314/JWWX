<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String openid=request.getParameter("openId");
%>
<script type="text/javascript" src="/js/jquery/jquery-1.7.2.min.js"></script>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=0">
<link rel="stylesheet" href="/css/weui-master/dist/style/weui.css" />
<link rel="stylesheet" href="/css/weui-master/dist/example/example.css" />
<title>路况直播室</title>
</head>
<body>
	<div class="page">
		<div style="">
			&nbsp;<input type="hidden" name="page" id="page" value="1">
		</div>
		<div class="bd spacing">
			<a id="prePage" href="javascript:preRoadNews();"
				class="weui_btn weui_btn_default">上一页</a>
			<div id="contentPage" class="weui_cells weui_cells_access"></div>
			<div style="">&nbsp;</div>
			<a id="nextPage" href="javascript:nextRoadNews();"
				class="weui_btn weui_btn_default">下一页</a> <a
				href="javascript:addRoadNews();" class="weui_btn weui_btn_primary">上报路况</a>
		</div>
	</div>
</body>
<script type="text/javascript">
$(document).ready(function(){
	getRoadNews();
});
function preRoadNews(){
	$("#page").val(parseInt($("#page").val())-1);
	getRoadNews();
}
function nextRoadNews(){
	$("#page").val(parseInt($("#page").val())+1);
	getRoadNews();
}
function getRoadNews(){
	var page=parseInt($("#page").val());
	var pageSize = 10;
    $.ajax({
		type : "post",
		url : "/csc/getSblkInfo",
		dataType : "json",
		data : {
			"page":page,
			"pageSize":pageSize
		},
		success : function(data) {
			$("#prePage").css("display","block");
			$("#nextPage").css("display","block");
			if(page==1){
				$("#prePage").css("display","none");
			}
			if(page*pageSize>=data.total){
				$("#nextPage").css("display","none");
			}
			if(data.total>0){
				var data=data.data;
				for(var i=0;i<data.length;i++){
					var html="";
					html+='<a class="weui_cell" href="javascript:detailSblkInfo('+data[i].id+');">';
					html+='<div class="weui_cell_bd weui_cell_primary">';
					html+='<p>'+data[i].title+'</p>';
					html+='</div>';
					html+='<div class="weui_cell_ft">查看</div>';
					html+='</a>';
					html+='<div class="bd spacing">'+data[i].content+'</div>';
					$("#contentPage").html(html);
			    }
			}else{
				$("#contentPage").html("暂无上报的路况信息！");
				$("#prePage").css("display","none");
				$("#nextPage").css("display","none");
			}
        }
	});
}
function detailSblkInfo(id){
	window.location.href="/csc/detailSblkInfo?openid=<%=openid%>&id="+id;
}
function addRoadNews(){
	window.location.href="/csc/wxSign?openid=<%=openid%>";
}
</script>
</html>