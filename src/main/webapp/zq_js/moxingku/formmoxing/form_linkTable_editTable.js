var store;
var tree;
Ext.onReady(function() {
    var cw;
    var tabs = Ext.widget('tabpanel', {
        width: '100%',
        activeTab: 0,
        id:'tabPanel',
        defaults: {
            bodyPadding: 10
        },
        items: [{
            width: '100%',
            title: '',
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
            url: ctx + '/moxingku/form/linkTable/er/treeJson.zq?form_id='+form_id+'',
            reader: {
                type: 'json',
                root: 'root'
            }
        },
        root: {
            id: 'root',
            text:"绑定数据库表",
            expanded: true
        }
    });

      tree = Ext.create('Ext.tree.Panel', {
        store: store,
        id:"formerTree",
        animate:false,
        tbar: [
               { xtype: 'button', text: '添加',handler:add },
               { xtype: 'button', text: '删除',handler:remove }
             ],
        viewConfig: {
            plugins: {
                ptype: 'treeviewdragdrop',
                enableDrop: true
            }
        },
        rootVisible: true
    });
    
    tree.addListener("itemclick",function( t, record, item, index, e, eOpts ){
    	if(record.data.id.indexOf('FB_')==0){
    		Ext.getCmp("tableibiao").setTitle(record.data.text);
    		$("#liebiao").attr("src",ctx+"/moxingku/form/linkTable/ui.zq?form_er_id="+record.data.id.replace("FB_","")+"&isMain=0");
        	Ext.getCmp("tableibiao").setActive(true);
        	Ext.getCmp("tableibiao").show();
    	}else if(record.data.id.indexOf('SB_')==0){
    		Ext.getCmp("tableibiao").setTitle(record.data.text);
    		$("#liebiao").attr("src",ctx+"/moxingku/form/linkTable/ui.zq?form_er_id="+record.data.id.replace("SB_","")+"&isMain=1");
        	Ext.getCmp("tableibiao").setActive(true);
        	Ext.getCmp("tableibiao").show();
    	}
    });
    var viewport = Ext.create('Ext.Viewport', {
        layout: {
            type: 'border',
            padding: 1
        },
        items: [{
            region: 'west',
            collapsible: true,
            title: '数据表',
            split: true,
            width: '23%',
            bodyPadding: 10,
            items: [tree]
        },
        {
            region: 'center',
            items: [tabs]
        }]
    });
    tabs.setHeight(viewport.getHeight() - 40);
    tree.setHeight(viewport.getHeight() - 54);
});

function add(){
	var select=tree.getSelectionModel().getSelection();
	if(select.length==0){
	alert("请选择要添加的节点!");
	}else if (select[0].get("id")=="root"){
		 Ext.create('Ext.Window', {
		    title: "添加主表",
		    width: 600,
		    height:$(window).height(), 
		    plain: true,
		    modal: true,
		    html: "<iframe src='"+ctx+"/moxingku/form/linkTable/er/addTable.zq?form_id="+form_id+"&isMain=0&type="+type+"' height='100%' width='100%' name='xinzeng' id='xinzeng' frameborder='0'></iframe>"
		}).show();
	}else if(select[0].get("id").indexOf('FB_')==0){
		 Ext.create('Ext.Window', {
		    title: "添加子表",
		    width: 600,
		    height:$(window).height(), 
		    plain: true,
		    modal: true,
		    html: "<iframe src='"+ctx+"/moxingku/form/linkTable/er/addTable.zq?form_er_id="+select[0].get("id").replace("FB_","")+"&form_id="+form_id+"&isMain=1&type="+type+"' height='100%' width='100%' name='xinzeng' id='xinzeng' frameborder='0'></iframe>"
			}).show();
	}else{
		alert("子表不能再添加子表");
	}

 
}
function remove(){
	 Ext.Msg.confirm("警告", "确定要删除吗？", function (button) {
         if (button == "yes") {
        		var select=tree.getSelectionModel().getSelection();
        		$.post(ctx+"/moxingku/form/linkTable/er/deleteFormEr.zq",
        				{form_er_id:select[0].get("id")},
        				   function(data){
        				     alert(data);
        				    regreshNodeById("root");
        				   });
         }
     });

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

function createViewTab(tabId, tabTitle, iconCls, src, isRefresh, config) {
	if (typeof (isRefresh) == "undefined") {
		isRefresh = false;
	}
	var frmId = tabId + '_Frame';
	var tabs2 = Ext.getCmp("tabPanel");
	var tab = tabs2.getComponent(tabId);
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
		tabs2.add(tab);
		tabs2.getComponent(tabId).show();
		
	} else {
		if (isRefresh) {// 刷新打开的tab页
			tab.addListener('show', function() {
				document.getElementById(frmId).src = src;
			});
		}
		tab.setTitle(tabTitle);
		tab.show();
	}
}
