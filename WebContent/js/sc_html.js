/**
黄思洋
2014-5-9
*/
//设置点击不同头，显示不同的内容
 function setStyle(thisTitle)
 {
	 //得到后缀
	 var seqNum = thisTitle.id.substring(9);
     var num=thisTitle.id.substring(5,9);
	 //选择的标题设为主标题
	 thisTitle.className = "tip2";
	 //显示对应的div
	 document.getElementById("div"+num+seqNum).style.display = "block";
	 //循环设置其他的标题为次标题，其他的div不显示
	 for(var i = 1 ; i<12 ; i++)
	 {
		if(i!=seqNum){
			document.getElementById("title"+num+i).className="tip1";
			document.getElementById("div"+num+i).style.display = "none";
		}
	 }
 }
 //点击某个标签，关闭
 function winclose(idvalue,namevalue){
	ownerDialog.openerWindow.get_sjzt(idvalue,namevalue);
	ownerDialog.close();
 }
 //点击某个标签，关闭
 function winCloseSearch(aLabel){
	 ownerDialog.openerWindow.get_sjzt(aLabel.name,aLabel.innerText);
 }
 //输入查询条件时进行查询
 function startSelect(rowsSize){
	 var searchValue = document.getElementById("searchValue").value.toUpperCase();
	 //显不显示查询结果div判断
	 if(searchValue == ""){
	 	document.getElementById("body1").className="divShow";
	 	document.getElementById("body2").className="divHidden";
	 }else{
		document.getElementById("body1").className="divHidden";
	 	document.getElementById("body2").className="divShow";
	 }
	 //清空之前的查询结果
	 for(var i=0;i<rowsSize;i++){
		 document.getElementById("search"+i).innerText = "";
		 document.getElementById("search"+i).name = "";
	 }
	 //查询出对应值，并显示在search链接标签中
	 var j = 0;
	 for(var i=0;i<rowsSize;i++){
		 if(document.getElementById("text"+i).value.indexOf(searchValue)>=0){
			 document.getElementById("search"+j).innerText = document.getElementById("text"+i).value.substring(document.getElementById("text"+i).value.indexOf('|')+1,document.getElementById("text"+i).value.lastIndexOf('|'));
			 document.getElementById("search"+j).name = document.getElementById("text"+i).value.substring(0,document.getElementById("text"+i).value.indexOf('|'));
			 j=j+1;
		 }
	 }
 }
 
 var inputValue = '';
 
 //查询条件输入框得到焦点
function fouceDef(inputThis){
	if(inputValue==''){
		inputValue = inputThis.value;
	}
	if(inputThis.value==inputValue)
	{
		inputThis.value=''; 
	}
	inputThis.className = 'inputFouce';
}
 //查询条件输入框失去焦点
function blurDef(inputThis){
	if(inputThis.value=='')
	{
		inputThis.value=inputValue;
		inputThis.className = 'inputBlur';
	}
}
/**
*根据区域显示地点
*cqs 2014-07-07
*/
 function setStyle1(thisTitle)
 {
 	 
	 //得到后缀
	 var seqNum = thisTitle.id.substring(5);
	 //选择的标题设为主标题
	 thisTitle.className = "tip2";
	 //显示对应的div
	 document.getElementById("div"+seqNum).style.display = "block";
	 var divid = getDivNum();
	 var Adivid = getADivNum();
	 //循环设置其他的标题为次标题，其他的div不显示
	 for(var i = 1 ; i<divid ; i++)
	 {
	 		if(seqNum==1){
				document.getElementById("title1").className="tip1";
				document.getElementById("title2").className="tip3";
				document.getElementById("title3").className="tip3";
			}
			if(seqNum==2){
				document.getElementById("title1").className="tip1";
				document.getElementById("title2").className="tip2";
				document.getElementById("title3").className="tip3";
			}
			if(seqNum==3){
				document.getElementById("title1").className="tip1";
				document.getElementById("title2").className="tip1";
				document.getElementById("title3").className="tip2";
			}
		if(i!=seqNum){
			document.getElementById("div"+i).style.display = "none";
		}
	 }
	 for(var i = 1 ; i<=Adivid ; i++){
			document.getElementById("Adiv"+i).style.display = "none";
	 }
 }

 function setAddress(id,name)
 {
	 setStyle1(title2);
	 var tid = id-8;
	 var divid = getDivNum();
	 document.all("qy").value=tid;
	 document.getElementById("title2").innerHTML =name;
	 document.getElementById("div"+tid).style.display = "block";
	 for(var i = 1 ; i<divid ; i++){
		 if(i!=tid){
			document.getElementById("div"+i).style.display = "none";
		 }
	 }
 }
 
 function getAddress(id,name){
 	 setStyle1(title3);
	 //var tid = id.substring(1,3)-8;
	 document.all("dd").value=id;
	 document.getElementById("title3").innerHTML =name;
	 var divid = getDivNum();
	 var Adivid = getADivNum();
	 document.getElementById("Adiv"+id).style.display = "block";
	 for(var i = 1 ; i<divid ; i++){
	 	document.getElementById("div"+i).style.display = "none";
	 }
	 for(var i = 1 ; i<=Adivid ; i++){
		 if(i!=id){
			document.getElementById("Adiv"+i).style.display = "none";
		 }
	 }
 }
 
 function setStyle2(){
 	 setStyle1(title2);
 	 var id = document.all("qy").value;
 	 if(id==""){
 	 	HiddenDiv();
 	 	return;
 	 }
 	 var divid = getDivNum();
	 var Adivid = getADivNum();
	 document.getElementById("div"+id).style.display = "block";
	 for(var i = 1 ; i<divid ; i++){
		 if(i!=id){
			document.getElementById("div"+i).style.display = "none";
		 }
	 }
	 for(var i = 1 ; i<=Adivid ; i++){
			document.getElementById("Adiv"+i).style.display = "none";
	 }
 }
 
 function setStyle3(){
 	 setStyle1(title3);
	 var id = document.all("dd").value;
	 if(id==""){
	 	HiddenDiv();
 	 	return;
 	 }
	 var divid = getDivNum();
	 var Adivid = getADivNum();
	 document.getElementById("Adiv"+id).style.display = "block";
	 for(var i = 1 ; i<divid ; i++){
			document.getElementById("div"+i).style.display = "none";
	 }
	 for(var i = 1 ; i<=Adivid ; i++){
		 if(i!=id){
			document.getElementById("Adiv"+i).style.display = "none";
		 }
	 }
 }
 
 function HiddenDiv(){
 	var divid = getDivNum();
	var Adivid = getADivNum();
	for(var i = 1 ; i<divid ; i++){
		document.getElementById("div"+i).style.display = "none";
	 }
	for(var i = 1 ; i<=Adivid ; i++){
		document.getElementById("Adiv"+i).style.display = "none";
	 }
 }
 
 function getDivNum()
 {
 	var divid;
	var div = document.getElementsByTagName("div");        
 	for(var i=0;i<div.length;i++){
 		if(div[i].id.substring(0,3) == "div"){
 			divid=+i;
 		}
 	}
 	return divid;
 }
 
 function getADivNum()
 {
 	var Adivid=0;
	var Adiv = document.getElementsByTagName("div");        
 	for(var i=0;i<Adiv.length;i++){
 		if(Adiv[i].id.substring(0,4) == "Adiv"){
 			Adivid=Adivid+1;
 		}
 	}
 	return Adivid;
 }
 
 function closeAddress(idvalue,namevalue){
	ownerDialog.openerWindow.get_address(idvalue,namevalue);
	ownerDialog.close();
 }