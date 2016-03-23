var store;
Ext.onReady(function() {
	Ext.define('DataListQuery', {
		extend : 'Ext.data.Model',
		fields : [ {
			name : 'id',
			type : 'string'
		}, {
			name : 'fieldName',
			type : 'string'
		},{
			name : 'title',
			type : 'string'
		}, {
			name : 'where',
			type : 'string'
		}, {
			name : 'uiType',
			type : 'string'
		}, {
			name : 'is_main',
			type : 'boolean'
		}, {
			name : 'order_index',
			type : 'int'
		} ]
	});
	store = Ext.create('Ext.data.Store', {
		model : 'DataListQuery',
		proxy : {
			type : 'ajax',
			url : ctx + '/zq/dataList/query/gridJson.zq?datalistid='
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
			text : '查询字段名',                               
			dataIndex : 'fieldName',                      
			flex : 0.2                                   	
		}, {                                             	
			text : '字段标题',                                
			dataIndex : 'title',                         	
			flex : 0.2                                   	
		},  {                                            	
			text : '字段类型',                                
			dataIndex : 'uiType',                       	
			flex : 0.2                                   	
		}, {                                             		
			text : '是否是主表',
			xtype : 'checkcolumn',
			dataIndex : 'is_main'
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
		    title: "添加查询列",
		    width: 600,
		    height:$(window).height(), 
		    plain: true,
		    modal: true,
		    resizable:false,
		    draggable :false,
		    html: "<iframe src='"+ctx+"/zq/dataList/query/getAddPage.zq?dataListId="+datalistid+"' height='100%' width='100%' name='xinzeng' id='xinzeng' frameborder='0'></iframe>"
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
			url : ctx + '/zq/dataList/query/save.zq',
			method : "post",
			params : {
				querys : Ext.JSON.encode(json),
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
			url : ctx + '/zq/dataList/query/delete.zq',
			method : "post",
			params : {
				dataListQueryIds:ids,
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