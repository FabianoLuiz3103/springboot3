package br.com.fiap.eventFlow.controller;

import br.com.fiap.eventFlow.model.Cidade;
import br.com.fiap.eventFlow.service.CidadeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @GetMapping("/form")
    public String loadForm(Model model){
        model.addAttribute("cidade", new Cidade());
        return "cidade/nova-cidade";
    }

    @PostMapping()
    public String insert(@Valid Cidade cidade,
                         BindingResult result,
                         RedirectAttributes redirect){
        if(result.hasErrors()){
            return "cidade/nova-cidade";
        }
        cidade = cidadeService.insert(cidade);
        redirect.addFlashAttribute("sucesso", "Cidade salva com sucesso!");
        return "redirect:/cidades/form";
    }

    @GetMapping()
    public String findAll(Model model){
        model.addAttribute("cidades", cidadeService.findAll());
        return "cidade/listar-cidades";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id,
                           Model model){
        model.addAttribute("cidade", cidadeService.findById(id));
        return "cidade/editar-cidade";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id,
                         @Valid Cidade cidade,
                         BindingResult result){
        if(result.hasErrors()){
            cidade.setId(id);
            return "cidade/editar-cidade";
        }
        cidadeService.update(id, cidade);
        return "redirect:/cidades";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id, Model model){
        cidadeService.delete(id);
        return "redirect:/cidades";
    }
}
