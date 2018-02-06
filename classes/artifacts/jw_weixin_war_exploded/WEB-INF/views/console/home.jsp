<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="common/taglibs.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>微电影投票管理系统</title>

<style type="text/css">
.mask {       
	position: absolute; top: 0px; filter: alpha(opacity=60); background-color: #fff;     
	z-index: 1002; left: 0px;     
	opacity:0.5; -moz-opacity:0.5;
	display:none;text-align:center;line-height:550px;
}

.btn{
	-webkit-border-radius: 3px;-moz-border-radius: 3px;border-radius: 3px;
}

input[type="text"]{
	height:30px
}
</style>
 
<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
  <script src="/BlueNileAdmin/lib/html5.js"></script>
<![endif]-->

</head>
<!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
<!--[if IE 7 ]> <body class="ie ie7 "> <![endif]-->
<!--[if IE 8 ]> <body class="ie ie8 "> <![endif]-->
<!--[if IE 9 ]> <body class="ie ie9 "> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> 
<body class=""> 
<!--<![endif]-->
<%@include file="common/top.jsp"%>
<div class="sidebar-nav">
    <a href="#dashboard-menu" class="nav-header" data-toggle="collapse"><i class="icon-list-alt"></i>系统菜单</a>
    <ul id="dashboard-menu" class="nav nav-list collapse in">
        <li><a href="${ctx }/console/home.htm"><i class="icon-facetime-video"></i>视频管理</a></li>
    </ul>
</div>
   
<div class="content">
       
	<div class="header">
	    <h1 class="page-title">视频管理</h1>
	</div>
	<!-- 
	<ul class="breadcrumb">
	    <li><a href="index.html">Home</a> <span class="divider">/</span></li>
	    <li class="active">Users</li>
	</ul>
	 -->
	<div class="container-fluid">
	
		<div class="row-fluid">
			<div class="span6 btn-toolbar">
				<button class="btn btn-primary" data-toggle="modal" data-target="#addOrEditModal"><i class="icon-plus"></i>添加视频</button>
			</div>
			<div class="span6 input-append pull-right" style="margin-top:10px;">
				<select class="span3" id="stateSearch" name="stateSearch">
				  <option value="" selected="selected">--请选择--</option>
				  <option value="0">上架</option>
				  <option value="1">下架</option>
			  	</select>
				<input class="span8" id="nameSearch" type="text" name="nameSearch">
				<button class="btn" type="button" onclick="queryList()">Go!</button>
			</div>
		</div>
		<div class="row-fluid">
			<div class="well">
				<table class="table table-bordered" id="videoTable">
					<thead>
						<tr>
						  <th width="40px">序号</th>
						  <th>编号</th>
						  <th>名称</th>
						  <th>优酷ID</th>
						  <th width="60px">投票数</th>
						  <th width="300px">描述</th>
						  <th>状态</th>
						  <th width="60px">操作</th>
						</tr>
					</thead>
					<tbody>
						
					</tbody>
				</table>
			</div>
     	</div>
     	<div class="row-fluid">
			<div class="span6">
				<span>共有<span id="totalCount">0</span>条记录，当前第<span id="pageIndex">1</span>页，共<span id="pageCount">1</span>页</span>
			</div>
			<div class="span4">
				<div class="btn-toolbar pull-right" style="margin:0px">
					<button class="btn btn-mini" onclick="setPage('home')"><i class="icon-home"></i>首页</button>
					<button class="btn btn-mini" onclick="setPage('before')"><i class="icon-hand-left"></i>上一页</button>
					<button class="btn btn-mini" onclick="setPage('next')"><i class="icon-hand-right"></i>下一页</button>
					<button class="btn btn-mini" onclick="setPage('last')"><i class="icon-inbox"></i>尾页</button>
				</div>
			</div>
			<div class="span2 pull-right" style="margin:0px">
				<span>转到</span>
				<input type="text" id="gotoPageIndex" style="width:20px; height:15px; font-size:12px; border:solid 1px #7aaebd;margin:0px;padding:0px">
				<span>页</span>
				<button class="btn btn-mini" style="margin:0px;" onclick="setPage('goto')"><i class="icon-share-alt"></i>转</button>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="addOrEditModal" tabindex="-111" style="display: none;" role="dialog" aria-labelledby="addOrEditModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="addOrEditModalLabel">添加视频</h4>
      </div>
      <div class="modal-body">
		<form class="form-horizontal" id="addOrEditFrom">
			<input type="hidden" id="videId" name="videId"/>
		  <div class="control-group">
		    <label class="control-label">编号：</label>
		    <div class="controls">
		      <input type="text" id="videCode" name="videCode">
		    </div>
		  </div>
		  
		  <div class="control-group">
		    <label class="control-label">名称：</label>
		    <div class="controls">
		      <input type="text" id="videName" name="videName">
		    </div>
		  </div>
		  
		  <div class="control-group">
		    <label class="control-label">视频ID：</label>
		    <div class="controls">
		      <input type="text" id="videFileId" name="videFileId">
		    </div>
		  </div>
		  <div class="control-group">
		    <label class="control-label">投票数：</label>
		    <div class="controls">
		      <input type="text" id="videVoteNum" name="videVoteNum">
		    </div>
		  </div>
		  <div class="control-group">
		    <label class="control-label">状态：</label>
		    <div class="controls">
		      <select id="videState" name="videState">
				  <option value="0">上架</option>
				  <option value="1">下架</option>
			  </select>
		    </div>
		  </div>
		  <div class="control-group">
		    <label class="control-label">描述：</label>
		    <div class="controls">
		    	<textarea rows="3" id="videDetails" name="videDetails"></textarea>
		    </div>
		  </div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" onclick="save()">保存</button>
      </div>
    </div>
  </div>
