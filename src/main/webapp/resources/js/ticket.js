var weekCount=1;

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
	
	$("#sedeSelectInput").change(function(){
		alert("lalala");
	});
	
});