package com.dbc.colabore.security;

import com.dbc.colabore.entity.UsuarioEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {
//    private final UsuarioService usuarioService; //TODO necessário service

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
//        Optional<UsuarioEntity> usuario = usuarioService.findByLogin(login); //TODO necessário service
//
//        if(usuario.isPresent()){
//            return usuario.get();
//        }

        return null;
    }
}
