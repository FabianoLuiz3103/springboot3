package br.com.challenge.euroIntegrate.service;

import br.com.challenge.euroIntegrate.dto.LoginDTO;
import br.com.challenge.euroIntegrate.dto.TokenDTO;
import br.com.challenge.euroIntegrate.model.usuario.UsuarioDetailsImpl;
import br.com.challenge.euroIntegrate.repository.UsuarioRepository;
import br.com.challenge.euroIntegrate.security.SecurityConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public TokenDTO autenticarUsuario(LoginDTO login){
        var usernamePasswordAutentication = new UsernamePasswordAuthenticationToken(login.email(), login.senha());
        var authentication = authenticationManager.authenticate(usernamePasswordAutentication);
        var usuarioDetails = (UsuarioDetailsImpl) authentication.getPrincipal();
        return new TokenDTO(tokenService.gerarToken(usuarioDetails));

    }
}
