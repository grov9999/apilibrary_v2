package com.capa.libraryrest.beans;

import org.springframework.data.relational.core.mapping.Column;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LibroDetalle {

    @Column("COD_LIB")
    private int id;

    @Column("NOM_LIB")
    private String titulo;

    @Column("NOM_AUT")
    private String autor;

    @Column("NOM_GEN")
    private String genero;

    @Column("FEC_PUB")
    private String anioPublicacion;

    @Column("DES_LIB")
    private String isbn;

    @Column("EST_LIB")
    private String descripcion;
}
