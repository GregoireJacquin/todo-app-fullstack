package com.todoapp.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.kotlin.jsonMapper
import com.todoapp.model.Todo
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
class TodoControllerTest @Autowired constructor(val mockMvc: MockMvc, val jsonMapper: ObjectMapper) {


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
    @Nested
    @DisplayName("POST /api/todo")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PostTodo {
        @Test
        fun `Should return a todo by todo`() {
            val todo: Todo = Todo(
                id = 6, title = "Todo 6", description = "Description 6",
                status = false
            );

            val performedPost =  mockMvc.post("/api/todos") {
                contentType = MediaType.APPLICATION_JSON
                content = jsonMapper.writeValueAsString(todo)
            }
            performedPost.andDo { print() }
                .andExpect {
                    status { isCreated() }
                    content { contentType("application/json") }
                    jsonPath("$.id") { value(6) }
                    jsonPath("$.title") { value("Todo 6") }
                    jsonPath("$.description") { value("Description 6") }
                    jsonPath("$.status") { value(false) }
                }
        }
        @Test
        fun `Should return a bad request when todo exist`() {
            val todoInvalid: Todo = Todo(
                id = 5, title = "Todo 5", description = "Description 5",
                status = false
            );

            val performedPost =  mockMvc.post("/api/todos") {
                contentType = MediaType.APPLICATION_JSON
                content = jsonMapper.writeValueAsString(todoInvalid)
            }
            performedPost.andDo { print() }
                .andExpect {
                    status { isBadRequest()}
                }
        }
    }
}