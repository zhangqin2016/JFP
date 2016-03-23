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
	var form_er_id="${form_er_id}";
	var isMain="${isMain}";
	var form_id="${form_id}";
var ctx="<%=request.getContextPath()%>";
		$(function($) {
			$("#zq_form").validation();
			$("#save").click(function() {
				if ($("#zq_form").valid() == false) {
					return false;
				}
			/* 	var obj = new Object;
				obj.type = $("#type").val();
				var ui = JSON.stringify(obj); */
				$.post("/moxingku/form/linkTable/er/insertTable.zq", {
					table_name : $("#table_name").val(),
					table_title : $("#table_title").val(),
					isMain:isMain,
					form_id:form_id,
					form_er_id:form_er_id,
					type:1
				}, function(data) {
				    if(isMain==1){
				    	   parent.regreshNodeById("FB_"+form_er_id);
				     }else{
				    	 parent.regreshNodeById("root"); 
				     }
				    alert(data);
				});
			});
		});
	</script>
	<form name="zq_form" id="zq_form">
		<div class="formbody">
			<ul class="forminfo">
				<li><label>数据库表名</label><input id="table_name" type="text"
					class="dfinput" style="width: 50%" /><i> </i></li>
				<li><label>数据库标题</label><input id="table_title" type="text"
					class="dfinput" style="width: 50%" /><i> </i></li>
				<li>&nbsp;<input id='save' type="button" class="btn"
					value="确认保存" />
				</li>
			</ul>
		</div>
	</form>
</body>
</html>