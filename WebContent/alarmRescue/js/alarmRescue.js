var latitude = "";
var longitude = "";
var id = getUrlParam("ID");
var phone = getUrlParam("phone");
var timestamp = getUrlParam("timestamp");
var isPass24Hours;
var LXFS = getUrlParam("LXFS");
var map;
var marker2;
var marker;
var point;
var markerArr = [];
var geocoder;
$(function() {
	createMap();// 创建地图
	var HEIGHT = $('.contentFoot').height();
	$(window).resize(function() {
		$('.contentFoot').height(HEIGHT);
	});

	$('body').on('touchmove', function(event) {
		event.preventDefault();
	});

	$('#phoneNum').val(phone);
	// 禁止页面下滑露出页面来源
	document.querySelector('.container')
			.addEventListener(
					'touchmove',
					function(e) {
						if (!document.querySelector('.contentFoot').contains(
								e.target)) {
							e.preventDefault();
						}
					})

	isPass24Hours = new Date().getTime() / 1000 - timestamp;
	isPass24Hours = 0;
	if (LXFS != "") {
		$("#phoneNum").val(LXFS);
	}
	if (isPass24Hours > 86400) {
		$('#pastDue').css("display", "block");
		$('#submitButton').css("background", "#808080");
		$('body')
				.append(
						"<div style='position:absolute;z-index:1000;width:100%;height:100%;'></div>");
	} else {
		$('#pastDue').css("display", "none");
	}
});

function createMap() {
	point = new AMap.LngLat(106.551201, 29.60957);
	map = new AMap.Map('dituContent', {
		resizeEnable : true,
		viewMode : '3D',
		rotateEnable : false,
		zoom : 16,
		center : point
	});
	map.setStatus({
		doubleClickZoom : true
	});
	map.setStatus({
		dragEnable : true
	});
	marker = new AMap.Marker({
		icon : "alarmRescue/img/location.png",
		position : point,
		map : map
	});
	marker.setMap(map);
	map.plugin([ "AMap.convertFrom", "AMap.Geocoder", "AMap.Scale",
			"AMap.ToolBar", "AMap.Geolocation" ], function() {
		geocoder = new AMap.Geocoder({
			city : "全国",// 城市，默认：“全国”
			extensions : "all"
		});
		var toolbar = new AMap.ToolBar({
			direction : false,// 方向键盘是否可见，默认为true
			autoPosition : false,// 是否自动定位，即地图初始化加载完成后，是否自动定位的用户所在地，在支持HTML5的浏览器中有效，默认为false
			locationMarker : marker
		});
		var geolocation = new AMap.Geolocation({
			enableHighAccuracy : true,// 是否使用高精度定位，默认:true
			showButton : true, // 显示定位按钮，默认：true
			buttonPosition : 'LB', // 定位按钮停靠位置，默认：'LB'，左下角
			buttonOffset : new AMap.Pixel(50, 100),// 定位按钮与设置的停靠位置的偏移量
			showMarker : false, // 定位成功后在定位到的位置显示点标记，默认：true
			showCircle : false, // 定位成功后用圆圈表示定位精度范围，默认：true
			panToLocation : true, // 定位成功后将定位到的位置作为地图中心点，默认：true
			zoomToAccuracy : true, // 定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
			convert : true
		// 自动偏移坐标，偏移后的坐标为高德坐标，默认：true
		});
		map.addControl(new AMap.Scale());
		map.addControl(toolbar);
		map.addControl(geolocation);
		geolocation.getCurrentPosition(function(status, result) {
			if (status === 'complete') {
				point = result.position;
				latitude = result.position.getLat();
				longitude = result.position.getLng();
				geocoder.getAddress(point, function(status, result) {
					if (status === 'complete' && result.info === 'OK') {
						marker.setPosition(point);
						map.setZoomAndCenter(18, point);
						var address = result.regeocode.formattedAddress; // 返回地址描述
						$("#currentLocation").html(address);
					}
				});
				// new AMap.convertFrom(point, 'gps', function(status, result) {
				// latitude = result.locations[0].lat;
				// longitude = result.locations[0].lng;
				// point = new AMap.LngLat(longitude, latitude);
				// marker.setPosition(point);
				// map.setZoomAndCenter(18, point);
				// geocoder.getAddress(point, function(status, result) {
				// if (status === 'complete' && result.info === 'OK') {
				// var address = result.regeocode.formattedAddress; // 返回地址描述
				// $("#currentLocation").html(address);
				// }
				// });
				// });
			}

		});

		toolbar.hide();
		map.remove(markerArr);
		AMap.event.addListener(geolocation, 'complete', onComplete);// 返回定位成功信息
		AMap.event.addListener(geolocation, 'error', onError); // 返回定位出错信息
	});

	// AMap.event.addListener(map, 'click', function(e) {
	// map.remove(markerArr);
	// longitude = e.lnglat.getLng();
	// latitude = e.lnglat.getLat();
	// point = new AMap.LngLat(longitude, latitude);
	//
	// geocoder.getAddress(point, function(status, result) {
	// if (status === 'complete' && result.info === 'OK') {
	// $("#currentLocation").html(result.regeocode.formattedAddress);
	// }
	// });
	// marker2 = new AMap.Marker({
	// icon : "alarmRescue/img/icon_location.png",
	// position : point,
	// draggable : false,
	// cursor : 'move',
	// raiseOnDrag : true,
	// map : map
	// });
	//
	// markerArr.push(marker2);
	// // marker2.setAnimation('AMAP_ANIMATION_BOUNCE');
	// marker2.setMap(map);
	//
	// marker2.setTitle("用户可以自定义新的位置");
	// marker2.setLabel({
	// offset : new AMap.Pixel(45, -10),
	// content : "自定义的位置"
	// });
	// });
}

