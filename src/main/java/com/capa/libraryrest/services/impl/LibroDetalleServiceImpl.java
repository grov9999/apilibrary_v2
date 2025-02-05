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

import com.capa.libraryrest.beans.LibroDetalle;
import com.capa.libraryrest.services.LibroDetalleService;

@Service
public class LibroDetalleServiceImpl implements LibroDetalleService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public LibroDetalle buscarLibro(Integer id) {
        return jdbcTemplate.execute((Connection conn) -> {
            try (CallableStatement cs = conn.prepareCall("{call SP_BUSCARLIBRO(?)}")) {
                cs.setInt(1, id); // Establece el parámetro ID en el procedimiento almacenado
                try (ResultSet rs = cs.executeQuery()) {
                    if (rs.next()) {
                        return mapearLibro(rs); // Mapea el resultado a un objeto Libro
                    } else {
                        return null; // Retorna null si no se encuentra el libro
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    public List<LibroDetalle> listarLibros() {
        return jdbcTemplate.execute((Connection conn) -> {
            List<LibroDetalle> libros = new ArrayList<>();
            try (CallableStatement cs = conn.prepareCall("{call SP_LISTARLIBRO()}");
                    ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    libros.add(mapearLibro(rs));
                }
            } catch (Exception e) {
                System.err.println("Error al mostrar los libros" + e.getMessage());
            }
            return libros;
        });
    }

    // public LibroDetalle mapearLibro(ResultSet rs) throws SQLException {
    // LibroDetalle libros = new LibroDetalle();
    // libros.setId(rs.getInt("id"));
    // libros.setTitulo(rs.getString("titulo"));
    // libros.setAutor(rs.getString("autor"));
    // libros.setGenero(rs.getString("genero"));
    // libros.setAnioPublicacion(rs.getString("anioPublicacion"));
    // libros.setIsbn(rs.getString("isbn"));
    // libros.setDescripcion(rs.getString("descripcion"));
    // return libros;
    // }

    private LibroDetalle mapearLibro(ResultSet rs) throws SQLException {
        LibroDetalle libro = new LibroDetalle();
        libro.setId(rs.getInt("COD_LIB"));
        libro.setTitulo(rs.getString("NOM_LIB"));
        libro.setAutor(rs.getString("NOM_AUT"));
        libro.setGenero(rs.getString("NOM_GEN"));
        libro.setAnioPublicacion(rs.getString("FEC_PUB"));
        libro.setIsbn(rs.getString("ISBN"));
        libro.setDescripcion(rs.getString("DES_LIB"));
        return libro;
    }
}
