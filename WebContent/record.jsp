<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>交通出行服务网</title>
<link rel="stylesheet" type="text/css"
	href="/js/jquery/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="/js/jquery/themes/icon.css">
<link rel="stylesheet" type="text/css" href="/css/main.css">
<script type="text/javascript" src="/js/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/js/jquery/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/jquery/easyui-lang-zh_CN.js"></script>
<!--页面录音  开始-->
<link type="text/css" rel="stylesheet" href="/css/record-style.css" />
<script type="text/javascript" src="/js/record/swfobject-record.js"></script>
<script type="text/javascript" src="/js/record/recorder.js"></script>
<script type="text/javascript" src="/js/record/main.js"></script>
<!--页面录音  end-->
</head>
<body>
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
		<form id="uploadForm" name="uploadForm" action="/fileUpload/saveVoice">
			<input name="authenticity_token" value="xxxxx" type="hidden">
			<input name="upload_file[filename]" value="1" type="hidden">
			<input name="format" value="json" type="hidden">
		</form>
	</div>
	<!--页面录音  结束-->
</body>
<script type="text/javascript">
$(document).ready(function() {
	configureMicrophone();
});	
</script>
</html>