$(function($){
	
	$("form").submit( function () {
		if($("#yhm").val()==""){
			$("#showmsg").html("	<h3>帐户不允许为空!</h3>");
			return false;
		}
		if($("#xm").val()==""){
			$("#showmsg").html("	<h3>姓名不允许为空!</h3>");
			return false;
		}
		if($("#mm").val()==""){
			$("#showmsg").html("	<h3>密码不允许为空!</h3>");
			return false;
		}
		if($("#dh").val()==""){
			$("#showmsg").html("	<h3>电话不允许为空!</h3>");
			return false;
		}
		if($("#mm").val()!=$("#password2").val()){
			$("#showmsg").html("	<h3>密码跟确认密码不一致!</h3>");
			return false;
		}
		  return true;
		});
});