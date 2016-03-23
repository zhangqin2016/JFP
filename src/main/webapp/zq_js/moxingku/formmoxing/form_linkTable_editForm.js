$(function($){
	$("input[name='createForm']").click(function(){
		$.post(ctx+"/moxingku/form/linkTable/ui/createForm.zq",
{form_id:form_id,column:$(this).attr("column")  } ,function(data){
			alert(data);
			window.location.replace(window.location);
		});
	});
	
	$("#save").click(function(){ 
		$.post(ctx+"/moxingku/form/linkTable/ui/saveForm.zq",
{form_id:form_id,htmlmoban:CKEDITOR.instances.htmlmoban.getData()}
				,function(data){
			alert(data);
			window.location.replace(window.location);
		});
	});
	
});