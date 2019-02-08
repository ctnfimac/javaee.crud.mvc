package com.christian.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


/**
 * Servlet implementation class ProductosController
 */
@WebServlet("/ProductosController")
public class ProductosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name="jdbc/Personaje")
	private DataSource miPool;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductosController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter salida = response.getWriter();
		response.setContentType("text/plain");
		
		Connection conexion = null;
		Statement statement = null;
		ResultSet resultado = null;
		
		try{
			conexion = miPool.getConnection();
			String sql = "SELECT * FROM personaje";
			statement = conexion.createStatement();
			resultado = statement.executeQuery(sql);
			
			while(resultado.next()){
				String nombre = resultado.getString(3);
				salida.println(nombre);
			}
		}catch(Exception e){
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
