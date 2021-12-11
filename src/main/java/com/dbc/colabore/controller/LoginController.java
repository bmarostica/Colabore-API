package com.dbc.colabore.controller;

import com.dbc.colabore.dto.*;
import com.dbc.colabore.entity.UsuarioEntity;
import com.dbc.colabore.security.TokenService;
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

    @PostMapping
    public String login(@RequestBody @Valid LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken user =
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getLogin(),
                        loginDTO.getSenha()
                );

        Authentication authenticate = authenticationManager.authenticate(user);

        String token = tokenService.generateToken((UsuarioEntity) authenticate.getPrincipal());
        return token;
    }

}
