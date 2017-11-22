function sendAlarmRescue(){
	
	var parm = {};
	parm.telephone =$('#alarmRescue_telephone').val();
	parm.orderId = $('#alarmRescue_orderId').val();
	
	$.ajax({  
        async:false,//是否异步  
        cache:false,//是否使用缓存  
        type: "POST",  
        data:parm,  
        dataType: "json",  
        timeout: 1000,  
        contentType : 'application/x-www-form-urlencoded; charset=utf-8',  
        url: "others/sendNote",  
        success: function(result){  
        	closeWindow("callPolice-window");
        	console.log("成功推送链接");
        },  
        error: function(result){  
        	console.log(result)       
        }  
    });
	
}