
$(function(){
	initNav();
});

function initNav(){
	var nav = getUrl();
	if(nav=="home"){
		$(".nav-web").find(".hoverdiv").eq(0).css("background-color","#D1B862");
		$(".nav-web").find(".hoverdiv").eq(0).find("p").css("color","#FFFFFF");
		$(".nav-web").find(".hoverdiv").eq(0).addClass("act");
	}
	if(nav=="value"){
		$(".nav-web").find(".hoverdiv").eq(1).css("background-color","#D1B862");
		$(".nav-web").find(".hoverdiv").eq(1).find("p").css("color","#FFFFFF");
		$(".nav-web").find(".hoverdiv").eq(1).addClass("act");
	}
	if(nav=="active" || nav=="outing"){
		$(".about-all").find(".aboutdiv").eq(0).css("background-color","#D1B862");
		$(".about-all").find(".aboutdiv").eq(0).find("p").css("color","#FFFFFF");
		$(".about-all").find(".aboutdiv").eq(0).addClass("act");
	}

}

$(".nav-web").on('mouseover', ".hoverdiv", function(event) {
    $(this).css("background-color","#D1B862");
    $(this).find("p").css("color","#FFFFFF");
  }).on('mouseout', ".hoverdiv", function(event) {
	  if(!$(this).hasClass("act")){
		  $(this).css("background-color","");
		  $(this).find("p").css("color","#000000");
	  }
});

$(".nav-web").on('mouseover', ".about-all", function(event) {
	$(this).find(".aboutdiv").show();
    $(this).find(".aboutdiv").eq(0).css("background-color","#D1B862");
    $(this).find(".aboutdiv").eq(0).find("p").css("color","#FFFFFF");
  }).on('mouseout', ".about-all", function(event) {
    $(this).find(".aboutdiv").hide();
    $(this).find(".aboutdiv").eq(0).show();
    $(this).find(".aboutdiv").eq(0).css("background","rgba(255, 255, 255,0)");
    $(this).find(".aboutdiv").eq(0).find("p").css("color","#000000");
});

$(".about-all").on('mouseover', ".aboutdiv", function(event) {
    $(this).css("background-color","#D1B862");
    $(this).find("p").css("color","#FFFFFF");
  }).on('mouseout', ".aboutdiv", function(event) {
    $(this).css("background-color","");
    $(this).find("p").css("color","#000000");
});


$("#website").click(function(){
	location.href="./home";
});

var count=300;
var timer;
$("#webvalue").click(function(){
	checkSession();
	clearInterval(timer);
	if($("#myModal").css("display")=="block"){
		return false;
	}
	$("#code").html("");
	var code = _getRandomString(5);
	$('#code').qrcode({render:"table",text:"http://www.blzgwx.com/wechat/login/v?redisCode="+code});
	$('#myModal').modal('show');
	timer = setInterval(function(){
		if(count<=0){
			$('#myModal').modal('hide');
			count=300;
			clearInterval(timer);
			return false;
		}
		checkRedis(code);
		count--;
		},1000);
});

$("#webactive").click(function(){
	location.href="./active";
});

$("#webouting").click(function(){
	location.href="./outing";
});


function checkRedis(code){
	if($("#myModal").css("display")=="none"){
		count=300;
		return false;
	}
	$.ajax({
		url:'./front/checkRedis',
		data:{'code':code},
		type:'POST',
		success:function(data){
			if(data!=''){
				location.href="./value";
			}
		}
	})
}


function _getRandomString(len) {  
    len = len || 32;  
    var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678'; // 默认去掉了容易混淆的字符oOLl,9gq,Vv,Uu,I1  
    var maxPos = $chars.length;  
    var pwd = '';
    for (i = 0; i < len; i++) {  
        pwd += $chars.charAt(Math.floor(Math.random() * maxPos));  
    }  
    return pwd;  
}

function getUrl(){
	var url = window.location.href;
	return url.substr(url.lastIndexOf('/')+1,url.length);
}

function checkSession(){
	$.ajax({
		url:'./checkSession',
		type:'POST',
		success:function(data){
			if(data=='success'){
				location.href="./value";
			}
		}
	});
}