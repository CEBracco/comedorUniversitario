var dayCount=0;

function displayDay(){
	var defaultDay=$(".defaultDay").first();
	var clonedDay=defaultDay.clone(true).appendTo("#daysContainer");
	clonedDay.removeClass("hide");
	clonedDay.removeClass("defaultDay");
	clonedDay.addClass("day");
	clonedDay.find("input[name='idMenusDefault']").attr('name', 'idMenus');
	dayCount++;
	clonedDay.find(".panel-title").text("Día " + dayCount);
}

function updateList(){
	dayCount=0;
	$(".day").each(function( index ) {
		$(this).find(".panel-title").text("Día " + ++index);
		dayCount++;
	});
}

$( window ).load(function() {
	updateList();
});


$(document).ready(function(){
	
	$(".addMenu").click(function(){
		//seleccionamos y clonamos el form-group 
		var clonedSelectMenu=$(".selectMenuDefault").first().clone(true);
		
		//hacemos visible al form-group
		clonedSelectMenu.removeClass("hide");
		
		//cambiamos la clase del formgroup(asi no tenemos conflicto cuando clonemos otra vez el formgroup default)
		clonedSelectMenu.removeClass("selectMenuDefault");
		clonedSelectMenu.addClass("selectMenu");
		
		//cambiamos el nombre del select interno asi empieza a encolar en el post 
		clonedSelectMenu.find("select[name='idMenusDefault']").attr('name', 'idMenus');
		clonedSelectMenu.find("select[name='idMenus']").prop('required',true);
		
		//agregamos el formgroup clonado al container
		$(this).parents(".panel-body").first().find(".selectContainer").first().append(clonedSelectMenu);
	});
	
	$(".clearButton").click(function(){
		$(this).parents(".selectMenu").remove();
	});
	
	$(".close").click(function(){
		$(this).parents(".day").remove();
		dayCount--;
		updateList();
	});
	
});