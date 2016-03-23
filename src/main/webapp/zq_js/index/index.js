/***
 * 
 */


$(function($){
	
	windowResize();
	pifuChange();
	tabChange();
	//图片显示
	//var dingshi=setInterval(chat, "3000");
	$("#beautifulGirl").hover(
	 function () {
	//	clearInterval(dingshi);//
		 },
	 function () {
	//  dingshi=setInterval(chat, "3000");
		}
	);
	$("#outSystem").click(function(){
		outSystem();
	});
	$("#liaoId").click(function(){
		window.open(ctx+'/chajian/chat','_blank')
	});
	

});
function outSystem(){
	  if(confirm("确定要退出系统吗?")){   
	    	$.post(ctx+"/console/logout.zq",
					   function(data){
						alert("退出成功!");
							window.open(data,'_self');
					   });
	    };
}
/**
 * 初始化窗口高度01 (1).jpg
 */
function chat(){
	$("img[name='avls']").eq(0).attr("src",ctx+"/guzhuang2/0 ("+Math.floor(Math.random()*55)+").jpg");
	$("img[name='avls']").eq(1).attr("src",ctx+"/guzhuang2/0 ("+Math.floor(Math.random()*55)+").jpg");
	$("img[name='avls']").eq(2).attr("src",ctx+"/guzhuang2/0 ("+Math.floor(Math.random()*55)+").jpg");
	$("img[name='avls']").eq(3).attr("src",ctx+"/guzhuang2/0 ("+Math.floor(Math.random()*55)+").jpg");
	$("img[name='avls']").eq(4).attr("src",ctx+"/guzhuang2/0 ("+Math.floor(Math.random()*55)+").jpg");
}
function windowResize() {
	var height = jQuery(window).height() -$("#beautifulGirl").height()-$(".nav.nav-tabs").height()-5;
	$('#consoleContent').height(height);
}
/**
 * 皮肤切换
 */
function pifuChange(){
	$("body").click(function(){
		$(".dropdown-menu").hide();
	});
	$(".dropdown-toggle").bind("click",function(event){
		event.stopPropagation(); 
		if($(".dropdown-menu").is(":visible")){
			$(".dropdown-menu").hide();
		}else{
			$(".dropdown-menu").show();
		}
	});
	$(".dropdown-menu > li").bind("click",function(event){
		//$("#avls").attr("src",""+ctx+"/zq_img/index/av/"+$(this).attr("img")+"");
		for(var i=0;i<5;i++){
			var s1=$("img[name='avls']").eq(Math.floor(Math.random()*5));
			var s2=$("img[name='avls']").eq(Math.floor(Math.random()*5));
			var old=s1.attr("src");
			s1.attr("src",s2.attr("src"));   
			s2.attr("src",old);   
		}
		$(".row").css("background-color",$(this).attr("col"));
	});
}
/**
 * 切换tab页
 */
function tabChange(){
	$("li[name='caidan']").bind("click",function(event){
		event.stopPropagation(); 
		$("li[name='caidan']").attr("class","");
		$(this).attr("class","active");
		$("#consoleContent").attr("src",ctx+$(this).attr("url"));
	});
}
/***
**图片放大镜
*/

$(function(){ 

	$("img[name='avls']").mousemove(function(event){
		$("#preview-img").attr("src",$(this).attr("src"));
	$(".preview-box").show(); 
$("#preview-img").height(400);
$("#preview-img").width($("#preview-img").width()/($("#preview-img").height()/400));
	if($(this).attr("type")=="left"){
		$("#preview-img").css('left',mousePos(event).x+'px'); 
	}else{
	$("#preview-img").css('left',mousePos(event).x+'px'); 
	}
	$("#preview-img").css('top',mousePos(event).y+'px'); 
	}); 
	$("img[name='avls']").mouseout(function(){ 
	$(".preview-box").hide(); 
	}); 
	function mousePos(event){
		var event = event||window.event;
		return {
		x:event.clientX+document.body.scrollLeft+document.documentElement.scrollLeft,
		y:event.clientY+document.body.scrollTop+document.documentElement.scrollTop-200
		};
		};
	}); 
