var pageSize = 25;
var hisNum;
var date = new Date();
var BigImgIndex = 0;
Date.prototype.Format = function (fmt) { //author: meizz 
	var o = {
			"M+": this.getMonth() + 1, //月份 
			"d+": this.getDate(), //日 
			"h+": this.getHours(), //小时 
			"m+": this.getMinutes(), //分 
			"s+": this.getSeconds(), //秒 
			"q+": Math.floor((this.getMonth() + 3) / 3), //季度 
			"S": this.getMilliseconds() //毫秒 
	};
	if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for (var k in o)
		if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
  		    return fmt;
		}

var heartCheck = {
    timeout: 60000,//60ms
    timeoutObj: null,
    serverTimeoutObj: null,
    reset: function(){
        clearTimeout(this.timeoutObj);
        clearTimeout(this.serverTimeoutObj);
		this.start();
    },
    start: function(){
        var self = this;
        this.timeoutObj = setTimeout(function(){
            ws.send("HeartBeat");
            self.serverTimeoutObj = setTimeout(function(){
                ws.close();//如果onclose会执行reconnect，我们执行ws.close()就行了.如果直接执行reconnect 会触发onclose导致重连两次
            }, self.timeout)
        }, this.timeout)
    },
}

function showHisMsgOneDayInit(){
	if(typeof(hisNum) == "undefined") hisNum = 0;
	var eTime = date.Format("yyyy-MM-dd hh:mm:ss");
	date.setDate(date.getDate() - 3);
	var bTime = date.Format("yyyy-MM-dd hh:mm:ss");
	date.setDate(date.getDate() + 3);
	$.ajax({
		type:"get",
		url:basePath + "/others/getMsgOneDay",
		dataType:"json",
		data:{
			openid:openId,
			bTime:bTime,
			eTime:eTime,
			begin:hisNum,
			end:hisNum+pageSize
		},
		success:function(data){
			if(data.length > 0){
				$(".history").css({
					"display":"block"
				});
			}
		}
	});
}

function showHisMsgOneDay(){
	$(".chatContent").unbind("scroll");
	if(typeof(hisNum) == "undefined") hisNum = 0;
	var eTime = date.Format("yyyy-MM-dd hh:mm:ss");
	date.setDate(date.getDate() - 3);
	var bTime = date.Format("yyyy-MM-dd hh:mm:ss");
	date.setDate(date.getDate() + 3);
	var hisHeight = 0;
	$.ajax({
		type:"get",
		url:basePath + "/others/getMsgOneDay",
		dataType:"json",
		data:{
			openid:openId,
			bTime:bTime,
			eTime:eTime,
			begin:hisNum,
			end:hisNum+pageSize
		},
		success:function(data){
			if(data.length > 0){
				if(hisNum == 0){
					$(".chatContent").append("<div class='discon'>以上是历史消息</div>");
				}else{
					hisHeight = $(".chatContent")[0].scrollHeight - $(".chatContent").scrollTop();
				}
				hisNum += pageSize;
				for(var i=0;i<data.length;i++){
					if(data[i].msgId==null){
						leftMessage(data[i]);
					}else{
						rightMessage(data[i]);
					}
				}
				$(".chatContent").scrollTop($(".chatContent")[0].scrollHeight - hisHeight);
				if(data.length >= pageSize){
					$(".chatContent").on("scroll",scrollLoadDataEvent);
				}
			}
		}
	});
}

function leftMessage(data){
	var rehtml = "";
	if("autotext" == data.msgType && data.content != "用户已离开"){
    	rehtml += "<div class='kfitem flex flex-row'>" +
            "<div class='kfhead flex flex-row flex-center'>" +
            "<img src='/image/others/chaticon.png' class='kfimg'>" +
            "</div>" +
            "<div class='kfcontent flex flex-column'>" +
            "<span>客服代表</span>"+ 
            "<span class='kfques'>" + replaceQQFace(data.content.replace(/(\n|&#13;)/g, '<br>')) + "</span>" +
            "</div>" +
            "</div>";
	}
	if("text" == data.msgType){
    	rehtml += "<div class='kfitem flex flex-row'>" +
            "<div class='kfhead flex flex-row flex-center'>" +
            "<img src='/image/others/chaticon.png' class='kfimg'>" +
            "</div>" +
            "<div class='kfcontent flex flex-column'>" +
            "<span>客服代表</span>"+ 
            "<span class='kfques'>" + replaceQQFace(data.msgContent.replace(/(\n|&#13;)/g, '<br>')) + "</span>" +
            "</div>" +
            "</div>";
	}else if ("image" == data.msgType) {
		rehtml += "<div class='kfitem flex flex-row'>" +
        "<div class='kfhead flex flex-row flex-center'>" +
        "<img src='/image/others/chaticon.png' class='kfimg'>" +
        "</div>" +
        "<div class='kfcontent flex flex-column'>" +
        "<span>客服代表</span>"+ 
        "<div class='bjimg'>" + "<img src=" + data.imageUrl + " width='150px' onclick='imgToBig(this)'></img>" +
        "</div>" +
        "</div>";
	}else if("url" == data.msgtype){
		rehtml += "<div class='kfitem flex flex-row'>" +
        "<div class='kfhead flex flex-row flex-center'>" +
        "<img src='/image/others/chaticon.png' class='kfimg'>" +
        "</div>" +
        "<div class='kfcontent flex flex-column'>" +
        "<span>客服代表</span>"+ 
        "<span class='kfques'>" + data.text.content + "</span>" +
        "</div>" +
        "</div>";
	}
	$(".chatContent").prepend(rehtml);
}

function rightMessage(data){
	var html = "";
	if("text" == data.msgType && data.content != null){
		html += "<div class='khitem flex flex-row flex-end'>" +
            "<div class='khcontent flex flex-column'>" +
            "<span class='khname'>" + userName + "</span>" +
            "<span class='khquestion'>" + replaceQQFace(data.content.replace(/(\n|&#13;)/g, '<br>')) + "</span>" +
            "</div>" +
            "<div class='khhead'>" +
            "<img src='/image/others/usericon.png' width='100%'>" +
            "</div>" +
            "</div>";
	}else if ("image" == data.msgType) {
		html += "<div class='khitem flex flex-row flex-end'>" +
	        "<div class='khcontent flex flex-column'>" +
	        "<span class='khname'>" + userName + "</span>" +
	        "<div class='bjimg'>" + "<img src=" + data.imageUrl + " width='150px'></img>" +
	        "</div>" +
	        "<div class='khhead'>" +
	        "<img src='/image/others/usericon.png' width='100%'>" +
	        "</div>" +
	        "</div>";
	}
	$(".chatContent").prepend(html);
}

function scrollLoadDataEvent(){
	document.activeElement.blur();
    if ($(".chatContent").scrollTop() == 0) {  
    	showHisMsgOneDay();
    } 
}

function scrollBottom(){
	$('.chatContent').scrollTop($('.chatContent')[0].scrollHeight);
}

function imgToBig(obj){
	var src=$(obj).attr("src");
	var image = '<div style="width:100%;height:100%;background-color:black;overflow:auto" onclick="closeBigImg()">'+
		'<img src="'+src+'" style="width:100%;vertical-align:middle;"/>'+
		'<span style="display: inline-block;height: 100%;vertical-align: middle;"></span> </div>';
	BigImgIndex =layer.open({
				type:1
				,content:image
				,style: 'position:fixed; left:0; top:0; width:100%; height:100%; border: none; -webkit-animation-duration: .5s; animation-duration: .5s;'
			});
}

function closeBigImg(){
	layer.close(BigImgIndex);
}