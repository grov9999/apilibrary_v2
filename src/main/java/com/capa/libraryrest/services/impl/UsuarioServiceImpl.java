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

import com.capa.libraryrest.beans.Usuario;
import com.capa.libraryrest.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private JdbcTemplate jdbcTemplate; // Inyección de dependencia

    // Método para buscar un usuario
    public Usuario buscarUsuario(Integer id) {
        return jdbcTemplate.execute((Connection conn) -> {
            try (CallableStatement cs = conn.prepareCall("{call SP_BUSCARUSUARIO(?)}")) {
                cs.setInt(1, id);
                try (ResultSet rs = cs.executeQuery()) {
                    if (rs.next()) {
                        return mapearUsuario(rs);
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

    // Método para listar los usuarios
    public List<Usuario> listarUsuarios() {
        return jdbcTemplate.execute((Connection conn) -> {
            List<Usuario> usuarios = new ArrayList<>();
            try (CallableStatement cs = conn.prepareCall("{call SP_LISTARUSUARIO()}");
                    ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    usuarios.add(mapearUsuario(rs));
                }
            } catch (SQLException e) {
                System.err.println("Error al mostrar los usuarios: " + e.getMessage());
            }
            return usuarios;
        });
    }

    // Método para insertar un usuario
    public boolean insertarUsuario(Usuario usuario) {
        return jdbcTemplate.execute((Connection conn) -> {
            try (CallableStatement cs = conn.prepareCall("{call SP_INSERTARUSUARIO(?,?,?,?,?)}")) {
                cs.setInt(1, usuario.getId());
                cs.setString(2, usuario.getNombre());
                cs.setString(3, usuario.getCorreo());
                cs.setString(4, usuario.getContrasena());
                cs.setString(5, usuario.getRol());
                cs.execute();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        });
    }

    // Método para actualizar un usuario
    public boolean actualizarUsuario(Usuario usuario) {
        return jdbcTemplate.execute((Connection conn) -> {
            try (CallableStatement cs = conn.prepareCall("{call SP_ACTUALIZARUSUARIO(?,?,?,?,?)}")) {
                cs.setInt(1, usuario.getId());
                cs.setString(2, usuario.getNombre());
                cs.setString(3, usuario.getCorreo());
                cs.setString(4, usuario.getContrasena());
                cs.setString(5, usuario.getRol());
                cs.execute();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        });
    }

    // Método para borrar un usuario
    @Override
    public boolean borrarUsuario(Integer id) {
        return jdbcTemplate.execute((Connection conn) -> {
            try (CallableStatement cs = conn.prepareCall("{call SP_BORRARUSUARIO(?)}")) {
                cs.setInt(1, id);
                cs.execute();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        });
    }

    // Método para mapear un usuario
    private Usuario mapearUsuario(ResultSet rs) throws SQLException {
        Usuario usuarios = new Usuario();
        usuarios.setId(rs.getInt("COD_USU"));
        usuarios.setNombre(rs.getString("NOM_USU"));
        usuarios.setCorreo(rs.getString("EMAIL_USU"));
        usuarios.setContrasena(rs.getString("PASS_USU"));
        usuarios.setRol(rs.getString("ROL_USU"));
        return usuarios;
    }

    
}
