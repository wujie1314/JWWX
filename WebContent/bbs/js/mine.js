//var openID = getURLName("openID");
var openID = "oNwnMv75CAORzVhzjt-7J8lWSQtA";
$(function(){
	if(openID != null){
		initUser();
	}
	init();
});

function initUser(){
	 $.ajax({
			type : "post",
			url : "/mineController/initUser",
			dataType : "json",
			async: true,
			data : {
				openID:openID
			},
			success : function(data){
				console.log(data);
	        }
		});
}
function init(){
	 $.ajax({
			type : "post",
			url : "/mineController/init",
			dataType : "json",
			async: true,
			data : {
				openID:openID
			},
			success : function(data){
				//console.log(data.result);
				console.log(data.resultPic);
				addInvitation(data);
	        }
		});
}

function addInvitation(data){
	for(var i=0; i<data.result.length; i++){
		addInvitationDiv(data.result[i],data.resultPic);
	}
}

function addInvitationDiv(data,dataPic){
	// data[0] id;data[1]:头像， data[2]:内容，data[3]:时间，data[4]：评论量,data[5]:用户名
	// dataPic[0] id;dataPic[1]:所有图片base64编码,
	var InvitationDiv = "";
	InvitationDiv += "<div class='content' id='content' >";
	InvitationDiv += "<div class='head'>" 
				  +     "<div class='headImage'><img src='data:image/jpeg;base64,"+ data[1] +"' style='width: 80%;height: 80%;margin-left: 10%;margin-top: 10%;'></div>"
				  +     "<div class='headNANDT'><div class='headName'>"+ data[5] + "</div>"
				  +		"<div class='headTime'>"+ data[3]+"</div></div>"
				  +	 "</div>";
	InvitationDiv += "<div class='contentText' style='margin-left:70px;'>"+ data[2] +"</div>";
	InvitationDiv += "<div class='contentPicture' style='margin-left:70px;margin-top:10px;'>"+ getPictrue(data[0],dataPic)  +"</div>";
	InvitationDiv += "<div class='foot' style='margin-left:70px;margin-top:10px;'>"+"<span>当前评论："+data[4] +"| </span>"+ "<span onclick='reply("+ data[0] +")'>回复 | </span>" + "<span onclick='particulars("+ data[0] +")'> 详情</span>" + "</div>";
	InvitationDiv += "</div>";
	$("#allInvitation").append(InvitationDiv);
}

function reply(id){
	window.location.href = "bbs/jsp/particulars.jsp?tellID=" + id;
}

function particulars(id){
	alert("reply" + id);
}
//加图片
function getPictrue(id,dataPic){
	var picDiv = "";
	for(var i=0; i<dataPic.length; i++){
		if(dataPic[i][0] == id)
			picDiv += "<img src='"+ dataPic[i][1]+ "' style='width: 30%;height: 100%;'>";
	}
	return picDiv;
}