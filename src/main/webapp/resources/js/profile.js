$(document).ready(function(){
	$("#upload_link").on('click', function(e){
        e.preventDefault();
        $("#upload:hidden").trigger('click');
	});
	$("#upload:hidden").on('change', function(){
		$("#formPhoto").submit();
	});
});
