# 가족어플 허니비 프로젝트 (Family app HoneyBee project)


## Period

 > 2021.04-2021.11

## 📝 Project History 📝
##### You can see more about the project. ->  <a href="https://www.notion.so/daram-daram/wetube-note-5ce971e7d82c4f0fa62f07a469e6a640"><img src="https://img.shields.io/badge/Notion-000000?style=flat-square&logo=Notion&logoColor=white&link=https://www.notion.so/daram-daram/wetube-note-5ce971e7d82c4f0fa62f07a469e6a640"/>
## Explanation
  - 가족만을 위한 패밀리 네트워킹 시스템인 가족 소통어플을 만든다. 
  - 소통 어플의 전반적인 기능 (채팅, 스토리) 을 만들고,가족 어플만의 퀘스트 기능을 추가해 가족간의 유대감을 키우도록 만들었다.
  - Android Studio를 사용하였고, PHP로 Android Studio와 DB를 연결해주고, Java 언어를 사용해서 만들었다.

## Teck Stack
 <p align="center">
     <img src="https://img.shields.io/badge/AndroidStudio-339933?style=flat-square&logo=AndroidStudio&logoColor=white"/></a>&nbsp
     <img src="https://img.shields.io/badge/Java-A86454?style=flat-square&logo=Java&logoColor=white"/></a>&nbsp
     <img src="https://img.shields.io/badge/MySQL-CC6699?style=flat-square&logo=MySQL&logoColor=white"/></a>&nbsp
     <img src="https://img.shields.io/badge/PHP-47A248?style=flat-square&logo=PHP&logoColor=white"/></a>&nbsp
     <img src="https://img.shields.io/badge/APACHE-8DD6F9?style=flat-square&logo=APACHE&logoColor=white"/></a>&nbsp
     
## About Project 

### 개발 환경 
  |구분|내용|
  |--|---|
  |OS|Window10|
  |Development Tool|Android studio 4.2.5|
  |Language|Java|
  |Library|volleyplus,OKHttp,Glide|
  |Server|Localhost via UNIX socket(Dothome제공)|
  |Server Type|MySQL|
  |Server Version|5.7.34 - MySQL Community Server(GPL)|
  |Web Server|Apache/2.2.15(CentOS)|
  |API|Google Map API|

### 시스템 구조도
<img src="https://user-images.githubusercontent.com/60298173/165788297-db9cce76-a8c9-4a12-b9a7-ae855f99083e.png" >

### 데이터베이스 구조도(ERD)
<img src="https://user-images.githubusercontent.com/60298173/165788388-d9b5ea93-5c1c-4735-a0c6-71d450fd7398.png" >

## Preview

### 로그인 / 회원가입 화면

<img src="https://user-images.githubusercontent.com/60298173/165788477-f4b982f7-a15a-4ad5-8070-fd35df4d9a76.png" width="1200" height="400">

#### 1. 로그인 화면
    > 맨 처음 화면으로 로그인 화면이 작동된다. 새 계정 만들기를 누르면 회원가입을 할 수 있게 구현했다.
#### 2. 회원가입 화면
    > 새 계정 만들기를 누르면 DB에 회원정보가 저장되고 회원가입이 완료된다.
#### 3. 코드 발급 및 로그인
    > 코드 발급 버튼을 통해서 패밀리코드를 발급한다.
    > 새 계정 만들기를 누르면 다시 로그인 화면으로 넘어와서 로그인 버튼을 누른 후 로그인 한다.

### 메인 화면

<img src="https://user-images.githubusercontent.com/60298173/165790620-b33fd425-7fba-4f71-9055-9cf9411a2399.png">

#### 1. 전체 메인 화면
    > 로그인 버튼 클릭 시 메인 화면으로 넘어 간다.
#### 2. 퀘스트 레벨 화면
    > 퀘스트 완료 시 경험치에 따라 레벨을 올려주고 레벨이 올라가면 퀘스트요정 행복이가 성장하는 모습을 구현한다.
#### 3. 위치 화면
    > 위치 아이콘을 누르면 지도가 뜨면서 위치를 부여받아 현재 위치가 나오게 구현한다.
#### 4. 메인 공지사항 화면
    > 가족 중 누군가가 새로운 퀘스트 추가 시 메인 공지사항에 자동으로 띄워지게 구현한다.
    
### 퀘스트 화면

<img src="https://user-images.githubusercontent.com/60298173/165788548-007abb88-0dfe-4f85-8a5a-5acb11469b10.png">

#### 1. 메인 화면에서 퀘스트
    > 메인 화면의 퀘스트 목록 버튼 클릭 시 퀘스트 화면으로 이동한다.
#### 2. 퀘스트 화면
    > 저장되어 있는 퀘스트 목록을 보여준다.
    > 퀘스트 추가 + 버튼 클릭 시 퀘스트 추가 화면으로 이동한다.
