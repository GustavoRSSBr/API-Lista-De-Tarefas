package br.com.tarefas.listaDeTarefas.converter;

import br.com.tarefas.listaDeTarefas.dto.BoredDTO;
import br.com.tarefas.listaDeTarefas.dto.TarefaDTO;
import br.com.tarefas.listaDeTarefas.model.Tarefa;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TarefaConverter {

    public TarefaDTO toTarefaDTO(Tarefa tarefa) {
        return TarefaDTO.builder()
                .atividade(tarefa.getAtividade())
                .numeroTarefa(tarefa.getNumeroTarefa())
                .dataCriacao(tarefa.getDataCriacao())
                .dataEntrega(tarefa.getDataEntrega())
                .dono(tarefa.getDono())
                .build();
    }

    public Tarefa toTarefa(TarefaDTO tarefaDTO) {
        return Tarefa.builder()
                .atividade(tarefaDTO.getAtividade())
                .numeroTarefa(tarefaDTO.getNumeroTarefa())
                .dataCriacao(tarefaDTO.getDataCriacao())
                .dataEntrega(tarefaDTO.getDataEntrega())
                .dono(tarefaDTO.getDono())
                .build();
    }

    public Tarefa toTarefa(BoredDTO BoredDTO) {
        return Tarefa.builder()
                .atividade(BoredDTO.getActivity())
                .build();
    }
}
