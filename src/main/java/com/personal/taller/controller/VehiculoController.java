package com.personal.taller.controller;

import com.personal.taller.dto.ClienteDto;
import com.personal.taller.dto.EmpresaDto;
import com.personal.taller.dto.VehiculoDto;
import com.personal.taller.repository.ClienteRepository;
import com.personal.taller.repository.VehiculoRepository;
import com.personal.taller.request.VehiculoRequest;
import com.personal.taller.response.VehiculoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("vehiculos")
public class VehiculoController {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    VehiculoRepository vehiculoRepository;

    @GetMapping(path = "/all", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody ResponseEntity getAllVehiculo() {
        System.out.println("getAll Vehiculo");

        List<VehiculoDto> vehiculoDtoList = vehiculoRepository.findAllHabilitado();
        List<VehiculoDto> vehRespList = new ArrayList<>();
        for (VehiculoDto vehiculo : vehiculoDtoList) {
            VehiculoDto vehResp = new VehiculoDto();
            vehResp.setId(vehiculo.getId());
            vehResp.setRutDueno(vehiculo.getRutDueno());
            vehResp.setNumeroMotor(vehiculo.getNumeroMotor());
            vehResp.setKilometraje(vehiculo.getKilometraje());
            vehResp.setModelo(vehiculo.getModelo());
            vehResp.setMarca(vehiculo.getMarca());
            vehResp.setHabilitado(vehiculo.getHabilitado());
            vehResp.setColor(vehiculo.getColor());
            vehResp.setNumeroChasis(vehiculo.getNumeroChasis());
            vehResp.setAnio(vehiculo.getAnio());
            vehResp.setPatente(vehiculo.getPatente());
            vehRespList.add(vehResp);
        }

        VehiculoResponse vehiculoResponse = new VehiculoResponse();
        vehiculoResponse.setVehiculoDtoList(vehRespList);
        return new ResponseEntity<>(vehRespList, HttpStatus.OK);
    }

    @GetMapping(value = "/cliente/{rutCliente}", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody ResponseEntity getVehiculoByCliente(@PathVariable String rutCliente) {

        List<VehiculoDto> vehiculoDtoList = vehiculoRepository.findByRutDueno(rutCliente);

        List<VehiculoDto> vehRespList = new ArrayList<>();
        for (VehiculoDto vehiculo : vehiculoDtoList) {
            VehiculoDto vehResp = new VehiculoDto();
            vehResp.setId(vehiculo.getId());
            vehResp.setRutDueno(vehiculo.getRutDueno());
            vehResp.setNumeroMotor(vehiculo.getNumeroMotor());
            vehResp.setKilometraje(vehiculo.getKilometraje());
            vehResp.setModelo(vehiculo.getModelo());
            vehResp.setMarca(vehiculo.getMarca());
            vehResp.setHabilitado(vehiculo.getHabilitado());
            vehResp.setColor(vehiculo.getColor());
            vehResp.setNumeroChasis(vehiculo.getNumeroChasis());
            vehResp.setAnio(vehiculo.getAnio());
            vehResp.setPatente(vehiculo.getPatente());
            vehRespList.add(vehResp);

            Set<ClienteDto> clienteDtoSet = new HashSet<>();
            for (ClienteDto clienteDto : vehiculo.getCliente()) {
                ClienteDto clte = new ClienteDto();
                clte.setId(clienteDto.getId());
                clte.setApellido(clienteDto.getApellido());
                clte.setHabilitado(clienteDto.getHabilitado());
                clte.setDireccion(clienteDto.getDireccion());
                clte.setCiudad(clienteDto.getCiudad());
                clte.setNombre(clienteDto.getNombre());
                clte.setRut(clienteDto.getRut());
                clte.setComuna(clienteDto.getComuna());
                clte.setEmail(clienteDto.getEmail());
                clte.setTelefono(clienteDto.getTelefono());

                EmpresaDto empresaDto = new EmpresaDto();
                empresaDto.setId(clienteDto.getEmpresa().getId());
                empresaDto.setNombre(clienteDto.getEmpresa().getNombre());
                empresaDto.setRut(clienteDto.getEmpresa().getRut());
                empresaDto.setDireccion(clienteDto.getEmpresa().getDireccion());

                clte.setEmpresa(empresaDto);
                clienteDtoSet.add(clte);
            }

            vehResp.setCliente(clienteDtoSet);

        }

        return new ResponseEntity<>(vehRespList, HttpStatus.OK);

    }

    @GetMapping(value = "/{patente}", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody ResponseEntity getVehiculo(@PathVariable String patente) {
        VehiculoDto vehiculo = vehiculoRepository.findByPatente(patente);
        if (vehiculo == null) {
            return new ResponseEntity("Vehiculo no se encuentra", HttpStatus.NO_CONTENT);
        }
        VehiculoDto vehResp = new VehiculoDto();
        vehResp.setId(vehiculo.getId());
        vehResp.setRutDueno(vehiculo.getRutDueno());
        vehResp.setNumeroMotor(vehiculo.getNumeroMotor());
        vehResp.setKilometraje(vehiculo.getKilometraje());
        vehResp.setModelo(vehiculo.getModelo());
        vehResp.setMarca(vehiculo.getMarca());
        vehResp.setHabilitado(vehiculo.getHabilitado());
        vehResp.setColor(vehiculo.getColor());
        vehResp.setNumeroChasis(vehiculo.getNumeroChasis());
        vehResp.setAnio(vehiculo.getAnio());
        vehResp.setPatente(vehiculo.getPatente());

        Set<ClienteDto> clienteDtoSet = new HashSet<>();
        for (ClienteDto clienteDto : vehiculo.getCliente()) {
            ClienteDto clte = new ClienteDto();
            clte.setId(clienteDto.getId());
            clte.setApellido(clienteDto.getApellido());
            clte.setHabilitado(clienteDto.getHabilitado());
            clte.setDireccion(clienteDto.getDireccion());
            clte.setCiudad(clienteDto.getCiudad());
            clte.setNombre(clienteDto.getNombre());
            clte.setRut(clienteDto.getRut());
            clte.setComuna(clienteDto.getComuna());
            clte.setEmail(clienteDto.getEmail());
            clte.setTelefono(clienteDto.getTelefono());

            EmpresaDto empresaDto = new EmpresaDto();
            empresaDto.setId(clienteDto.getEmpresa().getId());
            empresaDto.setNombre(clienteDto.getEmpresa().getNombre());
            empresaDto.setRut(clienteDto.getEmpresa().getRut());
            empresaDto.setDireccion(clienteDto.getEmpresa().getDireccion());

            clte.setEmpresa(empresaDto);
            clienteDtoSet.add(clte);
        }

        vehResp.setCliente(clienteDtoSet);
        return new ResponseEntity(vehResp, HttpStatus.OK);
    }

    @GetMapping(value = "/{patente}/empresa/{empresa}", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody ResponseEntity getVehiculoByEmpresa(@PathVariable String patente, @PathVariable long empresa) {
        VehiculoDto vehiculo = vehiculoRepository.findByPatenteAndEmpresa(patente, empresa);
        if (vehiculo == null) {
            return new ResponseEntity("Vehiculo no se encuentra", HttpStatus.NO_CONTENT);
        }
        VehiculoDto vehResp = new VehiculoDto();
        vehResp.setId(vehiculo.getId());
        vehResp.setRutDueno(vehiculo.getRutDueno());
        vehResp.setNumeroMotor(vehiculo.getNumeroMotor());
        vehResp.setKilometraje(vehiculo.getKilometraje());
        vehResp.setModelo(vehiculo.getModelo());
        vehResp.setMarca(vehiculo.getMarca());
        vehResp.setHabilitado(vehiculo.getHabilitado());
        vehResp.setColor(vehiculo.getColor());
        vehResp.setNumeroChasis(vehiculo.getNumeroChasis());
        vehResp.setAnio(vehiculo.getAnio());
        vehResp.setPatente(vehiculo.getPatente());

        Set<ClienteDto> clienteDtoSet = new HashSet<>();
        for (ClienteDto clienteDto : vehiculo.getCliente()) {
            ClienteDto clte = new ClienteDto();
            clte.setId(clienteDto.getId());
            clte.setApellido(clienteDto.getApellido());
            clte.setHabilitado(clienteDto.getHabilitado());
            clte.setDireccion(clienteDto.getDireccion());
            clte.setCiudad(clienteDto.getCiudad());
            clte.setNombre(clienteDto.getNombre());
            clte.setRut(clienteDto.getRut());
            clte.setComuna(clienteDto.getComuna());
            clte.setEmail(clienteDto.getEmail());
            clte.setTelefono(clienteDto.getTelefono());

            EmpresaDto empresaDto = new EmpresaDto();
            empresaDto.setId(clienteDto.getEmpresa().getId());
            empresaDto.setNombre(clienteDto.getEmpresa().getNombre());
            empresaDto.setRut(clienteDto.getEmpresa().getRut());
            empresaDto.setDireccion(clienteDto.getEmpresa().getDireccion());

            clte.setEmpresa(empresaDto);
            clienteDtoSet.add(clte);
        }

        vehResp.setCliente(clienteDtoSet);

        return new ResponseEntity(vehResp, HttpStatus.OK);
    }

    @PostMapping(path = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity create(@RequestBody VehiculoRequest newVehiculo) {

        VehiculoDto vehiculoDto = new VehiculoDto();
        vehiculoDto.setHabilitado(newVehiculo.getHabilitado());
        vehiculoDto.setMarca(newVehiculo.getMarca());
        vehiculoDto.setModelo(newVehiculo.getModelo());
        vehiculoDto.setPatente(newVehiculo.getPatente());
        vehiculoDto.setAnio(newVehiculo.getAnio());
        vehiculoDto.setColor(newVehiculo.getColor());
        vehiculoDto.setKilometraje(newVehiculo.getKilometraje());
        vehiculoDto.setModelo(newVehiculo.getModelo());
        vehiculoDto.setNumeroChasis(newVehiculo.getNumeroChasis());
        vehiculoDto.setNumeroMotor(newVehiculo.getNumeroMotor());
        vehiculoDto.setRutDueno(newVehiculo.getRutDueno());
        if (newVehiculo.getRutDueno() == null) {
            return new ResponseEntity("Vehiculo debe tener dueño", HttpStatus.BAD_REQUEST);
        }

        Optional<ClienteDto> clienteDtoOptional = clienteRepository
                .findByRutAndHabilitadoAndEmpresa(newVehiculo.getRutDueno(), newVehiculo.getId_empresa());
        ClienteDto cliente = new ClienteDto();

        if (clienteDtoOptional.isPresent()) {
            EmpresaDto empresaDto = new EmpresaDto();
            Set<ClienteDto> clienteDtoSet = new HashSet<>();
            cliente.setId(clienteDtoOptional.get().getId());
            cliente.setRut(clienteDtoOptional.get().getRut());
            cliente.setNombre(clienteDtoOptional.get().getNombre());
            cliente.setDireccion(clienteDtoOptional.get().getDireccion());
            cliente.setTelefono(clienteDtoOptional.get().getTelefono());
            cliente.setEmail(clienteDtoOptional.get().getEmail());
            cliente.setComuna(clienteDtoOptional.get().getComuna());
            cliente.setCiudad(clienteDtoOptional.get().getCiudad());
            cliente.setHabilitado(clienteDtoOptional.get().getHabilitado());
            cliente.setApellido(clienteDtoOptional.get().getApellido());

            empresaDto.setDireccion(clienteDtoOptional.get().getEmpresa().getDireccion());
            empresaDto.setNombre(clienteDtoOptional.get().getEmpresa().getNombre());
            empresaDto.setRut(clienteDtoOptional.get().getEmpresa().getRut());
            empresaDto.setId(clienteDtoOptional.get().getEmpresa().getId());

            cliente.setEmpresa(empresaDto);
            clienteDtoSet.add(cliente);

            vehiculoDto.setCliente(clienteDtoSet);
        } else {
            return new ResponseEntity("Dueño de vehiculo no se encuentra", HttpStatus.BAD_REQUEST);
        }

        try {
            VehiculoDto vehiculoAgregado = vehiculoRepository.save(vehiculoDto);

        } catch (Exception e) {
            return new ResponseEntity("Ocurrio un error interno", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(vehiculoDto, HttpStatus.CREATED);

    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity update(@RequestBody VehiculoRequest newVehiculo) {
        try {

            VehiculoDto vehiculoDto = new VehiculoDto();
            vehiculoDto.setId(newVehiculo.getId());
            vehiculoDto.setHabilitado(newVehiculo.getHabilitado());
            vehiculoDto.setMarca(newVehiculo.getMarca());
            vehiculoDto.setModelo(newVehiculo.getModelo());
            vehiculoDto.setPatente(newVehiculo.getPatente());
            vehiculoDto.setAnio(newVehiculo.getAnio());
            vehiculoDto.setColor(newVehiculo.getColor());
            vehiculoDto.setKilometraje(newVehiculo.getKilometraje());
            vehiculoDto.setModelo(newVehiculo.getModelo());
            vehiculoDto.setNumeroChasis(newVehiculo.getNumeroChasis());
            vehiculoDto.setNumeroMotor(newVehiculo.getNumeroMotor());
            vehiculoDto.setRutDueno(newVehiculo.getRutDueno());
            if (newVehiculo.getRutDueno() == null) {
                return new ResponseEntity("Vehiculo debe tener dueño", HttpStatus.BAD_REQUEST);
            }

            Optional<ClienteDto> clienteDtoOptional = clienteRepository
                    .findByRutAndHabilitadoAndEmpresa(newVehiculo.getRutDueno(), newVehiculo.getId_empresa());
            ClienteDto cliente = new ClienteDto();

            if (clienteDtoOptional.isPresent()) {
                EmpresaDto empresaDto = new EmpresaDto();
                Set<ClienteDto> clienteDtoSet = new HashSet<>();
                cliente.setId(clienteDtoOptional.get().getId());
                cliente.setRut(clienteDtoOptional.get().getRut());
                cliente.setNombre(clienteDtoOptional.get().getNombre());
                cliente.setDireccion(clienteDtoOptional.get().getDireccion());
                cliente.setTelefono(clienteDtoOptional.get().getTelefono());
                cliente.setEmail(clienteDtoOptional.get().getEmail());
                cliente.setComuna(clienteDtoOptional.get().getComuna());
                cliente.setCiudad(clienteDtoOptional.get().getCiudad());
                cliente.setHabilitado(clienteDtoOptional.get().getHabilitado());
                cliente.setApellido(clienteDtoOptional.get().getApellido());

                empresaDto.setDireccion(clienteDtoOptional.get().getEmpresa().getDireccion());
                empresaDto.setNombre(clienteDtoOptional.get().getEmpresa().getNombre());
                empresaDto.setRut(clienteDtoOptional.get().getEmpresa().getRut());
                empresaDto.setId(clienteDtoOptional.get().getEmpresa().getId());

                cliente.setEmpresa(empresaDto);
                clienteDtoSet.add(cliente);

                vehiculoDto.setCliente(clienteDtoSet);
            } else {
                return new ResponseEntity("Dueño de vehiculo no se encuentra", HttpStatus.BAD_REQUEST);
            }

            try {
                VehiculoDto vehiculoAgregado = vehiculoRepository.save(vehiculoDto);

            } catch (Exception e) {
                return new ResponseEntity("Ocurrio un error interno", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new ResponseEntity(vehiculoDto, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity("Ocurrio un error interno", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping(path = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<VehiculoDto> delete(@RequestBody VehiculoRequest newVehiculo) {

        try {

            Optional<VehiculoDto> vehiculoDtoOptional = vehiculoRepository.findById(newVehiculo.getId());
            if (vehiculoDtoOptional.isPresent()) {
                VehiculoDto vehiculoDto = vehiculoDtoOptional.get();
                vehiculoDto.setHabilitado(false);
                vehiculoRepository.save(vehiculoDto);
                return new ResponseEntity(vehiculoDto, HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity("Ocurrio un error interno", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity("Vehiculo no encontrado", HttpStatus.NOT_FOUND);
    }

}
