package br.com.challenge.euroIntegrate.dto;

import br.com.challenge.euroIntegrate.model.usuario.Role;

import java.util.List;

public record RecuperarUsuarioDTO(

        Long id,
        String email,
        List<Role> roles
) {
}
