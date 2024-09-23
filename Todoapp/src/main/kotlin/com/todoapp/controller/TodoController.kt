package com.todoapp.controller

import com.todoapp.model.Todo
import com.todoapp.service.TodoService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/todos")
class TodoController(private val todoService: TodoService) {
    @GetMapping
    fun getTodos(): Collection<Todo> = todoService.getTodos()
}