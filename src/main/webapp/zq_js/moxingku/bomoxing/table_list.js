var store;
Ext.onReady(function() {
	Ext.define('MetadataModel', {
	    extend: 'Ext.data.Model',
	    fields: [{
	        name: 'id',
	        type: 'string'
	    },{
	        name: 'table_name',
	        type: 'string'
	    },{
	        name: 'table_title',
	        type: 'string'
	    },{
	        name: 'order_index',
	        type: 'int'
	    }]
	});
 store=Ext.create('Ext.data.Store', {
    model:'MetadataModel',
    proxy: {
        type: 'ajax',
        url: ctx + '/moxingku/table/gridJson.zq?model_lib_id='+model_lib_id,
        reader: {
            type: 'json',
            root: 'root'
        }
    }
});
 var rowEditing = Ext.create('Ext.grid.plugin.CellEditing', {
	 clicksToEdit: 1
	});
var selModel = Ext.create('Ext.selection.CheckboxModel', {
    listeners: {
        selectionchange: function(sm, selections) {
            grid.down('#removeButton').setDisabled(selections.length === 0);
        }
    }
});
var grid=Ext.create('Ext.grid.Panel', {
    store: store,
    selModel:selModel,
    dockedItems: [{
        xtype: 'toolbar',
         dock: 'top',
        items: [{
            text: '新增',
            iconCls: 'icon-add',
            handler:add
        },'-',{
            text: '保存',
            iconCls: 'icon-save',
            handler:save
        }, '-', {
            text: '删除',
            itemId:'removeButton',
            iconCls: 'icon-delete',
            disabled: true,
            handler: del
        }]
    }],
/*    viewConfig: {
        plugins: {
            ptype: 'gridviewdragdrop',
            enableDrop: true
        }
    },*/
    columns: [
              Ext.create('Ext.grid.RowNumberer',{
            	  text:'序号',
            	  width:50,
            	  align:'center'
              }),
              { text: 'id',  dataIndex: 'id',hidden:true },
        { text: '表标题',  dataIndex: 'table_title',flex: 0.3, editor: {
            allowBlank: false
        }},
        { text: '表名(表名前自动补充bo_)',  dataIndex: 'table_name',flex: 0.3, editor: {
            allowBlank: false
        }},
        { text: '操作', flex: 0.3,renderer:caozuo},
        { text: 'order_index',  dataIndex: 'order_index',hidden:true }
    ],
    plugins: [rowEditing]
});

var viewport = Ext.create('Ext.Viewport', {
    layout: {
        type: 'border',
        padding: 1
    },
    items: [{
        region: 'center',
		items:[grid]
    }]
});
store.load();
grid.on('edit', function(editor, e) {
	if(e.field=='table_title'){
		e.record.set("table_name",makePy(e.value)[0].toLowerCase());
	}
    // commit the changes right after editing finished
});
function caozuo(value,metaData,record,rowIndex,colIndex,store,view){
	if(record.get("id")=='')return "";
	var src="<a href='#' onclick='openTab(\""+record.get("id")+"\",\""+record.get("table_title")+"["+record.get("table_name")+"]\")'>查看字段</a>";
		return src;
}
function add(){
	 var rec = new MetadataModel({
         table_name: '',
         table_title:'',
         id:''
     });
     store.insert(store.data.items.length, rec);
     rowEditing.startEditByPosition({
         row: store.data.items.length-1, 
         column: 2
     });
}

function save(){
    Ext.MessageBox.show({
        msg: '正在请求数据, 请稍侯',
        progressText: '正在请求数据',
        width: 300,
        wait: true,
        waitConfig: { interval: 10 }
    });
	var records=store.getModifiedRecords();
	if(records.length==0){
		Ext.Msg.alert('系统提示',"没有需要保存的数据!");
		return;
	}
	var json=[];
	Ext.Array.each(records, function(item) {
			json.push(item.data);
	});
    Ext.Ajax.request({
        url:ctx+'/moxingku/table/save.zq',
        method: "post",
        params: {
        	datas :Ext.JSON.encode(json),
        	model_lib_id:model_lib_id
        },
        success: function (response, opts) {
            Ext.MessageBox.hide();
            if (response.responseText) {
              	parent.regreshNodeById('table_'+model_lib_id);
             store.reload();
                Ext.Msg.alert('系统提示',response.responseText);
            } else {
                Ext.Msg.alert("系统提示", '操作失败');
            }
        },
        failure: function () {
            Ext.Msg.alert('系统提示', '系统出错！');
        }
    });
}
function del(){
    var selModel = grid.getSelectionModel();
    if (selModel.hasSelection()) {
        Ext.Msg.confirm("警告", "确定要删除吗？", function (button) {
            if (button == "yes") {
                var selected = selModel.getSelection();
                var table_names = []; //要删除的id
                var table_ids = [];
                Ext.each(selected, function (item) {
                	if(item.data.id==''){
                		store.remove(item);
                	}else{
                		table_names.push(item.data.table_name);
                		table_ids.push(item.data.id);
                	}
                   
                })
                dodel(table_names,table_ids);
            }
        });
    }
    else {
        Ext.Msg.alert("错误", "没有任何行被选中，无法进行删除操作！");
    }

}
function dodel(table_names,table_ids){
	if(table_names.length==0){
		return;
	}
    Ext.MessageBox.show({
        msg: '正在请求数据, 请稍侯',
        progressText: '正在请求数据',
        width: 300,
        wait: true,
        waitConfig: { interval: 10 }
    });
            Ext.Ajax.request({
                url:ctx+'/moxingku/table/del.zq',
                method: "post",
                params: {tableNames:table_names,table_ids:table_ids},
                success: function (response, opts) {
                    Ext.MessageBox.hide();
                    if (response.responseText) {
                    	alert(response.responseText);
                    	  store.reload();
                    	  	parent.regreshNodeById('table_'+model_lib_id);
                    /*	   Ext.Msg.alert('系统提示',response.responseText);
                     	parent.regreshNodeById('table_'+model_lib_id);
                        	parent.regreshNodeById('metadata_'+metadata_id);
                            var select = selModel.getSelection();
                            Ext.each(select, function (item) {
                            	if(response.responseText.indexOf(item.data.table_name)==-1){
                            		store.remove(item);
                            	}
                            })*/
                    } else {
                        Ext.Msg.alert("系统提示", '删除失败');
                    }
                },
                failure: function () {
                    Ext.Msg.alert('系统提示', '系统出错！');
                }
            });
}
});
function openTab(id,name){
	parent.Ext.getCmp("tableibiao").setTitle(name);
	$(parent.document).find("#liebiao").attr("src",ctx+"/moxingku/table/field.zq?metadata_id="+id);
}
