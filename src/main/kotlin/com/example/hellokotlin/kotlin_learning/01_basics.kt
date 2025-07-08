package com.example.hellokotlin.kotlin_learning

fun main() {
    println("=== Kotlin 기본 문법 학습 ===\n")
    
    variablesExample()
    functionsExample()
    conditionalExample()
    loopsExample()
    nullSafetyExample()
}

fun variablesExample() {
    println("1. 변수 선언")
    
    val immutableName = "GD"
    var mutableAge = 25
    
    println("val (불변): $immutableName")
    println("var (가변): $mutableAge")
    
    mutableAge = 26
    println("나이 변경 후: $mutableAge")
    
    val inferredType = "타입 추론"
    val explicitType: String = "명시적 타입"
    println("타입 추론: $inferredType")
    println("명시적 타입: $explicitType")
    
    println()
}

fun functionsExample() {
    println("2. 함수 선언")
    
    fun greet(name: String): String {
        return "안녕하세요, $name님!"
    }
    
    fun greetShort(name: String) = "반갑습니다, $name님!"
    
    fun printSum(a: Int, b: Int) {
        println("$a + $b = ${a + b}")
    }
    
    fun multiply(a: Int, b: Int = 2) = a * b
    
    println(greet("Kotlin"))
    println(greetShort("개발자"))
    printSum(10, 20)
    println("기본값 함수: 5 * 2 = ${multiply(5)}")
    println("인자 전달: 5 * 3 = ${multiply(5, 3)}")
    
    println()
}

fun conditionalExample() {
    println("3. 조건문")
    
    val score = 85
    
    if (score >= 90) {
        println("등급: A")
    } else if (score >= 80) {
        println("등급: B")
    } else {
        println("등급: C")
    }
    
    val grade = if (score >= 90) "A" else if (score >= 80) "B" else "C"
    println("표현식으로 사용: 등급 = $grade")
    
    val day = 3
    val dayName = when (day) {
        1 -> "월요일"
        2 -> "화요일"
        3 -> "수요일"
        4 -> "목요일"
        5 -> "금요일"
        6, 7 -> "주말"
        else -> "잘못된 입력"
    }
    println("when 표현식: $day일은 $dayName")
    
    val number = 15
    when {
        number % 2 == 0 -> println("$number는 짝수")
        number % 3 == 0 -> println("$number는 3의 배수")
        else -> println("$number는 홀수")
    }
    
    println()
}

fun loopsExample() {
    println("4. 반복문")
    
    println("for 루프:")
    for (i in 1..5) {
        print("$i ")
    }
    println()
    
    println("역순:")
    for (i in 5 downTo 1) {
        print("$i ")
    }
    println()
    
    println("step 사용:")
    for (i in 1..10 step 2) {
        print("$i ")
    }
    println()
    
    val fruits = listOf("사과", "바나나", "오렌지")
    println("리스트 순회:")
    for (fruit in fruits) {
        println("- $fruit")
    }
    
    println("인덱스와 함께:")
    for ((index, fruit) in fruits.withIndex()) {
        println("$index: $fruit")
    }
    
    println("\nwhile 루프:")
    var count = 0
    while (count < 3) {
        println("카운트: $count")
        count++
    }
    
    println()
}

fun nullSafetyExample() {
    println("5. Null Safety")
    
    var nullableName: String? = "Kotlin"
    var nonNullName: String = "Java"
    
    println("nullable 변수: $nullableName")
    println("non-null 변수: $nonNullName")
    
    nullableName = null
    println("null 할당 후: $nullableName")
    
    var testName: String? = "테스트"
    println("안전한 호출: ${testName?.length}")
    
    testName = null
    println("null일 때 안전한 호출: ${testName?.length}")
    
    val length = testName?.length ?: 0
    println("Elvis 연산자 사용: 길이 = $length")
    
    fun printLength(text: String?) {
        text?.let {
            println("텍스트 길이: ${it.length}")
        } ?: println("텍스트가 null입니다")
    }
    
    printLength("Hello")
    printLength(null)
    
    println()
}