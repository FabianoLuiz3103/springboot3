package br.com.fiap.aluno.controller;

import br.com.fiap.aluno.model.Aluno;
import br.com.fiap.aluno.repository.AlunoRepository;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/alunos")
public class AlunoController {

    //Fazer injeção de depêndencias na camada repository
    @Autowired
    private AlunoRepository repository;

    //Carregar o form de cadastro
    @GetMapping("/novo")
    public String novoAluno(Model model){
        model.addAttribute("aluno", new Aluno());
        return "/aluno/novo-aluno";
    }

    @PostMapping("/salvar")
    @Transactional
    public String salvar(@Valid Aluno aluno,
                         BindingResult result,
                         RedirectAttributes
                         atributo){
        if(result.hasErrors()){
            return "/aluno/novo-aluno";
        }
        repository.save(aluno);
        atributo.addFlashAttribute("sucesso", "Aluno cadastrado com sucesso!");
        //atributo sendo passado para a prox requisição, com o flash ele vai ser armazenado só até ela
        return "redirect:/alunos/novo";
    }

    @GetMapping("/listar")
    @Transactional(readOnly = true)
    public String listar(Model model){
        model.addAttribute("alunos", repository.findAll());
        return "/aluno/lista-aluno";
    }

    //Vai carregar os dados no form para edição
    @GetMapping("/editar/{id}")
    @Transactional(readOnly = true)
    public String editar(@PathVariable("id") Long id, Model model){
        Aluno aluno = repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Aluno não encontrado para o id: " + id)
        );
        model.addAttribute("aluno", aluno);
        return "/aluno/editar-aluno";
    }

    //Atualizando um aluno no banco
    @PostMapping("/editar/{id}")
    @Transactional
    public String editar(@PathVariable("id") Long id, @Valid Aluno aluno, BindingResult result){
        if(result.hasErrors()){
            aluno.setId(id);
            return "/aluno/editar-aluno";
        }
        repository.save(aluno);
        return "redirect:/alunos/listar";
    }

    //Excluindo um aluno
    @GetMapping("/deletar/{id}")
    @Transactional
    public String deletar(@PathVariable("id") Long id, Model model){
        if(!repository.existsById(id)){
            throw new IllegalArgumentException("Aluno não encontrado para o id: " + id);
        }
        try{
            repository.deleteById(id);
        } catch(Exception e){
            throw new IllegalArgumentException("Aluno não encontrado para o id: " + id);
        }
        return "redirect:/alunos/listar";
    }
}
