<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath()%>/zq_css/form/form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/zq_js/jquery/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/zq_js/moxingku/formmoxing/form_linkTable_base.js"></script>
<title></title>
</head>
<body>
<script type="text/javascript">
var ctx="<%= request.getContextPath()%>";
var moxingkuid="${moxingkuid}";
</script>
	<form name="zq_form"  >
    <div class="formbody">
    <div class="formtitle"><span>基本信息</span></div>
    <ul class="forminfo">
    <li><label>表单名称</label><input id="form_name"  type="text" class="dfinput" value="${form_name}" /> </li>
     <li><label>模型库</label> <div   class="vocation">
    <select class="dfinput" id='moxingku'>
    </select>	 
    </div> </li>   <li><label>表单id</label><label>${form_id}</label></li>
    <li><label>&nbsp;</label><input id='save' type="button" class="btn" value="保存"/>	<input type="hidden" id="form_id" value="${form_id }"/></li>
    </ul>
    </div>
	</form>
</body>
</html>