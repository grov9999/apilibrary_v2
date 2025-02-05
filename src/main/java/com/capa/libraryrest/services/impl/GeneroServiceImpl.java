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

import com.capa.libraryrest.beans.Genero;
import com.capa.libraryrest.services.GeneroService;

@Service
public class GeneroServiceImpl implements GeneroService {

    @Autowired
    private JdbcTemplate jdbcTemplate; // Inyección de dependencia

    // Métodos de la interfaz GeneroService
    // Método para buscar un género
    public Genero buscarGenero(Integer id) {
        return jdbcTemplate.execute((Connection conn) -> {
            try (CallableStatement cs = conn.prepareCall("{call SP_BUSCARGENERO(?)}")) {
                cs.setInt(1, id);
                try (ResultSet rs = cs.executeQuery()) {
                    if (rs.next()) {
                        return mapearGenero(rs);
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

    // Método para listar los géneros
    public List<Genero> listarGeneros() {
        return jdbcTemplate.execute((Connection conn) -> {
            List<Genero> generos = new ArrayList<>();
            try (CallableStatement cs = conn.prepareCall("{call SP_LISTARGENERO()}");
                    ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    generos.add(mapearGenero(rs));
                }
            } catch (SQLException e) {
                System.err.println("Error al mostrar los géneros: " + e.getMessage());
            }
            return generos;
        });
    }

    // Método para insertar un género
    public boolean insertarGenero(Genero genero) {
        return jdbcTemplate.execute((Connection conn) -> {
            try (CallableStatement cs = conn.prepareCall("{call SP_INSERTARGENERO(?, ?, ?)}")) {
                cs.setInt(1, genero.getId());
                cs.setString(2, genero.getNombre());
                cs.setString(3, genero.getDescripcion());
                return cs.executeUpdate() > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        });
    }

    // Método para actualizar un género
    public boolean actualizarGenero(Genero genero) {
        return jdbcTemplate.execute((Connection conn) -> {
            try (CallableStatement cs = conn.prepareCall("{call SP_ACTUALIZARGENERO(?, ?, ?)}")) {
                cs.setInt(1, genero.getId());
                cs.setString(2, genero.getNombre());
                cs.setString(3, genero.getDescripcion());
                return cs.executeUpdate() > 0; //
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        });
    }

    // Método para borrar un género
    public boolean borrarGenero(Integer id) {
        return jdbcTemplate.execute((Connection conn) -> {
            try (CallableStatement cs = conn.prepareCall("{call SP_BORRARGENERO(?)}")) {
                cs.setInt(1, id);
                return cs.executeUpdate() > 0;
            } catch (SQLException e) {
                System.err.println("Error al borrar el género: " + e.getMessage());
                return false;
            }
        });
    }

    // Método para mapear un género
    private Genero mapearGenero(ResultSet rs) throws SQLException {
        Genero genero = new Genero();
        genero.setId(rs.getInt("COD_GEN"));
        genero.setNombre(rs.getString("NOM_GEN"));
        genero.setDescripcion(rs.getString("DES_GEN"));
        return genero;
    }
}
