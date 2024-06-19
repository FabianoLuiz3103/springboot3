package br.com.challenge.euroIntegrate.colaborador.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tbl_departamento")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of={"id"})
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @OneToMany(mappedBy = "departamento")
    private List<Colaborador> colaboradores = new ArrayList<>();

    @OneToMany(mappedBy = "departamento")
    private List<Integracao> integracoes = new ArrayList<>();

    @OneToMany(mappedBy = "departamento")
    private List<Perguntas> perguntas = new ArrayList<>();
}
