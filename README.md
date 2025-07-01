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
└── repository/                  # JPA 레포지토리
    └── TodoRepository.kt        # Todo 레포지토리
```

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

## 📚 다음 단계

이 프로젝트를 완료한 후에는:
1. **2단계**: 법인차 운행기록부 생성 서비스 (스몰 프로젝트)
2. **3단계**: 투자분석/정리 서비스 (종합 프로젝트)

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