var store;
Ext.onReady(function() {
	// id,ui_length_type,form_er_id,metadata_map_id,ui_name,ui_title,default_value,is_null,is_edit,is_display,ui_type,ui_length,ui_param,ui_html,order_index

	Ext.define('FormUIModel', {
		extend : 'Ext.data.Model',
		fields : [ {
			name : 'id',
			type : 'string'
		}, {
			name : 'ui_title',
			type : 'string'
		}, {
			name : 'ui_name',
			type : 'string'
		} ]
	});
	store = Ext.create('Ext.data.Store', {
		model : 'FormUIModel',
		proxy : {
			type : 'ajax',
			url : ctx + '/zq/dataList/query/gridAddJson.zq?dataListId='+dataListId,
			reader : {
				type : 'json',
				root : 'root'
			}
		}
	});
	var selModel = Ext.create('Ext.selection.CheckboxModel');
	var grid = Ext.create('Ext.grid.Panel', {
		store : store,
		height:$(window).height(),
	    selModel:selModel,
		dockedItems : [ {
			xtype : 'toolbar',
			dock : 'top',
			items : [ {
				text : '添加查询列',
				iconCls : 'icon-save',
				handler : add
			} ]
		} ],
	/*	viewConfig : {
			plugins : {
				ptype : 'gridviewdragdrop',
				enableDrop : true
			}
		},*/
		columns : [ Ext.create('Ext.grid.RowNumberer', {
			text : '序号',
			width : 50,
			align : 'center'
		}), {
			text : 'id',
			dataIndex : 'id',
			hidden : true
		}, {
			text : '字段',
			dataIndex : 'ui_name',
			flex : 0.5
		}, {
			text : '标题',
			dataIndex : 'ui_title',
			flex : 0.5
		}]

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
	function add(){
		  var selModel = grid.getSelectionModel();
		    if (selModel.hasSelection()) {
		                var selected = selModel.getSelection();
		                var object=new Object();
		                var obs=[];
		                Ext.each(selected, function (item) {
		                	object=item.data;
		                	obs.push(object);
		                });
		                $.post(ctx+"/zq/dataList/query/add.zq",{dataListId:dataListId,querys:Ext.JSON.encode(obs)},function(data){
		                	alert(data);
		                	parent.store.reload();
		                })
		    }
		    else {
		        Ext.Msg.alert("错误", "没有选择数据，无法进行添加操作！");
		    }
	}
});



