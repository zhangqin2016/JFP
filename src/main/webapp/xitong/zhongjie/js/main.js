$(function($){
	$.getJSON(ctx+"/web/allFcJson.zq",function(data){
		var li="";
		$.each(data, function(i, item) {
			li+=' <a href="#" class="list-group-item"><h3><ul class="list-inline"><li>'+item.xm+'</li> <li>'+item.dh+'</li> <li>'+item.fcdz+'</li> </ul> </h3> </a>';
		});
		$("#listId").html(li);
		$("a[name='edit']").bind("click",function(){
			var id=$(this).attr("uuid");
			window.open(ctx+"/web/exe/selectFangchan.zq?uuid="+id,"_self");
		});
	});
	
	
});