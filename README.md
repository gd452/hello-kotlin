# Hello Kotlin - Spring Boot 프로젝트

Kotlin과 Spring Boot를 활용한 실전 프로젝트 학습 Repository

## 🚀 프로젝트 소개

이 프로젝트는 Kotlin & SpringBoot 실전 마스터 3단계 로드맵의 첫 번째 단계 프로젝트입니다.
간단한 Hello API와 Todo CRUD 기능을 구현하여 Kotlin과 Spring Boot의 기본기를 익힐 수 있습니다.

## 🛠 기술 스택

- **Language**: Kotlin
- **Framework**: Spring Boot 3.x
- **Build Tool**: Gradle (Kotlin DSL)
- **Database**: H2 (In-memory)
- **Dependencies**:
  - Spring Web
  - Spring Data JPA
  - H2 Database

## 📁 프로젝트 구조

```
src/main/kotlin/com/example/hellokotlin/
├── HelloKotlinApplication.kt    # 메인 애플리케이션
├── controller/                  # REST 컨트롤러
│   ├── HelloController.kt       # Hello API
│   └── TodoController.kt        # Todo CRUD API
├── entity/                      # JPA 엔티티
│   └── Todo.kt                  # Todo 엔티티
├── repository/                  # JPA 레포지토리
│   └── TodoRepository.kt        # Todo 레포지토리
└── kotlin_learning/             # Kotlin 학습 예제
    ├── 01_basics.kt             # 기본 문법
    ├── 02_oop.kt                # 객체지향 프로그래밍
    ├── 03_functional.kt         # 함수형 프로그래밍
    ├── 04_advanced.kt           # 고급 기능
    ├── 05_practice_missions.kt  # 실습 미션
    └── KOTLIN_LEARNING_GUIDE.md # 학습 가이드
```

## 📚 Kotlin 학습 과정

### 🎯 1단계: Kotlin 기초 학습

프로젝트에 포함된 학습 예제를 통해 Kotlin의 핵심 개념을 익힐 수 있습니다:

1. **기본 문법** (`01_basics.kt`)
   - 변수 선언 (val, var)
   - 함수 정의와 호출
   - 조건문 (if, when)
   - 반복문 (for, while)
   - Null Safety

2. **객체지향 프로그래밍** (`02_oop.kt`)
   - 클래스와 생성자
   - 데이터 클래스
   - 상속과 다형성
   - 인터페이스
   - Sealed 클래스
   - 싱글톤 객체

3. **함수형 프로그래밍** (`03_functional.kt`)
   - 람다 표현식
   - 고차 함수
   - 컬렉션 연산 (filter, map, reduce)
   - 시퀀스와 지연 평가
   - 함수 합성

4. **고급 기능** (`04_advanced.kt`)
   - 확장 함수
   - 값 클래스 (Inline Classes)
   - 위임 (Delegation)
   - 코루틴
   - DSL 구축

5. **실습 미션** (`05_practice_missions.kt`)
   - Todo 앱 구현
   - 계산기 구현
   - 가계부 앱 구현

### 📖 학습 방법

1. 각 파일의 `main()` 함수를 실행하여 예제 확인
2. IntelliJ IDEA에서 파일 열고 main 함수 왼쪽의 ▶ 버튼 클릭
3. 또는 터미널에서 실행:
   ```bash
   ./gradlew run -PmainClass=com.example.hellokotlin.kotlin_learning.01_basicsKt
   ```

자세한 학습 가이드는 [KOTLIN_LEARNING_GUIDE.md](src/main/kotlin/com/example/hellokotlin/kotlin_learning/KOTLIN_LEARNING_GUIDE.md)를 참고하세요.

## 🏃‍♂️ 실행 방법

### 1. 프로젝트 클론
```bash
git clone https://github.com/gd452/hello-kotlin.git
cd hello-kotlin
```

### 2. 애플리케이션 실행
```bash
./gradlew bootRun
```

### 3. H2 Console 접속
- URL: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: (비어있음)

## 📡 API 엔드포인트

### Hello API
- **GET** `/hello`
  - 간단한 인사 메시지 반환
  - Response: `"Hello, SpringBoot + Kotlin!"`

### Todo API
- **GET** `/todos`
  - 모든 Todo 목록 조회 (최신순 정렬)
  
- **GET** `/todos/{id}`
  - 특정 Todo 조회
  
- **POST** `/todos`
  - 새로운 Todo 생성
  - Request Body:
    ```json
    {
      "title": "할 일 제목",
      "description": "할 일 설명",
      "completed": false
    }
    ```

- **PUT** `/todos/{id}`
  - Todo 수정
  - Request Body:
    ```json
    {
      "title": "수정된 제목",
      "description": "수정된 설명",
      "completed": true
    }
    ```

- **DELETE** `/todos/{id}`
  - Todo 삭제

## 🧪 API 테스트 예시

### Todo 생성
```bash
curl -X POST http://localhost:8080/todos \
  -H "Content-Type: application/json" \
  -d '{"title":"Kotlin 공부하기","description":"Spring Boot와 함께"}'
```

### Todo 목록 조회
```bash
curl http://localhost:8080/todos
```

## 🚀 학습 로드맵

### 현재 단계: 1단계 - Kotlin & SpringBoot 실전 입문 ✅
- Kotlin 기본 문법 학습
- SpringBoot 기초 (Hello API, CRUD)
- H2 인메모리 DB 연동

### 다음 단계

#### 2단계: 법인차 운행기록부 생성 서비스 (스몰 프로젝트)
- 실제 업무 자동화 프로젝트
- 입력값 기반 운행기록 자동 생성
- 규칙 기반 데이터 처리
- CSV/Excel 출력 기능

#### 3단계: 투자분석/정리 서비스 (종합 프로젝트)
- 종목/거래 관리
- 투자 지표 분석
- 리포트 생성
- 확장 가능한 아키텍처 설계

각 단계별로 점진적으로 복잡도가 증가하며, 실전에서 사용할 수 있는 기술들을 익힐 수 있습니다.

## 🤝 기여 방법

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 License

이 프로젝트는 학습 목적으로 만들어졌습니다.

---

Built with ❤️ using Kotlin and Spring Boot