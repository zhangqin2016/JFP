var store;
Ext.onReady(function() {
	Ext.define('roleModel', {
	    extend: 'Ext.data.Model',
	    fields: [{
	        name: 'roleGroup',
	        type: 'string'
	    },{
	        name: 'id',
	        type: 'string'
	    }, {
	        name: 'orderIndex',
	        type: 'int'
	    }]
	});
 store=Ext.create('Ext.data.Store', {
    model:'roleModel',
    proxy: {
        type: 'ajax',
        url: ctx + '/portal/org/role/gridGroupJson.zq',
        reader: {
            type: 'json',
            root: 'root'
        }
    }
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
    draggable:true,
    selModel:selModel,
    dockedItems: [{
        xtype: 'toolbar',
         dock: 'top',
        items: [{
            text: '新增',
            iconCls: 'icon-add',
            handler:add
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
        { text: '角色分组',  dataIndex: 'roleGroup',flex: 1},
        { text: 'order_index',  dataIndex: 'orderIndex',hidden:true }
    ]
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
grid.setHeight(viewport.getHeight());
grid.setWidth(viewport.getWidth());
function add(){
    parent.createViewTab("add_rolegroup", "新建角色分组", "", ctx+"/portal/org/role/addRolePage.zq", false, null);
}

function del(){
        Ext.Msg.alert("提示", "分组下角色清空分组自动删除!");
}
});