package com.todos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.todos.exception.ResponseStatusException;
import com.todos.model.Todo;
import com.todos.repository.TodoRepository;

import java.util.List;

@Service
public class TodoJpaService {
    private final TodoRepository todoRepository;

    @Autowired
    public TodoJpaService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Todo getTodoById(int id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Todo not found with id: " + id));
    }
   
   

    public Todo saveOrUpdateTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public void deleteTodoById(int id) {
        todoRepository.deleteById(id);
    }
}
