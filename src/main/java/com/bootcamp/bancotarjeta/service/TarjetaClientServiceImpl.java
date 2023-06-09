package com.bootcamp.bancotarjeta.service;


import com.bootcamp.bancotarjeta.model.document.Movimientos;
import com.bootcamp.bancotarjeta.model.document.TarjetaClient;
import com.bootcamp.bancotarjeta.model.document.TarjetaCredit;
import com.bootcamp.bancotarjeta.model.document.TarjetaCreditClient;
import com.bootcamp.bancotarjeta.model.repository.TarjetaClientRepository;
import com.bootcamp.bancotarjeta.model.repository.TarjetaCreditRepository;
import com.bootcamp.bancotarjeta.model.service.TarjetaClientService;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.adapter.rxjava.RxJava3Adapter;
import reactor.core.publisher.Flux;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class TarjetaClientServiceImpl implements TarjetaClientService {

    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

    @Autowired
    private TarjetaClientRepository tarjetaClientRepository;

     @Autowired
     private TarjetaCreditRepository tarjetaCreditRepository;

    @Override
    public Maybe<TarjetaClient> save(TarjetaClient tarjetaClient) {
        return this.tarjetaClientRepository.save(tarjetaClient)
                .flatMap(tarjetaClientRepository::save)
                .as(RxJava3Adapter::monoToMaybe);
    }

    @Override
    public Maybe<TarjetaClient> getTarjetaClientByClient(String codigoCliente) {
        Query query=new Query();
        query.addCriteria(Criteria.where("cliente.idCliente").in(codigoCliente));

        return mongoTemplate
                .findOne(query,TarjetaClient.class)
                .as(RxJava3Adapter::monoToMaybe);
    }

    @Override
    public Maybe<TarjetaClient> getTarjetaClientByCodigoTarjeta(String codigoTarjeta) {
        Query query=new Query();
        query.addCriteria(Criteria.where("tarjetaCredito.nroTarjetaCredito").in(codigoTarjeta));

        return mongoTemplate
                .findOne(query,TarjetaClient.class)
                .as(RxJava3Adapter::monoToMaybe);
    }

    @Override
    public Maybe<TarjetaClient> getPayConsumption(String codigoTarjeta,double monto) {
        return this.getTarjetaClientByCodigoTarjeta(codigoTarjeta)
                .filter(validar->validar.getSaldo()<validar.getTarjetaCredito().get(0).getLimite())
                .map(cl-> {
                    cl.setSaldo(cl.getSaldo()+monto);
                    List<Movimientos> movimientosList=new ArrayList<>();
                    if(cl.getMovimiento()!=null){
                        movimientosList=cl.getMovimiento();
                    }
                    movimientosList.add(new Movimientos("Pago",monto,getFechaActual(),codigoTarjeta));
                    cl.setMovimiento(movimientosList);
                    return cl;
                })
                .to(RxJava3Adapter::maybeToMono)
                .flatMap(tarjetaClientRepository::save)
                .as(RxJava3Adapter::monoToMaybe);
    }

    @Override
    public Maybe<TarjetaClient> getCarryConsumption(String codigoTarjeta,double monto) {
        return this.getTarjetaClientByCodigoTarjeta(codigoTarjeta)
                .filter(validar->validar.getSaldo()>monto)
                .map(cl-> {
                    cl.setSaldo(cl.getSaldo()-monto);
                    List<Movimientos> movimientosList=new ArrayList<>();
                    if(cl.getMovimiento()!=null){
                        movimientosList=cl.getMovimiento();
                    }
                    movimientosList.add(new Movimientos("Cargo",monto,getFechaActual(),codigoTarjeta));
                    cl.setMovimiento(movimientosList);
                    return cl;
                })
                .to(RxJava3Adapter::maybeToMono)
                .flatMap(tarjetaClientRepository::save)
                .as(RxJava3Adapter::monoToMaybe);
    }

    @Override
    public Flux<TarjetaClient> getAllTarjetaClient() {
        return tarjetaClientRepository.findAll();
    }

    @Override
    public Flux<TarjetaCredit> getAllTarjeta() {
        return this.tarjetaCreditRepository.findAll();
    }


    private String getFechaActual(){
        //Fecha
        SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendarr = Calendar.getInstance();
        Date dateObj = calendarr.getTime();
        String formattedDate = dtf.format(dateObj);
        return formattedDate;
    }
}
