package com.capa.libraryrest.beans;

import org.springframework.data.relational.core.mapping.Column;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Usuario {

    @Column("COD_USU")
    private Integer id;

    @Column("NOM_USU")
    private String nombre;

    @Column("EMAIL_USU")
    private String correo;

    @Column("PASS_USU")
    private String contrasena;

    @Column("ROL_USU")
    private String rol;
}
