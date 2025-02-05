package com.capa.libraryrest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.capa.libraryrest.beans.Libro;
import com.capa.libraryrest.services.impl.LibroServiceImpl;

@RestController // Anotación para indicar que es un controlador REST
@RequestMapping("/libro")
public class LibroController {

    @Autowired
    private LibroServiceImpl libroServiceImpl;

    // Métodos del controlador LibroController
    // Método para buscar un libro por su ID
    @GetMapping("/buscar/{id}")
    public Libro buscarLibro(@PathVariable Integer id) {
        return libroServiceImpl.buscarLibro(id);
    }

    // Método para listar los libros
    @GetMapping("/listar")
    public List<Libro> listaLibros() {
        return libroServiceImpl.listarLibros();
    }

    // Método para insertar un libro
    @PostMapping("/insertar")
    @ResponseStatus(HttpStatus.CREATED)
    public void insertarLibro(@RequestBody Libro libro) {
        boolean ok = libroServiceImpl.insertarLibro(libro);
        if (!ok) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al insertar los datos");
        }
    }

    // Método para actualizar un libro
    @PutMapping("/modificar/{id}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable Integer id, @RequestBody Libro libro) {
        boolean result = libroServiceImpl.actualizarLibro(libro);
        if (result) {
            // Devuelve un código 200 con el libro actualizado
            Libro libroActualizado = libroServiceImpl.buscarLibro(id);
            return ResponseEntity.ok(libroActualizado);
        } else {
            // Devuelve un código 500 si hay un error
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo actualizar el libro");
        }
    }

    // Método para borrar un libro
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> borrarLibro(@PathVariable Integer id) {
        boolean result = libroServiceImpl.borrarLibro(id);
        if (result) {
            return ResponseEntity.ok("Libro eliminado correctamente");
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo eliminar el libro");
        }
    }

}
