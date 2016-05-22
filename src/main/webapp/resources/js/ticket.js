var weekCount=0;
var catalogPrice;
var userCredits;
var currentPurchase;

function initWeeks(numOfWeeks){
	weekCount=numOfWeeks;
}

function initMoney(price,credits){
	catalogPrice=parseFloat(price);
	userCredits=parseFloat(credits);
	currentPurchase=0;
}

function displayWeek(){
	var defaultWeek=$(".defaultWeek").first();
	var clonedWeek=defaultWeek.clone(true).appendTo("#weekContainer");
	clonedWeek.removeClass("hide");
	clonedWeek.removeClass("defaultWeek");
	clonedWeek.addClass("week");
	weekCount++;
	clonedWeek.find(".panel-title").text("Semana " + weekCount);
}

function updateList(){
	dayCount=0;
	$(".week").each(function( index ) {
		$(this).find(".panel-title").text("Semana " + ++index);
		dayCount++;
	});
}


$(document).ready(function(){
	
	$(".close").click(function(){
		if(weekCount > 1){
			$(this).parents(".week").remove();
			weekCount--;
			updateList();
		}
	});
	
	$(".menuSelect").focus(function(){
		var value=$(this).val();
		
		$(this).change(function(){
			if($(this).val() != 0 && value == 0){
				currentPurchase=currentPurchase + catalogPrice;
				userCredits=userCredits - catalogPrice;
				if(userCredits < 0){
					$("#price").css('color', 'red');
					$("#submitTicket").attr("disabled", true);
				}
			}
			else{
				if($(this).val() == 0 && value != 0){
					currentPurchase=currentPurchase - catalogPrice;
					userCredits=userCredits + catalogPrice;
					if(userCredits >= 0){
						$("#price").css('color', 'black');
						$("#submitTicket").attr("disabled", false);
					}
				}
			}
			
			$("#price").text("$" + currentPurchase);
			if(userCredits >= 0){
				$("#credits").text(userCredits);
			}
			else{
				$("#credits").text(0);
			}
			value=$(this).val();
		});
	});
	
	$(".menuSelect").focusout(function(){
		$(".menuSelect").unbind("change");
	});
	
});