<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
<link rel="stylesheet" href="${ctx}/css/weui-master/dist/style/weui.css" />
<link rel="stylesheet" href="${ctx}/css/weui-master/dist/example/example.css" />
<title>上报路况</title>
</head>
<body>
	<div class="page" id="opretor">
		<!-- <div class="weui_cells_title">上报路况</div> -->
		<input type="hidden" name="repoType" value="${type}">
		<div class="weui_cells weui_cells_form">
			<div class="weui_cell">
				<div class="weui_cell_hd">
					<label class="weui_label">姓名</label>
				</div>
				<div class="weui_cell_bd weui_cell_primary">
					<input class="weui_input" name="repoUserName" type="text" placeholder="请输入姓名" />
				</div>
			</div>
			<div class="weui_cell">
				<div class="weui_cell_hd">
					<label class="weui_label">电话</label>
				</div>
				<div class="weui_cell_bd weui_cell_primary">
					<input class="weui_input" name="repoUserPhone" type="text" placeholder="请输入电话" />
				</div>
			</div>
			<div class="weui_cells_title">描述</div>
			<div class="weui_cells weui_cells_form">
				<div class="weui_cell">
					<div class="weui_cell_bd weui_cell_primary">
						<textarea name="repoDetails" onkeydown="showContentNum(this);"
							class="weui_textarea" placeholder="请输入描述" rows="3"></textarea>
						<div class="weui_textarea_counter">
							<span id="contentNumber">0</span>/200
						</div>
					</div>
				</div>
			</div>
			<div class="weui_cells_title">图片上传</div>
			<div class="weui_cells weui_cells_form">
				<div class="weui_cell">
					<div class="weui_cell_bd weui_cell_primary">
						<div class="weui_uploader">
							<div class="weui_uploader_hd weui_cell">
								<div class="weui_cell_bd weui_cell_primary">图片数量</div>
								<div class="weui_cell_ft" id="imageNumber">0/4</div>
							</div>
							<div class="weui_uploader_bd">
								<ul class="weui_uploader_files" id="imageContent">
								</ul>
								<div class="weui_uploader_input_wrp">
									<input class="weui_uploader_input" type="file" onclick="chooseImage();return false;" accept="image/jpg,image/jpeg,image/png,image/gif" multiple />
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="weui_cells_tips">请勿上传包括反动、暴力、色情、违法及侵权的内容，违者将根据以下条款追究其相关法律责任。</div>
		<div class="weui_btn_area">
			<a class="weui_btn weui_btn_primary"
				href="javascript:saveSblkInfo();" id="showTooltips">确定</a>
		</div>
	</div>
	<div class="page" id="success" style="display: none;">
		<div class="weui_msg">
			<div class="weui_icon_area">
				<i class="weui_icon_success weui_icon_msg"></i>
			</div>
			<div class="weui_text_area">
				<h2 class="weui_msg_title">操作成功</h2>
				<p class="weui_msg_desc">信息上传成功。请您关注重庆交通微博、微信，中奖信息将于2月21日公示，上报次数越多中奖几率越大哟。</p>
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
			<div class="weui_dialog_bd" id="dialogContent"></div>
			<div class="weui_dialog_ft">
				<a href="javascript:closeDialog('dialog2');"
					class="weui_btn_dialog primary">确定</a>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">

