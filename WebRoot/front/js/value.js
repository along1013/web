
$(function(){
	getValueInfo(1);
});

$("#leftValue").click(function(){
	getValueInfo(1);
});

$("#rightValue").click(function(){
	getValueInfo(2);
});

function getValueInfo(pageNo){
	$.ajax({
		url:'./front/getValueInfo',
		data:{'pageNo':pageNo},
		type:'POST',
		success:function(data){
			console.info(data);
			var i=6;
			var htmlStr = "";
			$.each(data,function(name,v){
				i--;
				console.info(v.name);
				htmlStr += "<tr><td>"+v.name+"</td><td>"+v.new_value+"</td><td>"+v.week+"</td><td>"+v.year+"</td><td>"+v.value_time.substr(0,10)+"</td></tr>";
			});
			for(;i>0;i--){
				htmlStr += "<tr><td>&#12288;</td><td>&#12288;</td><td>&#12288;</td><td>&#12288;</td><td>&#12288;</td></tr>";
			}
			$(".value-table tr:not(:eq(0))").remove();
			$(".value-table tr:last").after(htmlStr);
		}
	});
}
