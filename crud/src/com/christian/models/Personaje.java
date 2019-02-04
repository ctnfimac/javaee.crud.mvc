package com.christian.models;

public class Personaje {
	private Integer id;
	private String imagen;
	private String nombre;
	private String descripcion;
	private String ataque;
	
	public Personaje(String imagen, String nombre, String descripcion, String ataque) {
		super();
		this.imagen = imagen;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.ataque = ataque;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getAtaque() {
		return ataque;
	}

	public void setAtaque(String ataque) {
		this.ataque = ataque;
	}
	
}
