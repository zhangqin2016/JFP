$(function($){
	$("#sub").click(function(){
		var yhm=$("#yhm").val();
		var mm=$("#mm").val();
		if(yhm.length==0){
			$("#showmsg").html("<h1>用户名不允许为空!</h1>");
			return;
		}else if(mm.length==0){
			$("#showmsg").html("<h1>密码不允许为空!</h1>");
			return;
		}
		$.post(ctx+"/web/loginUser.zq",{yhm:yhm,mm:mm},function(data){
			var obj=jQuery.parseJSON(data);
			if(obj.zhuangtai){
				$("#showmsg").html("<h1>"+obj.zhuangtai+"</h1>");
			}else{
window.open(obj.url,"_self");
				//parent.location.href=obj.url;
				
			}
		
		});
	});

});