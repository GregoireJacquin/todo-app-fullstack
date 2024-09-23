package com.todoapp.service

import com.todoapp.datasource.mock.MockDataSource
import com.todoapp.model.Todo
import org.springframework.stereotype.Service

@Service
class TodoService(private val mockDataSource: MockDataSource) {
    fun getTodos(): Collection<Todo> = mockDataSource.retrieveTodos()
}