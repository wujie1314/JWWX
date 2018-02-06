<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String openId=request.getParameter("openId");
	String userId=request.getParameter("userId");
	String userPhone=request.getParameter("userPhone");
	String id=request.getParameter("id");
	String batchNo=request.getParameter("batchNo");
	String topCommentTypeIds=request.getParameter("topCommentTypeIds");
%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>曝光台详情</title>

    <!-- Bootstrap -->
    <link href="../baoguangtai/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="../css/weui-master/dist/style/weui.min.css" />
	<link rel="stylesheet" href="../css/weui-master/dist/example/example.css" />

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body style="padding:10px">
	<div class="container-fluid">
		<h2 style="color:rgb(79,79,79);font-size:18px">上传图片或者视频</h2>
		<p style="border-top: 2px solid rgb(252,108,43);margin: 0px 0px 0px 0px;width:150px"></p>
		<p style="border-top: 1px solid #DCDDDD;margin: 14px 0px 6px 0px;" id="fileArea"></p>
		
		<div class="row">
			<div class="col-xs-4" style="padding-right:0px">
				<p style="color:rgb(79,79,79);font-size:14px;">举报车牌号</p>
			</div>
			<div class="col-xs-8">
				<p style="color:rgb(79,79,79);font-size:14px"><font id="plateNumbers"></font></p>
			</div>
		</div>
		<p style="border-top: 1px solid #DCDDDD;margin: 0px 0px 6px 0px;"></p>
		<div class="row" >
			<div class="col-xs-4" style="padding-right:0px">
				<p style="color:rgb(79,79,79);font-size:14px;">违规事由</p>
			</div>
			<div class="col-xs-8">
				<p style="color:rgb(79,79,79);font-size:14px"><font id="violationType"></font></p>
			</div>
		</div>
		<p style="border-top: 5px solid #DCDDDD;margin: 0px 0px 6px 0px;"></p>
	
		<h2 style="color:rgb(79,79,79);font-size:18px">举报信息</h2>
		<p style="border-top: 2px solid rgb(252,108,43);margin: 0px 0px 0px 0px;width:150px"></p>
		<p style="border-top: 1px solid #DCDDDD;margin: 0px 0px 10px 0px;"></p>
		<div class="row" >
			<div class="col-xs-4" style="padding-right:0px">
				<p style="color:rgb(79,79,79);font-size:14px;">违规时间</p>
			</div>
			<div class="col-xs-8">
				<p style="color:rgb(79,79,79);font-size:14px"><font id="violationTime"></font></p>
			</div>
		</div>
		<p style="border-top: 1px solid #DCDDDD;margin: 0px 0px 6px 0px;"></p>
		<div class="row" >
			<div class="col-xs-4" style="padding-right:0px">
				<p style="color:rgb(79,79,79);font-size:14px;">违规路段</p>
			</div>
			<div class="col-xs-8">
				<p style="color:rgb(79,79,79);font-size:14px"><img style="width:20px;" src="images/ico_ld.png"><font id="violationRoadSectionCodeName"></font></p>
			</div>
		</div>
		<p style="border-top: 1px solid #DCDDDD;margin: 0px 0px 6px 0px;"></p>
		<div class="row" >
			<div class="col-xs-4" style="padding-right:0px">
			</div>
			<div class="col-xs-8">
				<p style="color:rgb(79,79,79);font-size:14px"><img style="width:20px;" src="images/ico_qd.png"><font id="startFeesStationName"></font></p>
			</div>
		</div>
		<p style="border-top: 1px solid #DCDDDD;margin: 0px 0px 6px 0px;"></p>
		<div class="row" >
			<div class="col-xs-4" style="padding-right:0px">
			</div>
			<div class="col-xs-8">
				<p style="color:rgb(79,79,79);font-size:14px"><img style="width:20px;" src="images/ico_zd.png"><font id="endFeesStationName"></font></p>
			</div>
		</div>
		<p style="border-top: 1px solid #DCDDDD;margin: 0px 0px 6px 0px;"></p>
		
		<h2 style="color:rgb(79,79,79);font-size:18px">热门评论</h2>
		<p style="border-top: 2px solid rgb(252,108,43);margin: 0px 0px 0px 0px;width:150px"></p>
		<p style="border-top: 1px solid #DCDDDD;margin: 0px 0px 14px 0px;"></p>
		<div class="row">
			<div class="col-xs-3 col-sm-3" style="padding-right:0px">
			<span class="label label-default" style="font-weight: 300;background-color:#fff;border:1px solid rgb(79,147,224);
				font-size:15px;color:rgb(79,147,224);border-radius: 1.25em;"><font id="commentType0">暂无评论</font></span>
			</div>
			<div class="col-xs-3 col-sm-3" style="padding-right:0px" id="commentTypes1">
			<span class="label label-default" style="font-weight: 300;background-color:#fff;border:1px solid rgb(79,147,224);
				font-size:15px;color:rgb(79,147,224);border-radius: 1.25em; "><font id="commentType1">暂无评论</font></span>
			</div>
		</div>
		<p style="border-top: 5px solid #DCDDDD;margin: 14px 0px 6px 0px;"></p>
		<h2 style="color:rgb(79,79,79);font-size:18px">我来说一句</h2>
		<p style="border-top: 2px solid rgb(252,108,43);margin: 0px 0px 0px 0px;width:150px"></p>
		
		<button class="btn btn-info btn-block btn-lg" onclick="toSubmit();" type="submit" id="commentArea">提交评论</button>
	</div>
	<div class="weui_dialog_alert" id="errorDialog" style="display: none;">
		<div class="weui_mask"></div>
		<div class="weui_dialog" id="weui_dialog_error">
			<div class="weui_dialog_hd">
				<strong class="weui_dialog_title">提示信息</strong>
			</div>
			<div class="weui_dialog_bd" id="errorMsg"></div>
			<div class="weui_dialog_ft">
				<a href="javascript:;" class="weui_btn_dialog primary"
					onclick="offErrorDialog()">确定</a>
			</div>
		</div>
	</div>
  </body>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="../js/jquery/jquery-1.7.2.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../baoguangtai/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/ckplayer/ckplayer.js" charset="utf-8"></script>
