package fusion.fusion.service;

import fusion.fusion.entity.UserEntity;
import fusion.fusion.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity existingUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("El email no fue encontrado: " + email));

        // MODIFICADO: Convertir roles a authorities para Spring Security
        // Cada rol se prefija con "ROLE_" según la convención de Spring Security
        List<SimpleGrantedAuthority> authorities = existingUser.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toList());

        // MODIFICADO: Ahora pasamos las authorities en lugar de una lista vacía
        return new User(existingUser.getEmail(),
                existingUser.getPassword(),
                authorities);
    }
}