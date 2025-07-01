CLAUDE.md

Kotlin & SpringBoot 실전 마스터 3단계 로드맵

⸻

0. 개요
	•	목표
	•	Kotlin 언어와 SpringBoot를 실제 프로젝트에 쓸 수 있을 만큼 익힌다.
	•	작은 예제→스몰프로젝트→실전 서비스로 점진적으로 스킬을 다진다.
  . 1단계는 다양한 예제를 통해 개념을 익힐 수 있게 과정을 구성한다.
	•	마지막 3단계는 ‘완성’보다 ‘확장/반복’에 초점! 계속 발전시킬 수 있는 토대를 만든다.
  . 총 3단계인데 과정생성은 1단계부터 하고 완료하면 2단계를 하고 완료하면 마지막 3단계를 하는 식으로 진행.

⸻

1단계. Kotlin & SpringBoot 실전 입문

1.1 개발환경 세팅
	•	IntelliJ IDEA Community(무료) 설치
	•	실행 → New Project → Spring Initializr 선택
	•	Language: Kotlin
	•	Dependencies: Spring Web, Spring Data JPA, H2
	•	JDK: 17 이상
	•	프로젝트 구조/패키지명 자유롭게 지정
	•	필요 시 SaaS DB(PlanetScale, Neon 등)도 연결 가능
	•	(실습은 H2 인메모리 DB로 시작 권장, 바로 브라우저에서 확인 가능)

1.2 Kotlin 기본 문법 실습
	•	변수/함수/데이터클래스/컬렉션/람다/Null safety

val name = "GD"
fun greet(n: String) = "Hi, $n!"
data class Expense(val item: String, val amount: Int)
val expenses = listOf(Expense("Coffee", 3000), Expense("Lunch", 8000))
println(expenses.filter { it.amount > 5000 })


	•	객체지향, 상속, sealed class, 확장함수 등도 예시로 간단 체험
	•	Java와 다른 Kotlin만의 문법 차이/장점 짧게 비교 실습

1.3 SpringBoot 기본 실습 (Hello API ~ CRUD)
  . SpringBoot란? 
	•	Hello API 빠르게 만들어보기

@RestController
class HelloController {
    @GetMapping("/hello")
    fun hello() = "Hello, SpringBoot + Kotlin!"
}


	•	간단 CRUD 실습(예: Todo)
	•	Entity, Repository, Controller를 한 파일씩 만들어 실습
	•	H2 인메모리 DB 연결(설정 불필요, 즉시 사용)
	•	간단 REST 호출 (Postman/HTTPie/브라우저)로 테스트
	•	H2 콘솔: /h2-console에서 직접 DB 쿼리도 확인
	•	(실습 미션 예시)
	•	콘솔앱: “오늘의 할 일/Todo 리스트” 작성
	•	REST: POST /todos로 할 일 등록, GET /todos로 전체 조회
	•	DB와 연동되는 데이터 흐름 전체 경험

⸻

2단계. 스몰프로젝트: 법인차 운행기록부 생성 서비스

2.1 프로젝트 목적/개요
	•	목표:
	•	법인차 운행기록을 규칙에 따라 자동 생성하는 웹/CLI 서비스 개발
	•	일상적인 업무 자동화 실전 프로젝트 경험
	•	핵심 기능:
	•	계기판 시작 거리/출퇴근 거리/업무일정 등 직접 입력
	•	연간 운행기록 자동 산출, 표/엑셀로 출력
	•	업무용/출퇴근/일반업무용 구분, 전체 거리/일정 규칙 검증

2.2 주요 입력 및 산출 규칙
	•	입력값
	•	시작 계기판 거리 (예: 38,000, 연도별/차량별로 다름)
	•	기본 출퇴근 거리 (2~3km, 사용자 지정)
	•	일반업무용 일정 (일정명/날짜/거리범위 직접 입력)
	•	외부미팅 등 반복 일정도 직접 개수/날짜/거리 입력
	•	목표 총 주행거리(예: 12,000km 등, 초과시 자동 제외 룰 적용)
	•	운행기록 산출 규칙
	•	평일(월~금): 출퇴근 2km/3km 랜덤, 일반업무용 일정 있는 날은 업무용 거리/비고 입력, 출퇴근 거리 생략
	•	토/일: 하루만 출퇴근 거리, 일정 있으면 업무용만 입력
	•	일정 거리 배정: 입력 범위에서 랜덤 선택, 날짜별 배정
	•	계기판 거리/주행거리 누적, 0은 공란, 비고는 일정명
	•	목표 총 주행거리 초과시, 특정 일정부터 자동 제외

2.3 데이터 구조 예시

