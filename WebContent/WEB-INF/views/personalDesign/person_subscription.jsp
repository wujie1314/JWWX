<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="org.jiaowei.entity.RoadLxfdEntity"%>
<%@page import="java.util.List"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>私人定制</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta name="viewport" content="width=device-width, initial-scale=0.5, minimum-scale=0.3, maximum-scale=2.0, user-scalable=yes" />
<meta content="yes" name="apple-mobile-web-app-capable" />
<meta content="black" name="apple-mobile-web-app-status-bar-style" />
<meta content="telephone=no" name="format-detection" />

<link rel="stylesheet" type="text/css" href="personalDesign/css/wx_css/person_subscription.css"></link>

<link rel="stylesheet" type="text/css" href="personalDesign/css/bootstrap.css"></link>
<link rel="stylesheet" type="text/css" href="personalDesign/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="personalDesign/css/mobiscroll_date.css"></link>
<link rel="stylesheet" type="text/css" href="personalDesign/css/mobiscroll.css">
<link rel="stylesheet" href="personalDesign/css/normalize3.0.2.min.css" />
<link rel="stylesheet" href="personalDesign/css/style.css?v=7" />
<link rel="stylesheet" type="text/css" href="personalDesign/css/LCalendar.css">

<script src="personalDesign/js/jquery.min.js"></script> 
<script src="personalDesign/js/mobiscroll_date.js" charset="gb2312"></script>
<script src="personalDesign/js/mobiscroll.js"></script>
<script src="personalDesign/js/jquery-2.1.1.min.js"></script>
<script src="personalDesign/js/bootstrap.js"></script>
<script src="personalDesign/js/bootstrap.min.js"></script>
<script src="personalDesign/js/wx_js/person_subscription.js"></script>




</head>

