Ext.onReady(function() {
    var tabs = Ext.widget('tabpanel', {
        width: '100%',
        activeTab: 0,
        tabPosition:"bottom",
        defaults: {
            bodyPadding: 10
        },
        items: [{
            width: '100%',
            title: '基本信息',
            id:'base',
            html: "<iframe src='"+ctx+"/zq/dataList/getDataListInfoPage.zq?datalistid="+datalistid+"' height='100%' width='100%' name='baseTab' id='baseTab' frameborder='0'></iframe>"
        },{
            width: '100%',
            title: '显示列',
            id:'column',
            html: "<iframe src='"+ctx+"/zq/dataList/column.zq?datalistid="+datalistid+"' height='100%' width='100%' name='columnTab' id='columnTab' frameborder='0'></iframe>"
        },{
            width: '100%',
            title: '按钮',
            id:'button',
            html: "<iframe src='"+ctx+"/zq/dataList/botton.zq?datalistid="+datalistid+"' height='100%' width='100%' name='buttonTab' id='buttonTab' frameborder='0'></iframe>"
        },{
            width: '100%',
            title: '查询列',
            id:'query',
            html: "<iframe src='"+ctx+"/zq/dataList/query/listPage.zq?datalistid="+datalistid+"' height='100%' width='100%' name='queryTab' id='queryTab' frameborder='0'></iframe>"
        },{
            width: '100%',
            title: '事件',
            id:'event',
            html: "<iframe src='' height='100%' width='100%' name='eventTab' id='eventTab' frameborder='0'></iframe>"
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
