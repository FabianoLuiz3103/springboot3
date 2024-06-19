package br.com.challenge.euroIntegrate.colaborador.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tbl_normas")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public class Normas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_norma_integracao",
            joinColumns = @JoinColumn(name = "id_norma"),
            inverseJoinColumns = @JoinColumn(name = "id_integracao"))
    private Set<Integracao> integracoes = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_pergunta_norma",
            joinColumns = @JoinColumn(name = "id_norma"),
            inverseJoinColumns = @JoinColumn(name = "id_pergunta"))
    private Set<Perguntas> perguntas = new HashSet<>();
}
