package com.todoapp.datasource

import com.todoapp.model.Todo

interface TodoDataSource {
    fun retrieveTodos(): Collection<Todo>
}