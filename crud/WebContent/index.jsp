<%@include file="overall/head.jsp" %>
<%@include file="overall/nav.jsp" %>
	
	<h1 class="text-center mt-5">Lista de Personajes de Dragon Ball</h1>

	<div class="container mt-5">
		<a href="#" class="btn btn-dark-green btn-sm m-0 float-right mb-1" data-toggle="modal" data-target="#modalAgregar">Agregar Nuevo</a>
		<table class="table table-striped table-responsive-md btn-table text-center border">
			<thead>
			  <tr>
			    <th>id</th>
			    <th>Image</th>
			    <th>Name</th>
			    <th>Description</th>
			    <th>Atack</th>
			    <th>Operation</th>
			  </tr>
			</thead>
			
			<tbody >
			 <c:forEach var="personaje" items="${personajes}">
			 	<c:url var="linkELiminarPersonaje" value="PersonajesController">
			 		<c:param name="instruccion" value="eliminar"></c:param>
			 		<c:param name="id" value="${personaje.id}"></c:param>
			 		<c:param name="img" value="${personaje.imagen}"></c:param>
			 	</c:url>
			 	<c:url var="linkModificarPersonaje" value="PersonajesController">
			 		<c:param name="instruccion" value="cargarProducto"></c:param>
			 		<c:param name="id" value="${personaje.id}"></c:param>
			 	</c:url>
			 	<tr>
				 	<td>${personaje.id}</td>
				    <th scope="row"><img src="${personaje.imagen}" alt="${personaje.nombre}" width="150px" id="img1"></th>
				    <td>${personaje.nombre}</td>
				    <td>${personaje.descripcion}</td>
				    <td>${personaje.ataque}</td>
				    <td>
				      <a href="${linkELiminarPersonaje}" class="btn btn-deep-orange btn-sm m-0">Elimnar</a>
				      <a href="${linkModificarPersonaje}" class="btn btn-indigo btn-sm m-0">Modificar</a>
				    </td>
			    </tr>
			 </c:forEach>
			</tbody>
		</table>
	</div>


	<div class="modal fade" id="modalAgregar" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header text-center">
	        <h4 class="modal-title w-100 font-weight-bold">Agregar nuevo Personaje</h4>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <form action="PersonajesController" method="post" enctype="multipart/form-data">
	      <div class="modal-body mx-3">
	     	 <div class="input-group">
			  <div class="input-group-prepend">
			    <span class="input-group-text" id="inputGroupFileAddon01">Upload</span>
			  </div>
			  <div class="custom-file">
			    <input type="file" class="custom-file-input" name="imagen" id="imagen">
			    <label class="custom-file-label" for="imagen">Choose file</label>
			  </div>
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
	      	<button class="btn btn-default" class="close" data-dismiss="modal">Cancelar</button>
	        <button type="submit" class="btn btn-default">Agregar</button>
	      </div>
	      </form>
	    </div>
	  </div>
	</div>


<%@include file="overall/footer.jsp" %>

