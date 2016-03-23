$(function($){
	$("#save").bind('click',function(){
		if($("#coName").val().trim().length==0){
			alert("公司名称不允许为空！");
			return;
		}else if($("#coCode").val().trim().length==0){
			alert("公司代码不允许为空！");
			return;
		}else{
			$.post(ctx+"/portal/org/company/addCompany.zq", 
					{ coCode: $("#coCode").val(), coName: $("#coName").val(),coMemo:$("#coMemo").val() },
					   function(data){
					     alert(data);
					 	parent.regreshNodeById('org');					     
					   });
			
		}
		
	});
	
})