package com.bootcamp.bancotarjeta.model.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "tarjeta_cliente")

public class TarjetaClient {

    @Id
    private String id;
    private String nroTarjetaCredito;
    private Client cliente;
    private List<TarjetaCreditClient> tarjetaCredito;
    private List<Movimientos>movimiento;
    private double saldo;


}
