$(function($){
	$("#save").click(function(){
		$.post(ctx+"/web/exe/savePeopleInfo.zq",{xm:$("#xm").val(),dh:$("#dh").val(),yx:$("#yx").val(),id:$("#id").val()},function(d){
			alert(d);
			window.location.replace(window.location);
		});
	});

});