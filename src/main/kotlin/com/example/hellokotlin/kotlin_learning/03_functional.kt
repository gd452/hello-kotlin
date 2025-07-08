package com.example.hellokotlin.kotlin_learning

fun main() {
    println("=== Kotlin 함수형 프로그래밍 ===\n")
    
    lambdaExample()
    higherOrderFunctionExample()
    collectionOperationsExample()
    sequenceExample()
    functionalCompositionExample()
}

fun lambdaExample() {
    println("1. 람다 표현식")
    
    val sum = { a: Int, b: Int -> a + b }
    println("람다로 더하기: 5 + 3 = ${sum(5, 3)}")
    
    val greet: (String) -> String = { name -> "안녕하세요, $name님!" }
    println(greet("Kotlin"))
    
    val isEven = { n: Int -> n % 2 == 0 }
    println("4는 짝수인가? ${isEven(4)}")
    println("7은 짝수인가? ${isEven(7)}")
    
    val numbers = listOf(1, 2, 3, 4, 5)
    val doubled = numbers.map { it * 2 }
    println("원본: $numbers")
    println("2배: $doubled")
    
    val longGreeting = { name: String, time: String ->
        when (time) {
            "morning" -> "좋은 아침입니다, $name님!"
            "evening" -> "좋은 저녁입니다, $name님!"
            else -> "안녕하세요, $name님!"
        }
    }
    println(longGreeting("개발자", "morning"))
    
    println()
}

fun higherOrderFunctionExample() {
    println("2. 고차 함수")
    
    fun calculate(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
        return operation(a, b)
    }
    
    val add = { x: Int, y: Int -> x + y }
    val multiply = { x: Int, y: Int -> x * y }
    
    println("10 + 5 = ${calculate(10, 5, add)}")
    println("10 * 5 = ${calculate(10, 5, multiply)}")
    println("10 - 5 = ${calculate(10, 5) { x, y -> x - y }}")
    
    fun createMultiplier(factor: Int): (Int) -> Int {
        return { number -> number * factor }
    }
    
    val triple = createMultiplier(3)
    val quadruple = createMultiplier(4)
    
    println("3배 함수: 7 * 3 = ${triple(7)}")
    println("4배 함수: 7 * 4 = ${quadruple(7)}")
    
    fun String.processText(transform: (String) -> String): String {
        return transform(this)
    }
    
    val result = "hello kotlin".processText { it.uppercase() }
    println("대문자 변환: $result")
    
    val reversed = "hello kotlin".processText { it.reversed() }
    println("역순 변환: $reversed")
    
    println()
}

fun collectionOperationsExample() {
    println("3. 컬렉션 함수형 연산")
    
    val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println("원본 리스트: $numbers")
    
    val evenNumbers = numbers.filter { it % 2 == 0 }
    println("짝수만: $evenNumbers")
    
    val squared = numbers.map { it * it }
    println("제곱: $squared")
    
    val sum = numbers.reduce { acc, n -> acc + n }
    println("합계 (reduce): $sum")
    
    val sumWithInitial = numbers.fold(100) { acc, n -> acc + n }
    println("초기값 100에서 시작한 합계 (fold): $sumWithInitial")
    
    data class Person(val name: String, val age: Int, val city: String)
    
    val people = listOf(
        Person("김철수", 25, "서울"),
        Person("이영희", 30, "부산"),
        Person("박민수", 22, "서울"),
        Person("정지은", 28, "대구"),
        Person("최동욱", 35, "서울")
    )
    
    val seoulPeople = people.filter { it.city == "서울" }
    println("\n서울 거주자: $seoulPeople")
    
    val names = people.map { it.name }
    println("이름 목록: $names")
    
    val averageAge = people.map { it.age }.average()
    println("평균 나이: $averageAge")
    
    val grouped = people.groupBy { it.city }
    println("\n도시별 그룹:")
    grouped.forEach { (city, residents) ->
        println("$city: ${residents.map { it.name }}")
    }
    
    val sorted = people.sortedBy { it.age }
    println("\n나이순 정렬:")
    sorted.forEach { println("${it.name} (${it.age}세)") }
    
    println()
}

fun sequenceExample() {
    println("4. 시퀀스와 지연 평가")
    
    val numbers = (1..1000000).toList()
    
    val eagerResult = numbers
        .filter { it % 2 == 0 }
        .map { it * it }
        .take(5)
    println("즉시 평가 결과: $eagerResult")
    
    val lazyResult = numbers.asSequence()
        .filter { it % 2 == 0 }
        .map { it * it }
        .take(5)
        .toList()
    println("지연 평가 결과: $lazyResult")
    
    val sequence = generateSequence(1) { it + 1 }
        .filter { it % 3 == 0 }
        .map { it * 2 }
        .take(10)
        .toList()
    println("무한 시퀀스에서 3의 배수를 2배로: $sequence")
    
    fun fibonacci(): Sequence<Int> = sequence {
        var a = 0
        var b = 1
        yield(a)
        yield(b)
        while (true) {
            val next = a + b
            yield(next)
            a = b
            b = next
        }
    }
    
    val fib = fibonacci().take(10).toList()
    println("피보나치 수열 (처음 10개): $fib")
    
    println()
}

fun functionalCompositionExample() {
    println("5. 함수 합성과 파이프라인")
    
    val trim = { s: String -> s.trim() }
    val uppercase = { s: String -> s.uppercase() }
    val addPrefix = { s: String -> "PREFIX: $s" }
    
    fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C = { x -> f(g(x)) }
    
    val processText = compose(compose(addPrefix, uppercase), trim)
    println(processText("  hello world  "))
    
    infix fun <A, B> ((A) -> B).then(next: (B) -> B): (A) -> B = { x -> next(this(x)) }
    
    val pipeline = trim then uppercase then addPrefix
    println(pipeline("  kotlin functional  "))
    
    data class Order(val id: Int, val amount: Double, val status: String)
    
    val orders = listOf(
        Order(1, 100.0, "completed"),
        Order(2, 250.0, "pending"),
        Order(3, 150.0, "completed"),
        Order(4, 300.0, "cancelled"),
        Order(5, 200.0, "completed")
    )
    
    val totalCompleted = orders
        .filter { it.status == "completed" }
        .map { it.amount }
        .reduce { acc, amount -> acc + amount }
    
    println("\n완료된 주문 총액: $totalCompleted")
    
    val result = orders
        .asSequence()
        .filter { it.status == "completed" }
        .map { it.amount * 1.1 }
        .filter { it > 150 }
        .toList()
    
    println("10% 할증 후 150 초과 주문: $result")
    
    val (completed, notCompleted) = orders.partition { it.status == "completed" }
    println("\n완료된 주문: ${completed.size}개")
    println("미완료 주문: ${notCompleted.size}개")
    
    println()
}