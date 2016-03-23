$(function($){
	$.getJSON(ctx + "/moxingku/main/getMoXingKuJson.zq", function(data) {
		$("#moxingku").append("<option value='0'>请选择...</option>");
		$.each(data, function(i, item) {
			if(moxingkuid== item.id){
				$("#moxingku").append( "<option selected value='" + item.id + "'>" + item.model_name+"</option>");
			}else{
				$("#moxingku").append( "<option value='" + item.id + "'>" + item.model_name+"</option>");
			}
					});
	});
	
	$("#save").bind("click",function(){
		$.post(ctx+"/moxingku/form/updateFormBase.zq", 
				{ form_id: $("#form_id").val(),
			form_name: $("#form_name").val(),
			moxingku:$("#moxingku").val() },
				   function(data){
				     alert(data);
				     parent.parent.regreshNodeById('form_'+moxingkuid);
				   });
				
	});
})