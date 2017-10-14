<%@page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="road_taglibs.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta content="telephone=no" name="format-detection">
<title>${parentName }</title>
<link rel="stylesheet" href="${ctx}/css/road/style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/road/main.css" />
<style>
	.full-cover{position: absolute;right: 0px;top:0px;width: 100%;z-index: 10;}
</style>
</head>
<body>
	<div class="container" style="background: #fff;">
		<div class="loading-warp">
			<div class="box">
				<div>
					<img src="${ctx}/image/road/loading.gif" /> <span class="text">下拉开始刷新</span>
				</div>
			</div>
		</div>

		<div class="overflow wx003">
        <ul id="imgUl" class="wx01-second overflow" style="padding-bottom: 0px;">
            <li>
                <dl>
                    <dt class="overflow">
                        <img class="big-pic" src="${ctx}/image/road/05-01.png"/>
                        <img class="full-cover" src="${ctx}/image/road/05-01.png"/>
                        <div class="bg"></div>
                        <p class="text">长途汽车站-大厅</p>
                    </dt>
                    <dd>
                        <img src="${ctx}/image/road/01-xin-icon.png">
                        <span>23</span>
                    </dd>
                </dl>
            </li>
        </ul>
        </div>
    	<%@include file="bottom.jsp"%>
	</div>

	<script type="text/javascript" src="${ctx}/js/road/touche.js"></script>
	<script type="text/javascript" src="${ctx}/js/road/p-pull-refresh.js"></script>
	<script type="text/javascript">
    
    var openId = '${openId}';
    var parentId = '${parentId}';
	function loadVideoImg(){
    	$("#imgUl").html('');
    	$.ajax({
			url : "${ctx}/videoRoad/queryList",
			cache : false,
			type : "post",
			dataType : "json",
			data : {
				"type" : 2,
				"openId" : openId,
				"parentId" : parentId
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
			var isCollect = vide.isCollect;
			if(showNameLength > 10){
				showName = showName.substring(0,10) + "..."
			}
			var isHintImg = vide.isHintImg;
			var hintImgUrl = vide.hintImgUrl;
			var hintUl="";
			if(isHintImg == 1){
				hintUl = "<img onclick='hrefDetails("+vide.videId+")' class=\"full-cover\" src=\""+vide.hintImgUrl+"\"/>";
			}
			//if(isCollect == 1){
				//ul += "<li><dl><dt class=\"overflow\" onclick='hrefDetails("+vide.videId+")'><img class=\"big-pic\" src=\""+vide.videUrl+"\">"+hintUl+"<div class=\"bg\"></div><p class=\"text\">"+showName+"</p></dt><dd onclick='cancelCollent("+vide.videId+")'><img src=\"${ctx}/image/road/01-xin-icon.png\"><span>"+vide.collectNum+"</span></dd></dl></li>";
			//} else {
				//ul += "<li><dl><dt class=\"overflow\" onclick='hrefDetails("+vide.videId+")'><img class=\"big-pic\" src=\""+vide.videUrl+"\">"+hintUl+"<div class=\"bg\"></div><p class=\"text\">"+showName+"</p></dt><dd onclick='certainCollent("+vide.videId+")'><img src=\"${ctx}/image/road/01-xin-icon1.png\"><span>"+vide.collectNum+"</span></dd></dl></li>";
			//}
			if(isCollect == 1){
				//ul += "<li><dl><dt class=\"overflow\" ><img class=\"big-pic wx-switch-img\" src=\""+vide.videUrl+"\"><div class=\"bg\"></div><p class=\"text p-no-newline\">"+showName+"</p></dt><dd onclick='cancelCollent("+vide.videId+")'><img src=\"${ctx}/image/road/01-xin-icon.png\"><span>"+vide.collectNum+"</span></dd></dl></li>";
				ul += "<li style=\"border: 1px solid #cbcbcb;margin-right: 1px;background-color: #dedede;\"><dl><dt class=\"overflow\" style=\"position: relative;height:130px\"><img onclick='hrefDetails("+vide.videId+")' class=\"big-pic wx-switch-img\" style=\"width: 100%;height:130px;\" src=\""+vide.videUrl+"\">"+hintUl+"<img onclick='cancelCollent("+vide.videId+")' style=\"position: absolute;right: 5%;top: 5%;width: 17%;z-index: 20;\" src=\"${ctx}/image/road/01-xin-icon.png\"> </dt><dd style=\" height:40px; padding: 0px; line-height: 40px\"><p class=\"text p-no-newline\">"+showName+"</p></dd></dl></li>";
			} else {
				//ul += "<li><dl><dt class=\"overflow\" ><img class=\"big-pic wx-switch-img\" src=\""+vide.videUrl+"\"><div class=\"bg\"></div><p class=\"text p-no-newline\">"+showName+"</p></dt><dd onclick='certainCollent("+vide.videId+")'><img src=\"${ctx}/image/road/01-xin-icon1.png\"><span>"+vide.collectNum+"</span></dd></dl></li>";
				ul += "<li style=\"border: 1px solid #cbcbcb;margin-right: 1px;background-color: #dedede;\"><dl><dt class=\"overflow\" style=\"position: relative;height:130px\"><img onclick='hrefDetails("+vide.videId+")' class=\"big-pic wx-switch-img\" style=\"width: 100%;height:130px;\" src=\""+vide.videUrl+"\">"+hintUl+"<img onclick='certainCollent("+vide.videId+")' style=\"position: absolute;right: 5%;top: 5%;width: 17%;z-index: 20;\" src=\"${ctx}/image/road/01-xin-icon1.png\"> </dt><dd style=\" height:40px; padding: 0px; line-height: 40px\"><p class=\"text p-no-newline\">"+showName+"</p></dd></dl></li>";
			}
		});
		$("#imgUl").html(ul);
	}
	
	function hrefDetails(videId){
		location.href="${ctx}/videoRoad/details?openId="+openId+"&videId="+videId;
	}

	function certainCollent(videId){
		$.post("${ctx}/videoImg/insertCollent",
		    {"videId":videId,"openId":openId},
		    function(data) {
		    	//刷新列表 
		    	var content = eval('(' + data + ')');
		    	if("0"==content.code){
		    		document.location.reload();
		    		//location.href ="${ctx}/parcel/editInfoPage.htm?parcState=0";
		    	} else {
		    		alert(content.msg);
		    	}
		});
	}
	function cancelCollent(videId){
		$.post("${ctx}/videoRoad/delCollent",
		    {"videId":videId,"openId":openId},
		    function(data) {
		    	//刷新列表 
		    	var content = eval('(' + data + ')');
		    	if("0"==content.code){
		    		loadVideoImg();
		    	} else {
		    		alert(content.msg);
		    	}
		    });
	}
	
    $(function(){
        loadVideoImg();
    })
    
</script>
</body>
</html>
