package br.com.fiap.eventFlow.repository;

import br.com.fiap.eventFlow.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {
}
