package br.com.challenge.euroIntegrate.colaborador.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tbl_colaborador_rh")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of={"id"})
public class ColaboradorRh {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String emailRh;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_colaborador", nullable = false)
    private Colaborador colaborador;

    @OneToMany(mappedBy = "colaboradorRh")
    private List<Integracao> integracoes = new ArrayList<>();
}
