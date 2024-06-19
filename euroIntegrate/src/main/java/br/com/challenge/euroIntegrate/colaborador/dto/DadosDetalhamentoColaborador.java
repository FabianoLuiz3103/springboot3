package br.com.challenge.euroIntegrate.colaborador.dto;

import br.com.challenge.euroIntegrate.colaborador.model.Colaborador;
import jakarta.persistence.Column;

import java.time.LocalDate;

public record DadosDetalhamentoColaborador(
        Long id,
        String primeiroNome,
        String sobrenome,
        String cpf,
        String email,
        String telefone,
        LocalDate dataAdmissao,
        String avatar,
        String stsIntegracao,
        Integer qtdRespondidas,
        Integer pontuacao,
        Integer qtdCertas,
        Double porcProgresso,
        Double porcAcertos
) {

    public DadosDetalhamentoColaborador(Colaborador colaborador){
        this(
                colaborador.getId(),
                colaborador.getPrimeiroNome(),
                colaborador.getSobrenome(),
                colaborador.getCpf(),
                colaborador.getEmail(),
                colaborador.getTelefone(),
                colaborador.getDataAdmissao(),
                colaborador.getAvatar(),
                colaborador.getStsIntegracao(),
                colaborador.getQtdRespondidas(),
                colaborador.getPontuacao(),
                colaborador.getQtdCertas(),
                colaborador.getPorcProgresso(),
                colaborador.getPorcAcertos()
        );
    }
}
