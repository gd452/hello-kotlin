package com.example.hellokotlin

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class KotlinLearningRunner {
    @Bean
    fun commandLineRunner(): CommandLineRunner {
        return CommandLineRunner { args ->
            if (args.isNotEmpty()) {
                when (args[0]) {
                    "basics" -> com.example.hellokotlin.kotlin_learning.mainBasics()
                    "oop" -> com.example.hellokotlin.kotlin_learning.mainOop()
                    "functional" -> com.example.hellokotlin.kotlin_learning.mainFunctional()
                    "advanced" -> com.example.hellokotlin.kotlin_learning.mainAdvanced()
                    "practice" -> com.example.hellokotlin.kotlin_learning.mainPractice()
                    else -> println("Unknown command: ${args[0]}")
                }
            } else {
                println("Please specify which module to run: basics, oop, functional, advanced, or practice")
            }
        }
    }
}

fun main(args: Array<String>) {
    runApplication<KotlinLearningRunner>(*args)
}