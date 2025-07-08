package com.example.hellokotlin.kotlin_learning

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlin.system.measureTimeMillis

// Value classes 정의 (함수 밖에서 선언)
@JvmInline
value class Password(val value: String) {
    init {
        require(value.length >= 8) { "비밀번호는 8자 이상이어야 합니다" }
    }

    fun isStrong(): Boolean {
        return value.any { it.isDigit() } &&
               value.any { it.isUpperCase() } &&
               value.any { it.isLowerCase() }
    }
}

@JvmInline
value class Email(val value: String) {
    init {
        require("@" in value) { "올바른 이메일 형식이 아닙니다" }
    }
}

@JvmInline
value class Celsius(val value: Double) {
    fun toFahrenheit() = value * 9/5 + 32
    fun toKelvin() = value + 273.15
}

// 인터페이스 정의 (함수 밖에서 선언)
interface Printer {
    fun print(message: String)
}

class ConsolePrinter : Printer {
    override fun print(message: String) {
        println("콘솔: ${message}")
    }
}

class PrefixPrinter(
    private val prefix: String,
    private val printer: Printer
) : Printer by printer {
    override fun print(message: String) {
        printer.print("${prefix} ${message}")
    }
}

// 위임 속성 클래스들
class LoggingProperty(private var value: String) {
    operator fun getValue(thisRef: Any?, property: kotlin.reflect.KProperty<*>): String {
        println("${property.name} 읽기: '${value}'")
        return value
    }

    operator fun setValue(thisRef: Any?, property: kotlin.reflect.KProperty<*>, newValue: String) {
        println("${property.name} 변경: '${value}' -> '${newValue}'")
        value = newValue
    }
}

class RangeProperty(private var value: Int, private val range: IntRange) {
    operator fun getValue(thisRef: Any?, property: kotlin.reflect.KProperty<*>): Int = value

    operator fun setValue(thisRef: Any?, property: kotlin.reflect.KProperty<*>, newValue: Int) {
        require(newValue in range) { "${property.name}은(는) ${range} 범위 내여야 합니다" }
        value = newValue
    }
}

class DelegationUser {
    var name: String by LoggingProperty("")
    var age: Int by RangeProperty(0, 0..150)
}

// DSL 클래스들
class HTML {
    private val elements = mutableListOf<String>()

    fun head(block: Head.() -> Unit) {
        val head = Head()
        head.block()
        elements.add(head.toString())
    }

    fun body(block: Body.() -> Unit) {
        val body = Body()
        body.block()
        elements.add(body.toString())
    }

    override fun toString() = "<html>\n${elements.joinToString("\n")}\n</html>"
}

class Head {
    private var title = ""

    fun title(text: String) {
        title = text
    }

    override fun toString() = "  <head>\n    <title>${title}</title>\n  </head>"
}

class Body {
    private val content = mutableListOf<String>()

    fun h1(text: String) {
        content.add("    <h1>${text}</h1>")
    }

    fun p(text: String) {
        content.add("    <p>${text}</p>")
    }

    fun ul(block: UL.() -> Unit) {
        val ul = UL()
        ul.block()
        content.add(ul.toString())
    }

    override fun toString() = "  <body>\n${content.joinToString("\n")}\n  </body>"
}

class UL {
    private val items = mutableListOf<String>()

    fun li(text: String) {
        items.add("      <li>${text}</li>")
    }

    override fun toString() = "    <ul>\n${items.joinToString("\n")}\n    </ul>"
}

fun html(block: HTML.() -> Unit): HTML {
    val html = HTML()
    html.block()
    return html
}

fun mainAdvanced() {
    println("=== Kotlin 고급 기능 ===\n")
    
    extensionFunctionExample()
    inlineClassExample()
    delegationExample()
    coroutineExample()
    dslExample()
}

fun extensionFunctionExample() {
    println("1. 확장 함수")
    
    fun String.removeSpaces(): String = this.replace(" ", "")
    
    val text = "Hello Kotlin World"
    println("원본: '${text}'")
    println("공백 제거: '${text.removeSpaces()}'")
    
    fun String.isPalindrome(): Boolean {
        val cleaned = this.lowercase().filter { it.isLetterOrDigit() }
        return cleaned == cleaned.reversed()
    }
    
    println("\n회문 검사:")
    println("'level' -> ${
    "level".isPalindrome()}")
    println("'A man a plan a canal Panama' -> ${"A man a plan a canal Panama".isPalindrome()}")
    println("'hello' -> ${"hello".isPalindrome()}")
    
    fun <T> List<T>.secondOrNull(): T? = if (size >= 2) this[1] else null
    
    val numbers = listOf(10, 20, 30)
    val shortList = listOf(5)
    println("\n두 번째 요소:")
    println("${numbers} -> ${numbers.secondOrNull()}")
    println("${shortList} -> ${shortList.secondOrNull()}")
    
    fun Int.isEven() = this % 2 == 0
    fun Int.isOdd() = !isEven()
    
    println("\n숫자 확장:")
    println("4.isEven() = ${4.isEven()}")
    println("7.isOdd() = ${7.isOdd()}")
    
    fun <T> T.applyIf(condition: Boolean, block: T.() -> T): T {
        return if (condition) block() else this
    }
    
    val result = "hello"
        .applyIf(true) { uppercase() }
        .applyIf(false) { reversed() }
    println("\n조건부 적용: ${result}")
    
    println()
}

