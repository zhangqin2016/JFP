var store;
Ext.onReady(function() {
	Ext.define('DataListButton', {
		extend : 'Ext.data.Model',
		fields : [ {
			name : 'id',
			type : 'string'
		}, {
			name : 'title',
			type : 'string'
		},{name : 'order_index',
			type : 'int'
		} ]
	});
	store = Ext.create('Ext.data.Store', {
		model : 'DataListButton',
		proxy : {
			type : 'ajax',
			url : ctx + '/zq/dataList/botton/gridJson.zq?datalistid='+ datalistid,
			reader : {
				type : 'json',
				root : 'root'
			}
		}
	});
	var selModel = Ext.create('Ext.selection.CheckboxModel',
			{
				listeners : {
					selectionchange : function(sm, selections) {
						grid.down('#removeButton').setDisabled(
								selections.length === 0);
					}
				}
			});
	var grid = Ext.create('Ext.grid.Panel', {
		store : store,
		selModel : selModel,
		height:$(window).height(),
		dockedItems : [ {
			xtype : 'toolbar',
			dock : 'top',
			items : [ {
				text : '新增',
				iconCls : 'icon-add',
				handler : function(){
					add("");
				}
			}, '-', {
				text : '删除',
				itemId : 'removeButton',
				iconCls : 'icon-delete',
				disabled : true,
				handler : del
			} ]
		} ],
		/*
		 * viewConfig: { plugins: { ptype: 'gridviewdragdrop', enableDrop: true } },
		 */
		columns : [ Ext.create('Ext.grid.RowNumberer', {
			text : '序号',
			width : 50,
			align : 'center'
		}), {
			text : 'id',
			dataIndex : 'id',
			flex : 0.01,
			hidden : true
		}, {
			text : '标题',
			dataIndex : 'title',
			renderer:rendererTitle,
			flex : 0.2
		}, {
			text : '权限',
			flex : 0.2
		},{text : 'order_index',
			dataIndex : 'order_index',
			hidden : true
		} ]
	});

	var viewport = Ext.create('Ext.Viewport', {
		layout : {
			type : 'border',
			padding : 1
		},
		items : [ {
			region : 'center',
			items : [ grid ]
		} ]
	});
	
	store.load();
	function del() {
		var selModel = grid.getSelectionModel();
		if (selModel.hasSelection()) {
			Ext.Msg.confirm("警告", "确定要删除吗？", function(button) {
				if (button == "yes") {
					var selected = selModel.getSelection();
					var ids = []; // 要删除的id
					Ext.each(selected, function(item) {
							ids.push(item.data.id);
					})
					dodel(ids);
				}
			});
		} else {
			Ext.Msg.alert("错误", "没有任何行被选中，无法进行删除操作！");
		}

	}
	function dodel(ids) {
		if (ids.length == 0) {
			return;
		}
		Ext.MessageBox.show({
			msg : '正在请求数据, 请稍侯',
			progressText : '正在请求数据',
			width : 300,
			wait : true,
			waitConfig : {
				interval : 10
			}
		});
		Ext.Ajax.request({
			url : ctx + '/zq/dataList/botton/delete.zq',
			method : "post",
			params : {
				ids:ids.join(","),
				dataListId:datalistid
			},
			success : function(response, opts) {
				Ext.MessageBox.hide();
				if (response.responseText) {
					store.reload();
					Ext.Msg.alert('系统提示', response.responseText);
				} else {
					Ext.Msg.alert("系统提示", '删除失败');
				}
			},
			failure : function() {
				Ext.Msg.alert('系统提示', '系统出错！');
			}
		});
	}
});

	
	function rendererTitle(value,metaData,record, rowIndex,colIndex,store,view){
		var a="<a href='#' onclick='add(\""+record.data.id+"\")'>"+value+"</a>";
		return a;
	}
	
	function closeAddWindow(){
		Ext.getCmp('addWindowId').hide();
		Ext.getCmp('addWindowId').destroy();
	}
	function storeReload(){
		store.reload();
	}
	function add(id){
		  Ext.create('Ext.Window',{
			    width: $(window).width(),  
			    autoScroll:true,
			    border:1,
			    id:'addWindowId',
			    height:$(window).height(), 
			    constrain:true,
			    constrainHeader:true,
			    layout: 'border',
			    title: '添加按钮',
			    items:[getUItree(id),getTab()]
			  }).show(); 
		  
		  
		}
		function getUItree(id){
			var treestore = Ext.create('Ext.data.TreeStore', {
				   proxy: {
			            type: 'ajax',
			            url: ctx + '/zq/dataList/botton/getAddButtonTreeJson.zq',
			            reader: {
			                type: 'json',
			                root: 'root'
			            }
			        },
			    root: {
			        expanded: true,
			        id:'root',
			        text: "按钮", 
			        leaf: false
			    }
			});

		var tree =Ext.create('Ext.tree.Panel', {
			 width: ($(window).width())*0.2,  
			    height:$(window).height(),
			    region:'west',
			    store: treestore,
			    border:1,
			    rootVisible: false
			});
		tree.addListener("itemclick",function( t, record, item, index, e, eOpts ){
			Ext.getCmp("buttonTab").setTitle(record.data.text);
			$("#buttonframe").attr("src",ctx+"/zq/dataList/botton/getAddPage.zq?id="+id+"&dataListId="+datalistid+"&buttonId="+ record.data.id);

		});
			return tree;
		}

		function getTab(){
			   var windowtabs = Ext.createWidget('tabpanel', { 
				   width:( $(window).width())*0.8,
			        activeTab: 0, 
			        height:$(window).height(),
			        region:'center',
			        defaults :{
			            bodyPadding: 10 
			        }, 
			        items: [{ 
			        	  id:'buttonTab',
			            closable: false, 
			            html:"<iframe src='' height='100%' width='100%' name='buttonframe' id='buttonframe' frameborder='0'></iframe>" 
			        }] 
			    }); 
			   return windowtabs;
		}