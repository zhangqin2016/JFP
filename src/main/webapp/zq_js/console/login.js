
$(function($){
/**
 * 登录页面设置
 */
    $('.rem').click(function(){
        $(this).toggleClass('selected');
    })

    $('#signup_select').click(function(){
        $('.form_row ul').show();
        event.cancelBubble = true;
    })

    $('#d').click(function(){
        $('.form_row ul').toggle();
        event.cancelBubble = true;
    })

    $('body').click(function(){
        $('.form_row ul').hide();
    })

    $('.form_row li').click(function(){
        var v  = $(this).text();
        $('#signup_select').val(v);
        $('.form_row ul').hide();
    })
//结束
    $("#user_account").focus();
 $("#user_password").keyup(function(event){     
    	if(event.keyCode == 13){     
    		 $(".login-btn").click();
    		} 
    	});
    $(".login-btn").bind("click",function(){
    	var user_account=$("#user_account").val();
    	var user_password=$("#user_password").val();
    	if(user_account==""){
    		$("#user_account").focus();
    		alert("账户不能为空!");
    		return false;
    	}
    	if(user_password==""){
    		alert("密码不能为空!");
    		$("#user_password").focus();
    		return false ;
    		
    	}
    	$.post(ctx+"/console/login.zq", 
				{ user_account:user_account,user_password:user_password },
				   function(data){
					if(data=='-1'){
						   alert("账户或者密码错误!");
					}else{
						window.open(data,'_self');
					}
				   });
    });
})
