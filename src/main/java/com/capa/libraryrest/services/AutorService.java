package com.capa.libraryrest.services;

import java.util.List;

import com.capa.libraryrest.beans.Autor;

public interface AutorService {
    // Métodos de la interfaz AutorService

    // Método para buscar un autor por su ID
    public Autor buscarAutor(Integer id);

    // Método para listar los autores
    public List<Autor> listarAutores();

    // Método para insertar un autor
    public boolean insertarAutor(Autor autor);

    // Método para actualizar un autor
    public boolean actualizarAutor(Autor autor);
    
    // Método para borrar un autor
    public boolean borrarAutor(Integer id);
}