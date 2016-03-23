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
            title: '一级菜单',
            id:'tableibiao',
            html: "<iframe src='"+ctx+"/portal/navication/first.zq' height='100%' width='100%' name='liebiao' id='liebiao' frameborder='0'></iframe>"
        }]
    });
    /**
			 * tree
			 */
      store = Ext.create('Ext.data.TreeStore', {
        proxy: {
            type: 'ajax',
            url: ctx + '/portal/navication/treeJson.zq',
            reader: {
                type: 'json',
                root: 'root'
            }
        },
        root: {
            id: 'root',
            expanded: true,
            text:"导航菜单"
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
      var  msgTip ;
      tree.getView().addListener("beforedrop",function(node,data,overModel,dropPosition,dropHandler,eOpts){
    	  msgTip = new Ext.LoadMask(Ext.getBody(),{  
                msg:'请稍后...',  
                removeMask : true                     
            });  
    	  var yuanshi=data.records[0].data.id;
    	  var wancheng=overModel.data.id;
    	 //“before”，“after”或者“append”
     	  var yuanshi=data.records[0].data.id;
    	  var wancheng=overModel.data.id;
    	  if(yuanshi.indexOf('first_')==0){
    		if(dropPosition=='append'){
    			  return false;
      		}else if(wancheng.indexOf('first_')==-1){
    			  return false;
    		}else{
    			doMove(yuanshi,wancheng,dropPosition);
    		}
    	  }else if(yuanshi.indexOf('second_')==0){
    		  if(dropPosition=='append'){
    			  if(wancheng.indexOf('first_')!=-1){
    				  doMove(yuanshi,wancheng,dropPosition);
    			  }else{
    				  return false;
    			  }
    		  }else if(wancheng.indexOf('second_')!=-1){
    			  doMove(yuanshi,wancheng,dropPosition);
    		  }else {
    			  return false;
    		  }
    	  }else if(yuanshi.indexOf('three_')==0){
    		  if(dropPosition=='append'){
    			  if(wancheng.indexOf('second_')!=-1){
    				  doMove(yuanshi,wancheng,dropPosition);
    			  }
    		  }else if(wancheng.indexOf('three_')!=-1){
    			  doMove(yuanshi,wancheng,dropPosition);
    		  }else {
    			  return false;
    		  }
    	  }else {
    		  return false;
    	  }
      });
     function doMove(yuanshi,wancheng,dropPosition){
    	  msgTip.show();
    	 Ext.Ajax.request({
   	        url:ctx+'/portal/navication/dropTree.zq',
   	        method: "post",
   	        params: {
   	        	yuanshi :yuanshi,
   	        	wancheng :wancheng,
   	        	dropPosition:dropPosition
   	        },
   	        success: function (response, opts) {
   	        	var node = store.getNodeById(wancheng);
   	        	var opions = {node:node.parentNode};//进行封装                               
   	     			store.load(opions);//局部加载
   	        	msgTip.hide();
   	        },
   	        failure: function () {
   	            Ext.Msg.alert('系统提示', '系统出错！');
   	         msgTip.hide();
   	        }
   	    });
     }
    tree.addListener("itemclick",function( t, record, item, index, e, eOpts ){
    	if(record.data.id=='root'){
    		Ext.getCmp("tableibiao").setTitle("一级菜单");
    		$("#liebiao").attr("src",ctx+"/portal/navication/first.zq");
    	}else if(record.data.id.indexOf('first_')==0){
    		Ext.getCmp("tableibiao").setTitle("二级菜单");
    		$("#liebiao").attr("src",ctx+"/portal/navication/second.zq?first_id="+record.data.id.replace("first_",""));
        	Ext.getCmp("tableibiao").setActive(true);
        	Ext.getCmp("tableibiao").show();
    	}else if(record.data.id.indexOf('second_')==0){
    		Ext.getCmp("tableibiao").setTitle("三级菜单");
    		$("#liebiao").attr("src",ctx+"/portal/navication/three.zq?second_id="+record.data.id.replace("second_",""));
        	Ext.getCmp("tableibiao").setActive(true);
        	Ext.getCmp("tableibiao").show();
    	}else{
    		
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
            title: '导航菜单',
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
			tab.addListener('show', function() {
				document.getElementById(frmId).src = src;
			});
		}
		tab.setTitle(tabTitle);
		tab.show();
	}
}
