$(function($){
	$("#consoleContent").height($(window).height()-$("#nav").height()-70-$("#container").height())
	$("#regist").click(function(){
		window.open(ctx+"/web/regist.zq","_self");
		//$("#consoleContent").attr("src",ctx+"/web/regist.zq");
	});
	$("#toLogin").click(function(){
		window.open(ctx+"/web/login.zq","_self");
//		$("#consoleContent").attr("src",ctx+"/web/login.zq");
	});
	$("#shouye").click(function(){
		window.open(ctx+"/web/main.zq","_self");
		//$("#consoleContent").attr("src",ctx+"/web/main.zq");
	});
	$("#yzxx").click(function(){
		window.open(ctx+"/web/exe/yezhu.zq","_self");
		//$("#consoleContent").attr("src",ctx+"/web/exe/yezhu.zq");
	});
	$("#logOut").click(function(){
		  if(confirm("确定要退出系统吗?")){   
		    	$.post(ctx+"/web/exe/out.zq",
						   function(data){
							alert("退出成功!");
								window.open(data,'_self');
						   });
		    };
	});
	$("#peopleInfo").click(function(){
		window.open(ctx+"/web/exe/peopleInfo.zq",'_self');
	});
	
	$("#addFc").click(function(){
		window.open(ctx+"/web/exe/addFangchan.zq",'_self');
	});
	$("#myFc").click(function(){
		window.open(ctx+"/web/exe/myFc.zq",'_self');
	});
});