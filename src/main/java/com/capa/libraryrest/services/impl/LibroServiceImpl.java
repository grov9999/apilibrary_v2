package com.capa.libraryrest.services.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.capa.libraryrest.beans.Libro;
import com.capa.libraryrest.services.LibroService;

@Service
public class LibroServiceImpl implements LibroService {

    @Autowired
    private JdbcTemplate jdbcTemplate; // Inyección de dependencia para JdbcTemplate

    public String saludo(String autor) {
        return "Autor: ".concat(autor);
    }

    // Método para buscar un libro
    public Libro buscarLibro(Integer id) {
        return jdbcTemplate.execute((Connection conn) -> {
            try (CallableStatement cs = conn.prepareCall("{call SP_BuscarLibroPorId(?)}")) {
                cs.setInt(1, id); // Establece el parámetro ID en el procedimiento almacenado
                try (ResultSet rs = cs.executeQuery()) {
                    if (rs.next()) {
                        return mapearLibro(rs); // Mapea el ResultSet a un objeto Libro
                    } else {
                        return null; // Retorna null si no se encuentra el libro
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Imprime la excepción
                return null; // Retorna null si hay una excepción
            }
        });
    }

    // Método para listar los libros
    public List<Libro> listarLibros() {
        return jdbcTemplate.execute((Connection conn) -> {
            List<Libro> libros = new ArrayList<>();
            try (CallableStatement cs = conn.prepareCall("{call SP_ListarLibros()}");
                    ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    libros.add(mapearLibro(rs));
                }
            } catch (Exception e) {
                System.err.println("Error al listar los libros: " + e.getMessage());
            }
            return libros;
        });
    }

    // Método para insertar un libro
    public boolean insertarLibro(Libro libro) {
        return jdbcTemplate.execute((Connection conn) -> {
            try (CallableStatement cs = conn.prepareCall("{call SP_InsertarLibro(?, ?, ?, ?, ?, ?, ?)}")) {
                cs.setInt(1, libro.getId());
                cs.setString(2, libro.getTitulo());
                cs.setInt(3, libro.getAutorId());
                cs.setInt(4, libro.getGeneroId());
                cs.setString(5, libro.getAnioPublicacion());
                cs.setString(6, libro.getIsbn());
                cs.setString(7, libro.getDescripcion());
                cs.execute(); // Ejecuta el procedimiento almacenado
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        });
    }

    // Método para actualizar un libro
    public boolean actualizarLibro(Libro libro) {
        return jdbcTemplate.execute((Connection conn) -> {
            try (CallableStatement cs = conn.prepareCall("{call SP_ActualizarLibro(?, ?, ?, ?, ?, ?, ?, ?)}")) {
                cs.setInt(1, libro.getId());
                cs.setString(2, libro.getTitulo());
                cs.setInt(3, libro.getAutorId());
                cs.setInt(4, libro.getGeneroId());
                cs.setString(5, libro.getAnioPublicacion());
                cs.setString(6, libro.getIsbn());
                cs.setString(7, libro.getDescripcion());
                cs.setBoolean(8, libro.getEstado());
                cs.execute(); // Ejecuta el procedimiento almacenado
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        });
    }

    // Método para borrar un libro
    public boolean borrarLibro(Integer id) {
        return jdbcTemplate.execute((Connection conn) -> {
            try (CallableStatement cs = conn.prepareCall("{call SP_BorrarLibro(?)}")) {
                cs.setInt(1, id); // Establece el parámetro ID en el procedimiento almacenado
                cs.execute(); // Ejecuta el procedimiento almacenado
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        });
    }

    // Método para mapear un libro
    public Libro mapearLibro(ResultSet rs) throws SQLException {
        Libro libros = new Libro();
        libros.setId(rs.getInt("id"));
        libros.setTitulo(rs.getString("titulo"));
        libros.setAutorId(rs.getInt("autorId"));
        libros.setGeneroId(rs.getInt("generoId"));
        libros.setAnioPublicacion(rs.getString("anioPublicacion"));
        libros.setIsbn(rs.getString("isbn"));
        libros.setDescripcion(rs.getString("descripcion"));
        return libros;
    }
}
