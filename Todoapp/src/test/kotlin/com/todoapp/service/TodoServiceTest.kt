package com.todoapp.service

import com.todoapp.datasource.mock.MockDataSource
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class TodoServiceTest {
    private val dataSource: MockDataSource = mockk(relaxed = true)
    private val todoService = TodoService(dataSource);
    @Test
    fun `should call its data source at retrieve todos` (){
        todoService.getTodos()
        verify(exactly = 1){ dataSource.retrieveTodos() }
    }
}