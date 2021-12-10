package com.dbc.colabore.security;

import com.dbc.colabore.entity.PerfilEntity;
import com.dbc.colabore.entity.UsuarioEntity;
import com.dbc.colabore.service.PerfilService;
import com.dbc.colabore.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {
    private final PerfilService perfilService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<PerfilEntity> perfil = perfilService.findByLogin(login);

        if(perfil.isPresent()){
            return perfil.get();
        }

        return null;
    }


}
