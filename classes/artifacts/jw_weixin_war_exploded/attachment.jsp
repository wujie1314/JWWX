<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="/css/main.css">
<script type="text/javascript" src="/js/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/js/ckplayer/ckplayer.js"
	charset="utf-8"></script>
<script type="text/javascript" src="/js/ckplayer/offlights.js"></script>
<script type="text/javascript" src="/js/qqface.js"></script>
<%
    String workUnitId=request.getParameter("workUnitId");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>重庆市交通服务热线附件</title>
<style>
.button_a {
	Width: 200px;
	Padding: 2px;
	Line-height: 30px;
	Background-color: #3385FF;
	Border: 1px solid #3385FF;
	Color: #FFFFFF;
	Text-decoration: none;
	Text-align: center;
}
</style>
</head>
<body>
	<div style="text-align: center;" id="onlyAttachment">
		<a class="button_a" href="#" onclick="showAttachmentOnly();">显示附件</a>
	</div>
	<div style="text-align: center; dispaly: none;" id="allAttachment">
		<a class="button_a" href="#" onclick="showAttachmentAll();">显示全部</a>
	</div>
	<div id="msgbox" region="center"></div>
</body>
<script type="text/javascript">
	var headImgUrl="";
	var nickname="";
	var adminname="";
$(document).ready(function(){
	$("#onlyAttachment").show();
	$("#allAttachment").hide();
	getQQface();
    $.ajax({
		type : "post",
		url : "/csc/getAttachment",
		dataType : "json",
		async: false,
		data : {
			workUnitId:'<%=workUnitId%>'
		},
		success : function(data) {
			for(var i=0;i<data.length;i++){
				if ("autotext" == data[i].msgType) {
					getSystextHtml(data[i]);
				}else if(data[i].msgId==null){
					if(adminname==""){
						$.ajax({
							type : "post",
							url : "/csc/getCsUserInfoById/"+data[i].fromUser,
							dataType : "json",
							async: false,
							data : {
							},
							success : function(data1) {
								adminname=data1.userName;
							}
						});
					}
					rightMessage(data[i]);
				}else{
					if(nickname==""){
						$.ajax({
							type : "post",
							url : "/csc/getUserInfo",
							dataType : "json",
							async: false,
							data : {
								openId:data[i].fromUserName
							},
							success : function(data2) {
								//data2= $.parseJSON(data2);
								nickname=data2.nickname;
								headImgUrl=data2.headImgUrl;
							}
						});
					}
					leftMessage(data[i]);
				}
			}
        }
	});
});
function leftMessage(data){
	var msgdate=new Date(data.createTime).Format("yyyy-MM-dd hh:mm:ss");
	var html = "";
	html += '<div class="list-left">';
	if(data.workUnitNum!=""&&data.workUnitNum!=null){
		html += '<input type="hidden" name="isAttachment" value="1">';
	}else{
		html += '<input type="hidden" name="isAttachment" value="0">';
	}
	html += '<table style="padding:0px;margin:0px;">';
	html += '<tr>';
	html += '<td rowspan="2" width="50px" valign="top"><img src="/image/img_user.png"/></td>';
	html += '<td style="width:100%;">';
	html += '<span style="float:left;margin-left:15px;"><font size="2">'+ nickname +'&nbsp;&nbsp;'+msgdate+'</font></span>';
	html += '</td>';
	html += '</tr>';
	//html += '<span class="time">13141455</span>';
	html += '<tr>';
	html += '<td style="width:100%;">';
	html += '<span style="float:left;margin-top:10px;"><img src="/image/img_left.jpg"></img></span>';
	if ("text" == data.msgType) {
		html += '<div class="bj">';
		html += '<span class="msgtext"><font size="2">' + replaceQQFace(data.content) + '</font></span>';
	}
	else if ("image" == data.msgType) {
		html += '<div class="bjimg"">';
		html += "<img src=" + data.imageUrl + " width='150px'></img>";
	}
	else if ("voice" == data.msgType) {
		html += '<div class="bj">';
		html += "<embed src=" + data.voiceUrl + " windowlessVideo=1 autostart=false hidden=no units='pixels' width=300 height=44></embed>";
    }
	else if ("shortvideo" == data.msgType||"video" == data.msgType) {
		html += '<div class="bj">';
		//html += "<embed src=" + data.videoUrl + " windowlessVideo=1 autoplay=false hidden=no units='pixels' width=300 height=300></embed>";
		html += '<div id="ck'+data.msgId+'"></div>';
	}
	if(data.workUnitNum!=""&&data.workUnitNum!=null)html += "<img style='height:40px;position:absolute;bottom:0px;right:0px;z-index:99;' src='/image/work.png'></img>";
	html += '</div>';
	html += '</td>';
	html += '</tr>';
	html += '</table>';
	html += '</div>';
	$("#msgbox").append(html);
	if ("shortvideo" == data.msgType||"video" == data.msgType){
		playMedia("ck"+data.msgId,data.videoUrl);
	}
}
function rightMessage(data){
	var msgdate=new Date(data.createTime).Format("yyyy-MM-dd hh:mm:ss");
	var html = "";
	html += '<div class="list-right">';
	if(data.workUnitId!=""&&data.workUnitId!=null){
		html += '<input type="hidden" name="isAttachment" value="1">';
	}else{
		html += '<input type="hidden" name="isAttachment" value="0">';
	}
	html += '<table style="padding:0px;margin:0px;">';
	html += '<tr>';
	html += '<td style="width:100%;">';
	html += '<span style="float:right;margin-right:15px;"><font size="2">'+msgdate+'&nbsp;&nbsp;'+adminname + '</font></span>';
	html += '</td>';
	html += '<td rowspan="2" width="50px" valign="top"><img src="/image/img_admin.jpg"/></td>';
	html += '</tr>';
	//html += '<span class="time">13141455</span>';
	html += '<tr>';
	html += '<td style="width:100%;">';
	html += '<span style="float:right;margin-top:10px;"><img src="/image/img_right.jpg"></img></span>';
	if ("text" == data.msgType) {
		html += '<div class="bj">';
		html += '<span class="msgtext"><font size="2">' + replaceQQFace(data.msgContent) + '</font></span>';
	}
	else if ("image" == data.msgType) {
		html += '<div class="bjimg">';
		html += "<img src=" + data.imageUrl + " width='150px'></img>";
	}
	else if ("voice" == data.msgType) {
		html += '<div class="bj">';
		html += "<embed src=" + data.voiceUrl + " autostart=false hidden=no units='pixels' width=300 height=44></embed>";
    }
	if(data.workUnitId!=""&&data.workUnitId!=null)html += "<img style='height:40px;position:absolute;bottom:0px;right:0px;z-index:99;' src='/image/work.png'></img>";
	html += '</div>';
	if(data.isSuccess==1){
		html+="<img style='cursor:hand;float:right;' title='发送失败' src='/image/send_again.png'>";
	}html += '</td>';
	html += '</tr>';
	html += '</table>';
	html += '</div>';
	$("#msgbox").append(html);
}
function getSystextHtml(data){
	var msgdate=new Date(data.createTime).Format("yyyy-MM-dd hh:mm:ss");
	var html = "";
	html += '<div class="list-right">';
	html += '<input type="hidden" name="isAttachment" value="0">';
	html += '<table style="padding:0px;margin:0px;">';
	html += '<tr>';
	html += '<td style="width:100%;">';
	html += '<span style="float:right;margin-right:15px;"><font size="2">'+msgdate+'&nbsp;&nbsp;系统自动</font></span>';
	html += '</td>';
	html += '<td rowspan="2" width="50px" valign="top"><img src="/image/img_system.jpg"/></td>';
	html += '</tr>';
	html += '<tr>';
	html += '<td style="width:100%;">';
	html += '<span style="float:right;margin-top:10px;"><img src="/image/img_aright.jpg"></img></span>';
	html += '<div class="autobj"><font size="2">' + data.content+ '</font></div>';
	html += '</td>';
	html += '</tr>';
	html += '</table>';
	html += '</div>';
	$("#msgbox").append(html);
}
function playMedia(id,path){
    var flashvars={
        f:path,
        c:0,
        b:1
    };
    var params={bgcolor:'#FFF',allowFullScreen:true,allowScriptAccess:'always',wmode:'transparent'};
    CKobject.embedSWF('/js/ckplayer/ckplayer.swf',id,'ckplayer_a1','300','300',flashvars,params); 
} 

Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
//只显示附件
function showAttachmentOnly(){
	$("#onlyAttachment").hide();
	$("#allAttachment").show();
	$("input[name='isAttachment']").each(function(){
		if($(this).val()!=1)$(this).parent().hide();
	});
}
function showAttachmentAll(){
	$("#onlyAttachment").show();
	$("#allAttachment").hide();
	$("input[name='isAttachment']").each(function(){
		if($(this).val()!=1)$(this).parent().show();
	});
}
</script>
</html>