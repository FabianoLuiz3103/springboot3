package br.com.challenge.euroIntegrate.colaborador.dto;

import br.com.challenge.euroIntegrate.colaborador.model.Colaborador;

public record DadosHomeColaborador(

        String primeiroNome,
        String avatar,
        Integer pontuacao,
        Double porcProgresso
) {
    public DadosHomeColaborador(Colaborador colaborador){
        this(
                colaborador.getPrimeiroNome(),
                colaborador.getAvatar(),
                colaborador.getPontuacao(),
                colaborador.getPorcAcertos()
        );
    }
}
