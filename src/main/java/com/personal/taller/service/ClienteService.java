package com.personal.taller.service;

import com.personal.taller.request.ClienteRequest;
import org.springframework.http.ResponseEntity;

public interface ClienteService {

    ResponseEntity getAll();

    ResponseEntity getClientByCompany();

    ResponseEntity getClientByRut(String rut);

    ResponseEntity createClient(ClienteRequest newCliente);

    ResponseEntity updateClient(ClienteRequest newCliente);

    ResponseEntity changeRut(ClienteRequest request);

    ResponseEntity deleteClient(ClienteRequest newCliente);

}