<script type="text/javascript" src="../js/ckplayer/offlights.js"></script>
<script type="text/javascript">
var comments=new Array();
var violations=new Array();
$(document).ready(function(){
	$.ajax({
		type : "post",
		url : "../csc/getViolationReportBaseData",
		dataType : "json",
		async: false,
		data : {
		},
		success : function(data) {
			var commentType=data.commentType;
			var html="";
			for(var i=0;i<commentType.length;i++){
				comments[commentType[i].id]=commentType[i].name;
				if(i%3==0){
					html+='<p style="border-top: 1px solid #DCDDDD;margin: 0px 0px 14px 0px;"></p>';
					html+='<p>';
				}
				html+='<span id="comment'+commentType[i].id+'" onclick=addComment(this,'+commentType[i].id+'); class="label label-default" style="font-weight: 400;background-color:#ffffff;border:1px solid #DCDDDD;';
				html+='font-size:15px;color:#DCDDDD;border-radius: 1.25em; margin-left:15px">'+commentType[i].name+'</span>';
				if(i%3==2){
					html+='</p>';
				}	
			}
			html+='<p style="border-top: 1px solid #DCDDDD;margin: 0px 0px 14px 0px;"></p>';
			$("#commentArea").before(html);
			var violationType=data.violationType;
			for(var i=0;i<violationType.length;i++){
				violations[violationType[i].id]=violationType[i].name;
			}
		}
	});
	$.ajax({
		type : "post",
		url : "../csc/getViolationReportByBatchNo",
		dataType : "json",
		async: false,
		data : {
	    	batchNo:"<%=batchNo%>",
	    	id:"<%=id%>"
		},
		success : function(data) {
			if(data.errorMsg.errorMsg != 'success'){
				$("#errorMsg").html(data.errorMsg.errorMsg);
				$("#errorDialog").css("display", "block");
			}
			$("#violationTime").html(data.violationTime.substring(0,10));
			$("#violationRoadSectionCodeName").html(data.violationRoadSectionCode+data.violationRoadSectionName);
			$("#startFeesStationName").html(data.startFeesStationName);
			$("#endFeesStationName").html(data.endFeesStationName);
			$("#plateNumbers").html(data.plateNumbers); 
			$("#violationType").html(violations[data.violationTypeId]);
			var topCommentTypeIds="<%=topCommentTypeIds%>";
			$("#commentTypes1").hide();
			if(topCommentTypeIds!=""){
				var n=topCommentTypeIds.split(",");
				for(var j=0;j<n.length;j++){
					if(j==1)$("#commentTypes1").show();
					$("#commentType"+j).html(comments[n[j]]);
				}
			}
			var reportFile=data.reportFile;
			if(typeof(reportFile)!="undefined"){
				for(var i=0;i<reportFile.length;i++){
					var html="";
					if(reportFile[i].mediaType=="01"){
						html+='<img src="'+reportFile[i].path+'" style="width:154px;height:116px" class="img-thumbnail">';
					}else if(reportFile[i].mediaType=="02"){
						html+='<div id="ck'+i+'"><embed src="'+reportFile[i].path+'" autostart="false"></embed></div>';
					}
					$("#fileArea").before(html);
				}
			}
		}
	});
});
function playMedia(id, path) {
	var flashvars = {
		f : path,
		c : 0,
		b : 1
	};
	var params = {
		bgcolor : '#FFF',
		allowFullScreen : true,
		allowScriptAccess : 'always',
		wmode : 'transparent'
	};
	CKobject.embedSWF('../js/ckplayer/ckplayer.swf', id, 'ckplayer_a1',
			'300', '300', flashvars, params);
}
function addComment(obj,id){
	var color=getColor(obj);
	if(color=="#ffffff"){
		$(obj).css("background-color","#2828ff");
	}else{
		$(obj).css("background-color","#ffffff");
	}
}
function getColor(obj){
	var rgb=$(obj).css("background-color");
	if(!$.browser.msie){ 
		rgb = rgb.match(/^rgb\((\d+),\s*(\d+),\s*(\d+)\)$/); 
		function hex(x) { 
			return ("0" + parseInt(x).toString(16)).slice(-2); 
		} 
		rgb= "#" + hex(rgb[1]) + hex(rgb[2]) + hex(rgb[3]); 
	} 
	return rgb;
}
function toSubmit(){
	var commentTypeIds="";
	$("span[id^='comment']").each(function(){
		if(getColor(this)=="#2828ff"){
			commentTypeIds+=$(this).attr("id").replace("comment","")+","
		}
	});
	if(commentTypeIds.length>0)commentTypeIds=commentTypeIds.substring(0,commentTypeIds.length-1);
	if(commentTypeIds.length==0){
		$("#errorMsg").html("请选择评论类容！");
		$("#errorDialog").css("display", "block");
	}else{
		$.ajax({
			type : "post",
			url : "../csc/addViolationReportComment",
			dataType : "json",
			async: false,
			data : {
		    	batchNo:"<%=batchNo%>",
		    	id:"<%=id%>",
				userId:"<%=userId%>",
				userPhone:"<%=userPhone%>",
		    	openId:"<%=openId%>",
		    	commentTypeIds:commentTypeIds
			},
			success : function(data) {
				$("#errorMsg").html("评论成功！");
				$("#errorDialog").css("display", "block");
			}
		});
	}
}
function offErrorDialog(){
	$("#errorDialog").css("display", "none");
}
</script>
</html>