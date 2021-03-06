package com.christian.clases;

public class Personaje {
	private Integer id;
	private String imagen;
	private String nombre;
	private String descripcion;
	private String ataque;
	
	public Personaje(String imagen, String nombre, String descripcion, String ataque) {
		this.imagen = imagen;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.ataque = ataque;
	}

	public Personaje(Integer id, String imagen, String nombre, String descripcion, String ataque) {
		this.id = id;
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

	@Override
	public String toString() {
		return "Personaje [id=" + id + ", imagen=" + imagen + ", nombre=" + nombre + ", descripcion=" + descripcion
				+ ", ataque=" + ataque + "]";
	}
	
	
}
