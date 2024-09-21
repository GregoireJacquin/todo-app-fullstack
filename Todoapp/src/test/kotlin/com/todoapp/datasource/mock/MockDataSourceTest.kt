package com.todoapp.datasource.mock

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MockDataSourceTest {

    private val mockDataSource = MockDataSource()

    @Test
    fun `Should provide a collection Todo`() {
        // When
        val todos = mockDataSource.retrieveTodos()
        // Then
        assertThat(todos).isNotEmpty()
        assertThat(todos.size).isGreaterThanOrEqualTo(5)
    }

    @Test
    fun `Should provide some mock data`() {
        // When
        val todos = mockDataSource.retrieveTodos()
        // Then
        assertThat(todos).allMatch { it.title.isNotBlank()}
        assertThat(todos).anyMatch { it.id != 0}
        assertThat(todos).allMatch { it.description.isNotBlank()}
    }
}