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
		}, {
			name : 'is_display',
			type : 'string'
		}, {
			name : 'default_value',
			type : 'string'
		}, {
			name : 'is_null',
			type : 'boolean'
		}, {
			name : 'is_edit',
			type : 'boolean'
		}, {
			name : 'ui_type',
			type : 'string'
		}, {
			name : 'order_index',
			type : 'int'
		} ]
	});
	store = Ext.create('Ext.data.Store', {
		model : 'FormUIModel',
		proxy : {
			type : 'ajax',
			url : ctx + '/moxingku/form/linkTable/ui/gridJson.zq?form_er_id='
					+ form_er_id,
			reader : {
				type : 'json',
				root : 'root'
			}
		}
	});
	var rowEditing = Ext.create('Ext.grid.plugin.CellEditing', {
		clicksToEdit : 1
	});
	var grid = Ext.create('Ext.grid.Panel', {
		store : store,
		height:$(window).height(),
		dockedItems : [ {
			xtype : 'toolbar',
			dock : 'top',
			items : [ {
				text : '保存',
				iconCls : 'icon-save',
				handler : save
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
			text : 'ui标题',
			dataIndex : 'ui_title',
			flex : 0.2,
			editor : {
				allowBlank : false
			}
		}, {
			text : 'ui名称',
			dataIndex : 'ui_name',
			flex : 0.2
		}, {
			text : '默认值',
			dataIndex : 'default_value',
			flex : 0.3,
			editor : {}
		}, {
			text : '是否允许编辑',
			xtype : 'checkcolumn',
			dataIndex : 'is_edit',
			flex : 0.2,
			editor : {
				xtype : "checkbox"
			}
		}, {
			text : '是否允许为空',
			xtype : 'checkcolumn',
			dataIndex : 'is_null',
			flex : 0.2,
			editor : {
				xtype : "checkbox"
			}
		}, {
			text : '是否显示',
			dataIndex : 'is_display',
			flex : 0.2,
			editor : {
				xtype : "combobox",
				editable : false,
				store : Ext.create('Ext.data.Store', {
					fields : [ 'type' ],
					data : [ {
						"type" : "显示"
					}, {
						"type" : "不显示"
					}, {
						"type" : "隐藏"
					} ]
				}),
				queryMode : 'local',
				displayField : 'type',
				valueField : 'type'
			}
		}, {
			text : 'ui类型',
			dataIndex : 'ui_type',
			renderer:uiRenderer,
			flex : 0.2
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
	grid.on('edit', function(editor, e) {
		if (e.field == 'ui_title') {
			e.record.set("ui_name", makePy(e.value)[0].toLowerCase());
		}
		// commit the changes right after editing finished
	});

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
			url : ctx + '/moxingku/form/linkTable/ui/save.zq',
			method : "post",
			params : {
				datas : Ext.JSON.encode(json)
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
});


function uiRenderer(value,metaData,record, rowIndex,colIndex,store,view){
	
	var a="<a href='#' onclick='openUiChooseWindow(\""+record.data.ui_type+"\",\""+record.data.id+"\",\""+record.data.ui_title+"["+record.data.ui_name+"]\")'>"+value+"</a>";
	return a;
}
function openUiChooseWindow(type,id,title){
	var win = Ext.create('Ext.Window',{ 
	    width: $(window).width(),  
	    autoScroll:true,
	    border:1,
	    height:$(window).height(), 
	    constrain:true,
	    constrainHeader:true,
	    layout: 'border',
	    title: '',
	    items:[getUItree(type,id),getTab(type,id)]
	    
	  }); 
	  win.show(); 
}
function getUItree(type,id){
	var treestore = Ext.create('Ext.data.TreeStore', {
	    root: {
	        expanded: true,
	        children: [
	            { text: "文本", leaf: true},
	            { text: "多行", leaf:true},
	            { text: "单选按钮", leaf:true},
	            { text: "日期", leaf:true},
	            { text: "下拉框", leaf:true},
	            { text: "复选框", leaf:true},
	            { text: "html", leaf:true},
	            { text: "附件", leaf:true}
	        ]
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
	Ext.getCmp("uiTab").setTitle(record.data.text);
	$("#uiframe").attr("src",ctx+"/moxingku/form/linkTable/ui/getFormUiPage.zq?id="+id+"&ui_type=UI_"+record.data.text);

});
	return tree;
}

function getTab(type,id){
	   var windowtabs = Ext.createWidget('tabpanel', { 
		   width:( $(window).width())*0.8,
	        activeTab: 0, 
	        height:$(window).height(),
	        region:'center',
	        defaults :{ 
	            bodyPadding: 10 
	        }, 
	        items: [{ 
	        	   title:type,
	        	    id:'uiTab',
	            closable: false, 
	            html:"<iframe src='"+ctx+"/moxingku/form/linkTable/ui/getFormUiPage.zq?id="+id+"&ui_type=UI_"+type+"' height='100%' width='100%' name='uiframe' id='uiframe' frameborder='0'></iframe>" 
	        }] 
	    }); 
	   return windowtabs;
}
