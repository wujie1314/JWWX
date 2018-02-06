<%@page language="java" contentType="text/html;charset=UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta content="telephone=no" name="format-detection">
<title>视频截图</title>
<link rel="stylesheet" href="${ctx}/css/road/style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/road/main.css" />
</head>
<body style="background: #fff;">
	<div class="container">
		<div class="loading-warp">
			<div class="box">
				<div>
					<img src="${ctx}/image/road/images/loading.gif" /> <span
						class="text">下拉开始刷新</span>
				</div>
			</div>
		</div>
		<ul class="wx02 white-bg wx02-first wx001">
			<li><span class="input-title icon-title"
				style="background: url(${ctx}/image/road/001-01.png) left center no-repeat;background-size: 35%;">城区</span>
				<span class="no-text jiantou">周一,周二,周三</span></li>
			<li><span class="input-title icon-title"
				style="background: url(${ctx}/image/road/001-02.png) left center no-repeat;background-size: 35%;">高速</span>
				<span class="no-text jiantou">周一,周二,周三</span></li>
			<li><span class="input-title icon-title"
				style="background: url(${ctx}/image/road/001-03.png) left center no-repeat;background-size: 35%;">车站</span>
				<span class="no-text jiantou">周一,周二,周三</span></li>
			<li style="border-bottom: 1px solid #DCDDDD; padding-bottom: 15px;">
				<span class="input-title icon-title"
				style="background: url(${ctx}/image/road/001-04.png) left center no-repeat;background-size: 35%;">路况</span>
				<span class="no-text jiantou">周一,周二,周三</span>
			</li>
		</ul>
		<div class="wx01-title white-bg overflow"
			style="margin: 0px; font-size: 18px; padding: 0px; padding-left: 10px;">
			<div class="clear">热门收藏</div>
		</div>
		<div style="background: #fff;" class="overflow wx003">
			<p class="tips">可通过下拉屏幕刷新数据</p>
			<ul class="wx01-second overflow" style="padding-bottom: 0px;"
				id="myCollent">

				<li>
					<dl>
						<dt class="overflow">
							<img class="big-pic" src="${ctx}/image/road/05-01.png">
							<div class="bg"></div>
							<p class="text">长途汽车站-大厅</p>
						</dt>
						<dd>
							<img src="${ctx}/image/road/01-xin-icon.png"> <span>23</span>
						</dd>
					</dl>
				</li>
			</ul>
		</div>
	</div>

	<script type="text/javascript" src="${ctx}/js/road/touche.js"></script>
	<script type="text/javascript" src="${ctx}/js/road/p-pull-refresh.js"></script>
	<script type="text/javascript">
    var $statu = $('.loading-warp .text');
    var pullRefresh = $('.wx01-second').pPullRefresh({
        $el: $('.wx01-second'),
        $loadingEl: $('.loading-warp'),
        sendData: null,
        url: 'http://172.2.1.123:8088/videoImg/list?openId=oHMgqxMPkOAH7CzAmPISCkC9oI-4&type=0',
        callbacks: {
            pullStart: function(){
            	alert(1);
                $statu.text('松开开始刷新');
            },
            start: function(){
            	alert(2);
                $statu.text('数据刷新中···');
            },
            success: function(response){
            	alert(3);
                $statu.text('数据刷新成功！');
            },
            end: function(){
            	alert(4);
                $statu.text('下拉刷新结束');
            },
            error: function(){
            	alert(5);
                $statu.text('找不到请求地址,数据刷新失败');
            }
        }
    });
    $(function(){
        $(".wx02-first li" ).click(function(){
            $(this ).find(".icon-title" ).css('color','#ccc')
        })
        //loadVideoImg();
    })
    //pullRefresh.setDestroy(true);
	
    function loadVideoImg(){
    	var openId = '${openId}';
    	alert(openId);
    	$("#myCollent").html('');
    	$.ajax({
			url : "${ctx}/videoImg/queryList",
			cache : false,
			type : "post",
			dataType : "json",
			data : {
				"type" : 0,
				"openId" : openId
			},
			success : function(data) {
				var ul = '';
				alert(data);
				$.each(data, function(i,vide) {
					ul += "<li><dl><dt class=\"overflow\"><img class=\"big-pic\" src=\"${ctx}/"+vide.videUrl+"\"><div class=\"bg\"></div><p class=\"text\">"+vide.videShowName+"</p></dt><dd><img src=\"${ctx}/image/road/01-xin-icon.png\"><span>"+vide.collectNum+"</span></dd></dl></li>";
				});
				alert(ul);
				$("#myCollent").html(ul);
			}
		});
    }
    
</script>
</body>
</html>
