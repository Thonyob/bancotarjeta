package com.bootcamp.bancotarjeta.model.repository;

import com.bootcamp.bancotarjeta.model.document.TarjetaCredit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TarjetaCreditRepository extends ReactiveMongoRepository<TarjetaCredit,String> {
}
