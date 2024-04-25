package med.voll.api.domain.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.endereco.Endereco;

@Entity
@Table(name="medicos")
/*
Faz os getters, construtor vazio e construtor
com todos os atributos e; equals and hash em tempo
de compilação
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;

    //Enum do tipo String
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    //Vai continuar numa classe separada
    //Mas no banco de dados ele considera
    //Que os campos da classe endereço são da tabela de médicos
    //Para funcionar precisar tem @Embeddable na classe endereço
    @Embedded
    private Endereco endereco;

    private boolean ativo;

    public Medico(DadosCadastroMedico dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());

    }

    //Como é opcional atualizar um campo tem que fazer verificação para ver se não veio vazio
    public void atualizarInformacoes(DadosAtualizacaoMedico dados) {
        //Se for != significa que está vindo no DTO
        //Só vai sobrescrever/atualizar se tiver vindo
        if(dados.nome() != null){
            this.nome = dados.nome();
        }
        if(dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if(dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
