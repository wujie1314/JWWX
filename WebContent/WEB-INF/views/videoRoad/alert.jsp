<%@page language="java" contentType="text/html;charset=UTF-8"%>
<div class="load-bg" style="display: none"></div>
<div class="load-box" style="display: none">
    <img id="alertLoad" class="icon" src="${ctx}/image/road/loading.gif" alt=""/>
    <img id="alertSuccess" style="display: none" class="icon" src="${ctx}/image/road/03-01.png" alt=""/>
    <img id="alertFail" style="display: none" class="icon" src="${ctx}/image/road/04-01.png" alt=""/>
    <p>加载中...</p>
</div>
 
<script>

function showLoad(){
	$('.load-bg' ).show();
	$('.load-box').show();
	//setInterval(hideLoad, 1000);//自动隐藏
}
function hideLoad(){
	$('.load-bg' ).hide();
	$('.load-box').hide();
}
</script>