wx.config({
    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
    appId: '${sign.appid}', // 必填，公众号的唯一标识
    timestamp:'${sign.timestamp}', // 必填，生成签名的时间戳
    nonceStr:'${sign.nonceStr}', // 必填，生成签名的随机串
    signature:'${sign.signature}',// 必填，签名，见附录1
    jsApiList: ['chooseImage','previewImage','uploadImage','getLocation'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
});
wx.ready(function(){
	getLocation();
});
wx.error(function(res){
	alert(ress);
    // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
}); 
function previewImage(url){
	wx.previewImage({
	    current: url, // 当前显示图片的http链接
	   	urls: [url] // 需要预览的图片http链接列表
	});
}
function chooseImage(){
	var imageNumber=$("#imageNumber").html();
	var num=parseInt(imageNumber.substring(0,1));
	if(num<=4){
		wx.chooseImage({
		    count: 1, // 默认9
		    sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
		    sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
		    success: function (res) {
		        var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
		        for(var i=0;i<localIds.length;i++){
		        	uploadImage(localIds[i]);
		        }
		    }
		});
	}else{
		openDialog("dialog2","目前仅支持上传4张图片");
	}
}
function closeDialog(id){
	$("#"+id).css("display","none");
}
function openDialog(id,content){
	$("#dialogContent").html(content);
	$("#"+id).css("display","block");
}
function uploadImage(localId){
	wx.uploadImage({
	    localId: localId, // 需要上传的图片的本地ID，由chooseImage接口获得
	    isShowProgressTips: 1, // 默认为1，显示进度提示
	    success: function (res) {
	        var serverId = res.serverId; // 返回图片的服务器端ID
			var html=""
			html+='<li onclick=previewImage("'+localId+'") class="weui_uploader_file"';
			html+='style="background-image: url('+localId+')"></li>';
			$("#imageContent").append(html);
			$("#imageContent").append("<input type='hidden' name='imageSrc' value='"+serverId+"'/>");
			var imageNumber=$("#imageNumber").html();
			var num=parseInt(imageNumber.substring(0,1));
			$("#imageNumber").html((num+1)+"/4");
	        getImageSrc(serverId);
	    }
	});
}
function getImageSrc(serverId){
	$.ajax({
		type : "post",
		url : "${ctx}/activity/getImageSrc",
		dataType : "json",
		data : {
			"serverId":serverId
		},
		success : function(data) {
        },
		error:function(data){
		}
	});
}
function geocoder(latitude,longitude){
    
}
function getLocation(){
	wx.getLocation({
	    type: 'wgs84', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
	    success: function (res) {
	        var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
	        var longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。
	        var speed = res.speed; // 速度，以米/每秒计
	        var accuracy = res.accuracy; // 位置精度
	        $("input[name=latitude]").val(latitude);
	        $("input[name=longitude]").val(longitude);
	        geocoder(latitude,longitude);
	    }
	});
}
function showContentNum(obj){
	var num=$(obj).val().length+1;
	$("#contentNumber").html(num);
}
function saveSblkInfo(){
    var repoType=$("input[name=repoType]").val();
    var repoUserName=$.trim($("input[name=repoUserName]").val());
    var repoUserPhone=$.trim($("input[name=repoUserPhone]").val());
    var repoDetails=$.trim($("textarea[name=repoDetails]").val());
    var repoImgUrl=$.trim($("input[name=repoImgUrl]").val());
    if(repoUserName==""){
    	$("input[name=repoUserName]").focus();
		openDialog("dialog2","姓名不能为空");
    	return;
    }else if(repoUserPhone==""){
    	$("textarea[name=repoUserPhone]").focus();
		openDialog("dialog2","电话不能为空");
    	return;
    }else if(repoDetails==""){
    	$("textarea[name=repoDetails]").focus();
		openDialog("dialog2","描述不能为空");
    	return;
    }
    var imageSrc="";
    $("input[name=imageSrc]").each(function(){
    	imageSrc+=$(this).val()+",";
    });
    $.ajax({
		type : "post",
		url : "${ctx}/activity/insert",
		dataType : "text",
		data : {
			"repoType":repoType,
			"repoUserName":repoUserName,
			"repoUserPhone":repoUserPhone,
			"repoDetails":repoDetails,
			"repoImgUrl":imageSrc
		},
		success : function(data) {
			$("#opretor").css("display","none");
			$("#success").css("display","block");
        }
	});
}

function showSBLKRoadNews() {
	window.location.href="${ctx}/activity/home";
}
</script>
</html>