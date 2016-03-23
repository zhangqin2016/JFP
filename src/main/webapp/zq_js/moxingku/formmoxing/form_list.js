var store;
Ext.onReady(function() {
	//id,name,master,model_lib_id,template_name,order_index
	Ext.define('FormModel', {
	    extend: 'Ext.data.Model',
	    fields: [{
	        name: 'id',
	        type: 'string'
	    },{
	        name: 'name',
	        type: 'string'
	    },{
	        name: 'type',
	        type: 'string'
	    },{
	        name: 'order_index',
	        type: 'int'
	    }]
	});
 store=Ext.create('Ext.data.Store', {
    model:'FormModel',
    proxy: {
        type: 'ajax',
        url: ctx + '/moxingku/form/gridJson.zq?model_lib_id='+model_lib_id,
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
        { text: '表单名称',  dataIndex: 'name',flex: 0.4, editor: {
            allowBlank: false
        }},{ text: '表单类型',  dataIndex: 'type',flex: 0.5, editor:    {
        	xtype:"combobox",
        	editable:false,
            store: Ext.create('Ext.data.Store', {
                fields: ['type','value'],
                data : [
                    {"type":"存储表单:0","value":0},
                    {"type":"sql表单:1","value":1}
                ]
            }),
            queryMode: 'local',
            displayField: 'type',
            valueField: 'value'
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
function caozuo(value,metaData,record,rowIndex,colIndex,store,view){
	if(record.get("id")=='')return "";
	var src="<a href='#' onclick='openTab(\""+record.get("id")+"\",\""+record.get("name")+"\")'>生成表单</a>";
	return src;
}
function add(){
	 var rec = new FormModel({
         name: '',
         id:'',
         type:0
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
        url:ctx+'/moxingku/form/save.zq',
        method: "post",
        params: {
        	datas :Ext.JSON.encode(json),
        	model_lib_id:model_lib_id
        },
        success: function (response, opts) {
            Ext.MessageBox.hide();
            if (response.responseText) {
              	parent.regreshNodeById('form_'+model_lib_id);
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
                var ids = []; //要删除的id
                Ext.each(selected, function (item) {
                	if(item.data.id==''){
                		store.remove(item);
                	}else{
                		ids.push(item.data.id);
                	}
                   
                })
                dodel(ids);
            }
        });
    }
    else {
        Ext.Msg.alert("错误", "没有任何行被选中，无法进行删除操作！");
    }

}
function dodel(ids){
	if(ids.length==0){
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
                url:ctx+'/moxingku/form/del.zq',
                method: "post",
                params: {ids:ids},
                success: function (response, opts) {
                    Ext.MessageBox.hide();
                    if (response.responseText) {
                     	parent.regreshNodeById('form_'+model_lib_id);
                    	store.remove(selModel.getSelection());
                        Ext.Msg.alert('系统提示',response.responseText);
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
	$(parent.document).find("#liebiao").attr("src",ctx+"/moxingku/form/linkTable.zq?form_id="+id);
}
