package br.com.challenge.euroIntegrate.colaborador.controller;

import br.com.challenge.euroIntegrate.colaborador.dto.DadosHomeColaborador;
import br.com.challenge.euroIntegrate.colaborador.service.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/colaboradores")
public class ColaboradorController {

    @Autowired
    private ColaboradorService colaboradorService;

    @GetMapping("/home")
    public ResponseEntity<DadosHomeColaborador> telaHome(){
        return new ResponseEntity<>(colaboradorService.dadosHome(), HttpStatus.OK);
    }


}
