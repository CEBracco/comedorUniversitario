var weekCount=0;
var catalogPrice;
var userCredits;
var currentPurchase;
var currentDate;

function initWeeks(numOfWeeks){
	weekCount=numOfWeeks;
}

function initMoney(price,credits){
	catalogPrice=parseFloat(price);
	userCredits=parseFloat(credits);
	currentPurchase=0;
	refreshDates();
}

function displayWeek(){
	var defaultWeek=$(".defaultWeek").first();
	var clonedWeek=defaultWeek.clone(true).appendTo("#weekContainer");
	clonedWeek.removeClass("hide");
	clonedWeek.removeClass("defaultWeek");
	clonedWeek.addClass("week");
	weekCount++;
	clonedWeek.find(".panel-title").text("Semana " + weekCount);
	refreshDates();
}

function updateList(){
	dayCount=0;
	$(".week").each(function( index ) {
		$(this).find(".panel-title").text("Semana " + ++index);
		dayCount++;
	});
}

function initDate(){
	currentDate=new Date();
	currentDate.setDate(currentDate.getDate() + 1);
	while(currentDate.getDay() != 1){
		currentDate.setDate(currentDate.getDate() + 1);
	}
}

function nextDate(){
	currentDate.setDate(currentDate.getDate() + 1);
	if(currentDate.getDay() == 0 || currentDate.getDay() == 6){
		while(currentDate.getDay() != 1){
			currentDate.setDate(currentDate.getDate() + 1);
		}
	}
}

function refreshDates(){
	initDate();
	$("#weekContainer").find('thead').each(function(i,el){
		var ths=$(this).find('th');
		for(i=0; i < 5; i++){
			ths.eq(i).find('.date').text(currentDate.getDate());
			nextDate();
		}
	});
}


$(document).ready(function(){
	
	$(".close").click(function(){
		if(weekCount > 1){
			$(this).parents(".week").remove();
			weekCount--;
			updateList();
			refreshDates();
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