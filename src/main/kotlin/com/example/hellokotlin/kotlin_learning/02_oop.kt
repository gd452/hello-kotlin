package com.example.hellokotlin.kotlin_learning

fun mainOop() {
    println("=== Kotlin 객체지향 프로그래밍 ===\n")
    
    classExample()
    dataClassExample()
    inheritanceExample()
    interfaceExample()
    sealedClassExample()
    objectExample()
}

fun classExample() {
    println("1. 클래스와 생성자")
    
    class Person(val name: String, var age: Int) {
        fun introduce() {
            println("안녕하세요, 저는 ${name}이고 ${age}살입니다.")
        }
        
        fun birthday() {
            age++
            println("생일 축하! 이제 ${age}살이 되었습니다.")
        }
    }
    
    val person = Person("김코틀린", 25)
    person.introduce()
    person.birthday()
    
    class Car {
        var brand: String = ""
        var model: String = ""
        var year: Int = 0
        
        constructor(brand: String, model: String) {
            this.brand = brand
            this.model = model
            this.year = 2024
        }
        
        constructor(brand: String, model: String, year: Int) {
            this.brand = brand
            this.model = model
            this.year = year
        }
        
        fun displayInfo() {
            println("차량: ${year}년식 ${brand} ${model}")
        }
    }
    
    val car1 = Car("현대", "아반떼")
    val car2 = Car("기아", "K5", 2023)
    car1.displayInfo()
    car2.displayInfo()
    
    println()
}

fun dataClassExample() {
    println("2. 데이터 클래스")
    
    data class UserData(val id: Int, val name: String, val email: String)

    val user1 = UserData(1, "홍길동", "hong@example.com")
    val user2 = UserData(2, "김철수", "kim@example.com")
    val user3 = user1.copy(name = "홍길순")
    
    println("user1: ${user1}")
    println("user2: ${user2}")
    println("user3 (복사본): ${user3}")
    
    println("동등성 비교: user1 == user2 = ${user1 == user2}")
    println("동등성 비교: user1 == user3 = ${user1 == user3}")
    
    val (id, name, email) = user1
    println("구조 분해: id=${id}, name=${name}, email=${email}")
    
    data class Product(
        val name: String,
        val price: Int,
        val category: String = "일반"
    )
    
    val products = listOf(
        Product("노트북", 1500000, "전자제품"),
        Product("마우스", 30000, "전자제품"),
        Product("커피", 5000)
    )
    
    println("\n상품 목록:")
    products.forEach { println("- ${it}") }
    
    println()
}

fun inheritanceExample() {
    println("3. 상속과 다형성")
    
    open class Animal(val name: String) {
        open fun makeSound() {
            println("${name}이(가) 소리를 냅니다.")
        }
        
        fun eat() {
            println("${name}이(가) 먹이를 먹습니다.")
        }
    }
    
    class Dog(name: String) : Animal(name) {
        override fun makeSound() {
            println("${name}: 멍멍!")
        }
        
        fun wagTail() {
            println("${name}이(가) 꼬리를 흔듭니다.")
        }
    }
    
    class Cat(name: String) : Animal(name) {
        override fun makeSound() {
            println("${name}: 야옹~")
        }
        
        fun scratch() {
            println("${name}이(가) 발톱을 세웁니다.")
        }
    }
    
    val dog = Dog("바둑이")
    val cat = Cat("나비")
    
    dog.makeSound()
    dog.eat()
    dog.wagTail()
    
    cat.makeSound()
    cat.eat()
    cat.scratch()
    
    val animals: List<Animal> = listOf(dog, cat)
    println("\n다형성 예제:")
    animals.forEach { animal ->
        animal.makeSound()
    }
    
    println()
}

// 인터페이스 정의 (함수 밖에서 선언)
interface Drawable {
    fun draw()
    fun resize(scale: Double) {
        println("크기를 ${scale}배로 조정합니다.")
    }
}

interface Clickable {
    fun click()
    fun doubleClick() {
        click()
        click()
    }
}

class Button(val text: String) : Drawable, Clickable {
    override fun draw() {
        println("[${text}] 버튼을 그립니다.")
    }

    override fun click() {
        println("[${text}] 버튼을 클릭했습니다.")
    }
}

class Image(val fileName: String) : Drawable {
    override fun draw() {
        println("${fileName} 이미지를 화면에 표시합니다.")
    }

    override fun resize(scale: Double) {
        super.resize(scale)
        println("이미지 품질을 유지하며 크기 조정")
    }
}

fun interfaceExample() {
    println("4. 인터페이스")

    val button = Button("확인")
    button.draw()
    button.click()
    button.doubleClick()
    button.resize(1.5)
    
    val image = Image("logo.png")
    image.draw()
    image.resize(2.0)
    
    println()
}

// Sealed 클래스들을 함수 밖에서 선언
sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val message: String) : Result<Nothing>()
    object Loading : Result<Nothing>()
}

sealed class PaymentMethod {
    data class CreditCard(val number: String, val cvv: String) : PaymentMethod()
    data class BankTransfer(val accountNumber: String) : PaymentMethod()
    object Cash : PaymentMethod()
}

fun sealedClassExample() {
    println("5. Sealed 클래스")

    fun fetchData(id: Int): Result<String> {
        return when (id) {
            1 -> Result.Success("사용자 데이터")
            2 -> Result.Error("사용자를 찾을 수 없습니다")
            else -> Result.Loading
        }
    }
    
    fun handleResult(result: Result<String>) {
        when (result) {
            is Result.Success -> println("성공: ${result.data}")
            is Result.Error -> println("에러: ${result.message}")
            Result.Loading -> println("로딩 중...")
        }
    }
    
    handleResult(fetchData(1))
    handleResult(fetchData(2))
    handleResult(fetchData(3))
    
    fun processPayment(method: PaymentMethod) {
        when (method) {
            is PaymentMethod.CreditCard -> println("신용카드 결제: **** ${method.number.takeLast(4)}")
            is PaymentMethod.BankTransfer -> println("계좌이체: ${method.accountNumber}")
            PaymentMethod.Cash -> println("현금 결제")
        }
    }
    
    processPayment(PaymentMethod.CreditCard("1234-5678-9012-3456", "123"))
    processPayment(PaymentMethod.BankTransfer("123-456-789"))
    processPayment(PaymentMethod.Cash)
    
    println()
}

// 싱글톤 객체를 함수 밖에서 선언
object DatabaseConfig {
    var host = "localhost"
    var port = 5432
    var database = "mydb"

    fun getConnectionString(): String {
        return "jdbc:postgresql://${host}:${port}/${database}"
    }
}

// Companion object가 있는 클래스를 함수 밖에서 선언
class User private constructor(val id: Int, val name: String) {
    companion object {
        private var userCount = 0

        fun createUser(name: String): User {
            userCount++
            return User(userCount, name)
        }

        fun getTotalUsers() = userCount
    }

    override fun toString() = "User(id=${id}, name='${name}')"
}

fun objectExample() {
    println("6. 싱글톤 객체와 동반 객체")

    println("DB 연결 문자열: ${DatabaseConfig.getConnectionString()}")
    DatabaseConfig.host = "192.168.1.100"
    println("호스트 변경 후: ${DatabaseConfig.getConnectionString()}")

    val user1 = User.createUser("Alice")
    val user2 = User.createUser("Bob")
    val user3 = User.createUser("Charlie")
    
    println("\n생성된 사용자:")
    println(user1)
    println(user2)
    println(user3)
    println("총 사용자 수: ${User.getTotalUsers()}")
    
    println()
}