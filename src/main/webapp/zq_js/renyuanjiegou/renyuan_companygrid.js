var store;
Ext.onReady(function() {
	Ext.define('companyModel', {
	    extend: 'Ext.data.Model',
	    fields: [{
	        name: 'coName',
	        type: 'string'
	    },{
	        name: 'id',
	        type: 'string'
	    }, {
	        name: 'orderIndex',
	        type: 'int'
	    },{
	        name: 'coMemo',
	        type: 'string'
	    }]
	});
 store=Ext.create('Ext.data.Store', {
    model:'companyModel',
    proxy: {
        type: 'ajax',
        url: ctx + '/portal/org/company/gridJson.zq',
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
        { text: '单位名称',  dataIndex: 'coName',flex: 0.5},
        { text: 'orderIndex',  dataIndex: 'orderIndex',hidden:true },
        { text: '单位描述', dataIndex: 'coMemo', flex: 0.5 }
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
    parent.createViewTab("companyid", "新建公司", "", ctx+"/portal/org/company/addPage.zq", false, null);
}

function del(){
    var selModel = grid.getSelectionModel();
    if (selModel.hasSelection()) {
        Ext.Msg.confirm("警告", "确定要删除吗？", function (button) {
            if (button == "yes") {
                var selected = selModel.getSelection();
                var ids = []; //要删除的id
                Ext.each(selected, function (item) {
                    ids.push(item.data.id);
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
    Ext.MessageBox.show({
        msg: '正在请求数据, 请稍侯',
        progressText: '正在请求数据',
        width: 300,
        wait: true,
        waitConfig: { interval: 10 }
    });
            Ext.Ajax.request({
                url:ctx+'/portal/org/company/delCompany.zq',
                method: "post",
                params: {ids:ids},
                success: function (response, opts) {
                    Ext.MessageBox.hide();
                    if (response.responseText) {
                    	parent.regreshNodeById('org');
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