package com.todoapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TodoAppApplication

fun main(args: Array<String>) {
    runApplication<TodoAppApplication>(*args)
}
