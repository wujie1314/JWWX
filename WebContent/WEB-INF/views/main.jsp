<%@page import="com.alibaba.druid.sql.visitor.functions.Now"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName() +":"+request.getServerPort()+path;
%>
<html>
<head>
<link href="/image/favicon96096.ico" rel="Shortcut Icon">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>重庆市交通服务热线</title>
<script type='text/javascript' src=../js/sc_html.js ></script>
<link rel=stylesheet href=/css/sc_html.css type="text/css" ></link>
<script type="text/javascript">
/*座席信息*/
var admin=new Object();
/*微信用户信息*/
var userData=new Array();
/*用户群信息*/
var adminData=new Object();
/*初始化微信用户信息*/
var sysNum=8;
var sysFlag=true;//开关按钮状态
var voiceFlag=true;//开关按钮状态
var getuser_num=0;
/*群聊对应关系*/
var userAdmin=new Array();
/*当前聊天者信息*/
var nowOpenid="";
var ws = new Array();
</script>
<style type="text/css">
body {
	font: 14px 微软雅黑;
	padding: 0;
	margin: 0;
}

.qqface {
	border: 1px solid #cccccc;
}

.qqface td {
	padding: 0px;
	margin: 0px;
	border: 1px solid #cccccc;
}

.button_a {
	Display: block;
	Width: 60px;
	Padding: 2px;
	Line-height: 30px;
	Background-color: #3385FF;
	Border: 1px solid #3385FF;
	Color: #FFFFFF;
	Text-decoration: none;
	Text-align: center;
}
.words1{
    height:50px;
}
a:hover.l-btn-plain{
	color:black;
}
</style>
</head>
<body class="easyui-layout">
	<div region="north"
		title="<img style='height:12px;' src='/image/96096.png'/>重庆市交通服务热线微信版"
		split="true"
		style="height: 72px; line-height: 36px; background: #fafafa;">
		<div style="position: relative;">
			<div style="position: absolute; left: 0px; top: 0px; width: 270px;">
				<span
					style="float: left; line-hight: 36px; padding: 5px; margin-right: 10px;"><img
					onclick='changeImg(this);' style='height: 25px; cursor: hand;'
					id="onImg" src='/image/on.png'></span>&nbsp;<img
					style="vertical-align: middle; margin-top: 5px;"
					onclick='changeVoice(this);' style='height: 25px; cursor: hand;'
					id="voiceOk" src='/image/voice-ok.png'> <a
					style="position: absolute; top: 5px;" iconCls="icon-search"
					href="javascript:void(0)" id="mBtn1" class="easyui-menubutton"
					menu="#mBtn_1">查询</a>
				<div id="mBtn_1" style="width: 100px;">
					<div iconCls="icon-user">
						<a href="#" onclick="searchUserInfo();">全部用户</a>
					</div>
					<div iconCls="icon-list">
						<a href="#" onclick="searchLatelyUser();">最近用户</a>
					</div>
				</div>
				<a href="#" class="easyui-linkbutton" iconCls="icon-help"
					style="position: absolute; top: 5px; right: 0px;" plain="true">帮助</a>
			</div>
			<span
				style="position: absolute; left: 300px; top: 0px; width: 600px;">最大接待数：<font
				color="red"><b id="sysSetNum">0</b></font>&nbsp;&nbsp;&nbsp;&nbsp;等待用户数：<font
				color="red"><b id="wxWaitNum">0</b></font><font
				style="cursor: hand;" onclick="getOnLineCsInfo();">&nbsp;&nbsp;&nbsp;&nbsp;在线座席数：</font><font
				color="red"><b id="sysWaitNum">0</b></font></span>
			<div
				style="position: absolute; right: 395px; top: 0px; width: 150px;">
				欢迎您：<b id="adminName"></b>
			</div>
			<a style="position: absolute; right: 170px; top: 5px;"
				iconCls="icon-save" href="javascript:void(0)" id="mBtn1"
				class="easyui-menubutton" menu="#mBtn_2">系统配置</a>
			<div id="mBtn_2" style="width: 120px;">
				<div iconCls="icon-edit">
					<a href="#" onclick="openWindow('config-window',200,150);">设置服务数</a>
				</div>
				<div iconCls="icon-add" id="mBtn_addTitle">
					<a href="#" onclick="openWindow('btadd-window',415,180);">常用语类型</a>
				</div>
				<div iconCls="icon-add">
					<a href="#" onclick="addWords();">新增常用语</a>
				</div>
				<div iconCls="icon-edit">
					<a href="#" onclick="openWordsWindow();">编辑常用语</a>
				</div>
				<div iconCls="icon-edit" id="mBtn_selectGG">
					<a href="#" onclick="selectGGWindow();">查看公告</a>
				</div>
				<div iconCls="icon-add" id="mBtn_addGG">
					<a href="#" onclick="openWindow('ggadd-window',415,280);">新增公告</a>
				</div>
			</div>
			<a href="#" class="easyui-linkbutton" iconCls="icon-talk"
				onclick="connAdmins('ALL','聊天室');"
				style="position: absolute; top: 5px; right: 80px;" plain="true">聊天室</a>
			<font id="msgNum"
				style='position: absolute; color: red; font-weight: bold; width: 20px; right: 60px;'></font>
			<a href="#" class="easyui-linkbutton" iconCls="icon-no"
				onclick="window.close();"
				;
				style="position: absolute; top: 5px; right: 0px;"
				plain="true">关闭</a>
		</div>
	</div>
	<div region="east"
		title="工单记录&nbsp;&nbsp;<img onclick='addNewWorkTab();' style='height:14px;cursor:hand;' src='/image/add.png' title='新增工单'></img>"
		split="true" style="width: 550px;">
		<div id="tabs" class="easyui-tabs" fit="true" border="false">
			<div id="history" title="历史工单" style="padding: 10px;"></div>
		</div>
	</div>
	<div region="west" split="true" title="微信用户列表"
		style="width: 270px; padding: 0px; overflow: hidden;">
		<div class="easyui-layout" fit="true"
			style="background: #fafafa; padding: 0px;">
			<div class="user-list" region="center">
				<ul id="user-list">
				</ul>
			</div>
			<div region="south" split="true" style="height: 280px;"
				title="员工群<img style='height:12px;margin-left:80px;' src='/image/free.png'></img>&nbsp;空闲&nbsp;&nbsp;&nbsp;&nbsp;<img style='height:12px;' src='/image/busy.png'></img>&nbsp;繁忙">
				<div id="admin-list" class="easyui-accordion" fit="true"
					border="false"></div>
			</div>
		</div>
	</div>
	<div region="center" icon="icon-msg" title="公告" id="notice"
		style="overflow: hidden;">
		<div class="easyui-layout" fit="true" style="background: #ccc;">
			<div tabindex="0" id="qqface"
				style="margin: 0px; display: none; bottom: 162px; height: 75px; width: 100%; position: absolute; z-index: 98; background-color: #fafafa">
				<table cellpadding="0" cellspacing="0" class="qqface">
					<tr>
						<td onclick='choseQQFace(90);'><img style='cursor: hand;'
							alt='' src='/image/qq/90.png'></td>
						<td onclick='choseQQFace(82);'><img style='cursor: hand;'
							alt='' src='/image/qq/82.png'></td>
						<td onclick='choseQQFace(84);'><img style='cursor: hand;'
							alt='' src='/image/qq/84.png'></td>
					</tr>
					<tr>
						<td onclick='choseQQFace(1);'><img style='cursor: hand;'
							alt='' src='/image/qq/1.png'></td>
						<td onclick='choseQQFace(64);'><img style='cursor: hand;'
							alt='' src='/image/qq/64.png'></td>
						<td onclick='choseQQFace(22);'><img style='cursor: hand;'
							alt='' src='/image/qq/22.png'></td>
					</tr>
					<tr>
						<td onclick='choseQQFace(40);'><img style='cursor: hand;'
							alt='' src='/image/qq/40.png'></td>
						<td onclick='choseQQFace(43);'><img style='cursor: hand;'
							alt='' src='/image/qq/43.png'></td>
						<td onclick='choseQQFace(55);'><img style='cursor: hand;'
							alt='' src='/image/qq/55.png'></td>
					</tr>
				</table>
			</div>
			<div tabindex="0" id="message"
				style="margin: 0px; display: none; bottom: 162px; height: 100px; width: 100%; position: absolute; z-index: 98; background-color: #fafafa">
			</div>
			<div tabindex="0" id="hisMsgOneMore"
				style="margin: 0px; display: none; bottom: 162px; height: 20px; width: 100%; position: absolute; z-index: 98; background-color: #fafafa">
				<table style="width: 100%">
					<tr>
						<td width="15%"><font size="2">开始时间：</font></td>
						<td width="30%"><input id="bTime" class="easyui-datetimebox"></input></td>
						<td width="15%"><font size="2">结束时间：</font></td>
						<td width="30%"><input id="eTime" class="easyui-datetimebox"></input></td>
						<td width="10%"><a href="#" onclick='addNewHistoryTab();'
							class="easyui-linkbutton" title="查询消息记录" plain="true">确定</a></td>
					</tr>
				</table>
			</div>
			<div id="msgbox" region="center">
				<div tabindex="0" id="userinfo"
					style="margin: 0px; display: none; width: 100%; background-color: #fafafa;">
					<div id="admininfo" style="float: left; width: 100%;"></div>
					<div style="float: left; width: 40%;"></div>
					<div style="float: right; width: 58%;"></div>
				</div>
				<div id="connContent"></div>
			</div>
			<div region="south" split="true" style="height: 160px;">
				<div style="width: 100%; height: 28px; line-hight: 28px;">
					<span style="float: left;"> 
					<a href="#"
						onclick="showQQFace();" class="easyui-linkbutton"
						iconCls="icon-smile" title="选择表情" plain="true">表情</a> 
						<a href="#"
						onclick="showZmessage();" class="easyui-linkbutton"
						iconCls="icon-text" title="选择常用语" plain="true">常用语</a> 
						<a href="#"
						id="browse" class="easyui-linkbutton" iconCls="icon-image"
						title="发送图片" plain="true">图片</a>
						<a href="#"
						onclick="recordInit();" class="easyui-linkbutton"
						iconCls="icon-record" title="开始录音" plain="true">录音</a>
						<a href="#" onclick="newbbs();" class="easyui-linkbutton"
						iconCls="icon-help" title="专家服务" plain="true">专家</a>
						<a href="#" onclick="openCallPolice();" class="easyui-linkbutton"
						iconCls="icon-help" title="报警救援" plain="true">报警救援短信</a>
					</span>
					<span style="float: right;"> <a href="#"
						onclick='showHisMsgOneMore();' class="easyui-linkbutton"
						iconCls="icon-clock" title="查询消息记录" plain="true">消息记录</a>
					</span>
				</div>
				<div id="msg" onclick="closeZmessage();"
					style="position: relative; width: 100%; height: 116px;">
					<div id="msgSendBtn"
						style="position: absolute; width: 50px; bottom: 2px; right: 2px; display: none;">
						<a class="button_a" href="#" onclick="sendText();">发送</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="mm" class="easyui-menu" style="width: 80px;">
		<div id="transfer">转交</div>
		<div id="invite">邀请</div>
		<div id="sendmsgtocs">聊天</div>
	</div>
	<div id="reconnect" class="easyui-menu" style="width: 100px;">
		<div id="reconuser">重新连接</div>
	</div>
	<div id="work" class="easyui-menu" style="width: 100px;">
		<div id="push">推送信息</div>
		<div id="save">保存附件</div>
	</div>
	<div id="config-window" class="easyui-window" title="设置服务数"
		closed="true" iconCls="icon-save">
		<div style="padding: 10px;">
			<div>
				<label for="name">接待用户数量:</label><br> <input type="text"
					name="customerNum"></input>
			</div>
			<div style="text-align: center;">
				<a href="#" class="easyui-linkbutton" onclick="toSubmit();">提&nbsp;&nbsp;交</a>
			</div>
		</div>
	</div>
	<div id="sjlx-5010-window" class="easyui-window" title="事件类型"
		closed="true" iconCls="icon-save">
		<div id="sjlx_5010" style="padding: 0px; width: 580px;">
			<jsp:include flush="true" page="/sjlx/sjlx_5010.jsp" />
		</div>
	</div>
	<div id="sjlx-5020-window" class="easyui-window" title="事件类型"
		closed="true" iconCls="icon-save">
		<div id="sjlx_5020" style="padding: 0px; width: 580px;">
			<jsp:include flush="true" page="/sjlx/sjlx_5020.jsp" />
		</div>
	</div>
	<div id="sjlx-5021-window" class="easyui-window" title="事件类型"
		closed="true" iconCls="icon-save">
		<div id="sjlx_5021" style="padding: 0px; width: 580px;">
			<jsp:include flush="true" page="/sjlx/sjlx_5021.jsp" />
		</div>
	</div>
	<div id="sjlx-5022-window" class="easyui-window" title="事件类型"
		closed="true" iconCls="icon-save">
		<div id="sjlx_5022" style="padding: 0px; width: 580px;">
			<jsp:include flush="true" page="/sjlx/sjlx_5022.jsp" />
		</div>
	</div>
	<div id="sjlx-5023-window" class="easyui-window" title="事件类型"
		closed="true" iconCls="icon-save">
		<div id="sjlx_5023" style="padding: 0px; width: 580px;">
			<jsp:include flush="true" page="/sjlx/sjlx_5023.jsp" />
		</div>
	</div>
	<div id="sjlx-5024-window" class="easyui-window" title="事件类型"
		closed="true" iconCls="icon-save">
		<div id="sjlx_5024" style="padding: 0px; width: 580px;">
			<jsp:include flush="true" page="/sjlx/sjlx_5024.jsp" />
		</div>
	</div>
	<div id="sjlx-5025-window" class="easyui-window" title="事件类型"
		closed="true" iconCls="icon-save">
		<div id="sjlx_5025" style="padding: 0px; width: 580px;">
			<jsp:include flush="true" page="/sjlx/sjlx_5025.jsp" />
		</div>
	</div>
	<div id="sjlx-5026-window" class="easyui-window" title="事件类型"
		closed="true" iconCls="icon-save">
		<div id="sjlx_5026" style="padding: 0px; width: 580px;">
			<jsp:include flush="true" page="/sjlx/sjlx_5026.jsp" />
		</div>
	</div>
	<div id="sjlx-5027-window" class="easyui-window" title="事件类型"
		closed="true" iconCls="icon-save">
		<div id="sjlx_5027" style="padding: 0px; width: 580px;">
			<jsp:include flush="true" page="/sjlx/sjlx_5027.jsp" />
		</div>
	</div>
	<div id="sjlx-5028-window" class="easyui-window" title="事件类型"
		closed="true" iconCls="icon-save">
		<div id="sjlx_5028" style="padding: 0px; width: 580px;">
			<jsp:include flush="true" page="/sjlx/sjlx_5028.jsp" />
		</div>
	</div>
	<div id="sjlx-5029-window" class="easyui-window" title="事件类型"
		closed="true" iconCls="icon-save">
		<div id="sjlx_5029" style="padding: 0px; width: 580px;">
			<jsp:include flush="true" page="/sjlx/sjlx_5029.jsp" />
		</div>
	</div>
	<div id="sjlx-5030-window" class="easyui-window" title="事件类型"
		closed="true" iconCls="icon-save">
		<div id="sjlx_5030" style="padding: 0px; width: 580px;">
			<jsp:include flush="true" page="/sjlx/sjlx_5030.jsp" />
		</div>
	</div>
	<div id="sjlx-5032-window" class="easyui-window" title="事件类型"
		closed="true" iconCls="icon-save">
		<div id="sjlx_5032" style="padding: 0px; width: 580px;">
			<jsp:include flush="true" page="/sjlx/sjlx_5032.jsp" />
		</div>
	</div>
	<div id="sjlx-5050-window" class="easyui-window" title="事件类型"
		closed="true" iconCls="icon-save">
		<div id="sjlx_5050" style="padding: 0px; width: 580px;">
			<jsp:include flush="true" page="/sjlx/sjlx_5050.jsp" />
		</div>
	</div>
	<div id="sjlx-5051-window" class="easyui-window" title="事件类型"
		closed="true" iconCls="icon-save">
		<div id="sjlx_5051" style="padding: 0px; width: 580px;">
			<jsp:include flush="true" page="/sjlx/sjlx_5051.jsp" />
		</div>
	</div>
	<div id="sjlx-5052-window" class="easyui-window" title="事件类型"
		closed="true" iconCls="icon-save">
		<div id="sjlx_5052" style="padding: 0px; width: 580px;">
			<jsp:include flush="true" page="/sjlx/sjlx_5052.jsp" />
		</div>
	</div>
	<div id="sjlx-5053-window" class="easyui-window" title="事件类型"
		closed="true" iconCls="icon-save">
		<div id="sjlx_5053" style="padding: 0px; width: 580px;">
			<jsp:include flush="true" page="/sjlx/sjlx_5053.jsp" />
		</div>
	</div>
	<div id="sjlx-5054-window" class="easyui-window" title="事件类型"
		closed="true" iconCls="icon-save">
		<div id="sjlx_5054" style="padding: 0px; width: 580px;">
			<jsp:include flush="true" page="/sjlx/sjlx_5054.jsp" />
		</div>
	</div>
	<div id="sjlx-5055-window" class="easyui-window" title="事件类型"
		closed="true" iconCls="icon-save">
		<div id="sjlx_5055" style="padding: 0px; width: 580px;">
			<jsp:include flush="true" page="/sjlx/sjlx_5055.jsp" />
		</div>
	</div>
	<div id="sjlx-5056-window" class="easyui-window" title="事件类型"
		closed="true" iconCls="icon-save">
		<div id="sjlx_5056" style="padding: 0px; width: 580px;">
			<jsp:include flush="true" page="/sjlx/sjlx_5056.jsp" />
		</div>
	</div>
	<div id="btadd-window" class="easyui-window" title="常用语类型"
		closed="true" iconCls="icon-save">
		<div style="padding: 10px;">
			<div>
				类型名称:<input type="text" style="width: 360px;" name="title" value=""></input>
				<br> <br>
			</div>
			<div style="text-align: center;">
				<a href="#" class="easyui-linkbutton" onclick="toSubmitTitle();">确&nbsp;&nbsp;定</a>
			</div>
		</div>
	</div>
	<div id="wadd-window" class="easyui-window" title="新增常用语" closed="true"
		iconCls="icon-save">
		<div style="padding: 10px;">
			<div>
				是否为系统常用语： <input type="radio" name="isSystem" disabled="true"
					value="0" checked>否</input> <input type="radio" name="isSystem"
					disabled="true" value="1">是</input><br> 类型: <select
					id="wordsTypeAdd" name="type"></select><br> 标题: <input
					type="text" style="width: 360px;" name="title"></input><br>
				常用语:
				<textarea name="word" cols="30" rows="5"></textarea>
			</div>
			<div style="text-align: center;">
				<a href="#" class="easyui-linkbutton" onclick="toSubmit2();">增&nbsp;&nbsp;加</a>
			</div>
		</div>
	</div>
	<div id="ggadd-window" class="easyui-window" title="新增公告" closed="true"
		iconCls="icon-save">
		<div style="padding: 10px;">
			<div>
				<div style="margin-top: 5px;">
					公告标题：<input name="title" type="text">
				</div>
				<div style="margin-top: 5px;">
					开始时间：<input id="beginTime" class="easyui-datebox"></input>
				</div>
				<div style="margin-top: 5px;">
					结束时间：<input id="endTime" class="easyui-datebox"></input>
				</div>
				<div style="margin-top: 5px; margin-bottom: 5px;">
					公告内容：
					<textarea name="content" cols="30" rows="5"></textarea>
				</div>
			</div>
			<div style="text-align: center;">
				<a href="#" class="easyui-linkbutton" onclick="toSubmitNotice();">增&nbsp;&nbsp;加</a>
			</div>
		</div>
	</div>
	<div id="ggedit-window" class="easyui-window" title="新增公告"
		closed="true" iconCls="icon-save">
		<div style="padding: 10px;">
			<div>
				<input type="hidden" name="id">
				<div style="margin-top: 5px;">
					公告标题：<input name="title" type="text">
				</div>
				<div style="margin-top: 5px;">
					开始时间：<input id="beginTime" class="easyui-datebox"></input>
				</div>
				<div style="margin-top: 5px;">
					结束时间：<input id="endTime" class="easyui-datebox"></input>
				</div>
				<div style="margin-top: 5px; margin-bottom: 5px;">
					公告内容：
					<textarea name="content" cols="30" rows="5"></textarea>
				</div>
			</div>
			<div style="text-align: center;">
				<a href="#" class="easyui-linkbutton" onclick="editNotice();">修&nbsp;&nbsp;改</a>
				<a href="#" class="easyui-linkbutton" onclick="delNotice();">删&nbsp;&nbsp;除</a>
			</div>
		</div>
	</div>
	<div id="users-edit-window" class="easyui-window" title="用户统一管理"
		closed="true" iconCls="icon-save">
		<div style="padding: 10px;">
			<div>
				<input type="hidden" name="id">
				<div style="margin-top: 5px;">
					微信标识：<input name="openid" type="text" readonly="true">
				</div>
				<div style="margin-top: 5px;">
					微信名称：<input name="nicknam e" type="text" readonly="true">
				</div>
				<div style="margin-top: 5px;">
					电话号码：<input name="phone" type="text">
				</div>
				<div style="margin-top: 5px;">
					红黑名单：<select name="redBlack">
						<option value="正常">正常</option>
						<option value="红名单">红名单</option>
						<option value="黑名单">黑名单</option>
					</select>
				</div>
			</div>
			<div style="text-align: center;">
				<a href="#" class="easyui-linkbutton" onclick="editUsersInfo();">提&nbsp;&nbsp;交</a>
			</div>
		</div>
	</div>
	<div id="words-window" class="easyui-window" title="编辑常用语"
		closed="true" iconCls="icon-save"></div>
	<div id="notice-window" class="easyui-window" title="系统公告"
		closed="true" iconCls="icon-save"></div>
	<div id="online-window" class="easyui-window" title="在线座席信息"
		closed="true" iconCls="icon-save"></div>
	<div id="users-window" class="easyui-window" title="用户查询" closed="true"
		iconCls="icon-search">
		<div style="text-align: center;">
			微信名称：<input type="text" name="nickname" value="" /> 微信标识：<input
				type="text" name="openid" value="" /> <a href="#"
				onclick='selectUserInfo();' class="easyui-linkbutton"
				title="查询微信用户列表" plain="true">查询</a><a href="#"
				onclick='resetUserInfo();' class="easyui-linkbutton"
				title="重置微信用户列表" plain="true">重置</a>
		</div>
		<table id="users-table"></table>
	</div>
	<div id="newMessageDIV" style="display: none"></div>
	<div id="image-window" class="easyui-window"
		style="width: 600px; height: 300px; padding: 0px;" title=""
		closed="true">
		<div onclick="imgToClose();"></div>
	</div>
	<div id="image-scan-window" class="easyui-window"
		style="width: 600px; height: 300px; padding: 0px;" title=""
		closed="true">
		<div></div>
	</div>
	<div id="record-window" class="easyui-window" title="发送录音"
		closed="true" iconCls="icon-save">
		<!--页面录音  开始-->
		<div class="container">
			<div id="recorder-audio" class="control_panel idle">
				<button class="record_button"
					onclick="FWRecorder.record('audio', 'audio.wav');" title="开始录音">
					<img src="/image/record.png" alt="开始录音" />
				</button>
				<button class="stop_recording_button"
					onclick="FWRecorder.stopRecording('audio');" title="结束录音">
					<img src="/image/stop.png" alt="结束录音" />
				</button>
				<button class="play_button" onclick="FWRecorder.playBack('audio');"
					title="播放录音">
					<img src="/image/play.png" alt="播放录音" />
				</button>
				<button class="pause_playing_button"
					onclick="FWRecorder.pausePlayBack('audio');" title="暂停录音">
					<img src="/image/pause.png" alt="暂停录音" />
				</button>
				<button class="stop_playing_button"
					onclick="FWRecorder.stopPlayBack();" title="停止播放">
					<img src="/image/stop.png" alt="停止播放" />
				</button>
				<div class="level"></div>
			</div>
			<div class="details">
				<span id="save_button"> <span id="flashcontent"> </span>
				</span>
			</div>
			<form id="uploadForm" name="uploadForm"
				action="/fileUpload/saveVoice">
				<input name="openid" value="" type="hidden">
				<input name="authenticity_token" value="xxxxx" type="hidden">
				<input name="upload_file[filename]" value="1" type="hidden">
				<input name="format" value="json" type="hidden">
			</form>
		</div>
		<!--页面录音  结束-->
	</div>
	<!-- 论坛window -->
	<div id="bbs-window" class="easyui-window" title="新增论坛帖子"
		closed="true" iconCls="icon-save">
			<div class="bbs_left_list" id="bbs_left_list">
				<ul class= "bbs_ul_list" onclick="bbs_click_selected(this)" >
					<li class= "bbs_li_list">
						<img class="j-img" src="http://p1.music.126.net/H3QxWdf0eUiwmhJvA4vrMQ==/1407374893913311.jpg?param=66y66" data-src="http://p1.music.126.net/H3QxWdf0eUiwmhJvA4vrMQ==/1407374893913311.jpg?param=66y66">
						<div class="bbs_expert_info">
							<span class="expert_name" id = "12357210" >陈立</span>
							<p class="expert_des" >专家,曾获奖公路秩序树小才q是阿萨德</p>
						</div>
					</li> 
				</ul>
			</div>
			<div class="bbs_right_content" style="width:450px;height: 400px;float: right;text-align:center;">
				<div style="width:430px;height: 30px; margin-top:10px;text-align:center;">
					<label style="text-align: center;">标题</label>
					<input type="text" id = "expert_title" name="expert_title" value="" />
				</div>
				<div style="width:430px;height: 220px; margin-top:10px;">
					<label>内容</label>
					<textarea class="form-control" style="width:430px;height: 200px;" id="divText"></textarea>
				</div>
				<div style="width:430px; margin-top:10px; text-align: left;">
					<label>上传图片</label>
		  			<input type="file" id="bbsImg_file"/>
		  			<div class="bbs_imageDiv" id="bbs_imageDiv"></div>
				</div>					
		  			
		  		<div style="width:430px;height: 100px; text-align: right;">
		  			<button style="width:60px;height: 30px; margin-top:20px;" onclick="announce()">发表</button>
		  		</div>		  		
			</div>
			<form>
				<input name="expert_name" id="expert_name"value="xxx" type="hidden">
				<input name="expert_ID" id = "expert_ID"value="1" type="hidden">
				<input name="expert_phone" id="expert_phone" value="" type="hidden">
			</form> 
	</div>
	<!-- 报警救援 -->
	<div id="callPolice-window" class="easyui-window" title="短信-提示"
		closed="true" iconCls="icon-save">
			<div class="sendmessagebox">
				<div>
					&nbsp;&nbsp;工&nbsp;单&nbsp;号：&nbsp;<input type="text" id="alarmRescue_orderId" name="alarmRescue_orderId">
				</div>
				<div class="singlebox">
					&nbsp;&nbsp;电&nbsp;话&nbsp;号：&nbsp;<input type="text" id = "alarmRescue_telephone" name="alarmRescue_telephone">
				</div>
				<button class="sendnotebtn" onclick="sendAlarmRescue()">发送短信</button>
			</div>
	</div>
	<div id="media-html" style="display: none">
		<div id="jp_container_1" class="jp-video jp-video-180p"
			role="application" aria-label="media player">
			<div class="jp-type-single">
				<div class="jp-jplayer"></div>
				<div class="jp-gui">
					<div class="jp-video-play">
						<button class="jp-video-play-icon" role="button" tabindex="0">play</button>
					</div>
					<div class="jp-interface">
						<div class="jp-progress">
							<div class="jp-seek-bar">
								<div class="jp-play-bar"></div>
							</div>
						</div>
						<div class="jp-current-time" role="timer" aria-label="time">&nbsp;</div>
						<div class="jp-duration" role="timer" aria-label="duration">&nbsp;</div>
						<div class="jp-controls-holder">
							<div class="jp-controls">
								<button class="jp-play" role="button" tabindex="0">play</button>
								<button class="jp-stop" role="button" tabindex="0">stop</button>
							</div>
							<div class="jp-volume-controls">
								<button class="jp-mute" role="button" tabindex="0">mute</button>
								<button class="jp-volume-max" role="button" tabindex="0">max
									volume</button>
								<div class="jp-volume-bar">
									<div class="jp-volume-bar-value"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<!-- 报警救援引入 -->
