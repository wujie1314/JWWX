//Author:Vico_Ho & lls
var imgFile = new Array();
var pictureNum = 0;//图片数量
var wordNum = 0;//输入框中的字数
//点击小图片，显示表情
$(".bq").click(function(e){
	$(".face").slideDown();//慢慢向下展开
	e.stopPropagation();   //阻止冒泡事件
});

//在桌面任意地方点击，他是关闭
$(document).click(function(){
	$(".face").slideUp();//慢慢向上收
});

//动态获取文字来控制字数
function justifyWord(x)
{
	var y=document.getElementById("message").innerHTML;
	if(y.length > 140){
		document.getElementById("message").innerHTML="";
		document.getElementById("message").innerHTML=y.substring(0,140);
		alert("字数不得超过140个字!");	
	}
}

//点击小图标时，添加功能
$(".face ul li").click(function(){
	var simg=$(this).find("img").clone();
	$(".message").append(simg);
});

//点击发表按扭，发表内容
$("span.submit").click(function(){

	var txt=$(".message").html();
	wordNum = txt.length;
	if(wordNum > 140){
		alert("字数不得超过140个字!");
	}else if(txt==""){
		$('.message').focus();//自动获取焦点
		return;
	}

	$(".msgCon").prepend("<div class='msgBox'><dl><dt><img src='images/tx.jpg' width='50' height='50'/></dt><dd>神马都是浮云</dd></dl><div class='msgTxt'>"+txt+"</div></div>");
});

//压缩图片进行显示
$("input[type=file]").change(function () {
	/* 压缩图片 */
	lrz(this.files[0], {
		width: 300 //设置压缩参数
	}).then(function (rst) {
		if(pictureNum < 3){
			var img = new Image(); 
			var deleteImg = new Image();
			deleteImg.src = "bbs/images/delete1.png";
			img.src = rst.base64; 
			img.height = "75";
			img.width = "75";
			img.setAttribute("class","image");
			
			deleteImg.setAttribute("class","deleteImg");
			img.onclick = function(){
				$(this).remove();
				$(".deleteImg").remove();
				pictureNum--;
			}
			deleteImg.style.position="relative";
			deleteImg.style.top=0;
			deleteImg.style.right=20;
			deleteImg.style.width="20px";
			deleteImg.style.height="20px";
			
			img.style.marginLeft = '10'; //设置图片之间的间距
			$("#picture").append(img); //呈现图像(拍照結果)  	
			$(deleteImg).appendTo("#picture"); //呈现图像(拍照結果)  
			pictureNum++;
			imgFile[imgFile.length] = rst.base64;
			//.log(rst.base64);
		}else{
			alert("图片不要超过3张!");
		}
		
	}).catch(function (err) {
		
	}).always(function () {
		/* 必然执行 */
	})
})

$(".deleteImg").click(function(){
	
});




//选择图片
function addPicture(){
	$("#file").click();
}

//发表
function announce(){
	var content = $("#message").html();
	var imgFileString = JSON.stringify(imgFile);

    $.ajax({  
        async:false,//是否异步  
        cache:false,//是否使用缓存  
        type: "POST",  
        data:{
        	imgFile:imgFileString,
        	content:content,
        	oppenID:"11"
        	},  
        dataType: "json",  
        timeout: 1000,  
        contentType : 'application/x-www-form-urlencoded; charset=utf-8',  
        url: "/WriteAboutController/announce",  
        success: function(result){  
        	
        },  
        error: function(result){  
        	console.log(result)       
        }  
    });  
}