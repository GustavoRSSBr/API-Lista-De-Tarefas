package br.com.tarefas.listaDeTarefas.services;

import br.com.tarefas.listaDeTarefas.api.TarefaAPI;
import br.com.tarefas.listaDeTarefas.converter.TarefaConverter;
import br.com.tarefas.listaDeTarefas.dto.BoredDTO;
import br.com.tarefas.listaDeTarefas.dto.TarefaDTO;
import br.com.tarefas.listaDeTarefas.model.Tarefa;
import br.com.tarefas.listaDeTarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TarefaService {

    @Autowired
    private TarefaAPI tarefaApi;

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private TarefaConverter tarefaConverter;


    public BoredDTO gerarTarefa() {
        return tarefaApi.gerarTarefa();
    }

    //INSERT - CREATE
    public TarefaDTO criar(TarefaDTO tarefaDTO) {
        Tarefa tarefa = this.tarefaConverter.toTarefa(tarefaDTO);
        Tarefa tarefaDB = this.tarefaRepository.save(tarefa);
        return this.tarefaConverter.toTarefaDTO(tarefaDB);
    }


    public TarefaDTO criarBored() {

        BoredDTO bored = this.gerarTarefa();
        Tarefa tarefa = this.tarefaConverter.toTarefa(bored);
        Tarefa tarefaDB = this.tarefaRepository.save(tarefa);
        return this.tarefaConverter.toTarefaDTO(tarefaDB);
    }

    public List<TarefaDTO> todos() {
        return this.tarefaRepository.findAll().stream()
                .map(tarefa -> this.tarefaConverter.toTarefaDTO(tarefa))
                .collect(Collectors.toList());
    }

    public List<TarefaDTO> buscarAtividade(String atividade) {
        List<Tarefa> tarefas = this.tarefaRepository.findAllByAtividadeIgnoreCaseContaining(atividade);
        return tarefas.stream()
                .map(this.tarefaConverter::toTarefaDTO)
                .collect(Collectors.toList());
    }

    public TarefaDTO editar(TarefaDTO tarefaDTO) {
        Optional<Tarefa> optionalTarefa = this.tarefaRepository.findByAtividadeIgnoreCaseContaining(tarefaDTO.getAtividade());

        if (optionalTarefa.isPresent()) {
            Tarefa tarefaDB = optionalTarefa.get();

            Tarefa tarefaNova = Tarefa.builder()
                    .id(tarefaDB.getId())
                    .atividade(tarefaDTO.getAtividade())
                    .dataCriacao(LocalDateTime.now())
                    .dataEntrega(tarefaDTO.getDataEntrega())
                    .dono(tarefaDTO.getDono())
                    .build();

            Tarefa tarefaNovaDB = this.tarefaRepository.save(tarefaNova);
            return this.tarefaConverter.toTarefaDTO(tarefaNovaDB);
        }
        throw new RuntimeException("Tarefa não encontrada.");
    }

    public void excluir(String atividade) {
        Optional<Tarefa> optionalTarefa = this.tarefaRepository.findByAtividadeIgnoreCaseContaining(atividade);
        optionalTarefa.ifPresent(tarefa -> this.tarefaRepository.delete(tarefa));
    }
}
