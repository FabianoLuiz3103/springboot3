package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.DadosEndereco;

public record DadosCadastroMedico(
        // NotBlank Só para campos String e NotNull para os outros
        @NotBlank(message = "Nome é obrigatório")
        String nome,
        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Formato de email é inválido")
        String email,
        @NotBlank(message = "Telefone é obrigatório")
        String telefone,
        @NotBlank(message = "CRM é obrigatório")
        @Pattern(regexp = "\\d{4,6}", message = "Formato do CRM é inválido") //De 4 a 6 dígitos
        String crm,
        @NotNull(message = "Especialidade é obrigatória")
        Especialidade especialidade,
        @NotNull(message = "Dados de endereço são obrigatórios")
        @Valid //Para também validar o outro objeto
        DadosEndereco endereco) {
}
