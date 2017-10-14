<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>上报信息管理</title>

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
<%@include file="../common/top.jsp"%>
<div class="sidebar-nav">
    <a href="#dashboard-menu" class="nav-header" data-toggle="collapse"><i class="icon-list-alt"></i>系统菜单</a>
    <ul id="dashboard-menu" class="nav nav-list collapse in">
        <li><a href="${ctx }/activity/console/home"><i class="icon-facetime-video"></i>路况信息管理</a></li>
    </ul>
</div>
   
<div class="content">
       
	<div class="header">
	    <h1 class="page-title">上报路况信息管理</h1>
	</div>
	<!-- 
	<ul class="breadcrumb">
	    <li><a href="index.html">Home</a> <span class="divider">/</span></li>
	    <li class="active">Users</li>
	</ul>
	 -->
	<div class="container-fluid">
	
		<div class="row-fluid">
			
			<div class="span6 input-append pull-right" style="margin-top:10px;">
				<select class="span3" id="repoState" name="repoState">
				  <option value="" selected="selected">--获奖状态--</option>
				  <option value="0">未获奖</option>
				  <option value="1">获奖</option>
			  	</select>
				<input class="span8" id="searcKeyword" type="text" name="searcKeyword"  placeholder="姓名或者电话">
				<button class="btn" type="button" onclick="queryList()">Go!</button>
			</div>
		</div>
		<div class="row-fluid">
			<div class="well">
				<table class="table table-bordered" id="videoTable">
					<thead>
						<tr>
						  <th width="40px">序号</th>
						  <th>姓名</th>
						  <th>电话</th>
						  <th width="300px">描述</th>
						  <th>时间</th>
						  <th>类型</th>
						  <th>状态</th>
						  <th>图片</th>
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



<div id="mask" class="mask">正在登陆中，请稍等...</div> 
</body>

<script type="text/javascript">

function del(id){
	$.confirm({
        text: "您确定要删除选择的数据吗？",
        confirm: function(button) {
        	$.post("${ctx}/console/activity.htm",
       		    {"method":"del","id": id},
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
	var repoState = $("#repoState").val();
	var searcKeyword = $("#searcKeyword").val();
	
	showMask();
	$.post("${ctx}/console/activity.htm",
	    {"method":"queryPageList","repoState": repoState,"searcKeyword": searcKeyword,"pageIndex": pageIndex,"limitCount": limitCount},
	    function(data) {
			hideMask();
	    	//刷新列表 
	    	var content = eval('(' + data + ')');
	    	if("0"==content.code){
	    		var t = $("#videoTable tbody");
	    		t.html("");
	    		$.each(content.data,function(index,obj) { 
	    			t.append("<tr>");
		    		t.append("<td>"+(index+1)+"</td>");
		    		t.append("<td>"+obj.repoUserName+"</td>");
		    		t.append("<td>"+obj.repoUserPhone+"</td>");
		    		t.append("<td style='word-break:break-all; word-wrap:break-word;'>"+obj.repoDetails+"</td>");
		    		t.append("<td>"+obj.repoCreateTimeStr+"</td>");
		    		t.append("<td>"+obj.repoTypeStr+"</td>");
		    		t.append("<td>"+obj.repoStateStr+"</td>");
		    		var imgUrls = obj.repoImgUrl;
		    		var urlHtml = "";
		    		if(imgUrls){
		    			var urls = imgUrls.split(",");
		    			$.each(urls,function(i,u) { 
		    				if(u){
		    					urlHtml += "<a target='_blank' href='${ctx}/upload/activity/"+u+".jpg'><img width='30px'height='30px' src='${ctx}/upload/activity/"+u+".jpg'/></a>";
		    				}
		    			});
		    		}
		    		t.append("<td>"+urlHtml+"</td>");
		    		var repoState = obj.repoState;
		    		if(repoState == 0){
		    			t.append("<td><a href='javascript:editState("+obj.repoId+",1)' title='获奖'><i class='icon-pencil'></i></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:del("+obj.repoId+")' title='删除'><i class='icon-remove'></i></a></td>");
		    		} else {
		    			t.append("<td><a href='javascript:editState("+obj.repoId+",0)' title='取消获奖'><i class='icon-pencil'></i></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:del("+obj.repoId+")' title='删除'><i class='icon-remove'></i></a></td>");
		    		}
		    		t.append("</tr>");
	    		});
	    		
	    		$("#totalCount").html(content.recoredCount);
	    		$("#pageIndex").html(content.pageIndex);
	    		$("#pageCount").html(content.pageCount);
	    		pageIndex = content.pageIndex;
	    		pageCount = content.pageCount;
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
   
   function editState (id ,state){
	   $.confirm({
	        text: "您确定要修改的数据吗？",
	        confirm: function(button) {
	        	$.post("${ctx}/console/activity.htm",
	       		    {"method":"updateState","repoId": id,"repoState":state},
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

</script>
</html>
