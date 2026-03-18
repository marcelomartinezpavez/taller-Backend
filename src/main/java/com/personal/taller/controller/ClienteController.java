package com.personal.taller.controller;

import com.personal.taller.dto.ClienteDto;
import com.personal.taller.request.ClienteRequest;
import com.personal.taller.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping(path = "/all", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody ResponseEntity getAllCliente() {
        return clienteService.getAll();
    }

    @GetMapping(path = "/empresa/{idempresa}", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody ResponseEntity getAllClientePorEmpresa(@PathVariable long idempresa) {
        return clienteService.getClientByCompany(idempresa);
    }

    @GetMapping(value = "/{rut}", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody ResponseEntity getCliente(@PathVariable String rut) {
        return clienteService.getClientByRut(rut);
    }

    @PostMapping(path = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<ClienteDto> create(@RequestBody ClienteRequest newCliente) {
        return clienteService.createClient(newCliente);
    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<ClienteDto> update(@RequestBody ClienteRequest newCliente) {
        return clienteService.updateClient(newCliente);
    }

    @DeleteMapping(path = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<ClienteDto> delete(@RequestBody ClienteRequest newCliente) {
        return clienteService.deleteClient(newCliente);
    }
}
