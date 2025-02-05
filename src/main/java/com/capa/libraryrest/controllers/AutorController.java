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

import com.capa.libraryrest.beans.Autor;
import com.capa.libraryrest.services.impl.AutorServiceImpl;

@RestController // Anotación para indicar que es un controlador REST
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorServiceImpl autorServiceImpl;

    // Métodos del controlador AutorController
    // Método para buscar un autor por su ID
    @GetMapping("buscar/{id}")
    public Autor search(@PathVariable Integer id) {
        return autorServiceImpl.buscarAutor(id);
    }

    // Método para listar los autores
    @GetMapping("listar")
    public List<Autor> listaAutores() {
        return autorServiceImpl.listarAutores();
    }

    // Método para insertar un autor
    @PostMapping("insertar")
    @ResponseStatus(HttpStatus.CREATED)
    public void insert(@RequestBody Autor autor) {
        boolean result = autorServiceImpl.insertarAutor(autor);
        if (!result) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo insertar el autor");
        }
    }

    // Método para actualizar un autor
    @PutMapping("actualizar/{id}")
    public ResponseEntity<Autor> update(@PathVariable Integer id, @RequestBody Autor autor) {
        boolean result = autorServiceImpl.actualizarAutor(autor);
        if (result) {
            // return ResponseEntity.ok(autorServiceImpl.buscarAutor(id));
            Autor autorActualizado = autorServiceImpl.buscarAutor(id);
            return ResponseEntity.ok(autorActualizado);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo actualizar el autor");
        }
    }

    // Método para borrar un autor
    @DeleteMapping("borrar/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        boolean result = autorServiceImpl.borrarAutor(id);
        if (result) {
            return ResponseEntity.ok("Autor eliminado correctamente");
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo eliminar el autor");
        }
    }

}
