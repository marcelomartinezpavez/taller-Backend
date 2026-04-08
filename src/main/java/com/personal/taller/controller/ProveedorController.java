package com.personal.taller.controller;

import com.personal.taller.dto.ProveedorDto;
import com.personal.taller.request.ProveedorRequest;
import com.personal.taller.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("proveedor")
public class ProveedorController {

    @Autowired
    ProveedorService proveedorService;

    @GetMapping(path = "/all", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody ResponseEntity getAllProveedor() {
        return proveedorService.getAllProveedor();
    }

    @GetMapping(value = "/{rut}", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    ResponseEntity getProveedor(@PathVariable String rut) {
        return proveedorService.getProveedor(rut);
    }

    @PostMapping(path = "/insert",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity create(@RequestBody ProveedorRequest newProveedor) {
        return proveedorService.create(newProveedor);
    }

    @PutMapping(path = "/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<ProveedorDto> update(@RequestBody ProveedorRequest newProveedor) {
        return proveedorService.update(newProveedor);
    }

    @DeleteMapping(path = "/delete",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<ProveedorDto> delete(@RequestBody ProveedorDto newProveedor) {
        return proveedorService.delete(newProveedor);
    }
}
