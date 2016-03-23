<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath()%>/zq_css/form/form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/zq_js/jquery/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/chajian/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/zq_js/moxingku/formmoxing/form_linkTable_editForm.js"></script>

<title></title>
</head>
<body style="height: 100%">
<script type="text/javascript">
var ctx="<%= request.getContextPath()%>";
var form_id="${form_id}";
$(function($){
	 $(".btn").height($(".btn").height()/1.5)
});
</script>
<form>		
 <div class="formbody">
    <ul class="forminfo">
     <li><input id='save' type="button" class="btn" value="保存表单模版"/>
      <input name='createForm' type="button" column="2" class="btn" value="生成2列表单"/>
      <input name='createForm' type="button" column="4" class="btn" value="生成4列表单"/>
       <input name='createForm' type="button" column="6" class="btn" value="生成6列表单"/>
      </li>
    <li><textarea name="htmlmoban" id="htmlmoban" >${htmlmoban}</textarea></li>
    </ul>
</div>		
  <script type="text/javascript">
   var formEditor=CKEDITOR.replace('htmlmoban', { height:$(window).height()-$(".formbody").height()-$(".btn").height()});
   </script>
</form>
</body>
</html>