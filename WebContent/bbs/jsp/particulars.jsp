<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>帖子详情</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width" />
	
	<link rel="stylesheet" type="text/css" href="bbs/css/particulars.css">
    <link rel="stylesheet" type="text/css" href="bootstrap-3.3.5-dist/css/bootstrap.min.css">
    <script type="text/javascript" src="js/jquery/jquery-1.11.3.min.js"></script>
   
  </head>
  
  <body>
   		<div class="tell"> 
	   		<div class="tellMessage" id="tellMessage"></div>
	   		<div class="tellContent" id="tellContent"></div>
	   		<div class="tellPicture" id="tellPicture"></div>
	   		<div class="tellOperation" id="tellOperation"></div>
	   		<div class="tellComment" id="tellComment"></div>
	   		<div class="tellInput" id="tellInput">
	   			<div id="qq">
	<p>给出你的评论吧！</p>
	<div class="message" id="message" contentEditable='true'></div>

	<div class="But">
		<img src="bbs/images/bba_thumb.gif" class='bq'/>
		<span class='submit' onclick="review()">评论</span>
		<!--face begin-->
		<div class="face">
			<ul>
				<li><img src="bbs/images/zz2_thumb.gif" title="[织]" ></li>
				<li><img src="bbs/images/horse2_thumb.gif" title="神马]"></li>
				<li><img src="bbs/images/fuyun_thumb.gif" title="[浮云]"></li>
				<li><img src="bbs/images/geili_thumb.gif" title="[给力]"></li>
				<li><img src="bbs/images/wg_thumb.gif" title="[围观]"></li>
				<li><img src="bbs/images/vw_thumb.gif" title="[威武]"></li>
				<li><img src="bbs/images/panda_thumb.gif" title="[熊猫]"></li>
				<li><img src="bbs/images/rabbit_thumb.gif" title="兔子]"></li>
				<li><img src="bbs/images/otm_thumb.gif" title="[奥特曼]"></li>
				<li><img src="bbs/images/j_thumb.gif" title="[囧]"></li>
				<li><img src="bbs/images/hufen_thumb.gif" title="[互粉]"></li>
				<li><img src="bbs/images/liwu_thumb.gif" title="[礼物]"></li>
				<li><img src="bbs/images/smilea_thumb.gif" title="呵呵]"></li>
				<li><img src="bbs/images/tootha_thumb.gif" title="嘻嘻]"></li>
				<li><img src="bbs/images/laugh.gif" title="[哈哈]"></li>
				<li><img src="bbs/images/tza_thumb.gif" title="[可爱]"></li>
				<li><img src="bbs/images/kl_thumb.gif" title="[可怜]"></li>
				<li><img src="bbs/images/kbsa_thumb.gif" title="[挖鼻屎]"></li>
				<li><img src="bbs/images/cj_thumb.gif" title="[吃惊]"></li>
				<li><img src="bbs/images/shamea_thumb.gif" title="[害羞]"></li>
			    <li><img src="bbs/images/zy_thumb.gif" title="[挤眼]"></li>
				<li><img src="bbs/images/bz_thumb.gif" title="[闭嘴]"></li>
				<li><img src="bbs/images/bs2_thumb.gif" title="[鄙视]"></li>
				<li><img src="bbs/images/lovea_thumb.gif" title="[爱你]"></li>
				<li><img src="bbs/images/sada_thumb.gif" title="[泪]"></li>
				<li><img src="bbs/images/heia_thumb.gif" title="[偷笑]"></li>
				<li><img src="bbs/images/qq_thumb.gif" title="[亲亲]"></li>
				<li><img src="bbs/images/sb_thumb.gif" title="[生病]"></li>
				<li><img src="bbs/images/mb_thumb.gif" title="[太开心]"></li>
				<li><img src="bbs/images/ldln_thumb.gif" title="[懒得理你]"></li>
				<li><img src="bbs/images/yhh_thumb.gif" title="[右哼哼]"></li>
				<li><img src="bbs/images/zhh_thumb.gif" title="[左哼哼]"></li>
				<li><img src="bbs/images/x_thumb.gif" title="[嘘]"></li>
				<li><img src="bbs/images/cry.gif" title="[衰]"></li>
				<li><img src="bbs/images/wq_thumb.gif" title="[委屈]"></li>
				<li><img src="bbs/images/t_thumb.gif" title="[吐]"></li>
				<li><img src="bbs/images/k_thumb.gif" title="[打哈气]"></li>
				<li><img src="bbs/images/bba_thumb.gif" title="[抱抱]"></li>
				<li><img src="bbs/images/angrya_thumb.gif" title="[怒]"></li>
				<li><img src="bbs/images/yw_thumb.gif" title="[疑问]"></li>
				<li><img src="bbs/images/cza_thumb.gif" title="[馋嘴]"></li>
				<li><img src="bbs/images/88_thumb.gif" title="[拜拜]"></li>
				<!-- <li><img src="bbs/images/sk_thumb.gif" title="[思考]"></li>
				 <li><img src="images/sweata_thumb.gif" title="[汗]"></li>
				<li><img src="images/sleepya_thumb.gif" title="[困]"></li>
				<li><img src="images/sleepa_thumb.gif" title="[睡觉]"></li>
				<li><img src="images/money_thumb.gif" title="[钱]"></li>
				<li><img src="images/sw_thumb.gif" title="[失望]"></li>
				<li><img src="images/cool_thumb.gif" title="[酷]"></li>
				<li><img src="images/hsa_thumb.gif" title="[花心]"></li>
				<li><img src="images/hatea_thumb.gif" title="[哼]"></li>
				<li><img src="images/gza_thumb.gif" title="[鼓掌]"></li>
				<li><img src="images/dizzya_thumb.gif" title="[晕]"></li>
				<li><img src="images/bs_thumb.gif" title="[悲伤]"></li>
				<li><img src="images/crazya_thumb.gif" title="[抓狂]"></li>
				<li><img src="images/h_thumb.gif" title="[黑线]"></li>
				<li><img src="images/yx_thumb.gif" title="[阴险]"></li>
				<li><img src="images/nm_thumb.gif" title="[怒骂]"></li>
				<li><img src="images/hearta_thumb.gif" title="[心]"></li>
				<li><img src="images/unheart.gif" title="[伤心]"></li>
				<li><img src="images/pig.gif" title="[猪头]"></li>
				<li><img src="images/ok_thumb.gif" title="[ok]"></li>
				<li><img src="images/ye_thumb.gif" title="[耶]"></li>
				<li><img src="images/good_thumb.gif" title="[good]"></li>
				<li><img src="images/no_thumb.gif" title="[不要]"></li>
				<li><img src="images/z2_thumb.gif" title="[赞]"></li>
				<li><img src="images/come_thumb.gif" title="[来]"></li>
				<li><img src="images/sad_thumb.gif" title="[弱]"></li>
				<li><img src="images/lazu_thumb.gif" title="[蜡烛]"></li>
				<li><img src="images/clock_thumb.gif" title="[钟]"></li>
				<li><img src="images/cake.gif" title="[蛋糕]"></li>
				<li><img src="images/m_thumb.gif" title="[话筒]"></li>
				<li><img src="images/weijin_thumb.gif" title="[围脖]"></li>
				<li><img src="images/lxhzhuanfa_thumb.gif" title="[转发]"></li>
				<li><img src="images/lxhluguo_thumb.gif" title="[路过这儿]"></li>
				<li><img src="images/bofubianlian_thumb.gif" title="[bofu变脸]"></li>
				<li><img src="images/gbzkun_thumb.gif" title="[gbz困]"></li>
				<li><img src="images/boboshengmenqi_thumb.gif" title="[生闷气]"></li>
				<li><img src="images/chn_buyaoya_thumb.gif" title="[不要啊]"></li>
				<li><img src="images/daxiongleibenxiong_thumb.gif" title="[dx泪奔]"></li>
				<li><img src="images/cat_yunqizhong_thumb.gif" title="[运气中]"></li>
				<li><img src="images/youqian_thumb.gif" title="[有钱]"></li>
				<li><img src="images/cf_thumb.gif" title="[冲锋]"></li>
				<li><img src="images/camera_thumb.gif" title="[照相机]"></li>	 -->
			</ul>
		</div>
		<!--face end-->
			</div>
		</div>
	</div>
 </div>
  </body>
  <script type="text/javascript">
	//点击小图片，显示表情
	  $(".bq").click(function(e){
	  	$(".face").slideDown();//慢慢向下展开
	  	e.stopPropagation();   //阻止冒泡事件
	  });
	
	  //在桌面任意地方点击，他是关闭
	  $(document).click(function(){
	  	$(".face").slideUp();//慢慢向上收
	  });
	
	  //点击小图标时，添加功能
	  $(".face ul li").click(function(){
	  	var simg=$(this).find("img").clone();
	  	$(".message").append(simg);
	  });
	
/* 	  //点击发表按扭，发表内容
	  $("span.submit").click(function(){
	
	  	var txt=$(".message").html();
	  	if(txt==""){
	  		$('.message').focus();//自动获取焦点
	  		return;
	  	} */
	
	  $(".msgCon").prepend("<div class='msgBox'><dl><dt><img src='images/tx.jpg' width='50' height='50'/></dt><dd>神马都是浮云</dd></dl><div class='msgTxt'>"+txt+"</div></div>");
  </script>
	<script type="text/javascript" src="bbs/js/particulars.js"></script>
	<script type="text/javascript" src="bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
</html>
