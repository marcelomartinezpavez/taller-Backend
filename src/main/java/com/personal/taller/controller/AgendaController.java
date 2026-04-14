package com.personal.taller.controller;

import com.personal.taller.dto.AgendaDto;
import com.personal.taller.dto.ClienteDto;
import com.personal.taller.dto.VehiculoDto;
import com.personal.taller.repository.AgendaRepository;
import com.personal.taller.repository.ClienteRepository;
import com.personal.taller.repository.VehiculoRepository;
import com.personal.taller.request.AgendaRequest;
import com.personal.taller.util.RutUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("agenda")
public class AgendaController {
    @Autowired
    AgendaRepository agendaRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    VehiculoRepository vehiculoRepository;

    @GetMapping(path = "/all", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody ResponseEntity<?> getAllAgenda() {
        try {
            List<AgendaDto> agendas = agendaRepository.findAllActive();
            return new ResponseEntity<>(agendas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno al buscar agenda", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody ResponseEntity<?> getAgendaById(@PathVariable Long id) {
        try {
            if (id == null) {
                return new ResponseEntity<>("El ID es requerido", HttpStatus.BAD_REQUEST);
            }
            Optional<AgendaDto> agenda = agendaRepository.findById(id);
            if (!agenda.isPresent()) {
                return new ResponseEntity<>("Agenda no encontrada", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(agenda.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno al buscar agenda", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/cliente/{clienteId}", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody ResponseEntity<?> getAgendaByCliente(@PathVariable Long clienteId) {
        try {
            List<AgendaDto> agendas = agendaRepository.findByClienteId(clienteId);
            return new ResponseEntity<>(agendas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno al buscar agenda por cliente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/vehiculo/{vehiculoId}", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody ResponseEntity<?> getAgendaByVehiculo(@PathVariable Long vehiculoId) {
        try {
            List<AgendaDto> agendas = agendaRepository.findByVehiculoId(vehiculoId);
            return new ResponseEntity<>(agendas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno al buscar agenda por vehiculo", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/estado/{estado}", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody ResponseEntity<?> getAgendaByEstado(@PathVariable String estado) {
        try {
            List<AgendaDto> agendas = agendaRepository.findByEstado(estado);
            return new ResponseEntity<>(agendas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno al buscar agenda por estado", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/fecha/{fecha}", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody ResponseEntity<?> getAgendaByFecha(@PathVariable String fecha) {
        try {
            List<AgendaDto> agendas = agendaRepository.findByFechaReservaContains(fecha);
            return new ResponseEntity<>(agendas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno al buscar agenda por fecha", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> create(@RequestBody AgendaRequest request) {
        try {
            if (request.getFechaHoraReserva() == null || request.getFechaHoraReserva().isEmpty()) {
                return new ResponseEntity<>("La fecha y hora de reserva es requerida", HttpStatus.BAD_REQUEST);
            }

            AgendaDto agenda = new AgendaDto();
            agenda.setHabilitado(true);
            agenda.setFechaHoraReserva(request.getFechaHoraReserva());
            agenda.setEstado(request.getEstado() != null ? request.getEstado() : "PENDIENTE");
            agenda.setObservacion(request.getObservacion());
            agenda.setRutCliente(request.getRutCliente());
            agenda.setPatenteVehiculo(request.getPatenteVehiculo());

            if (request.getClienteId() != null) {
                Optional<ClienteDto> clienteOpt = clienteRepository.findById(request.getClienteId());
                if (clienteOpt.isPresent()) {
                    agenda.setCliente(clienteOpt.get());
                    agenda.setRutCliente(clienteOpt.get().getRut());
                }
            } else if (request.getRutCliente() != null && !request.getRutCliente().isEmpty()) {
                String rutNormalizado = RutUtils.normalizarRut(request.getRutCliente());
                Optional<ClienteDto> clienteOpt = clienteRepository.findByRutAndHabilitado(rutNormalizado);
                if (clienteOpt.isPresent()) {
                    agenda.setCliente(clienteOpt.get());
                    agenda.setRutCliente(clienteOpt.get().getRut());
                }
            }

            if (request.getVehiculoId() != null) {
                Optional<VehiculoDto> vehiculoOpt = vehiculoRepository.findById(request.getVehiculoId());
                if (vehiculoOpt.isPresent()) {
                    agenda.setVehiculo(vehiculoOpt.get());
                    agenda.setPatenteVehiculo(vehiculoOpt.get().getPatente());
                }
            } else if (request.getPatenteVehiculo() != null && !request.getPatenteVehiculo().isEmpty()) {
                VehiculoDto vehiculoOpt = vehiculoRepository.findByPatente(request.getPatenteVehiculo().toUpperCase());
                if (vehiculoOpt != null) {
                    agenda.setVehiculo(vehiculoOpt);
                }
            }

            AgendaDto saved = agendaRepository.save(agenda);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error al crear agenda", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> update(@RequestBody AgendaRequest request) {
        try {
            if (request.getId() == null) {
                return new ResponseEntity<>("El ID de la agenda es requerido", HttpStatus.BAD_REQUEST);
            }
            Optional<AgendaDto> agendaOpt = agendaRepository.findById(request.getId());
            if (!agendaOpt.isPresent()) {
                return new ResponseEntity<>("Agenda no encontrada", HttpStatus.NOT_FOUND);
            }

            AgendaDto agenda = agendaOpt.get();
            agenda.setHabilitado(request.isHabilitado());
            agenda.setFechaHoraReserva(request.getFechaHoraReserva());
            agenda.setEstado(request.getEstado());
            agenda.setObservacion(request.getObservacion());
            agenda.setRutCliente(request.getRutCliente());
            agenda.setPatenteVehiculo(request.getPatenteVehiculo());

            if (request.getClienteId() != null) {
                Optional<ClienteDto> clienteOpt = clienteRepository.findById(request.getClienteId());
                if (clienteOpt.isPresent()) {
                    agenda.setCliente(clienteOpt.get());
                    agenda.setRutCliente(clienteOpt.get().getRut());
                }
            } else if (request.getRutCliente() != null && !request.getRutCliente().isEmpty()) {
                String rutNormalizado = RutUtils.normalizarRut(request.getRutCliente());
                Optional<ClienteDto> clienteOpt = clienteRepository.findByRutAndHabilitado(rutNormalizado);
                if (clienteOpt.isPresent()) {
                    agenda.setCliente(clienteOpt.get());
                    agenda.setRutCliente(clienteOpt.get().getRut());
                }
            }

            if (request.getVehiculoId() != null) {
                Optional<VehiculoDto> vehiculoOpt = vehiculoRepository.findById(request.getVehiculoId());
                if (vehiculoOpt.isPresent()) {
                    agenda.setVehiculo(vehiculoOpt.get());
                    agenda.setPatenteVehiculo(vehiculoOpt.get().getPatente());
                }
            } else if (request.getPatenteVehiculo() != null && !request.getPatenteVehiculo().isEmpty()) {
                VehiculoDto vehiculoOpt = vehiculoRepository.findByPatente(request.getPatenteVehiculo().toUpperCase());
                if (vehiculoOpt != null) {
                    agenda.setVehiculo(vehiculoOpt);
                }
            }

            AgendaDto updated = agendaRepository.save(agenda);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error al actualizar agenda", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> delete(@RequestBody AgendaRequest request) {
        try {
            if (request.getId() == null) {
                return new ResponseEntity<>("El ID de la agenda es requerido", HttpStatus.BAD_REQUEST);
            }
            Optional<AgendaDto> agendaOpt = agendaRepository.findById(request.getId());
            if (!agendaOpt.isPresent()) {
                return new ResponseEntity<>("Agenda no encontrada", HttpStatus.NOT_FOUND);
            }
            AgendaDto agenda = agendaOpt.get();
            agenda.setHabilitado(false);
            agendaRepository.save(agenda);
            return new ResponseEntity<>(agenda, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error al eliminar agenda", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}