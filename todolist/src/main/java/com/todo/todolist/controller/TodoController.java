package com.todo.todolist.controller;

import com.todo.todolist.entity.Todo;
import com.todo.todolist.repository.TodoRepository;
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
    	//busca tarefa no BD
    	Optional<Todo> optionalTodo = todoRepository.findById(id);
    	
    	//verifica se encontrada
    	if (optionalTodo.isPresent()) {
    		// pega o objeto
    		Todo todoExistente = optionalTodo.get();
    		
    		//att campos
    		todoExistente.setTitulo(todoDetalhes.getTitulo());
    		todoExistente.setDescricao(todoDetalhes.getDescricao());
    		todoExistente.setFeito(todoDetalhes.isFeito());
    		
    		//salva att e retorna ok
    		return ResponseEntity.ok(todoRepository.save(todoExistente));
    	} else {
    		//se não, retorna 404 not found
    		return ResponseEntity.notFound().build();
    	}
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTodo(@PathVariable Long id) {
    	
    	//verifica se tarefa existe
    	if (todoRepository.existsById(id)) {
    		//se existe = deleta
    		todoRepository.deleteById(id);
    		//retorna sucesso, sem conteúdo
    		return ResponseEntity.noContent().build();
    	} else {
    		//se não existe, retorna 404 not found
    		return ResponseEntity.notFound().build();
    	}
    }
}