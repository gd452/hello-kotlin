package com.example.hellokotlin.controller

import com.example.hellokotlin.entity.Todo
import com.example.hellokotlin.repository.TodoRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/todos")
class TodoController(private val todoRepository: TodoRepository) {
    
    @GetMapping
    fun getAllTodos(): List<Todo> = todoRepository.findAllByOrderByCreatedAtDesc()
    
    @GetMapping("/{id}")
    fun getTodoById(@PathVariable id: Long): ResponseEntity<Todo> {
        return todoRepository.findById(id)
            .map { ResponseEntity.ok(it) }
            .orElse(ResponseEntity.notFound().build())
    }
    
    @PostMapping
    fun createTodo(@RequestBody todo: Todo): ResponseEntity<Todo> {
        val savedTodo = todoRepository.save(todo)
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTodo)
    }
    
    @PutMapping("/{id}")
    fun updateTodo(@PathVariable id: Long, @RequestBody todoDetails: Todo): ResponseEntity<Todo> {
        return todoRepository.findById(id)
            .map { existingTodo ->
                val updatedTodo = existingTodo.copy(
                    title = todoDetails.title,
                    description = todoDetails.description,
                    completed = todoDetails.completed,
                    completedAt = if (todoDetails.completed && !existingTodo.completed) 
                        LocalDateTime.now() else existingTodo.completedAt
                )
                ResponseEntity.ok(todoRepository.save(updatedTodo))
            }
            .orElse(ResponseEntity.notFound().build())
    }
    
    @DeleteMapping("/{id}")
    fun deleteTodo(@PathVariable id: Long): ResponseEntity<Void> {
        return if (todoRepository.existsById(id)) {
            todoRepository.deleteById(id)
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}