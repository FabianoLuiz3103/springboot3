package br.com.challenge.euroIntegrate.colaborador.service;

import br.com.challenge.euroIntegrate.colaborador.dto.DadosHomeColaborador;
import br.com.challenge.euroIntegrate.colaborador.repository.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ColaboradorService {

    @Autowired
    ColaboradorRepository colaboradorRepository;

    @Transactional(readOnly = true)
    public DadosHomeColaborador dadosHome(){
        var colaborador = colaboradorRepository.findByEmail("").orElseThrow(()-> new RuntimeException("Colaborador não encontrado!"));
        return new DadosHomeColaborador(colaborador);
    }
}
