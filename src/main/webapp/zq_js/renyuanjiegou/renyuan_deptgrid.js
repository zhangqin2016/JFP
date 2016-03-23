var store;
Ext.onReady(function() {
    Ext.define('deptModel', {
        extend: 'Ext.data.Model',
        fields: [{
            name: 'deptName',
            type: 'string'
        },
        {
            name: 'id',
            type: 'string'
        },
        {
            name: 'orderIndex',
            type: 'int'
        }]
    });
    store = Ext.create('Ext.data.Store', {
        model: 'deptModel',
        proxy: {
            type: 'ajax',
            url: ctx + '/portal/org/dept/gridJson.zq?type=' + type + '&id=' + id,
            reader: {
                type: 'json',
                root: 'root'
            }
        }
    });
    var selModel = Ext.create('Ext.selection.CheckboxModel', {
        listeners: {
            selectionchange: function(sm, selections) {
                grid.down('#removeButton').setDisabled(selections.length === 0);
            }
        }
    });
    var menua = Ext.create('Ext.menu.Menu', {
        floating: true,
        // usually you want this set to True
        // (default)
        width: 50,
        items: [{
            text: '新增人员',
            iconCls: '',
            handler:function(){
            	if(type=='comp'){
            		alert("公司下不能创建人员！")
            	}else	add('user');
            } 
            	
        },
        {
            text: '新增部门',
            iconCls: '',
            handler: function(){
            	add('dept');
            }
        }]
    });
    var menud = Ext.create('Ext.menu.Menu', {
        floating: true,
        // usually you want this set to True
        items: [{
            text: '删除人员',
            iconCls: '',
            handler: function() {
                del('user');
            }
        },
        {
            text: '删除部门',
            iconCls: '',
            handler: function() {
                del('dept');
            }
        }]
    });
    var grid = Ext.create('Ext.grid.Panel', {
        store: store,
        draggable: true,
        selModel: selModel,
        dockedItems: [{
            xtype: 'toolbar',
            dock: 'top',
            items: [{
                text: '新增',
                iconCls: 'bmenu',
                // <-- icon
                menu: menua
                // assign menu by instance
            },
            '-', {
                text: '删除',
                iconCls: 'bmenu',
                id:"removeButton",
                disabled: true,
                // <-- icon
                menu: menud
                // assign menu by instance
            }]
        }],
     /*   viewConfig: {
            plugins: {
                ptype: 'gridviewdragdrop',
                enableDrop: true
            },
        },    */  
        columns: [  Ext.create('Ext.grid.RowNumberer',{
      	  text:'序号',
    	  width:50,
    	  align:'center'
      }), {
            text: 'id',
            dataIndex: 'id',
            hidden: true
        },
        {
            text: '名称',
            dataIndex: 'deptName',
            renderer:rendererDept,
            flex: 1
        },
        {
            text: 'orderIndex',
            dataIndex: 'orderIndex',
            hidden: true
        }]
    });

    var viewport = Ext.create('Ext.Viewport', {
        layout: {
            type: 'border',
            padding: 1
        },
        items: [{
            region: 'center',
            items: [grid]
        }]
    });
    store.load();
    grid.setHeight(viewport.getHeight());
    grid.setWidth(viewport.getWidth());
    function add(o) {
    	var src="";
    	var title="";
    	if(o=='dept'){
    		src=ctx + "/portal/org/dept/addPage.zq?type=" + type + "&id=" + id;
    		title="新增部门"
    			 parent.createViewTab("bumenrenyuan", title, "", src, false, null);
    	}else{
    		src=ctx + "/portal/org/user/addPage.zq?type=" + type + "&id=" + id;
    		title="新增人员"
    			 parent.createViewTab("bumen", title, "", src, false, null);
    	}
    	 
    }
    function del(o) {
        var selModel = grid.getSelectionModel();
        if (selModel.hasSelection()) {
            Ext.Msg.confirm("警告", "确定要删除吗？", function(button) {
                if (button == "yes") {
                    var selected = selModel.getSelection();
                    var idsUser = []; // 要删除的id
                    var idsDept = []; // 要删除的id
                    Ext.each(selected, function(item) {
                    	if(item.data.id.indexOf("user_")!=-1){
                    		idsUser.push(item.data.id.replace('user_',''));
                    	}else{
                    		idsDept.push(item.data.id);
                    	}
                    });
                    if(o=='user'){
                    	 dodel(idsUser,o);
                    }else{
                    	 dodel(idsDept,o);
                    }
                   
                }
            });
        } else {
            Ext.Msg.alert("错误", "没有任何行被选中，无法进行删除操作！");
        }

    }
    function dodel(ids,o) {
        Ext.MessageBox.show({
            msg: '正在请求数据, 请稍侯',
            progressText: '正在请求数据',
            width: 300,
            wait: true,
            waitConfig: {
                interval: 10
            }
        });
        Ext.Ajax.request({
            url: ctx + '/portal/org/dept/delDept.zq',
            method: "post",
            params: {
                ids: ids,
                leixing:o
            },
            success: function(response, opts) {
                Ext.MessageBox.hide();
                if (response.responseText) {
                    store.reload();
                    parent.regreshNodeById(id);
                    Ext.Msg.alert('系统提示', response.responseText);
                } else {
                    Ext.Msg.alert("系统提示", '删除失败');
                }
            },
            failure: function() {
                Ext.Msg.alert('系统提示', '系统出错！');
            }
        });
    }
function rendererDept(value,metaData,record, rowIndex,colIndex,store,view){
	var img="";
	if(record.data.id.indexOf("user_")!=-1){
		img="<img src="+ctx+"/zq_img/zq_org/user.png />";
	}else{
		img="<img src="+ctx+"/zq_img/zq_org/dept.png />";
	}
	var a="<a href='#' onclick='openEditWindow(\""+record.data.id+"\")'>"+img+"&nbsp;&nbsp;&nbsp;&nbsp;"+value+"</a>";
	return a;
}

});
function openEditWindow(id){
	if(id.indexOf("user_")!=-1){
		src=ctx + "/portal/org/user/editPage.zq?type=" + type + "&id=" + id.replace("user_","")+"&"+Date.parse(new Date());
		title="编辑人员"
			 parent.createViewTab("edit_user", title, "", src, true, null);
	}else{
		src=ctx + "/portal/org/dept/editPage.zq?type=" + type + "&id=" + id;
		title="编辑部门"
 parent.createViewTab("edit_dept", title, "", src, true, null);
	}

}
/*var extWindow=function(src,title){
	var win=Ext.create('Ext.Window', {
    title: title,
    width: 600,
    height: 400,
    plain: true,
    modal: true,
    html: "<iframe src='"+src+"' height='100%' width='100%' name='xinzeng' id='xinzeng' frameborder='0'></iframe>"
});
return 	win;
}*/