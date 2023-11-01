package br.com.tarefas.listaDeTarefas.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_tarefas")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String numeroTarefa;

    @Column(nullable = false)
    private String atividade;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataCriacao;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataEntrega;

    @Column
    private String dono;
}
