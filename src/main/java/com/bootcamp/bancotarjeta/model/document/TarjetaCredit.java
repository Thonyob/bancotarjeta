package com.bootcamp.bancotarjeta.model.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "tarjeta_credito")
public class TarjetaCredit {

    @Id
    private String id;
    private String idTipoCredito;
    private String nombre;
    private String tipo;

}
