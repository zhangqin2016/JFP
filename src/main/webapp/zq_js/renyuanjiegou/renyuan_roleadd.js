$(function($){
	if ($("#role_group").val()!='请选择...') {
		$.getJSON(ctx + "/portal/org/role/gridGroupJson.zq", function(data) {
			$("#role_group").append("<option value='0'>请选择...</option>");
			$.each(data.root, function(i, item) {
				$("#role_group").append(
						"<option value='" + item.role_group + "'>" + item.role_group+"</option>");
			});
		});
	}else{
		var ps= $("#role_group").parent().parent();
		$("#role_group").parent().remove();
		ps.append("<input id=\"role_group\" type=\"text\" value=\"\" class=\"dfinput\"/>");
	}
	$("#save").bind('click',function(){
		if($("#role_group").val().trim().length==0||$("#role_group").val()=='请选择...'){
			alert("角色分组不允许为空!");
			return;
		}else if($("#role_name").val().trim().length==0){
			alert("角色名称不允许为空！");
			return;
		}else{
			$.post(ctx+"/portal/org/role/addRole.zq", 
					{ roleName: $("#role_name").val(), roleGroup: $("#role_group").val(),id:$("#id").val() },
					   function(data){
						alert(data);
					 parent.regreshNodeById('role');
					   });
			
		}
		
	});
	
})