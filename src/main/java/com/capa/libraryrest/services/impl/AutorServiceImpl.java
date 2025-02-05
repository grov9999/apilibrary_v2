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

import com.capa.libraryrest.beans.Autor;
import com.capa.libraryrest.services.AutorService;

@Service
public class AutorServiceImpl implements AutorService {

    @Autowired
    private JdbcTemplate jdbcTemplate; // Inyección de dependencia

    // Métodos de la interfaz AutorService
    // Método para buscar un autor
    public Autor buscarAutor(Integer id) {
        return jdbcTemplate.execute((Connection conn) -> {
            try (CallableStatement cs = conn.prepareCall("{call SP_BUSCARAUTOR(?)}")) {
                cs.setInt(1, id);
                try (ResultSet rs = cs.executeQuery()) {
                    if (rs.next()) {
                        return mapearAutor(rs);
                    } else {
                        return null;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    // Método para listar los autores
    public List<Autor> listarAutores() {
        return jdbcTemplate.execute((Connection conn) -> {
            List<Autor> autores = new ArrayList<>();
            try (CallableStatement cs = conn.prepareCall("{call SP_LISTARAUTOR()}");
                    ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    autores.add(mapearAutor(rs));
                }
            } catch (SQLException e) {
                System.err.println("Error al mostrar los autores: " + e.getMessage());
            }
            return autores;
        });
    }

    // Método para insertar un autor
    public boolean insertarAutor(Autor autor) {
        return jdbcTemplate.execute((Connection conn) -> {
            try (CallableStatement cs = conn.prepareCall("{call SP_INSERTARAUTOR(?, ?, ?, ?, ?)}")) {
                cs.setInt(1, autor.getId());
                cs.setString(2, autor.getNombre());
                cs.setString(3, autor.getBiografia());
                cs.setString(4, autor.getFechaNacimiento());
                cs.setString(5, autor.getNacionalidad());
                return cs.executeUpdate() > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        });
    }

    // Método para actualizar un autor
    public boolean actualizarAutor(Autor autor) {
        return jdbcTemplate.execute((Connection conn) -> {
            try (CallableStatement cs = conn.prepareCall("{call SP_ACTUALIZARAUTOR(?, ?, ?, ?, ?)}")) {
                cs.setInt(1, autor.getId());
                cs.setString(2, autor.getNombre());
                cs.setString(3, autor.getBiografia());
                cs.setString(4, autor.getFechaNacimiento());
                cs.setString(5, autor.getNacionalidad());
                return cs.executeUpdate() > 0;
            } catch (SQLException e) {
                System.err.println("Error al actualizar el autor: " + e.getMessage());
                return false;
            }
        });
    }

    // Método para borrar un autor
    public boolean borrarAutor(Integer id) {
        return jdbcTemplate.execute((Connection conn) -> {
            try (CallableStatement cs = conn.prepareCall("{call SP_BORRARAUTOR(?)}")) {
                cs.setInt(1, id);
                return cs.executeUpdate() > 0;
            } catch (SQLException e) {
                System.err.println("Error al borrar el autor: " + e.getMessage());
                return false;
            }
        });
    }

    // Método para mapear un autor
    private Autor mapearAutor(ResultSet rs) throws SQLException {
        Autor autor = new Autor();
        autor.setId(rs.getInt("COD_AUT"));
        autor.setNombre(rs.getString("NOM_AUT"));
        autor.setBiografia(rs.getString("BIO_AUT"));
        autor.setFechaNacimiento(rs.getString("FEC_NAC"));
        autor.setNacionalidad(rs.getString("NAC_AUT"));
        return autor;
    }
}