<link type="text/css" rel="stylesheet" href="alarmRescue/css/seatAlarmRescue.css" />
<script type="text/javascript" src="alarmRescue/js/seatAlarmRescue.js"></script>
<!-- JS引入 -->
<link type="text/css" rel="stylesheet" href="bbs/css/main_bbs_window.css" />
<script type="text/javascript" src="bbs/js/dist/lrz.all.bundle.js"></script>
<script type="text/javascript" src="bbs/js/specialistMessage.js"></script>

<script src="/js/sockjs-0.3.min.js"></script>
<script type="text/javascript" src='/js/websocket/swfobject.js'></script>
<script type="text/javascript" src="/js/websocket/web_socket.js"></script>
<script type="text/javascript"
	src="/js/jquery/plupload/plupload.full.min.js"></script>
<!--页面录音  开始-->
<link type="text/css" rel="stylesheet" href="/css/record-style.css" />
<script type="text/javascript" src="/js/record/swfobject-record.js"></script>
<script type="text/javascript" src="/js/record/recorder.js"></script>
<script type="text/javascript" src="/js/record/main.js"></script>
<!--页面录音  end-->
<script type="text/javascript" src="/js/ckplayer/ckplayer.js"
	charset="utf-8"></script>
<script type="text/javascript" src="/js/ckplayer/offlights.js"></script>
<script type="text/javascript" src="/js/normal.js"></script>
<script type="text/javascript" src="/js/qqface.js"></script>
<script type="text/javascript">	
function imgWSize(width){
	return ($(window).width()-800)/width;
}
function imgHSize(height){
	return ($(window).height()-100)/height;
}
function imgToClose(){
	closeWindow('image-window');
	$('#image-window>div').html('');
}
function imgToScan(webData, message){
	var src="/upload/"+webData;
	var image=new Image();
	image.onload = function(){
		var n=0;
		n=imgWSize(image.width);
		if($(window).height()-400<image.height*n){
			n=imgHSize(image.height);
		}
		$('#image-scan-window>div').html('');
		$('#image-scan-window>div').append('<img src="'+src+'" style="width:100%;padding:0px;"/>');
		$('#image-scan-window>div').append('<div style="width:100%;text-align:center"><a onclick=imgToScanClose("'+webData+'","'+message+'"); href="#" class="easyui-linkbutton">发&nbsp;&nbsp;送</a></div>');
		$('#image-scan-window').window({
			title: '图片预览',
	        left:($(window).width() - image.width*n) * 0.5,       
			top:($(window).height() - image.height*n-40) * 0.5, 
			width: image.width*n,
			height: image.height*n+40,
			modal: true,
			shadow: false,
			closed: false,
			draggable:true,
			collapsible:false,
			minimizable:false,
			maximizable:false
		});
	}
	image.src=src;
}
function imgToScanClose(webData, message){
	closeWindow('image-scan-window');
	sendImg(webData, message);
	$('#image-scan-window>div').html('');
}
function imgToBig(obj){
	var src=$(obj).attr("src");
	var image=new Image();
	image.onload = function(){
		var n=0;
		n=imgWSize(image.width);
		if($(window).height()-400<image.height*n){
			n=imgHSize(image.height);
		}
		$('#image-window>div').html('<img src="'+src+'" style="width:100%;height:100%;padding:0px;"/>');
		$('#image-window').window({
			title: '图片展示',
	        left:($(window).width() - image.width*n) * 0.5,       
			top:($(window).height() - image.height*n) * 0.5, 
			width: image.width*n,
			height: image.height*n,
			modal: false,
			shadow: false,
			closed: false,
			draggable:true,
			collapsible:false,
			minimizable:false,
			maximizable:false
		});
	}
	image.src=src;
}
function initUser(){
	//添加空用户
	for(var i=0;i<sysNum;i++){
		addNullUser();
	}
}
/*添加空用户*/
function addNullUser(){
	var _html='';
	_html+='<div onmouseover="showCloseBtn(this);" onmouseout="hideCloseBtn(this);">';
	_html+=getUserListHtml();
	_html+='</div>';
	$("#user-list").append(_html);
}
/*获取微信用户座位*/
function getUserListHtml(){
	var _html='';
	_html+='<input type="hidden" name="userState" value="0"/>';
	_html+='<li class="user-item">';
	_html+='<div class="user-item-left">';
	_html+='<img src="/image/add-img.png" onclick="searchUserInfo();"></img><div><font style="color:#ccc">微信用户名称</font></div>';
	_html+='<div class="user-item-right">';
	_html+='<img src="/image/face_0.png"></img><font style="color:#ccc">0</font>';
	_html+='</div>';
	_html+='</div>';
	_html+='</li>';
	return _html;
}
/*开启关闭调节*/
function changeImg(obj) {
	if (obj.id == "onImg") {
		obj.src = "/image/off.png";
		obj.id = "offImg";
		sysFlag=false;
	} else {
		obj.src = "/image/on.png";
		obj.id = "onImg";
		sysFlag=true;
	}
	var status="关闭服务";
	if(sysFlag)status="开启服务";
	sendBeatStatus(status);
}
function sendBeatStatus(status){
    $.ajax({
		type : "post",
		url : "/csc/sendBeatStatus",
		dataType : "json",
		data : {
			"seatId":admin.id,
			"status":status
		},
		success : function(data) {
			
        }
	});
}
function changeVoice(obj) {
	if (obj.id == "voiceOk") {
		obj.src = "/image/voice-no.png";
		obj.id = "voiceNo";
		voiceFlag=false;
	} else {
		obj.src = "/image/voice-ok.png";
		obj.id = "voiceOk";
		voiceFlag=true;
	}
}
/*获取正在与微信用户聊天的人数*/
function getUserOnlineNum(){
	var num=0;
	var o=$(".user-item-online");
	if(o.length==sysNum){
		return admin.customerNum;
	}
	$.each(o,function(){
		var n2=$(this).css("background");
		if(n2!="#f5f5f5"){
			num++;
		}
	});
	return num;
}
/*系统循环选择用户*/
function sysCheck(){
	if(getuser_num%5==0&&sysFlag){
		getUser(); 
		sendBeat();
	}
	if(getuser_num%15==0){
		changeBusyOrFree();
		showNotice(getuser_num/15);
	}
	getuser_num++;
	wxWaitNum();
	if(getuser_num%2==0){
		if(csToCs!="")pushMsgText();//推送坐席聊天信息
	}
	setTimeout("sysCheck()", 1000);
}

