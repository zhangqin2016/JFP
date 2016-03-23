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
            title: '公司列表',
            id:'tableibiao',
            html: "<iframe src='"+ctx+"/portal/org/company.zq' height='100%' width='100%' name='liebiao' id='liebiao' frameborder='0'></iframe>"
        }]
    });
    /**
			 * tree
			 */
      store = Ext.create('Ext.data.TreeStore', {
        proxy: {
            type: 'ajax',
            url: ctx + '/portal/org/treeJson.zq',
            reader: {
                type: 'json',
                root: 'root'
            }
        },
        root: {
            id: 'root',
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
        rootVisible: false
    });
    
    tree.addListener("itemclick",function( t, record, item, index, e, eOpts ){
    	if(record.data.id=='org'){
    		Ext.getCmp("tableibiao").setTitle("单位列表");
    		$("#liebiao").attr("src",ctx+"/portal/org/company.zq");
        	Ext.getCmp("tableibiao").setActive(true);
        	Ext.getCmp("tableibiao").show();
    	}else if(record.data.id.indexOf('comp_')==0){
    		Ext.getCmp("tableibiao").setTitle("部门/人员列表");
    		$("#liebiao").attr("src",ctx+"/portal/org/dept.zq?type=comp&id="+record.data.id);
        	Ext.getCmp("tableibiao").setActive(true);
        	Ext.getCmp("tableibiao").show();
    	}else if(record.data.id.indexOf('dept_')==0){
    		Ext.getCmp("tableibiao").setTitle("部门/人员列表");
    		$("#liebiao").attr("src",ctx+"/portal/org/dept.zq?type=dept&id="+record.data.id);
        	Ext.getCmp("tableibiao").setActive(true);
        	Ext.getCmp("tableibiao").show();
    	}else if(record.data.id.indexOf('user_')==0){
    		createViewTab("edit_user", "编辑人员", "", ctx + "/portal/org/user/editPage.zq?type=tree&id="+ record.data.id.replace("user_","")+"&"+Date.parse(new Date()), true, null)
    	}else if(record.data.id=='role'){
    		Ext.getCmp("tableibiao").setTitle("角色分组");
    		$("#liebiao").attr("src",ctx+"/portal/org/role/rolegroup.zq");
        	Ext.getCmp("tableibiao").setActive(true);
        	Ext.getCmp("tableibiao").show();
    	}else if(record.data.id.indexOf('rolegroup_')==0){
    		Ext.getCmp("tableibiao").setTitle("角色");
    		Ext.getCmp("tableibiao").setActive(true);
    		$("#liebiao").attr("src",encodeURI(ctx+"/portal/org/role.zq?role_group="+record.data.id.replace("rolegroup_","")));
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
            title: '人员结构',
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
    tabs.setHeight(viewport.getHeight() - 40);
    tree.setHeight(viewport.getHeight() - 54);
});
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
				document.getElementById(frmId).src = src;
		}
		tab.setTitle(tabTitle);
		tab.show();
	}
}
