package com.personal.taller.service.impl;

import com.personal.taller.dto.ClienteDto;
import com.personal.taller.repository.ClienteRepository;
import com.personal.taller.request.ClienteRequest;
import com.personal.taller.response.ClienteResponse;
import com.personal.taller.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public ResponseEntity getAll(){
        System.out.println("getAll");
        List<ClienteDto> resp = clienteRepository.findAllHabilitado();
        List<ClienteDto> clienteDtoList = new ArrayList<ClienteDto>();
        for (int i = 0; i<resp.size();i++){
            ClienteDto cliente = new ClienteDto();
            ClienteDto clienteResp = resp.get(i);

            cliente.setId(clienteResp.getId());
            cliente.setHabilitado(clienteResp.getHabilitado());
            cliente.setApellido(clienteResp.getApellido());
            cliente.setCiudad(clienteResp.getCiudad());
            cliente.setComuna(clienteResp.getComuna());
            cliente.setDireccion(clienteResp.getDireccion());
            cliente.setEmail(clienteResp.getEmail());
            cliente.setNombre(clienteResp.getNombre());
            cliente.setRut(clienteResp.getRut());
            cliente.setTelefono(clienteResp.getTelefono());
            clienteDtoList.add(cliente);
        }
        return new ResponseEntity(clienteDtoList, HttpStatus.OK);
    }

    public ResponseEntity getClientByCompany(){
        ClienteResponse clienteResponse = new ClienteResponse();
        List<ClienteDto> resp = clienteRepository.findAll();

        List<ClienteDto> clienteDtoList = new ArrayList<ClienteDto>();
        
        //Optional<EmpresaDto> respEmpresa = empresaRepository.findById(newCliente.getIdEmpresa());
        for (int i = 0; i<resp.size();i++){
            ClienteDto cliente = new ClienteDto();

            ClienteDto clienteResp = resp.get(i);

            cliente.setId(clienteResp.getId());
            cliente.setHabilitado(clienteResp.getHabilitado());
            cliente.setApellido(clienteResp.getApellido());
            cliente.setCiudad(clienteResp.getCiudad());
            cliente.setComuna(clienteResp.getComuna());
            cliente.setDireccion(clienteResp.getDireccion());
            cliente.setEmail(clienteResp.getEmail());
            cliente.setNombre(clienteResp.getNombre());
            cliente.setRut(clienteResp.getRut());
            cliente.setTelefono(clienteResp.getTelefono());
            clienteDtoList.add(cliente);
        }
        return new ResponseEntity(clienteDtoList,HttpStatus.OK);
    }

    public ResponseEntity getClientByRut(String rut){
        Optional<ClienteDto> clientes = clienteRepository.findByRutAndHabilitado(rut);
        if(clientes.isPresent()){
            ClienteDto cliente= new ClienteDto();
            
            ClienteDto clienteResp = clientes.get();
            
            cliente.setId(clienteResp.getId());
            cliente.setHabilitado(clienteResp.getHabilitado());
            cliente.setApellido(clienteResp.getApellido());
            cliente.setCiudad(clienteResp.getCiudad());
            cliente.setComuna(clienteResp.getComuna());
            cliente.setDireccion(clienteResp.getDireccion());
            cliente.setEmail(clienteResp.getEmail());
            cliente.setNombre(clienteResp.getNombre());
            cliente.setRut(clienteResp.getRut());
            cliente.setTelefono(clienteResp.getTelefono());
            return new ResponseEntity(cliente,HttpStatus.OK);

        }else{
            return new ResponseEntity("Cliente no se encuentra",HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity createClient(ClienteRequest newCliente){

        Optional<ClienteDto> clienteDtoOptional = clienteRepository.findByRutAndHabilitado(newCliente.getRut());
        if (clienteDtoOptional.isPresent()){
            if (!clienteDtoOptional.get().getHabilitado()){
                return new ResponseEntity("Cliente ya se encuentra registrado y esta deshabilitado",HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity("Cliente ya existe para esta empresa",HttpStatus.BAD_REQUEST);
        }

        ClienteDto cliente = new ClienteDto();
        //Optional<EmpresaDto> respEmpresa = empresaRepository.findById(newCliente.getIdEmpresa());

        //if(respEmpresa.isPresent()) {
            cliente.setHabilitado(newCliente.getHabilitado());
            cliente.setApellido(newCliente.getApellido());
            cliente.setCiudad(newCliente.getCiudad());
            cliente.setComuna(newCliente.getComuna());
            cliente.setDireccion(newCliente.getDireccion());
            cliente.setEmail(newCliente.getEmail());
            cliente.setNombre(newCliente.getNombre());
            cliente.setRut(newCliente.getRut());
            cliente.setTelefono(newCliente.getTelefono());
          //  cliente.setEmpresa(respEmpresa.get());
        /*}else{
            return new ResponseEntity("Error Empresa no existe",HttpStatus.BAD_REQUEST);
        }*/
        try {
            clienteRepository.save(cliente);
        }catch (Exception e){
            return new ResponseEntity("Error interno al crear Cliente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ClienteDto>(cliente, HttpStatus.CREATED);
    }

    public ResponseEntity updateClient(ClienteRequest newCliente){
        ClienteDto cliente = new ClienteDto();
        
        //Optional<EmpresaDto> respEmpresa = empresaRepository.findById(newCliente.getIdEmpresa());
        //if(respEmpresa.isPresent()) {
            cliente.setId(newCliente.getId());
            cliente.setHabilitado(newCliente.getHabilitado());
            cliente.setApellido(newCliente.getApellido());
            cliente.setCiudad(newCliente.getCiudad());
            cliente.setComuna(newCliente.getComuna());
            cliente.setDireccion(newCliente.getDireccion());
            cliente.setEmail(newCliente.getEmail());
            cliente.setNombre(newCliente.getNombre());
            cliente.setRut(newCliente.getRut());
            cliente.setTelefono(newCliente.getTelefono());
           // cliente.setEmpresa(respEmpresa.get());
        /*}else{
            return new ResponseEntity("Error Empresa no existe",HttpStatus.BAD_REQUEST);
        }*/

        clienteRepository.save(cliente);

        return new ResponseEntity(cliente, HttpStatus.CREATED);
    }

    public ResponseEntity deleteClient(ClienteRequest newCliente){
        ClienteDto cliente = clienteRepository.getById(newCliente.getId());
        if(cliente != null){
            cliente.setHabilitado(false);
            clienteRepository.save(cliente);
            return new ResponseEntity(cliente,HttpStatus.OK);
        }
        /*Optional<EmpresaDto> respEmpresa = empresaRepository.findById(newCliente.getIdEmpresa());

        if(respEmpresa.isPresent()) {
            cliente.setId(newCliente.getId());
            cliente.setHabilitado(0);
            cliente.setApellido(newCliente.getApellido());
            cliente.setCiudad(newCliente.getCiudad());
            cliente.setComuna(newCliente.getComuna());
            cliente.setDireccion(newCliente.getDireccion());
            cliente.setEmail(newCliente.getEmail());
            cliente.setNombre(newCliente.getNombre());
            cliente.setRut(newCliente.getRut());
            cliente.setTelefono(newCliente.getTelefono());
            cliente.setEmpresa(respEmpresa.get());
        }else{
            return new ResponseEntity("Error Empresa no existe",HttpStatus.BAD_REQUEST);
        }

        try {
            clienteRepository.save(cliente);
        }catch (Exception e){
            return new ResponseEntity("Error interno al eliminar cliente",HttpStatus.INTERNAL_SERVER_ERROR);
        }*/
        return new ResponseEntity("Cliente no encontrado", HttpStatus.BAD_REQUEST);
    }

}
