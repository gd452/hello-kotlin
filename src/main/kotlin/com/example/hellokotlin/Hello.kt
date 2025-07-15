package com.example.hellokotlin

class Car {
    var brand: String = ""
    var model: String = ""
    var year: Int = 0

    init {
        // init 블록은 기본 생성자 실행 시 호출됨
        println("차량이 생성되었습니다: $brand $model ($year)")
    }

    // 보조 생성자 1
    constructor(brand: String, model: String) {
        this.brand = brand
        this.model = model
        this.year = 2024  // 기본값
    }

    // 보조 생성자 2
    constructor(brand: String, model: String, year: Int) {
        this.brand = brand
        this.model = model
        this.year = year
    }

    fun displayCar() {
        println("차량: ${year}년식 ${brand} ${model}")
    }
}


fun main() {
//    println("Hello Kotlin")
//    val car3 = Car()
//    car3.displayCar()

    // 변수에 익명 객체 저장
    val adHocObject = object {
        var x = 1
        var y = 2
        fun sum() = x + y
    }

    println("익명 객체의 합: ${adHocObject.sum()}")
    adHocObject.x = 10
    println("x 변경 후 합: ${adHocObject.sum()}")

}