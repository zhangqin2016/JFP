var store;
Ext.onReady(function() {
	Ext.define('ModelLibraryModel', {
	    extend: 'Ext.data.Model',
	    fields: [{
	        name: 'id',
	        type: 'string'
	    },{
	        name: 'model_name',
	        type: 'string'
	    }, {
	        name: 'order_index',
	        type: 'int'
	    }]
	});
 store=Ext.create('Ext.data.Store', {
    model:'ModelLibraryModel',
    proxy: {
        type: 'ajax',
        url: ctx + '/moxingku/main/gridJson.zq',
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
              { text: 'id',  dataIndex: 'id',flex: 0.5,hidden:true },
        { text: '模型名称',  dataIndex: 'model_name',flex: 0.5, editor: {
            allowBlank: false
        }},
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
function add(){
	 var rec = new ModelLibraryModel({
         model_name: ''
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
        url:ctx+'/moxingku/main/save.zq',
        method: "post",
        params: {
        	datas :Ext.JSON.encode(json)
        },
        success: function (response, opts) {
            Ext.MessageBox.hide();
            if (response.responseText) {
            	parent.regreshNodeById('root');
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
                url:ctx+'/moxingku/main/del.zq',
                method: "post",
                params: {ids:ids},
                success: function (response, opts) {
                    Ext.MessageBox.hide();
                    if (response.responseText) {
                    	parent.regreshNodeById('root');
                    	store.reload();
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