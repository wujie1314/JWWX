<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input id='searchValue' type=text class=inputBlur
	onKeyup='startSelect(31);' value='可输入中文/拼音首字母/编号进行查询'
	onfocus='fouceDef(this)' onblur='blurDef(this)'>
<div id='body1' class='divShow'>
	<ul style='list-style: none;'>
		<li><div style='width: 900px; text-align: left'>
				<span class='tip2' id='title50001' onclick='setStyle(this);'>常用</span><span
					class='tip1' id='title50002' onclick='setStyle(this);'>咨询</span><span
					class='tip1' id='title50003' onclick='setStyle(this);'>投诉</span><span
					class='tip1' id='title50004' onclick='setStyle(this);'>建议</span><span
					class='tip1' id='title50005' onclick='setStyle(this);'>求助</span><span
					class='tip1' id='title50006' onclick='setStyle(this);'>报案</span><span
					class='tip1' id='title50007' onclick='setStyle(this);'>从业</span><span
					class='tip1' id='title50008' onclick='setStyle(this);'>业务</span><span
					class='tip1' id='title50009' onclick='setStyle(this);'>表扬</span><span
					class='tip1' id='title500010' onclick='setStyle(this);'>举报</span><span
					class='tip1' id='title500011' onclick='setStyle(this);'>其他</span>
			</div></li>
		<li><div id='div50001' class='divShow'>
				<table>
					<tr>
						<td><a id='1599' href="#"
							onclick="colseSjlxWindow('1599','从业人员来电-->其他');">&nbsp;从业人员来电-->其他&nbsp;</a></td>
						<td><a id='1701' href="#"
							onclick="colseSjlxWindow('1701','表扬-->人员->驾驶员');">&nbsp;表扬-->人员->驾驶员&nbsp;</a></td>
						<td><a id='1702' href="#"
							onclick="colseSjlxWindow('1702','表扬-->人员->执法人员');">&nbsp;表扬-->人员->执法人员&nbsp;</a></td>
					</tr>
					<tr>
						<td><a id='1703' href="#"
							onclick="colseSjlxWindow('1703','表扬-->人员->话务员');">&nbsp;表扬-->人员->话务员&nbsp;</a></td>
						<td><a id='1704' href="#"
							onclick="colseSjlxWindow('1704','表扬-->人员->其他');">&nbsp;表扬-->人员->其他&nbsp;</a></td>
						<td><a id='1799' href="#"
							onclick="colseSjlxWindow('1799','表扬-->其他');">&nbsp;表扬-->其他&nbsp;</a></td>
					</tr>
					<tr>
						<td><a id='1999' href="#"
							onclick="colseSjlxWindow('1999','其他-->其他');">&nbsp;其他-->其他&nbsp;</a></td>
				</table>
			</div>
			<div id='div50002' class='divHidden'>
				<table>
					<tr>
						<td><a id='1006' href="#"
							onclick="colseSjlxWindow('1006','咨询-->高速-->找补问题');">&nbsp;高速-->找补问题&nbsp;</a></td>
						<td><a id='1007' href="#"
							onclick="colseSjlxWindow('1007','咨询-->咨询电话');">&nbsp;咨询电话&nbsp;</a></td>
						<td><a id='1008' href="#"
							onclick="colseSjlxWindow('1008','咨询-->技术监督局12365');">&nbsp;技术监督局12365&nbsp;</a></td>
					</tr>
					<tr>
						<td><a id='1009' href="#"
							onclick="colseSjlxWindow('1009','咨询-->物价局12358');">&nbsp;物价局12358&nbsp;</a></td>
						<td><a id='1010' href="#"
							onclick="colseSjlxWindow('1010','咨询-->打错电话');">&nbsp;打错电话&nbsp;</a></td>
						<td><a id='1011' href="#"
							onclick="colseSjlxWindow('1011','咨询-->空话');">&nbsp;空话&nbsp;</a></td>
					</tr>
					<tr>
						<td><a id='1012' href="#"
							onclick="colseSjlxWindow('1012','咨询-->骚扰');">&nbsp;骚扰&nbsp;</a></td>
						<td><a id='1013' href="#"
							onclick="colseSjlxWindow('1013','咨询-->市政');">&nbsp;市政&nbsp;</a></td>
						<td><a id='1014' href="#"
							onclick="colseSjlxWindow('1014','咨询-->交警');">&nbsp;交警&nbsp;</a></td>
					</tr>
					<tr>
						<td><a id='1015' href="#"
							onclick="colseSjlxWindow('1015','咨询-->加气');">&nbsp;加气&nbsp;</a></td>
						<td><a id='1016' href="#"
							onclick="colseSjlxWindow('1016','咨询-->路桥收费');">&nbsp;路桥收费&nbsp;</a></td>
						<td><a id='1001' href="#"
							onclick="colseSjlxWindow('1001','咨询-->办事流程');">&nbsp;办事流程&nbsp;</a></td>
					</tr>
					<tr>
						<td><a id='1099' href="#"
							onclick="colseSjlxWindow('1099','咨询-->其他');">&nbsp;其他&nbsp;</a></td>
						<td><a id='1002' href="#"
							onclick="colseSjlxWindow('1002','咨询-->政策法规');">&nbsp;政策法规&nbsp;</a></td>
						<td><a id='1003' href="#"
							onclick="colseSjlxWindow('1003','咨询-->组织机构');">&nbsp;组织机构&nbsp;</a></td>
					</tr>
					<tr>
						<td><a id='1004' href="#"
							onclick="colseSjlxWindow('1004','咨询-->自驾游路线');">&nbsp;自驾游路线&nbsp;</a></td>
						<td><a id='1005' href="#"
							onclick="colseSjlxWindow('1005','咨询-->市内线路');">&nbsp;市内线路&nbsp;</a></td>
				</table>
			</div>
			<div id='div50003' class='divHidden'>
				<table>
					<tr>
						<td><a id='1101' href="#"
							onclick="colseSjlxWindow('1101','投诉-->人员行为');">&nbsp;人员行为&nbsp;</a></td>
						<td><a id='1102' href="#"
							onclick="colseSjlxWindow('1102','投诉-->行业管理');">&nbsp;行业管理&nbsp;</a></td>
						<td><a id='1103' href="#"
							onclick="colseSjlxWindow('1103','投诉-->人员态度');">&nbsp;人员态度&nbsp;</a></td>
					</tr>
					<tr>
						<td><a id='1104' href="#"
							onclick="colseSjlxWindow('1104','投诉-->业务技能');">&nbsp;业务技能&nbsp;</a></td>
						<td><a id='1199' href="#"
							onclick="colseSjlxWindow('1199','投诉-->其他');">&nbsp;其他&nbsp;</a></td>
				</table>
			</div>
			<div id='div50004' class='divHidden'>
				<table>
					<tr>
						<td><a id='1299' href="#"
							onclick="colseSjlxWindow('1299','建议-->其他');">&nbsp;其他&nbsp;</a></td>
				</table>
			</div>
			<div id='div50005' class='divHidden'>
				<table></table>
			</div>
			<div id='div50006' class='divHidden'>
				<table>
					<tr>
						<td><a id='1499' href="#"
							onclick="colseSjlxWindow('1499','报案-->其他');">&nbsp;其他&nbsp;</a></td>
				</table>
			</div>
			<div id='div50007' class='divHidden'>
				<table>
					<tr>
						<td><a id='1599' href="#"
							onclick="colseSjlxWindow('1599','从业人员来电-->其他');">&nbsp;其他&nbsp;</a></td>
				</table>
			</div>
			<div id='div50008' class='divHidden'>
				<table></table>
			</div>
			<div id='div50009' class='divHidden'>
				<table>
					<tr>
						<td><a id='1701' href="#"
							onclick="colseSjlxWindow('1701','表扬-->人员->驾驶员');">&nbsp;人员->驾驶员&nbsp;</a></td>
						<td><a id='1702' href="#"
							onclick="colseSjlxWindow('1702','表扬-->人员->执法人员');">&nbsp;人员->执法人员&nbsp;</a></td>
						<td><a id='1703' href="#"
							onclick="colseSjlxWindow('1703','表扬-->人员->话务员');">&nbsp;人员->话务员&nbsp;</a></td>
					</tr>
					<tr>
						<td><a id='1704' href="#"
							onclick="colseSjlxWindow('1704','表扬-->人员->其他');">&nbsp;人员->其他&nbsp;</a></td>
						<td><a id='1799' href="#"
							onclick="colseSjlxWindow('1799','表扬-->其他');">&nbsp;其他&nbsp;</a></td>
				</table>
			</div>
			<div id='div500010' class='divHidden'>
				<table></table>
			</div>
			<div id='div500011' class='divHidden'>
				<table>
					<tr>
						<td><a id='1999' href="#"
							onclick="colseSjlxWindow('1999','其他-->其他');">&nbsp;其他&nbsp;</a></td>
				</table>
			</div></li>
	</ul>
