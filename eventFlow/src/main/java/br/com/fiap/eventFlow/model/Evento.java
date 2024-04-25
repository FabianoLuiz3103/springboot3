package br.com.fiap.eventFlow.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

/*
 * FIAP 2024
 * @author Fabiano Luiz Santos de Sanatana ---- RM:96044
 * @author Lucas Aguida Mazzetto --------------- RM:93303
 */

//Lombok
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(of = {"id"})
//JPA
@Entity
@Table(name="tbl_evento")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Campo requerido!")
    @Size(min=5, message="O nome deve ter no mínimo 5 caracteres! ")
    private String nome;

    @NotBlank(message = "Campo requerido!")
    @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "Formato inválido. Utilize o formato dd/mm/yyyy.")
    private String data;

    @NotBlank(message = "Campo requerido")
    @URL(message ="URL inválida! Forneça uma URL válida." )
    private String url;

    @ManyToOne
    @JoinColumn(name="cidade_id", nullable = false)
    private Cidade cidade;

}
