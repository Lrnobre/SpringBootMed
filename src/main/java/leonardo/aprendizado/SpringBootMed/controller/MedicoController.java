package leonardo.aprendizado.SpringBootMed.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import leonardo.aprendizado.SpringBootMed.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid dadosCadastroMedico dados){
        repository.save(new Medico(dados));
    }

    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = "crm", direction = Sort.Direction.ASC) Pageable paginacao ) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public void excluisaoLogica(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.excluir();
    }

}
