package br.com.challenge.euroIntegrate.colaborador.controller;

import br.com.challenge.euroIntegrate.colaborador.dto.DadosAtualizacaoAvatar;
import br.com.challenge.euroIntegrate.colaborador.dto.DadosDetalhamentoColaborador;
import br.com.challenge.euroIntegrate.colaborador.dto.DadosHomeColaborador;
import br.com.challenge.euroIntegrate.colaborador.service.ColaboradorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/colaboradores")
public class ColaboradorController {

    @Autowired
    private ColaboradorService colaboradorService;

    @GetMapping("/home")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<DadosHomeColaborador> telaHome(Authentication authentication) {
        String email = authentication.getName();
        return new ResponseEntity<>(
                new DadosHomeColaborador(colaboradorService.dadosColaborador(email)), HttpStatus.OK);
    }
    @GetMapping("/perfil")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<DadosDetalhamentoColaborador> telaPerfil(Authentication authentication){
        String email = authentication.getName();
        return new ResponseEntity<>(
                new DadosDetalhamentoColaborador(colaboradorService.dadosColaborador(email)), HttpStatus.OK);
    }

    @PatchMapping("/avatar")
    public ResponseEntity<DadosDetalhamentoColaborador> atualizarAvatar(@RequestBody @Valid DadosAtualizacaoAvatar dados){
        var colaborador = colaboradorService.atualizacaoAvatar(dados);
        return new ResponseEntity<>(
                new DadosDetalhamentoColaborador(colaborador), HttpStatus.OK);
    }


}