</div>
<div id='body2' class='divHidden'>
	&nbsp;&nbsp;
	<table>
		<tr>
			<td>&nbsp;<a id='search0' name='' href='#'
				onclick='colseSjlxWindow(this.name,this.innerText);'></a>&nbsp;
			</td>
			<td>&nbsp;<a id='search1' name='' href='#'
				onclick='colseSjlxWindow(this.name,this.innerText);'></a>&nbsp;
			</td>
			<td>&nbsp;<a id='search2' name='' href='#'
				onclick='colseSjlxWindow(this.name,this.innerText);'></a>&nbsp;
			</td>
		</tr>
		<tr>
			<td>&nbsp;<a id='search3' name='' href='#'
				onclick='colseSjlxWindow(this.name,this.innerText);'></a>&nbsp;
			</td>
			<td>&nbsp;<a id='search4' name='' href='#'
				onclick='colseSjlxWindow(this.name,this.innerText);'></a>&nbsp;
			</td>
			<td>&nbsp;<a id='search5' name='' href='#'
				onclick='colseSjlxWindow(this.name,this.innerText);'></a>&nbsp;
			</td>
		</tr>
		<tr>
			<td>&nbsp;<a id='search6' name='' href='#'
				onclick='colseSjlxWindow(this.name,this.innerText);'></a>&nbsp;
			</td>
			<td>&nbsp;<a id='search7' name='' href='#'
				onclick='colseSjlxWindow(this.name,this.innerText);'></a>&nbsp;
			</td>
			<td>&nbsp;<a id='search8' name='' href='#'
				onclick='colseSjlxWindow(this.name,this.innerText);'></a>&nbsp;
			</td>
		</tr>
		<tr>
			<td>&nbsp;<a id='search9' name='' href='#'
				onclick='colseSjlxWindow(this.name,this.innerText);'></a>&nbsp;
			</td>
			<td>&nbsp;<a id='search10' name='' href='#'
				onclick='colseSjlxWindow(this.name,this.innerText);'></a>&nbsp;
			</td>
			<td>&nbsp;<a id='search11' name='' href='#'
				onclick='colseSjlxWindow(this.name,this.innerText);'></a>&nbsp;
			</td>
		</tr>
		<tr>
			<td>&nbsp;<a id='search12' name='' href='#'
				onclick='colseSjlxWindow(this.name,this.innerText);'></a>&nbsp;
			</td>
			<td>&nbsp;<a id='search13' name='' href='#'
				onclick='colseSjlxWindow(this.name,this.innerText);'></a>&nbsp;
			</td>
			<td>&nbsp;<a id='search14' name='' href='#'
				onclick='colseSjlxWindow(this.name,this.innerText);'></a>&nbsp;
			</td>
		</tr>
		<tr>
			<td>&nbsp;<a id='search15' name='' href='#'
				onclick='colseSjlxWindow(this.name,this.innerText);'></a>&nbsp;
			</td>
			<td>&nbsp;<a id='search16' name='' href='#'
				onclick='colseSjlxWindow(this.name,this.innerText);'></a>&nbsp;
			</td>
			<td>&nbsp;<a id='search17' name='' href='#'
				onclick='colseSjlxWindow(this.name,this.innerText);'></a>&nbsp;
			</td>
		</tr>
		<tr>
			<td>&nbsp;<a id='search18' name='' href='#'
				onclick='colseSjlxWindow(this.name,this.innerText);'></a>&nbsp;
			</td>
			<td>&nbsp;<a id='search19' name='' href='#'
				onclick='colseSjlxWindow(this.name,this.innerText);'></a>&nbsp;
			</td>
			<td>&nbsp;<a id='search20' name='' href='#'
				onclick='colseSjlxWindow(this.name,this.innerText);'></a>&nbsp;
			</td>
		</tr>
		<tr>
			<td>&nbsp;<a id='search21' name='' href='#'
				onclick='colseSjlxWindow(this.name,this.innerText);'></a>&nbsp;
			</td>
			<td>&nbsp;<a id='search22' name='' href='#'
				onclick='colseSjlxWindow(this.name,this.innerText);'></a>&nbsp;
			</td>
			<td>&nbsp;<a id='search23' name='' href='#'
				onclick='colseSjlxWindow(this.name,this.innerText);'></a>&nbsp;
			</td>
		</tr>
		<tr>
			<td>&nbsp;<a id='search24' name='' href='#'
				onclick='colseSjlxWindow(this.name,this.innerText);'></a>&nbsp;
			</td>
			<td>&nbsp;<a id='search25' name='' href='#'
				onclick='colseSjlxWindow(this.name,this.innerText);'></a>&nbsp;
			</td>
			<td>&nbsp;<a id='search26' name='' href='#'
				onclick='colseSjlxWindow(this.name,this.innerText);'></a>&nbsp;
			</td>
		</tr>
		<tr>
			<td>&nbsp;<a id='search27' name='' href='#'
				onclick='colseSjlxWindow(this.name,this.innerText);'></a>&nbsp;
			</td>
			<td>&nbsp;<a id='search28' name='' href='#'
				onclick='colseSjlxWindow(this.name,this.innerText);'></a>&nbsp;
			</td>
			<td>&nbsp;<a id='search29' name='' href='#'
				onclick='colseSjlxWindow(this.name,this.innerText);'></a>&nbsp;
			</td>
		</tr>
		<tr>
			<td>&nbsp;<a id='search30' name='' href='#'
				onclick='colseSjlxWindow(this.name,this.innerText);'></a>&nbsp;
			</td>
	</table>
