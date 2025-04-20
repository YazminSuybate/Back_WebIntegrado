package com.grupo6.back_webintegrado.repository;

import com.grupo6.back_webintegrado.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);
}
