package br.com.challenge.euroIntegrate.colaborador.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_colaborador")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public class Colaborador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String primeiroNome;
    private String sobrenome;
    private String cpf;
    private String email;
    private String telefone;
    private LocalDate dataAdmissao;
    private String avatar;
    private String stsIntegracao;
    private Integer qtdRespondidas;
    private Integer pontuacao;
    private Integer qtdCertas;
    private Double porcPorgresso;
    private Double porcAcertos;

    //id departamento

    //id integracao
}
