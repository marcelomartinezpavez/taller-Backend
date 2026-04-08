package com.personal.taller.controller;

import com.personal.taller.dto.ProveedorDto;
import com.personal.taller.dto.RepuestoDto;
import com.personal.taller.repository.ProveedorRepository;
import com.personal.taller.repository.RepuestoRepository;
import com.personal.taller.request.RepuestoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("repuesto")
public class RepuestoController {

    @Autowired
    RepuestoRepository repuestoRepository;

    @Autowired
    ProveedorRepository proveedorRepository;

    @GetMapping(path = "/all", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    ResponseEntity getAllRepuesto() {
        System.out.println("getAll Repuesto");

        List<RepuestoDto> repuestoDtoList = repuestoRepository.findAllHabilitado();
        List<RepuestoDto> repuestoDtoListResp = new ArrayList<>();
        for(RepuestoDto rep : repuestoDtoList){
            RepuestoDto repuesto = new RepuestoDto();

            //Optional<EmpresaDto> respEmpresa = empresaRepository.findById(rep.getEmpresa().getId());
            Optional<ProveedorDto> respProveedor = proveedorRepository.findByRutAndHabilitado(rep.getRutProveedor());

            if(!respProveedor.isPresent()){
                return new ResponseEntity("Error Proveedor no existe",HttpStatus.BAD_REQUEST);
            }

           // if(respEmpresa.isPresent()) {
                repuesto.setHabilitado(rep.getHabilitado());
                repuesto.setNombre(rep.getNombre());
                repuesto.setCodigo(rep.getCodigo());
                repuesto.setMarca(rep.getMarca());
                repuesto.setModelo(rep.getModelo());
                repuesto.setAnio(rep.getAnio());
                repuesto.setValor(rep.getValor());

                //EmpresaDto empresa = new EmpresaDto();
                //empresa.setId(respEmpresa.get().getId());
                //empresa.setDireccion(respEmpresa.get().getDireccion());
                //empresa.setRut(respEmpresa.get().getRut());
                //empresa.setNombre(respEmpresa.get().getNombre());

                //repuesto.setEmpresa(empresa);

                Set<ProveedorDto> proveedorDtoSet = new HashSet<>();
                ProveedorDto proveedor = new ProveedorDto();
                proveedor.setHabilitado(respProveedor.get().getHabilitado());
                proveedor.setNombre(respProveedor.get().getNombre());
                proveedor.setApellido(respProveedor.get().getApellido());
                proveedor.setRut(respProveedor.get().getRut());
                proveedor.setDireccion(respProveedor.get().getDireccion());
                proveedor.setComuna(respProveedor.get().getComuna());
                proveedor.setCiudad(respProveedor.get().getCiudad());
                proveedor.setTelefono(respProveedor.get().getTelefono());
                proveedor.setEmail(respProveedor.get().getEmail());
                //proveedor.setEmpresa(empresa);

                proveedorDtoSet.add(proveedor);

                repuesto.setProveedor(proveedorDtoSet);

            //}else{
            //    return new ResponseEntity("Error Empresa no existe",HttpStatus.BAD_REQUEST);
            //}
            repuestoDtoListResp.add(repuesto);
        }

        return new ResponseEntity<>(repuestoDtoListResp, HttpStatus.OK);
    }

    @GetMapping(value = "/codigo/{codigo}", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    ResponseEntity getRepuesto(@PathVariable String codigo) {
        RepuestoDto repuesto = null;
        try {
            repuesto = repuestoRepository.findByCodigo(codigo);
            if (repuesto == null){
                return new ResponseEntity("El repuesto no existe", HttpStatus.NO_CONTENT);
            }
        } catch (NullPointerException npe){
            return new ResponseEntity("El repuesto no existe", HttpStatus.NO_CONTENT);
        }
        RepuestoDto resp = new RepuestoDto();

        //Optional<EmpresaDto> respEmpresa = empresaRepository.findById(repuesto.getEmpresa().getId());
        Optional<ProveedorDto> respProveedor = proveedorRepository.findByRutAndHabilitado(repuesto.getRutProveedor());

        if(!respProveedor.isPresent()){
            return new ResponseEntity("Error Proveedor no existe",HttpStatus.BAD_REQUEST);
        }

        //if(respEmpresa.isPresent()) {
            resp.setHabilitado(repuesto.getHabilitado());
            resp.setNombre(repuesto.getNombre());
            resp.setCodigo(repuesto.getCodigo());
            resp.setMarca(repuesto.getMarca());
            resp.setModelo(repuesto.getModelo());
            resp.setAnio(repuesto.getAnio());
            resp.setValor(repuesto.getValor());

            /*EmpresaDto empresa = new EmpresaDto();
            empresa.setId(respEmpresa.get().getId());
            empresa.setDireccion(respEmpresa.get().getDireccion());
            empresa.setRut(respEmpresa.get().getRut());
            empresa.setNombre(respEmpresa.get().getNombre());
            */

            //resp.setEmpresa(empresa);

            Set<ProveedorDto> proveedorDtoSet = new HashSet<>();
            ProveedorDto proveedor = new ProveedorDto();
            proveedor.setHabilitado(respProveedor.get().getHabilitado());
            proveedor.setNombre(respProveedor.get().getNombre());
            proveedor.setApellido(respProveedor.get().getApellido());
            proveedor.setRut(respProveedor.get().getRut());
            proveedor.setDireccion(respProveedor.get().getDireccion());
            proveedor.setComuna(respProveedor.get().getComuna());
            proveedor.setCiudad(respProveedor.get().getCiudad());
            proveedor.setTelefono(respProveedor.get().getTelefono());
            proveedor.setEmail(respProveedor.get().getEmail());
            //proveedor.setEmpresa(empresa);

            proveedorDtoSet.add(proveedor);

            resp.setProveedor(proveedorDtoSet);
        /*
        }else{
            return new ResponseEntity("Error Empresa no existe",HttpStatus.BAD_REQUEST);
        }
        */
        return new ResponseEntity(resp, HttpStatus.OK);
    }

    @GetMapping(value = "/empresa/{idEmpresa}", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    ResponseEntity getRepuesto(@PathVariable long idEmpresa){
        List<RepuestoDto> repuestoDtoSet = repuestoRepository.findByEmpresa(idEmpresa);
        return new ResponseEntity(repuestoDtoSet, HttpStatus.OK);
    }


    @GetMapping(value = "/proveedor/{rut}", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    ResponseEntity getRepuestoProveedor(@PathVariable String rut) {
        Set<RepuestoDto> repuestoDtoSet = repuestoRepository.findByProveedor(rut);
        boolean errorProveedor = false;
        boolean errorEmpresa = false;
        List<RepuestoDto> repuestosDtoList = new ArrayList<RepuestoDto>();

        for (RepuestoDto repuesto : repuestoDtoSet) {
            RepuestoDto resp = new RepuestoDto();
            //Optional<EmpresaDto> respEmpresa = empresaRepository.findById(repuesto.getEmpresa().getId());
            Optional<ProveedorDto> respProveedor = proveedorRepository.findByRutAndHabilitado(repuesto.getRutProveedor());

            if (!respProveedor.isPresent()) {
                errorProveedor = true;
                break;
            }

            //if (respEmpresa.isPresent()) {
                resp.setId(repuesto.getId());
                resp.setHabilitado(repuesto.getHabilitado());
                resp.setNombre(repuesto.getNombre());
                resp.setCodigo(repuesto.getCodigo());
                resp.setMarca(repuesto.getMarca());
                resp.setModelo(repuesto.getModelo());
                resp.setAnio(repuesto.getAnio());
                resp.setValor(repuesto.getValor());
                /*
                EmpresaDto empresa = new EmpresaDto();
                empresa.setId(respEmpresa.get().getId());
                empresa.setDireccion(respEmpresa.get().getDireccion());
                empresa.setRut(respEmpresa.get().getRut());
                empresa.setNombre(respEmpresa.get().getNombre());

                resp.setEmpresa(empresa);
                */
                Set<ProveedorDto> proveedorDtoSet = new HashSet<>();
                ProveedorDto proveedor = new ProveedorDto();
                proveedor.setHabilitado(respProveedor.get().getHabilitado());
                proveedor.setNombre(respProveedor.get().getNombre());
                proveedor.setApellido(respProveedor.get().getApellido());
                proveedor.setRut(respProveedor.get().getRut());
                proveedor.setDireccion(respProveedor.get().getDireccion());
                proveedor.setComuna(respProveedor.get().getComuna());
                proveedor.setCiudad(respProveedor.get().getCiudad());
                proveedor.setTelefono(respProveedor.get().getTelefono());
                proveedor.setEmail(respProveedor.get().getEmail());
                //proveedor.setEmpresa(empresa);

                proveedorDtoSet.add(proveedor);

                resp.setProveedor(proveedorDtoSet);
                repuestosDtoList.add(resp);
            /*} else {
                errorEmpresa = true;
                break;
            }*/
        }

        if(errorProveedor){
            return new ResponseEntity("Error Proveedor no existe",HttpStatus.BAD_REQUEST);
        }

        if(errorEmpresa){
            return new ResponseEntity("Error Empresa no existe",HttpStatus.BAD_REQUEST);
        }


        return new ResponseEntity(repuestosDtoList, HttpStatus.OK);
    }


    @PostMapping(path = "/insert",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<RepuestoDto> create(@RequestBody RepuestoRequest newRepuesto) {
        RepuestoDto repuesto = new RepuestoDto();
        //Optional<EmpresaDto> respEmpresa = empresaRepository.findById(newRepuesto.getIdEmpresa());
        Optional<ProveedorDto> respProveedor = proveedorRepository.findByRutAndHabilitado(newRepuesto.getRutProveedor());

        if(!respProveedor.isPresent()){
            return new ResponseEntity("Error Proveedor no existe",HttpStatus.BAD_REQUEST);
        }

        //if(respEmpresa.isPresent()) {
            repuesto.setHabilitado(newRepuesto.getHabilitado());
            repuesto.setNombre(newRepuesto.getNombre());
            repuesto.setCodigo(newRepuesto.getCodigo());
            repuesto.setMarca(newRepuesto.getMarca());
            repuesto.setModelo(newRepuesto.getModelo());
            repuesto.setAnio(newRepuesto.getAnio());
            repuesto.setValor(newRepuesto.getValor());
            repuesto.setRutProveedor(respProveedor.get().getRut());

            //repuesto.setEmpresa(respEmpresa.get());

            Set<ProveedorDto> proveedorDtoSet = new HashSet<>();
            ProveedorDto proveedor = new ProveedorDto();
            proveedor.setHabilitado(respProveedor.get().getHabilitado());
            proveedor.setNombre(respProveedor.get().getNombre());
            proveedor.setApellido(respProveedor.get().getApellido());
            proveedor.setRut(respProveedor.get().getRut());
            proveedor.setDireccion(respProveedor.get().getDireccion());
            proveedor.setComuna(respProveedor.get().getComuna());
            proveedor.setCiudad(respProveedor.get().getCiudad());
            proveedor.setTelefono(respProveedor.get().getTelefono());
            proveedor.setEmail(respProveedor.get().getEmail());

            //proveedor.setEmpresa(respEmpresa.get());

            proveedorDtoSet.add(respProveedor.get());

            repuesto.setProveedor(proveedorDtoSet);
        /*}else{
            return new ResponseEntity("Error Empresa no existe",HttpStatus.BAD_REQUEST);
        }*/
        try {
            repuestoRepository.save(repuesto);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity("Error interno al crear Repuesto", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(repuesto, HttpStatus.CREATED);
    }

    @PutMapping(path = "/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<RepuestoDto> update(@RequestBody RepuestoRequest newRepuesto) {
        RepuestoDto repuesto = new RepuestoDto();
        //Optional<EmpresaDto> respEmpresa = empresaRepository.findById(newRepuesto.getIdEmpresa());
        Optional<ProveedorDto> respProveedor = proveedorRepository.findByRutAndHabilitado(newRepuesto.getRutProveedor());

        if(!respProveedor.isPresent()){
            return new ResponseEntity("Error Proveedor no existe",HttpStatus.BAD_REQUEST);
        }

        //if(respEmpresa.isPresent()) {
            repuesto.setId(newRepuesto.getId());
            repuesto.setHabilitado(newRepuesto.getHabilitado());
            repuesto.setNombre(newRepuesto.getNombre());
            repuesto.setCodigo(newRepuesto.getCodigo());
            repuesto.setMarca(newRepuesto.getMarca());
            repuesto.setModelo(newRepuesto.getModelo());
            repuesto.setAnio(newRepuesto.getAnio());
            repuesto.setValor(newRepuesto.getValor());
            repuesto.setRutProveedor(respProveedor.get().getRut());

            //repuesto.setEmpresa(respEmpresa.get());

            Set<ProveedorDto> proveedorDtoSet = new HashSet<>();
            ProveedorDto proveedor = new ProveedorDto();
            proveedor.setHabilitado(respProveedor.get().getHabilitado());
            proveedor.setNombre(respProveedor.get().getNombre());
            proveedor.setApellido(respProveedor.get().getApellido());
            proveedor.setRut(respProveedor.get().getRut());
            proveedor.setDireccion(respProveedor.get().getDireccion());
            proveedor.setComuna(respProveedor.get().getComuna());
            proveedor.setCiudad(respProveedor.get().getCiudad());
            proveedor.setTelefono(respProveedor.get().getTelefono());
            proveedor.setEmail(respProveedor.get().getEmail());

            //proveedor.setEmpresa(respEmpresa.get());

            proveedorDtoSet.add(respProveedor.get());

            repuesto.setProveedor(proveedorDtoSet);
        /*}else{
            return new ResponseEntity("Error Empresa no existe",HttpStatus.BAD_REQUEST);
        }*/
        try {
            repuestoRepository.save(repuesto);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity("Error interno al actualizar Repuesto", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(repuesto, HttpStatus.OK);

    }

    @DeleteMapping(path = "/delete",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<RepuestoDto> delete(@RequestBody RepuestoRequest newRepuesto) {
        RepuestoDto repuesto = repuestoRepository.findById(newRepuesto.getId()).get();

        if (repuesto != null) {
            repuesto.setHabilitado(false);
            repuestoRepository.save(repuesto);
            return new ResponseEntity(repuesto, HttpStatus.OK);
        }
         /*       new RepuestoDto();
        Optional<EmpresaDto> respEmpresa = empresaRepository.findById(newRepuesto.getIdEmpresa());
        Optional<ProveedorDto> respProveedor = proveedorRepository.findByRutAndHabilitadoAndIdEmpresa(newRepuesto.getRutProveedor(), newRepuesto.getIdEmpresa());

        if(!respProveedor.isPresent()){
            return new ResponseEntity("Error Proveedor no existe",HttpStatus.BAD_REQUEST);
        }

        if(respEmpresa.isPresent()) {
            repuesto.setId(newRepuesto.getId());
            repuesto.setHabilitado(0);
            repuesto.setNombre(newRepuesto.getNombre());
            repuesto.setCodigo(newRepuesto.getCodigo());
            repuesto.setMarca(newRepuesto.getMarca());
            repuesto.setModelo(newRepuesto.getModelo());
            repuesto.setAnio(newRepuesto.getAnio());
            repuesto.setValor(newRepuesto.getValor());
            repuesto.setRutProveedor(respProveedor.get().getRut());

            repuesto.setEmpresa(respEmpresa.get());

            Set<ProveedorDto> proveedorDtoSet = new HashSet<>();
            ProveedorDto proveedor = new ProveedorDto();
            proveedor.setHabilitado(respProveedor.get().getHabilitado());
            proveedor.setNombre(respProveedor.get().getNombre());
            proveedor.setApellido(respProveedor.get().getApellido());
            proveedor.setRut(respProveedor.get().getRut());
            proveedor.setDireccion(respProveedor.get().getDireccion());
            proveedor.setComuna(respProveedor.get().getComuna());
            proveedor.setCiudad(respProveedor.get().getCiudad());
            proveedor.setTelefono(respProveedor.get().getTelefono());
            proveedor.setEmail(respProveedor.get().getEmail());

            proveedor.setEmpresa(respEmpresa.get());

            proveedorDtoSet.add(respProveedor.get());

            repuesto.setProveedor(proveedorDtoSet);
        }else{
            return new ResponseEntity("Error Empresa no existe",HttpStatus.BAD_REQUEST);
        }
        try {
            repuestoRepository.save(repuesto);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity("Error interno al eliminar Repuesto", HttpStatus.INTERNAL_SERVER_ERROR);
        }*/
        return new ResponseEntity("Repuesto no encontrado", HttpStatus.BAD_REQUEST);

    }

}
