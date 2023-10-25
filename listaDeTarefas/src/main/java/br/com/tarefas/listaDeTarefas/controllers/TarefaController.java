package br.com.tarefas.listaDeTarefas.controllers;

import br.com.tarefas.listaDeTarefas.converter.TarefaConverter;
import br.com.tarefas.listaDeTarefas.dto.BoredDTO;
import br.com.tarefas.listaDeTarefas.dto.TarefaDTO;
import br.com.tarefas.listaDeTarefas.repository.TarefaRepository;
import br.com.tarefas.listaDeTarefas.services.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/todos")
    public List<TarefaDTO> todos() {
        return tarefaService.todos();
    }

    @GetMapping("/buscar/{atividade}")
    public List<TarefaDTO> buscarAtividade(@PathVariable("atividade") String atividade) {
        try {
            return this.tarefaService.buscarAtividade(atividade);
        } catch (RuntimeException e) {
            return null;
        }
    }

    @PutMapping(value = "/editar")
    public TarefaDTO editar(@RequestBody TarefaDTO tarefaDTO) {
        return this.tarefaService.editar(tarefaDTO);
    }

    @DeleteMapping(value = "/excluir/{atividade}")
    public void excluir(@PathVariable("atividade") String atividade) {
        this.tarefaService.excluir(atividade);
    }

}
