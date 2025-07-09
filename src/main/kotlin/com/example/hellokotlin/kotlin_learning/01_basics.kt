package com.example.hellokotlin.kotlin_learning

// Kotlin 기본 문법 학습
// 실행: 이 파일을 IntelliJ에서 직접 실행하거나, 터미널에서 kotlinc 01_basics.kt -include-runtime -d basics.jar && java -jar basics.jar

// SpringBoot Runner를 위한 함수
fun mainBasics() {
    main()
}

// 독립 실행을 위한 main 함수
fun main() {
    println("=== Kotlin 기본 문법 학습 ===")
    println("Java, C, Python과 비교하며 배우는 Kotlin\n")

    variablesExample()
    functionsExample()
    conditionalExample()
    loopsExample()
    nullSafetyExample()
}

fun variablesExample() {
    println("1. 변수 선언")
    println("―――――――――――――――――――――――――")

    // Kotlin의 변수 선언은 Python처럼 타입 추론이 가능하지만,
    // Java/C처럼 타입을 명시할 수도 있습니다.

    // val: 불변 변수 (Java의 final, C의 const와 유사)
    val immutableName = "GD"  // 타입 추론: String
    // immutableName = "NewName"  // 컴파일 에러! val은 재할당 불가

    // var: 가변 변수 (일반적인 변수)
    var mutableAge = 25  // 타입 추론: Int

    println("📌 val vs var")
    println("  val (불변): $immutableName")  // Python의 f-string처럼 문자열 템플릿 사용
    println("  var (가변): $mutableAge")

    mutableAge = 26  // var는 재할당 가능
    println("  나이 변경 후: $mutableAge")

    // 타입 추론 vs 명시적 타입 선언
    val inferredType = "타입 추론"           // 타입 추론
    val explicitType: String = "명시적 타입"  // Java처럼 타입 명시

    println("\n📌 타입 시스템")
    println("  타입 추론: $inferredType (Python처럼 자동)")
    println("  명시적 타입: $explicitType (Java/C처럼 명시)")

    // Kotlin의 기본 타입들 (Java의 primitive 타입과 달리 모두 객체)
    val intNum: Int = 42        // Java의 int (32비트)
    val longNum: Long = 42L     // Java의 long (64비트)
    val floatNum: Float = 3.14f // Java의 float
    val doubleNum: Double = 3.14 // Java의 double
    val boolValue: Boolean = true // Java의 boolean
    val charValue: Char = 'A'    // Java의 char

    println("\n📌 기본 타입들 (모두 객체)")
    println("  Int: $intNum, Long: $longNum")
    println("  Float: $floatNum, Double: $doubleNum")
    println("  Boolean: $boolValue, Char: $charValue")

    // 💡 Python과 달리 Kotlin은 정적 타입 언어입니다.
    // 💡 C와 달리 메모리 관리는 자동으로 처리됩니다.
    // 💡 Java와 달리 primitive 타입이 없고 모든 것이 객체입니다.

    println()
}

fun functionsExample() {
    println("2. 함수 선언")
    println("―――――――――――――――――――――――――")

    // 기본 함수 선언 (Java와 유사하지만 더 간결)
    fun greet(name: String): String {
        return "안녕하세요, ${name}님!"
    }

    // 단일 표현식 함수 (Python의 lambda처럼 간결)
    fun greetShort(name: String) = "반갑습니다, ${name}님!"

    // 반환값이 없는 함수 (Java의 void, C의 void와 동일한 개념)
    fun printSum(a: Int, b: Int) {
        println("$a + $b = ${a + b}")
    }

    // 기본 매개변수 (Python처럼 기본값 지원)
    fun multiply(a: Int, b: Int = 2) = a * b

    println("📌 다양한 함수 형태")
    println("  일반 함수: ${greet("Kotlin")}")
    println("  단일 표현식: ${greetShort("개발자")}")
    print("  Unit 반환: ")
    printSum(10, 20)
    println("  기본값 함수: 5 * 2 = ${multiply(5)}")
    println("  인자 전달: 5 * 3 = ${multiply(5, 3)}")

    // 명명된 인자 (Python의 keyword arguments와 유사)
    fun createUser(name: String, age: Int, city: String = "Seoul") =
        "User(name=$name, age=$age, city=$city)"

    println("\n📌 명명된 인자 (Named Arguments)")
    println("  ${createUser("김철수", 30)}")
    println("  ${createUser(age = 25, name = "이영희", city = "Busan")}")  // 순서 바꿔도 OK

    // 가변 인자 (Java의 varargs, Python의 *args와 유사)
    fun sum(vararg numbers: Int): Int {
        return numbers.sum()
    }

    println("\n📌 가변 인자 (Varargs)")
    println("  sum(1, 2, 3) = ${sum(1, 2, 3)}")
    println("  sum(1, 2, 3, 4, 5) = ${sum(1, 2, 3, 4, 5)}")

    // 💡 Java와 달리 함수를 최상위 레벨에 선언 가능 (클래스 불필요)
    // 💡 Python처럼 기본 매개변수와 명명된 인자 지원
    // 💡 C와 달리 함수 포인터 대신 함수 타입 사용 (다음 장에서 학습)

    println()
}

