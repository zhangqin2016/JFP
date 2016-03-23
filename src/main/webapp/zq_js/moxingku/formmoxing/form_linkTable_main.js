Ext.onReady(function() {
    var tabs = Ext.widget('tabpanel', {
        width: '100%',
        activeTab: 1,
        tabPosition:"bottom",
        defaults: {
            bodyPadding: 10
        },
        items: [{
            width: '100%',
            title: '基本信息',
            id:'base',
            html: "<iframe src='"+ctx+"/moxingku/form/linkTable/base.zq?form_id="+form_id+"' height='100%' width='100%' name='baseHtml' id='baseHtml' frameborder='0'></iframe>"
        },{
            width: '100%',
            title: '绑定的数据表',
            id:'editTable',
            html: "<iframe src='"+ctx+"/moxingku/form/linkTable/er/editTable.zq?form_id="+form_id+"' height='100%' width='100%' name='linkTableHtml' id='linkTableHtml' frameborder='0'></iframe>"
        },{
            width: '100%',
            title: '编辑表单',
            id:'editForm',
            html: "<iframe src='"+ctx+"/moxingku/form/linkTable/ui/editFormHtml.zq?form_id="+form_id+"' height='100%' width='100%' name='editFormHtml' id='editFormHtml' frameborder='0'></iframe>"
        }]
    });
    var viewport = Ext.create('Ext.Viewport', {
        layout: {
            type: 'border',
            padding: 1
        },
        items: [ 
        {
            region: 'center',
            items: [tabs]
        }]
    });
    tabs.setHeight(viewport.getHeight());
});
