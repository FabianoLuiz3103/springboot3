package br.com.challenge.euroIntegrate.colaborador.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_videos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of={"id"})
public class Videos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String linkVideo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_video_integracao",
            joinColumns = @JoinColumn(name = "id_video"),
            inverseJoinColumns = @JoinColumn(name = "id_integracao"))
    private Set<Integracao> integracoes = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_pergunta_video",
            joinColumns = @JoinColumn(name = "id_video"),
            inverseJoinColumns = @JoinColumn(name = "id_pergunta"))
    private Set<Perguntas> perguntas = new HashSet<>();
}
