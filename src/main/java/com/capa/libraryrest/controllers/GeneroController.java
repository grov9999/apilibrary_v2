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

import com.capa.libraryrest.beans.Genero;
import com.capa.libraryrest.services.impl.GeneroServiceImpl;

@RestController // Anotación para indicar que es un controlador REST
@RequestMapping("/genero")
public class GeneroController {

    @Autowired
    private GeneroServiceImpl generoServiceImpl;

    // Métodos del controlador GeneroController
    // Método para buscar un género por su ID
    @GetMapping("buscar/{id}")
    public Genero buscarGenero(@PathVariable Integer id) {
        return generoServiceImpl.buscarGenero(id);
    }

    // Método para listar los géneros
    @GetMapping("listar")
    public List<Genero> listaGeneros() {
        return generoServiceImpl.listarGeneros();
    }

    // Método para insertar un género
    @PostMapping("insertar")
    @ResponseStatus(HttpStatus.CREATED)
    public void insertarGenero(@RequestBody Genero genero) {
        boolean result = generoServiceImpl.insertarGenero(genero);
        if (!result) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo insertar el género");
        }
    }

    // Método para actualizar un género
    @PutMapping("actualizar/{id}")
    public ResponseEntity<Genero> actualizarGenero(@PathVariable Integer id, @RequestBody Genero genero) {
        boolean result = generoServiceImpl.actualizarGenero(genero);
        if (result) {
            // Devuelve un código 200 con el género actualizado
            Genero generoActualizado = generoServiceImpl.buscarGenero(id);
            return ResponseEntity.ok(generoActualizado);
        } else {
            // Devuelve un código 400 si no se pudo actualizar el género
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo actualizar el género");
        }
    }

    // Método para eliminar un género
    @DeleteMapping("borrar/{id}")
    public ResponseEntity<String> eliminarGenero(@PathVariable Integer id) {
        boolean result = generoServiceImpl.borrarGenero(id);
        if (result) {
            // Devuelve un código 204 si se eliminó el género
            return ResponseEntity.ok("Género eliminado correctamente");
        } else {
            // Devuelve un código 400 si no se pudo eliminar el género
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo eliminar el género");
        }
    }
}
