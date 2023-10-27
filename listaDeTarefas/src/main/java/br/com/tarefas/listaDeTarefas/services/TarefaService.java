package br.com.tarefas.listaDeTarefas.services;

import br.com.tarefas.listaDeTarefas.api.TarefaAPI;
import br.com.tarefas.listaDeTarefas.converter.TarefaConverter;
import br.com.tarefas.listaDeTarefas.dto.BoredDTO;
import br.com.tarefas.listaDeTarefas.dto.TarefaDTO;
import br.com.tarefas.listaDeTarefas.model.Tarefa;
import br.com.tarefas.listaDeTarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
        tarefa.setDataCriacao(LocalDateTime.now());
        UUID uuid = UUID.randomUUID();
        tarefa.setNumeroTarefa(uuid.toString());
        Tarefa tarefaDB = this.tarefaRepository.save(tarefa);
        return this.tarefaConverter.toTarefaDTO(tarefaDB);
    }


    public TarefaDTO criarBored() {

        BoredDTO bored = this.gerarTarefa();
        Tarefa tarefa = this.tarefaConverter.toTarefa(bored);
        UUID uuid = UUID.randomUUID();
        tarefa.setNumeroTarefa(uuid.toString());
        tarefa.setDataCriacao(LocalDateTime.now());
        Tarefa tarefaDB = this.tarefaRepository.save(tarefa);
        return this.tarefaConverter.toTarefaDTO(tarefaDB);
    }

    public Page<TarefaDTO> todos(Pageable pageable) {
        Page<Tarefa> tarefas = tarefaRepository.findAll(pageable);
        return tarefas.map(tarefa -> this.tarefaConverter.toTarefaDTO(tarefa));
    }


    public TarefaDTO buscarTarefa(String numeroTarefa) { 
        Optional<Tarefa> tarefa = this.tarefaRepository.findTarefaByNumeroTarefa(numeroTarefa);
        if(tarefa.isPresent()){
            return this.tarefaConverter.toTarefaDTO(tarefa.get());
        } else throw new RuntimeException("Tarefa não encontrada");

    }

    public TarefaDTO editar(String numeroAtividade, TarefaDTO tarefaDTO) {
        Optional<Tarefa> optionalTarefa = this.tarefaRepository.findTarefaByNumeroTarefa(numeroAtividade);

        if (optionalTarefa.isPresent()) {
            Tarefa tarefaDB = optionalTarefa.get();

            Tarefa tarefaNova = Tarefa.builder()
                    .id(tarefaDB.getId())
                    .numeroTarefa(tarefaDB.getNumeroTarefa())
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

    public void excluir(String numeroTarefa) {
        Optional<Tarefa> optionalTarefa = this.tarefaRepository.findTarefaByNumeroTarefa(numeroTarefa);
        optionalTarefa.ifPresent(tarefa -> this.tarefaRepository.delete(tarefa));
    }
}
