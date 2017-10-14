<%@page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="road_taglibs.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta content="telephone=no" name="format-detection">
<title>视频截图</title>
<link rel="stylesheet" href="${ctx}/css/road/style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/road/main.css" />
<style>
    /*.swiper-wrapper img{
        width: 100%;
    }*/
    .pagination {
        position: absolute;
        left: 0;
        text-align: center;
        bottom: .1rem;
        width: 100%;
        z-index: 98;
    }
    .top-banner{position: relative;}
    .top-banner .top-left{position: absolute;left: 0px;top: 43%;width: 8%;}
    .top-jiantou{display: none;}
    .top-banner .top-right{position: absolute;right: 0px;top:43%;width: 8%}
	.top-banner .full-cover{position: absolute;right: 0px;top:0px;width: 100%}
</style>
</head>
<body style="background: #fff;">
	<div class="container">
		<div class="loading-warp">
			<div class="box">
				<div>
					<img src="${ctx}/image/road/loading.gif" /> <span class="text">下拉开始刷新</span>
				</div>
			</div>
		</div>
		<div style="background: #fff;">
	        <div style="margin-top: 15px;padding: 0 15px;" class="swiper-container">
	            <div class="swiper-wrapper">
	                <div class="swiper-slide top-banner">
	                    <img style="width: 100%;" id="showImg" src="" alt="图片加载中">
	                    <img class="full-cover top-jiantou" id="hintImg" src="">
	                    <!-- 
	                    <img class="top-left top-jiantou" src="${ctx}/image/road/006-04.png">
	                    <img class="top-right top-jiantou" src="${ctx}/image/road/006-05.png">
	                     -->
	                </div>
	            </div>
	        </div>
    	</div>
    	<div class="wx006 white-bg wx006-first overflow" style="padding-bottom: 0px;">
          	<span class="share">
              <img class="weibo"  src="${ctx}/image/road/006-02.png">
          	  <img class="weixin" src="${ctx}/image/road/006-03.png">
          	</span>
          
          	<a class="direct" style="display: none;" id="isHintBtn" onclick="showHintImg();">显示方向</a>
      	</div>
    	<%@include file="bottom.jsp"%>
	</div>

	<script type="text/javascript" src="${ctx}/js/road/touche.js"></script>
	<script type="text/javascript" src="${ctx}/js/road/p-pull-refresh.js"></script>
	<script type="text/javascript">
    
    var openId = '${openId}';
    var videId = '${videId}';
    
    var statu = $('.loading-warp .text');
    var pullRefresh = $('body').pPullRefresh({
        $el: $('body'),
        $loadingEl: $('.loading-warp'),
        sendData: null,
        url: '${ctx}/videoRoad/query?openId='+openId+'&videId='+videId,
        callbacks: {
            pullStart: function(){
                statu.text('松开开始刷新');
            },
            start: function(){
                statu.text('数据刷新中···');
            },
            success: function(response){
            	fillAjaxData(response);
                statu.text('数据刷新成功！');
            },
            end: function(){
                statu.text('下拉刷新结束');
            },
            error: function(){
                statu.text('找不到请求地址,数据刷新失败');
            }
        }
    });
    
	function loadVideoImg(){
    	$("#myCollent").html('');
    	$.ajax({
			url : "${ctx}/videoRoad/query",
			cache : false,
			type : "post",
			dataType : "json",
			data : {
				"videId" : videId,
				"openId" : openId
			},
			success : function(data) {
				fillAjaxData(data);
			}
		});
    }
	
	function fillAjaxData(data){
		if(data){
			var isHintImg = data.isHintImg;
			var hintImgUrl = data.hintImgUrl;
			var videUrl = data.videUrl;
			$("#showImg").attr('src',videUrl);
			if(isHintImg == 1){
				$("#hintImg").attr('src',hintImgUrl);
				$("#hintImg").css('display','block');
				$("#isHintBtn").css('display','block');
			}
		} else {
			alert("加载失败");
		}
	}
	
	function showHintImg(){
		var display =$('#hintImg').css('display');
		if(display == 'block'){//显示，设置隐藏
		   $("#hintImg").css('display','none'); 
		} else {
		   $("#hintImg").css('display','block');
		}
	}
	
    $(function(){
    	loadVideoImg();
    })
    
</script>
</body>
</html>
