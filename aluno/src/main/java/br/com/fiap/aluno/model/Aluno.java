package br.com.fiap.aluno.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name="tbl_aluno")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Campo requerido!")
    @Size(min = 3, message = "O nome deve ter no mínimo 3 caracteres!")
    private String nome;
    @NotBlank(message = "Campo requerido!")
    @Email(message = "Formato de email inválido!")
    private String email;
    @NotBlank(message = "Campo requerido")
    private String rm;
    @NotBlank(message = "Campo requerido")
    private String turma;

    public Aluno() {
    }

    public Aluno(Long id, String nome, String email, String rm, String turma) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.rm = rm;
        this.turma = turma;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRm() {
        return rm;
    }

    public void setRm(String rm) {
        this.rm = rm;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return Objects.equals(id, aluno.id) && Objects.equals(nome, aluno.nome) && Objects.equals(email, aluno.email) && Objects.equals(rm, aluno.rm) && Objects.equals(turma, aluno.turma);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, email, rm, turma);
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", rm='" + rm + '\'' +
                ", turma='" + turma + '\'' +
                '}';
    }
}