#### 3-1. 퀘스트 추가
    > 가족끼리 하고싶은 퀘스트를 사용자가 입력하고 입력 완료 버튼을 클릭힌다.
#### 3-2. 퀘스트 완료
    > 방금 추가한 퀘스트가 성공적으로 들어간것을 확인 한다.
    > 퀘스트를 달성했다면, 완료 버튼을 누른다.
#### 3-3. 퀘스트 목록 화면
    > 완료 버튼을 누른 퀘스트는 퀘스트 목록에 사라지고 퀘스트 레벨은 +1씩 올라간다.

### 채팅 화면

<img src="https://user-images.githubusercontent.com/60298173/165793607-c9d0f0b8-1987-43e1-845d-be4ca4ef3fe4.png">

#### 1. 메인 화면에서 채팅
    > 메인 화면의 네비게이셔 바에서 채팅 아이콘을 클릭하면 채팅화면이 실행된다.
#### 2. 채팅 작성 화면
    > 메시지 입력 후 Chat을 클릭한다.
    > 두 개의 다른 아이디에서 동시에 입력해서 대화를 한다.
#### 3. 채팅 작성 후 서버로 넘김
    > 소켓을 사용하여 만들어 둔 서버들을 다른 IDE 프로그램에서 실행해둔다. 
    > 스레드가 실행되어 소켓 채팅 서버 실행된다.
#### 4. 채팅 화면
    > 서버가 정상적으로 작동되었을 때, 채팅이 동시 실행되는 것을 볼 수 있다.
    
### 스토리 화면

<img src="https://user-images.githubusercontent.com/60298173/165788639-db9962d7-3d4d-4bcb-b01c-3f9d49f1af74.png">

#### 1. 스토리 화면
    > 처음 스토리 입장 화면이다. 그림과 같이 가족들이 올린 사진과 글을 볼 수 있다.
    > + 버튼을 눌러 게시글 작성
#### 2. 게시글 작성 화면
    > 큰 + 버튼을 누르면 내 핸드폰에 있는 갤러리로 들어가서 사진을 선택한다.
#### 3. 사진 선택 / 글 작성 화면
    > 사진을 선택하고 글 작성을 한 것을 볼 수 있다.
    
### 캘린더 화면 

<img src="https://user-images.githubusercontent.com/60298173/165788714-b01946be-2a0c-40ca-99ce-6e76b3a09da2.png">

#### 1. 캘린더 화면
    > 원하는 날짜 누르면 날짜에 맞춰서 데이터가 보여지게 구현
#### 2. 일정 추가 화면
    > 캘린더에 + 버튼을 눌러서 날짜와 시간, 할 일을 쓰고 일정이 추가되도록 구현
#### 3. 일정 확인 화면
    > 추가하기 버튼을 눌러서 사용자가 지정했던 날짜를 터치하면 일정이 보여진다.
    
### 내 정보 화면 

<img src="https://user-images.githubusercontent.com/60298173/165788753-f0b0a49e-b9ee-472a-8365-3a6678b564fb.png">

#### 1. 메인화면에서 내정보 화면
    > 메인 화면에서 내 정보 아이콘을 클릭하면 내 정보 화면이 뜬다.
#### 2. 내정보 화면
    > 내 정보에 현재 로그인 되어있는 사용자의 이름과 상태 메시지가 나와 있다.
#### 3. 상태메시지 수정 화면
    > 내 정보 화면에서 수정을 누르면 상태메시지를 입력할 수 있느 화면이 뜬다.
#### 4. 상태메시지 화면
    > 확인 버튼 클릭 후 정상적으로 DB에서 상태메시지를 가져온 화면이다.
    
### 용돈 화면

<img src="https://user-images.githubusercontent.com/60298173/165788793-d9d60e46-8037-4943-9c9b-3b919ca81f37.png">

#### 1. 내정보에서 용돈 화면
    > 내 정보 화면에서 용돈을 클릭 후 용돈 화면으로 이동
#### 2-1. 용돈 화면
    > DB에서 money를 불러와 사용자에게 출력한다.(현재 돈이 없어 0으로 출력)
    > 충전 버튼을 클릭하면 돈을 충전할 수 있는 화면으로 넘어간다.
#### 2-2. 용돈 충전 화면
    > 처음에 계정을 생성할 때 만든 임의의 계좌가 화면에 띄워지며 본인이름과 충전할 금액을 입력하면 DB에 넣어 충전해준다.
#### 2-3. 용돈 확인 화면
    > 입력해준 돈이 정상적으로 잘 들어가 있는 모습이다.
#### 3. 용돈 보내기 화면
    > 내가 보내고 싶은 계좌번호를 입력하여 보내면 해당하는 계좌에 상대에 돈을 보내줄 수 있게 구현
    
----
    
### 시연 영상
  https://www.youtube.com/watch?v=Rrr14pqlhxE

