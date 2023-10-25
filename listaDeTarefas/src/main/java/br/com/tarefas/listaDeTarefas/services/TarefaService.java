package br.com.tarefas.listaDeTarefas.services;

import br.com.tarefas.listaDeTarefas.api.TarefaAPI;
import br.com.tarefas.listaDeTarefas.dto.BoredDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TarefaService {

    @Autowired
    private TarefaAPI tarefa;

    public BoredDTO gerarTarefa() {
        return tarefa.gerarTarefa();
    }

}
