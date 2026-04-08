package com.personal.taller.service.impl;

import com.personal.taller.dto.ClienteDto;
import com.personal.taller.dto.ProveedorDto;
import com.personal.taller.repository.ProveedorRepository;
import com.personal.taller.request.ProveedorRequest;
import com.personal.taller.response.ProveedorResponse;
import com.personal.taller.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProveedorServiceImpl implements ProveedorService {

    @Autowired
    ProveedorRepository proveedorRepository;

    public ResponseEntity getAllProveedor(){
        List<ProveedorDto> proveedorDtoList = proveedorRepository.findAllHabilitado();

        List<ProveedorDto> proveedorDtoListResp = new ArrayList<ProveedorDto>();
        for (int i = 0; i<proveedorDtoList.size();i++){
            ProveedorDto proveedor = new ProveedorDto();
            ProveedorDto proveedorResp = proveedorDtoList.get(i);
            proveedor.setHabilitado(proveedorResp.getHabilitado());
            proveedor.setNombre(proveedorResp.getNombre());
            proveedor.setApellido(proveedorResp.getApellido());
            proveedor.setRut(proveedorResp.getRut());
            proveedor.setDireccion(proveedorResp.getDireccion());
            proveedor.setComuna(proveedorResp.getComuna());
            proveedor.setCiudad(proveedorResp.getCiudad());
            proveedor.setTelefono(proveedorResp.getTelefono());
            proveedor.setEmail(proveedorResp.getEmail());
            
            proveedorDtoListResp.add(proveedor);
        }
        return new ResponseEntity(proveedorDtoListResp, HttpStatus.OK);
    }

    public ResponseEntity getAllProveedorPorEmpresa(long idempresa){
        ProveedorResponse proveedorResponse = new ProveedorResponse();
        List<ProveedorDto> resp = proveedorRepository.findAll();

        List<ProveedorDto> proveedorDtoList = new ArrayList<ProveedorDto>();
        
        //Optional<EmpresaDto> respEmpresa = empresaRepository.findById(newCliente.getIdEmpresa());
        for (int i = 0; i<resp.size();i++){
            ProveedorDto proveedor = new ProveedorDto();

            ProveedorDto proveedorResp = resp.get(i);

            proveedor.setId(proveedorResp.getId());
            proveedor.setHabilitado(proveedorResp.getHabilitado());
            proveedor.setApellido(proveedorResp.getApellido());
            proveedor.setCiudad(proveedorResp.getCiudad());
            proveedor.setComuna(proveedorResp.getComuna());
            proveedor.setDireccion(proveedorResp.getDireccion());
            proveedor.setEmail(proveedorResp.getEmail());
            proveedor.setNombre(proveedorResp.getNombre());
            proveedor.setRut(proveedorResp.getRut());
            proveedor.setTelefono(proveedorResp.getTelefono());
            proveedorDtoList.add(proveedor);
        }
        return new ResponseEntity(proveedorDtoList,HttpStatus.OK);
    }

    public ResponseEntity getProveedor(String rut){
        Optional<ProveedorDto> proveedores = proveedorRepository.findByRutAndHabilitado(rut);
        if(proveedores.isPresent()){
            ProveedorDto proveedor= new ProveedorDto();

            ProveedorDto clienteResp = proveedores.get();
            proveedor.setId(clienteResp.getId());
            proveedor.setHabilitado(clienteResp.getHabilitado());
            proveedor.setApellido(clienteResp.getApellido());
            proveedor.setCiudad(clienteResp.getCiudad());
            proveedor.setComuna(clienteResp.getComuna());
            proveedor.setDireccion(clienteResp.getDireccion());
            proveedor.setEmail(clienteResp.getEmail());
            proveedor.setNombre(clienteResp.getNombre());
            proveedor.setRut(clienteResp.getRut());
            proveedor.setTelefono(clienteResp.getTelefono());
            return new ResponseEntity(proveedor, HttpStatus.OK);

        }else{
            return new ResponseEntity("Proveedor no se encuentra",HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity create(ProveedorRequest newProveedor){

        Optional<ProveedorDto> proveedorDtoOptional = proveedorRepository.findByRutAndHabilitado(newProveedor.getRut());
        if (proveedorDtoOptional.isPresent()){
            if (!proveedorDtoOptional.get().getHabilitado()){
                return new ResponseEntity("Proveedor ya se encuentra registrado y esta deshabilitado",HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity("Proveedor ya existe para esta empresa",HttpStatus.BAD_REQUEST);
        }

        ProveedorDto proveedor = new ProveedorDto();
        
        //if(respEmpresa.isPresent()) {
            proveedor.setHabilitado(newProveedor.getHabilitado());
            proveedor.setApellido(newProveedor.getApellido());
            proveedor.setCiudad(newProveedor.getCiudad());
            proveedor.setComuna(newProveedor.getComuna());
            proveedor.setDireccion(newProveedor.getDireccion());
            proveedor.setEmail(newProveedor.getEmail());
            proveedor.setNombre(newProveedor.getNombre());
            proveedor.setRut(newProveedor.getRut());
            proveedor.setTelefono(newProveedor.getTelefono());
            //proveedor.setEmpresa(respEmpresa.get());
        /*}else{
            return new ResponseEntity("Error Empresa no existe",HttpStatus.BAD_REQUEST);
        }*/
        try {
            proveedorRepository.save(proveedor);
        }catch (Exception e){
            return new ResponseEntity("Error interno al crear Proveedor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(proveedor, HttpStatus.CREATED);
    }

    public ResponseEntity update(ProveedorRequest newProveedor){
        ProveedorDto proveedor = new ProveedorDto();
        //if(respEmpresa.isPresent()) {
            proveedor.setId(newProveedor.getId());
            proveedor.setHabilitado(newProveedor.getHabilitado());
            proveedor.setApellido(newProveedor.getApellido());
            proveedor.setCiudad(newProveedor.getCiudad());
            proveedor.setComuna(newProveedor.getComuna());
            proveedor.setDireccion(newProveedor.getDireccion());
            proveedor.setEmail(newProveedor.getEmail());
            proveedor.setNombre(newProveedor.getNombre());
            proveedor.setRut(newProveedor.getRut());
            proveedor.setTelefono(newProveedor.getTelefono());
        //    proveedor.setEmpresa(respEmpresa.get());
        //}else{
        //    return new ResponseEntity("Error Empresa no existe",HttpStatus.BAD_REQUEST);
        //}

        proveedorRepository.save(proveedor);

        return new ResponseEntity(proveedor, HttpStatus.CREATED);
    }

    public ResponseEntity delete(ProveedorDto newProveedor){
        Optional<ProveedorDto> proveedor = proveedorRepository.findById(newProveedor.getId());
        if(proveedor.isPresent()){
            proveedor.get().setHabilitado(false);
            proveedorRepository.save(proveedor.get());
        }else{
            return new ResponseEntity("Error proveedor no existe.",HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(proveedor, HttpStatus.OK);

    }

}