data class 운행기록(
    val 일자: LocalDate,
    val 주행전: Int,
    val 주행후: Int,
    val 주행거리: Int,
    val 출퇴근용: Int?,
    val 일반업무용: Int?,
    val 비고: String?
)

2.4 실습/구현 흐름
	1.	입력 UI/설정
	•	시작 계기판, 출퇴근 거리, 일정명/날짜/거리 등 입력폼 구성(Web or CLI)
	•	반복 일정/외부미팅 자동 배정 기능
	2.	자동 운행기록 산출
	•	날짜 루프 돌리며, 입력/규칙에 따라 거리/비고/계기판 자동 계산
	•	전체 거리 초과시 자동 일정 제외/경고
	3.	출력
	•	표/CSV/엑셀 다운로드
	•	웹 UI 테이블, 혹은 콘솔/CLI 표 출력
	•	샘플 출력 예시:

일자	주행전	주행후	주행거리	출퇴근용	일반업무용	비고
2025-01-02	38000	38002	2	2		
2025-01-06	38010	38050	40		40	외부미팅


	4.	(옵션) 고도화
	•	다중 차량 지원, 월별/사용자별 리포트
	•	위치/지도 API 연동
	•	사내 시스템 연계

2.5 프로젝트 설계 팁
	•	업무규칙은 코드 상수/DB로 관리 → 규칙변경에도 유지보수 편하게
	•	초반에는 로컬 H2 DB, 나중에 SaaS DB나 회사 DB로 쉽게 마이그레이션 가능
	•	모든 산출/출력은 실제 회사 운행기록부 양식에 맞추어 포맷

⸻

3단계. 종합프로젝트: 투자분석/정리 서비스

3.1 목표/비전
	•	나만의 투자 관리/분석 서비스를 “작게 시작해서 계속 발전”
	•	초기에는 종목/거래/지표/메모 관리,
이후에는 API/추천/리포트/대시보드 등 기능 추가/확장

3.2 1차 구현 범위(최소 버전 MVP)
	•	종목 관리: 관심/추천 종목 추가/삭제/태그/메모
	•	투자내역: 매수/매도/수량/단가/메모 등록/조회
	•	매크로 지표: 금리, 환율 등 직접 또는 외부 API로 입력/저장
	•	기초 리포트: 종목별/기간별 손익, 수익률, 요약 표/그래프
	•	모든 데이터는 DB에 저장, CSV/엑셀로 내보내기 가능

3.3 데이터 구조 예시

data class Stock(
    val code: String,
    val name: String,
    val memo: String? = null,
    val tags: List<String> = emptyList()
)
data class Trade(
    val stockCode: String,
    val date: LocalDate,
    val price: Int,
    val quantity: Int,
    val type: String // "buy", "sell"
)
data class MacroIndicator(
    val name: String, // 예: "금리", "환율"
    val date: LocalDate,
    val value: Double
)

3.4 프로젝트 성장 플랜
	•	초기 MVP: REST API만 우선 구현
	•	/stocks, /trades, /macro, /reports 엔드포인트
	•	웹테이블, 다운로드
	•	확장 계획
	•	외부 오픈API 연동(증권/경제지표/뉴스)
	•	투자 전략 리포트, 종목 추천, 차트, 대시보드
	•	사용자 인증(JWT), 멀티 계정, 웹/모바일 UI 등
	•	진화형 개발
	•	기본 기능이 완성되어도,
실전 투자/학습에서 발견되는 니즈에 따라 계속 업그레이드

3.5 프로젝트 설계/개발 팁
	•	DB 설계는 반드시 “확장 가능”하게(테이블 분리, 유연한 데이터 구조)
	•	API는 OpenAPI/Swagger로 문서화, 버전업 쉽게 관리
	•	실제 내 투자/관심사 패턴에 맞게 데이터 모델/기능을 계속 업데이트

⸻

[실습 순서 요약]
	1.	1단계:
	•	IntelliJ 환경세팅, Kotlin 기본/문법, SpringBoot + Hello/CRUD 실습
	2.	2단계:
	•	법인차 운행기록부 스몰프로젝트 실습(실제 데이터 입력, 출력까지)
	3.	3단계:
	•	투자분석 서비스 초기버전 개발 → 점진적 기능 추가하며 장기적으로 성장시킴

⸻

[추가 참고 및 팁]
	•	DB 연동:
	•	H2(로컬, 인메모리)로 시작 → 필요하면 PlanetScale/Neon 등 SaaS DB로 손쉽게 변경
	•	코드/데이터 관리:
	•	GitHub 등으로 버전 관리
	•	문서화/학습 관리:
	•	Notion, 위키 등에서 이 문서 그대로 붙여넣고 진행 상황 체크
	•	공식 문서:
	•	Kotlin 공식문서
	•	Spring Guides
	•	IntelliJ IDEA 다운로드


