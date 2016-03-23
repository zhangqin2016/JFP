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
	src="<%=request.getContextPath()%>/zq_js/renyuanjiegou/renyuan_companyadd.js"></script>
<title></title>
</head>
<body>
	<script type="text/javascript">
var ctx="<%=request.getContextPath()%>";
	</script>
	<form name="zq_form"  >
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">新增公司</a></li>
    </ul>
    </div>
    <div class="formbody">
    <div class="formtitle"><span>基本信息</span></div>
    <ul class="forminfo">
    <li><label>公司名称</label><input id="coName"  type="text" class="dfinput" value="${coName}" /><i>标题不能超过30个字符</i></li>
    <li><label>公司代码</label><input  id="coCode" type="text" class="dfinput" value="${coName}"/><i>多个关键字用,隔开</i></li>
   <li><label>公司描述</label><textarea id="coMemo" cols="" rows="" class="textinput">${coMemo}</textarea></li>
    <li><label>&nbsp;</label><input id='save' type="button" class="btn" value="确认保存"/>	<input type="hidden" id="id" value="${id }"/></li>
    </ul>
    </div>
	</form>
</body>
</html>