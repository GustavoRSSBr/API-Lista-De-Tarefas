package br.com.tarefas.listaDeTarefas.controllers;

import br.com.tarefas.listaDeTarefas.dto.BoredDTO;
import br.com.tarefas.listaDeTarefas.dto.TarefaDTO;
import br.com.tarefas.listaDeTarefas.services.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @RequestMapping(value = "/criar", method = RequestMethod.POST)
    public TarefaDTO criar(@RequestBody TarefaDTO tarefaDTO) {
        return this.tarefaService.criar(tarefaDTO);
    }

    @RequestMapping(value = "/criarAleatorio", method = RequestMethod.POST)
    public TarefaDTO criarBored() {
        return this.tarefaService.criarBored();
    }


}
