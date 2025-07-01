package com.example.hellokotlin.repository

import com.example.hellokotlin.entity.Todo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TodoRepository : JpaRepository<Todo, Long> {
    fun findByCompletedOrderByCreatedAtDesc(completed: Boolean): List<Todo>
    fun findAllByOrderByCreatedAtDesc(): List<Todo>
}