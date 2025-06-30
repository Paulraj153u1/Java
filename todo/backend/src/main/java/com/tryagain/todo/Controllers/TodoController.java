package com.tryagain.todo.Controllers;

import com.tryagain.todo.Entity.TodoEntity;
import com.tryagain.todo.Exception.ResourceNotFoundException;
import com.tryagain.todo.Repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todos")
@CrossOrigin(origins = "http://localhost:3000")
public class TodoController {

    @Autowired
    TodoRepository todoRepository;

    @GetMapping
    public List<TodoEntity> findAll() {
        return todoRepository.findAll();
    }

    @PostMapping
    public TodoEntity create(@RequestBody TodoEntity todoEntity) {
        return todoRepository.save(todoEntity);
    }

    @GetMapping("/{id}")
    public TodoEntity getTodoId(@PathVariable long id) {
        return todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo id not found : " + id));
    }

    @PutMapping("/{id}")
    public TodoEntity updateTodo(@PathVariable long id, @RequestBody TodoEntity todoEntity) {
        TodoEntity todoData = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo id not found : " + id));
        todoData.setText(todoEntity.getText());
        todoData.setDone(todoEntity.isDone());
        return todoRepository.save(todoData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TodoEntity> deleteTodo(@PathVariable long id) {
        TodoEntity todoData = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo id not found : " + id));
        todoRepository.delete(todoData);
        return ResponseEntity.ok(todoData);
    }
}