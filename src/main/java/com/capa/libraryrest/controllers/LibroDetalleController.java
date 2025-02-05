package com.capa.libraryrest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capa.libraryrest.beans.LibroDetalle;
import com.capa.libraryrest.services.impl.LibroDetalleServiceImpl;



@RestController
@RequestMapping("/detalle")
public class LibroDetalleController {

    @Autowired
    private LibroDetalleServiceImpl libroDetalleServiceImpl;

    @GetMapping("/buscar/{id}")
    public LibroDetalle buscarLibro(@PathVariable Integer id) {
        return libroDetalleServiceImpl.buscarLibro(id);
    }
    
    @GetMapping("/listar")
    public List<LibroDetalle> listaLibros() {
        return libroDetalleServiceImpl.listarLibros();
    }
    
}
