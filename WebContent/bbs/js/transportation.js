var openID = getURLName("openID");
var num = 1;
var size = 10;
var mescroll;
//var openID = "oNwnMv75CAORzVhzjt-7J8lWSQtA";
$(function(){
	//init();
	initXiala();
});

function initXiala(){
	 mescroll = initMeScroll("mescroll", {
		down:{
			auto: false, //是否在初始化完毕之后自动执行下拉回调callback; 默认true
			callback: function() {
				mescroll.resetUpScroll();//下拉刷新的回调,默认重置上拉加载列表为第一页
			}
		},
		up: {
			auto: true, //是否在初始化时以上拉加载的方式自动加载第一页数据; 默认false
			isBoth: false, //上拉加载时,如果滑动到列表顶部是否可以同时触发下拉刷新;默认false,两者不可同时触发; 
			callback: init, //上拉回调,此处可简写; 相当于 callback: function (page) { (page); }
		}
	});
}

function init(page){
	console.log(page.num);
	console.log(page.size);

	 $.ajax({
			type : "post",
			url : "/mineController/initTransportation",
			dataType : "json",
			async: true,
			data : {
				begin:(num - 1) * size,
				end: num * size
			},
			success: function(data){
		        	console.log(data);
		        	num++;
		        	//联网成功的回调,隐藏下拉刷新和上拉加载的状态;(参数:当前页数据的总数)
					mescroll.endSuccess(data.length);//如果传了参数,mescroll会自动判断列表若无任何数据,则提示空;列表无下一页数据,则提示无更多数据;如果不传参数则仅隐藏加载中的状态
					//设置列表数据
					addInvitation(data);
		        },
		        error: function(data){
		        	//联网失败的回调,隐藏下拉刷新和上拉加载的状态;
			        mescroll.endErr();
		        }
		    });
}

function addInvitation(data){
	for(var i=0; i<data.length; i++){
		addInvitationDiv(data[i]);
	}
}

function addInvitationDiv(data){
	// data[0] tellID;data[1]:姓名， data[2]:头像，data[3]:时间，data[4]：评论量,data[5]:标题,data[6]:内容,data[7]:图片
	var InvitationDiv = "";
	InvitationDiv += "<div class='content' id='content' >";
	InvitationDiv += "<div class='head'>" 
				  +     "<div class='headImage'><img src='"+ data[2] +"' style='width: 80%;height: 80%;margin-left: 10%;margin-top: 10%;'></div>"
				  +     "<div class='headNANDT'><div class='headName'>"+ data[1] + "</div>"
				  +		"<div class='headTime'>"+ data[3]+"</div></div>"
				  +	 "</div>";
	InvitationDiv += "<div class='contentText' style='margin-left:70px;'>"+"【" + data[5] +"】" + data[6] +"</div>";
	if(data[8] != null)
		InvitationDiv += "<div class='contentPicture' style='margin-left:70px;margin-top:10px;'>"+ getPictrue(data[8])  +"</div>";
	InvitationDiv += "<div class='foot' style='margin-left:70px;margin-top:10px;'>"+"<span>当前评论："+data[4] +"| </span>"+ "<span onclick='reply("+ data[0] +")'>回复 | </span>" + "<span onclick='particulars("+ data[0] +")'> 详情</span>" + "</div>";
	InvitationDiv += "</div>";
	$("#allInvitation").append(InvitationDiv);
}

//加图片
function getPictrue(dataPic){
	var arrayPic = dataPic.split(",");
	var picDiv = "";
	for(var i=0; i<arrayPic.length; i++){
			picDiv += "<img src='"+ arrayPic[i]+ "' style='width: 30%;margin-right:2%;'>";
	}
	return picDiv;
}