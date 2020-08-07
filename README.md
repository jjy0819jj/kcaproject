### **SpringFramework/Mybatis/Oracle 기반 웹사이트 제작**

------

#### **프로젝트 설명**

Spring Framework(Maven), Mybatis Framework, Oracle 기반으로 구현한 원페이지(pageless) 기업 소개 및 채용 사이트 입니다.

<br/>

<br/>

### **시작하기(Getting Started)**

------

#### **1. 개발환경 구축**

![getting_started.png](./img/getting_started.png)

<br/>

(1) 개발자도구: Spring Tool Suite(sts-3.9.12.RELEASE), SQLDeveloper

(2) 웹브라우저(Debug): Chrome

(3) 웹서버(Frontend): HTML5, CSS3, Bootstrap4

(4) 웹서버(Backend): JSP(Java Servlet Page), Javascript(jQuery)

(5) 웹 어플리케이션 서버: apache-tomcat-8.5.53(8.5v)

(6) 데이터베이스(DB): OracleXE112_Win64

(7) 프레임워크: Spring(Web), mybatis(DB)

<br/>

#### 2. Spring Libraries(TeamProject\pom.xml 참고)

![image-20200807150848324](./img/image-20200807150848324.png)

<br/>

<br/>

### **개발하기**

------

#### **1. 데이터베이스(TeamProject\src\main\webapp\resources\SQL 내 파일 참고)**

![image-20200806143858579](./img/image-20200806143858579.png)

<br/>

(1) Notice(공지사항 DB)

![image-20200806143936363](./img/image-20200806143936363.png)

<br/>

(2) Application(입사지원 DB)

![image-20200806144011351](./img/image-20200806144011351.png)

<br/>

(3) Member(전체 회원 DB)

![image-20200806143953541](./img/image-20200806143953541.png)

<br/>

(4) Board(Q&A/문의하기 DB)

![image-20200806144002501](./img/image-20200806144002501.png)

<br/>

<br/>

#### **2. 사이트 구조도**

![diagram_v1](./img/diagram_v1.jpg)

<br/>

<br/>

#### **3. 파일 상세 설명**

(1) JAVA 파일(TeamProject\src\main\java 내 파일 참고)

![image-20200807150426806.png](./img/image-20200807150426806.png)

<br/>

(2) JSP 파일-1(TeamProject\src\main\webapp\WEB-INF 내 파일 참고)

![image-20200807154359236](./img/image-20200807154359236.png)

<br/>

(3) JSP 파일-2(TeamProject\src\main\webapp\WEB-INF 내 파일 참고)

![image-20200807155549983](./img/image-20200807155549983.png)

<br/>

(4) 기타 파일

▶ **TeamProject\src\main\webapp\WEB-INF\web.xml**:

=> servlet-context.xml와 root-context.xml을 사용해서 웹 전체 흐름을 제어할 것을 정의

<br/>

▶ **TeamProject\src\main\webapp\WEB-INF\spring\appServlet\servlet-context.xml**:

=> MVC Controller와 관련된 설정

<br/>

▶ **TeamProject\src\main\webapp\WEB-INF\spring\root-context.xml**:

=> 웹 전체에서 사용하는 DataSource(DB접속정보), mybatis 연동 설정

<br/>

▶ TeamProject\src\main\resources\mybatis 내 파일:

=> **db.properties**: root-context.xml에서 정의한 내용을 통해 DB 접속정보 설정

=> **mybatis-config.xml**: DB에 데이터를 전달할 DTO 클래스와 SQL문을 구동시킬 mapping 파일을 정의

=> **OOO-mapping.xml**: mybatis framework를 사용해 구동시킬 SQL문을 정의

<br/>

▶ **Servers\Tomcat v8.5 Server at localhost-config\web.xml & server.xml**:

=> 해당 프로젝트를 서버에서 구동 시 처음으로 나타날 페이지를 지정(현재 index2.jsp)

<br/>

<br/>



#### **4. 구현 화면**

(1) 사이트 MAIN 페이지(index)

![image-20200807161444169](./img/image-20200807161444169.png)

<br/>

(2) LOGIN 페이지

![image-20200807161519299](./img/image-20200807161519299.png)

<br/>

(3) 관리자 페이지

![image-20200807161545553](./img/image-20200807161545553.png)

▶ 사이드 메뉴 및 아이콘 클릭을 통해 해당 DB 수정/삭제 페이지로 이동하며 실시간으로 DB 내용이 업데이트 됩니다.

<br/>

(4) 공지사항 페이지

![image-20200807161710289](./img/image-20200807161710289.png)

▶ 원페이지로 이동하며 관리자 페이지에서 작성한 공지사항이 업데이트 됩니다.

<br/>

(5) 입사지원 페이지

![image-20200807161812332](./img/image-20200807161812332.png)

▶ 상세 모집요강 및 입사지원서 다운로드 후 파일을 업로드하면 해당 사용자 Mypage, 관리자 페이지에서 새 이력서를 확인할 수 있습니다.

<br/>

(6) Q&A 페이지

![image-20200807161916142](./img/image-20200807161916142.png)

▶ CONTACT US 하단 메뉴 중 '문의하기' 페이지를 통해 문의한 내용이 실시간으로 업데이트 됩니다. 관리자 페이지에서 해당 문의사항에 대한 답변을 작성할 수 있습니다.

<br/>