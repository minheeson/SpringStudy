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

    ```java
    MyCalculator myCalculator = new MyCalculator();
    myCalculator.setCalculator(new Calculator());

    myCalculator.setFirstNum(10);
    myCalculator.setSecondNum(2);
    		
    myCalculator.add();
    ```

  - 방법 2) __외부에서 생성된 객체를 setter() 나 생성자를 통해__ 사용하는 방법 (_DI_)

- #### IOC 컨테이너

  <img src="https://github.com/minheeson/SpringStudy/blob/master/screenshots/2_IOC.png" width=250/>

  - 외부(IOC 컨테이너)에서 생성된 B/C 객체를 조립(주입)시켜 setter() 나 생성자를 통해 사용
  - 각각의 객체는 인터페이스를 통해 부품화 
  - 결국, 스프링은 부품을 __생성하고 조립하는 라이브러리의 집합체라__ 할 수 있음 

## Spring property

- #### Spring Property Configuration 

  ``` java
  // xml파일 위치 지정
  String configLocation = "classpath:applicationCTX.xml";
  		
  // IOC 컨테이너 생성 (부품들 생성) 
  // 지정한 위치를 참고하여 설정파일을 얻어옴 
  AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation);
  		
  // 스프링 컨테이너에서 컴포넌트(bean) 가져옴
  // 외부에서 얻어옴 (=주입)
  MyInfo myInfo = ctx.getBean("myInfo", MyInfo.class);
  		
  myInfo.getInfo();
  ctx.close();
  ```

  ```xml
  <!-- ".BMICalculator"클래스를 bmiCalculator라는 id를 지정해서 객체(bean) 생성 -->
  <bean id="bmiCalculator" class="spring_ex_4.BMICalculator">
    	<!-- 클래스 내의 필드의 값을 설정 -->
    	<!-- 기초 데이터 -->
  	<property name="lowWeight" value="18.5" />
  	<property name="normal" value="23" />
  	<property name="overWeight" value="25" />
  	<property name="obesity" value="30" />
  </bean>

  <!-- ".MyInfo"클래스를 myInfo라는 id를 지정해서 객체(bean) 생성 -->
  <bean id="myInfo" class="spring_ex_4.MyInfo">
  	<property name="name" value="홍길동" />
  	<property name="height" value="187" />
  	<property name="weight" value="84" />
    	<!--  List 타입 -->
  	<property name="hobby">
  		<list>
  			<value>수영</value>
  			<value>요리</value>
  			<value>독서</value>
  		</list>
  	</property>
    	<!-- 다른 빈 객체 참조 -->
    	<!-- bean "bmiCalculator"를 참조함 -->
  	<property name="bmiCalculator">
  		<ref bean="bmiCalculator" />
  	</property>
  </bean>
  ```

  - xml 파일을 쓰기 위해서는 반드시 클래스에서 setter() 만들어줘야 가능 
  - 생성, 조립 모두 컨테이너의 역할 

## DI 활용

- #### DI (Dependency Injection) :: 의존 주입 

  <img src="https://github.com/minheeson/SpringStudy/blob/master/screenshots/5_DI.png" width=500/>

- DI 장점 :: JAVA 파일의 수정 없이 __스프링 설정 파일만을 수정하여__ 부품들을 생성/조립할 수 있음

  ```java
  AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
  Pencil pencil = ctx.getBean("pencil", Pencil.class);
  pencil.use();
  		
  ctx.close();
  ```

  ```xml
  <bean id="pencil" class="spring_ex_5.Pencil6B"/>
  <!-- <bean id="pencil" class="spring_ex_5.Pencil4B"/> -->
  <!-- <bean id="pencil" class="spring_ex_5.Pencil6BWithEraser"/> -->
  <!-- applicationCTX.xml 에서 클래스만 바꿔주고 있음 -->
  ```

  ​

## DI 설정 방법

