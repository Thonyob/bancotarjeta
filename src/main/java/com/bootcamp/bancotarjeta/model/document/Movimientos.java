package com.bootcamp.bancotarjeta.model.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movimientos {

    private String tipo;
    private double monto;
    private String fecha;
    private String codigoTarjetaCredito;

}
