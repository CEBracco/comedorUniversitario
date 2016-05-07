function consultar(id,dir){

 $.ajax({
                data:  id,
                url:   dir+'?id='+id,
              
                type:  'get',
                beforeSend: function () {
                        $("#resultado").html("Procesando, espere por favor...");
                },
                success:  function (response) {
                        $("#resultad").html(response);
                }
        });
 
 

}



$("#grid-data-api").on("loaded.rs.jquery.bootgrid", function()
		{
	 grid.find(".command-delete").on("click", function(e){
		 var rows = Array();
		 var fila= $(this).closest("tr");
		 rows[0] = $(this).data("row-id");
		 rows[1] = $(this).data("row-link");
		 rows[2] = $(this).data("row-nombre");
		 if( rows[2] === undefined){
			  rows[2] = "cartilla";
		  }
		 bootbox.confirm("¿Estas seguro de que deseas eliminar?", function(result) {
			  if(result){
				 
				  $.notify(rows[2]+" ha sido borrado", { globalPosition: 'bottom left', 
						 style: 'bootstrap',
						 className: 'success',
						 showAnimation: 'slideDown',
						 hideAnimation: 'fadeOut',
						 showDuration: 700,
						 hideDuration: 900,
						 autoHideDelay: 4000});
				consultar(rows[0],rows[1]);
				fila.remove();
					 
				 }
		}); 
   		 	});
				});