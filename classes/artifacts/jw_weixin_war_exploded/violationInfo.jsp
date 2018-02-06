<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
<link rel="stylesheet" href="${ctx}/css/weui-master/dist/style/weui.css" />
<link rel="stylesheet" href="${ctx}/css/weui-master/dist/example/example.css" />
<style type="text/css">
.sel{border:0;border:none;width:100%;font-size:18px;}
.DivSelect
{
	width:100%;
	font-size:17px;
    float:left;
    position: relative;
    background-color: transparent;
    height: 20px;
    overflow: hidden; /*隐藏了小三角，宽度为比select宽度少20px*/
    border-width:0px;
    border-top-style: none; 
    border-right-style: none; 
    border-left-style: none; 
    border-bottom-style: none;
}
.button_a {
	display: block;
	width: 100%;
	padding: 2px;
	height: 30px;
	Line-height: 30px;
	border-radius: 5px;
	Background-color: #263584;
	border:0px;
	Color: #FFFFFF;
	Text-decoration: none;
	Text-align: center;
}
</style>
<title>违章查询</title>
</head>
<body>
	<div class="page" id="opretor">
		<div class="weui_cells_title">违章查询</div>
		<div class="weui_cells weui_cells_form">
			<div class="weui_cell">
				<div class="weui_cell_hd">
					<label class="weui_label">车牌</label>
				</div>
				<div class="weui_cell_bd weui_cell_primary">
					<input class="weui_input" name="plateNo" type="text"
						placeholder="请输入车牌" />
				</div>
			</div>
		</div>
		<div class="weui_cells weui_cells_form">
			<div class="weui_cell">
				<div class="weui_cell_hd">
					<label class="weui_label">颜色</label>
				</div>
				<div class="weui_cell_bd weui_cell_primary">
					<select class="DivSelect" name="colorNo">
					<option value="1">蓝色</option>
					<option value="2">白色</option>
					<option value="3">黑色</option>
					<option value="4">黄色</option>
					</select>
				</div>
			</div>
		</div>
		<div class="weui_btn_area">
			<a class="button_a" href="javascript:getViolationInfo();" id="showTooltips">查询</a>
		</div>
		<div id="vioInfo" style="display: none;">
		</div>
	</div>
	<div class="weui_dialog_alert" id="dialog2" style="display: none;">
		<div class="weui_mask"></div>
		<div class="weui_dialog">
			<div class="weui_dialog_hd">
				<strong class="weui_dialog_title">提示信息</strong>
			</div>
			<div class="weui_dialog_bd" id="dialogContent"></div>
			<div class="weui_dialog_ft">
				<a href="javascript:closeDialog('dialog2');"
					class="weui_btn_dialog primary">确定</a>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
function getViolationInfo(){
    var plateNo=$.trim($("input[name=plateNo]").val());
    var colorNo=$("select[name=colorNo]").val();
    if(plateNo==""){
    	$("input[name=plateNo]").focus();
		openDialog("dialog2","车牌不能为空");
    	return false;
    }
    $.ajax({
		type : "post",
		url : "${ctx}/csc/getViolationInfo",
		dataType : "json",
		data : {
			"plateNo":encodeURI(encodeURI(plateNo)),
			"colorNo":colorNo
		},
		success : function(data) {
			$("#vioInfo").html("");
			$("#vioInfo").show();
			var status=data.status;
			if(status==0){
				var temp=data.data;
				if(temp.length>0){
					for(var i=0;i<temp.length;i++){
						if(typeof(temp[i].caseDate)!="undefined")$("#vioInfo").append('<div class="weui_cells_title">违法时间：'+temp[i].caseDate+'</div>');
						if(typeof(temp[i].caseAddr)!="undefined")$("#vioInfo").append('<div class="weui_cells_title">违法地点：'+temp[i].caseAddr+'</div>');
						if(typeof(temp[i].caseInfo)!="undefined")$("#vioInfo").append('<div class="weui_cells_title">违法行为：'+temp[i].caseInfo+'</div>');
						if(i<temp.length-1)$("#vioInfo").append('<hr style="margin-left:15px;margin-right:15px;">');
					}
					$("#vioInfo").append('<div class="weui_cells_title">&nbsp;</div>');
					$("#vioInfo").append('<div class="weui_cells_title"><b>网上自助罚缴链接：</b><a href="http://jtzf.cq.gov.cn/eponline/">http://jtzf.cq.gov.cn/eponline/</a></div>');
				}else{
					$("#vioInfo").append('<div class="weui_cells_title">目前没有违法记录！</div>');
				}
			}else{
				$("#vioInfo").append('<div class="weui_cells_title">目前没有违法记录！</div>');
			}
        }
	});
}
function closeDialog(id){
	$("#"+id).css("display","none");
}
function openDialog(id,content){
	$("#dialogContent").html(content);
	$("#"+id).css("display","block");
}
</script>
</html>