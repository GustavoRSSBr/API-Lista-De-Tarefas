package br.com.tarefas.listaDeTarefas.controllers;

import br.com.tarefas.listaDeTarefas.converter.TarefaConverter;
import br.com.tarefas.listaDeTarefas.dto.BoredDTO;
import br.com.tarefas.listaDeTarefas.dto.TarefaDTO;
import br.com.tarefas.listaDeTarefas.repository.TarefaRepository;
import br.com.tarefas.listaDeTarefas.services.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Page<TarefaDTO> todos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return tarefaService.todos(pageable);
    }


    @GetMapping("/buscar/{numeroTarefa}")
    public TarefaDTO buscarAtividade(@PathVariable("numeroTarefa") String numeroTarefa) {
        try {
            return this.tarefaService.buscarTarefa(numeroTarefa);
        } catch (RuntimeException e) {
            return null;
        }
    }

    @PutMapping("/editar/{numeroTarefa}")
    public TarefaDTO editar(@PathVariable("numeroTarefa") String numeroTarefa, @RequestBody TarefaDTO tarefaDTO) {
        return this.tarefaService.editar(numeroTarefa, tarefaDTO);
    }

    @DeleteMapping(value = "/excluir/{numeroTarefa}")
    public void excluir(@PathVariable("numeroTarefa") String numeroTarefa) {
        this.tarefaService.excluir(numeroTarefa);
    }

}
