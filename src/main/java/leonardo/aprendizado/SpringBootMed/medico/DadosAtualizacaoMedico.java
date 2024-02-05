package leonardo.aprendizado.SpringBootMed.medico;

import jakarta.validation.constraints.NotNull;
import leonardo.aprendizado.SpringBootMed.endereco.Endereco;
import leonardo.aprendizado.SpringBootMed.endereco.dadosEndereco;

public record DadosAtualizacaoMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        dadosEndereco endereco) {
}
