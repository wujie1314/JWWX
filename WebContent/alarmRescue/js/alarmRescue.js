var latitude = "";
var longitude = "";
$(function() {

	createMap();// 创建地图
	var HEIGHT = $('.contentChoice').height();
	var HEIGHT1 = $('.contnetFoot').height();
    $(window).resize(function() {
        $('.contentChoice').height(HEIGHT);
        $('.contnetFoot').height(HEIGHT1);
    });
});
// 创建地图函数：
function createMap() {
	var map = new BMap.Map("dituContent");// 在百度地图容器中创建一个地图
	var point = new BMap.Point(106.557165, 29.570997);// 定义一个中心点坐标
	map.centerAndZoom(point, 17);// 设定地图的中心点和坐标并将地图显示在地图容器中
	map.disableDoubleClickZoom(true);
	window.map = map;// 将map变量存储在全局

	map.addControl(new BMap.NavigationControl({
		offset : new BMap.Size(document.body.clientWidth - 60, 100)
	})); // 添加控件：缩放地图的控件
	map.addControl(new BMap.ScaleControl()); // 添加控件：地图显示比例的控件，默认在左下方；
	map.addControl(new BMap.GeolocationControl({
		offset : new BMap.Size(170, 10)
	})); // 添加控件，地图定位控件，默认左下方；

	map.setCurrentCity("重庆"); // 设置地图显示的城市 此项是必须设置的

	map.enableContinuousZoom(true); // 启用地图惯性拖拽，默认禁用
	map.enableDragging(true);// 启用地图拖拽事件，默认启用(可不写)
	map.enableScrollWheelZoom(true);// 启用地图滚轮放大缩小
	map.enableDoubleClickZoom(true);// 启用鼠标双击放大，默认启用(可不写)
	map.enableKeyboard(true);// 启用键盘上下左右键移动地图

	var geolocation = new BMap.Geolocation();
	var geoc = new BMap.Geocoder();
	var myIcon = new BMap.Icon("img/icon_point.png", new BMap.Size(30, 30));

	map.addEventListener("touchstart", function(e) {
		longitude = e.point.lng;
		latitude = e.point.lat;
		point = new BMap.Point(longitude, latitude);// 定位

		var marker2 = new BMap.Marker(point, {
			icon : myIcon
		}); // 创建标注
		map.clearOverlays();
		geoc.getLocation(point, function(rs) {
			console.log(point);
			console.log(rs);
			console.log(rs.addressComponents);
			$('#currentLocation').html(
					rs.address + rs.business.replace(/,/g, ''));
		});
		map.addOverlay(marker2);
	});

	geolocation.getCurrentPosition(function(r) {
		if (this.getStatus() == BMAP_STATUS_SUCCESS) {
			longitude = r.point.lng;
			latitude = r.point.lat;
			point = new BMap.Point(longitude, latitude);// 定位

			geoc.getLocation(point, function(rs) {
				console.log(point);
				console.log(rs);
				console.log(rs.addressComponents);
				// var addComp = rs.addressComponents;
				// var position = addComp.province + addComp.city +
				// addComp.district
				// + addComp.street + addComp.streetNumber;
				$('#currentLocation').html(
						rs.address + rs.business.replace(/,/g, ''));
			});

			map.centerAndZoom(point, 17);
			var marker = new BMap.Marker(point);
			map.addOverlay(marker);
			marker.disableMassClear();
			map.panTo(point);
		}
	}, {
		enableHighAccuracy : true
	});
}

function submit() {
	var id = getUrlParam("ID");
 //   var direction = $('input:radio[name="direction"]:checked').val();
    
    var repairReason = ""; 
    $('input[name="repairReason"]:checked').each(function(){ 
    	repairReason += $(this).val() + " "; 
    }); 

    console.log(repairReason);
    console.log("latitude:==="+latitude + "longitude:==="+longitude);
    
    var parame = {};
    parame.ID = id;
    parame.longitude = longitude;
    parame.latitude = latitude;
    parame.phoneNum = encodeURI($('#phoneNum').val());
 //   parame.direction = encodeURI(direction);
    parame.repairReason = encodeURI(repairReason);
    
    $.ajax({
    	url: 'alarmRescue/alarmRescue',
    	method: 'get',
    	contentType: "application/json;charset=utf-8", // 中文乱码
    	data: parame,
		success : function(o) {
			alert("提交成功");
			
			
			
			
		}
    });
}

function callPhone() {
	window.location.href = "tel:12122";
}

function getUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return decodeURI(r[2]);
	return null;
}
