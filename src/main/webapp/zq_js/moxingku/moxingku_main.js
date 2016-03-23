var store;
var tree;
var tabs;
Ext.onReady(function() {
    var cw;
  tabs = Ext.widget('tabpanel', {
        width: '100%',
        activeTab: 0,
        id:"tabPanel",
       defaults: {
            bodyPadding: 10
        },
        items: [{
            width: '100%',
            title: '首页',
            id:'tableibiao',
            html: "<iframe src='' height='100%' width='100%' name='liebiao' id='liebiao' frameborder='0'></iframe>"
        }]
    });

    /**
			 * tree
			 */
      store = Ext.create('Ext.data.TreeStore', {
        proxy: {
            type: 'ajax',
            url: ctx + '/moxingku/main/treeJson.zq',
            reader: {
                type: 'json',
                root: 'root'
            }
        },
        root: {
            id: 'root',
            text:'模型库',
            expanded: true
        }
    });

      tree = Ext.create('Ext.tree.Panel', {
        store: store,
        animate:false,
        viewConfig: {
            plugins: {
                ptype: 'treeviewdragdrop',
                enableDrop: true
            }
        },
        rootVisible: true
    });
    
    tree.addListener("itemclick",function( t, record, item, index, e, eOpts ){
    	 Ext.getCmp("tableibiao").show();
    	if(record.data.id=='root'){
    		Ext.getCmp("tableibiao").setTitle("模型列表");
    		$("#liebiao").attr("src",ctx+"/moxingku/main/listPage.zq");
    	}else if(record.data.id.indexOf('table_')==0){
    		Ext.getCmp("tableibiao").setTitle("数据建模");
    		$("#liebiao").attr("src",ctx+"/moxingku/table.zq?model_lib_id="+record.data.id.replace("table_",""));
    	}else if(record.data.id.indexOf('metadata_')==0){
    		Ext.getCmp("tableibiao").setTitle(record.data.text);
    		$("#liebiao").attr("src",ctx+"/moxingku/table/field.zq?metadata_id="+record.data.id.replace("metadata_",""));
    	}else if(record.data.id.indexOf('form_')==0){
    		Ext.getCmp("tableibiao").setTitle(record.data.text);
    		$("#liebiao").attr("src",ctx+"/moxingku/form.zq?metadata_id="+record.data.id.replace("form_",""));
    	}else if(record.data.id.indexOf('formlist_')==0){
    	    Ext.getCmp("tableibiao").setTitle(record.data.text);
    		$("#liebiao").attr("src",ctx+"/moxingku/form/linkTable.zq?form_id="+record.data.id.replace("formlist_",""));
    	}else if(record.data.id.indexOf('datalist_')==0){
    	    Ext.getCmp("tableibiao").setTitle(record.data.text);
    		$("#liebiao").attr("src",ctx+"/zq/dataList/getDataListPage.zq?dataListType=数据列表&moxingkuid="+record.data.id.replace("datalist_",""));
    	}else if(record.data.id.indexOf('processlist_')==0){
    		 Ext.getCmp("tableibiao").setTitle(record.data.text);
     		$("#liebiao").attr("src",ctx+"/zq/dataList/getDataListPage.zq?dataListType=流程列表&moxingkuid="+record.data.id.replace("datalist_",""));
    	}
    	else if(record.data.id.indexOf('listdata_')==0){
    		createViewTab("listdata", record.data.text, "",ctx+"/zq/dataList/getDataListMainPage.zq?datalistid="+record.data.id.replace("listdata_",""), true);

    	}
    });
    /**
			 * 
			 * 布局
			 */
    var viewport = Ext.create('Ext.Viewport', {
        layout: {
            type: 'border',
            padding: 1
        },
        items: [{
            region: 'west',
            collapsible: true,
            title: '模型库',
            split: true,
            width: '20%',
            bodyPadding: 10,
            items: [tree]
        },
        {
            region: 'center',
            items: [tabs]
        }]
    });
    tabs.setHeight(viewport.getHeight() - 10);
    tree.setHeight(viewport.getHeight() - 60);
});

function regreshtab(){
	$("#liebiao").attr("src",$("#liebiao").attr("src"));
	//document.getElementById("liebiao").src = document.getElementById("liebiao").src;$("#liebiao").attr("src",$("#liebiao").attr("src"));
	
}
function regreshNodeById(id) {
	var node = store.getNodeById(id);
	if (node) {
		if (!node.leaf) {
			var opions = {node:node};//进行封装                               
			store.load(opions);//局部加载
			//tree.expandPath(node.getPath());
		} else {
			var opions = {node:node.parentNode};//进行封装                               
			store.load(opions);//局部加载
			//tree.expandPath(node.parentNode.getPath());
		}
	}
}

function closeTab(id){
	if(tabs.getComponent(id)){
		tabs.getComponent(id).close();
	}
	
}
function createViewTab(tabId, tabTitle, iconCls, src, isRefresh) {
	if (typeof (isRefresh) == "undefined") {
		isRefresh = false;
	}
	var frmId = tabId + '_Frame';
	var tab = tabs.getComponent(tabId);
	if (tab == null) {
		tab = {
			id : tabId,
			title : tabTitle,
			tabTip : tabTitle,
			closable : true,
			autoScroll : true,
			iconCls : iconCls,
			html : "<iframe id='" + frmId + "' name='" + frmId + "' src='" + src + "' frameborder=0 width=100% height=100%></iframe>"
		};
		tabs.add(tab);
		tabs.getComponent(tabId).show();
		
	} else {
		if (isRefresh) {// 刷新打开的tab页
				document.getElementById(frmId).src = src;
		}
		tab.setTitle(tabTitle);
		tab.show();
	}
}