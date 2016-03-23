var store;
Ext.onReady(function() {
	Ext.define('MetadataMapModel', {
	    extend: 'Ext.data.Model',
	    fields: [{
	        name: 'id',
	        type: 'string'
	    },{
	        name: 'column_title',
	        type: 'string'
	    },{
	        name: 'column_name',
	        type: 'string'
	    },{
	        name: 'column_type',
	        type: 'string'
	    },{
	        name: 'column_length',
	        type: 'string'
	    },{
	        name: 'order_index',
	        type: 'int'
	    },{
	        name: 'metadata_id',
	        type: 'string'
	    }]
	});
 store=Ext.create('Ext.data.Store', {
    model:'MetadataMapModel',
    proxy: {
        type: 'ajax',
        url: ctx + '/moxingku/table/field/gridJson.zq?metadata_id='+metadata_id,
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
    height:jQuery(window).height(),
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
              { text: 'metadata_id',  dataIndex: 'metadata_id',hidden:true },
        { text: '列标题',  dataIndex: 'column_title',flex: 0.5, editor: {
            allowBlank: false
        }},
        { text: '列名',  dataIndex: 'column_name',flex: 0.5, editor: {
            allowBlank: false
        }},  { text: '列类型',  dataIndex: 'column_type',flex: 0.5, editor:    {
        	xtype:"combobox",
        	editable:false,
            store: Ext.create('Ext.data.Store', {
                fields: ['type'],
                data : [
                    {"type":"文本"},
                    {"type":"数值"},
                    {"type":"日期"}
                ]
            }),
            queryMode: 'local',
            displayField: 'type',
            valueField: 'type'
        }},  { text: '列长度',  dataIndex: 'column_length',flex: 0.5, editor: {
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
grid.on('edit', function(editor, e) {
	if(e.field=='column_title'){
		e.record.set("column_name",makePy(e.value)[0].toLowerCase());
	}
    // commit the changes right after editing finished
});
function add(){
	 var rec = new MetadataMapModel({
		 column_title: '',
		 column_name:'',
		 column_length:'100',
		 column_type:'文本',
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
        url:ctx+'/moxingku/table/field/save.zq',
        method: "post",
        params: {
        	datas :Ext.JSON.encode(json),
        	metadata_id:metadata_id
        },
        success: function (response, opts) {
            Ext.MessageBox.hide();
            if (response.responseText) {
              	parent.regreshNodeById('metadata_'+metadata_id);
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
                var table_fields = []; //要删除的id
                Ext.each(selected, function (item) {
                	if(item.data.id==''){
                		store.remove(item);
                	}else{
                		table_fields.push(item.data);
                	}
                   
                })
                dodel(table_fields);
            }
        });
    }
    else {
        Ext.Msg.alert("错误", "没有任何行被选中，无法进行删除操作！");
    }

}
function dodel(table_fields){
	if(table_fields.length==0){
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
                url:ctx+'/moxingku/table/field/del.zq',
                method: "post",
                params: {datas:Ext.encode(table_fields)},
                success: function (response, opts) {
                    Ext.MessageBox.hide();
                    if (response.responseText) {
                    	parent.regreshNodeById('metadata_'+metadata_id);
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