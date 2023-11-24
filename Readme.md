<div align="center">

# 🏥&nbsp; BeNurse

간호사들의 <b>업무 편의성을</b>올려주는 서비스<br/>

<img title="" src="Readme_assets/BeNurseIcon.png" alt="BeNurseIcon.png" width="132">



## 목차

1. [개요](#개요)
2. [서비스 화면](#서비스-화면)
3. [기술 소개](#기술-소개)
4. [개발 환경](#개발-환경)
5. [설계 문서](#설계-문서)
6. [팀원 소개](#팀원-소개)

## 1. 개요

> <b>프로젝트 기간</b> : 2023/10/09 ~ 2023/11/17 <br> > <b>참고자료 </b> : 🎞 [UCC](https://youtu.be/kQ3KlyPVkUI)
> 📃 [최종발표 자료](https://docs.google.com/presentation/d/1QwsdfY8H5LlSaeu2jP0Gdhv5w4-nLvkt/edit?usp=drive_link&ouid=117454697899885480089&rtpof=true&sd=true)
> <br>

## 2. 서비스 화면

📱 Mobile

💉 <b>Login & Invite_Code</b>

| <img title="" src="Readme_assets/login.gif" alt="" width="300"> | <img title="" src="Readme_assets/invite.gif" alt="" width="300"> |
|:-----------------------------------------------------------------------------------------------------------------------------:|:------------------------------------------------------------------------------------------------------------------------------:|
| **Login Page**                                                                                                                | **Invite Code**                                                                                                                |

<br>
💉 <b>Main</b>

| <img title="" src="Readme_assets/mainpage.gif" alt="" width="300"> | <img title="" src="Readme_assets/notice.gif" alt="" width="300"> |
|:--------------------------------------------------------------------------------------------------------------------------------:|:------------------------------------------------------------------------------------------------------------------------------:|
| **Main Page**                                                                                                                    | **Notice**                                                                                                                     |
| <img title="" src="Readme_assets/mypage.gif" alt="" width="300">   | <img title="" src="Readme_assets/tip.gif" alt="" width="300">    |
| **My Page**                                                                                                                      | **Tip**                                                                                                                        |

<br>
💉 <b>Handover</b>

| <img title="" src="Readme_assets/App_HandoverMain.gif" alt="" width="300"> | <img title="" src="Readme_assets/App_HandoverCreate.gif" alt="" width="300"> |
|:----------------------------------------------------------------------------------------------------------------------------------------:|:------------------------------------------------------------------------------------------------------------------------------------------:|
| **Handover Main Page**                                                                                                                   | **Create Handover**                                                                                                                        |

<br>

| <img title="" src="Readme_assets/App_CreatePaHo.gif" alt="" width="300"> | <img title="" src="Readme_assets/App_WriteHo.gif" alt="" width="300"> |
|:--------------------------------------------------------------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------------------------------------------------:|
| **Create Patient Handover**                                                                                                            | **Add Handover**                                                                                                                    |

<br>

| <img title="" src="Readme_assets/App_TakeSelect.gif" alt="" width="300"> | <img title="" src="Readme_assets/App_TakeGive.gif" alt="" width="300"> |
|:--------------------------------------------------------------------------------------------------------------------------------------:|:------------------------------------------------------------------------------------------------------------------------------------:|
| **Select Taker**                                                                                                                       | **Temp & Recive Handover**                                                                                                           |

<br>
💉 <b>Schedule</b>

| <img title="" src="Readme_assets/App_WeeklySchedule.gif" alt="" width="300"> | <img title="" src="Readme_assets/App_MonthlySchedule.gif" alt="" width="300"> |
|:------------------------------------------------------------------------------------------------------------------------------------------:|:-------------------------------------------------------------------------------------------------------------------------------------------:|
| **Weekly Schedule**                                                                                                                        | **Monthly Schedule**                                                                                                                        |
| <img title="" src="Readme_assets/App_OffApply.gif" alt="" width="300">       | <img title="" src="Readme_assets/App_DayDetail.gif" alt="" width="300">       |
| **Day Off Apply**                                                                                                                          | **Day Detail**                                                                                                                              |

<br>
💉 <b>Patient Management</b>

| <img title="" src="Readme_assets/App_PatientList.gif" alt="" width="300">    | <img title="" src="Readme_assets/App_PatientDetail.gif" alt="" width="300"> |
|:------------------------------------------------------------------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------------------------------------------------------:|
| **Patient List**                                                                                                                           | **Patient Detail**                                                                                                                        |
| <img title="" src="Readme_assets/App_PatientJournal.gif" alt="" width="300"> |                                                                                                                                           |
| **Patient Journal**                                                                                                                        |                                                                                                                                           |

<br>
💉 <b>Device Management</b>

| <img title="" src="Readme_assets/App_DeviceMain.gif" alt="" width="300">     | <img title="" src="Readme_assets/App_DeviceList.gif" alt="" width="300"> |
|:------------------------------------------------------------------------------------------------------------------------------------------:|:--------------------------------------------------------------------------------------------------------------------------------------:|
| **Device Main**                                                                                                                            | **Device List**                                                                                                                        |
| <img title="" src="Readme_assets/App_DeviceLocation.gif" alt="" width="300"> | <img title="" src="Readme_assets/App_DeviceUse.gif" alt="" width="300">  |
| **Device Location**                                                                                                                        | **Device Use List**                                                                                                                    |

<br>

 💶 Web
💊 <b>Login & Select Role</b>

| <img title="" src="Readme_assets/Web_Login.gif" alt="" width="386"> | <img title="" src="Readme_assets/Web_Role.gif" alt="" width="386"> |
|:-------------------------------------------------------------------:|:--------------------------------------------------------------------------------------------------------------------------------:|
| **Login Page**                                                      | **Select Role**                                                                                                                  |
| <br>                                                                |                                                                                                                                  |
| 💊 <b>Create Hospital & Main</b>                                    |                                                                                                                                  |

| <img title="" src="Readme_assets/Web_CreateHospital.gif" alt="" width="386"> | <img title="" src="Readme_assets/Web_Main.gif" alt="" width="386"> |
|:------------------------------------------------------------------------------------------------------------------------------------------:|:--------------------------------------------------------------------------------------------------------------------------------:|
| **Create Hospital**                                                                                                                        | **Main Page**                                                                                                                    |

<br>
💊 <b>Make Schedule & Management Hospital</b>

| <img title="" src="Readme_assets/Web_mkschedule.gif" alt="" width="386"> | <img title="" src="Readme_assets/Web_Setting.gif" alt="" width="386"> |
|:--------------------------------------------------------------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------------------------------------------------:|
| **Make Schedule**                                                                                                                      | **Management**                                                                                                                      |

<br>

## 3. 기술 소개

- ##### 1. 장비 관리
  
  - ###### Three.js를 통한 시각적 위치 정보 전달
  - ###### NFC 태깅을 통한 손쉬운 장비 사용 및 관리
  - ###### BLE 비콘의 RSSI를 통한 위치정보 자동 저장

- ##### 2. 근무표
  
  - ###### 제작된 알고리즘에 의한 근무표 자동 생성
  - ###### 생성된 근무표에서 자신의 근무만을 가져와 스케줄에 자동 등록
  - ###### 간편한 휴무 신청 및 집계

- ##### 3. 인수인계 및 환자 간호일지
  
  - ###### 기존 인수인계의 복잡함과 다양함을 정형화
  - ###### 실시간으로 작성한 간호일지를 가져와 코멘트를 남겨 인수인계 작성의 편리성 증대
  - ###### 인계장의 디지털화로 인한 인계 내용 누락 가능성 대폭 축소
    
    <br>

## 4. 개발 환경

### ⚙ Management Tool

- 형상 관리 : Gitlab, Gerrit
- 이슈 관리 : Jira
- 커뮤니케이션 : Mattermost, Notion, Discord
- 디자인 : Figma, PowerPoint, ERD Cloud

### ⌨ IDE

- Visual Studio Code `1.83.0`
- Eclipse `4.29.0`
- Android studio

### 🖥 Frontend

### 💾 Backend

- jpa
- redis
- security
- jdbc
- oauth2
- lombok
- mysql
- swagger
- jwt
- kakaoLogin
- openFeign
- mariaDB

### 📱 Mobile

- React Native
- NFC
- BLE
- Android studio

### 📟 Infra

- AWS EC2
- Nginx 1.18.0
- Docker 20.10.12
- Jenkins
- Redis
- Ubuntu 20.04.6 LTS

<br>

## 5. 설계 문서

### ◼ Figma

<div align="center">
  <img src="Readme_assets/피그마_웹.PNG" alt="Figma 웹" width="400"/>
  <img src="Readme_assets/피그마_모바일.PNG" alt="Figma 모바일" width="400"/>
</div>

### ◼ 기능 명세서

<div align="center">
  <img src="Readme_assets/기능명세서_1_.PNG" alt="기능 명세서 1" width="400"/>
  <img src="Readme_assets/기능명세서_2_.PNG" alt="기능 명세서 2" width="400"/>
  <img src="Readme_assets/기능명세서_3_.PNG" alt="기능 명세서 3" width="400"/>
</div>

### ◼ API 명세서

<div align="center">
  <img src="Readme_assets/API_1_.PNG" alt="API 명세서 1" width="400"/>
  <img src="Readme_assets/API_2_.PNG" alt="API 명세서 2" width="400"/>
  <img src="Readme_assets/API_3_.PNG" alt="API 명세서 3" width="400"/>
</div>

### ◼ ERD

<div align="center">
  <img src="Readme_assets/Be_Nurse__5_.png" alt="ERD" width="600"/>
</div>

### ◼ Swagger

<div align="center">
  <img src="Readme_assets/swagger_1_.PNG" alt="Swagger 1" width="400"/>
  <img src="Readme_assets/swagger_2_.PNG" alt="Swagger 2" width="400"/>
  <img src="Readme_assets/swagger_3_.PNG" alt="Swagger 3" width="400"/>
  <img src="Readme_assets/swagger_4_.PNG" alt="Swagger 4" width="400"/>
  <img src="Readme_assets/swagger_5_.PNG" alt="Swagger 5" width="400"/>
  <img src="Readme_assets/swagger_6_.PNG" alt="Swagger 6" width="400"/>
  <img src="Readme_assets/swagger_7_.PNG" alt="Swagger 7" width="400"/>
</div>

### ◼ 시스템 아키텍처

<div align="center">
  <img src="Readme_assets/아키.png" width="600"/>
<div>

# 팀원 소개

| **[김대웅]()**                                                 | **[정소정]()**                                                 | **[정은경]()**                                                 | **[성제현]()**                                                   | **[이종윤]()**                                                 | **[김성현]()**                                                 |
|:-----------------------------------------------------------:|:-----------------------------------------------------------:|:-----------------------------------------------------------:|:-------------------------------------------------------------:|:-----------------------------------------------------------:|:-----------------------------------------------------------:|
| <img title="" src="Readme_assets/웅.png" alt="" width="500"> | <img title="" src="Readme_assets/소.png" alt="" width="500"> | <img title="" src="Readme_assets/은.png" alt="" width="500"> | <img title="" src="Readme_assets/SJH.jpg" alt="" width="500"> | <img title="" src="Readme_assets/종.png" alt="" width="500"> | <img title="" src="Readme_assets/현.png" alt="" width="500"> |
| Frontend                                                    | Frontend                                                    | Frontend                                                    | Backend & CI/CD                                               | Backend & CI/CD                                             | IoT                                                         |

## 😎 역할 분담

**Frontend**

- 김대웅 : Three.js 장비관리 기능 / React, React-Native 개발 / UX/UI 설계

- 정소정 : UX/UI 설계 / / / / /

- 정은경 : UX/UI 설계 / React 컴포넌트 개발 / 3D 모델링, Three.js FBX 렌더링

**Backend & CI/CD**

- 성제현: API 제작 / Redis 캐싱 / 더미데이터 생성 / jenkins / DB 구축

- 이종윤: API 제작 / Redis 캐싱 / ERD 설계 / EMR 서버 제작 / deploy / ssl / nginx

**IoT**

- 김성현 : Mobile / NFC 태깅 / 앱 연동 / 근무표 생성 알고리즘 
