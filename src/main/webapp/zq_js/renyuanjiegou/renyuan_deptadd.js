$(function($){
	$("#save").bind('click',function(){
		if($("#dept_name").val().trim().length==0){
			alert("部门名称不允许为空！");
			return;
		}else if($("#dept_code").val().trim().length==0){
			alert("部门代码不允许为空！");
			return;
		}else{
			$.post(ctx+"/portal/org/dept/addDept.zq", 
					{ deptCode: $("#dept_code").val(), deptName: $("#dept_name").val(),type:type,id:id},
					   function(data){
					     alert(data);
					    parent.regreshNodeById(id);
					   });
			
		}
		
	});
	
})