</div>

<div id="mask" class="mask">正在登陆中，请稍等...</div> 
</body>

<script type="text/javascript">

function save(){
	var videName = $("#videName").val();
	if (videName == "") {
		alert("名称不能为空，请重新输入！");
        return;
    }
	var videId = $("#videId").val();
	var options = {};
	if(videId == ""){
		options = {
				url : '${ctx}/console/video.htm?method=insert',
				type : 'post',
				dataType : "html",
				success : function(data) {
					var content = eval('(' + data + ')');
					if(content.code == 0){
						alert(content.msg);
						queryList();
						$('#addOrEditModal').modal('hide');
					} else {
						alert(content.msg);
					}
				},
				error :function(data, status, e){
					alert(data+"===>"+status+"===>"+e)
					layer.msg("内部错误，保存失败"+data+"===>"+status+"===>"+e,{icon:1,time:1000}); 
				}
			};
	} else {
		options = {
				url : '${ctx}/console/video.htm?method=edit',
				type : 'post',
				dataType : "html",
				success : function(data) {
					var content = eval('(' + data + ')');
					if(content.code == 0){
						alert(content.msg);
						$("#videName").val("")
						queryList();
						$('#addOrEditModal').modal('hide');
					} else {
						alert(content.msg);
					}
				},
				error :function(data, status, e){
					alert(data+"===>"+status+"===>"+e)
					layer.msg("内部错误，保存失败"+data+"===>"+status+"===>"+e,{icon:1,time:1000}); 
				}
			};
	}
	
	
	$("#addOrEditFrom").ajaxSubmit(options);
}

function initEdit(id){
	$.post("${ctx}/console/video.htm",
	    {"method":"editPage","id": id},
	    function(data) {
			hideMask();
	    	//刷新列表 
	    	var content = eval('(' + data + ')');
	    	if("0"==content.code){
	    		$("#addOrEditModalLabel").html("修改视频");
	    		var obj = content.data;
	    		$("#videId").val(obj.videId);
	    		$("#videCode").val(obj.videCode);
	    		$("#videName").val(obj.videName);
	    		$("#videDetails").val(obj.videDetails);
	    		$("#videVoteNum").val(obj.videVoteNum);
	    		$("#videState").val(obj.videState);
	    		$("#videFileId").val(obj.videFileId);
	    		$('#addOrEditModal').modal('show');
	    	} else {
	    		alert(content.msg);
	    	}
   	});
	
}

