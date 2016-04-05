function displaySelect(){
	var defaultSelect=$(".selectPlato").first();	
	var clonedSelect=defaultSelect.clone(true).appendTo("#selectContainer");
	clonedSelect.removeClass("hide");
	clonedSelect.find("select[name='idPlatosDefault']").attr('name', 'idPlatos');
	clonedSelect.find("select[name='idPlatos']").prop('required',true);
}

$(document).ready(function(){
	
	$(".clearButton").click(function(){
		$(this).parents(".selectPlato").remove();
	});
});