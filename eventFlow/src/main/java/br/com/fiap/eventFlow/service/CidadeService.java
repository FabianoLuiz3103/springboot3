package br.com.fiap.eventFlow.service;

import br.com.fiap.eventFlow.model.Cidade;
import br.com.fiap.eventFlow.repository.CidadeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository repository;

    /*
    Método que faz insert
     */
    @Transactional
    public Cidade insert(Cidade cidade){
        return repository.save(cidade);
    }

    /*
    Método que retorna todos os registros
     */
    @Transactional(readOnly = true)
    public List<Cidade> findAll(){
        return repository.findAll();
    }

    /*
    Método que retorna somente um registro pelo id
     */
    @Transactional(readOnly = true)
    public Cidade findById(Long id){
        return repository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Recurso não encontrado com id: " + id)
        );
    }

    /*
    Método que faz atualiza um registro
     */
    @Transactional
    public Cidade update(Long id, Cidade entity){
        try{
            Cidade cidade = repository.getReferenceById(id);
            copyToCidade(entity, cidade);
            repository.save(cidade);
            return cidade;
        }catch (EntityNotFoundException e){
            throw new IllegalArgumentException("Recurso não encontrado com id: " + id);
        }
    }

    /*
    Método que deleta um registro
     */
    @Transactional
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new IllegalArgumentException("Recurso não encontrado com id: " + id);
        }
        try{
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e ){
            throw new IllegalArgumentException("Falha de integridade referêncial id: " + id);
        }
    }

    /*
    Método que atualiza os atributos da referência cidade com os novos dados
    vindos do objeto cidade atualizado
     */
    private void copyToCidade(Cidade entity, Cidade cidade){
        cidade.setNome(entity.getNome());
        cidade.setEstado(entity.getEstado());
    }
}

