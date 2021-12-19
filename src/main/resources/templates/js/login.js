$('.mid2').hide();          //手机&二维码
// $('.mid').hide();
$('.midd').hide();
$('.tipp').hide();



//二维码

$('.mid-top').hover(function(){	
	$('.mid1').stop().animate({'left':'19px'},400);
	$('.mid2').stop().show(400);
},function(){
	$('.mid1').stop().animate({'left':'84px'},400);
	$('.mid2').stop().hide(400);
})
$('.login-all>ul>li').click(function(){
	$(this).addClass('redd');
	$(this).siblings().removeClass('redd');
	var index=$(this).index();
	var index1=$(this).siblings().index();
	$('.login-all>div').eq(index).show();
	$('.login-all>div').eq(index1).hide();
	
})                    
function getred(a){
	$(a).css('border','1px solid red');
}
function getgray(a){
	$(a).css('border','1px solid #bdbdbd');
}

$('button').click(function(){
	var flag=true;
	if($('.inp1').val()=='' && $('.inp2').val()==''){
		$('.tip1').siblings('.tipp').hide();
		$('.tip1').show();
		getred('.inp1');getred('.inp2');
  		flag=false;
	}else if($('.inp1').val()==''){
		$('.tip2').siblings('.tipp').hide();
		$('.tip2').show();
		getred('.inp1');getgray('.inp2');
		flag=false;
	}else if($('.inp2').val()==''){
		$('.tip3').siblings('.tipp').hide();
		$('.tip3').show();
		getred('.inp2');getgray('.inp1');
		flag=false;
	}
	return flag;
});
$('html').click(function(){
	$('.tipp').hide();
});
$('.midd input').focus(function(){
	$(this).css('border','1px solid #bdbdbd');
});