function getQuondamSeat(){
	
}

function getNullSeat(){
	var temp=$("input[name='userState']");
	for(var i=0;i<temp.length;i++){
		if($(temp[i]).val()==0){
			return i;
		}
	}
}
/*查询排队的微信用户*/
function getUser(){
	if(getUserOnlineNum()<admin.customerNum){
		var status="开启服务";
		sendBeatStatus(status);
	    $.ajax({
			type : "post",
			url : "/csc/allocateWxUser",
			dataType : "json",
			//async: false,
			data : {
				csId:admin.id,
				num:1
			},
			success : function(data) {
				if(data.length>0){
				    /*微信用户信息*/
					var temp=$.parseJSON(data[0]);
				    if(isSeat(temp.openid)){
				    	
						//找出通道位置
						var xth;
						
						//空位置
						var nth;
						
						// 如果已经在列表里
						var uData = getUserData(temp.openid);
						if(typeof(uData)!="undefined"){
							xth= uData.index;
							nth= uData.index;
						  	//清除历史记录
						  	$("#history"+nowOpenid).remove();
						  	//清除输入文本信息
						  	$("#msg"+nowOpenid).remove();
						  	$("#msgSendBtn").css("display","none");
						}
						
						if(typeof(xth)=="undefined"){
							for(var i=0;i<=userData.length;i++){
								if(typeof(userData[i])=="undefined"||userData[i]==null){
									xth=i;
									break;
								}
							}
						}
						
						//找出空位置
						if(typeof(nth)=="undefined" ){
							nth=getNullSeat();
						}
				    	userData[xth]=temp;
						userData[xth]["index"]=xth;
						userData[xth]["seat"]=nth;
						userData[xth]["status"]=true;
						var random=randomString(4);
						var id=userData[xth].openid.substring(4,userData[xth].openid.length)+random;
						userData[xth]["serviceId"]=id;
						//userData[xth].openid+=xth;
						//添加用户
						addUser(xth,temp.begin);
					    //找出可交换的位置
					    var dth=sysNum;
					    var dindex=sysNum;
						for(var i=0;i<=userData.length;i++){// ？？？
							if(typeof(userData[i])!="undefined"&&userData[i]!=null&& !userData[i].status){
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
						$(_temp[userData[xth].seat]).next().css("background","#ffa500");
						if(temp.begin != 0){
							$(_temp[userData[xth].seat]).next().css("background","#ed5565");
						}
						// 标记
						playVoice();
						setTimeout(function(){
							playVoice();
						},2000 );
						
						
				    }
				}
	        }
		});
	}else{
		var status="关闭服务";
		sendBeatStatus(status);
	}
}

/*通知后台坐席在线，并且得知有无转交用户到来*/
function sendBeat(){	
    $.ajax({
		type : "post",
		url : "/csc/sendBeat",
		dataType : "json",
		data : {
			seatId:admin.id
		},
		success : function(data) {
			if(data.status=="Transfer"){
				reConnUser(data.openId,"Transfer");
				//transferUserResult("Accept");
			}else if(data.status=="Invite"){
				reConnUser(data.openId,"Invite");
			}
			if(data.msgNum!=0){
				$("#msgNum").html(data.msgNum);
			}else{
				$("#msgNum").html("");
			}
			if(data.wxOpenId!=""&&data.wxOpenId.length>10){
				var userData = getUserData(data.wxOpenId);
				userData.transferFlag=false;
				transferFlag(userData.index);
			}
        }
	});
}
function transferUserResult(typeName){	
    $.ajax({
		type : "post",
		url : "/csc/transferUserEnd",
		dataType : "json",
		data : {
			typeName:typeName
		},
		success : function(data) {
        }
	});
}
/*安排微信用户入座*/
function addUser(i,mesStartTime){
	var _html='';
	_html+='<input type="hidden" name="userState" value="1"/>';
	_html+='<li class="user-item-online" onmousedown="reConnUserInit(this,'+i+');" onclick="connUser(this,'+i+');">';
	_html+='<div class="user-item-left">';
	if(userData[i].headimgurl=="")userData[i].headimgurl="/image/img_user.png";
	_html+='<img style="width:40px;height:40px;" src="'+userData[i].headimgurl+'"></img><div><font style="color:#000000">'+userData[i].nickname+'</font></div>';
	_html+='<div class="user-item-right" id="wait'+userData[i].openid+'">';
	_html+='<input type="hidden" name="time" value="0"/><img src="/image/face_0.png"/><font style="color:#000000">0</font>';
	_html+="<img class='delBtn' onclick='connClose();' src='/image/delete.png' style='position:absolute;z-index:77;top:12px;right:0px;height:16px;display:none;'></img>"
	_html+='</div>';
	_html+='</div>';
	_html+='</li>';
	var _temp=$("#user-list>div");
	$(_temp[userData[i].seat]).html(_html);
	var _temp=$("input[name='userState']");
	$(_temp[userData[i].seat]).val(userData[i].openid);
	$("#connContent").append('<div style="display:none" id="console'+userData[i].openid+'"></div>');
    var h20='<div id="xhis'+userData[i].openid+'" style="text-align:center;">';
    h20+='<img src="/image/clock.jpg" style="vertical-align:middle;"><a href="#" onclick=showHisMsgOneDay("'+userData[i].openid+'");>查看更多消息</a>';
    h20+='</div>';
    $("#console"+userData[i].openid).prepend(h20);
    showHisMsgToday(userData[i].openid,mesStartTime);
	$("#msg").append('<textarea name="msgtext" id="msg'+userData[i].openid+'" rows="" cols="" style="height:115px;width:100%;display:none;"></textarea>');
	$('#msg'+userData[i].openid).bind('keypress',function(event){
    	if(event.ctrlKey){
    		$('#msg'+userData[i].openid).val($('#msg'+userData[i].openid).val()+"\r\n");
    	}else if(event.keyCode == "13"){sendText();}
    });
	this.focus();
}
function changeBusyOrFree(){
    $.ajax({
		type : "post",
		url : "/csc/changeBusyOrFree",
		dataType : "json",
		//async: false,
		data : {
			"userId":admin.userId
		},
		success : function(data) {
			var panel3=$("#admin-list").accordion("panels");
			$(panel3).each(function(index){
				$(this).panel("setTitle",dept[index].deptName+"&nbsp;&nbsp;<img height='12px' src='/image/busy.png'></img>");
			});
			for(var n=0;n<dept.length;n++){
				dept[n]["msgNum"]=0;
			}
			for(var i=0;i<data.length;i++){
				var temp=getAdminInfoById(data[i].id);
				var deptName=data[i].deptName;
				var mm=0;
				for(var n=0;n<dept.length;n++){
					if(dept[n].deptName==deptName)mm=n;
				}
				if(data[i].isBusy==0){
					var panel2=$("#admin-list").accordion("panels");
					$(panel2).each(function(index){
						if(index==mm)$(this).panel("setTitle",dept[index].deptName+"&nbsp;&nbsp;<img height='12px' src='/image/free.png'></img>");
					});
					temp.isBusy=0;
					$("#isBusyImg"+data[i].id).attr("src","/image/free.png");
					//alert($("#isBusyImg"+data[i].id).parent().parent().parent().html());
			        var _html='<li id="admin-li-'+adminData[i].id+'" onmousedown="connAdmin(this,'+data[i].id+');" class="admin-item">';
			        _html+=$("#isBusyImg"+data[i].id).parent().parent().parent().html();
					_html+='</li>';
					$("#admin-li-"+data[i].id).remove();
					$("#admin-list-"+data[i].deptId).prepend(_html);
				}else{
					temp.isBusy=1;
					$("#isBusyImg"+data[i].id).attr("src","/image/busy.png");
				}
				dept[mm]["msgNum"]+=data[i].readed;
				if(data[i].readed!=0){
					$("#readedMsg"+data[i].id).html(data[i].readed);
				}else{
					$("#readedMsg"+data[i].id).html("");
				}
			}
			var panel4=$("#admin-list").accordion("panels");
			$(panel4).each(function(index){
				if(dept[index]["msgNum"]!=0){
					$(this).panel("setTitle",$(this).panel("options").title+"&nbsp;&nbsp;<font style='color:red;font-weight:bold;'>"+dept[index]["msgNum"]+"</font>");
				}else{
					$(this).panel("setTitle",$(this).panel("options").title);
				}
			});
		}
    });
}
var dept=new Array();
/*初始化座席信息*/
function initAdmin(){
    $.ajax({
		type : "post",
		url : "/csc/getSysUserInfo",
		dataType : "json",
		//async: false,
		data : {
		},
		success : function(data) {
			adminData=data;
			var busyArray=new Array();
			for(var i=0;i<adminData.length;i++){
				if(adminData[i].isBusy==0)busyArray[adminData[i].deptId]="free";
			}
			//var title="";
			for(var i=0;i<adminData.length;i++){
				var _html='';
		        if(!$("#admin-list-"+adminData[i].deptId).length){
					if(busyArray[adminData[i].deptId]!="free")busyArray[adminData[i].deptId]="busy";
		            $('#admin-list').accordion('add', {
		                title: adminData[i].deptName,//click:,
		                content: '<div id="admin-tab-'+adminData[i].deptId+'" class="easyui-panel" style="border:0px;height:140%;"><ul style="margin:4px;padding:0px;" id="admin-list-'+adminData[i].deptId+'"></ul></div>'
		            });
				}
		        _html+='<li id="admin-li-'+adminData[i].id+'" onmousedown="connAdmin(this,'+adminData[i].id+');" class="admin-item">';
		        _html+='<div class="admin-item-left">';
				_html+='<img src="/image/img_admin.jpg"></img>';
				_html+='<div><font class="fontLeft" style="color:#000">'+adminData[i].userName+'</font>';
		        _html+='<font class="fontRight" id="readedMsg'+adminData[i].id+'" style="color:red;float:right;font-weight:bold;"></font>';
				_html+='<img id="isBusyImg'+adminData[i].id+'" src="/image/busy.png"></img></div>';
				_html+='</div>';
				_html+='</li>';
				$("#admin-list-"+adminData[i].deptId).append(_html);
				var panel=$("#admin-list").accordion("panels");
				$(panel).each(function(index){
					var title=$(this).panel("options").title;
					dept[index]=new Object();
					dept[index]["deptName"]=title;
				});
			}
		}
    });
}
/*为每个微信用户创建一种Object,保存聊天座席是否在与之聊天*/
function initUserAdmin(){
	for(var i=0;i<sysNum;i++){
		userAdmin[i]=new Object();
	}
}
//微信等待用户数量
function wxWaitNum(){
    $.ajax({
		type : "post",
		url : "/csc/getAllWaitWx",
		dataType : "json",
		//async: false,
		data : {
			"seatId":admin.userId
		},
		success : function(data) {
			$("#sysSetNum").html(admin.customerNum);
			$("#wxWaitNum").html(data.userNum);
			$("#sysWaitNum").html(data.sysNum);
        }
	});
}
//座席求助
function connAdmin(obj,id){
	var data=getAdminInfoById(id);
	var busy=data.isBusy;
	var userIndex=getUserDataIndex();
	if(busy==0&&nowOpenid!=""&&id!=admin.id&&userIndex<10){
		if(userAdmin[userIndex]["id"+id]==0||typeof(userAdmin[userIndex]["id"+id])=="undefined"){
			$('#mm').menu('enableItem', $("#invite"));
		}else{
			$('#mm').menu('disableItem', $("#invite"));
		}
		$('#mm').menu('enableItem', $("#transfer"));
	}else{
		$('#mm').menu('disableItem', $("#transfer"));
		$('#mm').menu('disableItem', $("#invite"));
	}
	if(id==admin.id){
		$('#mm').menu('disableItem', $("#sendmsgtocs"));
	}else{
		$('#mm').menu('enableItem', $("#sendmsgtocs"));
	}
	$(obj).bind('contextmenu', function(e) {
  		$('#mm').menu('show', {  
		  	left: e.pageX,  
		  	top: e.pageY  
		});  
  		$('#mm').menu({  
  		    onClick:function(item){  
  		        if(item.id=="transfer")transferAdmin(id); 
  		        else if(item.id=="invite")inviteAdmin(id);
  		        else if(item.id=="sendmsgtocs")connAdmins(data.userId,data.userName); 
  		    }  
  		});
        // 你弹出右键的函数
        //下面这句必须
        return false;
    });
}
/*转交其他座席*/  //标记
function transferAdmin(id){
	var num=connCloseFlag();
	if(num==0){
		$.ajax({
			type : "post",
			url : "/csc/transferUserBegin",
			dataType : "json",
			async: false,
			data : {
				seatId:admin.id,
				openId:nowOpenid,
				csId:id
			},
			success : function(data) {
				if(data.status=="OK"){
					var index=getUserDataIndex();
					transferFlag2(index);
					/* getAdminInfoById(data.csId);
					+getAdminInfoById(data.csId).userName
					+getAdminInfoById(data.seatId).userName */
					$.messager.alert("提示消息","用户 “"+data.nickName+"” 已转交到坐席 “"+getAdminInfoById(data.csId).userName+"” 处，请等待。","info");
				}
			}
		});
	}else{
		$.messager.alert("提示消息","请先完成工单，然后再转交。","info");
	}
}
/*根据ID查询出座席信息*/
function getAdminInfoById(id){
	for(var i=0;i<adminData.length;i++){
		if(adminData[i].id==id)return adminData[i];
	}
}
/*根据ID查询出座席队列信息*/
function getAdminIndexById(id){
	for(var i=0;i<adminData.length;i++){
		if(adminData[i].id==id)return i;
	}
}
/*邀请其他座席*/
function inviteAdmin(id){
	var data=getAdminInfoById(id);
	//var adminIndex=getAdminIndexById(id);
	var index=getUserDataIndex();
	if(userAdmin[index]["id"+id]==0||typeof(userAdmin[index]["id"+id])=="undefined"){
		//当前聊天对应
		userAdmin[index]["id"+id]=1;
		//查询该座席是否繁忙
		//不繁忙，1改变状态，2邀请成功
		//$("#admininfo").append('<span ondblclick="removeAdmin(this,'+id+');" style="margin-left:8px;cursor:hand;"><img style="height:40px;vertical-align:middle;" src="/image/admin_type_'+data.type+'.jpg"></img>'+data.name+'</span>');
		//繁忙不改变状态，1邀请失败，提示座席忙
		$.ajax({
			type : "post",
			url : "/csc/inviteUserBegin",
			dataType : "json",
			async: false,
			data : {
				openId:nowOpenid,
				csId:id
			},
			success : function(data) {
				if(data.status=="OK"){
					$.messager.alert("提示消息","已邀请此坐席，请等待。","info");
				}
			}
		});
		//更新座席繁忙状态
	}
}
/*取消邀请其他座席*/
function removeAdmin(obj,id){
	$(obj).css("display","none");
	//$(obj).next().css("display","none");
	//1改变状态，2取消成功
	var userIndex=getUserDataIndex();
	//取消邀请其他座席
	userAdmin[userIndex]["id"+id]=0;
}
//websocket标记
var host = window.location.host.split(":")[0];
WEB_SOCKET_SWF_LOCATION = "/js/websocket/WebSocketMainInsecure.swf";
WEB_SOCKET_SUPPRESS_CROSS_DOMAIN_SWF_ERROR = true;
WEB_SOCKET_DEBUG = true;
var basePath = '<%=basePath%>';
/* basePath+"/crossdomain.xml" */
/* "xmlsocket://" + host + ":10844" */
	 try {
		WebSocket.loadFlashPolicyFile("xmlsocket://" + host + ":10844");
	} catch (e) {
	} 
//websocket标记

function openRoad(index,type){
	//if(typeof(ws[index])=="undefined"||ws[index]==null){
    var  tmp = basePath + "/chatSocket?type="+type+"&user="+admin.userId+"&openId="+userData[index].openid+"&serviceId="+userData[index].serviceId;
    tmp = tmp.replace("http","ws");
	ws[index]= new WebSocket(encodeURI(tmp));
	connect(ws[index]);
	//}
}
function openMsgRoad(index){ //废弃方法
	if(typeof(ws[index])=="undefined"||ws[index]==null){
	    var  tmp = basePath + "/chatSocket?type=FROMMSG&user="+admin.userName+"&openId=CSTOCSALL&serviceId="+admin.userId;
	    tmp = tmp.replace("http","ws");
		ws[index]= new WebSocket(encodeURI(tmp));
		connect(ws[index]);
	}
}
function reConnUserInit(obj,index){
	if(!userData[index].status){
		$(obj).bind('contextmenu', function(e) {
	  		$('#reconnect').menu('show', {  
			  	left: e.pageX,  
			  	top: e.pageY  
			});  
	  		$('#reconnect').menu({  
	  		    onClick:function(item){  
	  		        if(item.id=="reconuser"){
	  		        	reConnUser(userData[index].openid,"Choose"); 
	  		        }
	  		    }  
	  		});
	        // 你弹出右键的函数
	        //下面这句必须
	        return false;
		});
	}
}
function leaveFlag(index){
	var h=$("#user-list").find("li");
	$(h[userData[index].seat]).css("background","#f5f5f5");
	var xxx=$(h[userData[index].seat]).find("img");
	$(xxx[0]).css("filter","gray");
	$(xxx[1]).css("filter","gray");
	var fff=$(h[userData[index].seat]).find("font");
	for(var i=0;i<fff.length;i++){
		$(fff[i]).css("color","#ccc");
	}
	/* var temp=$("input[name='userState']");
	$(temp[index]).val(0);*/
	
	userData[index].status=false; 
	//关闭聊天通道
	ws[index]=null;
	var random=(Math.random()+"").substring(2,12);
	html=getAutotextHtml(random,"用户已离开");
	$("#console" + userData[index].openId).append(html);
	$("#msgbox").scrollTop(9999999);
}
function transferFlag(index){//标记
	var h=$("#user-list").find("li");
	$(h[userData[index].seat]).css("background","#f5f5f5");
	var xxx=$(h[userData[index].seat]).find("img");
	$(xxx[0]).css("filter","gray");
	$(xxx[1]).css("filter","gray");
	var fff=$(h[userData[index].seat]).find("font");
	for(var i=0;i<fff.length;i++){
		$(fff[i]).css("color","#ccc");
	}
	userData[index].status=false;
	//关闭聊天通道
	ws[index]=null;
	//关闭聊天界面
	connToClose();
	var random=(Math.random()+"").substring(2,12);
	var html=getAutotextHtml(random,"用户转交成功。");
	$("#console" + nowOpenid).append(html);
	$("#msgbox").scrollTop(9999999);
}
/*用户转交中*/
function transferFlag2(index){
	userData[index]["transferFlag"]=true;
	var random=(Math.random()+"").substring(2,12);
	var html=getAutotextHtml(random,"正在转交，请稍后。");
	$("#console" + nowOpenid).append(html);
	$("#msgbox").scrollTop(9999999);
}
/**
 *与微信用户建立联系
 *用户接入色ffa500
 *主动拉人色ffa501
 *当前聊天色b8d45d
 *等待聊天色cde9f5
 *用户离开色f5f5f5
 */
 // 标记
function connUser(obj,index){
	csToCs="";
	nowOpenid=userData[index].openid;
	var tempColor=$(".user-item-online");
	$.each(tempColor,function(){
		var n2 = "";
		if(isIE()){
			n2=$(this).css("background");
		}
		else{
			n2=rgb2hex($(this).css("background-color"));
		}
		if(n2!="#ffa500"&&n2!="#ffa501"&&n2!="#ffa502"&&n2!="#f5f5f5"&&n2!="#ed5565")$(this).css("background","#cde9f5");
	});
	var bgc = $(obj).css("background-color");
	if(isIE()){
		bgc = $(obj).css("background");
	}
	else{
		bgc = rgb2hex(bgc);
	}
	if(bgc=="#ffa500"){//第一次点击，自动发送消息
    	$.ajax({
    		type : "post",
    		url : "/csc/getUserStatus",
    		dataType : "json",
			async: false,
    		data : {
    			openId:nowOpenid,
    			csId:admin.id
    		},
    		success : function(data) {
    			if(data.status=="OK"){//在服务队列中
    				openRoad(index,"FROMWX");
    			}
    			else if(data.status=="END"){
    				$.messager.alert("提示消息","此微信用户已断开连接！","warning");
    				leaveFlag(index);
    			}
    		}
        });
	}else if(bgc=="#ffa501"){//转交
    	$.ajax({
    		type : "post",
    		url : "/csc/getUserStatus",
    		dataType : "json",
			async: false,
    		data : {
    			openId:nowOpenid,
    			csId:admin.id
    		},
    		success : function(data) {
    			if(data.status=="OK"){//在服务队列中
    				//openRoad(index,"FROMWX");//正常
    				openRoad(index,"FROMCS");//主动拉人,转交，邀请
    			}else if(data.status=="END"){//用户已断开
    				$.messager.alert("提示消息","此微信用户已断开连接！","warning");
    				leaveFlag(index);
    			}
    		}
        });
	}else if(bgc=="#ffa502"){//第一次点击，自动发送消息
    	openRoad(index,"FROMCSYQ");//邀请
	}else if(bgc=="#ed5565"){	// 标记 留言
		$.ajax({
    		type : "post",
    		url : "/csc/getUserStatus",
    		dataType : "json",
			async: false,
    		data : {
    			openId:nowOpenid,
    			csId:admin.id
    		},
    		success : function(data) {
    			if(data.status=="OK"){//在服务队列中
    				openRoad(index,"FROMMESSAGE");
    			}else if(data.status=="END"){//用户已断开
    				$.messager.alert("提示消息","此微信用户已断开连接！","warning");
    				leaveFlag(index);
    			}
    		}
        });
	}

    if(bgc!="#f5f5f5"){
    	$(obj).css("background","#b8d45d");
    }
    
	userAdmin[index]["id"+admin.id]=1;
	//聊天界面上方显示聊天者信息
	$("#userinfo").css("display","block");
	connClear();
	var userinfo=$("#userinfo>div");
	//$(userinfo[0]).append('<span><img style="height:40px;vertical-align:middle;" src="/image/admin_type_'+admin.type+'.jpg"></img>'+admin.userId+'</span>');
	connInit(nowOpenid);
	$(userinfo[1]).html("微信名称："+userData[index].nickname);
	$(userinfo[2]).html("微信标识："+userData[index].openid);
	var tabs = $('#tabs').tabs('tabs');	
	$.each(tabs,function(i,n){ 
		var _temp=$('#tabs').tabs('getTab',i).panel('options');
		if(_temp.title=="消息记录"+(index+1)||_temp.title=="工单记录"+(index+1)||_temp.title=="历史工单"){
			_temp.tab.show();
		}else{
			_temp.tab.hide();
		}
    });-
	$('#tabs').tabs('select', "历史工单");
	$("#connContent>div").each(function(){
		$(this).css("display","none");
	});
	$("#console"+nowOpenid).css("display","block");
	//查询历史记录
	$("#history>div").each(function(){
		$(this).css("display","none");
	});
	// 标记 这里可以先注释，这里有关工单的东西， 因为没有连不到内网 所以这里比较慢
	/* if(!$("#history"+nowOpenid).length){
		historyTab(nowOpenid);
	}  */
	$("#history"+nowOpenid).css("display","block");
	//显示指定文本框
	$("textarea[name=msgtext]").each(function(){
		$(this).css("display","none");
	});
  	$("#msgSendBtn").css("display","block");
	$("#msg"+nowOpenid).css("display","block");
}
var csToCs="";
function addMsgTextMoreBtn(){
    var h20='<div id="xhisCSTOCSALL" style="text-align:center;">';
    h20+='<img src="/image/clock.jpg" style="vertical-align:middle;"><a href="#" onclick=showMsgTextMore();>查看更多消息</a>';
    h20+='</div>';
    $("#consoleCSTOCSALL").prepend(h20);
}
function csToCsInit(){
	$("#connContent").append('<div style="display:none" id="consoleCSTOCSALL"></div>');
	addMsgTextMoreBtn();
	$("#msg").append('<textarea name="msgtext" id="msgCSTOCSALL" rows="" cols="" style="height:115px;width:100%;display:none;"></textarea>');
	$('#msgCSTOCSALL').bind('keypress',function(event){
    	if(event.ctrlKey){
    		$('#msgCSTOCSALL').val($('#msgCSTOCSALL').val()+"\r\n");
    	}else if(event.keyCode == "13"){sendText();}
    });
}
function connAdmins(user,userName){
	nowOpenid="CSTOCSALL";
	var tempColor=$(".user-item-online");
	$.each(tempColor,function(){
		var n2=$(this).css("background");
		if(n2=="#b8d45d")$(this).css("background","#cde9f5");
	});
	var tabs = $('#tabs').tabs('tabs');	
	$.each(tabs,function(i,n){ 
		var _temp=$('#tabs').tabs('getTab',i).panel('options');
		if(_temp.title=="历史工单"){
			_temp.tab.show();
		}else{
			_temp.tab.hide();
		}
    });
	$('#tabs').tabs('select', "历史工单");
	$("#history>div").each(function(){
		$(this).css("display","none");
	});
	$("#userinfo").css("display","block");
	var userinfo=$("#userinfo>div");
	$(userinfo[0]).html(userName);
	$(userinfo[1]).html("");
	$(userinfo[2]).html("");
	$("#connContent>div").each(function(){
		$(this).css("display","none");
	});
	$("#consoleCSTOCSALL").css("display","block");
	//显示指定文本框
	$("textarea[name=msgtext]").each(function(){
		$(this).css("display","none");
	});
  	$("#msgSendBtn").css("display","block");
	$("#msgCSTOCSALL").css("display","block");
	if(csToCs!=user){
		$("#consoleCSTOCSALL").html("");
		addMsgTextMoreBtn();
		csToCs=user;
		$.ajax({	
			type : "post",
			url : "/csc/getMsgText",
			dataType : "json",
			//async: false,
			data : {
				"fromUser":admin.userId,
				"toUser":csToCs
			},
			success : function(data) {
				for(var i=0;i<data.length;i++){
					$("#msgCSTOCSALL").val("");
					var html ="";
					if(data[i].fromUser==admin.userId)html=getRightMsgTextHtml(data[i]);
					else html=getLeftMsgTextHtml(data[i]);
					$("#consoleCSTOCSALL").append(html);
					$("#msgbox").scrollTop(9999999);
				}
	        }
		});
	}else{
		$("#msgbox").scrollTop(9999999);
	}
}
function sendMsgText(){
	var message = $.trim($("#msgCSTOCSALL").val());
	message = removeHtmlTab(message);
	alert(message);
	if (message == "") {
		$.messager.alert("发送消息", "发送的信息不能为空！", "warning");
		return;
	}
	$.ajax({
		type : "post",
		url : "/csc/sendMsgText",
		dataType : "json",
		//async: false,
		data : {
			"fromUser":admin.userId,
			"toUser":csToCs,
			"content":message
		},
		success : function(data) {
			if(data.id>0){
				$("#msgCSTOCSALL").val("");
			}else{
				$.messager.alert("提示消息","发送消息失败，请联系管理员！","info");
			}
        }
	});
}
/*聊天结束*/
function connClose(){
	if(nowOpenid!=""){
		$.messager.confirm('关闭提示', '你确定关闭本次服务吗?', function(r){
			if (r){
				var num=connCloseFlag();
				if(num==0){
					var index=getUserDataIndex();
					if (!userData[index].status) {
						connToClose();
					}else{
				      	//通知后台
					    $.ajax({
							type : "post",
							url : "/csc/endServicing/"+admin.id+"/"+nowOpenid,
							dataType : "json",
							//async: false,
							data : {
							},
							success : function(data) {
								if(data.status=="OK"||data.status=="END"){
									connToClose();
								}else{
									$.messager.alert("提示消息","微信服务断开失败，请联系管理员！","info");
								}
					        }
						});
					}
				}else{
					$.messager.alert("关闭提醒","现有"+num+"份工单未保存！","warning");
				}
			}
		});
	}else{
		$.messager.alert("提示消息","请选择微信用户后关闭！","info");
	}
}
function connToClose(){
	$("#userinfo").css("display","none");
	//清除聊者信息
	connClear();
	var index=getUserDataIndex();
	//清除聊天内容
	$("#connContent>div").each(function() {
		if ($(this).css("display") == "block") {
			$(this).remove();
		}
	});
	//清除tab标签页
    for(var i=0;i<1;i++){
    	if($('#tabs').tabs('getTab',"消息记录"+(index+1))!=null){//清除消息记录
        	$('#tabs').tabs('close',"消息记录"+(index+1));
        	i--;
    	}else if($('#tabs').tabs('getTab',"工单记录"+(index+1))!=null){//清除工单记录
    		$('#tabs').tabs('close',"工单记录"+(index+1));
        	i--;
    	}
    }
  	//清除用户列表
	var _temp=$("#user-list>div");
	$(_temp[userData[index].seat]).html(getUserListHtml());
	//清除用户信息
	userData[index]=null;
	//关闭聊天通道
	ws[index]=null;
	//清除邀请聊天者标识
	userAdmin[index]=new Object();
  	//清除历史记录
  	$("#history"+nowOpenid).remove();
  	//清除输入文本信息
  	$("#msg"+nowOpenid).remove();
  	$("#msgSendBtn").css("display","none");
	//清除聊天者标识
	nowOpenid="";
	$.messager.alert("提示消息","已成功与微信用户断开服务！","info");
}

/*添加其他邀请者*/
function connInit(openId){
  	//通知后台
    $.ajax({
		type : "post",
		url : "/csc/getUsersInfo",
		dataType : "json",
		//async: false,
		data : {
			openId:openId
		},
		success : function(data) {
			$("#admininfo").html("");
			for(var i=0;i<data.length;i++){
				var adminM=getAdminInfoById(data[i]);
				$("#admininfo").append('<span style="margin-left:8px;cursor:hand;"><img style="height:40px;vertical-align:middle;" src="/image/img_admin.jpg"></img>坐席'+adminM.userId+'</span>')
			}
        }
	});
}
/*清除聊天*/
function connClear(){
	//清除聊天者信息
	$("#userinfo>div").each(function(){
		$(this).html("");
	});
	
}
/*显示聊天内容*/
function connContent(){
	
}
function showHisMsgOneMore(){
	closeZmessage();
	$("#hisMsgOneMore").css("display", "block");
}
/*新增消息记录页*/
function addNewHistoryTab(){
	//显示查询页
	if(nowOpenid!=""){
		var bTime=$("#bTime").datebox("getValue");
		var eTime=$("#eTime").datebox("getValue");
		var d=new Date();
		d.setMonth(d.getMonth()-3);
		var aTime=d.Format("yyyy-MM-dd hh:mm:ss");
		if(bTime==""){
			$.messager.alert("消息记录","开始时间不能为空！",'warning');
		}else if(eTime==""){
			$.messager.alert("消息记录","结束时间不能为空！",'warning');
		}else if(bTime>=eTime){
			$.messager.alert("消息记录","开始时间不能大于结束时间！",'warning');
		}else if(aTime>=bTime){
			$.messager.alert("消息记录","开始时间不能为三个月前！",'warning');
		}else{
			var index=getUserDataIndex()+1;
			addHisTab("消息记录"+index,bTime,eTime);
		}
	}else{
		$.messager.alert("消息记录","请选择微信用户后查询记录！",'warning');
	}
}
/*新增工单记录页*/
function addNewWorkTab(){
	if(nowOpenid!=""){
		var index=getUserDataIndex();
		if (!userData[index].status) {
			$.messager.alert("提示信息", "此微信用户已断开连接！", "warning");
			return;
		}
		var serviceId=userData[index].serviceId;
		addWorkTab("工单记录"+(index+1),serviceId);
	}else{
		$.messager.alert("添加工单","请选择微信用户后添加工单！",'warning');
	}
}
//信息处理
function workContent(obj,type,csOrWx){
	var id=$(obj).find("input[name='workId']").val();
	var msgId=$(obj).find("input[name='msgId']").val();
	if(id==""){
		saveWorkMenu(obj);
	}else{
		cancelWorkMenu(obj);
	}
	var push=0;
	if(type!=1){//非文字信息不能推送
		$('#work').menu('disableItem', $("#push")); 
	}else{
		$('#work').menu('enableItem', $("#push")); 
	}
	$(obj).bind('contextmenu', function(e) {
  		$('#work').menu('show', {  
		  	left: e.pageX,  
		  	top: e.pageY  
		});  
  		$('#work').menu({  
  		    onClick:function(item){  
  		        if(item.id=="push"){
  		        	pushWorkContent(obj,type); 
  		        }
  		        else if(item.id=="save"){
  		        	saveOrUpdateAttachment(obj,msgId,1,csOrWx); 
  		        }
  		        else if(item.id=="cancel"){
  		        	saveOrUpdateAttachment(obj,msgId,2,csOrWx); 
  		        }
  		    }  
  		});
        // 你弹出右键的函数
        //下面这句必须
        return false;
    });
}
/*改变右键菜单属性*/
function saveWorkMenu(obj){
	var item = $('#work').menu('findItem', '取消保存'); 
	if(item!=null){
		$('#work').menu('setText', { 
			target: item.target, 
			text: '保存附件' 
		}); 
		$("#cancel").attr("id","save");
	}
}
/*取消保存菜单变更*/
function cancelWorkMenu(obj){
	var item = $('#work').menu('findItem', '保存附件'); 
	$('#work').menu('setText', { 
		target: item.target, 
		text: '取消保存' 
	}); 
	$("#save").attr("id","cancel");
}
/*关闭事件类型窗口*/
function colseSjlxWindow(type,value){
	var message="sjlx|"+type+","+value;
	closeWindow("sjlx-"+_type+"-window");
	//获取当前选中的tab
	var tab = $('#tabs').tabs('getSelected');  
	var title=tab.panel('options').title;
	var index=getUserDataIndex()+1;
	if(title=="工单记录"+index){
	    var ifr = $("iframe",tab);
	    var targetOrigin = 'http://10.224.2.177:7001';
	    //var targetOrigin = 'http://localhost:8088';
	    ifr[0].contentWindow.postMessage(message,targetOrigin);
	}else{
		$.messager.alert("推送信息","请选择工单后推送信息！",'warning');
	}
}
//推送信息
function pushWorkContent(obj,type){
	var msg=$(obj).text();
	var message="gdlr|"+msg;
	//改变背景
	//获取当前选中的tab
	var tab = $('#tabs').tabs('getSelected');  
	var title=tab.panel('options').title;
	var index=getUserDataIndex()+1;
	if(title=="工单记录"+index){
	    var ifr = $("iframe",tab);
	    var targetOrigin = 'http://10.224.2.177:7001';
	    //var targetOrigin = 'http://localhost:8088';
	    ifr[0].contentWindow.postMessage(message,targetOrigin);
	}else{
		$.messager.alert("推送信息","请选择工单后推送信息！",'warning');
	}
}
//推送消息吴杰
function pushWorkContent1(obj,type){
	var msg=$(obj).text();
	//改变背景
	//获取当前选中的tab
	var tab = $('#tabs').tabs('getSelected');  
	var title=tab.panel('options').title;
	var index=getUserDataIndex()+1;
	if(title=="工单记录"+index){
	    var ifr = $("iframe",tab);
	    var targetOrigin = 'http://10.224.2.177:7001';
	    //var targetOrigin = 'http://localhost:8088';
	    if(msg.indexOf("(") > 0 && msg.indexOf(")")> 0 && msg.indexOf(",")>0 && msg.indexOf(".")>0 && str.substr(str.length-1,1)==")"){
	    	msg1 = msg.substring(0,msg.indexOf("("));
	    	var message="wzms|"+msg1;
	    	ifr[0].contentWindow.postMessage(message,targetOrigin);
	    	msg=revert(msg);
	    	var msg2=msg.substring(1,msg.indexOf(")"));
	    	msg2=revert(msg2);
	    	var msg3=msg2.substring(0,msg2.indexOf(","));
	    	var message1="dljd|"+msg3;
	    	ifr[0].contentWindow.postMessage(message1,targetOrigin);
	    	
	    	msg4=msg2.substring(msg2.indexOf(","),msg2.lengh);
	    	var message2="dlwd|"+msg4;
	    	ifr[0].contentWindow.postMessage(message2,targetOrigin);
	    	
	    	
			
		}else{
			var message="gdlr|"+msg;
			 ifr[0].contentWindow.postMessage(message,targetOrigin);
		}
	}else{
		$.messager.alert("推送信息","请选择工单后推送信息！",'warning');
	}
}
function revert(str){

  var arr=str.split('');

  var  len=arr.length;

  for(var i=0;i<=len/2;i++){

    	var temp=arr[i];
		arr[i]=arr[len-1-i];
		arr[len-1-i]=temp;

	}

return arr.join('');

}

/*保存或变更附件*/
function saveOrUpdateAttachment(obj,msgId,type,csOrWx){
	//获取当前选中的tab
	var tab = $('#tabs').tabs('getSelected'); 
	var title=tab.panel('options').title;
	var index=getUserDataIndex()+1;
	if(title=="工单记录"+index){
		var csMsgIds="";
		var wxMsgIds="";
		if(csOrWx==2){
			csMsgIds=msgId;
		}else{
			wxMsgIds=msgId;
		}
		var workId="";
		if(type==1){
			workId=$("iframe",tab).attr("id");
		}else if(type==2){
			workId=$(obj).find("input[name='workId']").val();
		}
	    $.ajax({
			type : "post",
			url : "/csc/addAttachment",
			dataType : "json",
			//async: false,
			data : {
				'jsonStr':'[{"workunitId":"'+workId+'","csMsgIds":"'+csMsgIds+'","wxMsgIds":"'+wxMsgIds+'","type":"'+type+'"}]'
			},
			success : function(data) {
				if(data.status=="OK"){
					if(type==1){//保存附件
						$(obj).append("<img style='height:40px;position:absolute;bottom:0px;right:0px;z-index:99;' src='/image/work.png'></img>");
						$(obj).find("input[name='workId']").val(workId);
					}else if(type==2){//取消附件保存
						$(obj).find("img:first").remove();
						$(obj).find("input[name='workId']").val("")
					}
				}else{
					$.messager.alert("提示消息","发送信息异常，请与管理员联系！","warning");
				}
	        }
		});
	}else{
		if(type==1)$.messager.alert("保存附件","请选择工单后保存附件！",'warning');
	}
}
/*保存或变更附件*/
function saveOrUpdateServiceId(msgId,serviceId){
    $.ajax({
		type : "post",
		url : "/csc/addServiceId",
		dataType : "json",
		//async: false,
		data : {
			'msgId':msgId,
			'serviceId':serviceId
		},
		success : function(data) {
        }
	});
}
/*新增选项卡*/
function addWorkTab(nickName,serviceId) {
	var index=getUserDataIndex();
	var tabs = $('#tabs').tabs('tabs');
	if(typeof(userData[index]["workNum"])=="undefined")userData[index]["workNum"]=0;
	userData[index]["workNum"]++;
	var random="0"+userData[index]["workNum"];
	var id=serviceId+random.substring(random.length-2,random.length);
	var content = "<div id='tab"+nickName+"'><iframe id='"+id+"' frameborder='0' height='100%' src='http://10.224.2.177:7001/WebRoot/jsp/wx/wxdj.jsp?callSeq="+id+"&callNum=&phone="+userData[index].phone+"&Agentid="+admin.userId+"&Type=wx&org=5010'></iframe></div>";
	$('#tabs').tabs('add', {
		title : nickName,
		content : content,
		closable : true
	});
}
/*新增选项卡*/
function addHisTab(nickName,bTime,eTime) {
	var content = "<iframe frameborder='0' height='100%' src='/hisMsgOneMore.jsp?openid="+nowOpenid+"&bTime="+bTime+"&eTime="+eTime+"'></iframe>";
	$('#tabs').tabs('add', {
		title : nickName,
		content : content,
		closable : true
	});
}
function addCtnuWorkTab(id){
	var index=getUserDataIndex()+1;
	var nickName="工单记录"+index;
	var addFlag=true;
	var tabs = $('#tabs').tabs('tabs');
	$.each(tabs, function(i, n) {
		var workId = $("iframe", $('#tabs').tabs('getTab', i)).attr("id");
		if(id==workId){
			$('#tabs').tabs('select', i);
			addFlag=false;
		}
	});
	if(addFlag){
		var content = "<div id='tab"+nickName+"'><iframe id='"+id+"' frameborder='0' height='100%' src='http://10.224.2.177:7001/WebRoot/jsp/wx/wxdj_tz.jsp?callSeq="+id+"&Agentid="+admin.userId+"'></iframe></div>";
		$('#tabs').tabs('add', {
			title : nickName,
			content : content,
			closable : true
		});
	}
}
function addHisWorkTab(id,myid){
	var index=getUserDataIndex();
	var nickName="工单记录"+(index+1);
	var addFlag=true;
	workAdmin[index][id] = 1;
	var tabs = $('#tabs').tabs('tabs');
	$.each(tabs, function(i, n) {
		var workId = $("iframe", $('#tabs').tabs('getTab', i)).attr("id");
		if(id==workId){
			$('#tabs').tabs('select', i);
			addFlag=false;
		}
	});
	if(addFlag){
		var content = "<div id='tab"+nickName+"'><iframe id='"+id+"' frameborder='0' height='100%' src='http://10.224.2.177:7001/WebRoot/jsp/wx/wxck.jsp?callSeq="+id+"&MYID="+myid+"'></iframe></div>";
		$('#tabs').tabs('add', {
			title : nickName,
			content : content,
			closable : true
		});
	}
}
/*将常用语植入文本框*/
function showMessage(str){
	$("#msg"+nowOpenid).val($("#msg"+nowOpenid).val()+str);
	$("#msg"+nowOpenid).focus();
	closeZmessage();
}
/*显示常用语*/
function showZmessage() {
	$("#message").empty();
	closeZmessage();
	getWordTypeList();
	$.ajax({
		type : "post",
		url : "/csc/selectUsuallyWords",
		dataType : "json",
		//async: false,
		data : {
			csId:admin.id
		},
		success : function(data) {
			$("#message").empty();
			if(data.length>0){
				var wordTypeNames=new Array();
				var wordTypeIds=new Array();
				if(wordTypeName!=""){
					wordTypeNames=wordTypeName.split(",");
					wordTypeIds=wordTypeId.split(",");
				}
				var _span="<span class='tip4' style='background-color:#aaaaaa' onclick='showWordsTitle(this,0);'>系统常用语</span>";
				var _div="<div class='tip5' id='words0' style='position:absolute;height:75px;overflow-y:auto;width:100%;'></div>";
				for(var i=0;i<wordTypeNames.length-1;i++){
					_span+="<span class='tip4' style='background-color:#fafafa' onclick='showWordsTitle(this,"+wordTypeIds[i]+");'>"+wordTypeNames[i]+"</span>";
					_div+="<div class='tip5' id='words"+wordTypeIds[i]+"' style='position:absolute;display:none;height:75px;overflow-y:auto;width:100%;'></div>";			
				}
				$("#message").append(_span);
				$("#message").append(_div);
				for(var i=0;i<data.length;i++){
					var _html="";
					if(data[i].isSystem==1){
						_html='<b><a href="#" onclick=showMessage("'+data[i].usuallyWords+'"); title="系统常用语">'+data[i].title+'</a></b><br>';
					}else{
						_html='<a href="#" onclick=showMessage("'+data[i].usuallyWords+'"); title="自定义常用语">'+data[i].title+'</a><br>';
					}
					$("#words"+data[i].type).append(_html);
				}
				$("#message").css("display", "block");
			}else{
				$.messager.alert("提示消息","没有可用的常用语，请联系管理员！","warning");
			}
        }
	});
}
/*关闭所有浮动层*/
function closeZmessage() {
	//关闭常用语
	$("#qqface").css("display","none");
	$("#message").css("display","none");
	$("#hisMsgOneMore").css("display","none");
}
function showQQFace(){
	closeZmessage();
	$("#qqface").css("display","block");
}
function choseQQFace(id){
	var index=getUserDataIndex();
    $.ajax({
		type : "post",
		url : "/csc/sendQQFace",
		dataType : "json",
		data : {
			"id":id
		},
		success : function(data) {
			$("#msg"+nowOpenid).val($("#msg"+nowOpenid).val()+data.showName);
			closeZmessage();
        }
	});
}
	/*获取微信用户信息*/
	function getUserData(FromUserName) {
		for (var i = 0; i < userData.length; i++) {
			if (userData[i] != null && FromUserName == userData[i].openid )
				return userData[i];
		}
	}
	function isSeat(FromUserName) {
		for (var i = 0; i < userData.length; i++) {
			if (userData[i] != null && FromUserName == userData[i].openid && userData[i].status)
				return false;
		}
		return true;
	}
	
	/*获取微信用户座位信息*/
	function getUserDataIndex() {
		if(nowOpenid=="CSTOCSALL"){
			return 10;
		}
		for (var i = 0; i < userData.length; i++) {
			if (userData[i] != null && nowOpenid == userData[i].openid)
				return userData[i].index;
		}
	}
	/*连接消息通道，发送信息给微信用户*/
	function connect(ws) {
		ws.onopen = function() {
			//alert("open");
		};
		ws.onmessage = function(event) {
			var data = $.parseJSON(event.data);
			if(data.Flag=="CsToCs"&&data.data.fromUser!=admin.id){
				connInit(data.data.toUser);
				var html="";
				var msgType=data.data.msgType;
				var adminD=getAdminInfoById(data.data.fromUser);
				if (msgType == "text") {
					html = getTextHtml(data.data.id,data.data.msgContent,adminD.userId);
				} else if (msgType == "image") {
					html=getImageHtml(data.data.id,data.data.imageUrl,adminD.fromUser);
				} else if (msgType == "voice") {
					html=getVoiceHtml(data.data.id,data.data.voiceUrl,adminD.fromUser);
				}
				$("#console" + data.data.toUser).append(html);
				$("#msgbox").scrollTop(9999999);
			}else{
				var userData = getUserData(data.FromUserName);
				var html = "";
				if ("autotext" == data.MsgType) {
					if (data.Content == "用户已离开") {
						var index = userData.index;
						leaveFlag(index);
					}else if(data.Content == "发送失败"){
						$("input[name=msgId]").each(function(){
							if($(this).val()==data.MsgId){
								$(this).parent().parent().append("<img onclick=sendAgain("+$(this).val()+") style='cursor:hand;float:right;' title='发送失败' src='/image/send_again.png'>");
							}
						});
					}else{
						html = getAutotextHtml(data.MsgId, data.Content);
						$("#console" + data.FromUserName).append(html);
						$("#msgbox").scrollTop(9999999);
					}
					return;
				}
				html += '<div class="list-left">';
				html += '<table style="padding:0px;margin:0px;">';
				html += '<tr>';
				html += '<td rowspan="2" width="50px" valign="top"><img style="height:52px;" src="'+userData.headimgurl+'"/></td>';
				html += '<td style="width:100%;">';
				html += '<span style="float:left;margin-left:15px;"><font size="2">'
						+ userData.nickname
						+ '&nbsp;&nbsp;'
						+ new Date().Format("hh:mm:ss") + '</font></span>';
				html += '</td>';
				html += '</tr>';
				html += '<tr>';
				html += '<td style="width:100%;">';
				html += '<span style="float:left;margin-top:10px;"><img src="/image/img_left.jpg"></img></span>';
				if ("text" == data.MsgType) {
					html += '<div class="bj" onmousedown="workContent(this,1,1)"><input type="hidden" name="msgId" value="'+data.MsgId+'"/>';
					html += '<span class="msgtext"><font size="2">'
							+ replaceQQFace(data.Content)
							+ '</font></span><input type="hidden" name="workId" value=""/>';
				} else if ("image" == data.MsgType) {
					html += '<div class="bjimg" onmousedown="workContent(this,2,1)"><input type="hidden" name="msgId" value="'+data.MsgId+'"/><input type="hidden" name="workId" value=""/>';
					html += "<img onclick='imgToBig(this);' src=" + data.MediaUrl
							+ " width='150px'></img>";
				} else if ("voice" == data.MsgType) {
					html += '<div class="bj" onmousedown="workContent(this,3,1)"><input type="hidden" name="msgId" value="'+data.MsgId+'"/><input type="hidden" name="workId" value=""/>';
					html += "<embed src=" + data.MediaUrl + " windowlessVideo=1 autostart=false hidden=no units='pixels' width=300 height=44></embed>";
					//html += $("#media-html").html();
				} else if ("shortvideo" == data.MsgType || "video" == data.MsgType) {
					html += '<div class="bj" onmousedown="workContent(this,4,1)"><input type="hidden" name="msgId" value="'+data.MsgId+'"/><input type="hidden" name="workId" value=""/>';
					//html += "<embed src=" + data.MediaUrl + " windowlessVideo=1 autoplay=false hidden=no units='pixels' width=300 height=240></embed>";
					html += '<div id="ck'+data.MsgId+'"></div>';
					//html += $("#media-html").html();
				} else if("location" == data.MsgType){
					var content = data.Label+"(" + data.Location_X +","+ data.Location_Y+")";
					html += '<div class="bj" onmousedown="workContent(this,1,1)"><input type="hidden" name="msgId" value="'+data.MsgId+'"/>';
					html += "<a target='_blank' href='http://10.224.9.116:8180/jtbst/main.html?loader=gongdan&lon="+data.Location_Y+"&lat="+data.Location_X+"&type=&desc="+data.Label+"&createTime='><span class='msgtext'><font size='2'>"+replaceQQFace(content)+"</font></span></a><input type='hidden' name='workId' value=''/>"
				}
				html += '</div>';
				html += '</td>';
				html += '</tr>';
				html += '</table>';
				html += '</div>';
				$("#console" + data.FromUserName).append(html);
				$("#msgbox").scrollTop(9999999);
				if ("shortvideo" == data.MsgType || "video" == data.MsgType) {
					playMedia("ck" + data.MsgId, data.MediaUrl);
				}
				var _wait = $("#wait" + data.FromUserName).children();
				var _num = parseInt($(_wait[2]).html());
				$(_wait[1]).attr("src",
						"/image/face_" + getImgSrcByMsg(_num + 1) + ".png");
				$(_wait[2]).html(_num + 1);
				playMsgVoice();
				//saveOrUpdateServiceId(data.MsgId,userData.serviceId);
			}
		};
		ws.onclose = function(event) {
		};
	}
	function sendAgain(obj){
		$.messager.alert("提示信息", "此功能暂未推出！", "info");
	}
	/*根据时间改变显示的图片*/
	function getImgSrcByMsg(time) {
		if (parseInt(time / 10) > 3) {
			return 4;
		} else {
			return parseInt(time / 10) + 1;
		}
	}
	/*保存聊天信息入库*/
	function saveMessage(fromUser, toUser, msgType, content, index, mediaUrl) {
		if (!userData[index].status) {
			$.messager.alert("提示信息", "此微信用户已断开连接！", "warning");
			return;
		}
		if (userData[index].transferFlag) {
			$.messager.alert("提示信息", "此微信用户正在转交中！", "warning");
			return;
		}
		var msgContent = "";
		var imgUrl = "";
		var voiceUrl = "";
		var videoUrl = "";
		if (msgType == "text" || msgType == "autotext") {
			msgContent = content;
		} else if (msgType == "image") {
			imgUrl = content;
		} else if (msgType == "voice") {
			voiceUrl = content;
		}
		$
				.ajax({
					type : "post",
					url : "/csc/saveMsgFromCS",
					dataType : "json",
					async: true,
					data : {
						fromUser : fromUser,
						toUser : toUser,
						msgType : msgType,
						msgContent : msgContent,
						imageUrl : imgUrl,
						voiceUrl : voiceUrl,
						videoUrl : videoUrl,
						workServiceId:userData[index].serviceId
					},
					success : function(data) {
						if (data.id == 0) {
							$.messager.alert("提示消息", "此微信用户已断开连接！", "warning");
		    				leaveFlag(index);
						} else {
							if (msgType == "text" || msgType == "autotext") {
								$("#connContent>div")
										.each(
												function() {
													if ($(this).css("display") == "block") {
														var html = "";
														if (msgType == "text") {
															html = getTextHtml(
																	data.id,
																	content,admin.userId);
														} else if (msgType == "autotext") {
															html = getAutotextHtml(
																	data.id,
																	content);
														}
														$(this).append(html);
														$("#msgbox").scrollTop(
																9999999);
													}
												});
								//清除输入文本
								$('#msg' + nowOpenid).val("");
								//清除座席未读用户发送信息条数和等待时间
								clearMsg();
								//发送信息给微信用户
								var text = '{"touser":"'
										+ userData[index].openid
										+ '","id":"'+data.id+'","msgtype":"text","text":{"content":"'
										+ msgContent + '"}}';
								ws[index].send(text);
							} else if (msgType == "image") {
								$("#connContent>div")
										.each(
												function() {
													if ($(this).css("display") == "block") {
														var html=getImageHtml(data.id, content,admin.userId);
														$(this).append(html);
														$("#msgbox").scrollTop(
																9999999);
													}
												});
								//清除座席未读用户发送信息条数和等待时间
								clearMsg();
								//发送信息给微信用户
								var image = '{"touser":"'
										+ userData[index].openid
										+ '","id":"'+data.id+'","msgtype":"image","image":{"media_id":"'
										+ mediaUrl + '"}}';
								ws[index].send(image);
							} else if (msgType == "voice") {
								$("#connContent>div")
										.each(
												function() {
													if ($(this).css("display") == "block") {
														var html=getVoiceHtml(data.id, content,admin.userId);
														$(this).append(html);
														$("#msgbox").scrollTop(
																9999999);
													}
												});
								//清除座席未读用户发送信息条数和等待时间
								clearMsg();
								//发送信息给微信用户
								var voice = '{"touser":"'
										+ userData[index].openid
										+ '","id":"'+data.id+'","msgtype":"voice","voice":{"media_id":"'
										+ mediaUrl + '"}}';
								ws[index].send(voice);
							}
						}
					}
				});
	}
	/*发送文本信息*/
	function sendText() {
		var index = getUserDataIndex();
		if(index==10){
			sendMsgText();
		}
		else if (ws[index] != null) {
			if (nowOpenid == "") {
				$.messager.alert("发送消息", "请选择微信用户后发送！", "warning");
				return;
			}
			var message = $.trim($("#msg" + nowOpenid).val());
			message = removeHtmlTab(message);
			if (message == "") {
				$.messager.alert("发送消息", "发送的信息不能为空！", "warning");
				return;
			}
			saveMessage(admin.id, nowOpenid, "text", message, index, "");
		} else {
			$.messager.alert("提示消息", "请选择微信用户后操作！", "warning");
		}
	}
	/*发送图片信息*/
	function sendImg(src, message) {
		var index = getUserDataIndex();
		if (ws[index] != null) {
			if (nowOpenid == "") {
				$.messager.alert("发送消息", "请选择微信用户后发送！", "warning");
			} else {
				saveMessage(admin.id, nowOpenid, "image", "/upload/" + src,index, message);
			}
		} else {
			$.messager.alert("提示消息", "请选择微信用户后操作！", "warning");
		}
	}
	/*发送音频信息*/
	function sendVoice(mediaId, mediaUrl) {
		var index = getUserDataIndex();
		if (ws[index] != null) {
			var msgId = saveMessage(admin.id, nowOpenid, "voice", mediaUrl,
					index, mediaId);
		} else {
			$.messager.alert("提示消息", "请选择微信用户后操作！", "warning");
		}
	}
	/*清除消息条数与图片*/
	function clearMsg() {
		var _wait = $("#wait" + nowOpenid).children();
		$(_wait[0]).val(0);
		$(_wait[1]).attr("src", "/image/face_1.png");
		$(_wait[2]).html(0);
	}
	/*开始录音*/
	function recordInit() {
		if (nowOpenid == "") {
			$.messager.alert("录音提示", "请选择微信用户后录音！", "warning");
			return false;
		}
		$("#uploadForm").find("input[name='openid']").val(nowOpenid);
		openWindow("record-window", 360, 250);
	}
	// 论坛
	function newbbs(){
	/* 	if (nowOpenid == "") {
			$.messager.alert("论坛提示", "请选择微信用户后录音！", "warning");
			return false;
		} */
		fullExpertListHtml(getExpertInfo());
		openWindow("bbs-window", 700, 450);
	}
	
	/*发短信给用户*/
	function openCallPolice(){
		/* 	if (nowOpenid == "") {
		$.messager.alert("论坛提示", "请选择微信用户后录音！", "warning");
		return false;
		} */
		
		openWindow("callPolice-window", 370, 180);
	}
	
	/*提交配置信息*/
	function toSubmit() {
		var customerNum = $("input[name=customerNum]").val();
		if (0 < customerNum && customerNum < 6) {
			$.ajax({
				type : "post",
				url : "/csc/updateCsWxNum/" + admin.id,
				dataType : "json",
				//async: false,
				data : {
					csId : admin.id,
					num : customerNum
				},
				success : function(data) {
					if (data.status == "OK") {
						admin.customerNum = customerNum;
						closeWindow("config-window");
						$.messager.alert("提示消息", "配置成功！", "info");
					}
				}
			});
		} else {
			$.messager.alert("提示消息", "请输入1到5的整数！", "info");
		}
	}
	/*查询历史工单*/
	function historyTab(openId) {
		$("#history").append("<div id='history"+openId+"'></div>");
		selectCtnuList(openId);
		selectHisList(1,10,openId);
	}
	function refreshHisTab(openId) {
		$("#history" + openId).html("");
		selectCtnuList(openId);
		selectHisList(1,10,openId);
	}
	function selectCtnuList(openId) {
		$.ajax({
			type : "post",
			url : "/csc/getWorkUnitSuspended",
			dataType : "json",
			async : false,
			data : {
				openId : openId
			},
			success : function(data) {
				if (data.length > 0) {
					for (var i = 0; i < data.length; i++) {
						html = getCntuListHtml(data[i]);
						if (i < data.length - 1)
							html += "<hr style='width:95%;'/>";
						$("#history" + openId).append(html);
					}
				} else {
				}
			}
		});
	}
	function selectHisList(_start, _end,openId) {
		$
				.ajax({
					type : "post",
					url : "/csc/getWorkUnitHis",
					dataType : "json",
					//async: false,
					data : {
						openId : openId,
						start : _start,
						end : _end
					},
					success : function(data) {
						if (data.length > 0) {
							var html = "<div style='text-align:center;margin:5px;'><font style='color:green;'>历史完单记录</font><br><img src='/image/hr.jpg' style='width:95%;height:1px;'/></div>";
							$("#history" + openId).append(html);
							for (var i = 0; i < data.length; i++) {
								html = getHisListHtml(data[i]);
								if (i < data.length - 1)
									html += "<hr style='width:95%;'/>";
								$("#history" + openId).append(html);
							}
						} else {
							var html = "<div style='text-align:center;margin:5px;'><font style='color:green;'>没有完单记录</font><br><img src='/image/hr.jpg' style='width:95%;height:1px;'/></div>";
							$("#history" + openId).append(html);
						}
					}
				});
	}
	function getHisListHtml(data) {
		var html = "";
		html += "<div class='imgDiv'>";
		html += "<table class='gridtable'>";
		html += "<tr>";
		html += "<th>来电时间</th>";
		html += "<td>" + undefinedToNull(data.CALLTIME) + "</td>";
		html += "<th>事件主题</th>";
		html += "<td>" + undefinedToNull(data.CALLREASON) + "</td>";
		html += "<th>处理人</th>";
		html += "<td>" + undefinedToNull(data.PERSONID) + "</td>";
		html += "</tr>";
		html += "<tr>";
		html += "<th>工单内容<br><img class='detimg' onclick=addHisWorkTab('"
				+ data.COMPUTERID
				+ "','"
				+ data.MYID
				+ "') title='查看详情' src='/image/detail.png'></img><img class='fujian' onclick=fujianWindow('"
				+ data.COMPUTERID
				+ "') title='查看附件' src='/image/fujian.png'></img></th>";
		html += "<td colspan=5>" + undefinedToNull(data.CONTENT) + "</td>";
		html += "</tr>";
		html += "</table>";
		html += "</div>";
		return html;
	}
	function undefinedToNull(obj) {
		if (typeof (obj) == "undefined" || obj == "undefined") {
			return "";
		} else {
			return obj;
		}
	}
	function getCntuListHtml(data) {
		var html = "";
		html += "<div class='imgDiv'></img>";
		html += "<table class='gridtable'>";
		html += "<tr>";
		html += "<th>来电时间</th>";
		html += "<td>" + undefinedToNull(data.CALLTIME) + "</td>";
		html += "<th>事件主题</th>";
		html += "<td>" + undefinedToNull(data.CALLREASON) + "</td>";
		html += "<th>处理人</th>";
		html += "<td>" + undefinedToNull(data.PERSONID) + "</td>";
		html += "</tr>";
		html += "<tr>";
		html += "<th>工单内容<br><img class='conimg' onclick=addCtnuWorkTab('"
				+ data.COMPUTERID
				+ "') title='继续编辑' src='/image/continue.png'><img class='fujian' onclick=fujianWindow('"
				+ data.COMPUTERID
				+ "') title='查看附件' src='/image/fujian.png'></img><img class='delete' onclick=deleteWorkTab(this,'"
				+ data.MYID
				+ "') title='删除工单' src='/image/delete.png'></img></th>";
		html += "<td colspan=5>" + undefinedToNull(data.CONTENT) + "</td>";
		html += "</tr>";
		html += "</table>";
		html += "</div>";
		return html;
	}
	function deleteWorkTab(obj,id){
		$.messager.confirm('删除提示', '你确定删除这工单吗?', function(r){
			if (r){
				$.ajax({
					type : "post",
					url : "/csc/deleteWorkTab",
					dataType : "json",
					data : {
						"userId":admin.userId,
						"id":id
					},
					success : function(data) {
						$(obj).parent().parent().parent().parent().parent().next().remove();
						$(obj).parent().parent().parent().parent().parent().remove();
						$.messager.alert("提示消息","删除成功！", "info");
			        }
				});
			}
		});
	}
	function fujianWindow(id) {
		var href = "attachment.jsp?workUnitId=" + id;
		window.open(href);
	}
	/*页面初始化*/
	$(document)
			.ready(
					function() {
						getQQface();
						//文件上传测试
						var uploader = new plupload.Uploader({ //实例化一个plupload上传对象
							runtimes : 'html5,flash,silverlight,html4',
							browse_button : 'browse',
							url : '/fileUpload/save',
							flash_swf_url : '/js/plupload/Moxie.swf',
							silverlight_xap_url : '/js/plupload/Moxie.xap',
						    filters: {
									  mime_types : [ //只允许上传图片
									    { title : "图片文件", extensions : "jpg,gif,png,jpeg,bmp,tiff,pcx,eps" }
									  ]
									}
						});
						uploader.init(); //初始化
						//绑定文件添加进队列事件
						uploader.bind('BeforeUpload', function (uploader, files) {  
			                uploader.settings.url = "/fileUpload/save?openid=" +nowOpenid;
         				});  
						uploader.bind('FilesAdded', function(uploader, files) {
							for (var i = 0, len = files.length; i < len; i++) {
								var file_name = files[i].name; //文件名
							}
							uploader.start();
						});
						//绑定文件上传完成事件
						uploader.bind('FileUploaded', function(uploader, file,
								responseObject) {
							var message = responseObject.response;
							var jsonObj = jQuery.parseJSON(message);
							var webData = jsonObj.webData;
							var wxData = jsonObj.wxData;
							message = (jQuery.parseJSON(wxData)).media_id;
							imgToScan(webData, message);
						});
						configureMicrophone();
						$('#tabs')
								.tabs(
										{
											onBeforeClose : function(title) {
												var tab = $('#tabs').tabs(
														'getSelected');
												var i = $('#tabs').tabs(
														'getTabIndex', tab);
												var index = getUserDataIndex();
												var workId = $(
														"iframe",
														$('#tabs').tabs(
																'getTab', i))
														.attr("id");
												if (typeof (workId) != "undefined"
														&& workAdmin[index][workId] != 1) {
													$('#tabs')
															.tabs('select', i);
													$.messager.alert("提示消息",
															"请保存此工单！", "info");
													return false;
												} else {
													return true;
												}
											}
										});
						admin["userName"] = "${entity.userName}";
						admin["userId"] = "${entity.userId}";
						admin["deptId"] = "${entity.deptId}";
						admin["id"] = "${entity.id}";
						admin["customerNum"] = "${entity.customerNum}";
						admin["isAdmin"] = "${entity.isAdmin}";
						admin["head"] = "/image/add-img.png";
						admin["type"] = "2";
						$("#adminName").html(admin.userName);
						$("input[name=customerNum]").val(admin.customerNum);
						initUser();
						initAdmin();
						initUserAdmin();
						initWorkAdmin();
						sysCheck();
						initDatagraid();
						if(admin.isAdmin==1){//管理员
							var o = $("#wadd-window").find("input[name=isSystem]");
							$.each(o, function() {
								$(this).attr("disabled",false);
							});
						}else{
							$("#mBtn_addGG").remove();
							$("#mBtn_addTitle").remove();
						}
						getNotice();
						csToCsInit();
					});
	var workAdmin = new Object();
	function initWorkAdmin() {
		for (var i = 0; i < sysNum; i++) {
			workAdmin[i] = new Object();
		}
	}
	function connCloseFlag() {
		var flag = 0;
		var index = getUserDataIndex();
		var tabs = $('#tabs').tabs('tabs');
		$.each(tabs, function(i, n) {
			var _temp = $('#tabs').tabs('getTab', i).panel('options');
			if (_temp.title == "工单记录" + (index + 1)) {
				var workId = $("iframe", $('#tabs').tabs('getTab', i)).attr("id");
				if (workAdmin[index][workId] != 1) {
					$('#tabs').tabs('select', i);
					flag++;
				} else {
					$('#tabs').tabs('close', i);
				}
			}
		});
		return flag;
	}
	function editUsersPhone(openid,phone){
		/* $.messager.confirm('电话号码同步提示', '你确定更新电话号码吗?', function(r){
			if (r){ */
				$.ajax({
					type : "post",
					url : "/csc/editUsersInfo",
					dataType : "json",
					//async: false,
					data : {
						"openid":openid,
						"phone":phone,
						"redBlack":""
					},
					success : function(data) {
						$.messager.alert('操作提示', '电话号码同步成功!', 'info');
						closeWindow('users-edit-window');
					}
				});
		/* 	}
		}); */
	}
	var _type="";
	//window.addEventListener('message', function(event){
	window.attachEvent('onmessage', function(event) {
		//alert(event.origin);
		if (event.origin == 'http://10.224.2.177:7001') {
			var lxmc=event.data.substring(0,4);
			if(lxmc=="sjlx"){
				_type=event.data.substring(5,9);
				openWindow('sjlx-'+_type+'-window',600,320);
			}else if(lxmc=="gdbc"){
				var strs=new Array();
				var workId=event.data.substring(5,event.data.length);
				strs=workId.split(",");
				var index=0;
				for (var i = 0; i < userData.length; i++) {
					if (strs[0].substring(0,strs[0].length-2) == userData[i].serviceId)
						index=i;
				}
				workAdmin[index][strs[0]] = 1;
				refreshHisTab(userData[index].openid);
				if(strs[1]!=""&&strs[1]!=userData[index].phone){
					editUsersPhone(userData[index].openid,strs[1]);
				}
			}
		}
	}, false);
	function playMedia(id, path) {
		var flashvars = {
			f : path,
			c : 0,
			b : 1
		};
		var params = {
			bgcolor : '#FFF',
			allowFullScreen : true,
			allowScriptAccess : 'always',
			wmode : 'transparent'
		};
		CKobject.embedSWF('/js/ckplayer/ckplayer.swf', id, 'ckplayer_a1',
				'300', '300', flashvars, params);
	}
	function playVoice() {
		$('#newMessageDIV').html('<embed src="/image/newmsg.mp3"/>');
	}
	function playMsgVoice() {
		if (voiceFlag)
			$('#newMessageDIV').html('<embed src="/image/new-msg.mp3"/>');
	}
	function showCloseBtn(obj) {
		var id = $(obj).find("input[name=userState]:first").val();
		if (id == nowOpenid) {
			$(obj).find(".delBtn:first").css("display", "block");
		}
	}
	function hideCloseBtn(obj) {
		$(obj).find(".delBtn:first").css("display", "none");
	}
	function getTextHtml(id,content,userId) {
		var html = "";
		html += '<div class="list-right">';
		html += '<table style="padding:0px;margin:0px;">';
		html += '<tr>';
		html += '<td style="width:100%;">';
		html += '<span style="float:right;margin-right:15px;"><font size="2">'
				+ new Date().Format("hh:mm:ss") + '&nbsp;&nbsp;座席'
				+ userId + '</font></span>';
		html += '</td>';
		html += '<td rowspan="2" width="50px" valign="top"><img src="/image/img_admin.jpg"/></td>';
		html += '</tr>';
		html += '<tr>';
		html += '<td style="width:100%;">';
		html += '<span style="float:right;margin-top:10px;"><img src="/image/img_right.jpg"></img></span>';
		html += '<div class="bj" onmousedown="workContent(this,1,2)"><input type="hidden" name="msgId" value="'+id+'"/><font size="2">'
				+ replaceQQFace(content);
				+ '</font><input type="hidden" name="workId" value=""/></div>';
		html += '</td>';
		html += '</tr>';
		html += '</table>';
		html += '</div>';
		return html;
	}
	function getAutotextHtml(id, content) {
		var html = "";
		html += '<div class="list-right">';
		html += '<table style="padding:0px;margin:0px;">';
		html += '<tr>';
		html += '<td style="width:100%;">';
		html += '<span style="float:right;margin-right:15px;"><font size="2">'
				+ new Date().Format("hh:mm:ss")
				+ '&nbsp;&nbsp;系统自动</font></span>';
		html += '</td>';
		html += '<td rowspan="2" width="50px" valign="top"><img src="/image/img_system.jpg"/></td>';
		html += '</tr>';
		html += '<tr>';
		html += '<td style="width:100%;">';
		html += '<span style="float:right;margin-top:10px;"><img src="/image/img_aright.jpg"></img></span>';
		html += '<div class="autobj" onmousedown="workContent(this,1,2)"><input type="hidden" name="msgId" value="'+id+'"/><font size="2">'
				+ content
				+ '</font><input type="hidden" name="workId" value=""/></div>';
		html += '</td>';
		html += '</tr>';
		html += '</table>';
		html += '</div>';
		return html;
	}
	function getImageHtml(id, content,userId){
		var html = "";
		html += '<div class="list-right">';
		html += '<table style="padding:0px;margin:0px;">';
		html += '<tr>';
		html += '<td style="width:100%;">';
		html += '<span style="float:right;margin-right:15px;"><font size="2">'
				+ new Date()
						.Format("hh:mm:ss")
				+ '&nbsp;&nbsp;座席'
				+ userId
				+ '</font></span>';
		html += '</td>';
		html += '<td rowspan="2" width="50px" valign="top"><img src="/image/img_admin.jpg"/></td>';
		html += '</tr>';
		//html += '<span class="time">13141455</span>';
		html += '<tr>';
		html += '<td style="width:100%;">';
		html += '<span style="float:right;margin-top:10px;"><img src="/image/img_right.jpg"></img></span>';
		html += '<div class="bjimg" onmousedown="workContent(this,2,2)"><input type="hidden" name="msgId" value="'+id+'"/><img onclick="imgToBig(this);" src="'
				+ content
				+ '" width="150px"></img><input type="hidden" name="workId" value=""/></div>';
		html += '</td>';
		html += '</tr>';
		html += '</table>';
		html += '</div>';
		return html;
	}
	function getVoiceHtml(id, content,userId){
		var html = "";
		html += '<div class="list-right">';
		html += '<table style="padding:0px;margin:0px;">';
		html += '<tr>';
		html += '<td style="width:100%;">';
		html += '<span style="float:right;margin-right:15px;"><font size="2">'
				+ new Date()
						.Format("hh:mm:ss")
				+ '&nbsp;&nbsp;座席'
				+ userId
				+ '</font></span>';
		html += '</td>';
		html += '<td rowspan="2" width="50px" valign="top"><img src="/image/img_admin.jpg"/></td>';
		html += '</tr>';
		//html += '<span class="time">13141455</span>';
		html += '<tr>';
		html += '<td style="width:100%;">';
		html += '<span style="float:right;margin-top:10px;"><img src="/image/img_right.jpg"></img></span>';
		html += '<div class="bj" onmousedown="workContent(this,3,2)"><input type="hidden" name="msgId" value="'+id+'"/>';
		html += '<audio src="'+content+'" controls="controls"></audio>'
		/* 标记 把原来的播放音频插件改为h5的audio标签  */
		/* html += '<embed src="'+content+'" windowlessVideo=1 autostart=false hidden=no units="pixels" width=300 height=44></embed>'; */
		html += '<input type="hidden" name="workId" value=""/></div>';
		html += '</td>';
		html += '</tr>';
		html += '</table>';
		html += '</div>';
		return html;
	}
	function  getLocationHtml(id,content,userId){
		var html = "";
		html += '<div class="list-right">';
		html += '<table style="padding:0px;margin:0px;">';
		html += '<tr>';
		html += '<td style="width:100%;">';
		html += '<span style="float:right;margin-right:15px;"><font size="2">'
				+ new Date().Format("hh:mm:ss") + '&nbsp;&nbsp;座席'
				+ userId + '</font></span>';
		html += '</td>';
		html += '<td rowspan="2" width="50px" valign="top"><img src="/image/img_admin.jpg"/></td>';
		html += '</tr>';
		html += '<tr>';
		html += '<td style="width:100%;">';
		html += '<span style="float:right;margin-top:10px;"><img src="/image/img_right.jpg"></img></span>';
		html += '<div class="bj" onmousedown="workContent(this,1,2)"><input type="hidden" name="msgId" value="'+id+'"/><font size="2">'
				+ replaceQQFace(content);
				+ '</font><input type="hidden" name="workId" value=""/></div>';
		html += '</td>';
		html += '</tr>';
		html += '</table>';
		html += '</div>';
		return html;
	}
	/* rgb 转换成 #XXX(HEX)格式  */
	function rgb2hex(rgb) {
		rgb = rgb.match(/^rgb\((\d+),\s*(\d+),\s*(\d+)\)$/); 
		function hex(x) {
		return ("0" + parseInt(x).toString(16)).slice(-2); 
		} 
		return "#" + hex(rgb[1]) + hex(rgb[2]) + hex(rgb[3]);
	}
	function isIE() { //ie?
		 if (!!window.ActiveXObject || "ActiveXObject" in window)
		  return true;
		  else
		  return false;
 	}
	
	//去掉html标签
	function removeHtmlTab(tab) {
		 return tab.replace(/<[^<>]+?>/g,'');//删除所有HTML标签
	}
	//普通字符转换成转意符
	function html2Escape(sHtml) {
		 return sHtml.replace(/[<>&"]/g,function(c){return {'<':'&lt;','>':'&gt;','&':'&amp;','"':'&quot;'}[c];});
	}
	//转意符换成普通字符
	function escape2Html(str) {
		 var arrEntities={'lt':'<','gt':'>','nbsp':' ','amp':'&','quot':'"'};
		 return str.replace(/&(lt|gt|nbsp|amp|quot);/ig,function(all,t){return arrEntities[t];});
	}
	//去除开头结尾换行,并将连续3次以上换行转换成2次换行
	function trimBr(str) {
		 str=str.replace(/((\s|&nbsp;)*\r?\n){3,}/g,"\r\n\r\n");//限制最多2次换行
		 str=str.replace(/^((\s|&nbsp;)*\r?\n)+/g,'');//清除开头换行
		 str=str.replace(/((\s|&nbsp;)*\r?\n)+$/g,'');//清除结尾换行
		 return str;
	}
</script>
</html>

