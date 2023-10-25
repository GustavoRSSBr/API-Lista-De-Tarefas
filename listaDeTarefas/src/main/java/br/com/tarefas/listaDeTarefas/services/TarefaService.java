package br.com.tarefas.listaDeTarefas.services;

import br.com.tarefas.listaDeTarefas.api.TarefaAPI;
import br.com.tarefas.listaDeTarefas.converter.TarefaConverter;
import br.com.tarefas.listaDeTarefas.dto.BoredDTO;
import br.com.tarefas.listaDeTarefas.dto.TarefaDTO;
import br.com.tarefas.listaDeTarefas.model.Tarefa;
import br.com.tarefas.listaDeTarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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




}
