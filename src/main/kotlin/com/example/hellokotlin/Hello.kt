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
    println("Hello Kotlin")
    val car3 = Car()
    car3.displayCar()
}