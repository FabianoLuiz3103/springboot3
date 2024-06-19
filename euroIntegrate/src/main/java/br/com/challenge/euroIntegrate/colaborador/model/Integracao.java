package br.com.challenge.euroIntegrate.colaborador.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="tbl_integracao")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of={"id"})
public class Integracao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String status;
    private Integer qtdColaboradores;
    private Double mediaProgresso;
    private Double mediaAcertos;

    @OneToMany(mappedBy = "integracao")
    private List<Colaborador> colaboradores = new ArrayList<>();

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_rh", nullable = false)
    private ColaboradorRh colaboradorRh;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_departamento", nullable = false)
    private Departamento departamento;

    @ManyToMany(mappedBy = "integracoes", fetch = FetchType.EAGER)
    private Set<Videos> videos = new HashSet<>();

    @ManyToMany(mappedBy = "integracoes", fetch = FetchType.EAGER)
    private Set<Normas> normas = new HashSet<>();

}
