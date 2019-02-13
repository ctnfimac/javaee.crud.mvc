<%@include file="overall/head.jsp" %>
<%@include file="overall/nav.jsp" %>


<section class="formulario mt-5">
	<div class="container">
	  <div class="row justify-content-center">
	    <div class="col-sm-6 border">
	      <div class="modal-header text-center">
	        <h4 class="modal-title w-100 font-weight-bold">Modificando Personaje</h4>
	        <a href="PersonajesController" class="close">
	          <span aria-hidden="true">&times;</span>
	        </a>
	      </div>
	      <form action="PersonajesController" method="post" enctype="multipart/form-data">
	      <div class="modal-body mx-3">
	     	 <div class="input-group align-items-center d-flex">
			  <div class="input-group-prepend">
			    <span class="input-group-text" id="inputGroupFileAddon01">Upload</span>
			  </div>
			  <div class="custom-file">
			 	 
			    <input type="file" class="custom-file-input" name="imagen" id="imagen">
			    <label class="custom-file-label" for="imagen">Choose file</label>
			    
			  </div>
			  <img class="p-1" src="img/goku.jpg" width="180">
			</div>
	        <div class="md-form mb-5">
	          <input type="text" id="nombre" name="nombre" class="form-control validate">
	          <label for="nombre">Nombre</label>
	        </div>
	
	        <div class="md-form mb-4">
	          <input type="text" id="descripcion" name="descripcion" class="form-control validate">
	          <label for="descripcion">Descripción</label>
	        </div>
	
			<div class="md-form mb-4">
	          <input type="text" id="ataque" name="ataque" class="form-control validate">
	          <label for="ataque">Ataque</label>
	          <input type="hidden" name="instruccion" value="agregar">
	        </div>
	      </div>
	      <div class="modal-footer d-flex justify-content-center">
	      	<a href="PersonajesController" class="btn btn-default">Cancelar</a>
	        <button type="submit" class="btn btn-default">Modificar</button>
	      </div>
	      </form>
	      </div>
	    </div>
	</div>
</section>


<%@include file="overall/footer.jsp" %>	