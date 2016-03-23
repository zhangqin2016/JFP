$(function($){
	$("#ulTabId > li").click(function(){
		window.location.href=$(this).attr("url");
	});
	$.getJSON(ctx+"/web/exe/owen/yezhuJson.zq",function(data){
		var tr="";
		$.each(data, function(i, item) {
			if(!item.dh){
				item.dh='';
			}
			 tr+=' <tr><td><input name="userCheck" type="checkbox" /></td><th scope="row">'+(i+1)+'</th> <td style="display:none" id="id">'+item.id+'</td> <td>'+item.xm+'</td><td>'+item.yhm+'</td>  <td>'+item.dh+'</td>  <td><a href="#"> <span class="glyphicon glyphicon-search"></span></a></td></tr>';
		});
		$("#submitUserId").find("tbody").html(tr);
	});
});