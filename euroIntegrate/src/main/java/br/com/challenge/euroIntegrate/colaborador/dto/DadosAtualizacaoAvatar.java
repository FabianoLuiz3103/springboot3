package br.com.challenge.euroIntegrate.colaborador.dto;

import br.com.challenge.euroIntegrate.colaborador.model.Colaborador;
import jakarta.validation.constraints.NotBlank;

public record DadosAtualizacaoAvatar(
        @NotBlank
        String email,
        String avatar
) {

        public DadosAtualizacaoAvatar(Colaborador colaborador){
                this(colaborador.getEmail(), colaborador.getAvatar());
        }
}
