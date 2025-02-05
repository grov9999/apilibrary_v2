package com.capa.libraryrest.services;

import java.util.List;

import com.capa.libraryrest.beans.LibroDetalle;

public interface LibroDetalleService {

    public LibroDetalle buscarLibro(Integer id);

    public List<LibroDetalle> listarLibros();

    // public boolean insertarLibro(LibroDetalle libro);

    // public boolean actualizarLibro(LibroDetalle libro);

    // public boolean borrarLibro(Integer id);
}
