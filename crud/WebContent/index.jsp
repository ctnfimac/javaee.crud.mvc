<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	 <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/mdb.min.css">
</head>
<body>

<nav class="navbar navbar-dark primary-color">
  <a class="navbar-brand" href="http://christianperalta.com" target="_blank">Christian Peralta</a>
</nav>

<div class="container mt-5">
	<a href="#" class="btn btn-dark-green btn-sm m-0 float-right mb-1">Agregar Nuevo</a>
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
		  <tr>
		 	<td>1</td>
		    <th scope="row"><img src="./img/goku.jpg" alt="nombre" width="150px" id="img1"></th>
		    <td>Son Goku</td>
		    <td>Gerrero Saiyan Criado en la tierra</td>
		    <td>Kame Hame Ha</td>
		    <td>
		      <a href="#" class="btn btn-deep-orange btn-sm m-0">Elimnar</a>
		      <a href="#" class="btn btn-indigo btn-sm m-0">Modificar</a>
		    </td>
		  </tr>
		   <tr>
		 	<td>1</td>
		    <th scope="row"><img src="./img/freezer.jpg" alt="nombre" width="150px" id="img2"></th>
		    <td>Freezer</td>
		    <td>Emperador del Universo</td>
		    <td>Bola Mortal</td>
		    <td>
		      <a href="#" class="btn btn-deep-orange btn-sm m-0">Elimnar</a>
		      <a href="#" class="btn btn-indigo btn-sm m-0">Modificar</a>
		    </td>
		  </tr>
		</tbody>
	</table>
</div>



<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/popper.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/mdb.min.js"></script>
</body>
</html>