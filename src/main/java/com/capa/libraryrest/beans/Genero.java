package com.capa.libraryrest.beans;

import org.springframework.data.relational.core.mapping.Column;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Genero {

    @Column("COD_GEN")
    private Integer id;

    @Column("NOM_GEN")
    private String nombre;

    @Column("DES_GEN")
    private String descripcion;
}
