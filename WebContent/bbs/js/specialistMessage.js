var imgFile = new Array();


//压缩图片进行显示
$("#file").change(function () {
	/* 压缩图片 */
	lrz(this.files[0], {
		width: 300 //设置压缩参数
	}).then(function (rst) {
			var img = new Image(); 
			img.src = rst.base64;
			$("#imageDiv").append(img);
			imgFile[imgFile.length] = rst.base64;		
	}).catch(function (err) {
		
	}).always(function () {
		/* 必然执行 */
	})
})

//发表
function announce(){
	console.log(imgFile);
	var content = $("#divText").val();
	var imgFileString = JSON.stringify(imgFile);

    $.ajax({  
        async:false,//是否异步  
        cache:false,//是否使用缓存  
        type: "POST",  
        data:{
        	imgFile:imgFileString,
        	content:content,
        	oppenID:"1111111",   //"专家的ID" 
        	name: "李四" //专家的名字
        	},  
        dataType: "json",  
        timeout: 1000,  
        contentType : 'application/x-www-form-urlencoded; charset=utf-8',  
        url: "/WriteAboutController/specialist",  
        success: function(result){  
        	alert("OK");
        },  
        error: function(result){  
        	console.log(result)       
        }  
    });  
}