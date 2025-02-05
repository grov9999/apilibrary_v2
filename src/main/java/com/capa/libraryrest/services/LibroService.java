package com.capa.libraryrest.services;

import java.util.List;

import com.capa.libraryrest.beans.Libro;

public interface LibroService {

	// Métodos de la interfaz LibroService
	// Método para buscar un libro por su ID
	public Libro buscarLibro(Integer id);

	// Método para listar los libros
	public List<Libro> listarLibros();

	// Método para insertar un libro
	public boolean insertarLibro(Libro libro);

	// Método para actualizar un libro
	public boolean actualizarLibro(Libro libro);

	// Método para borrar un libro
	public boolean borrarLibro(Integer id);
}
