<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String openId = (String) request.getSession()
			.getAttribute("openId");
	String userName = (String) request.getSession().getAttribute(
			"userName");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<!--优先使用 IE 最新版本和 Chrome-->
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<!--百度禁止转码-->
<meta http-equiv="Cache-Control" content="no-siteapp" />
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="author" content="">
<!-- 搜索引擎抓取 -->
<meta name="robots" content="index,follow" />
<meta name="baidu-site-verification" content="" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<!-- 为移动设备添加 viewport -->
<!--<meta name="viewport" content="initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no">-->
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<!--禁止数字识自动别为电话号码-->
<meta content="telephone=no" name="format-detection">
<!-- windows phone 点击无高光 -->
<meta name="msapplication-tap-highlight" content="no">
<title>在线客服</title>
<link rel="stylesheet" type="text/css" href="/css/others/online.css" />
</head>
<script type="text/javascript">
        var basePath = '<%=basePath%>';
        var openId = '<%=openId%>';
        var userName = '<%=userName%>';
</script>
<body>
	<!--聊天-->
	<div class="content">
		<div class="chatContent">
		</div>
	</div>
<!-- 	<div class="footerDiv flex flex-row flex-centerss"> -->
<!-- 		<div style="width: 30%" class="flex flex-row flex-centerss"> -->
<!-- 			<div class="iconDiv" style="padding: 7px 7px 3px 7px;margin-left:10px;"> -->
<!-- 				<img src="/image/others/lookicon.png" class="inco"> -->
<!-- 			</div> -->
<!-- 			<div class="iconDiv" style="margin-left:10px;"> -->
<!-- 				<img src="/image/others/imgicon.png" class="inco"> -->
<!-- 			</div> -->
<!-- 			<div class="iconDiv" style="margin-left: 10px"> -->
<!-- 				<img src="/image/others/dwicon.png" class="inco"> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->
	<div class="footerSendDiv flex flex-row flex-centerss">
		<div class="entryDiv">
				<input type="text" class="entry" placeholder="在此输入消息" speech x-webkit-speech>
		</div>
		<button class="send">发送</button>
		<!--<div style="width: 30%" class="flex flex-row flex-centerss">-->
		<!--<div class="iconDiv" style="padding: 7px 7px 3px 7px;">-->
		<!--<img src="/image/others/imgicon.png" class="inco">-->
		<!--</div>-->
		<!--<div class="iconDiv" style="margin-left: 10px">-->
		<!--<img src="/image/others/dwicon.png" class="inco">-->
		<!--</div>-->
		<!--</div>-->
	</div>
	</div>
	<script type="text/javascript" src="/js/jquery/jquery-1.11.3.min.js"></script>
	<script type="text/javascript " src="/js/others/layer/mobile/layer.js"></script>
	<script src="/js/sockjs-0.3.min.js"></script>
	<script type="text/javascript" src="/js/websocket/swfobject.js"></script>
	<script type="text/javascript" src="/js/websocket/web_socket.js"></script>
	<script type="text/javascript" src="/js/qqface.js"></script>
	<script type="text/javascript" src="/js/others/others.js?timeStamp=20180517001"></script>
	<script>
  		//发送参数
  		var ws = null;
        var postdata = {
            Content: "",
            CreateTime: "",
            ToUserName: "gh_2ed2a7b99aa4",
            FromUserName: openId,
            Nickname: userName,
            MsgType: "text",
            MsgId: "",
        };
        var content = "";
        
        $(".send").on('click', function () {
            content = $(".entry").val();
            var time = new Date();
            postdata.CreateTime = Math.round(time.getTime()/1000);
            postdata.Content = content;
            postdata.MsgId = openId.substring(2) + postdata.CreateTime;
            seedMsg(postdata);
        })
        
        //连接websocket
        function connect(){
			var  tmp = basePath +"/appChatSocket?openId=" + openId;
			tmp = tmp.replace("http","ws");
			ws= new WebSocket(encodeURI(tmp));
			
			
			ws.onopen = function() {
				heartCheck.start();
				console.log("Connection open.");
			};
			
			ws.onmessage = function(message) {
				heartCheck.reset();
				var msg = message.data.replace(/(\n|&#13;)/g, '<br>');
				if("HeartBeat" == msg){
				}else{
					var data = $.parseJSON(message.data.replace(/(\n|&#13;)/g, '<br>'));
					var rehtml = "";
					if("text" == data.msgtype){
						rehtml += "<div class='kfitem flex flex-row'>" +
			                "<div class='kfhead flex flex-row flex-center'>" +
			                "<img src='/image/others/chaticon.png' class='kfimg'>" +
			                "</div>" +
			                "<div class='kfcontent flex flex-column'>" +
			                "<span>客服代表</span>"+
			                "<span class='kfques'>" + replaceQQFace(data.text.content) + "</span>" +
			                "</div>" +
			                "</div>";
					}else if ("image" == data.msgtype) {
						rehtml += "<div class='kfitem flex flex-row'>" +
				        "<div class='kfhead flex flex-row flex-center'>" +
				        "<img src='/image/others/chaticon.png' class='kfimg'>" +
				        "</div>" +
				        "<div class='kfcontent flex flex-column'>" +
				        "<span>客服代表</span>"+ 
				        "<div class='bjimg'>" + "<img src=" + data.imageUrl + " width='150px' onload=scrollBottom() onclick=imgToBig(this)></img>" +
				        "</div>" +
				        "</div>";
					}else if("url" == data.msgtype){
						rehtml += "<div class='kfitem flex flex-row'>" +
				        "<div class='kfhead flex flex-row flex-center'>" +
				        "<img src='/image/others/chaticon.png' class='kfimg'>" +
				        "</div>" +
				        "<div class='kfcontent flex flex-column'>" +
				        "<span>客服代表</span>"+ 
				        "<span class='kfques' style='border: 1px solid;border-radius: 4px;padding: 5px;background: #f7f1f1;' onclick=toUrl('" + data.url + "')>" + data.text.content + "</span>" +
				        "</div>" +
				        "</div>";
					}
		            $(".chatContent").append(rehtml);
		          //滚动条自动到最低端
		            scrollBottom();
				}
		    }; 
			
		    ws.onclose = function(){
			    connect();
		    	console.log("连接已断开.");
		    }
		    
			ws.onerror = function(e){
				console.log("连接发生错误.");
			};
		}
        
        function toUrl(url){
        	window.location.href = url;
        }
        
        //发送信息
        function seedMsg(postdata) {
        	if(!ws || ws.readyState != ws.OPEN){
        		layer.open({
                    content:'连接异常，请稍后再试'
                    ,skin: 'msg'
                    ,time: 2 //2秒后自动关闭
                });
        		return;
        	}
        	if(!content){
        		layer.open({
                    content:'发送内容不能为空'
                    ,skin: 'msg'
                    ,time: 2 //2秒后自动关闭
                });
        		return;
        	}
            var html = "<div class='khitem flex flex-row flex-end'>" +
                "<div class='khcontent flex flex-column'>" +
                "<span class='khname'>" + userName + "</span>" +
                "<span class='khquestion'>" + content + "</span>" +
                "</div>" +
                "<div class='khhead'>" +
                "<img src='/image/others/usericon.png' width='100%'>" +
                "</div>" +
                "</div>";
            $(".chatContent").append(html);
            $.ajax({
                type: "POST",
                url: basePath + "/others/appmes",
                data: JSON.stringify(postdata),
                contentType: 'application/json',
                dataType: "XML",
                success: function (res) {
                    if($(res).find("Content").text()){
	                   var rehtml = "<div class='kfitem flex flex-row'>" +
	                        "<div class='kfhead flex flex-row flex-center'>" +
	                        "<img src='/image/others/chaticon.png' class='kfimg'>" +
	                        "</div>" +
	                        "<div class='kfcontent flex flex-column'>" +
	                        "<span>客服代表</span>"+
	                        "<span class='kfques'>" + $(res).find("Content").text().replace(/(\n|&#13;)/g, '<br>') + "</span>" +
	                        "</div>" +
	                        "</div>";
	                    $(".chatContent").append(rehtml);
                    }

                  //滚动条自动到最低端
                     scrollBottom();
                  //清空
                    $(".entry").val("")
                },
                error:function(res){
                    layer.open({
                        content:'发送失败'
                        ,skin: 'msg'
                        ,time: 2 //2秒后自动关闭
                    });
                },
                complete: function (res) {

                }
            })
        }
        
        $(document).ready(function(){
        	getQQface();
	        connect();
	        showHisMsgOneDay();
	        setInterval(function(){ //每隔1分钟发送一次心跳
	            if (ws.readyState == ws.OPEN) {
	                ws.send("HeartBeat");
	            }
	        },1000);
	        
	        $(".chatContent").on("click",function(){
	        	document.activeElement.blur();
	        });
        });
    </script>
</body>

</html>