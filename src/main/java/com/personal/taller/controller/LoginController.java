package com.personal.taller.controller;

import com.personal.taller.dto.UsersDto;
import com.personal.taller.repository.LoginRepository;
import com.personal.taller.request.Users;
import com.personal.taller.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired
    LoginRepository loginRepository;

    @PostMapping(path = "/",produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    ResponseEntity<UsersDto> login(@RequestBody Users newUsers) {
        Optional<UsersDto> resp = loginRepository.findByUsersAndPass(newUsers.getUsers(), newUsers.getPass());
        UsersDto usersDto = new UsersDto();
        if(!resp.isPresent()){
            return new ResponseEntity("Error al validar usuario",HttpStatus.BAD_REQUEST);
        }else{
            //LoginResponse loginResponse = new LoginResponse();
            usersDto.setUsers(resp.get().getUsers());
            usersDto.setRol(resp.get().getRol());
            usersDto.setId(resp.get().getId());
            usersDto.setHabilitado(resp.get().getHabilitado());
            //loginResponse.setUsers(usersDto);
            
            return new ResponseEntity<UsersDto>(usersDto, HttpStatus.OK);
        }
    }

    @PostMapping(path = "/create",produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    ResponseEntity<LoginResponse> create(@RequestBody Users users) {
        Optional<UsersDto> user = loginRepository.findByUsersAndPass(users.getUsers(), users.getPass());

        UsersDto usuario = new UsersDto();

        if (!user.isPresent()){

            usuario.setUsers(users.getUsers());
            usuario.setPass(users.getPass());
            usuario.setHabilitado(true);
            usuario.setRol(users.getRol());

            // Guardar el usuario en la BD para que persista
            usuario = loginRepository.save(usuario);

        }else{
            return new ResponseEntity("Usuario que intenta crear ya esta en uso",HttpStatus.IM_USED);
        }

        LoginResponse loginResponse = new LoginResponse();



        loginResponse.setUsers(usuario);

        return new ResponseEntity(loginResponse, HttpStatus.OK);
    }

}
