
$(document).ready(function(){
    initPage();
});
function initPage() {
    alert(window.location.href);/***用于获得当前连接url用**/
/***用户点击分享到微信圈后加载接口接口*******/
$.post("others/getWxConfig",{"url":window.location.href,"openId":$('#openID').val()},function(data,status){
    console.log(data.appId+" "+data.timestamp+" "+data.nonceStr+" "+data.signature);
    wx.config({
        debug: true,
        appId: data.appId,
        timestamp:data.timestamp,
        nonceStr:data.nonceStr,
        signature:data.signature,
        jsApiList: [
            'checkJsApi',
            'getLocation',
            'onMenuShareTimeline',
            'hideOptionMenu',
        ]
    })
    wx.ready(function(){
        console.log("配置成功");
    });
    wx.error(function(res){
        alert("验证失败"+res);
        // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
        });
    });
}

/**
 * 
 * 获取微信当前经纬度
 * @returns {___anonymous993_994}
 */
function getLocationdd() {
	var location = {};
    wx.checkJsApi({
        jsApiList: ['getLocation'], // 需要检测的JS接口列表，所有JS接口列表见附录2,
        success: function(res) {
            // 以键值对的形式返回，可用的api值true，不可用为false
            // 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
        	 if (res.checkResult.getLocation == false) {
                 alert('你的微信版本太低，不支持微信JS接口，请升级到最新的微信版本！');
                 return;
             }
        }
    });
    wx.getLocation({
        type: 'wgs84', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
        success: function (res) {
        	location.latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
        	location.longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。
        	location.rspeed = res.speed; // 速度，以米/每秒计
        	location.accuracy = res.accuracy; // 位置精度

            var locationStr = "latitude："+location.latitude+"，"+"longitude："+location.longitude+"，"+"speed："+location.rspeed+"，"+"accuracy："+location.accuracy+"，"+"errMsg："+errMsg;
            alert(locationStr);
            
        },
        cancel: function (res) {
            alert('用户拒绝授权获取地理位置');
        },
        fail:function (res) {
            alert("调用失败");
        }
    });
    wx.error(function (res) {
        alert(res.errMsg);
    });
    return location;
}