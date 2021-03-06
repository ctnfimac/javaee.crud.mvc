package com.christian.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import com.christian.clases.Personaje;
import com.christian.models.PersonajeModel;


/**
 * Servlet implementation class PersonajesController
 */
@WebServlet("/PersonajesController")
@MultipartConfig
public class PersonajesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name="jdbc/personaje")
	private DataSource miPool;
	
	private PersonajeModel personajeModel;
	
	private final String ROOT = "C:/Users/christian/Documents/Git-Github/javaee.crud.mvc/crud/WebContent/img/";
	private final String DB_IMG_ROOT = "img/";
	private final String PATH_IMG_DELETE = "C:/Users/christian/Documents/Git-Github/javaee.crud.mvc/crud/WebContent/";
   
    /**
     * @throws ServletException 
     * @see HttpServlet#HttpServlet()
     */
//    public ProductosController() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
	
	@Override
	public void init() throws ServletException{
		super.init();
		try{
			personajeModel = new PersonajeModel(miPool);
		}catch(Exception e){
			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handler(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handler(request, response);
	}
	
	private void handler(HttpServletRequest request, HttpServletResponse response){
		String instruccion = request.getParameter("instruccion");
		
		if(instruccion == null ) instruccion = "mostrar";
		switch(instruccion){
			case "mostrar":
				mostrarPersonajes(request,response);
				break;
			case "agregar":
				try {
					agregarPersonaje(request,response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "eliminar":
				eliminarPersonaje(request,response);
				break;
			case "cargarProducto":
				cargarProducto(request,response);
				break;
			case "modificar":
				modificarProducto(request,response);
				break;
			default:
				mostrarPersonajes(request,response);
				break;
		}
	}

	
	private void mostrarPersonajes(HttpServletRequest request, HttpServletResponse response) {
		List<Personaje> personajes = null;
		try {
			personajes = personajeModel.getPersonajes();
			request.setAttribute("personajes", personajes);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void agregarPersonaje(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		String nombre = request.getParameter("nombre");
		String descripcion = request.getParameter("descripcion");
		String ataque = request.getParameter("ataque");
		Part filePart = request.getPart("imagen");
		
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
		File dir = new File(ROOT);
		 
		File file = File.createTempFile("img","-"+fileName,dir); //Evita que hayan dos archivos con el mismo nombre

		try (InputStream input = filePart.getInputStream()){
		    Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
		}
		Personaje personajeNuevo = new Personaje(DB_IMG_ROOT + file.getName(),nombre,descripcion,ataque);
		try {
			personajeModel.agregarPersonaje(personajeNuevo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		mostrarPersonajes(request,response);
		//para refrescar el directorio de imagenes
		//https://stackoverflow.com/questions/16913514/refresh-project-in-eclipse-when-a-new-file-is-added
	}
	
	
	private void eliminarPersonaje(HttpServletRequest request, HttpServletResponse response) {
		Integer id = Integer.parseInt(request.getParameter("id"));
		personajeModel.eliminarPersonaje(id);
		String imgDir = PATH_IMG_DELETE + request.getParameter("img");
		File imagenAeliminar = new File(imgDir);
		imagenAeliminar.delete();
		mostrarPersonajes(request,response);
	}

	private void cargarProducto(HttpServletRequest request, HttpServletResponse response){
		Integer id = Integer.valueOf(request.getParameter("id"));
		Personaje personaje = personajeModel.getPersonaje(id);
		request.setAttribute("personaje", personaje);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/modificar.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void modificarProducto(HttpServletRequest request, HttpServletResponse response) {
		try {
			Integer id = Integer.valueOf(request.getParameter("id"));
			String imagen = request.getParameter("imagen_old");
			Part filePart = request.getPart("imagen");
			String nombre = request.getParameter("nombre");
			String descripcion = request.getParameter("descripcion");
			String ataque = request.getParameter("ataque");
			
			if(Paths.get(filePart.getSubmittedFileName()).getFileName().toString().equals("")) 
				System.out.println("imagen vacia");
			else {
				//aca elimino la imagen vieja
				String imgDir = PATH_IMG_DELETE + imagen;
				File imagenAeliminar = new File(imgDir);
				imagenAeliminar.delete();
				
				//ac� guardo la imagen nueva
				String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
				File dir = new File(ROOT);
				File file = File.createTempFile("img","-"+fileName,dir);
				imagen = DB_IMG_ROOT + file.getName();
				try (InputStream input = filePart.getInputStream()){
				    Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
				}
			}
			//System.out.println("id:" + id + "\n imagen: " + imagen + "\n nombre:" + nombre + "\n descripcion: " + descripcion + "\n ataque: " + ataque);
			Personaje personaje = new Personaje(id,imagen,nombre,descripcion,ataque);
			personajeModel.modificarPersonaje(personaje);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		mostrarPersonajes(request,response);
	}


}
