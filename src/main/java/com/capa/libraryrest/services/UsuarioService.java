package com.capa.libraryrest.services;

import java.util.List;

import com.capa.libraryrest.beans.Usuario;

public interface UsuarioService {
    // Métodos de la interfaz UsuarioService

    // Método para buscar un usuario por su ID
    public Usuario buscarUsuario(Integer id);

    // Método para listar los usuarios
    public List<Usuario> listarUsuarios();

    // Método para insertar un usuario
    public boolean insertarUsuario(Usuario usuario);

    // Método para actualizar un usuario
    public boolean actualizarUsuario(Usuario usuario);
    
    // Método para borrar un usuario
    public boolean borrarUsuario(Integer id);
}
