<%@page language="java" contentType="text/html;charset=UTF-8"%>
<style>
.cell_rep {
	padding: 5px 15px;
	font-size: 15px;
}
</style>
<div class="weui_cells weui_cells_checkbox">
	<label class="weui_cell weui_check_label cell_rep" for="s0">
		<div class="weui_cell_hd">
			<input type="checkbox" class="weui_check" name="checkbox0" id="s0"
				onclick="selectAll()"> <i class="weui_icon_checked"></i>
		</div>
		<div class="weui_cell_bd weui_cell_primary">
			<p>每天</p>
		</div>
	</label> <label class="weui_cell weui_check_label cell_rep" for="w0">
		<div class="weui_cell_hd">
			<input type="checkbox" name="checkbox1" class="weui_check" id="w0"
				value="0"> <i class="weui_icon_checked"></i>
		</div>
		<div class="weui_cell_bd weui_cell_primary">
			<p>周日</p>
		</div>
	</label> <label class="weui_cell weui_check_label cell_rep" for="w1">
		<div class="weui_cell_hd">
			<input type="checkbox" class="weui_check" name="checkbox1" id="w1"
				value="1"> <i class="weui_icon_checked"></i>
		</div>
		<div class="weui_cell_bd weui_cell_primary">
			<p>周一</p>
		</div>
	</label> <label class="weui_cell weui_check_label cell_rep" for="w2">
		<div class="weui_cell_hd">
			<input type="checkbox" name="checkbox1" class="weui_check" id="w2"
				value="2"> <i class="weui_icon_checked"></i>
		</div>
		<div class="weui_cell_bd weui_cell_primary">
			<p>周二</p>
		</div>
	</label> <label class="weui_cell weui_check_label cell_rep" for="w3">
		<div class="weui_cell_hd">
			<input type="checkbox" class="weui_check" name="checkbox1" id="w3"
				value="3"> <i class="weui_icon_checked"></i>
		</div>
		<div class="weui_cell_bd weui_cell_primary">
			<p>周三</p>
		</div>
	</label> <label class="weui_cell weui_check_label cell_rep" for="w4">
		<div class="weui_cell_hd">
			<input type="checkbox" name="checkbox1" class="weui_check" id="w4"
				value="4"> <i class="weui_icon_checked"></i>
		</div>
		<div class="weui_cell_bd weui_cell_primary">
			<p>周四</p>
		</div>
	</label> <label class="weui_cell weui_check_label cell_rep" for="w5">
		<div class="weui_cell_hd">
			<input type="checkbox" class="weui_check" name="checkbox1" id="w5"
				value="5"> <i class="weui_icon_checked"></i>
		</div>
		<div class="weui_cell_bd weui_cell_primary">
			<p>每五</p>
		</div>
	</label> <label class="weui_cell weui_check_label cell_rep" for="w6">
		<div class="weui_cell_hd">
			<input type="checkbox" name="checkbox1" class="weui_check" id="w6"
				value="6"> <i class="weui_icon_checked"></i>
		</div>
		<div class="weui_cell_bd weui_cell_primary">
			<p>周六</p>
		</div>
	</label>
</div>
<script type="text/javascript">
function selectAll(){
	if($("#s0").is(':checked')){
		$("input[name='checkbox1']").each(function(){
			if(!$(this).is(':checked'))$(this).click();
		});
	} else {
		$("input[name='checkbox1']").each(function(){
			if($(this).is(':checked'))$(this).click();
		});
		//$("input[name='checkbox1']").attr("checked",false);
	}
}
</script>