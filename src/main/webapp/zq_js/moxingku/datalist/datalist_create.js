$(function($){
	if(dataListType=="数据列表"){
		$("#pro").remove();
	}
	$("#save").click(function(){
		var name=$("#name").val();
		var displayTitle=$("#displayTitle").val();
		var formId=$("#formId").val();
		var processId=$("#processId").val();
		if(name.trim().length==0){
			alert("列表名称不允许为空!");
			return;
		}else if(displayTitle.trim().length==0){
			alert("列表显示名称不允许为空!");
			return;
		}else if(formId.trim().length==0){
			alert("列表表单不允许为空!");
			return;
		}
		$.post(ctx+"/zq/dataList/createDataList.zq",{modelLibraryId:modelLibraryId,name:name,displayTitle:displayTitle,formId:formId,dataListType:dataListType,processId:processId},function(data){
			parent.regreshtab();
			if(dataListType=="流程列表"){
				parent.regreshNodeById("processlist_"+modelLibraryId);
				parent.closeTab("addprocesslist");
			}else{
				parent.regreshNodeById("datalist_"+modelLibraryId);
				parent.closeTab("adddatalist");
			}
		});
	});

});	

function bindForm(event){		
	if(Ext.getCmp("tree")){
		$("#tree").parent().show();
	}else{
		$("#tree").parent().show();
	rendererTree().init("tree",300,$("#formId").width(),"zq_form",function(node){
		if(node.data.id.indexOf("formlist_")==0){
			$("#formname").text(node.data.text);
			$("#formId").val(node.data.id.replace("formlist_",""));
			$("#tree").parent().hide();
		}
	});
	}
}