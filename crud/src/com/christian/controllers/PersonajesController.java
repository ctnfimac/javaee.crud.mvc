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
	private final String DB_IMG_ROOT = "./img/";
       
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
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ServletException e) {
					e.printStackTrace();
				}
				break;
			default:
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
	
	

}
