package com.todos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todos.model.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {
}
