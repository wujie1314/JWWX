<%@page import="org.jiaowei.entity.RoadLxfdEntity"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html;charset=UTF-8"%>
<style>
.cell_lxfd {
	font-size: 12px;
	padding-left: 15px;
	margin: 0px;
}
</style>
<div class='weui_cells weui_cells_radio' style="overflow-y: auto;">
	<%
		List list = (List) request.getAttribute("list");
		for (int i = 0; i < list.size(); i++) {
			RoadLxfdEntity rEntity = (RoadLxfdEntity) list.get(i);
			out.println("<label class='weui_cell weui_check_label' for='LXFD"+rEntity.getRoadCode()+"'>");
			out.println("<input type='radio' class='cell_radio' VALUE='"+rEntity.getRoadCode()+"' name='lxfd' id='"+rEntity.getRoadCode()+"'>");
			out.println(rEntity.getRoadCode()+ rEntity.getRoadName() + rEntity.getLdName()
					+ "<br><span class='cell_lxfd'>（"+rEntity.getStartName()+"-"+rEntity.getEndName()+ "）</span>");
			out.println("</label>");
		}
	%>
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