var qqface;
function getQQface(){
    $.ajax({
		type : "post",
		url : "/csc/replaceQQFace",
		dataType : "json",
		async: true,
		data : {
		},
		success : function(data) {
			qqface=data;
        }
	});
}
function replaceQQFace(content){
	var i=content.indexOf("/:");
	if(i!=-1){
		for(var i=0;i<qqface.length;i++){
			content=content.replace(qqface[i].showName,'<img src="/image/qq/'+qqface[i].id+'.png"></img>');
			if(content.indexOf(qqface[i].showName)!=-1)i--;
			else if(content.indexOf("/:")==-1)break;
		}
	}
	return content;
}