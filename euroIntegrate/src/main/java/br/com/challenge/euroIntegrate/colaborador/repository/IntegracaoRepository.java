package br.com.challenge.euroIntegrate.colaborador.repository;

import br.com.challenge.euroIntegrate.colaborador.model.Integracao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntegracaoRepository extends JpaRepository<Integracao, Long> {
}
