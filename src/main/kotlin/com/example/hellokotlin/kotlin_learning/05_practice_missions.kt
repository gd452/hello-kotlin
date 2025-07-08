package com.example.hellokotlin.kotlin_learning

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

// Calculator operation types
sealed class Operation {
    data class Add(val a: Double, val b: Double) : Operation()
    data class Subtract(val a: Double, val b: Double) : Operation()
    data class Multiply(val a: Double, val b: Double) : Operation()
    data class Divide(val a: Double, val b: Double) : Operation()
    data class Power(val base: Double, val exponent: Double) : Operation()
    data class Sqrt(val number: Double) : Operation()
}

// Expense category types
enum class Category {
    FOOD, TRANSPORT, SHOPPING, ENTERTAINMENT, BILLS, OTHER
}

fun mainPractice() {
    println("=== Kotlin 실습 미션 ===\n")
    
    println("1. 콘솔 Todo 앱")
    todoAppMission()
    
    println("\n2. 계산기 앱")
    calculatorMission()
    
    println("\n3. 가계부 앱")
    expenseTrackerMission()
}

fun todoAppMission() {
    class Todo(
        val id: Int,
        val title: String,
        var completed: Boolean = false,
        val createdAt: LocalDateTime = LocalDateTime.now()
    ) {
        fun toggle() {
            completed = !completed
        }
        
        override fun toString(): String {
            val status = if (completed) "✓" else "○"
            val time = createdAt.format(DateTimeFormatter.ofPattern("MM-dd HH:mm"))
            return "[${status}] #${id} ${title} (${time})"
        }
    }
    
    class TodoManager {
        private val todos = mutableListOf<Todo>()
        private var nextId = 1
        
        fun addTodo(title: String): Todo {
            val todo = Todo(nextId++, title)
            todos.add(todo)
            return todo
        }
        
        fun getTodos(showCompleted: Boolean = true): List<Todo> {
            return if (showCompleted) todos else todos.filter { !it.completed }
        }
        
        fun findById(id: Int): Todo? = todos.find { it.id == id }
        
        fun toggleTodo(id: Int): Boolean {
            val todo = findById(id)
            todo?.toggle()
            return todo != null
        }
        
        fun deleteTodo(id: Int): Boolean {
            return todos.removeIf { it.id == id }
        }
        
        fun getStats(): String {
            val total = todos.size
            val completed = todos.count { it.completed }
            val pending = total - completed
            return "전체: ${total} | 완료: ${completed} | 대기: ${pending}"
        }
    }
    
    val manager = TodoManager()
    
    manager.addTodo("Kotlin 기초 문법 학습")
    manager.addTodo("SpringBoot 프로젝트 생성")
    manager.addTodo("REST API 구현")
    manager.addTodo("데이터베이스 연동")
    
    println("초기 할 일 목록:")
    manager.getTodos().forEach { println(it) }
    
    manager.toggleTodo(1)
    manager.toggleTodo(2)
    
    println("\n일부 완료 후:")
    manager.getTodos().forEach { println(it) }
    
    println("\n미완료 항목만:")
    manager.getTodos(showCompleted = false).forEach { println(it) }
    
    println("\n통계: ${manager.getStats()}")
}

