$(function () {
    var $uploadStatus = $('#upload_status');
    //$showLevelButton = $('.show_level'),
    //$hideLevelButton = $('.hide_level'),
    var $level = $('.control_panel .level');

    var CLASS_CONTROLS = "control_panel";
    var CLASS_RECORDING = "recording";
    var CLASS_PLAYBACK_READY = "playback_ready";
    var CLASS_PLAYING = "playing";
    var CLASS_PLAYBACK_PAUSED = "playback_paused";

//  Embedding flash object ---------------------------------------------------------------------------------------------

    //setUpFormOptions();
    var appWidth = 24;
    var appHeight = 24;
    var flashvars = {'upload_image': '/image/upload.png'};
    var params = {};
    var attributes = {'id': "recorderApp", 'name': "recorderApp"};
    swfobject.embedSWF("/js/record/recorder.swf", "flashcontent", appWidth, appHeight, "11.0.0", "", flashvars, params, attributes);

//  Handling FWR events ------------------------------------------------------------------------------------------------

    window.fwr_event_handler = function fwr_event_handler() {
        $('#status').text("Last recorder event: " + arguments[0]);
        var name, $controls;

        switch (arguments[0]) {
            case "ready":
                var width = parseInt(arguments[1]);
                var height = parseInt(arguments[2]);
                FWRecorder.uploadFormId = "#uploadForm";
                FWRecorder.uploadFieldName = "upload_file[filename]";
                FWRecorder.connect("recorderApp", 0);
                FWRecorder.recorderOriginalWidth = width;
                FWRecorder.recorderOriginalHeight = height;
                $('.save_button').css({'width': width, 'height': height});
                break;

            case "no_microphone_found":
                break;

            case "microphone_user_request":
                recorderEl().addClass("floating");
                FWRecorder.showPermissionWindow();
                break;

            case "microphone_connected":
                FWRecorder.isReady = true;
                $uploadStatus.css({'color': '#000'});
                break;

            case "permission_panel_closed":
                FWRecorder.defaultSize();
                recorderEl().removeClass("floating");
                break;

            case "microphone_activity":
                $('#activity_level').text(arguments[1]);
                break;

            case "recording":
                name = arguments[1];
                $controls = controlsEl(name);
                FWRecorder.hide();
                setControlsClass($controls, CLASS_RECORDING);
                break;

            case "recording_stopped":
                name = arguments[1];
                $controls = controlsEl(name);
                var duration = arguments[2];
                FWRecorder.show();
                setControlsClass($controls, CLASS_PLAYBACK_READY);
                $('#duration').text(duration.toFixed(4) + " seconds");
                break;

            case "microphone_level":
                $level.css({width: arguments[1] * 50 + '%'});
                break;

            case "observing_level":
                //$showLevelButton.hide();
                //$hideLevelButton.show();
                break;

            case "observing_level_stopped":
                $showLevelButton.show();
                $hideLevelButton.hide();
                $level.css({width: 0});
                break;

            case "playing":
                name = arguments[1];
                for (var i = 0; i < arguments.length; i++) {
                    alert(arguments[i])
                }
                $controls = controlsEl(name);
                setControlsClass($controls, CLASS_PLAYING);
                break;

            case "playback_started":
                name = arguments[1];
                var latency = arguments[2];
                break;

            case "stopped":
                name = arguments[1];
                $controls = controlsEl(name);
                setControlsClass($controls, CLASS_PLAYBACK_READY);
                break;

            case "playing_paused":
                name = arguments[1];
                $controls = controlsEl(name);
                setControlsClass($controls, CLASS_PLAYBACK_PAUSED);
                break;

            case "save_pressed":
                FWRecorder.updateForm();
                break;

            case "saving":
                name = arguments[1];
                break;
            case "saved":
                name = arguments[1];
                //arguments[2] 是语音文件保存成功后返回的json格式的字符串
                //根据需要组织数据，发送到后台
                //alert(arguments[2]);
                var obj = jQuery.parseJSON(arguments[2]);
                var mediaId = obj.media_id;
                var mediaUrl=obj.media_url;
                //发送语音消息
    			closeWindow("record-window");
    			sendVoice(mediaId,mediaUrl);
                break;
            case "save_failed":
                name = arguments[1];
                var errorMessage = arguments[2];
                $uploadStatus.css({'color': '#F00'}).text(name + " failed: " + errorMessage);
                break;

            case "save_progress":
                name = arguments[1];
                var bytesLoaded = arguments[2];
                var bytesTotal = arguments[3];
                $uploadStatus.css({'color': '#000'}).text(name + " progress: " + bytesLoaded + " / " + bytesTotal);
                break;
        }
    };

//  Helper functions ---------------------------------------------------------------------------------------------------

    function setUpFormOptions() {
        var gain = $('#gain')[0];
        var silenceLevel = $('#silenceLevel')[0];
        for (var i = 0; i <= 100; i++) {
            gain.options[gain.options.length] = new Option(100 - i);
            silenceLevel.options[silenceLevel.options.length] = new Option(i);
        }
    }

    function setControlsClass($controls, className) {
        $controls.attr('class', CLASS_CONTROLS + ' ' + className);
    }

    function controlsEl(name) {
        return $('#recorder-' + name);
    }

    function recorderEl() {
        return $('#recorderApp');
    }


//  Button actions -----------------------------------------------------------------------------------------------------

    window.microphonePermission = function () {
        recorderEl().addClass("floating");
        FWRecorder.showPermissionWindow({permanent: true});
    };

    window.configureMicrophone = function () {
        if (!FWRecorder.isReady) {
            return;
        }
        FWRecorder.configure(44, 100, 0, 2000);
        FWRecorder.setUseEchoSuppression(false);
        FWRecorder.setLoopBack(false);
    };


});
