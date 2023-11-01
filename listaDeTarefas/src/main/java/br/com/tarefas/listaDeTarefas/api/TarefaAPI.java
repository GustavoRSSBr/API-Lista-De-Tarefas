package br.com.tarefas.listaDeTarefas.api;

import br.com.tarefas.listaDeTarefas.dto.BoredDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "tarefa-client", url = "https://www.boredapi.com")

public interface TarefaAPI {

    @GetMapping("/api/activity")
    BoredDTO gerarTarefa();

}
