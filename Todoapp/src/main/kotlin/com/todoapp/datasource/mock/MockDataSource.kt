package com.todoapp.datasource.mock

import com.todoapp.datasource.TodoDataSource
import com.todoapp.model.Todo
import org.springframework.stereotype.Repository

@Repository
class MockDataSource : TodoDataSource {
    private val todos = mutableListOf(
        Todo(1, "Todo 1", "Description 1", false),
        Todo(2, "Todo 2", "Description 2", false),
        Todo(3, "Todo 3", "Description 3", false),
        Todo(4, "Todo 4", "Description 4", false),
        Todo(5, "Todo 5", "Description 5", false),
    )

    override fun retrieveTodos(): Collection<Todo> = todos
    override fun getTodoById(id: String): Todo = todos.firstOrNull() { it.id == id.toInt() } ?: throw NoSuchElementException("Todo with id $id not found")
    override fun createTodo(todo: Todo): Todo {
        if(todos.any { it.id == todo.id }) {
            throw IllegalArgumentException("Todo with id ${todo.id} already exists")
        }
        todos.add(todo)
        return todo
    }
    override fun update(todo: Todo): Todo {
        val currentTodo = todos.firstOrNull() { it.id == todo.id } ?: throw NoSuchElementException("Todo with id ${todo.id} not found")
        todos.remove(currentTodo)
        todos.add(todo)
        return todo
    }


}