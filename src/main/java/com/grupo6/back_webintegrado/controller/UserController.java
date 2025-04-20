package com.grupo6.back_webintegrado.controller;

import com.grupo6.back_webintegrado.model.entity.Usuario;
import com.grupo6.back_webintegrado.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/empleados")
public class UserController {

    @Autowired
    private UserService userService;

}
