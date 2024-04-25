package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

//Classe controller rest
@RestController
//URL desse controller, mapeando url barra médicos
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired //Injeção de dependências.
    private MedicoRepository repository;

    //Requisição POST
    //Se chegar requisição do tipo post para a url /médicos
    //Chamar método cadastrar
    @PostMapping
    //Puxar o parametro do corpo da requisição com o requestbody
    //Sem essa anotação o Spring não ler o corpo da requisição e mapear os campos para o DTO
    //Uma das formas de receber informações
    //Usando java record para fazer o DTO

    @Transactional //Ter transação ativa com banco de dados
    //Tem o valid para o spring executar as validações em cima desse dto
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder){
        var medico = new Medico(dados);
        repository.save(medico);
        //Para cadastro tem que devolver o 201
        //Na resposta tem que devolver o os dados que acabaram de cadastrar
        //Cabeçalho HTTP
        //'uri' representa o endereço para o spring criar o cabeçalho automaticamente
        //'dto' o que vai voltar no corpo
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri(); //id que acabou de ser gerado
         return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));

    }

    /*
     Devolver a informação do cliente
    Não precisa ter transação ativa pq é só leitura
    Não podemos trabalhar diretamente com as entidades JPA
    porque isso causa vunerabilidade e também retorna todos os atributos
    no formato JSON e as vezes só usamos alguns; também pode dar StackOverFlow
    Então não podemos passar 'Medico' no <> List
    Fazer um DTO que devolve os dados
     */
    @GetMapping
    /*
     Interface do Spring para paginação
    Size e page da url, o spring passa pro pageable e mostra quantidade
    de itens por página e o número da página
    O sort também fica na url e o spring passa para o Pageable
    por padrão é crescente

    O pageable default muda o valor do que o Spring tem como padrão se não for inserido parâmetro na url
    Com a url vazia agora tem que carregar 10 itens ordenados pelo nome
     */
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size=10, sort = {"nome"}) Pageable paginacao){
        /*
        Devolve uma lista da entidade médico
        Então é preciso fazer uma conversão por causa do retorno ser DTO
        Usando list tem que usar o Stream que transforma em fluxo de objetos,
        o map usa no list e no page já mapeia cada objeto do fluxo
        para um novo objeto do tipo DadosListagem, ou seja, converte
        o toList transforma em uma lista novamente
        :: faz referência ao construtor da classe
        Com paginação não devolve List, mas sim o tipo Page do spring que devolve
        as informações do DTO e as informações sobre a paginação
         */
        //Pela nomenclatura ele já sabe que o select precisa de ativo = true
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
        return ResponseEntity.ok(page); //Vai devolver o 200 com a página
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        //Por padrão a exception é devolvida por erro 500
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    /*
    Atualização de dados
    Precisa de transção porque estou fazendo alteração
     */
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
        /*
        A JPA vai detectar que foi feita alteração no objeto médico que ela retornou do banco
        e vai fazer update automático
         */
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    /*
    Tem a exclusão tradicional e a exclusão lógica
    na tradicional ele é apagado do banco e na lógica ele fica "inativo"
    para fazer a lógica precisa de uma nova coluna no banco de dados
    ou seja, uma nova migration
     */

    //Pegar o id que está vindo da url, anotar como variavél do path
    //Precisa da transação
//    @DeleteMapping("/{id}")
//    @Transactional
    //Exclusão tradicional
    /*
     public void excluir(@PathVariable Long id){
        repository.deleteById(id);
    }

     */
    @DeleteMapping("/{id}")
    @Transactional
    //Exclusão lógica
    //No lugar de void usar responseEntity 204 na exlusão
    public ResponseEntity excluir(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        //this.excluir = false;
        medico.excluir();

        return ResponseEntity.noContent().build();
        //Para devolver um protocolo 204, sem conteúdo na resposta
    }

}
