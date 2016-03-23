<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/chajian/bootstrap-3.3.0-dist/dist/css/bootstrap.min.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/zq_js/jquery/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/zq_js/moxingku/datalist/button/datalist_button_save.js"></script>
<title>首页</title>
</head>
<body style="padding-top: 70px;">
	<script type="text/javascript">
var ctx="<%=request.getContextPath()%>";
	</script>
   <div class="container-fluid" >
	<div class="row clearfix">
		<div class="col-md-1 column">
		</div>
				<div class="col-md-10 column">
				<form class="form-horizontal">
  <div class="form-group">
    <label  class="col-sm-2 control-label">按钮名称</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="title" value="${title}" >
    </div>
  </div>
  <div class="form-group">
    <label  class="col-sm-2 control-label">tooltip</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="tooltip" value="${tooltip}" >
    </div>
  </div>
   <div class="form-group">
   <label  class="col-sm-2 control-label">icon</label>
   <div class="col-sm-10">
      <input type="text" class="form-control" id="icon" value="${icon}" >
   </div>
  </div>
<input type="hidden" name="id" value="${id}" id="id">
<input type="hidden" name="buttonId" value="${buttonId}" id="buttonId">
<input type="hidden" name="dataListId" value="${dataListId}" id="dataListId">
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
	  <button type="button" id="save" class="btn btn-default" >保存</button>
    </div>
  </div>
</form>
	 </div>
	 	<div class="col-md-1 column">
		</div>
	 </div>
    </div>
</body>
</html>