function onComplete(data) { // 解析定位结果
	var point1 = [ data.position.getLng(), data.position.getLat() ];
	latitude = data.position.getLat();
	longitude = data.position.getLng();
	geocoder.getAddress(point1, function(status, result) {
		if (status === 'complete' && result.info === 'OK') {
			marker.setPosition(point1);
			map.setZoomAndCenter(18, point1);
			$("#currentLocation").html(result.regeocode.formattedAddress);
		}
	});
	// new AMap.convertFrom(point1, 'gps', function(status, result) {
	// latitude = result.locations[0].lat;
	// longitude = result.locations[0].lng;
	// point = new AMap.LngLat(longitude, latitude);
	// marker.setPosition(point);
	// map.setZoomAndCenter(18, point);
	// map.remove(markerArr);
	//	
	// geocoder.getAddress(point, function(status, result) {
	// if (status === 'complete' && result.info === 'OK') {
	//			
	// $("#currentLocation").html(result.regeocode.formattedAddress);
	// }
	// });
	// });
};
var num = 0;
function onError(data) { // 解析定位错误信息
	map.remove(markerArr);
	if (num < 3) {
		createMap();
		num = num + 1;
	}
	switch (data.info) {
	case 'PERMISSION_DENIED':
		$("#currentLocation").html("浏览器阻止了定位操作");
		break;
	case 'POSITION_UNAVAILBLE':
		$("#currentLocation").html("无法获得当前位置,请确定GPS是否打开");
		break;
	case 'TIMEOUT':
		$("#currentLocation").html("定位超时");
		break;
	default:
		$("#currentLocation").html("未知错误,请确定GPS是否打开");
		break;
	}
};

function submit() {
	var repairReason = $('input:radio[name="repairReason"]:checked').val();

	$("#phoneNum").click(function() {
		if ($('#phoneNum').val() == "手机号码有误或为空,请重填") {
			$('#phoneNum').val("");
			$('#phoneNum').css("color", "#333");
		}
	});
	if (!(/^1[34578]\d{9}$/.test($('#phoneNum').val()))) {
		$('#phoneNum').val("手机号码有误或为空,请重填");
		$('#phoneNum').css("color", "#f00");
		return false;
	} else {
		var parame = {};
		parame.ID = id;
		parame.currentLocation = encodeURI($('#currentLocation').html());
		parame.longitude = longitude;
		parame.latitude = latitude;

		parame.startPosition = encodeURI($('#startPosition').val());
		parame.endPosition = encodeURI($('#endPosition').val());
		parame.contact_way = encodeURI($('#phoneNum').val());
		parame.repairReason = encodeURI(repairReason);
		$.ajax({
			url : 'alarmRescue/alarmRescue',
			method : 'get',
			contentType : "application/json;charset=utf-8", // 中文乱码
			data : parame,
			success : function(o) {
				if ($("#file").val() != "") {
					$('#file').fileinput('upload');
				}
				alert("提交成功");
			},
			error : function(o) {
				alert("提交失败");
			}
		});
	}
}

