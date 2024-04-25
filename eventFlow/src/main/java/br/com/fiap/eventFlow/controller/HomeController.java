package br.com.fiap.eventFlow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping()
    public String index(Model model){
        model.addAllAttributes(Map.of(
                "msgPt1", "Bem vindo(a) ao",
                "msgPt2", "Event",
                "msgPt3", "Flow"
        ));
        return "/home/index";
    }
}
