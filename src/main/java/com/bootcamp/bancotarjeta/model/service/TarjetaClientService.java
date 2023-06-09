package com.bootcamp.bancotarjeta.model.service;

import com.bootcamp.bancotarjeta.model.document.TarjetaClient;
import com.bootcamp.bancotarjeta.model.document.TarjetaCredit;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import reactor.core.publisher.Flux;

public interface TarjetaClientService {

    Maybe<TarjetaClient> save(TarjetaClient tarjetaClient);
    Maybe<TarjetaClient> getTarjetaClientByClient(String codigoCliente);
    Maybe<TarjetaClient> getTarjetaClientByCodigoTarjeta(String codigoTarjeta);
    Maybe<TarjetaClient> getPayConsumption(String codigoTarjeta,double monto);
    Maybe<TarjetaClient> getCarryConsumption(String codigoTarjeta,double monto);
    Flux<TarjetaClient> getAllTarjetaClient();
    Flux<TarjetaCredit> getAllTarjeta();

}