function deleteFile() {
	if ($('#file').fileinput("getFilesCount") <= 1) {
		$('#dituContent').css("height", "57%");
		$('.Relocation').css("top", "28%");
		$('.submitBut').css("height", "30%");
		$('.contentFoot').css("height", "43%");
	}
}
var interval;
var clientHeight = document.documentElement.clientHeight || document.body.clientHeight;
$(window).on('resize', function () {
    var nowClientHeight = document.documentElement.clientHeight || document.body.clientHeight;
    if (clientHeight > nowClientHeight) {
    	 clearInterval(interval);
    }
    else {    	
//    	alert($('#file').fileinput("getFilesCount"));
//    	alert($(".progress-bar").html());
//    	if ($(".progress-bar").html() != "" && ($('#file').fileinput("getFilesCount") < 1)) { // 获取文件个数
//    		$('#dituContent').css("height", "50%");
//    		$('.Relocation').css("top", "28%");
//    		$('.submitBut').css("height", "30%");
//    		$('.contentFoot').css("height", "50%");
//    		alert("aa");
//    	}
    	interval = setInterval(function () {
    		if ($('#file').fileinput("getFilesCount") >= 1) { // 获取文件个数
    			$('#dituContent').css("height", "40%");
    			$('.Relocation').css("top", "18%");
    			$('.submitBut').css("height", "20%");
    			$('.contentFoot').css("height", "60%");
    		}
    		if ($('#file').fileinput("getFilesCount") < 1) { // 获取文件个数
    			$('#dituContent').css("height", "57%");
    			$('.Relocation').css("top", "28%");
    			$('.submitBut').css("height", "30%");
    			$('.contentFoot').css("height", "43%");
    		}
    	}, 100);
    }
});

//setInterval(function () {
//	if ($(".progress-bar").html() = "上传成功") { // 获取文件个数
//		$('#dituContent').css("height", "50%");
//		$('.Relocation').css("top", "28%");
//		$('.submitBut').css("height", "30%");
//		$('.contentFoot').css("height", "50%");
//	}
//},100);

$("#file").fileinput(
		{
			language : 'zh', // 设置语言
			uploadUrl : '/alarmRescue/upload',
			enctype : 'multipart/form-data', // error
			allowedFileExtensions : [ 'jpg', 'jpeg', 'svg', 'png', 'gif',
					'bmp', 'mp4', 'avi', 'wmv', 'mpeg', 'mpg', 'rm', 'asf' ],
			maxFileSize : 30000,
			maxFilesNum : 3,
			maxFileCount : 3,
			overwriteInitial : false, // 不覆盖已存在的图片
			dropZoneEnabled : false,// 是否显示拖拽区域
			showPreview : true, // 展前预览
			showRemove : false, // 是否显示移除按钮
			showUpload : false, // 是否显示上传按钮
			showCancel : false, // 是否显示取消按钮
			showClose : false, // 是否显示关闭按钮
			showBrowse : true, // 是否显示浏览按钮
			showCaption : false, // 不显示文字表述
			uploadAsync : false, // 同步上传
			slugCallback : function(filename) {
				return filename.replace('(', '_').replace(']', '_');
			},
			uploadExtraData : function(previewId, index) {
				// 向后台传递id作为额外参数，是后台可以根据id修改对应的图片地址。
				var obj = {};
				obj.id = $('#file').val();
				return obj;
			}
		});

$("#file").on('filebatchuploadsuccess', function(event, data) {
	$(".file-preview-frame").remove();
	if ($('#file').fileinput("getFilesCount") > 0) { // 获取文件个数
		$('#dituContent').css("height", "53%");
		$('.Relocation').css("top", "28%");
		$('.submitBut').css("height", "30%");
		$('.contentFoot').css("height", "47%");
	}
}).on('filebatchuploaderror', function(event, data, msg) {

}).on("filebatchselected", function(event, files) {
	$('#dituContent').css("height", "40%");
	$('.Relocation').css("top", "18%");
	$('.submitBut').css("height", "20%");
	$('.contentFoot').css("height", "60%");
});

$("#showRepairFactory")
		.click(
				function() {
					window.location = "http://ditu.amap.com/search?query=%E7%BB%B4%E4%BF%AE&query_type=RQBXY&longitude=106.322422&latitude=29.586976&city=500000&zoom=16";

				});

$("#callPhone").click(function() {
	window.location.href = "tel:12122";
});

function getUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return decodeURI(r[2]);
	return null;
}