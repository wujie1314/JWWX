function hrefDetails(videId){
	location.href="${ctx}/videoRoad/details?openId="+openId+"&videId="+videId;
}

function certainCollent(videId,openId){
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