fun conditionalExample() {
    println("3. 조건문")
    println("―――――――――――――――――――――――――")

    val score = 85

    // if-else (Java/C와 동일하지만 표현식으로도 사용 가능)
    println("📌 if-else 문")
    if (score >= 90) {
        println("  등급: A")
    } else if (score >= 80) {
        println("  등급: B")
    } else {
        println("  등급: C")
    }

    // if를 표현식으로 사용 (Python의 삼항 연산자와 유사)
    val grade = if (score >= 90) "A" else if (score >= 80) "B" else "C"
    println("  표현식으로 사용: 등급 = $grade")

    // when (Java의 switch, Python의 match와 유사하지만 더 강력)
    println("\n📌 when 표현식 (강력한 switch)")
    val day = 3
    val dayName = when (day) {
        1 -> "월요일"
        2 -> "화요일"
        3 -> "수요일"
        4 -> "목요일"
        5 -> "금요일"
        6, 7 -> "주말"  // 여러 값을 한 번에
        else -> "잘못된 입력"
    }
    println("  when 기본: ${day}일은 $dayName")

    // when with conditions (if-elif-else를 더 깔끔하게)
    val number = 15
    when {
        number % 2 == 0 -> println("  ${number}는 짝수")
        number % 3 == 0 -> println("  ${number}는 3의 배수")
        number % 5 == 0 -> println("  ${number}는 5의 배수")
        else -> println("  ${number}는 특별한 성질이 없음")
    }

    // when with ranges
    val testScore = 75
    val letterGrade = when (testScore) {
        in 90..100 -> "A"
        in 80..89 -> "B"
        in 70..79 -> "C"
        in 60..69 -> "D"
        else -> "F"
    }
    println("  범위 체크: ${testScore}점은 ${letterGrade} 등급")

    // when with type checking (다음 장에서 자세히)
    val x: Any = "Hello"
    when (x) {
        is String -> println("  타입 체크: 문자열 길이는 ${x.length}")
        is Int -> println("  타입 체크: 정수값은 $x")
        else -> println("  타입 체크: 알 수 없는 타입")
    }

    // 💡 Java의 switch와 달리 break가 필요 없음 (fall-through 없음)
    // 💡 Python의 match보다 더 간결하고 표현력이 뛰어남
    // 💡 모든 조건문이 표현식으로 사용 가능 (값을 반환)

    println()
}

fun loopsExample() {
    println("4. 반복문")
    println("―――――――――――――――――――――――――")

    // for 루프 - 범위 (Python의 range와 유사)
    println("📌 for 루프 - 범위")
    print("  1..5: ")
    for (i in 1..5) {  // 1부터 5까지 (5 포함)
        print("$i ")
    }
    println()

    print("  1 until 5: ")
    for (i in 1 until 5) {  // 1부터 4까지 (5 제외)
        print("$i ")
    }
    println()

    print("  5 downTo 1: ")
    for (i in 5 downTo 1) {  // 5부터 1까지 역순
        print("$i ")
    }
    println()

    print("  1..10 step 2: ")
    for (i in 1..10 step 2) {  // 1부터 10까지 2씩 증가
        print("$i ")
    }
    println()

    // 컬렉션 순회 (Python의 for-in과 동일)
    val fruits = listOf("사과", "바나나", "오렌지")
    println("\n📌 컬렉션 순회")
    println("  기본 순회:")
    for (fruit in fruits) {
        println("    - $fruit")
    }

    // 인덱스와 함께 (Python의 enumerate와 유사)
    println("  인덱스와 함께:")
    for ((index, fruit) in fruits.withIndex()) {
        println("    $index: $fruit")
    }

    // while 루프 (Java/C와 동일)
    println("\n📌 while 루프")
    var count = 0
    while (count < 3) {
        println("  카운트: $count")
        count++
    }

    // do-while (Java/C와 동일, Python에는 없음)
    println("\n📌 do-while 루프")
    var num = 0
    do {
        println("  실행: $num")
        num++
    } while (num < 3)

    // break와 continue (모든 언어와 동일)
    println("\n📌 break와 continue")
    print("  break 예제: ")
    for (i in 1..10) {
        if (i > 5) break
        print("$i ")
    }
    println()

    print("  continue 예제: ")
    for (i in 1..10) {
        if (i % 2 == 0) continue  // 짝수 건너뛰기
        print("$i ")
    }
    println()

    // 💡 Python의 range()보다 더 직관적인 범위 표현
    // 💡 Java의 향상된 for문보다 더 다양한 순회 방법
    // 💡 C의 for(;;)보다 안전하고 간결한 문법

    println()
}

