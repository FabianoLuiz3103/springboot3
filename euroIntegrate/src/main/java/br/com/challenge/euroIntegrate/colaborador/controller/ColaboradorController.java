package br.com.challenge.euroIntegrate.colaborador.controller;

import br.com.challenge.euroIntegrate.colaborador.dto.*;
import br.com.challenge.euroIntegrate.colaborador.model.Normas;
import br.com.challenge.euroIntegrate.colaborador.service.ColaboradorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/colaboradores")
public class ColaboradorController {

    @Autowired
    private ColaboradorService colaboradorService;

    @GetMapping("/home")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<DadosHomeColaborador> telaHome(Authentication authentication) {
        String email = authentication.getName();
        return new ResponseEntity<>(colaboradorService.dadosHomeColaborador(email), HttpStatus.OK);
    }
    @GetMapping("/perfil")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<DadosDetalhamentoColaborador> telaPerfil(Authentication authentication){
        String email = authentication.getName();
        return new ResponseEntity<>(colaboradorService.dadosColaborador(email), HttpStatus.OK);
    }

    @PatchMapping("/avatar")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<DadosDetalhamentoColaborador> atualizarAvatar(@RequestBody @Valid DadosAtualizacaoAvatar dados, Authentication authentication){
        String email = authentication.getName();
        var colaborador = colaboradorService.atualizacaoAvatar(dados, email);
        return new ResponseEntity<>(colaborador, HttpStatus.OK);
    }

    @GetMapping("/videos")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<DadosVideos>> telaVideos(Authentication authentication) {
        String email = authentication.getName();
        var videosComPerguntas = colaboradorService.carregarVideosComPerguntas(email);
        return new ResponseEntity<>(videosComPerguntas, HttpStatus.OK);

    }
    @GetMapping("/normas-departamento")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<DadosNormas>> telaNormasDepartamento(Authentication authentication){
        String email = authentication.getName();
        var normasComPerguntas = colaboradorService.carregarNormasComPerguntasDept(email);
        return new ResponseEntity<>(normasComPerguntas, HttpStatus.OK);
    }

    @GetMapping("/normas-gerais")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<DadosNormas>> telaNormasGerais(){
        var normasComPerguntas = colaboradorService.carregarNormasComPerguntasGeral();
        return new ResponseEntity<>(normasComPerguntas, HttpStatus.OK);
    }



}
