package br.com.tarefas.listaDeTarefas.dto;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoredDTO {

    private String activity;

    private String type;

    private Integer participants;

    private float price;

    private String link;

    private String key;

    private Double accessibility;

}