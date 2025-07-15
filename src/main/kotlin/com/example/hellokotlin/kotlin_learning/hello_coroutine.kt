package com.example.hellokotlin.kotlin_learning

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.system.measureTimeMillis


/**
 * 코틀린 코루틴 예제
 * 코루틴은 경량 스레드로 비동기 프로그래밍을 쉽게 만들어줍니다.
 * 이 예제에서는 launch와 async를 사용하여 코루틴을 실행합니다.
 */

fun main() {
    println("=== Kotlin Coroutine 예제 ===\n")
//    coroutineExample2() // 괄호 필수
//    coroutineExample3() // 괄호 필수
//    coroutineExample4() // 괄호 필수
//    coroutineExample5() // 괄호 필수
    coroutineExample6() // 괄호 필수
}

fun coroutineExample2() {
    println("1. 코루틴 실행")


    // runBlocking은 현재 스레드를 차단하고 코루틴이 완료될 때까지 기다립니다.
    runBlocking {
        println("코루틴 시작")

        // launch는 새로운 코루틴을 시작합니다.
        launch {
            delay(1000L) // 1초 지연
            println("코루틴 1 완료")
        }

        // async는 결과를 반환하는 코루틴을 시작합니다.
        val deferred = async {
            delay(500L) // 0.5초 지연
            "코루틴 2 결과"
        }

        // 코루틴이 완료될 때까지 기다립니다.
        println(deferred.await()) // 코루틴 2의 결과 출력

        println("코루틴 종료")
    }
}

fun coroutineExample3() {

    runBlocking {
        suspend fun fetchData(name: String, delay: Long): String {
            delay(delay)
            return "$name 데이터"
        }

        // 순차 실행
        val time1 = measureTimeMillis {
            val data1 = fetchData("첫 번째", 1000)
            val data2 = fetchData("두 번째", 1000)
            println("순차: $data1, $data2")
        }
        println("순차 실행 시간: ${time1}ms")

        // 병렬 실행
        val time2 = measureTimeMillis {
            val deferred1 = async { fetchData("첫 번째", 1000) }
            val deferred2 = async { fetchData("두 번째", 1000) }
            println("병렬: ${deferred1.await()}, ${deferred2.await()}")
        }
        println("병렬 실행 시간: ${time2}ms")
    }
}


fun coroutineExample4() {
    // 코루틴 스코프와 구조적 동시성
    runBlocking {
        println("메인 코루틴 시작")

        // coroutineScope: 모든 자식이 완료될 때까지 대기
        coroutineScope {
            launch {
                delay(2000)
                println("자식 코루틴 1 완료")
            }

            launch {
                delay(1000)
                println("자식 코루틴 2 완료")
            }

            println("coroutineScope 내부")
        }

        println("모든 자식 코루틴 완료 후 실행")
    }
}

fun coroutineExample5() {
    // Flow - 비동기 데이터 스트림
    runBlocking {
        // Flow 생성
        fun simpleFlow(): Flow<Int> = flow {
            println("Flow 시작")
            for (i in 1..3) {
                delay(300)  // 비동기 작업 시뮬레이션
                println("방출 >>: $i ")
                emit(i)     // 값 방출
                println("방출 >>>>: $i ")
            }
        }

        // Flow 수집
        println("Flow 수집 시작")
        simpleFlow()
            .map {
                println("map is called with: $it")
                it * it
            }  // 변환
            .filter { it > 3 } // 필터링
            .collect { value ->
                println("수신: $value")
            }
    }

}


fun coroutineExample6() {
    // 실용적인 예제: 여러 API 호출
    runBlocking {
        data class User(val id: Int, val name: String)
        data class Post(val userId: Int, val title: String)

        suspend fun fetchUser(id: Int): User {
            delay(1000)  // API 호출 시뮬레이션
            return User(id, "User$id")
        }

        suspend fun fetchPosts(userId: Int): List<Post> {
            delay(1500)  // API 호출 시뮬레이션
            return listOf(
                Post(userId, "Post 1"),
                Post(userId, "Post 2")
            )
        }

        // 여러 사용자의 정보와 게시물을 병렬로 가져오기
        val userIds = listOf(1, 2, 3)

        val time = measureTimeMillis {
            val results = userIds.map { id ->
                async {
                    val user = fetchUser(id)
                    val posts = fetchPosts(id)
                    user to posts
                }
            }.awaitAll()

            results.forEach { (user, posts) ->
                println("${user.name}: ${posts.size}개 게시물")
            }
        }

        println("총 소요 시간: ${time}ms")
    }
}





