$(function($){
	initPeople();
	$("#sendBtn").click(function(){
		edit.sync();
		saveMsg();
	});
	getMsg();
});
function getMsg(){
	$.ajax({
		   type: "POST",
		   url: "/zq/liao/getMsg.zq",
		   data: "timeout=5",//秒数
		   dataType:'json',
		   timeout:20000,
		   success: function(msg){
			   if(msg=="1"){
				   getMsg();
			   }else{
				   $(".chat01_content").html("");
			   $.each(msg, function(i, item) {
					$(".chat01_content").append('<div  class="message_box mes3" style="display: block;"><div class="message clearfix"><div class="user-logo"><img src="img/head/2024.jpg"/></div><div class="wrap-text"><h5 class="clearfix">'+item.user_name+'</h5><div>'+item.user_msg+'</div></div><div class="wrap-ri"><div class="clearfix"><span>'+item.send_date+'</span></div></div><div style="clear: both;"></div></div></div>');
				});
			   $(".chat01_content").scrollTop($(".message_box.mes3").size()*$(".message_box.mes3").height()); 
			   getMsg();
			   }
		   }
		});
}
function saveMsg(){
	var msg=edit.html();
	$.ajax({
		   type: "POST",
		   url: "/zq/liao/saveMsg.zq",
		   data: "msg="+msg,
		   success: function(msg){
			   edit.html("");
		   }
		});
}
function initPeople(){
	$.post(ctx+"/zq/liao/getUsers.zq?"+ Date.parse(new Date()), 
			   function(data){
					$.each(JSON.parse(data), function(i, item) {
					var obj='<li><label class="online"> </label> <a href="javascript:;"><img src="img/head/2013.jpg"></a><a href="javascript:;" class="chat03_name">'+item.user_name+'</a><input type="hidden" value="'+item.user_account+'" /></li>';
					$("#user").append(obj);
		})
});
}

