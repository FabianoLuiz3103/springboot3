package br.com.challenge.euroIntegrate.colaborador.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tbl_perguntas")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public class Perguntas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String enunciado;
    private String respostaCorreta;
    private String tipo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_departamento", nullable = false)
    private Departamento departamento;

    @ManyToMany(mappedBy = "perguntas", fetch = FetchType.EAGER)
    private Set<Videos> videos = new HashSet<>();

    @ManyToMany(mappedBy = "perguntas", fetch = FetchType.EAGER)
    private Set<Normas> normas = new HashSet<>();
}
