package br.com.fiap.eventFlow.repository;

import br.com.fiap.eventFlow.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}
