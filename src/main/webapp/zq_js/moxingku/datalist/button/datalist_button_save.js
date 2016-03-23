$(function($){
	$("#save").click(function(){
		$.post(ctx+"/zq/dataList/botton/save.zq",
				{
					title:$('#title').val(),
					tooltip:$('#tooltip').val(), 
					icon:$('#icon').val(),
					id:$('#id').val(),
					buttonId:$('#buttonId').val(),
					dataListId:$('#dataListId').val() 
				},function(data){
					parent.store.reload();
					parent.closeAddWindow();
				});
		
	})

});
