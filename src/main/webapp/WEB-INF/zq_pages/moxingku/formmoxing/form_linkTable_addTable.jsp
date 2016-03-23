<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath()%>/zq_css/form/form.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/zq_js/extjs/css/ext-theme-neptune-all.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/zq_css/zq_org/zq_org.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/zq_js/extjs/ext-all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/zq_js/extjs/ext-theme-neptune.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/zq_js/extjs/ext-lang-zh_CN.js"></script>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/zq_js/jquery/jquery-1.7.1.min.js"></script>
	
<script type="text/javascript" src="<%=request.getContextPath()%>/zq_js/moxingku/moxingku_public_tree.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/zq_js/moxingku/formmoxing/form_linkTable_addTable.js"></script>
<title></title>
</head>

<body >
	<script type="text/javascript">
var ctx="<%=request.getContextPath()%>";
var form_er_id="${form_er_id}";
var isMain="${isMain}";
	</script>
		<form name="zq_form"  >
    <div class="formbody" >
    <div class="formtitle"  ><span>绑定数据库</span></div>
    <ul class="forminfo">
    <li> <div id="tree"  ></div></li>
    <li> <input id='save' type="button" class="btn" value="确定"/>	<input type="hidden" id="form_id" value="${form_id }"/></li>
    </ul>
    </div>
	</form>
</body>
</html>