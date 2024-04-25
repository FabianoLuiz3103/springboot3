package br.com.fiap.aluno.repository;

import br.com.fiap.aluno.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
