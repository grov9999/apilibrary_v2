package com.capa.libraryrest.services;

import java.util.List;

import com.capa.libraryrest.beans.Genero;

public interface GeneroService {
    // Métodos de la interfaz GeneroService

    // Método para buscar un género por su ID
    public Genero buscarGenero(Integer id);

    // Método para listar los géneros
    public List<Genero> listarGeneros();

    // Método para insertar un género
    public boolean insertarGenero(Genero genero);

    // Método para actualizar un género
    public boolean actualizarGenero(Genero genero);
    
    // Método para borrar un género
    public boolean borrarGenero(Integer id);
}
