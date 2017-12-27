## Spring

- #### Framework

  - 특정한 목적에 맞게 프로그래밍을 쉽게 하기 위한 약속 

- #### Spring 

  - JAVA 언어를 기반으로, 다양한 어플리케이션을 제작하기 위한 약속된 프로그래밍 틀 
  - 코드의 경량화
  - 개발 중 테스트가 쉬움 



## DI(Dependency Injection) 와 IOC 컨테이너 

<img src="https://github.com/minheeson/SpringStudy/blob/master/screenshots/2_DI.png" width=500/>

- #### A 객체에서 B/C 객체 이용하는 방법

  - 방법 1) A 객체가 B/C 객체를 new 생성자를 통해 직접 생성 
  - __방법 2) 외부에서 생성된 객체를 setter() 나 생성자를 통해 사용하는 방법__
    - 외부(IOC 컨테이너)에서 생성된 B/C 객체를 조립(주입)시켜 setter() 나 생성자를 통해 사용
    - DI 방식