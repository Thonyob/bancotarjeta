package com.bootcamp.bancotarjeta.controller;

import com.bootcamp.bancotarjeta.model.document.TarjetaClient;
import com.bootcamp.bancotarjeta.model.document.TarjetaCredit;
import com.bootcamp.bancotarjeta.model.service.TarjetaClientService;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/tarjeta-client")
public class TarjetaClientController {

    @Autowired
    TarjetaClientService tarjetaClientService;


    @PostMapping
    public ResponseEntity<Maybe<TarjetaClient>> save(@RequestBody TarjetaClient tarjetaClient ) {
        return new ResponseEntity<>(tarjetaClientService.save(tarjetaClient), HttpStatus.CREATED);
    }

    @GetMapping("/tarjeta/byCodigoCliente/{codigoCliente}")
    public ResponseEntity<Maybe<TarjetaClient>> getTarjetaClientByClient(@PathVariable("codigoCliente") String codigoCliente){
        return new ResponseEntity<>(tarjetaClientService.getTarjetaClientByClient(codigoCliente),HttpStatus.OK);
    }

    @GetMapping("/tarjeta/byCodigoTarjeta/{codigoTarjeta}")
    public ResponseEntity<Maybe<TarjetaClient>> getTarjetaClientByCodigoTarjeta(@PathVariable("codigoTarjeta") String codigoTarjeta){
        return new ResponseEntity<>(tarjetaClientService.getTarjetaClientByCodigoTarjeta(codigoTarjeta),HttpStatus.OK);
    }

    @PostMapping("/tarjeta/payTarjeta")
    public ResponseEntity<Maybe<TarjetaClient>> getPayConsumption (@RequestParam("codigoTarjeta") String codigoTarjeta,
                                                                   @RequestParam("monto")double monto){
        return new ResponseEntity<>(tarjetaClientService.getPayConsumption(codigoTarjeta,monto),HttpStatus.OK);
    }

        @PostMapping("/tarjeta/carryTarjeta")
    public ResponseEntity<Maybe<TarjetaClient>> getCarryConsumption (@RequestParam("codigoTarjeta") String codigoTarjeta,
                                                                     @RequestParam("monto")double monto){
        return new ResponseEntity<>(tarjetaClientService.getCarryConsumption(codigoTarjeta,monto),HttpStatus.OK);
    }

    @GetMapping("/tarjeta/getAlTarjetaClient")
    public ResponseEntity<Flux<TarjetaClient>> getAllTarjetaClient(){
        return new ResponseEntity<>(tarjetaClientService.getAllTarjetaClient(),HttpStatus.OK);
    }

    @GetMapping("/tarjeta/getAllTarjeta")
    public ResponseEntity<Flux<TarjetaCredit>> getAllTarjeta(){
        return new ResponseEntity<>(tarjetaClientService.getAllTarjeta(),HttpStatus.OK);
    }





}
