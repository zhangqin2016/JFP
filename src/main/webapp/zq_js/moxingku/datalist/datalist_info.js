$(function($){
	if(dataListType=="数据列表"){
		$("#pro").remove();
	}

	$("#save").click(function(){
		var name=$("#name").val();
		var displayTitle=$("#displayTitle").val();
		var formId=$("#formId").val();
		var processId=$("#processId").val();
		var pageSize=$("#pageSize").val();
		var sqlFilter=$("#sqlFilter").val();
		var order_by=$("#order_by").val();
		var table_id=$("#table_id").val();
		if(name.trim().length==0){
			alert("列表名称不允许为空!");
			return;
		}else if(displayTitle.trim().length==0){
			alert("列表显示名称不允许为空!");
			return;
		}else if(formId.trim().length==0){
			alert("列表表单不允许为空!");
		}
		$.post(ctx+"/zq/dataList/saveDataListInfo.zq",{pageSize:pageSize,id:datalistid,table_id:table_id,sqlFilter:sqlFilter,order_by:order_by,modelLibraryId:modelLibraryId,name:name,displayTitle:displayTitle,formId:formId,dataListType:dataListType,processId:processId},function(data){
			alert(data);
		});
	});
});	
