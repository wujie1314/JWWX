<%@page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta content="telephone=no" name="format-detection">
<title>2018年重庆市交通委员会运行监测与应急调度系统综合演练观摩</title>
<link rel="stylesheet" href="${ctx}/css/meeting/pcs.css">
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript " src="/js/others/layer/mobile/layer.js"></script>
</head>
<style>
li div{
	margin: 15px 0;
}

li{
	margin-right: 10%;
}
span{
	padding: 8px 10px;
    display: inline-block;
}
.layui-m-layercont{
	height: 100%;
}
</style>
<body>
<div class="banner"><img src="${ctx}/image/meeting/banner_head.jpg?timeStamp=201805031352"/></div>
<div>	
	<ul>
       	<li onclick="intrJiaotousi()"><div class="li_cont bac2"><span>重庆市交通投资有限公司</span></div></li>
       	<li onclick="intrRongyun()"><div class="li_cont bac6" style="background: #27ad6c"><span>重庆市融运交通科技有限公司</span></div></li>
      	<li onclick="intrLiantong()"><div class="li_cont bac3"><span>重庆联通</span></div></li>
      	<li onclick="intrTongfan()"><div class="li_cont bac1"><span>北京同方软件股份有限公司</span></div></li>
      	<li onclick="intrWangshan()"><div class="li_cont bac4"><span>重庆旺山实业有限公司</span></div></li>
        <li onclick="intrJiaokai()"><div class="li_cont bac5"><span>重庆交凯信息技术有限公司</span></div></li>
      	<li onclick="intrWeixin()"><div class="li_cont bac6"><span>深圳惟新科技股份有限公司</span></div></li>
    </ul>
</div>
<script>
var pageii = 0;
function intrJiaotousi(){
	var html = '<div style="width:100%;height:100%;overflow:auto" onclick="closeBigImg()"><img width="100%" id="huodongtu" src="${ctx}/image/meeting/jiaotousi.jpg?timeStamp=201805031231"/><div>';
	pageii = layer.open({
		  type: 1
		  ,content: html
		  ,anim: 'up'
		  ,style: 'position:fixed; left:0; top:0; width:100%; height:100%; border: none; -webkit-animation-duration: .5s; animation-duration: .5s;'
		});
}

function intrWangshan(){
	var html = '<div style="width:100%;height:100%;overflow:auto" onclick="closeBigImg()"><img width="100%" id="huodongtu" src="${ctx}/image/meeting/wangshan.jpg?timeStamp=201805031231"/><div>';
	pageii = layer.open({
		  type: 1
		  ,content: html
		  ,anim: 'up'
		  ,style: 'position:fixed; left:0; top:0; width:100%; height:100%; border: none; -webkit-animation-duration: .5s; animation-duration: .5s;'
		});
}

function intrLiantong(){
	var html = '<div style="width:100%;height:100%;overflow:auto" onclick="closeBigImg()"><img width="100%" id="huodongtu" src="${ctx}/image/meeting/liantong.jpg?timeStamp=201805031231"/><div>';
	pageii = layer.open({
		  type: 1
		  ,content: html
		  ,anim: 'up'
		  ,style: 'position:fixed; left:0; top:0; width:100%; height:100%; border: none; -webkit-animation-duration: .5s; animation-duration: .5s;'
		});
}

function intrRongyun(){
	var html = '<div style="width:100%;height:100%;overflow:auto" onclick="closeBigImg()">'
	 + '<img width="100%" id="huodongtu" src="${ctx}/image/meeting/rongyun1.jpg?timeStamp=201805031231"/>'
	 + '<img width="100%" id="huodongtu" src="${ctx}/image/meeting/rongyun2.jpg?timeStamp=201805031231"/>'
	 + '<img width="100%" id="huodongtu" src="${ctx}/image/meeting/rongyun3.jpg?timeStamp=201805031231"/>'
	 + '<img width="100%" id="huodongtu" src="${ctx}/image/meeting/rongyun4.jpg?timeStamp=201805031231"/>'
	 + '<img width="100%" id="huodongtu" src="${ctx}/image/meeting/rongyun5.jpg?timeStamp=201805031231"/>'
	 + '<div>';
	pageii = layer.open({
		  type: 1
		  ,content: html
		  ,anim: 'up'
		  ,style: 'position:fixed; left:0; top:0; width:100%; height:100%; border: none; -webkit-animation-duration: .5s; animation-duration: .5s;'
		});
}

function intrJiaokai(){
	var html = '<div style="width:100%;height:100%;overflow:auto" onclick="closeBigImg()">'
	 + '<img width="100%" id="huodongtu" src="${ctx}/image/meeting/jiaokai1.jpg?timeStamp=201805031231"/>'
	 + '<img width="100%" id="huodongtu" src="${ctx}/image/meeting/jiaokai2.jpg?timeStamp=201805031231"/>'
	 + '<img width="100%" id="huodongtu" src="${ctx}/image/meeting/jiaokai3.jpg?timeStamp=201805031231"/>'
	 + '<img width="100%" id="huodongtu" src="${ctx}/image/meeting/jiaokai4.jpg?timeStamp=201805031231"/>'
	 + '<img width="100%" id="huodongtu" src="${ctx}/image/meeting/jiaokai5.jpg?timeStamp=201805031231"/>'
	 + '<div>';
	pageii = layer.open({
		  type: 1
		  ,content: html
		  ,anim: 'up'
		  ,style: 'position:fixed; left:0; top:0; width:100%; height:100%; border: none; -webkit-animation-duration: .5s; animation-duration: .5s;'
		});
}

function intrWeixin(){
	var html = '<div style="width:100%;height:100%;overflow:auto" onclick="closeBigImg()">'
	 + '<img width="100%" id="huodongtu" src="${ctx}/image/meeting/weixin1.jpg?timeStamp=201805031231"/>'
	 + '<img width="100%" id="huodongtu" src="${ctx}/image/meeting/weixin2.jpg?timeStamp=201805031231"/>'
	 + '<img width="100%" id="huodongtu" src="${ctx}/image/meeting/weixin3.jpg?timeStamp=201805031231"/>'
	 + '<div>';
	pageii = layer.open({
		  type: 1
		  ,content: html
		  ,anim: 'up'
		  ,style: 'position:fixed; left:0; top:0; width:100%; height:100%; border: none; -webkit-animation-duration: .5s; animation-duration: .5s;'
		});
}

function intrTongfan(){
	var html = '<div style="width:100%;height:100%;overflow:auto" onclick="closeBigImg()">'
	 + '<img width="100%" id="huodongtu" src="${ctx}/image/meeting/tongfang1.jpg?timeStamp=201805031231"/>'
	 + '<img width="100%" id="huodongtu" src="${ctx}/image/meeting/tongfang2.jpg?timeStamp=201805031231"/>'
	 + '<div>';
	pageii = layer.open({
		  type: 1
		  ,content: html
		  ,anim: 'up'
		  ,style: 'position:fixed; left:0; top:0; width:100%; height:100%; border: none; -webkit-animation-duration: .5s; animation-duration: .5s;'
		});
}

function closeBigImg(){
	layer.close(pageii);
}
</script>
</body>
</html>
