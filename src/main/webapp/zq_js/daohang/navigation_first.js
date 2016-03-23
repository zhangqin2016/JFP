var store;
Ext.onReady(function() {
	Ext.define('NavigationFirst', {
	    extend: 'Ext.data.Model',
	    fields: [{
	        name: 'id',
	        type: 'string'
	    },{
	        name: 'firstName',
	        type: 'string'
	    },{
	        name: 'firstUrl',
	        type: 'string'
	    },{
	        name: 'urlTarget',
	        type: 'string'
	    },{
	        name: 'iconUrl',
	        type: 'string'
	    },{
	        name: 'status',
	        type: 'string'
	    },{
	        name: 'orderIndex',
	        type: 'int'
	    }]
	});
 store=Ext.create('Ext.data.Store', {
    model:'NavigationFirst',
    proxy: {
        type: 'ajax',
        url: ctx + '/portal/navication/first/gridJson.zq',
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
    columns: [
              Ext.create('Ext.grid.RowNumberer',{
            	  text:'序号',
            	  width:50,
            	  align:'center'
              }),
              { text: 'id',  dataIndex: 'id',hidden:true },
        { text: '菜单名称',  dataIndex: 'firstName',flex: 0.3, editor: {
            allowBlank: false
        }},
        { text: '菜单地址',  dataIndex: 'firstUrl',flex: 0.3, editor: {
            allowBlank: false
        }},
        { text: '图标',  dataIndex: 'iconUrl',flex: 0.3, editor: {
            allowBlank: false
        }}, { text: '菜单显示区域',  dataIndex: 'urlTarget',flex: 0.5, editor:    {
        	xtype:"combobox",
        	editable:false,
            store: Ext.create('Ext.data.Store', {
                fields: ['type'],
                data : [
                    {"type":"主窗口"},
                    {"type":"新窗口"},
                    {"type":"当前窗口"}
                ]
            }), 
            queryMode: 'local',
            displayField: 'type',
            valueField: 'type'
        }}, 
            { text: '是否可用',  dataIndex: 'status',flex: 0.5, editor:    {
            	xtype:"combobox",
            	editable:false,
                store: Ext.create('Ext.data.Store', {
                    fields: ['type'],
                    data : [
                        {"type":"是"},
                        {"type":"否"}
                    ]
                }),
                queryMode: 'local',
                displayField: 'type',
                valueField: 'type'
            }}, 
        { text: 'order_index',  dataIndex: 'order_index',hidden:true }
    ],
    plugins: [rowEditing]
});
/**
 * 
 * 	private String id;
	
	private String first_name;
	
	private String first_url;
	
	private String url_target;
	
	private String icon_url;
	
	private int order_index;
	
    private int status;

 */
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
	 var rec = new NavigationFirst({
		 firstName: '',
		 firstUrl:'',
		 urlTarget:'主窗口',
		 status:'是',
		 iconUrl:'',
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
        url:ctx+'/portal/navication/first/save.zq',
        method: "post",
        params: {
        	datas :Ext.JSON.encode(json)
        },
        success: function (response, opts) {
            Ext.MessageBox.hide();
            if (response.responseText) {
              	store.reload();	parent.regreshNodeById('root');
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
                url:ctx+'/portal/navication/first/del.zq',
                method: "post",
                params: {ids:ids},
                success: function (response, opts) {
                    Ext.MessageBox.hide();
                    if (response.responseText) {
                     	parent.regreshNodeById('root');
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
	$(parent.document).find("#liebiao").attr("src",ctx+"/portal/navication/first/field.zq?metadata_id="+id);
}
