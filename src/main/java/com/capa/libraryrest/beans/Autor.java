package com.capa.libraryrest.beans;

import org.springframework.data.relational.core.mapping.Column;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Autor {

    @Column("COD_AUT")
    private Integer id;

    @Column("NOM_AUT")
    private String nombre;

    @Column("BIO_AUT")
    private String biografia;

    @Column("FEC_NAC")
    private String fechaNacimiento;

    @Column("NAC_AUT")
    private String nacionalidad;
}
