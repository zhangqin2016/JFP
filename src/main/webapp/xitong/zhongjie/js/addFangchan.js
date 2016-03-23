$(function($){
	$("#selSpkgm").val($("#spkgm").val());
	$("#save").click(function(){
		if($("#xm").val().trim().length==0){
			$("#showmsg").html("<h1>联系人不允许为空!</h1>");
			return false;
		}
		if($("#dh").val().trim().length!=11){
			$("#showmsg").html("<h1>电话得为11位!</h1>");
			return false;
		}
		if($("#fcdz").val().trim().length==0){
			$("#showmsg").html("<h1>房产地址不允许为空!</h1>");
			return false;
		}
		$("#spkgm").val($("#selSpkgm").val());
		$.post(ctx+"/web/exe/saveFangchan.zq",
				{
			    id:$("#id").val(),
			    xm:$("#xm").val(),
			    dh:$("#dh").val(),
			    sfzh:$("#sfzh").val(),
			    sfzzp:$("#sfzzp").val(),
			    fcdz:$("#fcdz").val(),
			    fczzp:$("#fczzp").val(),
			    qtfj:$("#qtfj").val(),
			    yx:$("#yx").val(),
			    spkgm:$("#spkgm").val(),
			    yx:$("#yx").val(),
			    yx:$("#yx").val()
				},function(data){
					alert(data);
					window.location.replace(window.location);
				});
	});
	/*$("#xm").change(function() {
        // 上传方法
        $.upload({
                // 上传地址
                url: ctx+'/web/exe/fileServer/upload.zq',
                // 文件域名字
                fileName: 'sfzzp_f',
                // 其他表单数据
                params: {name: 'pxblog'},
                // 上传完成后, 返回json, text
                dataType: 'json',
                // 上传之前回调,return true表示可继续上传
                onSend: function() {
                        return true;
                },
                // 上传之后回调
                onComplate: function(data) {
                        alert(data);
                }
        });
	});
	$("#fczzp_f").change(function() {
		  $.ajaxFileUpload
          (
              {
                  url: '/web/exe/fileServer/upload.zq', //用于文件上传的服务器端请求地址
                  secureuri: false, //是否需要安全协议，一般设置为false
                  fileElementId: 'sfzzp_f', //文件上传域的ID
                  dataType: 'json', //返回值类型 一般设置为json
                  success: function (data, status)  //服务器成功响应处理函数
                  {
                  },
                  error: function (data, status, e)//服务器响应失败处理函数f                                                                                                                                                                                                                              
                  {
                      alert(e);
                  }
              }
          )
	});
	$("#qtfj_f").change(function() {
        // 上传方法
        $.upload({
                // 上传地址
                url: '/web/exe/fileServer/upload.zq',
                // 文件域名字
                fileName: 'qtfj_f',
                // 其他表单数据
                params: {name: 'pxblog'},
                // 上传完成后, 返回json, text
                dataType: 'json',
                // 上传之前回调,return true表示可继续上传
                onSend: function() {
                        return true;
                },
                // 上传之后回调
                onComplate: function(data) {
                        alert(data);
                }
        });
	});*/
});