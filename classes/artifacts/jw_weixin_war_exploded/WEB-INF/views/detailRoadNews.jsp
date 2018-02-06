<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=0">
<link rel="stylesheet" href="/css/weui-master/dist/style/weui.css" />
<link rel="stylesheet" href="/css/weui-master/dist/example/example.css" />
<title>路况详情</title>
</head>
<body>
	<div class="page" id="opretor">
		<div class="weui_cells_title">路况详情</div>
		<div class="weui_cells weui_cells_form">
			<div class="weui_cell">
				<div class="weui_cell_hd">
					<label class="weui_label">标题</label>
				</div>
				<div class="weui_cell_bd weui_cell_primary">${wsEntity.title }
				</div>
			</div>
			<div class="weui_cell">
				<div class="weui_cell_hd">
					<label class="weui_label">位置</label>
				</div>
				<div class="weui_cell_bd weui_cell_primary">
					${wsEntity.address }</div>
			</div>
			<div class="weui_cells_title">具体情况</div>
			<div class="weui_cells weui_cells_form">
				<div class="weui_cell">
					<div class="weui_cell_bd weui_cell_primary">
						${wsEntity.content }</div>
				</div>
			</div>
			<div class="weui_cells_title">路况图片</div>
			<div class="weui_cells weui_cells_form">
				<c:forEach items="${list}" var="vide" varStatus="i">
					<img width="80%" src="/upload/${vide.imageSrc }.jpg">
				</c:forEach>
				<!-- <div class="weui_cell">
					<div class="weui_cell_bd weui_cell_primary">
						<div class="weui_uploader">
							<div class="weui_uploader_bd" id="imageContent">
	                            <ul class="weui_uploader_files">
									<c:forEach items="${list}" var="vide" varStatus="i">
	                                	<li class="weui_uploader_file" onclick="previewImage('/upload/${vide.imageSrc }.jpg')" style="background-image:url('/upload/${vide.imageSrc }.jpg')"></li>
	                                </c:forEach>
	                            </ul>
							</div>
						</div>
					</div>
				</div>-->
			</div>
		</div>
		<div class="weui_cells_tips">请对上传包括反动、暴力、色情、违法及侵权的内容进行举报，感谢您的配合。</div>
		<div class="weui_btn_area">
			<a class="weui_btn weui_btn_primary"
				href="javascript:reportSblkInfo();" id="showTooltips">举报</a>
		</div>
	</div>
	<div class="page" id="success" style="display: none;">
		<div class="weui_msg">
			<div class="weui_icon_area">
				<i class="weui_icon_success weui_icon_msg"></i>
			</div>
			<div class="weui_text_area">
				<h2 class="weui_msg_title">操作成功</h2>
				<p class="weui_msg_desc">感谢您对重庆交通的支持</p>
			</div>
			<div class="weui_opr_area">
				<p class="weui_btn_area">
					<a href="javascript:showSBLKRoadNews();"
						class="weui_btn weui_btn_primary">确定</a> <a
						href="javascript:showSBLKRoadNews();"
						class="weui_btn weui_btn_default">取消</a>
				</p>
			</div>
		</div>
	</div>
	<div class="weui_dialog_alert" id="dialog2" style="display: none;">
		<div class="weui_mask"></div>
		<div class="weui_dialog">
			<div class="weui_dialog_hd">
				<strong class="weui_dialog_title">提示信息</strong>
			</div>
			<div class="weui_dialog_bd">感谢您的配合，您已举报过此信息。</div>
			<div class="weui_dialog_ft">
				<a href="javascript:closeDialog('dialog2');"
					class="weui_btn_dialog primary">确定</a>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="/js/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
function reportSblkInfo(){
    $.ajax({
		type : "post",
		url : "/csc/reportSblkInfo",
		dataType : "json",
		data : {
			"openid":"${openid}",
			"id":"${wsEntity.id}"
		},
		success : function(data) {
			if(data.status=="OK"){
    			$("#opretor").css("display","none");
    			$("#success").css("display","block");
			}else if(data.status=="GOT"){
				openDialog("dialog2");
			}
		}
    });
}
function closeDialog(id){
	$("#"+id).css("display","none");
}
function openDialog(id){
	$("#"+id).css("display","block");
}
function showSBLKRoadNews() {
	window.location.href="../reportRoadNews.jsp?openId=${openid}";
}
wx.config({
    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
    appId: '${sign.appid}', // 必填，公众号的唯一标识
    timestamp:'${sign.timestamp}', // 必填，生成签名的时间戳
    nonceStr:'${sign.nonceStr}', // 必填，生成签名的随机串
    signature:'${sign.signature}',// 必填，签名，见附录1
    jsApiList: ['previewImage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
});
wx.ready(function(){
	getLocation();
});
wx.error(function(res){
    // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
}); 
function previewImage(url){
	wx.previewImage({
	    current: url, // 当前显示图片的http链接
	   	urls: [url] // 需要预览的图片http链接列表
	});
}
//下面这个函数用来转换数组到json格式
function arrayToJson(o) { 
	var r = []; 
	if (typeof o == "string") return "\"" + o.replace(/([\'\"\\])/g, "\\$1").replace(/(\n)/g, "\\n").replace(/(\r)/g, "\\r").replace(/(\t)/g, "\\t") + "\""; 
	if (typeof o == "object") { 
	if (!o.sort) { 
	for (var i in o) 
	r.push(i + ":" + arrayToJson(o[i])); 
	if (!!document.all && !/^\n?function\s*toString\(\)\s*\{\n?\s*\[native code\]\n?\s*\}\n?\s*$/.test(o.toString)) { 
	r.push("toString:" + o.toString.toString()); 
	} 
	r = "{" + r.join() + "}"; 
	} else { 
	for (var i = 0; i < o.length; i++) { 
	r.push(arrayToJson(o[i])); 
	} 
	r = "[" + r.join() + "]"; 
	} 
	return r; 
	} 
	return o.toString(); 
}
//这个是调用微信图片浏览器的函数 
function imagePreview(curSrc,srcList) {
	//这个检测是否参数为空
	if(!curSrc || !srcList || srcList.length == 0) {
		return;
	}
	//这个使用了微信浏览器提供的JsAPI 调用微信图片浏览器
	WeixinJSBridge.invoke('imagePreview', { 
		'current' : curSrc,
		'urls' : srcList
	});
};
$(document).ready(function(){
	//下面是获取当前页面所有的img的src 转成数组 并且 转换成json格式
	var aa=[];
	var i=0;
	var src=[];
	var json=null;
	aa=$('img');
	for (i=0;i<aa.length;i++){
		src[i]=aa[i].src;    //把所有的src存到数组里
	}
	var srcList=arrayToJson(src); //转换成json并赋值给srcList
	//下面是点击图片的时候获取当前第几个图片并且启用咱们做的调用微信图片浏览器的函数
	$('img').click(function(){
		var index = $('img').index(this);
		imagePreview(srcList[index],srcList);
	});
});
</script>
</html>