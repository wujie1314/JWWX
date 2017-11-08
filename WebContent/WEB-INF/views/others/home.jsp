<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>其他页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<!-- 	<link rel="stylesheet" type="text/css" href="styles.css"> -->

<script type="text/javascript" src="/js/jquery/jquery-1.7.2.min.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>	
  </head>
  <style>
<!--
html {
  -ms-text-size-adjust: 100%;
  -webkit-text-size-adjust: 100%;
  -webkit-user-select: none;
  user-select: none;
}
body {
  line-height: 1.6;
  font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
  background-color: #f1f0f6;
}
* {
  margin: 0;
  padding: 0;
}
button {
  font-family: inherit;
  font-size: 100%;
  margin: 0;
  *font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
}
ul,
ol {
  padding-left: 0;
  list-style-type: none;
}
a {
  text-decoration: none;
}
.label_box {
  background-color: #ffffff;
}
.label_item {
  padding-left: 15px;
}
.label_inner {
  padding-top: 10px;
  padding-bottom: 10px;
  min-height: 24px;
  position: relative;
}
.label_inner:before {
  content: " ";
  position: absolute;
  left: 0;
  top: 0;
  width: 200%;
  height: 1px;
  border-top: 1px solid #ededed;
  -webkit-transform-origin: 0 0;
  transform-origin: 0 0;
  -webkit-transform: scale(0.5);
  transform: scale(0.5);
  top: auto;
  bottom: -2px;
}
.lbox_close {
  position: relative;
}
.lbox_close:before {
  content: " ";
  position: absolute;
  left: 0;
  top: 0;
  width: 200%;
  height: 1px;
  border-top: 1px solid #ededed;
  -webkit-transform-origin: 0 0;
  transform-origin: 0 0;
  -webkit-transform: scale(0.5);
  transform: scale(0.5);
}
.lbox_close:after {
  content: " ";
  position: absolute;
  left: 0;
  top: 0;
  width: 200%;
  height: 1px;
  border-top: 1px solid #ededed;
  -webkit-transform-origin: 0 0;
  transform-origin: 0 0;
  -webkit-transform: scale(0.5);
  transform: scale(0.5);
  top: auto;
  bottom: -2px;
}
.lbox_close .label_item:last-child .label_inner:before {
  display: none;
}
.btn {
  display: block;
  margin-left: auto;
  margin-right: auto;
  padding-left: 14px;
  padding-right: 14px;
  font-size: 18px;
  text-align: center;
  text-decoration: none;
  overflow: visible;
  /*.btn_h(@btnHeight);*/
  height: 42px;
  border-radius: 5px;
  -moz-border-radius: 5px;
  -webkit-border-radius: 5px;
  box-sizing: border-box;
  -moz-box-sizing: border-box;
  -webkit-box-sizing: border-box;
  color: #ffffff;
  line-height: 42px;
  -webkit-tap-highlight-color: rgba(255, 255, 255, 0);
}
.btn.btn_inline {
  display: inline-block;
}
.btn_primary {
  background-color: #04be02;
}
.btn_primary:not(.btn_disabled):visited {
  color: #ffffff;
}
.btn_primary:not(.btn_disabled):active {
  color: rgba(255, 255, 255, 0.9);
  background-color: #039702;
}
button.btn {
  width: 100%;
  border: 0;
  outline: 0;
  -webkit-appearance: none;
}
button.btn:focus {
  outline: 0;
}
.wxapi_container {
  font-size: 16px;
}
h1 {
  font-size: 14px;
  font-weight: 400;
  line-height: 2em;
  padding-left: 15px;
  color: #8d8c92;
}
.desc {
  font-size: 14px;
  font-weight: 400;
  line-height: 2em;
  color: #8d8c92;
}
.wxapi_index_item a {
  display: block;
  color: #3e3e3e;
  -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
}
.wxapi_form {
  background-color: #ffffff;
  padding: 0 15px;
  margin-top: 30px;
  padding-bottom: 15px;
}
h3 {
  padding-top: 16px;
  margin-top: 25px;
  font-size: 16px;
  font-weight: 400;
  color: #3e3e3e;
  position: relative;
}
h3:first-child {
  padding-top: 15px;
}
h3:before {
  content: " ";
  position: absolute;
  left: 0;
  top: 0;
  width: 200%;
  height: 1px;
  border-top: 1px solid #ededed;
  -webkit-transform-origin: 0 0;
  transform-origin: 0 0;
  -webkit-transform: scale(0.5);
  transform: scale(0.5);
}
.btn {
  margin-bottom: 15px;
}
-->
</style>
<script>
    $(document).ready(function(){
        initPage();
    });
    function initPage() {
        alert(window.location.href);/***用于获得当前连接url用**/
        /***用户点击分享到微信圈后加载接口接口*******/
        $.post("others/getWxConfig",{"url":window.location.href,"openId":"<%=request.getSession().getAttribute("othersOpenId")%>"},function(data,status){
            data=eval("("+data+")");
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
                alert("准备分享");
            });
            wx.error(function(res){
                alert("验证失败"+res);
                // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
            });
        });
    }
</script>
  <body>
  	<label>openId</label>
  	<input type="text" value=<%=request.getSession().getAttribute("othersOpenId")%> />
  	<div class = "btn btn_primary">
  		<a href="#">私人定制</a>
  	</div>
  	<div class = "btn btn_primary">
  		<a href="#">失物招领</a>
  	</div>
  	<div class = "btn btn_primary">
  		<a href="#">论坛</a>
  	</div>
  	<h1> JS-SDK 测试</h1>
    <a id="ddd" onclick="getLocationdd()">点我</a>
  </body>
  <script>
    function getLocationdd() {
        wx.checkJsApi({
            jsApiList: ['chooseImage'], // 需要检测的JS接口列表，所有JS接口列表见附录2,
            success: function(res) {
                // 以键值对的形式返回，可用的api值true，不可用为false
                // 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
            }
        });
        wx.getLocation({
            type: 'wgs84', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
            success: function (res) {
                var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
                var longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。
                var rspeed = res.speed; // 速度，以米/每秒计
                var accuracy = res.accuracy; // 位置精度

                var locationStr = "latitude："+latitude+"，"+"longitude："+longitude+"，"+"speed："+speed+"，"+"accuracy："+accuracy+"，"+"errMsg："+errMsg;
                alert(locationStr);
            },
            fail:function () {
                alert("调用失败");
            }
        });
        wx.error(function (res) {
            alert(res.errMsg);
        });
    }

</script>
</html>
