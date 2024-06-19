package br.com.challenge.euroIntegrate.autenticacao.controller;

import br.com.challenge.euroIntegrate.autenticacao.dto.LoginDTO;
import br.com.challenge.euroIntegrate.autenticacao.dto.TokenDTO;
import br.com.challenge.euroIntegrate.autenticacao.service.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class AutenticacaoController {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> autenticarUsuario(@RequestBody LoginDTO login){
        var token = autenticacaoService.autenticarUsuario(login);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<String> getAuthenticationTest() {
        return new ResponseEntity<>("Autenticado com sucesso", HttpStatus.OK);
    }

    @GetMapping("/test/customer")
    public ResponseEntity<String> getCustomerAuthenticationTest() {
        return new ResponseEntity<>("Cliente autenticado com sucesso", HttpStatus.OK);
    }

    @GetMapping("/test/administrator")
    public ResponseEntity<String> getAdminAuthenticationTest() {
        return new ResponseEntity<>("Administrador autenticado com sucesso", HttpStatus.OK);
    }
}
