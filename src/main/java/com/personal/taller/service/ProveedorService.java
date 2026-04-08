package com.personal.taller.service;

import com.personal.taller.dto.ProveedorDto;
import com.personal.taller.request.ProveedorRequest;
import org.springframework.http.ResponseEntity;

public interface ProveedorService {

    ResponseEntity getAllProveedor();

    ResponseEntity getProveedor(String rut);

    ResponseEntity create(ProveedorRequest newProveedor);

    ResponseEntity update(ProveedorRequest newProveedor);

    ResponseEntity delete(ProveedorDto newProveedor);
}
