<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String openId=request.getParameter("openId");
	String userId=request.getParameter("userId");
	String deviceNo=request.getParameter("deviceNo");
	String userPhone=request.getParameter("userPhone");
%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>曝光台信息</title>

    <!-- Bootstrap -->
    <link href="../baoguangtai/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body style="padding:10px">
	<div class="container-fluid">
		<p></p><p></p>
		<div class="row" >
			<div class="col-xs-12">
				<div class="input-group">
					<input type="text" class="form-control" placeholder="搜索...">
					<span class="input-group-btn">
						<button class="btn btn-info" type="button"> GO </button>
					</span>
				</div>
			</div>
		</div>
		<p style="border-top: 1px solid #DCDDDD;margin: 10px 0px;"></p>
	</div>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="../js/jquery/jquery-1.7.2.min.js"></script>
    <!--<script src="js/jquery-3.2.0.min.js"></script>-->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../baoguangtai/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script>
	var comments=new Array();
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
				for(var i=0;i<commentType.length;i++){
					comments[commentType[i].id]=commentType[i].name;
				}
			}
		});
		$.ajax({
			type : "post",
			url : "../csc/getViolationReportList",
			dataType : "json",
			async: false,
			data : {
				pageIndex:1,
				pageSize:10
			},
			success : function(data) {
				var success=data.errorMsg.errorMsg;
				if(success!="success"){
					
				}else{
					var v=data.violationReport;
					for(var i=0;i<v.length;i++){
						var html="";
						html+='<div class="row" onclick=showDetail("'+v[i].id+'","'+v[i].batchNo+'","'+v[i].topCommentTypeIds+'");>';
						html+='<div class="col-xs-8">';
						html+='<p style="color:rgb(252,108,43);font-size:18px">'+v[i].plateNumbers+'</p>';
						if(v[i].startFeesStationName==""){
							html+='<p style="color:rgb(79,79,79);font-size:14px">'+v[i].reportAddress+'</p>';
						}else{
							html+='<p style="color:rgb(79,79,79);font-size:14px">'+v[i].violationRoadSectionCode+v[i].violationRoadSectionName+' '+v[i].startFeesStationName+'-'+v[i].endFeesStationName+'</p>';
						}
						html+='<p>';
						if(typeof(v[i].topCommentTypeIds)!="undefined"&&v[i].topCommentTypeIds!=""){
							var n=v[i].topCommentTypeIds.split(",");
							for(var j=0;j<n.length;j++){
								html+='<button type="button" class="btn btn-info btn-xs">'+comments[n[j]]+'</button>&nbsp';
							}
						}else{
							html+='<button type="button" class="btn btn-info btn-xs">暂无评论</button>';
						}
						html+='</p>';
						var viewCnt=0;
						if(typeof(v[i].viewCnt)!="undefined")viewCnt=v[i].viewCnt;
						html+='<p style="color:rgb(159,158,158);font-size:12px">'+viewCnt+'次浏览量 '+v[i].reportTime.substring(0,10)+'</p>';
						html+='</div>';
						html+='<div class="col-xs-4">';
						html+='<img src="'+v[i].reportFile[0].thumbnailPath+'" style="width:156px;height:104px;float:right" alt="'+v[i].reportInfo+'">';
						html+='</div>';
						html+='</div>';
						html+='<p style="border-top: 1px solid #DCDDDD;margin: 10px 0px;"></p>';
						$(".container-fluid").append(html);
					}
				}
			}
		});
	});
	function showDetail(id,batchNo,topCommentTypeIds){
		var platform="app";
		var userId="<%=userId%>";
		var openId="<%=openId%>";
		var deviceNo="<%=deviceNo%>";
		var userPhone="<%=userPhone%>";
		if(userId!=0)platform="weixin";
		$.ajax({
			type : "post",
			url : "../csc/addViolationReportView",
			dataType : "json",
			async: false,
			data : {
				id:id,
		    	userPhone:userPhone,
		    	batchNo:batchNo,
				userId:userId,
		    	openId:openId,
		    	deviceNo:deviceNo,
		    	platform:platform
			},
			success : function(data) {
				window.location.href="details.jsp?openId="+openId+"&userPhone="+userPhone+"&userId="+userId+"&topCommentTypeIds="+topCommentTypeIds+"&id="+id+"&batchNo="+batchNo;
			}
		});
	}
	</script>
  </body>
</html>