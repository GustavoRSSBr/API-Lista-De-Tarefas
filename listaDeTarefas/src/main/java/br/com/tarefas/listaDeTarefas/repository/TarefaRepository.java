package br.com.tarefas.listaDeTarefas.repository;

import br.com.tarefas.listaDeTarefas.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TarefaRepository extends JpaRepository <Tarefa, Long> {
    public Optional<Tarefa> findTarefaByNumeroTarefa(String numeroTarefa);
}
