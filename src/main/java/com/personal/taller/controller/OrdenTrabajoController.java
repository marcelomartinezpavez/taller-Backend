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
    EmpresaRepository empresaRepository;

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
    public @ResponseBody ResponseEntity getAllOrdenTrabajo() {
        try {
            System.out.println("getAll Orden Trabajo");
            List<OrdenTrabajoDto> ot = ordenTrabajoRepository.findAll();
            return new ResponseEntity(ot, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Error Interno al buscar todos las ordenes de trabajo",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/orden/{numeroOrden}", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody ResponseEntity getOrdenTrabajo(@PathVariable String numeroOrden) {
        try {
            OrdenTrabajoDto ordenTrabajo = ordenTrabajoRepository.findByNumeroOrden(numeroOrden);
            return new ResponseEntity(ordenTrabajo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Error Interno al buscar por numero de orden", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/orden/{numeroOrden}/empresa/{idEmpresa}", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody ResponseEntity getOrdenTrabajoByNumAndEmp(@PathVariable String numeroOrden,
            @PathVariable long idEmpresa) {
        try {
            OrdenTrabajoDto ordenTrabajo = ordenTrabajoRepository.findByNumeroOrdenAndEmpresa(numeroOrden, idEmpresa);
            return new ResponseEntity(ordenTrabajo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Error Interno al buscar por numero de orden", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/patente/{patente}", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody ResponseEntity getOrdenTrabajoPatente(@PathVariable String patente) {
        try {
            List<OrdenTrabajoDto> ordenTrabajo = ordenTrabajoRepository.findByPatenteVehiculo(patente);
            return new ResponseEntity(ordenTrabajo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Error Interno al buscar por patente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/patente/{patente}/empresa/{idEmpresa}", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody ResponseEntity getOrdenTrabajoPatenteAndEmpresa(@PathVariable String patente,
            @PathVariable long idEmpresa) {
        try {
            List<OrdenTrabajoDto> ordenTrabajo = ordenTrabajoRepository.findByPatenteVehiculoAndEmpresa(patente,
                    idEmpresa);
            return new ResponseEntity(ordenTrabajo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Error Interno al buscar por patente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/cliente/{rut}", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody ResponseEntity getOrdenTrabajoCliente(@PathVariable String rut) {
        try {
            List<OrdenTrabajoDto> ordenTrabajo = ordenTrabajoRepository.findByRutCliente(rut);
            return new ResponseEntity(ordenTrabajo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Error Interno al buscar por cliente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/cliente/{rut}/empresa/{idEmpresa}", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody ResponseEntity getOrdenTrabajoCliente(@PathVariable String rut, @PathVariable long idEmpresa) {
        try {
            List<OrdenTrabajoDto> ordenTrabajo = ordenTrabajoRepository.findByRutClienteAndEmpresa(rut, idEmpresa);
            return new ResponseEntity(ordenTrabajo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Error Interno al buscar por cliente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/empresa/{idEmpresa}", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody ResponseEntity getOrdenTrabajoEmpresa(@PathVariable long idEmpresa) {
        try {
            List<OrdenTrabajoDto> ordenTrabajo = ordenTrabajoRepository.findByIdEmpresa(idEmpresa);
            return new ResponseEntity(ordenTrabajo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Error Interno al buscar por empresa", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<OrdenTrabajoDto> create(@RequestBody OrdenTrabajoRequest newOrdenTrabajo) {
        System.out.println("INSERT OT");

        OrdenTrabajoDto otResponse = new OrdenTrabajoDto();
        try {
            EmpresaDto empresaDto = empresaRepository.getById(newOrdenTrabajo.getIdEmpresa());
            VehiculoDto vehiculoDto = vehiculoRepository.findByPatenteAndEmpresa(newOrdenTrabajo.getPatenteVehiculo(),
                    newOrdenTrabajo.getIdEmpresa());
            Optional<ClienteDto> clienteDto = clienteRepository
                    .findByRutAndHabilitadoAndEmpresa(newOrdenTrabajo.getRutCliente(), newOrdenTrabajo.getIdEmpresa());

            EmpresaDto empresa = new EmpresaDto();
            empresa.setNombre(empresaDto.getNombre());
            empresa.setDireccion(empresaDto.getDireccion());
            empresa.setRut(empresaDto.getRut());
            empresa.setId(empresaDto.getId());

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String fechaActual = dtf.format(LocalDateTime.now());

            otResponse.setHabilitado(true);
            otResponse.setEstado("ABIERTAS");
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
                cl.setEmpresa(empresa);
                otResponse.setCliente(clienteDto.get());
            } else {
                return new ResponseEntity("Cliente no existe", HttpStatus.BAD_REQUEST);
            }

            otResponse.setPatenteVehiculo(newOrdenTrabajo.getPatenteVehiculo());
            otResponse.setCodigo(newOrdenTrabajo.getCodigo());
            otResponse.setValorOt(newOrdenTrabajo.getValorOt());

            otResponse.setNumeroOrden(newOrdenTrabajo.getNumeroOrden());

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

            otResponse.setEmpresa(empresa);

            Set<DetalleRepuestosDto> detalleSet = new HashSet();
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
                        repuestoDto.setEmpresa(empresa);
                        // Falta agregar proveedor a repuestoDto
                    }
                    detalleDto.setRepuesto(repuestoDto);
                }
                detalleRepository.save(detalleDto);
                detalleSet.add(detalleDto);
            });
            otResponse.setDetalle(detalleSet);

            Set<TrabajosGeneralesDto> trabajosGeneralesSet = new HashSet();
            newOrdenTrabajo.getTrabajosGenerales().forEach(trabajosGenerales -> {
                TrabajosGeneralesDto trabajosGeneralesDto = new TrabajosGeneralesDto();
                trabajosGeneralesDto.setDescripcion(trabajosGenerales.getDescripcion());
                trabajosGeneralesDto.setPorcentajeRecargo(trabajosGenerales.getPorcentajeRecargo());
                trabajosGeneralesDto.setValor(trabajosGenerales.getValor());
                trabajosGeneralesDto.setCantidad(1);
                trabajosGeneralesDto.setTotal(trabajosGenerales.getTotal());
                trabajosGeneralesDto.setPrestadorServicio(trabajosGenerales.getPrestadorServicio());
                trabajosGeneralesDto.setOrdenTrabajo(otResponse);

                trabajosGeneralesRepository.save(trabajosGeneralesDto);

                trabajosGeneralesSet.add(trabajosGeneralesDto);
            });
            otResponse.setTrabajosGenerales(trabajosGeneralesSet);

            Set<TrabajosTercerosDto> trabajosTercerosSet = new HashSet();
            newOrdenTrabajo.getTrabajosTerceros().forEach(trabajosTerceros -> {
                TrabajosTercerosDto trabajosTercerosDto = new TrabajosTercerosDto();
                trabajosTercerosDto.setDescripcion(trabajosTerceros.getDescripcion());
                trabajosTercerosDto.setPorcentajeRecargo(trabajosTerceros.getPorcentajeRecargo());
                trabajosTercerosDto.setValor(trabajosTerceros.getValor());
                trabajosTercerosDto.setCantidad(1);
                trabajosTercerosDto.setTotal(trabajosTerceros.getTotal());
                trabajosTercerosDto.setPrestadorServicio(trabajosTerceros.getPrestadorServicio());
                trabajosTercerosDto.setOrdenTrabajo(otResponse);

                trabajosTercerosRepository.save(trabajosTercerosDto);

                trabajosTercerosSet.add(trabajosTercerosDto);
            });
            otResponse.setTrabajosTerceros(trabajosTercerosSet);

            ordenTrabajoRepository.save(otResponse);

        } catch (Exception e) {
            e.printStackTrace();
            new ResponseEntity("Error al crear orden de trabajo", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(otResponse, HttpStatus.CREATED);

    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<OrdenTrabajoDto> update(@RequestBody OrdenTrabajoRequest newOrdenTrabajo) {
        OrdenTrabajoDto otResponse = new OrdenTrabajoDto();

        try {
            EmpresaDto empresaDto = empresaRepository.getById(newOrdenTrabajo.getIdEmpresa());
            VehiculoDto vehiculoDto = vehiculoRepository.findByPatenteAndEmpresa(newOrdenTrabajo.getPatenteVehiculo(),
                    newOrdenTrabajo.getIdEmpresa());
            Optional<ClienteDto> clienteDto = clienteRepository
                    .findByRutAndHabilitadoAndEmpresa(newOrdenTrabajo.getRutCliente(), newOrdenTrabajo.getIdEmpresa());

            Optional<OrdenTrabajoDto> ot = ordenTrabajoRepository.findById(newOrdenTrabajo.getId());

            if (!ot.isPresent()) {
                return new ResponseEntity("No existe orden de trabajo para editar", HttpStatus.NOT_FOUND);
            }

            EmpresaDto empresa = new EmpresaDto();
            empresa.setNombre(empresaDto.getNombre());
            empresa.setDireccion(empresaDto.getDireccion());
            empresa.setRut(empresaDto.getRut());
            empresa.setId(empresaDto.getId());

            otResponse.setId(newOrdenTrabajo.getId());
            otResponse.setHabilitado(newOrdenTrabajo.getHabilitado());
            otResponse.setEstado(newOrdenTrabajo.getEstado());
            if (newOrdenTrabajo.getEstado().toUpperCase().equalsIgnoreCase("CERRADO")) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                String fechaCerrado = dtf.format(LocalDateTime.now());
                otResponse.setFechaCerrado(fechaCerrado);
            }
            otResponse.setFechaIngreso(ot.get().getFechaIngreso());
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
                cl.setEmpresa(empresa);
                otResponse.setCliente(clienteDto.get());
            } else {
                return new ResponseEntity("Cliente no existe", HttpStatus.BAD_REQUEST);
            }

            otResponse.setPatenteVehiculo(newOrdenTrabajo.getPatenteVehiculo());
            otResponse.setCodigo(newOrdenTrabajo.getCodigo());
            otResponse.setValorOt(newOrdenTrabajo.getValorOt());

            otResponse.setNumeroOrden(newOrdenTrabajo.getNumeroOrden());

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

            otResponse.setEmpresa(empresa);

            Set<DetalleRepuestosDto> detalleSet = new HashSet();
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
                        repuestoDto.setEmpresa(empresa);

                        // Falta agregar proveedor a repuestoDto
                    }
                    detalleDto.setRepuesto(repuestoDto);

                }

                detalleRepository.save(detalleDto);
                detalleSet.add(detalleDto);

            });
            otResponse.setDetalle(detalleSet);
            ordenTrabajoRepository.save(otResponse);

        } catch (Exception e) {
            e.printStackTrace();
            new ResponseEntity("Error al actualizar orden de trabajo", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(otResponse, HttpStatus.OK);

    }

    @DeleteMapping(path = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<OrdenTrabajoDto> delete(@RequestBody OrdenTrabajoRequest newOrdenTrabajo) {
        OrdenTrabajoDto otResponse = new OrdenTrabajoDto();
        try {
            OrdenTrabajoDto ot = ordenTrabajoRepository.findById(newOrdenTrabajo.getId()).get();
            ot.setHabilitado(false);
            ordenTrabajoRepository.save(ot);
            return new ResponseEntity<>(ot, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            new ResponseEntity("Error al eliminar orden de trabajo", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}
