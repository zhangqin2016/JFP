function deleteData(datalist_id){
	var ids=$("#datalist_"+datalist_id+"").bootgrid("getSelectedRows");
	if(ids.length==0){
	    $.alert({
	        title: '提示!',
	        confirmButton:'确定',
	        autoClose: 'confirm|1000',
	        content: '请选择要删除的数据'
	    });
	    
	}else{
	    $.confirm({
	        keyboardEnabled: true,
	        content: '是否删除？',
	        title:"提示",
	        animation:"scalex",
	        confirmButton:"是",
	        autoClose: 'cancel|5000',
	        cancelButton:"否",
	        confirm: function(){
	        	$.post("/run/form/bootgrid/deleted_data.zq",{ids:ids.join(","),datalist_id:datalist_id},function(data){
	       		 $("#datalist_"+datalist_id+"").bootgrid('reload');  
	       	});
	        }
	    });
	}
}