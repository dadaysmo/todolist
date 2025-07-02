package com.todo.todolist.controller;

import com.todo.todolist.entity.Todo;
import com.todo.todolist.repository.TodoRepository;
import com.todo.todolist.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;
    
    @PostMapping
    public Todo criarTodo(@RequestBody Todo todo) {
    	return todoRepository.save(todo);
    }
    
    @GetMapping
    public List<Todo> listarTodos(){
    	return todoRepository.findAll();
    }
    
    @PutMapping ("/{id}")
    public ResponseEntity<Todo> atualizarTodo(@PathVariable Long id, @RequestBody Todo todoDetalhes){
     	
    	//verifica se encontrada a tarefa no BD
    	Todo todoExistente = todoRepository.findById(id)
    			.orElseThrow(() -> new ResourceNotFoundException("Tarefa com o ID " + id + " não foi encontrada"));
    		
    	//att campos
    	todoExistente.setTitulo(todoDetalhes.getTitulo());
    	todoExistente.setDescricao(todoDetalhes.getDescricao());
    	todoExistente.setFeito(todoDetalhes.isFeito());
    		
    	//salva att e retorna ok
    	return ResponseEntity.ok(todoRepository.save(todoExistente));
   }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTodo(@PathVariable Long id) {
    	
    	//verifica se tarefa existe
    	if (!todoRepository.existsById(id)) {
    		throw new ResourceNotFoundException("Tarefa com o ID " + id + " não foi encontrada");
    	}
    		
    		//retorna sucesso, sem conteúdo
    	todoRepository.deleteById(id);
    	return ResponseEntity.noContent().build();
    }
}