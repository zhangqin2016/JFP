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
	src="<%=request.getContextPath()%>/zq_js/renyuanjiegou/renyuan_useradd.js"></script>
<title></title>
</head>
<body>
	<script type="text/javascript">
var roleJson="${rolejson}";
var ctx="<%=request.getContextPath()%>";
var type="${type}";
	</script>
			<form name="zq_form"  >
    
    <div class="formbody">
    
    <div class="formtitle"><span>基本信息</span></div>
    
    <ul class="forminfo">
    <li><label>账号</label><input id="user_account"  type="text" class="dfinput" value="${user_account}" /><i> </i></li>
    <li><label>姓名</label><input  id="user_name" type="text" class="dfinput" value="${user_name}"/><i> </i></li>
     <li><label>角色</label> <div  id='role' class="vocation">
    <select   id='role_select'  class="dfinput">
  <option selected="selected" value='${role_id}'>${user_roleName}</option>
    </select>	 
    </div><i> </i></li>
     <li><label>部门管理者</label><cite><input name="dept_manager" type="radio" value="是" checked="checked" />是&nbsp;&nbsp;&nbsp;&nbsp;<input name="dept_manager" type="radio" value="否" />否</cite></li>
      <li><label>电话</label><input  id="user_tel" type="text" class="dfinput" value="${user_tel}"/><i> </i></li>
       <li><label>传真</label><input  id="user_fax" type="text" class="dfinput" value="${user_fax}"/><i> </i></li>
        <li><label>手机</label><input  id="user_mobile" type="text" class="dfinput" value="${user_mobile}"/><i> </i></li>
      <li><label>性别</label><cite><input name="user_sex" type="radio" value="男" checked="checked" />男&nbsp;&nbsp;&nbsp;&nbsp;<input name="user_sex" type="radio" value="女" />女</cite></li>
         <li><label>邮箱</label><input  id="user_mail" type="text" class="dfinput" value="${user_mail}"/><i> </i></li>
    <li><label>&nbsp;</label><input id='save' type="button" class="btn" value="确认保存"/>	<input type="hidden" id="id" value="${id }"/></li>
    </ul>
    </div>
	</form>
</body>
</html>