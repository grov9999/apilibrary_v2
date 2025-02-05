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

import com.capa.libraryrest.beans.Usuario;
import com.capa.libraryrest.services.impl.UsuarioServiceImpl;

@RestController // Anotación para indicar que es un controlador REST
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;

    // Métodos del controlador UsuarioController
    // Método para buscar un usuario por su ID
    @GetMapping("buscar/{id}")
    public Usuario buscarUsuario(@PathVariable Integer id) {
        return usuarioServiceImpl.buscarUsuario(id);
    }

    // Método para listar los usuarios
    @GetMapping("listar")
    public List<Usuario> listaUsuarios() {
        return usuarioServiceImpl.listarUsuarios();
    }

    // Método para insertar un usuario
    @PostMapping("insertar")
    @ResponseStatus(HttpStatus.CREATED)
    public void insertarUsuario(@RequestBody Usuario usuario) {
        boolean result = usuarioServiceImpl.insertarUsuario(usuario);
        if (!result) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo insertar el usuario");
        }
    }

    // Método para actualizar un usuario
    @PutMapping("actualizar/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {
        boolean result = usuarioServiceImpl.actualizarUsuario(usuario);
        if (result) {
            // Devuelve un código 200 con el usuario actualizado
            Usuario usuarioActualizado = usuarioServiceImpl.buscarUsuario(id);
            return ResponseEntity.ok(usuarioActualizado);
        } else {
            // Devuelve un código 400
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo actualizar el usuario");
        }
    }

    // Método para borrar un usuario
    @DeleteMapping("borrar/{id}")
    public ResponseEntity<String> borrarUsuario(@PathVariable Integer id) {
        boolean result = usuarioServiceImpl.borrarUsuario(id);
        if (result) {
            // Devuelve un código 200
            return ResponseEntity.ok("Usuario borrado exitosamente");
        } else {
            // Devuelve un código 400
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo borrar el usuario"); 
        }
    }
}
