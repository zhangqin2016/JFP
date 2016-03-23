var store;
Ext.onReady(function() {
	Ext.define('DataListColumn', {
		extend : 'Ext.data.Model',
		fields : [ {
			name : 'id',
			type : 'string'
		}, {
			name : 'field',
			type : 'string'
		},{
			name : 'is_hidden',
			type : 'boolean'
		}, {
			name : 'title',
			type : 'string'
		}, {
			name : 'is_fuzzySearch',
			type : 'boolean'
		}, {
			name : 'order_index',
			type : 'int'
		} ]
	});
	store = Ext.create('Ext.data.Store', {
		model : 'DataListColumn',
		proxy : {
			type : 'ajax',
			url : ctx + '/zq/dataList/column/gridJson.zq?datalistid='
					+ datalistid,
			reader : {
				type : 'json',
				root : 'root'
			}
		}
	});
	var rowEditing = Ext.create('Ext.grid.plugin.CellEditing', {
		clicksToEdit : 1
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
				handler : add
			}, '-', {
				text : '保存',
				iconCls : 'icon-save',
				handler : save
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
			editor: {
	            allowBlank: false
	        },
			flex : 0.2
		}, {
			text : '字段',
			dataIndex : 'field',
			flex : 0.2
		},  {
			text : '链接',
			dataIndex : 'hotLink',
			flex : 0.2
		}, {
			text : '是否隐藏',
			xtype : 'checkcolumn',
			dataIndex : 'is_hidden',
			flex : 0.3,
			editor : {
				xtype : "checkbox"
			}
		}, {
			text : '是否添加为模糊搜索列',
			xtype : 'checkcolumn',
			dataIndex : 'is_fuzzySearch',
			flex : 0.3,
			editor : {
				xtype : "checkbox"
			}
		}, {
			text : 'order_index',
			dataIndex : 'order_index',
			hidden : true
		} ],
		plugins : [ rowEditing ]
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
	function add() {
		Ext.create('Ext.Window', {
		    title: "添加显示列",
		    width: 600,
		    height:$(window).height(), 
		    plain: true,
		    modal: true,
		    resizable:false,
		    draggable :false,
		    html: "<iframe src='"+ctx+"/zq/dataList/column/getAddPage.zq?dataListId="+datalistid+"' height='100%' width='100%' name='xinzeng' id='xinzeng' frameborder='0'></iframe>"
		}).show();
	}

	function save() {
		Ext.MessageBox.show({
			msg : '正在请求数据, 请稍侯',
			progressText : '正在请求数据',
			width : 300,
			wait : true,
			waitConfig : {
				interval : 10
			}
		});
		var records = store.getModifiedRecords();
		if (records.length == 0) {
			Ext.Msg.alert('系统提示', "没有需要保存的数据!");
			return;
		}
		var json = [];
		Ext.Array.each(records, function(item) {
			json.push(item.data);
		});
		Ext.Ajax.request({
			url : ctx + '/zq/dataList/column/save.zq',
			method : "post",
			params : {
				columns : Ext.JSON.encode(json),
				dataListId:datalistid
				
			},
			success : function(response, opts) {
				Ext.MessageBox.hide();
				if (response.responseText) {
					store.reload();
					Ext.Msg.alert('系统提示', response.responseText);
				} else {
					Ext.Msg.alert("系统提示", '操作失败');
				}
			},
			failure : function() {
				Ext.Msg.alert('系统提示', '系统出错！');
			}
		});
	}
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
			url : ctx + '/zq/dataList/column/delete.zq',
			method : "post",
			params : {
				dataListColumnIds:ids,
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
	function hotLinkRenderer(value,metaData,record, rowIndex,colIndex,store,view){
		if(value==''){
			value="添加链接"
		}
		var a="<a href='#' onclick='addHotLint()'>"+value+"</a>";
		return a;
	}
	
	function addHotLint(){
		  Ext.create('Ext.Window',{ 
			    width: $(window).width(),  
			    autoScroll:true,
			    border:1,
			    height:$(window).height(), 
			    constrain:true,
			    constrainHeader:true,
			    layout: 'border',
			    title: '添加链接',
			    items:[getUItree(),getTab()]
			    
			  }).show(); 
		}
		function getUItree(){
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
			        text: "链接", 
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
			Ext.getCmp("hotLinkTab").setTitle(record.data.text);
			 html: "<iframe src='"+ctx+"/zq/dataList/column/getAddHotLinkPage.zq?datalistid="+ datalistid+"' height='100%' width='100%' name='xinzeng' id='xinzeng' frameborder='0'></iframe>"
		});
			return tree;
		}

		function getTab(){
			   var windowtabs = Ext.createWidget('tabpanel', { 
				   width:( $(window).width())*0.8,
			        activeTab: 0, 
			        id:'hotLinkTab',
			        height:$(window).height(),
			        region:'center',
			        defaults :{ 
			            bodyPadding: 10 
			        }, 
			        items: [{ 
			        	   title:'',
			            closable: false, 
			            html:"<iframe src='"+ctx+"/moxingku/form/linkTable/ui/getFormUiPage.zq?ui_type=UI_"+type+"&id="+id+"' height='100%' width='100%' name='uiframe' id='uiframe' frameborder='0'></iframe>" 
			        }] 
			    }); 
			   return windowtabs;
		}
});