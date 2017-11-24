var imgFile = new Array();

//压缩图片进行显示
$("#bbsImg_file").change(function () {
	/* 压缩图片 */
	lrz(this.files[0], {
		width: 300 //设置压缩参数
	}).then(function (rst) {
			var img = new Image(); 
			img.src = rst.base64;
			$("#bbs_imageDiv").append(img);
			imgFile[imgFile.length] = rst.base64;		
	}).catch(function (err) {
		
	}).always(function () {
		/* 必然执行 */
	})
})

//发表
function announce(){
	var content = $("#divText").val();
	var imgFileString = JSON.stringify(imgFile);
	var expert_name = $('#expert_name').val();
	var expert_ID = $("#expert_ID").val();
	var expert_title = $("#expert_title").val();
	var phone = $("#expert_phone").val();
    $.ajax({  
        async:false,//是否异步  
        cache:false,//是否使用缓存  
        type: "POST",  
        data:{
        	imgFile:imgFileString,
        	content:content,
        	specialistOppenID:expert_ID,   //"专家的ID" 
        	name: expert_name, //专家的名字
        	title:expert_title, //标题
        	userOpenID:nowOpenid //用户的openID
        	},  
        dataType: "json",  
        timeout: 1000,  
        contentType : 'application/x-www-form-urlencoded; charset=utf-8',  
        url: "/WriteAboutController/specialist",  
        success: function(result){
        	//console.log("tellID 为 ========  "+ result);
        	send_link(expert_ID,result,phone); //推送论坛链接
        	// 关闭窗口
        	closeWindow("bbs-window");
        	// 清空窗口内容
        	clear_bbs_window();
        },  
        error: function(result){  
        	console.log(result)       
        }  
    });  
}
/*请空bbs-window*/
function clear_bbs_window(){
	cancel_selected();
	$("#divText").val("");
	$("#bbsImg_file").val("");
	$("#bbs_imageDiv").empty();
}

/*专业点击被选中*/
function bbs_click_selected(dd){
	cancel_selected();
	$(dd).find("li").addClass("export_selected");
	var span = $(dd).find("span");
	var input = $(dd).find("input").val();
	//console.log(span);
	$('#expert_ID').val($(span).attr("ID"));
	$('#expert_phone').val(input);
	$('#expert_name').val($(span).text());
}
/*只能选中一个*/
function cancel_selected(){

	$(".bbs_li_list").each(function(){
		$(this).removeClass("export_selected");
	});
}
/*发表成功后分别给专家，和微信用户发链接信息*/
function send_link(expert_ID,tellID,phone){
	 $.ajax({  
	        async:false,//是否异步  
	        cache:false,//是否使用缓存  
	        type: "POST",  
	        data:{
	        	openId:nowOpenid,
	        	expertId:expert_ID,
	        	tellId: tellID,
	        	phone:phone
	        	},  
	        dataType: "json",  
	        timeout: 1000,  
	        contentType : 'application/x-www-form-urlencoded; charset=utf-8',  
	        url: "/WriteAboutController/sendLink",  
	        success: function(result){  
	        	console.log("成功推送链接");
	        },  
	        error: function(result){  
	        	console.log(result)       
	        }  
	    });  
}

/*拼接专家列表html*/
function fullExpertListHtml(data){
	$('#bbs_left_list').empty();
	var html = "";
	for (var i = 0; i < data.length; i++) {
		html += "<ul class= 'bbs_ul_list' onclick='bbs_click_selected(this)' >"
		html += "<li class= 'bbs_li_list'>";
		html += "<img class='j-img' src='http://p1.music.126.net/H3QxWdf0eUiwmhJvA4vrMQ==/1407374893913311.jpg?param=66y66' " +
				"data-src='http://p1.music.126.net/H3QxWdf0eUiwmhJvA4vrMQ==/1407374893913311.jpg?param=66y66' />";
		html += "<div class='bbs_expert_info'>";
		html += "<span class='expert_name' id='"+data[i].id+"''>"+data[i].expertName+"</span>";
		html += "<input type='hidden' value='"+data[i].phone+"'>"
		html += "<p class='expert_des'>"+data[i].introduce+"</p>";			
		html += "</div></li></ul>";
		$('#bbs_left_list').append(html);
		html = "";
	}
}

/*获取专家列表*/
function getExpertInfo(){
	 var data = null;
	 $.ajax({  
	        async:false,//是否异步  
	        cache:false,//是否使用缓存  
	        type: "get",  
	        dataType: "json",  
	        timeout: 1000,  
	        contentType : 'application/x-www-form-urlencoded; charset=utf-8',  
	        url: "/WriteAboutController/expertInfo",  
	        success: function(result){
	        	data = result;
	        	console.log(result);
	        },  
	        error: function(result){  
	        	console.log(result)       
	        }  
	    }); 
	 return data;
}