# 📌 AI 활용 비즈니즈 프로젝트 (2NE1)

## 📖 프로젝트 목적/상세

## AI 활용 주문 관리 자동화 시스템 개발(2025.02.12~2025.02.26)

**OO의 민족과 같은 주문 관리 플랫폼을 Spring Boot 기반의 모놀리식 애플리케이션으로 개발하는 것입니다.**

기존의 전화 주문 및 종이 기록 방식에서 벗어나, 앱을 통해 주문을 받고, 기록, 결제 및 처리까지
자동화 하는 시스템을 구축합니다. 이를 통해 반복적이고 인력이 많이 소요되는 효율적으로 자동화
하고, 실생활의 주문 및 관리 프로세스를 개발하는 것이 이번 프로젝트의 핵심 목표입니다.
특히,  2주라는 짧은 기간 내에 기획, 설계, 개발, 테스트, 배포까지 전 과정을 경험하며, 모든 요구사항을 완수해야 하는 일정 속에서 협업과 프로젝트 관리의 중요성을 체감하는 것 또한 중요한 목표입니다.

<br>

## 🏗 서비스 구성 및 실행 방법
### 💾 환경 설정
```bash
git clone https://github.com/사용자명/레포명.git
cd 레포명
 1. git에서 push
 2. CI 진행
 3. 도커 이미지를 도커 허브에 올린 후 EC2 진입
 4. 도커 허브에서 도커 이미지를 다운 후 도커 컴포즈로 환경변수 주입
 5. 엔진엑스로 2개의 포트 중 현재 사용중이지 않은 포트로 배포
```


<br>

## 🎯 팀원 역할분담
<table>
  <tr>
    <th>
      <a href="https://github.com/Leewon2" target="_blank">
        이원희&lt;팀장&gt;
      </a>
    </th>
    <th>
      <a href="https://github.com/dkki4887" target="_blank">
        이채연
      </a>
    </th>
    <th>
      <a href="https://github.com/leeseowoo" target="_blank">
        이서우
      </a>
    </th>
    <th>
      <a href="https://github.com/ckdrmsdl9999" target="_blank">
        윤창근
      </a>
    </th>
    <th>
      <a href="https://github.com/jjsh0208" target="_blank">
        전승현
      </a>
    </th>
  </tr>
  <tr>
    <td>
      <img src="https://github.com/Leewon2.png" width="150" alt="이원희 팀장">
    </td>
    <td>
      <img src="https://github.com/dkki4887.png" width="150" alt="이채연">
    </td>
    <td>
      <img src="https://github.com/leeseowoo.png" width="150" alt="이서우">
    </td>
    <td>
      <img src="https://github.com/ckdrmsdl9999.png" width="150" alt="윤창근">
    </td>
    <td>
      <img src="https://github.com/jjsh0208.png" width="150" alt="전승현">
    </td>
  </tr>
  <tr>

  <th>Payment <br> Card <br> 배포  <!-- 원희 -->
  <th>Order <br> Review</th> <!-- 채연 -->
  <th>Gemini AI <br> Product</th> <!-- 서우 -->
  <th>Region <br> Store</th> <!-- 창근 -->
  <th>User<br> DeliveryAddress <br> Spring Security</th> <!-- 승현 -->
  </tr>
</table>

<br>

## ☁️ 인프라 설계서
![Image](https://github.com/user-attachments/assets/30111de9-b5b7-47f3-9a0d-b9e71399e04c)

<br>

## 📌 ERD

![Image](https://github.com/user-attachments/assets/f144479b-b3e2-47f8-8ca2-6c9f0beb9f0f)

<br>

## 🛠 기술 스택
## Backend
- **Framework**: Spring Boot 3.x
  <!-- Spring Boot 최신 버전 사용 -->
- **Database Access**: Spring Data JPA , QueryDSL
  <!-- ORM 프레임워크로 데이터베이스와의 연동을 쉽게 처리 -->
- **Security**: Spring Security 6.x
  <!-- 인증과 인가를 위한 보안 모듈 -->
- **API Documentation**: Swagger (Springdoc OpenAPI)
  <!-- API 문서를 자동 생성해주는 Swagger 도구 -->
- **WebSocket**: 실시간 데이터 통신 적용
  <!-- 실시간 통신을 위해 WebSocket을 사용 -->
- **REST API**: RESTful API 설계
  <!-- REST 아키텍처 스타일에 따른 API 설계 -->

## Database
- **Primary DB**: PostgreSQL
  <!-- 주 데이터베이스로 사용 -->
- **In-memory DB**: H2 ( 통합 테스트 환경 )
  <!-- 테스트 및 개발 환경에서 사용하는 인메모리 데이터베이스 -->

## Server
- **Application Server**: Apache Tomcat 9.0
  <!-- 서블릿 컨테이너로 사용하는 Tomcat 서버 -->

## Authentication
- **Token-Based Authentication**: JWT (JSON Web Token)
  <!-- 토큰 기반 인증 방식으로 JWT 사용 -->

## Devops
- **배포 빌드**: Docker, Docker-Compose
- **확장성 및 보안**: nginx
- **CI**: github action
     
## Etc
- **외부 API 연동**
    - - **‎Gemini API** : 상품 설명 문구 추천을 위한 외부 AI API 연동.

