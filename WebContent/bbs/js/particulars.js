function getURLName(name) {
		    var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
		    var r = window.location.search.substr(1).match(reg);
		    if (r != null) {
		        return unescape(r[2]);
		    }
		    return null;
}
//var tellID = "1510140742222";
//var openID = "oNwnMv75CAORzVhzjt-7J8lWSQtA";

var openID = getURLName("openID");
var tellID = getURLName("tellID");
$(function(){
	init();
})
function init(){
	//alert(tellID);
	 $.ajax({
			type :"post",
			url : "/particularsController/init",
			dataType : "json",
			async: true,
			data : {
				tellID:tellID
			},
			success : function(data){
				//console.log(data);
				addContent(data);
				 
	        },
	        error:function(e){
	        	alert("error");
	        	//console.log(e);
	        }
		});
}
function addContent(data){
	addTellMessage(data.tell[0]);
	addPic(data.pic);
	addCommentDiv(data.comment);
}

//加载说说
//message[0]:内容 message[1]:时间  message[2]:评论量 message[3]:用户名 message[4]:头像 message[5]:ID
function addTellMessage(message){
	var headDiv = "";
	headDiv += "<div class='headImg'><img src='data:image/jpeg;base64,"+ message[4] +"' style='width: 80%;height: 80%;margin-left: 10%;margin-top: 10%;'></div>"
			+  "<div class='nameAndTime'><div class='userName'>"+ message[3] +"</div>"
			+  "<div class='tellTime'>"+ message[1] + "</div><div>";
	$("#tellMessage").append(headDiv);
	var contentDiv = "";
	contentDiv = message[0];
	$("#tellContent").append(contentDiv);
	
	var operationDiv = "";
	operationDiv += "<span onclick='' style='float: right;margin-right: 10%;'>回复</span>"
	$("#tellOperation").append(operationDiv);
}
//加载图片
function addPic(pic){
	var picDiv = "";
	for(var i=0; i<pic.length; i++)
		picDiv += "<img src='"+ pic[i] +"' style='width: 30%;height: 90%;margin-top: 5%;margin-left: 5%;'>";
	$("#tellPicture").append(picDiv);
}

//加载评论
//comment[0]：时间  comment[1]：评论内容 comment[2]：评论人头像 comment[3] 评论人ID
function addCommentDiv(comment){
	for(var i=0; i<comment.length; i++)
		addACommentDiv(comment[i])
}

//comment[0]：时间  comment[1]：评论内容 comment[2]：评论人头像 comment[3] 评论人ID
function addACommentDiv(comment){
	var commentDiv = "";
	commentDiv += "<div class='AComment'>" 
			   +"<img src='data:image/jpeg;base64,"+ comment[2] +"' style='height: 100%;'>"
			   + "<span class='commentSpan'>:</span>"
			   + comment[1] + "</div>";
	$("#tellComment").append(commentDiv);

}

//发表评论
function review(){
	 var reviewData = $("#message").html();
	 //alert(reviewData);
	 $.ajax({
			type :"post",
			url : "/particularsController/review",
			dataType : "json",
			async: true,
			data : {
				tellID:tellID,
				reviewData:reviewData,
				openID : openID
			},
			success : function(data){
				window.location.href = "bbs/jsp/particulars.jsp?tellID=" + tellID + "&openID=" + openID;
				// alert(data.result);
				//addContent(data)
				 
	        },
	        error:function(e){
	        	//alert("error");
	        	//console.log(e);
	        }
		});
}