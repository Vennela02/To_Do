package com.todos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.todos.exception.ResponseStatusException;
import com.todos.model.Todo;
import com.todos.service.TodoJpaService;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {
	private final TodoJpaService todoService;

	@Autowired
	public TodoController(TodoJpaService todoService) {
		this.todoService = todoService;
	}

	@GetMapping
	public List<Todo> getAllTodos() {
		return todoService.getAllTodos();
	}

	@PostMapping
	public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
	    if(("TO DO".equals(todo.getStatus()) || "IN PROGRESS".equals(todo.getStatus())  || "DONE".equals(todo.getStatus()))  ) {
	        Todo newTodo = todoService.saveOrUpdateTodo(todo);
	        return new ResponseEntity<>(newTodo, HttpStatus.CREATED);
	    }
	    throw new ResponseStatusException(HttpStatus.BAD_REQUEST," Possible values for status are TO DO, IN PROGRESS, and DONE");
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getTodoById(@PathVariable int id) {
		Todo todo = todoService.getTodoById(id);

		return ResponseEntity.ok(todo);

	}

	@PutMapping("/{id}")
	public Todo updateTodo(@PathVariable int id, @RequestBody Todo todoDetails) {
		
		Todo todo = todoService.getTodoById(id);
	    if( (("TO DO".equals(todoDetails.getStatus()) || "IN PROGRESS".equals(todoDetails.getStatus())  || "DONE".equals(todoDetails.getStatus())) ) ) {

		todo.setStatus(todoDetails.getStatus());
		return todoService.saveOrUpdateTodo(todo);
	    }
	    throw new ResponseStatusException(HttpStatus.BAD_REQUEST," Possible values for status are TO DO, IN PROGRESS, and DONE");

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTodoById(@PathVariable int id) {
		Todo todo = todoService.getTodoById(id);
			todoService.deleteTodoById(id);
		
		return ResponseEntity.ok().build();
	}
}
