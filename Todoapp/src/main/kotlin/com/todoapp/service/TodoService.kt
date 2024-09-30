package com.todoapp.service

import com.todoapp.datasource.mock.MockDataSource
import com.todoapp.model.Todo
import org.springframework.stereotype.Service

@Service
class TodoService(private val mockDataSource: MockDataSource) {
    fun getTodos(): Collection<Todo> = mockDataSource.retrieveTodos()
    fun getTodoById(id: String): Todo = mockDataSource.getTodoById(id)
    fun addTodo(todo: Todo): Todo = mockDataSource.createTodo(todo)
}