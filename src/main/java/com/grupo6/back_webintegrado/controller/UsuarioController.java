package com.grupo6.back_webintegrado.controller;

import com.grupo6.back_webintegrado.model.dto.UsuarioDTO;
import com.grupo6.back_webintegrado.model.entity.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/recurso")
public class UsuarioController {

    @PostMapping("/save")
    ResponseEntity<String> registerLocal(@RequestBody UsuarioDTO usuario){

        String message = "Recurso Creado";
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }


}
