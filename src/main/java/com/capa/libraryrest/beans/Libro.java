package com.capa.libraryrest.beans;

import org.springframework.data.relational.core.mapping.Column;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Libro {

	@Column("COD_LIB")
    private Integer id;

	@Column("NOM_LIB")
	private String titulo;

	@Column("COD_AUT")
	private Integer autorId;

	@Column("COD_GEN")
	private Integer generoId;

	@Column("FEC_PUB")
	private String anioPublicacion;

	@Column("ISBN")
	private String isbn;

	@Column("DES_LIB")
	private String descripcion;

	@JsonIgnore
	@Column("EST_LIB")
	private Boolean estado;
}
