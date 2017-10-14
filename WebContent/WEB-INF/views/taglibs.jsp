<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css"
	href="/js/jquery/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="/js/jquery/themes/icon.css">
<link rel="stylesheet" type="text/css" href="/css/main.css">
<script type="text/javascript" src="/js/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/js/jquery/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/jquery/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
    function checkBlank(){
    	var bool=true;
    	$("input:text").each(function(){
    		if($(this).val()==""&&$(this).attr('title')!=""){
    			$.messager.alert('操作提示', "请输入"+$(this).attr('title')+"！",'warning');
    			bool=false;
    			return false;
    		}
    	});
    	return bool;
    }
</script>
