<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath()%>/zq_css/form/form.css" rel="stylesheet" type="text/css" />

<script type="text/javascript"
	src="<%=request.getContextPath()%>/zq_js/jquery/jquery-1.7.1.min.js"></script>
	<script type="text/javascript"
	src="<%=request.getContextPath()%>/zq_js/renyuanjiegou/renyuan_deptadd.js"></script>
<title></title>
</head>
<body>
	<script type="text/javascript">
var ctx="<%=request.getContextPath()%>";
var type="${type}";
var id="${id}";
	</script>
		<form name="zq_form"  >
    <div class="formbody">
    <div class="formtitle"><span>基本信息</span></div>
    <ul class="forminfo">
    <li><label>部门名称</label><input id="dept_name"  type="text" class="dfinput" value="${dept_name}" /><i> </i></li>
    <li><label>部门代码</label><input  id="dept_code" type="text" class="dfinput" value="${dept_code}"/><i> </i></li>
    <li><label>&nbsp;</label><input id='save' type="button" class="btn" value="确认保存"/>	<input type="hidden" id="id" value="${id }"/></li>
    </ul>
    </div>
	</form>
</body>
</html>