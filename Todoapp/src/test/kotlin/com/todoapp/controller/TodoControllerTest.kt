package com.todoapp.controller

import org.junit.jupiter.api.Test
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