<body>
	<div class="container">
		<div class="row clearfix">
			<div class="col-xs-1 col-md-1 col-lg-1 column">
				<img src="personalDesign/img/icon_subscriptiontype.png" />
			</div>
			<div class="col-xs-2 col-md-2 col-lg-2  column">
				<label>订阅类型</label>
			</div>
			<div class="col-xs-9 col-md-9 col-lg-9 column">
				<select class="btn-group">
					<option value="" style="display:none;">请选择订阅类型</option>
					<option onclick="checkSubsType(0);" name=subsType value="0">定时推送</option>
					<option onclick="checkSubsType(1);" name=subsType value="1">立即推送</option>
				</select>
			</div>
		</div>
		<div class="row clearfix">
			<div class="col-xs-1 col-md-1 col-lg-1 column">
				<img src="personalDesign/img/icon_date.png" />
			</div>
			<div class="col-xs-2 col-md-2 col-lg-2  column">
				<label>日期</label>
			</div>
			<div class="col-xs-9 col-md-9 col-lg-9 column">
				<input type="text" name="DATE" id="DATE" readonly class="input" placeholder="请选择日期" />
				<div class="clear h10"></div>
			</div>
		</div>
		<div class="row clearfix">
			<div class="col-xs-1 col-md-1 col-lg-1 column">
				<img src="personalDesign/img/icon_time.png" />
			</div>
			<div class="col-xs-2 col-md-2 col-lg-2 column">
				<label>时间</label>
			</div>
			<div class="col-xs-9 col-md-9 col-lg-9 column">
				<input type="text" name="TIME" id="TIME" readonly class="input" placeholder="请选择时间" />
				<div class="clear h10"></div>
			</div>
		</div>
	</div>

	<hr />

	<div class="container" id="container">
		<div class="row clearfix">
			<div class="col-xs-12 col-md-12 col=lg-12 column">
				<div class="tabbable" id="tabs-nav">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#panel-bus" id="bus"
							data-toggle="tab">公交信息</a></li>
						<li><a href="#panel-condition" data-toggle="tab">路况信息</a></li>
						<li><a href="#panel-highPeccancy" data-toggle="tab">高速违章</a></li>
					</ul>

					<div class="tab-content">
						<div class="tab-pane active" id="panel-bus">
							<div id="busInfo">
								<iframe
									src="http://web.chelaile.net.cn/wwd/index?src=webapp_weixin_chongqing"
									name="iframe"></iframe>
								<br>
							</div>
						</div>

						<div class="tab-pane" id="panel-condition">
							<div class="panel-group" id="panel-fold">
								<div class="panel panel-default">
									<div class="panel-heading">
										<label class="panel-title" data-toggle="collapse"
											data-parent="#panel-fold" href="#panel-element-condition">高速路况</label>
									</div>
									<div id="panel-element-condition" class="panel-collapse in">
										<div class="panel-body">

											<div class="row clearfix">
												<div class="col-xs-1 col-md-1 col-lg-1 column">
													<img src="personalDesign/img/icon_loadname.png" />
												</div>
												<div class="col-xs-2 col-md-2 col-lg-2  column">
													<label>线路名称</label>
												</div>
												<div class="col-xs-9 col-md-9 col-lg-9 column">
													<select class="btn-group" id="lxfd"
														onchange="showOtherChange(this.value);">
														<option value="" style="display:none;">请选择线路名称</option>
														<%
															List list = (List) request.getAttribute("list");
															for (int i = 0; i < list.size(); i++) {
																RoadLxfdEntity rEntity = (RoadLxfdEntity) list.get(i);
																out.println("<option data-icon='&spades;' value='"
																		+ rEntity.getRoadCode() + "'>" + rEntity.getRoadCode()
																		+ rEntity.getRoadName() + rEntity.getLdName()
																		+ "</option>");
															}
														%>
													</select>
												</div>
											</div>

											<div class="row clearfix">
												<div class="col-xs-1 col-md-1 col-lg-1 column">
													<img src="personalDesign/img/icon_begin.png" />
												</div>
												<div class="col-xs-2 col-md-2 col-lg-2  column">
													<label>开始路段</label>
												</div>
												<div class="col-xs-9 col-md-9 col-lg-9 column">
													<select class="btn-group" id="htlj_begin">
														<option value="" style="display:none;">请选择开始路段</option>

													</select>
												</div>
											</div>

											<div class="row clearfix">
												<div class="col-xs-1 col-md-1 col-lg-1 column">
													<img src="personalDesign/img/icon_end.png" />
												</div>
												<div class="col-xs-2 col-md-2 col-lg-2  column">
													<label>结束路段</label>
												</div>
												<div class="col-xs-9 col-md-9 col-lg-9 column">
													<select id="htlj_end" class="btn-group">
														<option value="" style="display:none;">请选择结束路段</option>

													</select>
												</div>
											</div>

											<div class="row clearfix">
												<div class="col-xs-1 col-md-1 col-lg-1 column">
													<img src="personalDesign/img/icon_loaddirection.png" />
												</div>
												<div class="col-xs-2 col-md-2 col-lg-2  column">
													<label>道路方向</label>
												</div>
												<div class="col-xs-9 col-md-9 col-lg-9 column">
													<select class="btn-group" id="dlfx">
														<option value="" style="display:none;">请选择道路方向</option>

													</select>
												</div>
											</div>
										</div>

									</div>
								</div>
								<div class="panel panel-default">
									<div class="panel-heading">
										<label class="panel-title collapsed" data-toggle="collapse"
											data-parent="#panel-fold" href="#panel-element-vodeoCapture">视频截图</label>
									</div>
									<div id="panel-element-vodeoCapture"
										class="panel-collapse collapse">
										<div class="panel-body">
											<div class="row clearfix">
												<div class="col-xs-1 col-md-1 col-lg-1 column">
													<img src="personalDesign/img/icon_screenshot.png" />
												</div>
												<div class="col-xs-2 col-md-2 col-lg-2  column">
													<label>截图来源</label>
												</div>
												<div class="col-xs-9 col-md-9 col-lg-9 column">
													<select class="btn-group">
														<option value="" style="display:none;">请选择截图来源</option>

													</select>
												</div>
											</div>

											<div class="row clearfix">
												<div class="col-xs-1 col-md-1 col-lg-1 column">
													<img src="personalDesign/img/icon_section.png" />
												</div>
												<div class="col-xs-2 col-md-2 col-lg-2  column">
													<label>路段</label>
												</div>
												<div class="col-xs-9 col-md-9 col-lg-9 column">
													<select class="btn-group">
														<option value="" style="display:none;">请选择路段</option>

													</select>
												</div>
											</div>

										</div>
									</div>
								</div>
							</div>
						</div>

						<div class="tab-pane" id="panel-highPeccancy">
							<div class="panel-heading">
								<label>车辆信息查询</label>
							</div>

							<div class="container">
								<div class="row clearfix">
									<div class="col-xs-3 col-md-3 col-lg-3 column">
										<label>车牌号</label>
									</div>
									<div class="col-xs-9 col-md-9 col-lg-9 column">
										<input type="text" placeholder="请输入车牌号" id="license">

									</div>
								</div>

								<div class="row clearfix">
									<div class="col-xs-3 col-md-3 col-lg-3  column">
										<label>牌照颜色</label>
									</div>
									<div class="col-xs-9 col-md-9 col-lg-9 column">
										<select class="btn-group">
											<option value="" style="display:none;">请选择线路名称</option>
											<option value="0">蓝色</option>
											<option value="1">黑色</option>
											<option value="2">白色</option>
											<option value="3">黄色</option>
										</select>
									</div>
								</div>

								<!-- <div class="row clearfix">

									<div class="col-xs-3 col-md-3 col-lg-3 column">
										<label>图片验证码</label>
									</div>
									<div class="col-xs-9 col-md-9 col-lg-9 column">
										<input type="text" id="verificationCode">
										 <img src="" id="randomCode"  class="codePicture">
									</div>
								</div> -->

							</div>
						</div>
					</div>


				</div>

			</div>
		</div>

	</div>
	<hr />

	<div id="footer">
		<input type="button" value="确定" onclick="addRoad();" id="confirm">
	</div>

	<div class="weui_dialog_alert" id="errorDialog" style="display: none;">
		<div class="weui_mask"></div>
		<div class="weui_dialog" id="weui_dialog">
			<div class="weui_dialog_hd">
				<strong class="weui_dialog_title">错误提示</strong>
			</div>
			<div class="weui_dialog_bd" id="errorMsg"></div>
			<div class="weui_dialog_ft">
				<a href="javascript:;" class="weui_btn_dialog primary"
					onclick="offErrorDialog()">确定</a>
			</div>
		</div>
	</div>
</body>
</html>
