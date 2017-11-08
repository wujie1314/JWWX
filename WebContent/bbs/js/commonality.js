function getURLName(name) {
		    var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
		    var r = window.location.search.substr(1).match(reg);
		    if (r != null) {
		        return unescape(r[2]);
		    }
		    return null;
}

var openID = getURLName("openID");
function goAddTell(){
	window.location.href = "/bbs/jsp/writeAbout.jsp?openID=" + openID;
}

function goMine(){
	window.location.href = "/bbs/jsp/mine.jsp?openID=" + openID;
}

function goExpert(){
	window.location.href = "/bbs/jsp/mine.jsp?openID=" + openID;
}

function goHighwayCondition(){
	window.location.href = "/bbs/jsp/mine.jsp?openID=" + openID;
}

function reply(id){
	window.location.href = "bbs/jsp/particulars.jsp?tellID=" + id + "&openID=" + openID;
}

function particulars(id){
	window.location.href = "bbs/jsp/particulars.jsp?tellID=" + id + "&openID=" + openID;
}