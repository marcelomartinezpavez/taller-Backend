package com.personal.taller.controller;

import com.personal.taller.dto.*;
import com.personal.taller.repository.*;
import com.personal.taller.request.OrdenTrabajoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("ordenTrabajo")
public class OrdenTrabajoController {
    @Autowired
    OrdenTrabajoRepository ordenTrabajoRepository;

    @Autowired
    VehiculoRepository vehiculoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    RepuestoRepository repuestoRepository;

    @Autowired
    DetalleRepuestoRepository detalleRepository;

    @Autowired
    TrabajosGeneralesRepository trabajosGeneralesRepository;

    @Autowired
    TrabajosTercerosRepository trabajosTercerosRepository;

    @GetMapping(path = "/all", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody ResponseEntity<?> getAllOrdenTrabajo() {
        try {
            System.out.println("getAll Orden Trabajo");
            List<OrdenTrabajoDto> ot = ordenTrabajoRepository.findAll();
            return new ResponseEntity<>(ot, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error Interno al buscar todos las ordenes de trabajo",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{numeroOrden}", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody ResponseEntity<?> getOrdenTrabajo(@PathVariable Long numeroOrden) {
        try {
            OrdenTrabajoDto ordenTrabajo = ordenTrabajoRepository.findByNumeroOrden(numeroOrden);
            if (ordenTrabajo == null) {
                return new ResponseEntity<>("No existe orden de trabajo para el numero indicado", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(ordenTrabajo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error Interno al buscar por numero de orden", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/patente/{patente}", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody ResponseEntity<?> getOrdenTrabajoPatente(@PathVariable String patente) {
        try {
            List<OrdenTrabajoDto> ordenTrabajo = ordenTrabajoRepository.findByPatenteVehiculo(patente);
            return new ResponseEntity<>(ordenTrabajo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error Interno al buscar por patente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/cliente/{rut}", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody ResponseEntity<?> getOrdenTrabajoCliente(@PathVariable String rut) {
        try {
            List<OrdenTrabajoDto> ordenTrabajo = ordenTrabajoRepository.findByRutCliente(rut);
            return new ResponseEntity<>(ordenTrabajo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error Interno al buscar por cliente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> create(@RequestBody OrdenTrabajoRequest newOrdenTrabajo) {
        System.out.println("INSERT OT");

        OrdenTrabajoDto otResponse = new OrdenTrabajoDto();
        try {
            VehiculoDto vehiculoDto = vehiculoRepository.findByPatente(newOrdenTrabajo.getPatenteVehiculo());
            if (vehiculoDto == null) {
                return new ResponseEntity<>("Vehiculo no existe", HttpStatus.BAD_REQUEST);
            }
            Optional<ClienteDto> clienteDto = clienteRepository
                    .findByRutAndHabilitado(newOrdenTrabajo.getRutCliente());

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String fechaActual = dtf.format(LocalDateTime.now());

            otResponse.setHabilitado(true);
            otResponse.setEstado("ABIERTA");
            otResponse.setFechaIngreso(fechaActual);
            otResponse.setRutCliente(newOrdenTrabajo.getRutCliente());
            ClienteDto cl = new ClienteDto();
            if (clienteDto.isPresent()) {
                cl.setId(clienteDto.get().getId());
                cl.setHabilitado(clienteDto.get().getHabilitado());
                cl.setNombre(clienteDto.get().getNombre());
                cl.setApellido(clienteDto.get().getApellido());
                cl.setRut(clienteDto.get().getRut());
                cl.setDireccion(clienteDto.get().getDireccion());
                cl.setComuna(clienteDto.get().getComuna());
                cl.setCiudad(clienteDto.get().getCiudad());
                cl.setTelefono(clienteDto.get().getTelefono());
                cl.setEmail(clienteDto.get().getEmail());
                otResponse.setCliente(clienteDto.get());
            } else {
                return new ResponseEntity<>("Cliente no existe", HttpStatus.BAD_REQUEST);
            }

            otResponse.setPatenteVehiculo(newOrdenTrabajo.getPatenteVehiculo());
            otResponse.setCodigo(newOrdenTrabajo.getCodigo());
            otResponse.setValorOt(newOrdenTrabajo.getValorOt());
            otResponse.setKilometrajeVehiculoActual(newOrdenTrabajo.getKilometrajeVehiculoActual());
            otResponse.setNivelCombustible(newOrdenTrabajo.getNivelCombustible());
            otResponse.setObservaciones(newOrdenTrabajo.getObservaciones());

            //otResponse.setNumeroOrden(newOrdenTrabajo.getNumeroOrden());

            VehiculoDto vehiculo = new VehiculoDto();
            vehiculo.setId(vehiculoDto.getId());
            vehiculo.setPatente(vehiculoDto.getPatente());
            vehiculo.setHabilitado(vehiculoDto.getHabilitado());
            vehiculo.setMarca(vehiculoDto.getMarca());
            vehiculo.setModelo(vehiculoDto.getModelo());
            vehiculo.setAnio(vehiculoDto.getAnio());
            vehiculo.setNumeroChasis(vehiculoDto.getNumeroChasis());
            vehiculo.setNumeroMotor(vehiculoDto.getNumeroMotor());
            vehiculo.setColor(vehiculoDto.getColor());
            vehiculo.setKilometraje(vehiculoDto.getKilometraje());
            vehiculo.setRutDueno(vehiculoDto.getRutDueno());

            Set<ClienteDto> clientesSet = new HashSet<>();
            clientesSet.add(cl);
            vehiculo.setCliente(clientesSet);

            otResponse.setVehiculo(vehiculo);
            otResponse.setVehiculo(vehiculoDto);

            Set<DetalleRepuestosDto> detalleSet = new HashSet<>();
            if (newOrdenTrabajo.getDetalleRepuesto() != null) {
                newOrdenTrabajo.getDetalleRepuesto().forEach(detalle -> {
                DetalleRepuestosDto detalleDto = new DetalleRepuestosDto();
                detalleDto.setDescripcion(detalle.getDescripcion());
                detalleDto.setPorcentajeRecargo(detalle.getPorcentajeRecargo());
                detalleDto.setValor(detalle.getValor());
                detalleDto.setCantidad(detalle.getCantidad());
                detalleDto.setTotal(detalle.getTotal());

                RepuestoDto repuestoDto = new RepuestoDto();
                if (detalle.getRepuesto_id() != 0) {
                    Optional<RepuestoDto> repuestoDtoOptional = repuestoRepository.findById(detalle.getRepuesto_id());
                    if (repuestoDtoOptional.isPresent()) {
                        repuestoDto.setId(repuestoDtoOptional.get().getId());
                        repuestoDto.setCodigo(repuestoDtoOptional.get().getCodigo());
                        repuestoDto.setHabilitado(repuestoDtoOptional.get().getHabilitado());
                        repuestoDto.setNombre(repuestoDtoOptional.get().getNombre());
                        repuestoDto.setMarca(repuestoDtoOptional.get().getMarca());
                        repuestoDto.setModelo(repuestoDtoOptional.get().getModelo());
                        repuestoDto.setAnio(repuestoDtoOptional.get().getAnio());
                        repuestoDto.setRutProveedor(repuestoDtoOptional.get().getRutProveedor());
                        repuestoDto.setValor(repuestoDtoOptional.get().getValor());
                        // Falta agregar proveedor a repuestoDto
                    }
                    detalleDto.setRepuesto(repuestoDto);
                }
                detalleRepository.save(detalleDto);
                detalleSet.add(detalleDto);
            });
            }
            otResponse.setDetalle(detalleSet);

            Set<TrabajosGeneralesDto> trabajosGeneralesSet = new HashSet<>();
            if (newOrdenTrabajo.getTrabajosGenerales() != null) {
                newOrdenTrabajo.getTrabajosGenerales().forEach(trabajosGenerales -> {
                TrabajosGeneralesDto trabajosGeneralesDto = new TrabajosGeneralesDto();
                trabajosGeneralesDto.setDescripcion(trabajosGenerales.getDescripcionGeneral());
                trabajosGeneralesDto.setPorcentajeRecargo(trabajosGenerales.getPorcentajeRecargoGeneral());
                trabajosGeneralesDto.setValor(trabajosGenerales.getValorGeneral());
                long cantidad = trabajosGenerales.getCantidadGeneral() <= 0 ? 1 : trabajosGenerales.getCantidadGeneral();
                trabajosGeneralesDto.setCantidad(cantidad);
                trabajosGeneralesDto.setTotal(trabajosGenerales.getValorGeneral() * cantidad);
                trabajosGeneralesDto.setPrestadorServicio(trabajosGenerales.getPrestadorServicioGeneral());
                //trabajosGeneralesDto.setOrdenTrabajo(otResponse);

                trabajosGeneralesRepository.save(trabajosGeneralesDto);

                trabajosGeneralesSet.add(trabajosGeneralesDto);
            });
            }
            otResponse.setTrabajosGenerales(trabajosGeneralesSet);

            Set<TrabajosTercerosDto> trabajosTercerosSet = new HashSet<>();
            if (newOrdenTrabajo.getTrabajosTerceros() != null) {
                newOrdenTrabajo.getTrabajosTerceros().forEach(trabajosTerceros -> {
                TrabajosTercerosDto trabajosTercerosDto = new TrabajosTercerosDto();
                trabajosTercerosDto.setDescripcion(trabajosTerceros.getDescripcionTercero());
                trabajosTercerosDto.setPorcentajeRecargo(trabajosTerceros.getPorcentajeRecargoTercero());
                trabajosTercerosDto.setValor(trabajosTerceros.getValorTercero());
                long cantidad = trabajosTerceros.getCantidadTercero() <= 0 ? 1 : trabajosTerceros.getCantidadTercero();
                trabajosTercerosDto.setCantidad(cantidad);
                trabajosTercerosDto.setTotal(trabajosTerceros.getValorTercero() * cantidad);
                trabajosTercerosDto.setPrestadorServicio(trabajosTerceros.getPrestadorServicioTercero());
                //trabajosTercerosDto.setOrdenTrabajo(otResponse);

                trabajosTercerosRepository.save(trabajosTercerosDto);

                trabajosTercerosSet.add(trabajosTercerosDto);
            });
            }
            otResponse.setTrabajosTerceros(trabajosTercerosSet);

            OrdenTrabajoDto otNew = ordenTrabajoRepository.save(otResponse);
            otNew.setCodigo(String.valueOf(otNew.getId()));
            otNew.setNumeroOrden(otNew.getId());
            ordenTrabajoRepository.save(otNew);

            detalleSet.forEach(detalle -> {
                detalle.setOrdenTrabajo(otNew);
                detalleRepository.save(detalle);
            });
            
            trabajosGeneralesSet.forEach(trabajoGeneral -> {
                trabajoGeneral.setOrdenTrabajo(otNew); 
                trabajosGeneralesRepository.save(trabajoGeneral);
            });



            trabajosTercerosSet.forEach(trabajoTercero -> {
                trabajoTercero.setOrdenTrabajo(otNew);
                trabajosTercerosRepository.save(trabajoTercero);
            });

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error al crear orden de trabajo", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(otResponse, HttpStatus.CREATED);

    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> update(@RequestBody OrdenTrabajoRequest newOrdenTrabajo) {
        try {
            VehiculoDto vehiculoDto = vehiculoRepository.findByPatente(newOrdenTrabajo.getPatenteVehiculo());
            if (vehiculoDto == null) {
                return new ResponseEntity<>("Vehiculo no existe", HttpStatus.BAD_REQUEST);
            }
            Optional<ClienteDto> clienteDto = clienteRepository
                    .findByRutAndHabilitado(newOrdenTrabajo.getRutCliente());

            Optional<OrdenTrabajoDto> ot = ordenTrabajoRepository.findById(newOrdenTrabajo.getId());

            if (!ot.isPresent()) {
                return new ResponseEntity<>("No existe orden de trabajo para editar", HttpStatus.NOT_FOUND);
            }

            OrdenTrabajoDto otResponse = ot.get();
            otResponse.setId(newOrdenTrabajo.getId());
            otResponse.setHabilitado(newOrdenTrabajo.getHabilitado());
            otResponse.setEstado(newOrdenTrabajo.getEstado());
            if (newOrdenTrabajo.getEstado().toUpperCase().equalsIgnoreCase("CERRADO")) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                String fechaCerrado = dtf.format(LocalDateTime.now());
                otResponse.setFechaCerrado(fechaCerrado);
            }
            otResponse.setRutCliente(newOrdenTrabajo.getRutCliente());
            ClienteDto cl = new ClienteDto();
            if (clienteDto.isPresent()) {
                cl.setId(clienteDto.get().getId());
                cl.setHabilitado(clienteDto.get().getHabilitado());
                cl.setNombre(clienteDto.get().getNombre());
                cl.setApellido(clienteDto.get().getApellido());
                cl.setRut(clienteDto.get().getRut());
                cl.setDireccion(clienteDto.get().getDireccion());
                cl.setComuna(clienteDto.get().getComuna());
                cl.setCiudad(clienteDto.get().getCiudad());
                cl.setTelefono(clienteDto.get().getTelefono());
                cl.setEmail(clienteDto.get().getEmail());
                otResponse.setCliente(clienteDto.get());
            } else {
                return new ResponseEntity<>("Cliente no existe", HttpStatus.BAD_REQUEST);
            }

            otResponse.setPatenteVehiculo(newOrdenTrabajo.getPatenteVehiculo());
            otResponse.setCodigo(newOrdenTrabajo.getCodigo());
            otResponse.setValorOt(newOrdenTrabajo.getValorOt());
            otResponse.setKilometrajeVehiculoActual(newOrdenTrabajo.getKilometrajeVehiculoActual());
            otResponse.setNivelCombustible(newOrdenTrabajo.getNivelCombustible());
            otResponse.setObservaciones(newOrdenTrabajo.getObservaciones());

            //otResponse.setNumeroOrden(newOrdenTrabajo.getNumeroOrden());

            VehiculoDto vehiculo = new VehiculoDto();
            vehiculo.setId(vehiculoDto.getId());
            vehiculo.setPatente(vehiculoDto.getPatente());
            vehiculo.setHabilitado(vehiculoDto.getHabilitado());
            vehiculo.setMarca(vehiculoDto.getMarca());
            vehiculo.setModelo(vehiculoDto.getModelo());
            vehiculo.setAnio(vehiculoDto.getAnio());
            vehiculo.setNumeroChasis(vehiculoDto.getNumeroChasis());
            vehiculo.setNumeroMotor(vehiculoDto.getNumeroMotor());
            vehiculo.setColor(vehiculoDto.getColor());
            vehiculo.setKilometraje(vehiculoDto.getKilometraje());
            vehiculo.setRutDueno(vehiculoDto.getRutDueno());

            Set<ClienteDto> clientesSet = new HashSet<>();
            clientesSet.add(cl);
            vehiculo.setCliente(clientesSet);

            otResponse.setVehiculo(vehiculo);
            otResponse.setVehiculo(vehiculoDto);

            Set<DetalleRepuestosDto> detalleSet = new HashSet<>();
            if (newOrdenTrabajo.getDetalleRepuesto() != null) {
                newOrdenTrabajo.getDetalleRepuesto().forEach(detalle -> {
                DetalleRepuestosDto detalleDto = new DetalleRepuestosDto();
                detalleDto.setDescripcion(detalle.getDescripcion());
                detalleDto.setPorcentajeRecargo(detalle.getPorcentajeRecargo());
                detalleDto.setValor(detalle.getValor());
                detalleDto.setCantidad(detalle.getCantidad());
                detalleDto.setTotal(detalle.getTotal());

                RepuestoDto repuestoDto = new RepuestoDto();
                if (detalle.getRepuesto_id() != 0) {
                    Optional<RepuestoDto> repuestoDtoOptional = repuestoRepository.findById(detalle.getRepuesto_id());
                    if (repuestoDtoOptional.isPresent()) {
                        repuestoDto.setId(repuestoDtoOptional.get().getId());
                        repuestoDto.setCodigo(repuestoDtoOptional.get().getCodigo());
                        repuestoDto.setHabilitado(repuestoDtoOptional.get().getHabilitado());
                        repuestoDto.setNombre(repuestoDtoOptional.get().getNombre());
                        repuestoDto.setMarca(repuestoDtoOptional.get().getMarca());
                        repuestoDto.setModelo(repuestoDtoOptional.get().getModelo());
                        repuestoDto.setAnio(repuestoDtoOptional.get().getAnio());
                        repuestoDto.setRutProveedor(repuestoDtoOptional.get().getRutProveedor());
                        repuestoDto.setValor(repuestoDtoOptional.get().getValor());
                        // Falta agregar proveedor a repuestoDto
                    }
                    detalleDto.setRepuesto(repuestoDto);

                }
                detalleSet.add(detalleDto);

            });
            }
            otResponse.getDetalleRepuestosDtos().clear();
            detalleSet.forEach(detalle -> {
                detalle.setOrdenTrabajo(otResponse);
                otResponse.getDetalleRepuestosDtos().add(detalle);
            });
            OrdenTrabajoDto otUpdated = ordenTrabajoRepository.save(otResponse);
            
            return new ResponseEntity<>(otUpdated, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error al actualizar orden de trabajo", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping(path = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> delete(@RequestBody OrdenTrabajoRequest newOrdenTrabajo) {
        try {
            Optional<OrdenTrabajoDto> otOptional = ordenTrabajoRepository.findById(newOrdenTrabajo.getId());
            if (!otOptional.isPresent()) {
                return new ResponseEntity<>("No existe orden de trabajo para eliminar", HttpStatus.NOT_FOUND);
            }
            OrdenTrabajoDto ot = otOptional.get();
            ot.setHabilitado(false);
            ordenTrabajoRepository.save(ot);
            return new ResponseEntity<>(ot, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error al eliminar orden de trabajo", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
