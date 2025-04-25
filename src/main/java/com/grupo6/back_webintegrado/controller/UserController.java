package com.grupo6.back_webintegrado.controller;

import com.grupo6.back_webintegrado.model.entity.Usuario;
import com.grupo6.back_webintegrado.services.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario saveUser (@RequestBody Usuario usuario){
        return userService.saveUser(usuario);
    }

    @GetMapping
    public List<Usuario> findUser(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable("id") long userID){
        return userService.getUsuarioById(userID)
                .map(ResponseEntity :: ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUser(@PathVariable("id") long userID, @RequestBody Usuario usuario){
        return userService.getUsuarioById(userID)
                .map(userSave -> {
                    userSave.setNombre(usuario.getNombre());
                    userSave.setApellido(usuario.getApellido());
                    userSave.setCorreo(usuario.getCorreo());
                    userSave.setPassword(usuario.getPassword());
                    userSave.setRoles(usuario.getRoles());

                    Usuario userUpdate = userService.updateUser(userSave);
                    return new ResponseEntity<>(userUpdate, HttpStatus.OK);

                })
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long userID){
        userService.deleteUser(userID);
        return new ResponseEntity<String>("Empleado eliminado", HttpStatus.OK);
    }

}
