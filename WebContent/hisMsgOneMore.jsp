<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    String openid=request.getParameter("openid");
	String bTime=request.getParameter("bTime");
	String eTime=request.getParameter("eTime");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>消息记录</title>
<style type="text/css">
body {
	font-family: "Microsoft YaHei", 微软雅黑, "MicrosoftJhengHei", 华文细黑, STHeiti,
		MingLiu
}
</style>
</head>
<body>
	<div id="msgbox" style="height: 80%" region="center"></div>
</body>
<link rel="stylesheet" type="text/css" href="/css/main.css">
<script type="text/javascript" src="/js/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/js/ckplayer/ckplayer.js"
	charset="utf-8"></script>
<script type="text/javascript" src="/js/ckplayer/offlights.js"></script>
<script type="text/javascript" src="/js/qqface.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	getQQface();
    var h20='<div id="msg" style="text-align:center;">';
    h20+='<img src="/image/clock.jpg" style="vertical-align:middle;"><a href="#" onclick=showHisMsgOneMore();>查看更多消息</a>';
    h20+='</div>';
    $("#msgbox").prepend(h20);
    showHisMsgOneMore();
});
var pageSize=50;
var begin=0;
var headImgUrl="";
var userData=parent.getUserData("<%=openid%>");
var date=new Date();
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
function showHisMsgOneMore(){
	var openid="<%=openid%>";
	var bTime="<%=bTime%>";
	var eTime="<%=eTime%>";
    $.ajax({
		type : "post",
		url : "/csc/getHisMsgOneDay",
		dataType : "json",
		//async: false,
		data : {
			openid:openid,
			bTime:bTime,
			eTime:eTime,
			begin:begin,
			end:begin+pageSize
		},
		success : function(data) {
			if(data.length>0){
				begin+=10;
				for(var i=0;i<data.length;i++){
					if(data[i].msgId==null){
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
						rightMessage(data[i]);
					}else{
						leftMessage(data[i]);
					}
				}
				if(data.length<pageSize)$("#msg").html("暂无更多消息");
				$("#msgbox").prepend($("#msg"));
			}else{
				$("#msg").html("暂无更多消息");
			}
		}
    });
}
function leftMessage(data){
	var msgdate=new Date(data.createTime).Format("yyyy-MM-dd hh:mm:ss");
	var html = "";
	html += '<div class="list-left">';
	html += '<table style="padding:0px;margin:0px;">';
	html += '<tr>';
	html += '<td rowspan="2" width="50px" valign="top"><img style="height:52px;" src="'+userData.headimgurl+'"/></td>';
	html += '<td style="width:100%;">';
	html += '<span style="float:left;margin-left:15px;"><font>'+userData.nickname+'&nbsp;&nbsp;'+msgdate+'</font></span>';
	html += '</td>';
	html += '</tr>';
	//html += '<span class="time">13141455</span>';
	html += '<tr>';
	html += '<td style="width:100%;">';
	html += '<span style="float:left;margin-top:10px;"><img src="/image/img_left.jpg"></img></span>';
	if ("text" == data.msgType) {
		html += '<div class="bj">';
		html += '<span class="msgtext"><font>' + replaceQQFace(data.content) + '</font></span>';
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
	html += '</div>';
	html += '</td>';
	html += '</tr>';
	html += '</table>';
	html += '</div>';
	$("#msgbox").prepend(html);
	$("#msgbox").scrollTop(0);
	if ("shortvideo" == data.msgType||"video" == data.msgType){
		playMedia("ck"+data.msgId,data.videoUrl);
	}
}
function rightMessage(data){
	var msgdate=new Date(data.createTime).Format("yyyy-MM-dd hh:mm:ss");
	var html = "";
	if ("autotext" == data.msgType) {
		html=getSystextHtml(data);
	}else{
		html += '<div class="list-right">';
		html += '<table style="padding:0px;margin:0px;">';
		html += '<tr>';
		html += '<td style="width:100%;">';
		html += '<span style="float:right;margin-right:15px;"><font>'+msgdate+'&nbsp;&nbsp;'+adminname+ '</font></span>';
		html += '</td>';
		html += '<td rowspan="2" width="50px" valign="top"><img src="/image/img_admin.jpg"/></td>';
		html += '</tr>';
		//html += '<span class="time">13141455</span>';
		html += '<tr>';
		html += '<td style="width:100%;">';
		html += '<span style="float:right;margin-top:10px;"><img src="/image/img_right.jpg"></img></span>';
		if ("text" == data.msgType) {
			html += '<div class="bj">';
			html += '<span class="msgtext"><font>' + replaceQQFace(data.msgContent) + '</font></span>';
		}
		else if ("image" == data.msgType) {
			html += '<div class="bjimg">';
			html += "<img src=" + data.imageUrl + " width='150px'></img>";
		}
		else if ("voice" == data.msgType) {
			html += '<div class="bj">';
			html += "<embed src=" + data.voiceUrl + " autostart=false hidden=no units='pixels' width=300 height=44></embed>";
	    }
		html += '</div>';
		if(data.isSuccess==1){
			html+="<img style='cursor:hand;float:right;' title='发送失败' src='/image/send_again.png'>";
		}
		html += '</td>';
		html += '</tr>';
		html += '</table>';
		html += '</div>';
	}
	$("#msgbox").prepend(html);
	$("#msgbox").scrollTop(0);
}
function getSystextHtml(data){
	var msgdate=new Date(data.createTime).Format("yyyy-MM-dd hh:mm:ss");
	var html = "";
	html += '<div class="list-right">';
	html += '<table style="padding:0px;margin:0px;">';
	html += '<tr>';
	html += '<td style="width:100%;">';
	html += '<span style="float:right;margin-right:15px;"><font>'+msgdate+'&nbsp;&nbsp;系统自动</font></span>';
	html += '</td>';
	html += '<td rowspan="2" width="50px" valign="top"><img src="/image/img_system.jpg"/></td>';
	html += '</tr>';
	html += '<tr>';
	html += '<td style="width:100%;">';
	html += '<span style="float:right;margin-top:10px;"><img src="/image/img_aright.jpg"></img></span>';
	html += '<div class="autobj"><font>' + data.content+ '</font></div>';
	html += '</td>';
	html += '</tr>';
	html += '</table>';
	html += '</div>';
	return html;
}
function getLocalTime(nS) {     
    return new Date(parseInt(nS)).toLocaleString();
    //.replace(/年|月/g, "-").replace(/日/g, " ");
}   
</script>
</html>