fun inlineClassExample() {
    println("2. 값 클래스 (Inline Classes)")
    
    val password = Password("MyPass123")
    println("비밀번호 강도: ${if (password.isStrong()) "강함" else "약함"}")
    
    val email = Email("user@example.com")
    println("이메일: ${email.value}")
    
    val temp = Celsius(25.0)
    println("\n온도 변환:")
    println("섭씨: ${temp.value}°C")
    println("화씨: ${temp.toFahrenheit()}°F")
    println("켈빈: ${temp.toKelvin()}K")
    
    println()
}

fun delegationExample() {
    println("3. 위임 (Delegation)")
    
    val printer = PrefixPrinter("[INFO]", ConsolePrinter())
    printer.print("애플리케이션 시작")
    
    println("\n속성 위임:")
    val user = DelegationUser()
    user.name = "김코틀린"
    println("이름: ${user.name}")
    user.age = 25
    
    val lazyValue: String by lazy {
        println("값 계산 중...")
        "지연 초기화된 값"
    }
    
    println("\n지연 초기화:")
    println("첫 번째 접근: ${lazyValue}")
    println("두 번째 접근: ${lazyValue}")
    
    println()
}

fun coroutineExample() = runBlocking {
    println("4. 코루틴")
    
    suspend fun fetchUser(id: Int): String {
        delay(1000)
        return "User${id}"
    }
    
    suspend fun fetchOrders(userId: String): List<String> {
        delay(800)
        return listOf("Order1", "Order2", "Order3")
    }
    
    println("순차 실행:")
    val time1 = measureTimeMillis {
        val user = fetchUser(1)
        val orders = fetchOrders(user)
        println("사용자: ${user}, 주문: ${orders}")
    }
    println("소요 시간: ${time1}ms")
    
    println("\n병렬 실행:")
    val time2 = measureTimeMillis {
        coroutineScope {
            val userDeferred = async { fetchUser(2) }
            val ordersDeferred = async { fetchOrders("User2") }
            
            val user = userDeferred.await()
            val orders = ordersDeferred.await()
            println("사용자: ${user}, 주문: ${orders}")
        }
    }
    println("소요 시간: ${time2}ms")
    
    println("\n여러 코루틴 실행:")
    val jobs = List(3) { index ->
        launch {
            delay((index + 1) * 200L)
            println("작업 ${index} 완료")
        }
    }
    jobs.forEach { it.join() }
    
    fun simpleFlow() = flow {
        for (i in 1..3) {
            delay(300)
            emit(i)
        }
    }
    
    println("\nFlow 예제:")
    simpleFlow().collect { value ->
        println("수신: ${value}")
    }
    
    val channel = Channel<Int>()
    
    launch {
        for (x in 1..5) {
            delay(200)
            channel.send(x * x)
        }
        channel.close()
    }
    
    println("\n채널 예제:")
    for (y in channel) {
        println("채널에서 수신: ${y}")
    }
    
    println()
}

fun dslExample() {
    println("5. DSL (Domain Specific Language)")
    
    val document = html {
        head {
            title("Kotlin DSL 예제")
        }
        body {
            h1("DSL로 HTML 생성하기")
            p("이것은 Kotlin DSL로 만든 HTML입니다.")
            ul {
                li("간단하고")
                li("타입 안전하며")
                li("읽기 쉽습니다")
            }
        }
    }
    
    println(document)
    
    class Person {
        var name: String = ""
        var age: Int = 0
        var email: String = ""
        
        override fun toString() = "Person(name='${name}', age=${age}, email='${email}')"
    }
    
    fun person(block: Person.() -> Unit): Person {
        val person = Person()
        person.block()
        return person
    }
    
    val user = person {
        name = "홍길동"
        age = 30
        email = "hong@example.com"
    }
    
    println("\nPerson DSL: ${user}")
    
    println()
}