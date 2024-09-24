package com.todoapp.controller

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
class TodoControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Nested
    @DisplayName("GET /api/todos")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetTodos {
        @Test
        fun `Should return all todos`() {
            mockMvc.get("/api/todos")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType("application/json") }
                    jsonPath("$") { isNotEmpty() }
                    jsonPath("$[0].title") { value("Todo 1") }
                }
        }
    }
    @Nested
    @DisplayName("GET /api/todo/{id}")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetTodo {
        @Test
        fun `Should return a todo by id`() {
            mockMvc.get("/api/todos/1")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType("application/json") }
                    jsonPath("$.title") { value("Todo 1") }
                }
        }
        @Test
        fun `Should return NOT FOUND when a todo by id does not exist`() {
            mockMvc.get("/api/todos/1000")
                .andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }
        }
    }
}