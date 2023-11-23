package br.com.tarefas.listaDeTarefas.test.rest;

import br.com.tarefas.listaDeTarefas.controllers.TarefaController;
import br.com.tarefas.listaDeTarefas.dto.TarefaDTO;
import br.com.tarefas.listaDeTarefas.services.TarefaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TarefaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TarefaController tarefaController;

    @Autowired
    private TarefaService tarefaService;

    @Test
    public void test_CreateRandomTask_ReturnsOkStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/tarefa/criarAleatorio"))
                .andExpect(status().isOk());
    }

    @Test
    public void test_CreateTask_ReturnsOkStatus() throws Exception {
        mockMvc.perform(post("/tarefa/criar")
                        .contentType("application/json")
                        .content("{\"atividade\":\"Tarefa Teste\"}")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numeroTarefa").exists())
                .andExpect(jsonPath("$.atividade", is("Tarefa Teste")));
    }


    @Test
    public void test_GetAllTasksWithSelectedPage() throws Exception {
        // Criando 20 tarefas aleatórias
        for (int x = 0; x < 20; x++) {
            tarefaController.criarBored();
        }

        mockMvc.perform(get("/tarefa/todos")
                        .param("page", "0")
                        .param("size", "10")
                        .param("sortBy", "id"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(10)));
    }

    @Test
    public void test_EditTask() throws Exception {
        TarefaDTO tarefa = tarefaController.criarBored();

        mockMvc.perform(put("/tarefa/editar/"+tarefa.getNumeroTarefa().toString())
                        .contentType("application/json")
                .content("{\"atividade\":\"Tarefa Editar\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.atividade", is("Tarefa Editar")));
    }

    @Test
    public void test_SearchTask() throws Exception {
        TarefaDTO tarefa = tarefaController.criarBored();

        mockMvc.perform(get("/tarefa/buscar/"+tarefa.getNumeroTarefa().toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.atividade", is(tarefa.getAtividade().toString())));
    }

    @Test
    public void test_DeleteTask() throws Exception {
        TarefaDTO tarefa = tarefaController.criarBored();

        mockMvc.perform(delete("/tarefa/excluir/"+tarefa.getNumeroTarefa().toString()))
                .andExpect(status().isOk());
    }

    @Test
    public void test_SearchForDeletedTask() throws Exception {
        TarefaDTO tarefa = tarefaController.criarBored();
        tarefaService.excluir(tarefa.getNumeroTarefa());

        mockMvc.perform(get("/tarefa/buscar/"+tarefa.getNumeroTarefa().toString()))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }
}