var store;
Ext.onReady(function() {
	Ext.define('DataListModel', {
	    extend: 'Ext.data.Model',
	    fields: [{
	        name: 'id',
	        type: 'string'
	    },{
	        name: 'name',
	        type: 'string'
	    },{
	        name: 'displayTitle',
	        type: 'string'
	    },{
	        name: 'dataListType',
	        type: 'string'
	    },{
	        name: 'order_index',
	        type: 'int'
	    }]
	});
 store=Ext.create('Ext.data.Store', {
    model:'DataListModel',
    proxy: {
        type: 'ajax',
        url:  encodeURI(ctx + '/zq/dataList/getDataListJson.zq?moxingkuid='+moxingkuid+'&dataListType='+dataListType),
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
    selModel:selModel,
    dockedItems: [{
        xtype: 'toolbar',
         dock: 'top',
        items: [{
            text: '新增',
            iconCls: 'icon-add',
            handler:add
        },'-', {
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
              { text: 'id',  dataIndex: 'id',flex: 0.1,hidden:true },
        { text: '名称',  dataIndex: 'name',flex: 0.3},
        { text: '显示名称',  dataIndex: 'displayTitle',flex: 0.3},
        { text: '列表类型',  dataIndex: 'dataListType',flex: 0.1},
        { text: '操作',flex: 0.2,renderer:caozuo},
        { text: 'order_index',  dataIndex: 'order_index',hidden:true }
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
function add(){
	if(dataListType=="流程列表"){
		parent.createViewTab("addprocesslist", "添加流程列表", "", ctx+"/zq/dataList/getCreateDataListPage.zq?moxingkuid="+moxingkuid+"&dataListType="+dataListType, true);
	}else{
		parent.createViewTab("adddatalist", "添加数据列表","", ctx+"/zq/dataList/getCreateDataListPage.zq?moxingkuid="+moxingkuid+"&dataListType="+dataListType, true);
	}

};
function del(){
    var selModel = grid.getSelectionModel();
    if (selModel.hasSelection()) {
        Ext.Msg.confirm("警告", "确定要删除吗？", function (button) {
            if (button == "yes") {
                var selected = selModel.getSelection();
                var ids = []; //要删除的id
                Ext.each(selected, function (item) {
                		 ids.push(item.data.id);
                });
                dodel(ids);
            }
        });
    }
    else {
        Ext.Msg.alert("错误", "没有选择数据，无法进行删除操作！");
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
                url:ctx+'/zq/dataList/delDataList.zq',
                method: "post",
                params: {ids:ids},
                success: function (response, opts) {
                    Ext.MessageBox.hide();
                    if (response.responseText) {
                    	if(dataListType=="流程列表"){
                         	parent.regreshNodeById("processlist_"+moxingkuid);
                    	}else{
                    	parent.regreshNodeById("datalist_"+moxingkuid);}
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
function caozuo(value,metaData,record,rowIndex,colIndex,store,view){
	var id=record.data.id;
	var bianji="<img src=\""+ctx+"/zq_img/datalist/bianji.png\">";
	var shengchengyemian="<img src=\""+ctx+"/zq_img/datalist/shengchengyemian.png\">";
	var bushu="<img src=\""+ctx+"/zq_img/datalist/bushu.png\">";
	var ceshi="<img src=\""+ctx+"/zq_img/datalist/ceshi.png\">";
	var returns="<a href='#' onclick='edit(\""+id+"\",\""+record.data.name+"\");'>"+bianji+" </a>&nbsp;&nbsp;<a href='#' onclick='build(\""+id+"\");'>"+shengchengyemian+"</a>&nbsp;&nbsp;<a href='#' onclick='run(\""+id+"\");'>"+ceshi+"</a>&nbsp;&nbsp;<a href='#' onclick='bushu(\""+id+"\");'>"+bushu+"</a>";
	return returns;
}
function build(id){
	$.post(ctx+"/zq/datalist/build.zq",{type:'bootgrid',id:id},function(data){
		alert(data);
	});
}
function edit(id,name){
	parent.createViewTab("listdata", name, "",ctx+"/zq/dataList/getDataListMainPage.zq?datalistid="+id, true);
}

function run(id){
	window.open(ctx+"/run/datalist/page.zq?id="+id,"_blank");
}

function bushu(id){
	
}