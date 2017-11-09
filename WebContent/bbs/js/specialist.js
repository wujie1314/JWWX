//var openID = getURLName("openID");
openID = "oNwnMv75CAORzVhzjt-7J8lWSQtA";
$(function(){
	init();
});

function addInvitationDiv(data,dataPic,dataComment){
	//console.log(dataComment);
	// data[0] id;data[1]:头像， data[2]:内容，data[3]:时间，data[4]：评论量,data[5]:用户名
	// dataPic[0] id;dataPic[1]:所有图片base64编码,
	var InvitationDiv = "";
	InvitationDiv += "<div class='content' id='content' >";
	InvitationDiv += "<div class='head'>" 
				  +     "<div class='headImage'><img src='data:image/jpeg;base64,"+ data[1] +"' style='width: 80%;height: 98%;margin-left: 10%;margin-top: 10%;'></div>"
				  +     "<div class='headNANDT'><div class='headName'>"+ data[5] + "</div>"
				  +		"<div class='headTime'>"+ data[3]+"</div></div>"
				  +	 "</div>";
	InvitationDiv += "<div class='contentText'>"+ data[2] +"</div>";
	var commentDiv = addComment(data[0],dataComment);
	if(commentDiv.length != 0)
		InvitationDiv += "<div class=AcommentText style='width: 100%;height: 20%;'>" + addComment(data[0],dataComment) + "</div>";
	var picDiv = getPictrue(data[0],dataPic);
	if(picDiv.length != 0)
		InvitationDiv += "<div class='contentPicture' style='margin-left:70px;margin-top:10px;'>"+ picDiv +"</div>";
	InvitationDiv += "<div class='foot' style='margin-left:70px;margin-top:10px;'>"+"<span>当前评论："+data[4] +"| </span>"+ "<span onclick='reply("+ data[0] +")'>回复 | </span>" + "<span onclick='particulars("+ data[0] +")'> 详情</span>" + "</div>";
	InvitationDiv += "</div>";
	$("#allInvitation").append(InvitationDiv);
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

//加评论
function addComment(id,dataComment){
	var commentText = "";
	var count  = 0;
	for(var i=0; i<dataComment.length; i++){
		if(id == dataComment[i][0]){
			commentText += "<div class='AComment' style='width: 100%;'><img src='data:image/jpeg;base64,"+ dataComment[i][3] + "' style='height: 53%;margin-left: 20%;margin-top: 2%;'>" +
					"<span>:"+ dataComment[i][1] +"</span>" + "</div>"
			count++;
			if(count == 1) break;
		}
	}
	return commentText;
}

function init(){
	 $.ajax({
			type : "post",
			url : "/mineController/initSpecialist",
			dataType : "json",
			async: true,
			data : {
				begin:0,
				end:11
			},
			success : function(data){
				//console.log(data.result);
				//console.log(data.resultPic);
				addInvitation(data);
	        }
		});
}

function addInvitation(data){
	for(var i=0; i<data.result.length; i++){
		addInvitationDiv(data.result[i],data.resultPic,data.resultComment);
	}
}