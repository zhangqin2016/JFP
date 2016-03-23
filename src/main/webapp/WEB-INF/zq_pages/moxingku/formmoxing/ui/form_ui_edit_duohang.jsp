<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath()%>/zq_css/form/form.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/zq_js/jquery/jquery-1.7.1.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/chajian/bootstrap-3.3.0-dist/bootstrap3-validation.js"></script>

<title></title>
</head>
<body>
	<script type="text/javascript">
var ctx="<%=request.getContextPath()%>";
		$(function($) {
			$("#zq_form").validation();
			$("#save").click(function() {
				if ($("#zq_form").valid() == false) {
					alert('填写信息不完整。');
					return false;
				}
				$.post("/moxingku/form/ui/window/save.zq", {
					ui_length : $("#ui_length").val(),
					ui_html : $("#ui_html").val(),
					ui_type : "${ui_type}",
					id : '${id}'
				}, function(data) {
					alert(data);
					parent.store.reload();
				});
			});
		});
	</script>
<form name="zq_form"  >
    <div class="formbody">
    <ul class="forminfo">
    <li><label>宽度</label><input id="ui_length"  type="text" class="dfinput" style="width: 50%"  value="${ui_length}" /><i> </i></li>
    <li><label>样式</label><textarea class="textinput" rows="2" style="width: 50%" id='ui_html' ></textarea>   </li>
    <li><label>&nbsp;</label><input id='save' type="button" class="btn" value="确认保存"/> </li>
    </ul>
    </div>
</form>
</body>
</html>