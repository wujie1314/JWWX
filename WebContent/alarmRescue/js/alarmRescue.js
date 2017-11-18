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
// 创建地图函数
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
	var myIcon = new BMap.Icon("alarmRescue/img/icon_point.png", new BMap.Size(30, 30));

	
	map.addEventListener("touchstart", function(e) {
		longitude = e.point.lng;
		latitude = e.point.lat;
		point = new BMap.Point(longitude, latitude);// 定位
		var label = new BMap.Label("新位置",{offset:new BMap.Size(25,-15)});
		var marker2 = new BMap.Marker(point, {
			icon : myIcon
		}); 
		marker2.setLabel(label);
		
		map.clearOverlays();
		
		geoc.getLocation(point, function(rs) {
			console.log(rs.addressComponents);
			$('#currentLocation').html(rs.address + rs.business.replace(/,/g, ''));
		});
		
		map.addOverlay(marker2);
	});

	geolocation.getCurrentPosition(function(r) {
		if (this.getStatus() == BMAP_STATUS_SUCCESS) {
			longitude = r.point.lng;
			latitude = r.point.lat;
			point = new BMap.Point(longitude, latitude);// 定位

			geoc.getLocation(point, function(rs) {
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

$("#showRepairFactory").click(function(){
	window.location="http://ditu.amap.com/search?query=%E7%BB%B4%E4%BF%AE&query_type=RQBXY&longitude=106.322422&latitude=29.586976&city=500000&zoom=16";
	
});
	

function showRepairFactory(){
	var local = new BMap.LocalSearch(map, { renderOptions:{map: map, autoViewport: true}});
	local.searchNearby("汽修", new BMap.Point(longitude, latitude));

}

//
//$(":checkbox").click(function(){
//	$(this).attr("checked",true);//设置当前选中checkbox的状态为checked
//	$(this).siblings().attr("checked",false); //设置当前选中的checkbox同级(兄弟级)其他checkbox状态为未选中
//	});
//
//$(':checkbox').each(function(){ //遍历页面中所有的checkbox
//	$(this).click(function(){//为页面中每一个checkbox设置点击事件
//	if($(this).attr('checked')){ //如果有checkbox状态为选中
//	$(':checkbox').removeAttr('checked'); //移除checked属性，改变checkbox状态为未选中(为页面中所有checkbox复选框添加设置)
//	//$(':checkbox').attr('disabled','disabled'); //或者直接设置checkbox复选框为禁用(为页面中所有checkbox复选框添加设置)
//	$(this).attr('checked','checked'); //为当前点击选中的checkbox复选框添加checked属性
//	} 
//	}); 
//	});

function submit() {
	var id = getUrlParam("ID");
    var repairReason = $('input:radio[name="repairReason"]:checked').val();
    var phone = $('#phoneNum').val();

    $("#phoneNum").click(function(){
    	if($('#phoneNum').val() == "手机号码有误或为空,请重填"){
	    	$('#phoneNum').val("");
	    	$('#phoneNum').css("color","#333");
    	}
    });
    if(!(/^1[34578]\d{9}$/.test(phone))){  
    	$('#phoneNum').val("手机号码有误或为空,请重填");
    	$('#phoneNum').css("color","#f00");
        return false; 
    } 
//    var repairReason = ""; 
//    $('input[name="repairReason"]:checked').each(function(){ 
//    	repairReason += $(this).val() + " "; 
//    }); 
    else{
	    var parame = {};
	    parame.ID = id;
	    parame.longitude = longitude;
	    parame.latitude = latitude;
	    parame.startPosition = encodeURI($('#startPosition').val());
	    parame.endPosition = encodeURI($('#endPosition').val());
	    parame.phoneNum = encodeURI($('#phoneNum').val());
	    parame.repairReason = encodeURI(repairReason);
	    console.log(parame);
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
