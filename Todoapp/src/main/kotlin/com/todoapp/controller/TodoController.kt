package com.todoapp.controller

import com.todoapp.model.Todo
import com.todoapp.service.TodoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/todos")
class TodoController(private val todoService: TodoService) {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @GetMapping
    fun getTodos(): Collection<Todo> = todoService.getTodos()

    @GetMapping("/{id}")
    fun getTodo(@PathVariable id: String): Todo = todoService.getTodoById(id)
}