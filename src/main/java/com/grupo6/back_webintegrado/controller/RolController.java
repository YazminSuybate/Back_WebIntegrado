package com.grupo6.back_webintegrado.controller;

import com.grupo6.back_webintegrado.model.entity.Rol;
import com.grupo6.back_webintegrado.services.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rol")
public class RolController {

    @Autowired
    private RolService rolService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Rol saveRol(@RequestBody Rol rol) {
        return rolService.saveRol(rol);
    }

    @GetMapping
    public List<Rol> findRoles() {
        return rolService.getAllRoles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rol> getRolById(@PathVariable long id) {
        return rolService.getRolById(id)
                .map(ResponseEntity :: ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rol> updateRol(@PathVariable long id, @RequestBody Rol rol) {
        return rolService.getRolById(id)
                .map(rolSave -> {
                    rolSave.setRol(rol.getRol());

                    Rol rolUpdate = rolService.updateRol(rolSave);
                    return new ResponseEntity<>(rolUpdate, HttpStatus.OK);

                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRol(@PathVariable long id) {
        rolService.deleteRol(id);
        return new ResponseEntity<>("Rol eliminado", HttpStatus.OK);
    }

}
