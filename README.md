## Spring

- #### Framework

  - 특정한 목적에 맞게 프로그래밍을 쉽게 하기 위한 약속 

- #### Spring 

  - JAVA 언어를 기반으로, 다양한 어플리케이션을 제작하기 위한 약속된 프로그래밍 틀 
  - 코드의 경량화
  - 개발 중 테스트가 쉬움 



## DI(Dependency Injection) 와 IOC 컨테이너 

- #### A 객체에서 B/C 객체 이용하는 방법

  <img src="https://github.com/minheeson/SpringStudy/blob/master/screenshots/2_DI.png" width=500/>

  - 방법 1) A 객체가 B/C 객체를 new 생성자를 통해 직접 생성 

    - ```java
      MyCalculator myCalculator = new MyCalculator();
      myCalculator.setCalculator(new Calculator());

      myCalculator.setFirstNum(10);
      myCalculator.setSecondNum(2);
      		
      myCalculator.add();
      ```

  - 방법 2) __외부에서 생성된 객체를 setter() 나 생성자를 통해__ 사용하는 방법 (_DI_)

    - ```java
      String configLocation = "classpath:applicationCTX.xml";
      AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation);
      MyCalculator myCalculator = ctx.getBean("myCalculator", MyCalculator.class);

      myCalculator.add();
      ```

    - ```xml
      <beans xmlns="http://www.springframework.org/schema/beans"
      	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

      	<bean id="calculator" class="springProject_ex.Calculator" />

      	<bean id="myCalculator" class="springProject_ex.MyCalculator">
      		<property name="calculator">
      			<ref bean="calculator" />
      		</property>
      		<property name="firstNum" value="10" />
      		<property name="secondNum" value="2" />

      	</bean>

      </beans>
      ```

- #### IOC 컨테이너

  <img src="https://github.com/minheeson/SpringStudy/blob/master/screenshots/2_IOC.png" width=250/>

  - 외부(IOC 컨테이너)에서 생성된 B/C 객체를 조립(주입)시켜 setter() 나 생성자를 통해 사용
  - 각각의 객체는 인터페이스를 통해 부품화 
  - 결국, 스프링은 부품을 __생성하고 조립하는 라이브러리의 집합체라__ 할 수 있음 