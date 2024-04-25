package br.com.fiap.aluno.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(Model model){
        model.addAttribute("mensagem", "Seja bem-vindo(a) ao portal FIAP");
        return "/aluno/index";
    }
}
