package com.bootcamp.bancotarjeta.model.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TarjetaCreditClient {

    private String nroTarjetaCredito;
    private String tipo;
    private double limite;

}
