package med.voll.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Para APIs rest, é preciso!!
@RestController
//Qual URL que esse controller vai responder
//Se chegar requisição localhost:8080/hello vai cair aqui
@RequestMapping("/hello")
public class HelloController {

    //Se a requisição for do tipo Get vai chamar esse
    @GetMapping
    public String olaMundo(){
        return "Hello World!";
    }
}
