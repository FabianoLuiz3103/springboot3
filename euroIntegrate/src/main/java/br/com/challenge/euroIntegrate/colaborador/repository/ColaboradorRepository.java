package br.com.challenge.euroIntegrate.colaborador.repository;

import br.com.challenge.euroIntegrate.autenticacao.usuario.Usuario;
import br.com.challenge.euroIntegrate.colaborador.model.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {

    Optional<Colaborador> findByEmail(String email);
    Colaborador getReferenceByEmail(String email);
}
