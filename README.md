# banking-server-api

![spring boot](https://img.shields.io/badge/Spring_Boot-3.0.2-6DB33F?logo=spring-boot)
![spring security](https://img.shields.io/badge/Spring_Security-6.0.2-6DB33F?logo=spring-security)
![mysql](https://img.shields.io/badge/MySQL-8.0.31-4479A1?logo=mysql&logoColor=white)
![kotlin](https://img.shields.io/badge/Kotlin-1.8.11-7F52FF?logo=kotlin&logoColor=7F52FF)
![exposed](https://img.shields.io/badge/Exposed-0.41.1-000000?logo=jetbrains&logoColor=white)

## API Docs

- https://documenter.getpostman.com/view/9693533/2s93CSoAfx

## 서버 실행 방법

### 준비물

- git
- docker compose

```
git clone <repository>
cd ./banking-server/infra && docker-compose up
```

## 프로젝트 소개

### kotlin

- 사용한 이유: 써보고 싶었다

### 로그인

- JWT Token 로그인
- Spring Security로 token filter를 사용

#### 부족한 부분

- secret key 환경변수로 관리하기
- expired 세팅하기
- email 중복체크 동시성 오류 핸들링

### 데이터베이스 ORM

- Jetbrains의 Exposed 사용
- DB 연결 코드를 빈에 등록해서 스프링 application이 시작할 때 마다 자동으로 연결되게 세팅
- jpa 안 쓴 이유: kotlin에서 entity에 @getter를 붙이기 싫어서

#### Exposed 느낀 점

- 모든 코드가 transaction { } 안에 들어가야 함
  - 지연 로딩 해놓고 밖에서 접근하면 안됨
- 언어처럼 잘 읽히는 것 같다
- (잘은 모르지만) 코틀린스럽다

### 테스트

- juint5 사용
- `application.yaml`과 `application-test.yaml`로 환경을 분리하면서 테스트용 데이터베이스를 연결
- jpa가 아닌 exposed를 사용하면서 db 연결이 필수적인 상황이 돼서 기존의 controller - service - repository 각각의 유닛테스트가 아닌 spring application로 함
- 테스트가 실행될 때 마다 데이터베이스를 드랍하고 띄움

#### 느낀 점

- 각각의 기능을 담당하는 계층에 대한 테스트를 진행하지 않아서 안좋을 줄 알았는데 테스트를 여러개 안만들어도 되고 안정적이라서 좋았다

### 송금 동시성 고민

- db lock을 안걸고 최대한 Create, Read로 구현하려고 했는데 그냥 lock을 걸었다
  - 생각한 방식: 거래 history만 insert하고 조회할 땐 계산해서 redis에 잔액을 캐싱
  - 이렇게 안 한 이유: 복잡해서

### Spring Boot 3.0.2 & Spring Security 6.0.2

- 쓴 이유: 와탭랩스 컨퍼런스에서 버전은 항상 latest 사용하라는 조언을 듣고
- 장점: deprecated 안떠서 ide warning 창이 깨끗함
- 단점: 인터넷에 잘 안나옴 (chat gpt도 잘 모름)


