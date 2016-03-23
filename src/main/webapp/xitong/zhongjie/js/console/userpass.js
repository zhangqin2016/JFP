$(function($){
	$.getJSON(ctx+"/web/exe/submitUser/json.zq",{sftg:'是'},function(data){
		var tr="";
		$.each(data, function(i, item) {
			if(!item.dh){
				item.dh='';
			}
			 tr+=' <tr><td><input name="userCheck" type="checkbox" /></td><th scope="row">'+(i+1)+'</th> <td style="display:none" id="id">'+item.id+'</td> <td>'+item.xm+'</td><td>'+item.yhm+'</td>  <td>'+item.dh+'</td><td>'+item.sptgsq+'</td> </tr>';
		});
		$("#submitUserId").find("tbody").html(tr);
	});
	$("#qx").bind("click",function(){
		$.each($("input[name='userCheck']"), function(i, item) {
			if($("#qx").is(":checked")){
				item.checked=true;
			}else{
				item.checked=false;
			}
		});
	});
	$("#ulTabId > li").click(function(){
		window.location.href=$(this).attr("url");
	});
});