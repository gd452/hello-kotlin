# Kotlin 학습 가이드

## 학습 과정 소개

이 가이드는 Kotlin 언어를 단계별로 학습할 수 있도록 구성된 실습 예제 모음입니다.
각 파일을 순서대로 실행하면서 Kotlin의 핵심 개념을 익힐 수 있습니다.

## 실행 방법

각 파일의 `main()` 함수를 실행하여 예제를 확인할 수 있습니다:
- IntelliJ IDEA: 파일 열고 main 함수 왼쪽의 ▶ 버튼 클릭
- 터미널: `./gradlew run -PmainClass=com.example.hellokotlin.kotlin_learning.파일명Kt`

## 학습 순서

### 1. 기본 문법 (01_basics.kt)
- 변수 선언 (val, var)
- 함수 정의
- 조건문 (if, when)
- 반복문 (for, while)
- Null Safety

**주요 개념:**
- `val`: 불변 변수 (read-only)
- `var`: 가변 변수
- 문자열 템플릿: `"Hello $name"`
- 타입 추론
- Nullable 타입: `String?`

### 2. 객체지향 프로그래밍 (02_oop.kt)
- 클래스와 생성자
- 데이터 클래스
- 상속과 다형성
- 인터페이스
- Sealed 클래스
- 싱글톤 객체

**주요 개념:**
- `data class`: equals(), toString(), copy() 자동 생성
- `open class`: 상속 가능한 클래스
- `sealed class`: 제한된 계층 구조
- `object`: 싱글톤 패턴
- `companion object`: 정적 멤버

### 3. 함수형 프로그래밍 (03_functional.kt)
- 람다 표현식
- 고차 함수
- 컬렉션 연산 (filter, map, reduce)
- 시퀀스와 지연 평가
- 함수 합성

**주요 개념:**
- 람다: `{ x -> x * 2 }`
- 함수 타입: `(Int) -> String`
- 컬렉션 함수: filter, map, groupBy, fold
- Sequence: 지연 평가로 성능 최적화

### 4. 고급 기능 (04_advanced.kt)
- 확장 함수
- 값 클래스 (Inline Classes)
- 위임 (Delegation)
- 코루틴
- DSL 구축

**주요 개념:**
- 확장 함수: 기존 클래스에 메서드 추가
- `value class`: 성능 최적화를 위한 인라인 클래스
- 속성 위임: `by lazy`, 커스텀 위임
- 코루틴: 비동기 프로그래밍
- DSL: 도메인 특화 언어 구축

### 5. 실습 미션 (05_practice_missions.kt)
- Todo 앱 구현
- 계산기 구현
- 가계부 앱 구현

**실습 포인트:**
- 실제 애플리케이션 로직 구현
- 다양한 Kotlin 기능 종합 활용
- 클린 코드 작성 연습

## 다음 단계

1. **SpringBoot 연동**: 학습한 Kotlin 지식을 바탕으로 웹 애플리케이션 개발
2. **코루틴 심화**: 비동기 프로그래밍과 동시성 처리
3. **테스트 작성**: JUnit5와 MockK를 활용한 테스트
4. **실전 프로젝트**: CLAUDE.md의 2단계, 3단계 프로젝트 진행

## 추가 학습 자료

- [Kotlin 공식 문서](https://kotlinlang.org/docs/home.html)
- [Kotlin Koans](https://play.kotlinlang.org/koans/overview): 인터랙티브 학습
- [Kotlin by Example](https://play.kotlinlang.org/byExample/overview): 예제로 배우는 Kotlin

## 팁

1. **REPL 활용**: IntelliJ에서 Tools > Kotlin > Kotlin REPL로 빠른 실험
2. **타입 확인**: 변수에 마우스를 올려 타입 추론 결과 확인
3. **자동 완성**: Ctrl+Space로 사용 가능한 함수 확인
4. **리팩토링**: Alt+Enter로 코드 개선 제안 확인