fun calculatorMission() {
    
    class Calculator {
        private val history = mutableListOf<Pair<String, Double>>()
        
        fun calculate(operation: Operation): Double {
            val result = when (operation) {
                is Operation.Add -> operation.a + operation.b
                is Operation.Subtract -> operation.a - operation.b
                is Operation.Multiply -> operation.a * operation.b
                is Operation.Divide -> {
                    require(operation.b != 0.0) { "0으로 나눌 수 없습니다" }
                    operation.a / operation.b
                }
                is Operation.Power -> Math.pow(operation.base, operation.exponent)
                is Operation.Sqrt -> {
                    require(operation.number >= 0) { "음수의 제곱근은 계산할 수 없습니다" }
                    Math.sqrt(operation.number)
                }
            }
            
            val expression = when (operation) {
                is Operation.Add -> "${operation.a} + ${operation.b}"
                is Operation.Subtract -> "${operation.a} - ${operation.b}"
                is Operation.Multiply -> "${operation.a} × ${operation.b}"
                is Operation.Divide -> "${operation.a} ÷ ${operation.b}"
                is Operation.Power -> "${operation.base}^${operation.exponent}"
                is Operation.Sqrt -> "√${operation.number}"
            }
            
            history.add(expression to result)
            return result
        }
        
        fun getHistory() = history.toList()
        
        fun clearHistory() = history.clear()
    }
    
    val calc = Calculator()
    
    val operations = listOf(
        Operation.Add(10.0, 5.0),
        Operation.Subtract(20.0, 8.0),
        Operation.Multiply(4.0, 7.0),
        Operation.Divide(100.0, 4.0),
        Operation.Power(2.0, 8.0),
        Operation.Sqrt(144.0)
    )
    
    operations.forEach { op ->
        try {
            val result = calc.calculate(op)
            println("계산 완료: ${calc.getHistory().last().first} = ${result}")
        } catch (e: Exception) {
            println("오류: ${e.message}")
        }
    }
    
    println("\n계산 기록:")
    calc.getHistory().forEach { (expr, result) ->
        println("${expr} = ${result}")
    }
}

fun expenseTrackerMission() {
    
    data class Expense(
        val id: Int,
        val description: String,
        val amount: Int,
        val category: Category,
        val date: LocalDateTime = LocalDateTime.now()
    )
    
    class ExpenseTracker {
        private val expenses = mutableListOf<Expense>()
        private var nextId = 1
        
        fun addExpense(description: String, amount: Int, category: Category): Expense {
            val expense = Expense(nextId++, description, amount, category)
            expenses.add(expense)
            return expense
        }
        
        fun getExpenses() = expenses.toList()
        
        fun getExpensesByCategory(category: Category) = 
            expenses.filter { it.category == category }
        
        fun getTotalAmount() = expenses.sumOf { it.amount }
        
        fun getTotalByCategory(): Map<Category, Int> {
            return expenses.groupBy { it.category }
                .mapValues { (_, expenses) -> expenses.sumOf { it.amount } }
        }
        
        fun getMonthlyReport(year: Int, month: Int): String {
            val monthlyExpenses = expenses.filter {
                it.date.year == year && it.date.monthValue == month
            }
            
            val total = monthlyExpenses.sumOf { it.amount }
            val byCategory = monthlyExpenses
                .groupBy { it.category }
                .mapValues { (_, exp) -> exp.sumOf { it.amount } }
            
            return buildString {
                appendLine("=== ${year}년 ${month}월 지출 보고서 ===")
                appendLine("총 지출: ${total}원")
                appendLine("\n카테고리별 지출:")
                byCategory.forEach { (category, amount) ->
                    val percentage = if (total > 0) (amount * 100.0 / total) else 0.0
                    appendLine("- ${category.name}: ${amount}원 (${percentage.format(1)}%)")
                }
            }
        }
        
        private fun Double.format(digits: Int) = "%.${digits}f".format(this)
    }
    
    val tracker = ExpenseTracker()
    
    val sampleExpenses = listOf(
        Triple("점심 식사", 8500, Category.FOOD),
        Triple("지하철 교통카드", 1250, Category.TRANSPORT),
        Triple("커피", 4500, Category.FOOD),
        Triple("영화 관람", 12000, Category.ENTERTAINMENT),
        Triple("마트 장보기", 35000, Category.SHOPPING),
        Triple("통신비", 55000, Category.BILLS),
        Triple("저녁 외식", 25000, Category.FOOD),
        Triple("택시비", 8000, Category.TRANSPORT)
    )
    
    sampleExpenses.forEach { (desc, amount, category) ->
        tracker.addExpense(desc, amount, category)
    }
    
    println("전체 지출 내역:")
    tracker.getExpenses().forEach { expense ->
        val date = expense.date.format(DateTimeFormatter.ofPattern("MM-dd"))
        println("${date} | ${expense.description} | ${expense.amount}원 | ${expense.category}")
    }
    
    println("\n카테고리별 총액:")
    tracker.getTotalByCategory().forEach { (category, total) ->
        println("${category.name}: ${total}원")
    }
    
    println("\n총 지출액: ${tracker.getTotalAmount()}원")
    
    val now = LocalDateTime.now()
    println("\n${tracker.getMonthlyReport(now.year, now.monthValue)}")
}