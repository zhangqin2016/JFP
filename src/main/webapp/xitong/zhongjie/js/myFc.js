$(function($){
	$("#qx").bind("click",function(){
		$.each($("input[name='userCheck']"), function(i, item) {
			if($("#qx").is(":checked")){
				item.checked=true;
			}else{
				item.checked=false;
			}
		});
	});
	$("#add").click(function(){
		window.open(ctx+"/web/exe/addFangchan.zq",'_self');
	});
	$("#del").click(function(){
		var ids=[];
		var cs=$("input[name='userCheck']:checked");
		if(cs.length==0){
			alert("至少选择一条记录!");
			return ;
		}
		$.each( cs, function(i, n){
			ids.push($(n).attr("uid"));
			});
		$.post(ctx+"/web/exe/delMyFcJson.zq",{ids:ids.join(",")},function(data){
		
			var ss=$.parseJSON(data);
			var tr="";
			$.each(ss, function(i, item) {
				 tr+=' <tr><td><input name="userCheck" type="checkbox" uid="'+item.id+'" /></td><th scope="row">'+(i+1)+'</th> <td style="display:none" id="id">'+item.id+'</td> <td>'+item.xm+'</td><td>'+item.dh+'</td><td>'+item.fcdz+'</td>    <td><a href="#" name="edit" uid="'+item.bind_id+'"> <span class="glyphicon glyphicon-search"></span></a></td></tr>';
			});
			$("#submitUserId").find("tbody").html(tr);
			alert("操作成功!");
		
		});
	});
	$.getJSON(ctx+"/web/exe/myFcJson.zq",function(data){
		var tr="";
		$.each(data, function(i, item) {
			 tr+=' <tr><td><input name="userCheck" type="checkbox" uid="'+item.id+'" /></td><th scope="row">'+(i+1)+'</th> <td style="display:none" id="id">'+item.id+'</td> <td>'+item.xm+'</td><td>'+item.dh+'</td><td>'+item.fcdz+'</td>    <td><a  href="#"  name="edit" uuid="'+item.bind_id+'"> <span class="glyphicon glyphicon-search"></span></a></td></tr>';
		});
		$("#submitUserId").find("tbody").html(tr);
		$("a[name='edit']").bind("click",function(){
			var id=$(this).attr("uuid");
			window.open(ctx+"/web/exe/selectFangchan.zq?uuid="+id,"_self");
		});
	});
	
	
});