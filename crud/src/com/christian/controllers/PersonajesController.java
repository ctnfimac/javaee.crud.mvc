package com.christian.controllers;

import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.christian.clases.Personaje;
import com.christian.models.PersonajeModel;


/**
 * Servlet implementation class PersonajesController
 */
@WebServlet("/PersonajesController")
public class PersonajesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name="jdbc/personaje")
	private DataSource miPool;
	
	private PersonajeModel personajeModel;
       
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
		
		String instruccion = request.getParameter("instruccion");
		
		if(instruccion == null ) instruccion = "mostrar";
	
		switch(instruccion){
			case "mostrar":
				mostrarPersonajes(request,response);
				break;
			case "agregar":
				System.out.println("agregando producto");
				break;
			default:
				break;
		}
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
