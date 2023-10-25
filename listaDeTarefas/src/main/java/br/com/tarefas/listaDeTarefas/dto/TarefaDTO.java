package br.com.tarefas.listaDeTarefas.dto;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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

    private LocalDateTime dataCriacao;

    private LocalDateTime dataEntrega;

    private String dono;
}
