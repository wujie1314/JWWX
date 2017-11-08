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
	
<link rel="stylesheet" type="text/css" href="/css/others/style.css"> 
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>	
<script type="text/javascript" src="/js/others/wx_sdk.js"></script>
<script type="text/javascript" src="/js/jquery/jquery-1.7.2.min.js"></script>

<script type="text/javascript" src="/js/others/wx_sdk.js"></script>
</head>
  <body>
  	<label>openId</label>
  	<input type="text" value=<%=request.getSession().getAttribute("othersOpenId")%> id="openID"/>
  	<div class = "btn btn_primary">
  		<a href="#">私人定制</a>
  	</div>
  	<div class = "btn btn_primary">
  		<a href="#">失物招领</a>
  	</div>
  	<div class = "btn btn_primary" onclick="BBS()">
  		<a onclick="BBS()">论坛</a>
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
  </body>
  		<input type="text" id="openID" value=<%=request.getSession().getAttribute("othersOpenId")%> />
  		<button class = "btn btn_primary" onclick="Personal()" >私人定制</button>
  		<button class = "btn btn_primary" >失物招领</button>
		<button class = "btn btn_primary"  id="BBS" onclick="BBS()" >论坛</button>
  		<button class = "btn btn_primary" id="ddd" onclick="getLocationdd()">SDK获取微信当前位置</button>
  </body>
  <script type="text/javascript">
  		function BBS(){
  			var openID = $("#openID").val();
  			window.location.href = "bbs/jsp/mine.jsp?openID=" + openID;  
  		}
  		function Personal(){
  			var openID = $("#openID").val();
  			window.location.href = "/psDesign/getRoadName?openID=" + openID;  
  		}
  </script>
</html>
