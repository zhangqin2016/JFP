$(function($){
	rendererTree().init("tree",300,"auto","zq_table",null);
	$("#save").click(function(){
		var selectTable=Ext.getCmp("tree").getSelectionModel().getSelection();
		if(selectTable.length==0||selectTable[0].get("id").indexOf('metadata_')==-1){
			alert("请选择一张数据库表!");
		}else {
		
		$.post(ctx+"/moxingku/form/linkTable/er/insertTable.zq", 
				{ table_id:selectTable[0].get("id").replace("metadata_",""), isMain:isMain,type:0,form_id:$("#form_id").val(),form_er_id:form_er_id },
				   function(data){
				     alert(data);
				     if(isMain==1){
				    	   parent.regreshNodeById("FB_"+form_er_id);
				     }else{
				    	 parent.regreshNodeById("root"); 
				     }
				  
				   });
	}
	});
});
