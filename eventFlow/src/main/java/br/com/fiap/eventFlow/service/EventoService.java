package br.com.fiap.eventFlow.service;

import br.com.fiap.eventFlow.model.Evento;
import br.com.fiap.eventFlow.repository.EventoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoRepository repository;

    /*
    Método que faz insert
     */
    @Transactional
    public Evento insert(Evento evento){
        return repository.save(evento);
    }

    /*
    Método que retorna todos os registros
     */
    @Transactional(readOnly = true)
    public List<Evento> findAll(){
        return repository.findAll();
    }

    /*
    Método que retorna um registro pelo id
     */
    @Transactional(readOnly = true)
    public Evento findById(Long id){
        return repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Recurso não encontrado com id: " + id)
        );
    }

    /*
    Método que atualiza um registro
     */
    @Transactional
    public Evento update(Long id, Evento entity){
        try{
            Evento evento = repository.getReferenceById(id);
            copyToEvento(entity, evento);
            repository.save(evento);
            return evento;
        }catch(EntityNotFoundException e){
            throw new IllegalArgumentException("Recurso não encontrato com id: " + id);
        }
    }

    /*
    Método que deleta um registro
     */
    @Transactional
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new IllegalArgumentException("Recurso não encontrado com id: " +id);
        }
        try{
            repository.deleteById(id);
        }catch (Exception e){
            throw new IllegalArgumentException("Recurso não encontrado com id: " + id);
        }

    }

    /*
 - Realizar atualizações
 Método recebe uma referência do evento com id 'x'
 sem dados atualizados e um objeto evento com os dados ataulizados.
  */
    private void copyToEvento(Evento entity, Evento evento){
        evento.setNome(entity.getNome());
        evento.setData(entity.getData());
        evento.setUrl(entity.getUrl());
        evento.setCidade(entity.getCidade());
    }

}
