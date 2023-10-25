package br.com.tarefas.listaDeTarefas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ListaDeTarefasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ListaDeTarefasApplication.class, args);
	}

}
