package com.christian.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.christian.clases.Personaje;

public class PersonajeModel {
	private DataSource origenDatos;
	
	public PersonajeModel(DataSource origenDatos){
		this.origenDatos = origenDatos;
	}
	
	public List<Personaje> getPersonajes() throws Exception{
		List<Personaje> personajes = new ArrayList<>();
		Connection conexion = null;
		Statement statement = null;
		ResultSet resultado = null;
		
		conexion = origenDatos.getConnection();
		statement = conexion.createStatement();
		String sql = "SELECT * FROM personaje";
		resultado = statement.executeQuery(sql);
		while(resultado.next()){
			Integer id = resultado.getInt("id");
			String imagen = resultado.getString("imagen");
			String nombre = resultado.getString("nombre");
			String descripcion = resultado.getString("descripcion");
			String ataque = resultado.getString("ataque");
			personajes.add(new Personaje(id,imagen,nombre,descripcion,ataque));
		}
		return personajes;
	}
}
