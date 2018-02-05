var pageSize=50;
var hisNum=new Array();
var headImgUrl="";
var nickname="";
var adminname="";
var date=new Date();
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
function showHisMsgToday(openid,time){
	var userData=getUserData(openid);
	var index=userData.index;
	var bTime=date.Format("yyyy-MM-dd hh:mm:ss");
	if(time != 0){
		bTime = timetrans(time);
	}
	//console.log(timetrans(time));
	var date2=new Date();
	var eTime=date2.Format("yyyy-MM-dd hh:mm:ss");
    $.ajax({
		type : "post",
		url : "/csc/getHisMsgOneDay",
		dataType : "json",
		//async: false,
		data : {
			openid:openid,
			bTime:bTime,
			eTime:eTime,
			begin:0,
			end:10000
		},
		success : function(data) {
			if(data.length>0){
				for(var i=0;i<data.length;i++){
					if(data[i].msgId==null){
						$.ajax({
							type : "post",
							url : "/csc/getCsUserInfoById/"+data[i].fromUser,
							dataType : "json",
							async: false,
							data : {
							},
							success : function(data1) {
								adminname=data1.userName;
							}
						});
						rightMessage(data[i]);
					}else{
						leftMessage(data[i],index);
					}
				}
				$("#console" + openid).prepend($("#xhis" + openid));
			}
		}
    });
}
function showHisMsgOneDay(openid){
	var userData=getUserData(openid);
	var index=userData.index;
	if(typeof(hisNum[index])=="undefined")hisNum[index]=0;
	var eTime=date.Format("yyyy-MM-dd hh:mm:ss");
	date.setDate(date.getDate()-3);
	var bTime=date.Format("yyyy-MM-dd hh:mm:ss");
	date.setDate(date.getDate()+3);
    $.ajax({
		type : "post",
		url : "/csc/getHisMsgOneDay",
		dataType : "json",
		//async: false,
		data : {
			openid:openid,
			bTime:bTime,
			eTime:eTime,
			begin:hisNum[index],
			end:hisNum[index]+pageSize
		},
		success : function(data) {
			if(data.length>0){
				if(hisNum[index]==0){
					$("#console" + openid).prepend("<div style='text-align:center;'><font>----以上是历史消息----</font></div>");
				}
				hisNum[index]+=pageSize;
				for(var i=0;i<data.length;i++){
					if(data[i].msgId==null){
						$.ajax({
							type : "post",
							url : "/csc/getCsUserInfoById/"+data[i].fromUser,
							dataType : "json",
							async: false,
							data : {
							},
							success : function(data1) {
								adminname=data1.userName;
							}
						});
						rightMessage(data[i]);
					}else{
						leftMessage(data[i],index);
					}
				}
				if(data.length<pageSize)$("#xhis" + openid).html("暂无更多消息");
				$("#console" + openid).prepend($("#xhis" + openid));
			}else{
				$("#xhis" + openid).html("暂无更多消息");
			}
		}
    });
}
function leftMessage(data,i){
	var msgdate=new Date(data.createTime).Format("yyyy-MM-dd hh:mm:ss");
	var html = "";
	html += '<div class="list-left">';
	html += '<table style="padding:0px;margin:0px;">';
	html += '<tr>';
	html += '<td rowspan="2" width="50px" valign="top"><img style="height:52px;" src="'+userData[i].headimgurl+'"/></td>';
	html += '<td style="width:100%;">';
	html += '<span style="float:left;margin-left:15px;"><font size="2">'+ userData[i].nickname+'&nbsp;&nbsp;'+msgdate+'</font></span>';
	html += '</td>';
	html += '</tr>';
	//html += '<span class="time">13141455</span>';
	html += '<tr>';
	html += '<td style="width:100%;">';
	html += '<span style="float:left;margin-top:10px;"><img src="/image/img_left.jpg"></img></span>';
	if ("text" == data.msgType) {
		html += '<div class="bj">';
		html += '<span class="msgtext"><font size="2">' + replaceQQFace(data.content)+ '</font></span>';
	}
	else if ("image" == data.msgType) {
		html += '<div class="bjimg"">';
		html += "<img src=" + data.imageUrl + " width='150px'></img>";
	}
	else if ("voice" == data.msgType) {
		html += '<div class="bj">';
		html += "<embed src=" + data.voiceUrl + " windowlessVideo=1 autostart=false hidden=no units='pixels' width=300 height=44></embed>";
    }
	else if ("shortvideo" == data.msgType||"video" == data.msgType) {
		html += '<div class="bj">';
		//html += "<embed src=" + data.videoUrl + " windowlessVideo=1 autoplay=false hidden=no units='pixels' width=300 height=300></embed>";
		html += '<div id="ck'+data.msgId+'"></div>';
	}
	else if("location" == data.msgType){
		var content = data.Label+"(" + data.Location_X +","+ data.Location_Y+")";
		html += '<div class="bj" onmousedown="workContent(this,1,1)"><input type="hidden" name="msgId" value="'+data.MsgId+'"/>';
		html += "<a target='_blank' href='http://10.224.9.116:8180/jtbst/main.html?loader=gongdan&lon="+data.Location_Y+"&lat="+data.Location_X+"&type=&desc="+data.Label+"&createTime='><span class='msgtext'><font size='2'>"+replaceQQFace(content)+"</font></span></a><input type='hidden' name='workId' value=''/>"
	}
	html += '</div>';
	html += '</td>';
	html += '</tr>';
	html += '</table>';
	html += '</div>';
	$("#console" + data.fromUserName).prepend(html);
	$("#msgbox").scrollTop(0);
	if ("shortvideo" == data.msgType||"video" == data.msgType){
		playMedia("ck"+data.msgId,data.videoUrl);
	}
}
function rightMessage(data){
	var msgdate=new Date(data.createTime).Format("yyyy-MM-dd hh:mm:ss");
	var html = "";
	if ("autotext" == data.msgType) {
		html=getSystextHtml(data);
		$("#console" + data.fromUserName).prepend(html);
		$("#msgbox").scrollTop(0);
	}else{
		html += '<div class="list-right">';
		html += '<table style="padding:0px;margin:0px;">';
		html += '<tr>';
		html += '<td style="width:100%;">';
		html += '<span style="float:right;margin-right:15px;"><font size="2">'+msgdate+'&nbsp;&nbsp;'+ adminname+ '</font></span>';
		html += '</td>';
		html += '<td rowspan="2" width="50px" valign="top"><img src="/image/img_admin.jpg"/></td>';
		html += '</tr>';
		//html += '<span class="time">13141455</span>';
		html += '<tr>';
		html += '<td style="width:100%;">';
		html += '<span style="float:right;margin-top:10px;"><img src="/image/img_right.jpg"></img></span>';
		if ("text" == data.msgType) {
			html += '<div class="bj">';
			html += '<span class="msgtext"><font size="2">' + replaceQQFace(data.msgContent) + '</font></span>';
		}
		else if ("image" == data.msgType) {
			html += '<div class="bjimg">';
			html += "<img src=" + data.imageUrl + " width='150px'></img>";
		}
		else if ("voice" == data.msgType) {
			html += '<div class="bj">';
			html += "<embed src=" + data.voiceUrl + " autostart=false hidden=no units='pixels' width=300 height=44></embed>";
	    }
		html += '</div>';
		if(data.isSuccess==0){
			html+="<img style='cursor:hand;float:right;' title='发送失败' src='/image/send_again.png'>";
		}
		html += '</td>';
		html += '</tr>';
		html += '</table>';
		html += '</div>';
		$("#console" + data.toUser).prepend(html);
		$("#msgbox").scrollTop(0);
	}
}
function getSystextHtml(data){
	var msgdate=new Date(data.createTime).Format("yyyy-MM-dd hh:mm:ss");
	var html = "";
	html += '<div class="list-right">';
	html += '<table style="padding:0px;margin:0px;">';
	html += '<tr>';
	html += '<td style="width:100%;">';
	html += '<span style="float:right;margin-right:15px;"><font>'+msgdate+'&nbsp;&nbsp;系统自动</font></span>';
	html += '</td>';
	html += '<td rowspan="2" width="50px" valign="top"><img src="/image/img_system.jpg"/></td>';
	html += '</tr>';
	html += '<tr>';
	html += '<td style="width:100%;">';
	html += '<span style="float:right;margin-top:10px;"><img src="/image/img_aright.jpg"></img></span>';
	html += '<div class="autobj"><font>' + data.content+ '</font></div>';
	html += '</td>';
	html += '</tr>';
	html += '</table>';
	html += '</div>';
	return html;
}
function getLocalTime(nS) {     
    return new Date(parseInt(nS)).toLocaleString();
    //.replace(/年|月/g, "-").replace(/日/g, " ");
}   
function initDatagraid() {
	//初始化列表
	$('#users-table').datagrid({
		title : '微信用户列表',
		iconCls : 'icon-save',
		width : 840,
		height : 360,
		pageNumber:1,
		pageSize:10,
		nowrap : false,
		striped : true,
		collapsible : true,
		url : '',
		sortName : 'createTime',
		sortOrder : 'desc',
		remoteSort : false,
		idField : 'openid',
		frozenColumns : [ [ {
			title : '主键',
			field : 'openid',
			width : 30,
			sortable : true,
			hidden : true
		} ] ],
		columns : [ [ {
			field : 'isOnLine',
			title : '是否在线',
			width : 70
		}, {
			field : 'nickname',
			title : '微信名称',
			width : 150
		}, {
			field : 'openid',
			title : '微信标识',
			width : 270,
			formatter: function(value, row, index) {//隐藏微信标识符，只显示前十位
				return value.substring(0, 10) + "...";				
			}
		}, {
			field : 'phone',
			title : '手机号码',
			width : 80			
		}, {
			field : 'redBlack',
			title : '红黑名单',
			width : 60
		}, {
			field : 'createTime',
			title : '最后服务时间',
			width : 150
		}]],
		pagination : true,
		rownumbers : true,
		onClickCell: function(index,field,data) {
			$('#users-table').datagrid('selectRow',index);
			var row = $('#users-table').datagrid('getSelected');
			if(field=="phone"||field=="redBlack"){
				$.messager.confirm('选择提示', '你确定【修改】微信昵称为'+row.nickname+'的用户吗?', function(r){
					if (r){
						closeWindow('users-window');
						$("#users-edit-window").find("input[name=nickname]").val(row.nickname);
						$("#users-edit-window").find("input[name=openid]").val(row.openid);
						$("#users-edit-window").find("input[name=phone]").val(row.phone);
						$("#users-edit-window").find("select[name=redBlack]").val(row.redBlack);
						openWindow('users-edit-window', 415, 300);
					}else{
						$('#users-table').datagrid('unselectRow',index);
					}
				});
			}else{
				$.messager.confirm('选择提示', '你确定【选择】微信昵称为'+row.nickname+'的用户吗?', function(r){
					if (r){
						reConnUser(row.openid,"Choose");
						$('#users-table').datagrid('unselectRow',index);
						closeWindow('users-window');
					}else{
						$('#users-table').datagrid('unselectRow',index);
					}
				});
			}
		}
	});
	//初始化分页
	var p = $('#users-table').datagrid('getPager');
	$(p).pagination({
		pageList : [ 10, 20, 30 ],// 可以设置每页记录条数的列表
		onBeforeRefresh : function() {
		},
		onSelectPage : function(pageNumber, pageSize) {//分页触发  
			var openid=$("input[name='openid']").val();
			var nickname=$("input[name='nickname']").val();
			selectUser(openid,nickname,pageSize,pageNumber);
		}
	});
}

