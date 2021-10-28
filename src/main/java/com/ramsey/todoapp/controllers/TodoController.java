package com.ramsey.todoapp.controllers;

import com.ramsey.todoapp.entities.Todo;
import com.ramsey.todoapp.entities.TodoRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private final TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    //get requests.
    @GetMapping
    public List<Todo> getTodos(){
        return todoRepository.findAll();
    }

    //get requests for individual todo
    @GetMapping("/{todoId}")
    public Optional<Todo>getTodo(@PathVariable("todoId") Long todoId){
        var todo  = todoRepository.findById(todoId);
        return todo;
    }

    @PostMapping
    public Todo newTodo(@RequestBody Todo todo){
        return this.todoRepository.save(todo);
    }

//update method
    @PutMapping("/{todoId}")
    public Optional<Todo> updateTodo(@PathVariable("todoId") Long todoId, @RequestBody Todo updatedTodo){
        return this.todoRepository.findById(todoId)
                .map (oldTodo -> this.todoRepository.save(updatedTodo));
    }

    //delete method
    @DeleteMapping("/{todoId}")
        public void deleteTodo(@PathVariable("todoId")Long todoId){
    this.todoRepository.deleteById(todoId);
    }
}
