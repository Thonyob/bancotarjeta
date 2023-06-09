package com.bootcamp.bancotarjeta.model.repository;

import com.bootcamp.bancotarjeta.model.document.TarjetaClient;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TarjetaClientRepository extends ReactiveMongoRepository<TarjetaClient,String> {
}
