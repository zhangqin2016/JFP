<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/zq_js/extjs/css/ext-theme-neptune-all.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/zq_js/extjs/ext-all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/zq_js/extjs/ext-theme-neptune.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/zq_js/extjs/ext-lang-zh_CN.js"></script>
<link href="<%=request.getContextPath()%>/zq_css/form/form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/zq_js/jquery/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/zq_js/moxingku/datalist/datalist_info.js"></script>
<title></title>
</head>
<body>
<script type="text/javascript">
var ctx="<%= request.getContextPath()%>";
var modelLibraryId="${modelLibraryId}";
var dataListType='${dataListType}';
var datalistid="${datalistid}";
</script>
	<form name="zq_form"  >
    <div class="formbody">
    <div class="formtitle"><span>基本信息</span></div>
    <ul class="forminfo">
    <li><label>列表名称</label><input id="name"  type="text" class="dfinput" value="${name}" /> </li>
      <li><label>列表标示</label><label>${datalistid}</label></li>
 
    <li><label>列表显示名称</label><input id="displayTitle"  type="text" class="dfinput" value="${displayTitle}" /> </li>
    
    <li><label>列表表单</label><label>${formId}</label> <input type="hidden" id="formId" value="${formId}"/></li>
    
    <li id='pro'><label>流程</label><input id="processId"  type="text" class="dfinput" value="${processId}" /> </li>
     <li><label>主键</label><input id="table_id"  class="dfinput" value="${table_id}"  /></li>
<%--    	<li><label>每页条数</label><input id="pageSize"  class="dfinput" value="${pageSize}"  /></li>
  	<li><label>排序条件</label><input id="order_by"  class="dfinput" value="${order_by}"  /></li> --%>
 
    <li><label>过滤条件</label><textarea id="sqlFilter"  class="dfinput" >${sqlFilter}</textarea> </li>
    
    
     <li><label>&nbsp;</label><input id='save' type="button" class="btn" value="保存"/></li>
   
    </ul>
    </div>
    
	</form>
</body>
</html>