package com.grupo6.back_webintegrado.controller;

import com.grupo6.back_webintegrado.model.entity.Usuario;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class LocalController {


    @PostMapping("/save")
    ResponseEntity<String> createUser(@RequestBody Usuario usuario){
        String message = "Recurso Creado Exitosamente";
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping("/get")
    ResponseEntity<List<Usuario>> findUsers(){
        List<Usuario> localList = new ArrayList<>();
        for (long i = 1; i <= 1; i++) {
            Usuario usuario = new Usuario();
            usuario.setId(i);
            usuario.setNombre("Nombre" + i);
            usuario.setApellido("Apellido" + i);
            usuario.setCorreo("Correo" + i + "@correo.com");
            usuario.setPassword("123456");
            usuario.setRoles(new ArrayList<>());

            localList.add(usuario);
        }
        return ResponseEntity.ok(localList);
    }

}
