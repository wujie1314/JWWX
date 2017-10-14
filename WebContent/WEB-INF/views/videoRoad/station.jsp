<%@page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="road_taglibs.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta content="telephone=no" name="format-detection">
<title>车站截图</title>
<link rel="stylesheet" href="${ctx}/css/road/style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/road/main.css" />
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

        <ul id="imgUl" class="wx02 white-bg wx05-four wx002 overflow" style="margin-top: 0;padding-bottom: 0px;">
            <li>
              <div class="top">
                  <img class="pic" src="images/05-01.png">
              </div>
              <p class="middle-center">渝中区</p>
            </li>
        </ul>
    
    <%@include file="bottom.jsp"%>
	</div>

	<script type="text/javascript">
    
    var openId = '${openId}';
  
	function loadVideoImg(){
    	$("#imgUl").html('');
    	$.ajax({
			url : "${ctx}/videoRoad/queryList",
			cache : false,
			type : "post",
			dataType : "json",
			data : {
				"type" : 3,
				"parentId" : -1,
				"openId" : openId
			},
			success : function(data) {
				fillAjaxData(data);
			}
		});
    }
	
	function fillAjaxData(data){
		var ul = '';
		$.each(data, function(i,vide) {
			var showNameLength = vide.videShowName.length;
			var showName = vide.videShowName;
			var videName = vide.videName;
			//if(showNameLength > 10){
				//showName = showName.substring(0,10) + "..."
			//}
			var encodeShowName = encodeURIComponent(vide.videShowName);
			ul += "<li style=\"border: 1px solid #cbcbcb;margin-right: 1px;background-color: #dedede;\" onclick=\"hrefNext('"+vide.videId+"','"+encodeShowName+"')\"><div class='top'><img class='pic wx-switch-img' style=\"height:160px\" src='"+vide.videUrl+"'></div><p class=\"middle-center p-no-newline\">"+showName+"</p></li>";
		});
		ul+="<span class=\"last overflow\"></span>";
		$("#imgUl").html(ul);
		
		//initImagePreview();
	}
	
	function hrefNext(videId,parentName){
		location.href="${ctx}/videoRoad/station_next?openId="+openId+"&parentId="+videId+'&parentName='+encodeURIComponent(parentName);
	}
	
    $(function(){
        loadVideoImg();
    })
    
</script>
</body>
</html>