function del(id){
	$.confirm({
        text: "您确定要删除选择的数据吗？",
        confirm: function(button) {
        	$.post("${ctx}/console/video.htm",
       		    {"method":"remove","id": id},
       		    function(data) {
       				hideMask();
       		    	//刷新列表 
       		    	var content = eval('(' + data + ')');
       		    	if("0"==content.code){
       		    		alert(content.msg);
       		    		queryList();
       		    	} else {
       		    		alert(content.msg);
       		    	}
       		});
        }
    });
}

var pageIndex = 1;
var pageCount = 1;
var limitCount = 20;

$(function(){
	queryList();
});
function queryList(){
	var stateSearch = $("#stateSearch").val();
	var nameSearch = $("#nameSearch").val();
	
	showMask();
	$.post("${ctx}/console/video.htm",
	    {"method":"queryPageList","stateSearch": stateSearch,"videName": nameSearch,"pageIndex": pageIndex,"limitCount": limitCount},
	    function(data) {
			hideMask();
	    	//刷新列表 
	    	var content = eval('(' + data + ')');
	    	if("0"==content.code){
	    		var t = $("#videoTable tbody");
	    		t.html("");
	    		$.each(content.result,function(index,obj) { 
	    			t.append("<tr>");
		    		t.append("<td>"+(index+1)+"</td>");
		    		t.append("<td>"+obj.videCode+"</td>");
		    		t.append("<td>"+obj.videName+"</td>");
		    		t.append("<td>"+obj.videFileId+"</td>");
		    		t.append("<td>"+obj.videVoteNum+"</td>");
		    		t.append("<td  style='word-break:break-all; word-wrap:break-word;'>"+obj.videDetails+"</td>");
		    		t.append("<td>"+obj.videState+"</td>");
		    		t.append("<td><a href='javascript:initEdit("+obj.videId+")'><i class='icon-pencil'></i></a><a href='javascript:del("+obj.videId+")'><i class='icon-remove'></i></a></td>");
		    		t.append("</tr>");
	    		});
	    		
	    		$("#totalCount").html(content.recoredCount);
	    		$("#pageIndex").html(content.pageIndex);
	    		$("#pageCount").html(content.pageCount);
	    		pageIndex = content.pageIndex;
	    		pageCount = content.pageCount;
	    		$('#addOrEditFrom')[0].reset();
	    		$("#gotoPageIndex").val("");
	    	} else {
	    		alert("msg,"+content.msg);
	    	}
	});
}
	
function setPage(type){
	if("home"==type){//首页
		pageIndex = 1;
	} else if("before" == type){
		pageIndex -= 1;
	} else if("next" == type){
		pageIndex = Number(pageIndex) + 1; 
	} else if("last" == type){
		pageIndex = pageCount;
	} else if("goto" == type){
		pageIndex = $("#gotoPageIndex").val();
	}
	queryList();
}
var checkName=function() {
    document.getElementById("errorMsg").innerHTML ="";
    var name = eval(document.getElementById('userName')).value;
    if (name.length > 20 || name.length < 1)
      document.getElementById("errorMsg").innerHTML = "用户名长度在1-20之间！" ;
}
var checkPassword = function(){
    document.getElementById("errorMsg").innerHTML ="";
    var name = eval(document.getElementById('password')).value;
    if (name.length > 12 || name.length < 6)
      document.getElementById("errorMsg").innerHTML="密码长度在6-12之间！" ;
}


//显示遮罩层    
   function showMask(){     
       $("#mask").css("height",$(document).height());    
       $("#mask").css("width",$(document).width());   
       $("#mask").show();
   }  
   //隐藏遮罩层  
   function hideMask(){     
       $("#mask").hide();     
   }  

</script>
</html>
