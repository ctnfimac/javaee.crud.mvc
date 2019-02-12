package com.christian.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;

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

	public void agregarPersonaje(Personaje personajeNuevo) throws SQLException {
		Connection conexion = null;
		PreparedStatement statement = null;
		
		conexion = origenDatos.getConnection();
		String sql = "INSERT INTO personaje(imagen,nombre,descripcion,ataque) values (?,?,?,?)";
		statement = conexion.prepareStatement(sql);
		statement.setString(1, personajeNuevo.getImagen());
		statement.setString(2, personajeNuevo.getNombre());
		statement.setString(3, personajeNuevo.getDescripcion());
		statement.setString(4, personajeNuevo.getAtaque());
		statement.execute();
		
		statement.close();
		conexion.close();
	}

	public void eliminarPersonaje(Integer id) {
		Connection conexion = null;
		PreparedStatement statement = null;
		try {
			conexion = origenDatos.getConnection();
			String sql = "DELETE FROM personaje WHERE id = ? ";
			statement = conexion.prepareStatement(sql);
			statement.setInt(1, id);
			statement.execute();
			
			conexion.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
}