function selectUser(openid,nickname,pageNumber,pageSize) {
	openWindow('users-window',880,440);
	$.ajax({
		type : "POST",
		dataType : "json",
		url : '/csc/getAllUsers',
		data : {
			'searchType':searchType,
			'openid':openid,
			'nickname':nickname,
			'rows' : pageNumber,
			'page' : pageSize
		},
		success : function(data) {
			$("#users-table").datagrid('loadData', data);
		},
		error : function(err) {
			$.messager.alert('操作提示', '获取信息失败...请联系管理员!', 'error');
		}
	});
}
var searchType="";
function searchUserInfo(){
	searchType="All";
	var openid=$("#users-window").find("input[name='openid']").val();
	var nickname=$("#users-window").find("input[name='nickname']").val();
	var pageSize=$("#users-table").datagrid('options').pageSize;
	var pageNumber=$("#users-table").datagrid('options').pageNumber;
	selectUser(openid,nickname,pageSize,pageNumber);
}
function searchLatelyUser(){
	searchType="Lately";
	var openid=$("#users-window").find("input[name='openid']").val();
	var nickname=$("#users-window").find("input[name='nickname']").val();
	var pageSize=$("#users-table").datagrid('options').pageSize;
	var pageNumber=$("#users-table").datagrid('options').pageNumber;
	selectUser(openid,nickname,pageSize,pageNumber);
}
function resetUserInfo(){
	initDatagraid();
	$("#users-window").find("input[name='openid']").val("");
	$("#users-window").find("input[name='nickname']").val("");
	searchUserInfo();
}
function selectUserInfo(){
	initDatagraid();
	var openid=$("#users-window").find("input[name='openid']").val();
	var nickname=$("#users-window").find("input[name='nickname']").val();
	var pageSize=$("#users-table").datagrid('options').pageSize;
	var pageNumber=$("#users-table").datagrid('options').pageNumber;
	selectUser(openid,nickname,pageSize,pageNumber);
}
function reConnUser(openid,typeName){
    $.ajax({
		type : "post",
		url : "/csc/reAllocateWxUser",
		dataType : "json",
		//async: false,
		data : {
			csId:admin.id,
			openId:openid,
			typeName:typeName
		},
		success : function(data) {
			if(data.length>0){
				var color="";
				if(typeName=="Transfer"||typeName=="Choose"){
					color="#ffa501";
				}else if(typeName=="Invite"){
					color="#ffa502";
				}
			    /*微信用户信息*/
				var temp=$.parseJSON(data[0]);
			    if(isSeat(temp.openid)){//没有在服务列表中
					//找出通道位置
					var xth;
					for(var i=0;i<=userData.length;i++){
						if(typeof(userData[i])=="undefined"||userData[i]==null){
							xth=i;
							break;
						}
					}
					//找出空位置
					var nth=getNullSeat();
			    	userData[xth]=temp;
					userData[xth]["index"]=xth;
					userData[xth]["seat"]=nth;
					userData[xth]["status"]=true;
					var random=randomString(4);
					var id=userData[xth].openid.substring(4,userData[xth].openid.length)+random;
					userData[xth]["serviceId"]=id;
					//userData[xth].openid+=xth;
					//添加用户
					addUser(xth);
				    //找出可交换的位置
				    var dth=sysNum;
				    var dindex=sysNum;
					for(var i=0;i<=userData.length;i++){
						if(typeof(userData[i])!="undefined"&&userData[i]!=null&&!userData[i].status){
							if(userData[i].seat<dth){
								dth=userData[i].seat;//取最小的座位号
								dindex=i;
							}
						}
					}
					if(dth<nth){
						var _temp=$("#user-list>div");
						var nhtml=$(_temp[nth]).html();
						var dhtml=$(_temp[dth]).html();
						$(_temp[nth]).html(dhtml);
						$(_temp[dth]).html(nhtml);
						userData[xth].seat=dth;
						userData[dindex].seat=nth;
					}
					//分配用户后提示信息
					var _temp=$("input[name='userState']");
					$(_temp[userData[xth].seat]).next().css("background",color);
					//声音提示
					playVoice();
				
					
			    }else{
			    	var _userData=getUserData(temp.openid);
			    	_userData.status=true;
			    	var h=$("#user-list").find("li");
			    	$(h[_userData.seat]).css("background",color);
			    	var xxx=$(h[_userData.seat]).find("img");
			    	$(xxx[0]).css("filter","");
			    	$(xxx[1]).css("filter","");
			    	var fff=$(h[_userData.seat]).find("font");
			    	for(var i=0;i<fff.length;i++){
			    		$(fff[i]).css("color","#000");
			    	}
					playVoice();
			
			    }
			}else{
				$.messager.alert("提示消息","此微信用户正与其他座席聊天，无法连接！","warning");
			}
		}
    });
}
function wordsToEdit(obj) {
	wordsInit();
	$(obj).parent().prev().prev().prev().prev().prev().prev().prev().attr("disabled", false);
	$(obj).parent().prev().prev().prev().prev().prev().attr("disabled", false);
	$(obj).parent().prev().prev().prev().attr("disabled", false);
	$(obj).parent().prev().prev().find("input[type='radio']:first").attr(
			"disabled", false);
	$(obj).parent().prev().prev().find("input[type='radio']:last").attr(
			"disabled", false);
	$(obj).next().css("display", "block");
	$(obj).css("display", "none");
}
function wordsToDel(obj) {
	var id = $(obj).parent().prev().prev().prev().prev().prev().val();
	$.messager.confirm('删除提示', '你确定删除此常用语吗?', function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : "/csc/delUsuallyWord",
				dataType : "json",
				//async: false,
				data : {
					id : id
				},
				success : function(data) {
					if (data.status == "OK") {
						$(obj).parent().parent().remove();
					} else {
						$.messager.alert("提示消息", "常用语删除失败，请联系管理员！",
								"warning");
					}
				}
			});
		}
	});
}
function wordsToSave(obj) {
	var o = $(obj).parent().prev().prev().find("input[type='radio']");
	var isSystem = "";
	$.each(o, function() {
		if ($(this).attr("checked"))
			isSystem = $(this).val();
	});
	var title = $(obj).parent().prev().prev().prev().prev().prev().val();
	var word  = $(obj).parent().prev().prev().prev().val();
	var type  = $(obj).parent().prev().prev().prev().prev().prev().prev().prev().val();
	var id    = $(obj).parent().prev().prev().prev().prev().prev().prev().prev().prev().val();
	if (title == "") {
		$.messager.alert("提示消息", "请输入标题！", "info");
	}else if (word == "") {
		$.messager.alert("提示消息", "请输入常用语！", "info");
	} else {
		$.ajax({
			type : "post",
			url : "/csc/updateUsuallyWord",
			dataType : "json",
			//async: false,
			data : {
				id : id,
				type : type,
				isSystem : isSystem,
				title : title,
				word : word
			},
			success : function(data) {
				if (data.status == "OK") {
					wordsInit();
					$(obj).prev().css("display", "block");
					$(obj).css("display", "none");
				} else {
					$.messager.alert("提示消息", "常用语修改失败，请联系管理员！", "warning");
				}
			}
		});
	}
}
function wordsInit() {
	var m = $("#words-window").find("textarea");
	$.each(m, function() {
		$(this).attr("disabled", true);
	});
	var n = $("#words-window").find("span.words-edit");
	$.each(n, function() {
		$(this).css("display", "block");
	});
	var l = $("#words-window").find("span.words-save");
	$.each(l, function() {
		$(this).css("display", "none");
	});
	var o = $("#words-window").find("input[type='radio']");
	$.each(o, function() {
		$(this).attr("disabled", true);
	});
	var p = $("#words-window").find("input[name='title']");
	$.each(p, function() {
		$(this).attr("disabled", true);
	});
	var q = $("#words-window").find("select[name='type']");
	$.each(q, function() {
		$(this).attr("disabled", true);
	});
}
function toSubmit2() {
	var word = $("#wadd-window").find("textarea[name=word]:first").val();
	var title = $("#wadd-window").find("input[name=title]:first").val();
	var type=$("#wordsTypeAdd").val();
	var isSystem = "";
	var o = $("#wadd-window").find("input[name=isSystem]");
	$.each(o, function() {
		if ($(this).attr("checked"))
			isSystem = $(this).val();
	});
	if (title == "") {
		$.messager.alert("提示消息", "请输入标题！", "info");
	}else if (word == "") {
		$.messager.alert("提示消息", "请输入常用语！", "info");
	} else {
		$.ajax({
			type : "post",
			url : "/csc/saveUsuallyWord",
			dataType : "json",
			//async: false,
			data : {
				csId : admin.id,
				type:type,
				isSystem : isSystem,
				word : word,
				title : title
			},
			success : function(data) {
				if (data.status == "OK") {
					closeWindow("wadd-window");
					$.messager.alert("提示消息", "配置成功！", "info");
				}
			}
		});
	}
}
function toSubmitTitle() {
	var title = $("#btadd-window").find("input[name=title]:first").val();
	if (title == "") {
		$.messager.alert("提示消息", "请输入类型名称！", "info");
	} else {
		$.ajax({
			type : "post",
			url : "/csc/saveUsuallyWordType",
			dataType : "json",
			//async: false,
			data : {
				deptId : admin.deptId,
				name : title
			},
			success : function(data) {
				if (data.status == "OK") {
					closeWindow("btadd-window");
					$("#btadd-window").find("input[name=title]:first").val("");
					$.messager.alert("提示消息", "配置成功！", "info");
				}
			}
		});
	}
}
var wordTypeListHtml="";
var wordTypeName="";
var wordTypeId="";
function getWordTypeList(){
	$.ajax({
		type : "post",
		url : "/csc/getUsuallyWordType",
		dataType : "json",
		async: false,
		data : {
			deptId : admin.deptId
		},
		success : function(data) {
			if (data.length>0) {
				var _html="";
				var _name="";
				var _id="";
				for (var i = 0; i < data.length; i++) {
					var temp = data[i];
					_html+="<option value='"+temp.id+"'>"+temp.name+"</option>";
					_name+=temp.name+",";
					_id+=temp.id+",";
				}
				wordTypeListHtml=_html;
				wordTypeName=_name;
				wordTypeId=_id;
			}
		}
	});
}
function addWords(){
	getWordTypeList();
	if (wordTypeListHtml!="") {
		$("#wordsTypeAdd").html(wordTypeListHtml);
		openWindow('wadd-window',415,280);
	}else{
		$.messager.alert("提示消息", "请联系管理员配置常用语类型！", "info");
	}
}
function openWordsWindow() {
	getWordTypeList();
	$("#words-window").html("");
	$.ajax({
				type : "post",
				url : "/csc/getUsuallyWords",
				dataType : "json",
				//async: false,
				data : {
					csId : admin.id
				},
				success : function(data) {
					if (data.length > 0) {
						for (var i = 0; i < data.length; i++) {
							var html = "";
							var temp = data[i];
							html += '<div style="padding:10px;text-align:left;">';
							html += '<input type="hidden" name="wordid" value="'+temp.id+'"/>';
							html += '类型：<select name="type" id="type'+i+'" value="'+temp.type+'" disabled="true">'+wordTypeListHtml;
							html += '</select><br>';
							html += '标题：<input type="text" style="width:360px;" disabled="true" name="title" value="'+temp.title+'"/><br>';
							html += '常用语：<textarea name="word" disabled="true" style="display:block;height:50px;">';
							html += temp.usuallyWords;
							html += '</textarea>';
							html += '<div style="float:left">';
							if (temp.isSystem == 0) {
								html += '<input type="radio" name="isSystem'+i+'" disabled="true" value="0" checked>否</input>';
								html += '<input type="radio" name="isSystem'+i+'" disabled="true" value="1">是</input>';
							} else {
								html += '<input type="radio" name="isSystem'+i+'" disabled="true" value="0">否</input>';
								html += '<input type="radio" name="isSystem'+i+'" disabled="true" value="1" checked>是</input>';
							}
							html += '</div>';
							html += '<div style="float:right;">';
							html += '<span style="display:block;cursor:hand;" onclick="wordsToDel(this);">&nbsp;&nbsp;删除</span>';
							html += '</div>';
							html += '<div style="float:right;">';
							html += '<span style="display:block;cursor:hand;" onclick="wordsToEdit(this);" class="words-edit">编辑</span>';
							html += '<span style="display:none;cursor:hand;" onclick="wordsToSave(this);" class="words-save">提交</span>';
							html += '</div>';
							html += '</div>';
							html += '<br>';
							$("#words-window").append(html);
							$("#type"+i).val(temp.type);
						}
						openWindow('words-window', 450, 400);
					} else {
						$.messager.alert("提示消息", "没有可修改的常用语！", "info");
					}
				}
			});
}
function toSubmitNotice() {
	var content = $("#ggadd-window").find("textarea[name=content]").val();
	var title = $("#ggadd-window").find("input[name=title]").val();
	var beginTime=$("#ggadd-window").find("#beginTime").datebox("getValue");
	var endTime=$("#ggadd-window").find("#endTime").datebox("getValue");
	if (content == "") {
		$.messager.alert("提示消息", "请输入公告内容！", "info");
	} else {
		$.ajax({
			type : "post",
			url : "/csc/saveNotice",
			dataType : "json",
			//async: false,
			data : {
				title : title,
				content : content,
				beginTime : beginTime,
				endTime : endTime,
				deptId:admin.deptId
			},
			success : function(data) {
				if (data.status == "OK") {
					closeWindow("ggadd-window");
					$.messager.alert("提示消息", "发布成功！", "info");
				}
			}
		});
	}
}
var noticeData=new Object();
function getNotice(){
	$.ajax({
		type : "post",
		url : "/csc/getNotice",
		dataType : "json",
		async: true,
		data : {
			deptId:admin.deptId
		},
		success : function(data) {
			noticeData=data;
		}
	});
}
function showNotice(i){
	getNotice();
	if (noticeData.length==1) {
		$("#notice").panel({
			title:"<font style='cursor:hand;' onclick='selectGGWindow();'>&nbsp;&nbsp;"+noticeData[i%noticeData.length].title+"</font>"
		});
	}else if(noticeData.length>1){
		$("#notice").panel({
			title:"<font style='cursor:hand;' onclick='selectGGWindow();'>&nbsp;&nbsp;"+(i%noticeData.length+1)+"、"+noticeData[i%noticeData.length].title+"</font>"
		});
	}else{
		$("#notice").panel({
			title:"&nbsp;&nbsp;公告"
		});
	}
}
function selectGGWindow() {
	getNotice();
	$("#notice-window").html("");
	var html = "";
	html+="<table cellpadding='0' cellspacing='0' style='margin:5px;border:1px solid #99BBE8;width:385px;'>";
	html+="<tr>";
	html+="<td style='width:100px;height:30px;background:#FFFFFF'>标题</td>";
	html+="<td style='width:285px;background:#D1EEEE;' align='center' rowspan="+(noticeData.length+1)+" id='detailNotice'></td>";
	html+="</tr>";
	for (var i = 0; i < noticeData.length; i++) {
		var temp = noticeData[i];
		html+="<tr>";
		html+="<td class='notice_title' onmouseover='showNoticeColor(this);' onmouseout='hideNoticeColor(this);' onclick='showDetailNotice(this,"+i+");' style='cursor: hand;height:30px;background:#F5F5F5'>";
		html+=temp.title;
		if(admin.userName=="admin"){
			html+="&nbsp;<img src='/image/edit.png' title='编辑' onclick='editNoticeInit("+i+");'></img>";
		}
		html+="</td>";
		html+="</tr>";
	}
	html+="</table>";
	$("#notice-window").append(html);
	openWindow('notice-window', 415, 30*(noticeData.length+1)+150);
	$(".notice_title").each(function(){
		$(this).click();
		return;
	});
}
function showDetailNotice(obj,i){
	$(".notice_title").each(function(){
		$(this).css("background","#FFFFFF");
	});
	$(obj).css("background","#D1EEEE");
	var html="";
	html+=noticeData[i].title+"<br>";
	html+=new Date(noticeData[i].beginTime).Format("yyyy-MM-dd")+"至"+new Date(noticeData[i].endTime).Format("yyyy-MM-dd")+"<br>";
	html+=noticeData[i].content;
	$("#detailNotice").html(html);
}
function showNoticeColor(obj){
	if($(obj).css("background")!="#d1eeee")$(obj).css("background","#E0EEEE");
}
function hideNoticeColor(obj){
	if($(obj).css("background")!="#d1eeee")$(obj).css("background","#FFFFFF");
}
function editNoticeInit(index){
	closeWindow('notice-window');
	var obj=noticeData[index];
	$("#ggedit-window").find("textarea[name=content]").val(obj.content);
	$("#ggedit-window").find("input[name=title]").val(obj.title);
	$("#ggedit-window").find("input[name=id]").val(obj.id);
	$("#ggedit-window").find("#beginTime").datebox("setValue",new Date(obj.beginTime).Format("yyyy-MM-dd"));
	$("#ggedit-window").find("#endTime").datebox("setValue",new Date(obj.endTime).Format("yyyy-MM-dd"));
	openWindow('ggedit-window');
}
function editNotice(){
	var content = $("#ggedit-window").find("textarea[name=content]").val();
	var title = $("#ggedit-window").find("input[name=title]").val();
	var id = $("#ggedit-window").find("input[name=id]").val();
	var beginTime=$("#ggedit-window").find("#beginTime").datebox("getValue");
	var endTime=$("#ggedit-window").find("#endTime").datebox("getValue");
	$.ajax({
		type : "post",
		url : "/csc/editNotice",
		dataType : "json",
		async: false,
		data : {
			id : id,
			title : title,
			content : content,
			beginTime : beginTime,
			endTime : endTime
		},
		success : function(data) {
			if(data.status=="OK"){
				closeWindow('ggedit-window');
				selectGGWindow();
			}
		}
	});
}
function delNotice(){
	$.messager.confirm('关闭提示', '你确定删除此公告吗?', function(r){
		if (r){
			var id = $("#ggedit-window").find("input[name=id]").val();
			$.ajax({
				type : "post",
				url : "/csc/delNotice",
				dataType : "json",
				async: false,
				data : {
					id:id
				},
				success : function(data) {
					if(data.status=="OK"){
						closeWindow('ggedit-window');
						selectGGWindow();
					}
				}
			});
		}
	});
}
/*打开弹出窗口*/
function openWindow(id, width, height) {
	$('#' + id).window({
		width : width,
		height : height,
		left : ($(window).width() - width) * 0.5,
		top : ($(window).height() - height) * 0.5,
		modal : true,
		shadow : false,
		closed : true
	});
	$('#' + id).window('open');
}
/*关闭弹出窗口*/
function closeWindow(id) {
	$('#' + id).window('close');
}
function getLeftMsgTextHtml(data) {
	var msgdate=new Date(data.createTime).Format("yyyy-MM-dd hh:mm:ss.S");
	var msgdate2=new Date(data.createTime).Format("yyyy-MM-dd hh:mm:ss");
	var html = "";
	html += '<div class="list-left">';
	html += '<table style="padding:0px;margin:0px;">';
	html += '<tr>';
	html += '<td rowspan="2" width="50px" valign="top"><img src="/image/img_admin.jpg"/></td>';
	html += '<td style="width:100%;">';
	html += '<span style="float:left;margin-left:15px;"><font size="2">'
			+ '座席'+ data.fromUser+"&nbsp;&nbsp;"+msgdate2+ '</font></span>';
	html += '</td>';
	html += '</tr>';
	html += '<tr>';
	html += '<td style="width:100%;">';
	html += '<span style="float:left;margin-top:10px;"><img src="/image/img_left.jpg"></img></span>';
	html += '<div class="bj"><font size="2">'+ replaceQQFace(data.content) + '</font><input type="hidden" name="msgdate" value="'+msgdate+'"></div>';
	html += '</td>';
	html += '</tr>';
	html += '</table>';
	html += '</div>';
	return html;
}
function getRightMsgTextHtml(data) {
	var msgdate=new Date(data.createTime).Format("yyyy-MM-dd hh:mm:ss.S");
	var msgdate2=new Date(data.createTime).Format("yyyy-MM-dd hh:mm:ss");
	var html = "";
	html += '<div class="list-right">';
	html += '<table style="padding:0px;margin:0px;">';
	html += '<tr>';
	html += '<td style="width:100%;">';
	html += '<span style="float:right;margin-right:15px;"><font size="2">'
		 +msgdate2+ '&nbsp;&nbsp;座席'+ data.fromUser+ '</font></span>';
	html += '</td>';
	html += '<td rowspan="2" width="50px" valign="top"><img src="/image/img_admin.jpg"/></td>';
	html += '</tr>';
	html += '<tr>';
	html += '<td style="width:100%;">';
	html += '<span style="float:right;margin-top:10px;"><img src="/image/img_right.jpg"></img></span>';
	html += '<div class="bj"><font size="2">'+ replaceQQFace(data.content) + '</font><input type="hidden" name="msgdate" value="'+msgdate+'"></div>';
	html += '</td>';
	html += '</tr>';
	html += '</table>';
	html += '</div>';
	return html;
}
function pushMsgText(){
	var endTime=$("#consoleCSTOCSALL").find("input[name=msgdate]:last").val();
	if(typeof(endTime)=="undefined"){
		endTime=new Date().Format("yyyy-MM-dd");
	}
	$.ajax({
		type : "post",
		url : "/csc/pushMsgText",
		dataType : "json",
		//async: false,
		data : {
			"fromUser":admin.userId,
			"toUser":csToCs,
			"endTime":endTime
		},
		success : function(data) {
			for(var i=0;i<data.length;i++){
				var html ="";
				if(data[i].fromUser==admin.userId)html=getRightMsgTextHtml(data[i]);
				else html=getLeftMsgTextHtml(data[i]);
				$("#consoleCSTOCSALL").append(html);
				$("#msgbox").scrollTop(9999999);
			}
        }
	});
}
function showMsgTextMore(){
	var pageSize=10;
	var beginTime=$("#consoleCSTOCSALL").find("input[name=msgdate]:first").val();
	if(typeof(beginTime)=="undefined"){
		beginTime=new Date().Format("yyyy-MM-dd");
	}
	$.ajax({
		type : "post",
		url : "/csc/showMsgTextMore",
		dataType : "json",
		//async: false,
		data : {
			"fromUser":admin.userId,
			"toUser":csToCs,
			"beginTime":beginTime,
			"pageSize":pageSize
		},
		success : function(data) {
			if(data.length>0){
				for(var i=0;i<data.length;i++){
					var html ="";
					if(data[i].fromUser==admin.userId)html=getRightMsgTextHtml(data[i]);
					else html=getLeftMsgTextHtml(data[i]);
					$("#consoleCSTOCSALL").prepend(html);
					$("#msgbox").scrollTop(0);
					if(data.length<pageSize)$("#xhisCSTOCSALL").html("暂无更多消息");
					$("#consoleCSTOCSALL").prepend($("#xhisCSTOCSALL"));
				}
			}else{
				$("#xhisCSTOCSALL").html("暂无更多消息");
			}
        }
	});
}
function editUsersInfo(){
	var openid=$("#users-edit-window").find("input[name=openid]").val();
	var phone=$("#users-edit-window").find("input[name=phone]").val();
	var redBlack=$("#users-edit-window").find("select[name=redBlack]").val();
	$.ajax({
		type : "post",
		url : "/csc/editUsersInfo",
		dataType : "json",
		//async: false,
		data : {
			"openid":openid,
			"phone":phone,
			"redBlack":redBlack
		},
		success : function(data) {
			$.messager.alert('操作提示', '修改成功!', 'info');
			closeWindow('users-edit-window');
		}
	});
}
function randomString(len) {
	len = len || 32;
	var $chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
	var maxPos = $chars.length;
	var pwd = '';
	for (i = 0; i < len; i++) {
	    pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
	}
	return pwd;
}
function getOnLineCsInfo(){
    $.ajax({
		type : "post",
		url : "/csc/getOnLineCsInfo",
		dataType : "json",
		data : {
			"userId":admin.userId
		},
		success : function(data) {
			$("#online-window").html("");
			var html="";
			html+="<table style='width:100%;'>";
			html+="<tr>";
			html+="<td align='center'>姓名</td><td align='center'>服务状态</td><td align='center'>可接待数</td><td align='center'>已接待数</td>";
			html+="</tr>";
			if(data.length>0){
				for(var i=0;i<data.length;i++){
					html+="<tr>";
					html+="<td align='center'>"+data[i].userName+"</td>";
					if(data[i].status!="关闭服务"){
						html+="<td align='center'>开启</td>";
					}else{
						html+="<td align='center'>关闭</td>";
					}
					html+="<td align='center'>"+data[i].customerNum+"</td>";
					html+="<td align='center'>"+data[i].changeNum+"</td>";
					html+="</tr>";
				}
			}
			html+="</table>";
			$("#online-window").html(html);
			openWindow("online-window",300,data.length*20+150);
        }
	});
}
function showWordsTitle(obj,type){
	$(".tip4").each(function(){
		$(this).css("background-color","#FAFAFA");
	});
	$(".tip5").each(function(){
		$(this).hide();
	});
	$("#words"+type).show();
	$(obj).css("background-color","#AAAAAA");
}

function timetrans(date){
    var date = new Date(date);//如果date为10位不需要乘1000
    var Y = date.getFullYear() + '-';
    var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
    var D = (date.getDate() < 10 ? '0' + (date.getDate()) : date.getDate()) + ' ';
    var h = (date.getHours() < 10 ? '0' + date.getHours() : date.getHours()) + ':';
    var m = (date.getMinutes() <10 ? '0' + date.getMinutes() : date.getMinutes()) + ':';
    var s = (date.getSeconds() <10 ? '0' + date.getSeconds() : date.getSeconds());
    return Y+M+D+h+m+s;
}
