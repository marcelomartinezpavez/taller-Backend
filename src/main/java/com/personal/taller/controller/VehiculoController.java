package com.personal.taller.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.personal.taller.dto.ClienteDto;
import com.personal.taller.dto.VehiculoDto;
import com.personal.taller.repository.ClienteRepository;
import com.personal.taller.repository.VehiculoRepository;
import com.personal.taller.request.VehiculoRequest;
import com.personal.taller.response.VehiculoResponse;

@Controller
@RequestMapping("vehiculos")
//@CrossOrigin(origins = "${taller.server.url}")
public class VehiculoController {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    VehiculoRepository vehiculoRepository;

    @GetMapping(path = "/all", produces = "application/json")
    //@CrossOrigin(origins = "*")
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
    //@CrossOrigin(origins = "*")
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

                clienteDtoSet.add(clte);
            }

            vehResp.setCliente(clienteDtoSet);

        }

        return new ResponseEntity<>(vehRespList, HttpStatus.OK);

    }

    @GetMapping(value = "/{patente}", produces = "application/json")
    //@CrossOrigin(origins = "*")
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

            clienteDtoSet.add(clte);
        }

        vehResp.setCliente(clienteDtoSet);
        return new ResponseEntity(vehResp, HttpStatus.OK);
    }

    @PostMapping(path = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    //@CrossOrigin(origins = "*")
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
                .findByRutAndHabilitado(newVehiculo.getRutDueno());
        ClienteDto cliente = new ClienteDto();

        if (clienteDtoOptional.isPresent()) {
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
    //@CrossOrigin(origins = "*")
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
                    .findByRutAndHabilitado(newVehiculo.getRutDueno());
            ClienteDto cliente = new ClienteDto();

            if (clienteDtoOptional.isPresent()) {
                
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

    @Transactional
    @PutMapping(path = "/changePatente", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity changePatente(@RequestBody VehiculoRequest request) {
        String oldPatente = request.getOldPatente();
        String newPatente = request.getPatente();
        final String TEMP_PATENTE = "AAAA11";

        VehiculoDto existing = vehiculoRepository.findByPatente(oldPatente);
        if (existing == null) {
            return new ResponseEntity("Vehículo no encontrado", HttpStatus.BAD_REQUEST);
        }

        if (!oldPatente.equals(newPatente)) {
            VehiculoDto existsNew = vehiculoRepository.findByPatente(newPatente);
            if (existsNew != null) {
                return new ResponseEntity("La nueva patente ya está registrada", HttpStatus.BAD_REQUEST);
            }
        }

        // Actualizar campos sin cambio de patente
        existing.setMarca(request.getMarca());
        existing.setModelo(request.getModelo());
        existing.setAnio(request.getAnio());
        existing.setColor(request.getColor());
        existing.setKilometraje(request.getKilometraje());
        existing.setNumeroMotor(request.getNumeroMotor());
        existing.setNumeroChasis(request.getNumeroChasis());
        existing.setRutDueno(request.getRutDueno());
        existing.setHabilitado(request.getHabilitado());

        if (oldPatente.equals(newPatente)) {
            vehiculoRepository.save(existing);
            return new ResponseEntity(existing, HttpStatus.OK);
        }

        // 1. Crear vehículo temporal con patente "AAAA11"
        VehiculoDto temp = new VehiculoDto();
        temp.setPatente(TEMP_PATENTE);
        temp.setMarca("TEMP");
        temp.setModelo("TEMP");
        temp.setHabilitado(true);
        temp.setRutDueno(existing.getRutDueno());
        temp.setAnio("");
        temp.setColor("");
        temp.setKilometraje("");
        temp.setNumeroMotor("");
        temp.setNumeroChasis("");
        vehiculoRepository.save(temp);

        // 2. Mover todas las asociaciones de la patente antigua → "AAAA11"
        vehiculoRepository.updateVehiculoClientePatente(oldPatente, TEMP_PATENTE);
        vehiculoRepository.updateOrdenTrabajoVehiculoPatente(oldPatente, TEMP_PATENTE);
        vehiculoRepository.updateOrdenTrabajoPatenteVehiculo(oldPatente, TEMP_PATENTE);
        vehiculoRepository.updateAgendaPatenteVehiculo(oldPatente, TEMP_PATENTE);

        // 3. Cambiar la patente del vehículo
        vehiculoRepository.updateVehiculoPatente(oldPatente, newPatente);

        // 4. Mover todas las asociaciones de "AAAA11" → nueva patente
        vehiculoRepository.updateVehiculoClientePatente(TEMP_PATENTE, newPatente);
        vehiculoRepository.updateOrdenTrabajoVehiculoPatente(TEMP_PATENTE, newPatente);
        vehiculoRepository.updateOrdenTrabajoPatenteVehiculo(TEMP_PATENTE, newPatente);
        vehiculoRepository.updateAgendaPatenteVehiculo(TEMP_PATENTE, newPatente);

        // 5. Eliminar vehículo temporal
        vehiculoRepository.deleteByPatente(TEMP_PATENTE);

        // 6. Actualizar los demás campos
        VehiculoDto updated = vehiculoRepository.findByPatente(newPatente);
        if (updated != null) {
            updated.setMarca(request.getMarca());
            updated.setModelo(request.getModelo());
            updated.setAnio(request.getAnio());
            updated.setColor(request.getColor());
            updated.setKilometraje(request.getKilometraje());
            updated.setNumeroMotor(request.getNumeroMotor());
            updated.setNumeroChasis(request.getNumeroChasis());
            updated.setRutDueno(request.getRutDueno());
            updated.setHabilitado(request.getHabilitado());
            vehiculoRepository.save(updated);
            return new ResponseEntity(updated, HttpStatus.OK);
        }

        return new ResponseEntity("Error al cambiar patente", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(path = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    //@CrossOrigin(origins = "*")
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