- #### XML 파일을 이용한 DI 설정 방법

  ```xml
  <bean id="student1" class="spring_ex_6_1.Student">
  	<!-- 생성자 설정(기초 데이터) -->
  	<constructor-arg value="홍길동" />
  	<constructor-arg value="10" />
    
  	<!-- 생성자 설정(객체 데이터) -->
  	<constructor-arg>
  		<list>
  			<value>수영</value>
  			<value>요리</value>
  		</list>
  	</constructor-arg>

  	<!-- setter() 설정(property) -->
  	<property name="height">
  		<value>187</value>
  	</property>

  	<property name="weight" value="84" />
  </bean>
  ```

  ```xml
  <bean id="family" class="spring_ex_6_1.Family" c:papaName="홍아빠" c:mamiName="홍엄마" p:sisterName="홍누나">
  	<property name="brotherName" value="홍오빠" />
  </bean>
  ```

  - c 네임스페이스 : construct
  - p 네임스페이스 : property 

- #### JAVA를 이용한 DI 설정 방법

  ```java
  @Configuration
  public class ApplicationConfig {
  	// 컨테이너 역할

  	@Bean
  	public Student student1() {
  		ArrayList<String> hobby = new ArrayList<String>();
  		hobby.add("수영");
  		hobby.add("요리");

  		Student student = new Student("손민희");
  		student.setAge(10);
  		student.setHobby(hobby);
  		return student;
  	}
  }
  ```

  - @Configuration, @Bean 어노테이션 이용 

- #### XML과 JAVA를 같이 사용 

  - XML 파일에 JAVA 파일을 포함시켜 사용하는 방법

    ```xml
    <!-- annotation 이 들어간 config를 사용하겠다 명시 -->
    <context:annotation-config />
    <bean class ="spring_ex_6_3.ApplicationConfig" />

    ... 
    ```

  - JAVA 파일에 XML 파일을 포함시켜 사용하는 방법 

    ```java
    @Configuration
    @ImportResource("classpath:applicationCTX.xml")
    public class ApplicationConfig {
      ...
    }
    ```

## LifeCyle 

- #### Container LifeCycle

  <img src="https://github.com/minheeson/SpringStudy/blob/master/screenshots/7_containerLifecycle.png" width=500/>

- #### Spring Bean LifeCycle

  - 방법 1) InitializingBean, DisposableBean Interface 이용 

    ```java
    public class Student implements InitializingBean, DisposableBean{

    	/* ... */

    	@Override
    	public void afterPropertiesSet() throws Exception {
    		// 빈 초기화 과정에서 호출 (생성되는 시점)
    		System.out.println("afterPropertiesSet()");
    	}

    	@Override
    	public void destroy() throws Exception {
    		// 빈 소멸 과정에서 호출
    		// ctx.close()는 컨테이너가 소멸하는 단계, 컨테이너가 소멸하면 빈은 자동 소멸됨
    		System.out.println("destroy()");
    	}
    }
    ```

  - 방법 2) 어노테이션 이용 

    ```java
    @PostConstruct
    public void initMethod() { // 빈 초기화 과정에서 호출
    	System.out.println("initMethod()");	
    }
    	
    @PreDestroy
    public void destroyMethod() { // 빈 소멸 과정에서 호출
    	System.out.println("destroyMethod()");
    }
    ```

- #### Spring Bean Scope

  ```xml
  <bean id ="student" class = "spring_ex_7_3.Student" scope ="singleton">
    <constructor-arg value = "홍길순" />
    <constructor-arg value = "30" />
  </bean>
  ```

  ```java
  Student student1 = ctx.getBean("student", Student.class);
  Student student2 = ctx.getBean("student", Student.class);
  student2.setName("손민희");
  student2.setAge(100);

  if(student1.equals(student2)) {
    System.out.println("student1 == student2"); 
  } else {
    System.out.println("stuent1 != student2");
  }

  // student1 == student2 
  // config 파일에서 빈이 생성된것이기 때문에
  // student1과 student2의 주소값은 동일함. 따라서, 동일한 객체~!
  ```

  - bean 태그의 scope 속성 값 
    - singleton : 스프링 컨테이너에 의해 한 개의 빈 객체만 생성 (기본 값)
    - prototype : 빈을 사용할 때 마다 객체를 생성 
    - request : HTTP 요청마다 빈 객체 생성
    - session : HTTP 세션마다 빈 객체 생성 