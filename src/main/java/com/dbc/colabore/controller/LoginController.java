package com.dbc.colabore.controller;

import com.dbc.colabore.dto.*;
import com.dbc.colabore.entity.PerfilEntity;
import com.dbc.colabore.entity.UsuarioEntity;
import com.dbc.colabore.security.TokenService;
import com.dbc.colabore.service.PerfilService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
@Validated
@RequiredArgsConstructor
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final PerfilService perfilService;

    @PostMapping
    public String login(@RequestBody @Valid LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken user =
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsuario(),
                        loginDTO.getSenha()
                );

        Authentication authenticate = authenticationManager.authenticate(user);

        String token = tokenService.generateToken((PerfilEntity) authenticate.getPrincipal());
        return token;
    }

    @PostMapping("/create")
    public PerfilDTO createLogin(@RequestBody @Valid PerfilCreateDTO perfilCreateDTO) {
        return perfilService.create(perfilCreateDTO);
    }
}