fun nullSafetyExample() {
    println("5. Null Safety (Kotlin의 킬러 기능)")
    println("―――――――――――――――――――――――――――――――――――――――――")

    // Kotlin의 타입 시스템은 null 참조를 명시적으로 다룹니다
    // 이는 "Billion Dollar Mistake"라 불리는 NullPointerException을 방지합니다

    // nullable vs non-null 타입
    var nullableName: String? = "Kotlin"  // ?가 있으면 null 가능
    var nonNullName: String = "Java"      // ?가 없으면 null 불가능

    println("📌 Nullable vs Non-null 타입")
    println("  nullable 변수 (String?): $nullableName")
    println("  non-null 변수 (String): $nonNullName")

    nullableName = null  // OK
    // nonNullName = null  // 컴파일 에러!
    println("  null 할당 후: $nullableName")

    // 안전한 호출 연산자 (?.)
    println("\n📌 안전한 호출 연산자 (?.)")
    var testName: String? = "테스트"
    println("  값이 있을 때: ${testName?.length}")  // 정상 실행

    testName = null
    println("  null일 때: ${testName?.length}")  // null 반환 (에러 없음)

    // Elvis 연산자 (?:) - null일 때 기본값 제공
    println("\n📌 Elvis 연산자 (?:)")
    val length = testName?.length ?: 0  // Python의 or와 유사하지만 더 안전
    println("  null일 때 기본값: 길이 = $length")

    val name = testName ?: "기본 이름"
    println("  null일 때 기본 문자열: $name")

    // let과 함께 사용 (null이 아닐 때만 실행)
    println("\n📌 let과 함께 사용")
    fun printLength(text: String?) {
        text?.let {
            println("  텍스트 '${it}'의 길이: ${it.length}")
        } ?: println("  텍스트가 null입니다")
    }

    printLength("Hello")
    printLength(null)

    // null 아님 단언 (!!) - 주의해서 사용
    println("\n📌 null 아님 단언 (!!) - 위험!")
    var assertName: String? = "무조건 있다!"
    println("  !! 사용: ${assertName!!.length}")  // null이면 NPE 발생
    // assertName = null
    // println(assertName!!.length)  // NullPointerException!

    // 스마트 캐스트
    println("\n📌 스마트 캐스트")
    var smartName: String? = "스마트"
    if (smartName != null) {
        // 이 블록 안에서는 smartName이 자동으로 String 타입으로 변환
        println("  if 블록 내부: 길이는 ${smartName.length}")  // ? 불필요
    }

    // 실용적인 예제
    println("\n📌 실용적인 예제")
    data class User(val name: String, val email: String?)

    fun sendEmail(user: User) {
        user.email?.let { email ->
            println("  ${user.name}님께 이메일 전송: $email")
        } ?: println("  ${user.name}님의 이메일이 없습니다")
    }

    sendEmail(User("김철수", "kim@example.com"))
    sendEmail(User("박영희", null))

    // 💡 Java: NullPointerException 위험 상존, Optional 사용이 번거로움
    // 💡 Python: None 체크를 런타임에만 할 수 있음
    // 💡 C: NULL 포인터 역참조로 인한 세그먼테이션 폴트
    // 💡 Kotlin: 컴파일 시점에 null 안정성 보장!

    println("\n🎯 Null Safety 정리:")
    println("  1. ?로 nullable 타입 명시")
    println("  2. ?.로 안전하게 접근")
    println("  3. ?:로 기본값 제공")
    println("  4. let으로 null이 아닐 때만 실행")
    println("  5. !!는 정말 확실할 때만 사용")

    println()
}