</div>
<div id='body3' class='divHidden'>
	<input type=text id='text0' value='1006|咨询-->高速-->找补问题|ZXGSZBWT'><input
		type=text id='text1' value='1007|咨询-->咨询电话|ZXZXDH'><input
		type=text id='text2' value='1008|咨询-->技术监督局12365|ZXJSJDJ12365'><input
		type=text id='text3' value='1009|咨询-->物价局12358|ZXWJJ12358'><input
		type=text id='text4' value='1010|咨询-->打错电话|ZXDCDH'><input
		type=text id='text5' value='1011|咨询-->空话|ZXKH'><input
		type=text id='text6' value='1012|咨询-->骚扰|ZXSR'><input
		type=text id='text7' value='1013|咨询-->市政|ZXSZ'><input
		type=text id='text8' value='1014|咨询-->交警|ZXJJ'><input
		type=text id='text9' value='1015|咨询-->加气|ZXJQ'><input
		type=text id='text10' value='1016|咨询-->路桥收费|ZXLQSF'><input
		type=text id='text11' value='1001|咨询-->办事流程|ZXBSLC'><input
		type=text id='text12' value='1099|咨询-->其他|ZXQT'><input
		type=text id='text13' value='1101|投诉-->人员行为|TSRYXW'><input
		type=text id='text14' value='1102|投诉-->行业管理|TSHYGL'><input
		type=text id='text15' value='1103|投诉-->人员态度|TSRYTD'><input
		type=text id='text16' value='1104|投诉-->业务技能|TSYWJN'><input
		type=text id='text17' value='1002|咨询-->政策法规|ZXZCFG'><input
		type=text id='text18' value='1003|咨询-->组织机构|ZXZZJG'><input
		type=text id='text19' value='1004|咨询-->自驾游路线|ZXZJYLX'><input
		type=text id='text20' value='1005|咨询-->市内线路|ZXSNXL'><input
		type=text id='text21' value='1199|投诉-->其他|TSQT'><input
		type=text id='text22' value='1299|建议-->其他|JYQT'><input
		type=text id='text23' value='1499|报案-->其他|BAQT'><input
		type=text id='text24' value='1599|从业人员来电-->其他|CYRYLDQT'><input
		type=text id='text25' value='1701|表扬-->人员->驾驶员|BYRYJSY'><input
		type=text id='text26' value='1702|表扬-->人员->执法人员|BYRYZFRY'><input
		type=text id='text27' value='1703|表扬-->人员->话务员|BYRYHWY'><input
		type=text id='text28' value='1704|表扬-->人员->其他|BYRYQT'><input
		type=text id='text29' value='1799|表扬-->其他|BYQT'><input
		type=text id='text30' value='1999|其他-->其他|QT'>
</div>
