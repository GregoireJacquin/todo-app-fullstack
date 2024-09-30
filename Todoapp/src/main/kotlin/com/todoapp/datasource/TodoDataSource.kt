package com.todoapp.datasource

import com.todoapp.model.Todo

interface TodoDataSource {
    fun retrieveTodos(): Collection<Todo>
    fun getTodoById(id: String): Todo
    fun createTodo(todo: Todo): Todo
}