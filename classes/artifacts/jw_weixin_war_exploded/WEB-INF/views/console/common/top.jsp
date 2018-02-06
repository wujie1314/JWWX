<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style type="text/css">
.brand { font-family: georgia, serif; }
.brand .first {
    color: #ccc;
    font-style: italic;
}
.brand .second {
    color: #fff;
    font-weight: bold;
}
</style>

<div class="navbar">
	<div class="navbar-inner">
		<c:if test="${!empty user }">
		<ul class="nav pull-right">
			<ul class="nav pull-right">
				<li><a href="#" class="hidden-phone visible-tablet visible-desktop" role="button"><i class="icon-user"></i>&nbsp;&nbsp;欢迎您：&nbsp;&nbsp;${user.userName }</a></li>
				<li><a href="${ctx }/console/login.htm?method=logout" class="hidden-phone visible-tablet visible-desktop" role="button"><i class="icon-signout"></i>&nbsp;&nbsp;退出</a></li>
			</ul>
      	</ul>
      	</c:if>
   		<a class="brand" href="${ctx }/console/home.htm"><span class="first">重庆交委</span> <span class="second">微信后台管理系统</span></a>
    </div>
</div>