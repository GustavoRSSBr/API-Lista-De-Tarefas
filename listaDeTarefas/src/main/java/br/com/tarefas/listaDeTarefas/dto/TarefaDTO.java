package br.com.tarefas.listaDeTarefas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TarefaDTO {

    private String atividade;

    private String numeroTarefa;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataEntrega;

    private String dono;
}
