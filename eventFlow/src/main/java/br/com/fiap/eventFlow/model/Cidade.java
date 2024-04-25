package br.com.fiap.eventFlow.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

/*
 * FIAP 2024
 * @author Fabiano Luiz Santos de Sanatana ---- RM:96044
 */

//Lombok
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(of = {"id"})
//JPA
@Entity
@Table(name="tbl_cidade")
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Campo requerido! ")
    @Size(min=3, message = "O nome da cidade deve ter no mínimo 3 caracteres! ")
    private String nome;

    @NotBlank(message = "Campo requerido! ")
    @Size(min=2, message = "O nome da cidade deve ter no mínimo 2 caracteres! ")
    private String estado;

    @OneToMany(mappedBy = "cidade")
    private List<Evento> eventos = new ArrayList<>();
}
