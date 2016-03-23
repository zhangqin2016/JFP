$(function($){
	$("#ulTabId > li").click(function(){
		window.location.href=$(this).attr("url");
	});
	getj(ctx+"/web/exe/submitUser/json.zq",{sftg:'否'});
	$("#qx").bind("click",function(){
		$.each($("input[name='userCheck']"), function(i, item) {
			if($("#qx").is(":checked")){
				item.checked=true;
			}else{
				item.checked=false;
			}
		});
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
		$.post(ctx+"/web/exe/submitUser/delJson.zq",{ids:ids.join(","),sftg:'否'},function(data){
			var ss=$.parseJSON(data);
			each("",ss);
			alert("操作成功!");
		});
	});
	$("#tg").click(function(){
		var ids=[];
		var cs=$("input[name='userCheck']:checked");
		if(cs.length==0){
			alert("至少选择一条记录!");
			return ;
		}
		$.each( cs, function(i, n){
			ids.push($(n).attr("uid"));
			});
		$.post(ctx+"/web/exe/submitUser/tgJson.zq",{ids:ids.join(","),sftg:'否'},function(data){
			var ss=$.parseJSON(data);
			each("",ss);
			alert("操作成功!");
		});
	});
	function getj(url,param){
		$.getJSON(url,param,function(data){
			each("",data);
		});
	}
	function each(str,data){
			$.each(data, function(i, item) {
				if(!item.dh){
					item.dh='';
				}
				str+=' <tr><td><input uid="'+item.id+'" name="userCheck" type="checkbox"  /></td><th scope="row">'+(i+1)+'</th> <td style="display:none" id="id">'+item.id+'</td> <td>'+item.xm+'</td> <td>'+item.yhm+'</td> <td>'+item.dh+'</td><td>'+item.sptgsq+'</td> </tr>';
			});
	$("#submitUserId").find("tbody").html(str);
	}

});