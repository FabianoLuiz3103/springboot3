package br.com.fiap.eventFlow.controller;

import br.com.fiap.eventFlow.model.Cidade;
import br.com.fiap.eventFlow.model.Evento;
import br.com.fiap.eventFlow.service.CidadeService;
import br.com.fiap.eventFlow.service.EventoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private CidadeService cidadeService;

    @GetMapping("/form")
    public String loadForm(Model model){
        model.addAttribute("evento", new Evento());
        return "evento/novo-evento";
    }

    @ModelAttribute("cidades")
    public List<Cidade> cidades(){
        return cidadeService.findAll();
    }

    @PostMapping()
    public String insert(@Valid Evento evento,
                         BindingResult result,
                         RedirectAttributes redirect){
        if(result.hasErrors()){
            return "evento/novo-evento";
        }
        evento  = eventoService.insert(evento);
        redirect.addFlashAttribute("sucesso", "Evento salvo com sucesso!");
        return "redirect:/eventos/form";
    }

    @GetMapping()
    public String findAll(Model model){
        model.addAttribute("eventos", eventoService.findAll());
        return "evento/listar-eventos";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model){
        model.addAttribute("evento", eventoService.findById(id));
        return "evento/editar-evento";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id,
                         @Valid Evento evento,
                         BindingResult result){
        if(result.hasErrors()){
            evento.setId(id);
            return "evento/editar-evento";
        }
        eventoService.update(id, evento);
        return "redirect:/eventos";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id, Model model){
        eventoService.delete(id);
        return "redirect:/eventos";
    }

}
