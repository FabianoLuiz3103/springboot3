package br.com.challenge.euroIntegrate.colaborador.service;

import br.com.challenge.euroIntegrate.colaborador.dto.DadosAtualizacaoAvatar;
import br.com.challenge.euroIntegrate.colaborador.dto.DadosHomeColaborador;
import br.com.challenge.euroIntegrate.colaborador.model.Colaborador;
import br.com.challenge.euroIntegrate.colaborador.repository.ColaboradorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ColaboradorService {

    @Autowired
    ColaboradorRepository colaboradorRepository;

    @Transactional(readOnly = true)
    public Colaborador dadosColaborador(String email) {
        return colaboradorRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Colaborador não encontrado!"));
    }
    @Transactional
    public Colaborador atualizacaoAvatar(DadosAtualizacaoAvatar dados) {
        var colaborador = colaboradorRepository.getReferenceByEmail(dados.email());
        colaborador.atualizarAvatar(dados);
        //colaboradorRepository.save(colaborador);
        return colaborador;
    }

}
