package br.com.challenge.euroIntegrate.colaborador.service;

import br.com.challenge.euroIntegrate.colaborador.dto.*;
import br.com.challenge.euroIntegrate.colaborador.model.Normas;
import br.com.challenge.euroIntegrate.colaborador.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ColaboradorService {

    @Autowired
    ColaboradorRepository colaboradorRepository;

    @Autowired
    VideosRepository videosRepository;

    @Autowired
    NormasRepository normasRepository;

    @Transactional(readOnly = true)
    public DadosHomeColaborador dadosHomeColaborador(String email) {
        return new DadosHomeColaborador(colaboradorRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Colaborador não encontrado!")));
    }

    @Transactional(readOnly = true)
    public DadosDetalhamentoColaborador dadosColaborador(String email) {
        return new DadosDetalhamentoColaborador(colaboradorRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Colaborador não encontrado!")));
    }

    @Transactional
    public DadosDetalhamentoColaborador atualizacaoAvatar(DadosAtualizacaoAvatar dados) {
        var colaborador = colaboradorRepository.getReferenceByEmail(dados.email());
        colaborador.atualizarAvatar(dados);
        return new DadosDetalhamentoColaborador(colaborador);
    }



    @Transactional(readOnly = true)
    public List<DadosVideos> carregarVideosComPerguntas(String email) {
        Long idDept = colaboradorRepository.findDepartamentoIdByEmail(email).orElseThrow(
                ()-> new RuntimeException("Colaborador não encontrado!")
        );
        var videos = videosRepository.findAllByDepartamentoIdWithPerguntasAndOpcoes(idDept);
        return videos.stream()
                .map(video -> {
                    var perguntas = video.getPerguntas().stream()
                            .map(pergunta -> {
                                var opcoes = pergunta.getOpcoes().stream()
                                        .map(DadosOpcoes::new)
                                        .toList();
                                return new DadosPerguntas(pergunta, opcoes);
                            })
                            .toList();
                    return new DadosVideos(video, perguntas);
                })
                .toList();
    }

    @Transactional(readOnly = true)
    public List<DadosNormas> carregarNormasComPerguntasDept(String email) {
        Long idDept = colaboradorRepository.findDepartamentoIdByEmail(email).orElseThrow(
                ()-> new RuntimeException("Colaborador não encontrado!"));
        var normas = normasRepository.findAllByDepartamentoIdWithPerguntasAndOpcoes(idDept);
        return perguntasNormas(normas);
    }

    @Transactional(readOnly = true)
    public List<DadosNormas> carregarNormasComPerguntasGeral() {
        var normas = normasRepository.findAllByDepartamentoIdIsNull();
        return perguntasNormas(normas);
    }

    private List<DadosNormas> perguntasNormas(List<Normas> normas){
        return normas.stream()
                .map(
                        norma -> {
                            var perguntas = norma.getPerguntas().stream().map(
                                    pergunta -> {
                                        var opcoes = pergunta.getOpcoes().stream()
                                                .map(DadosOpcoes::new)
                                                .toList();
                                        return new DadosPerguntas(pergunta, opcoes);
                                    })
                                    .toList();
                            return new DadosNormas(norma, perguntas);
                        }
                ).toList();
    }




}
