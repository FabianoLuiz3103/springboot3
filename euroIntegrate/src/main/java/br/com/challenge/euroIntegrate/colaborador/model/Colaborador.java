package br.com.challenge.euroIntegrate.colaborador.model;

import br.com.challenge.euroIntegrate.colaborador.dto.DadosAtualizacaoAvatar;
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
    @Column(unique = true)
    private String cpf;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String telefone;
    private LocalDate dataAdmissao;
    private String avatar;
    private String stsIntegracao;
    private Integer qtdRespondidas;
    private Integer pontuacao;
    private Integer qtdCertas;
    private Double porcProgresso;
    private Double porcAcertos;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_departamento", nullable = false)
    private Departamento departamento;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_integracao")
    private Integracao integracao;

    @OneToOne(mappedBy = "colaborador")
    private ColaboradorRh colaboradorRh;



    public void atualizarAvatar(DadosAtualizacaoAvatar dados){
        if(dados.avatar() != null){
            this.avatar = dados.avatar();
        }
    }
}
