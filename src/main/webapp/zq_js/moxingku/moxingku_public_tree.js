
function rendererTree(){
	function buildGrid(renderer_id,height,width,type,dbclick){
		var store;
		var tree;
		 /**
		 * tree
		 */
	store = Ext.create('Ext.data.TreeStore', {
	proxy: {
	    type: 'ajax',
	    url: ctx + '/moxingku/main/treeJson.zq?type='+type,
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
	renderTo:renderer_id,
	id:renderer_id,
	border:"2 2 2 2",
	height:height,
	width:width,
	tbar: [
	       { xtype: 'button', text: '展开',handler:function(){tree.expandAll();}},
	       { xtype: 'button', text: '折叠',handler:function(){tree.collapseAll();}},
	       { xtype: 'button', text: '关闭',handler:function(){$("#"+renderer_id+"").parent().hide();}}
	     ],
	animate:false,
	viewConfig: {
	    plugins: {
	        ptype: 'treeviewdragdrop',
	        enableDrop: true
	    }
	},
	rootVisible: true
	});
	tree.addListener("itemdblclick",function( t, record, item, index, e, eOpts ){
		try{dbclick(record);}catch(e){
			
		}
	});
	
	tree.expandAll();
	}

return {
	init : function(renderer_id,height,width,type,dbclick) {
		if(document.getElementById(renderer_id)){
			document.getElementById(renderer_id).style.display='display';
		buildGrid(renderer_id,height,width,type,dbclick);}else{
			alert("没有找到你所设定的渲染dom的id");
		}
	}
}
}
