function getURLName(name) {
		    var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
		    var r = window.location.search.substr(1).match(reg);
		    if (r != null) {
		        return unescape(r[2]);
		    }
		    return null;
}

var openID = getURLName("tellID");

$(function(){
	init();
})
function init(){
	alert(openID);
}