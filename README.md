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

## 외부 파일을 이용한 설정 

- #### Environment 객체를 이용해서 스프링 빈 설정 

  <img src="https://github.com/minheeson/SpringStudy/blob/master/screenshots/8_envionment.png" width=600 />

  ```java
  ConfigurableApplicationContext ctx = new GenericXmlApplicationContext();
  ConfigurableEnvironment env = ctx.getEnvironment();
  MutablePropertySources propertySources = env.getPropertySources();
  		
  try {
      propertySources.addLast(new ResourcePropertySource("classpath:admin.properties"));
      System.out.println( env.getProperty("admin.id") );
      System.out.println( env.getProperty("admin.pw") );
  } catch (IOException e) {}
    
  GenericXmlApplicationContext gCtx = (GenericXmlApplicationContext)ctx;
  gCtx.load("applicationCTX.xml");
  gCtx.refresh();
  		
  AdminConnection adminConnection = gCtx.getBean("adminConnection", AdminConnection.class);
  System.out.println("admin ID : " + adminConnection.getAdminId());
  System.out.println("amdin PW : " + adminConnection.getAdminPw());
  		
  gCtx.close();
  ctx.close();
  ```

- #### 프로퍼티 파일을 이용한 설정 

  - XML 파일에 프로퍼티 파일을 명시 

    ```xml
    <context:property-placeholder
    		location="classpath:admin.properties, classpath:sub_admin.properties" />
    ```

  - JAVA 파일에 프로퍼티 파일을 명시 

    ```java
    @Configuration
    public class ApplicationConfig {

        // Value annotaion은 값을 바로 set 해줌
    	@Value("${admin.id}")
    	private String adminId;
    	@Value("${admin.pw}")
    	private String adminPw;
    	@Value("${sub_admin.id}")
    	private String sub_adminId;
    	@Value("${sub_admin.pw}")
    	private String sub_adminPw;
    	
    	@Bean
    	public static PropertySourcesPlaceholderConfigurer Properties() {
    		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
    		
    		Resource[] locations = new Resource[2];
    		locations[0] = new ClassPathResource("admin.properties");
    		locations[1] = new ClassPathResource("sub_admin.properties");
    		configurer.setLocations(locations);
    		
    		return configurer;
    	}
    	
    	@Bean
    	public AdminConnection adminConfig() {
    		AdminConnection adminConnection = new AdminConnection();
    		adminConnection.setAdminId(adminId);
    		adminConnection.setAdminPw(adminPw);
    		adminConnection.setSub_adminId(sub_adminId);
    		adminConnection.setSub_adminPw(sub_adminPw);
    		return adminConnection;
    	}
    	
    }
    ```

- #### 프로파일(profile) 속성을 이용한 설정 

  - 동일한 스프링 빈을 여러 개 만들어 놓고 상황(환경)에 따라서 적절한 스프링 빈 사용

  - XML 설정 파일을 이용하는 경우 

    ``` xml
    <beans ...
           profile = "run">
      <!-- profile namespace 추가 -->
      <bean id = "serverInfo" class = "spring_ex_8_3.ServerInfo">
        <property name="ipNum" value="213.186.229.29"/>
        <property name="portNum" value="80"/>
      </bean>

    </beans>
    ```

    ```java
    String config = "run";
    GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
    ctx.getEnvironment().setActiveProfiles(config);
    ctx.load("applicationCTX_dev.xml", "applicationCTX_run.xml");
    ```


-   JAVA 설정 파일을 이용하는 경우 

    ```java
    @Configuration
    @Profile("run")
    public class ApplicationConfigDev {
      @Bean
      public ServerInfo serverInfo() {
        ...
      }
    }
    ```


## AOP (Aspect Oriented Programming)

- #### AOP란

  - 공통 기능을 모든 모듈에 적용하기 위한 __상속의 한계__

    - JAVA에서는 다중 상속이 불가능
    - 핵심 기능 코드와 공통 기능 코드가 섞여 있어, 효율성이 떨어짐

  - __AOP__ : 핵심 기능과 공통 기능을 분리해 놓고, __공통기능을 필요로 하는 핵심 기능에 적용하는 방법__ 

    - _Aspect_ : 공통 기능
    - _Advice_ : Aspect의 기능 자체
    - _Jointpoint_ : Advice를 적용해야하는 부분 (메소드)
    - _Pointcut_ : Jointpoint의 부분으로 실제로 Advice가 적용된 부분
    - _Weaving_ : Advice를 핵심 기능에 적용하는 행위 

  - __proxy를 이용한__ AOP 구현 방법 

    <img src="https://github.com/minheeson/SpringStudy/blob/master/screenshots/9_aop.png" width=400 />

    1. 수행 할 Advice를 Proxy에 요청
    2. 핵심 기능 수행전에 사용할 공통 기능 수행
    3. 공통 기능 수행 후 핵심 기능의 로직을 수행
    4. 핵심 기능 수행 후 다시 Proxy로 가서 공통 기능 수행 

- #### XML 기반의 AOP 구현

  1. 의존 설정 (pom.xml)

     ```xml
     <!-- AOP -->
     <dependency>
     	<groupId>org.aspectj</groupId>
     	<artifactId>aspectjweaver</artifactId>
     	<version>1.7.4</version>
     </dependency>
     ```

  2. 공통 기능 클래스 제작 :: Advice 역할 클래스

     ```java
     public class LogAop {

     	public Object loggerAop(ProceedingJoinPoint joinpoint) throws Throwable {
     		String signatureStr = joinpoint.getSignature().toShortString();
     		System.out.println(signatureStr + " is start.");
     		long st = System.currentTimeMillis();

           	// 핵심 기능 전에 공통 기능 수행  
     		try {
               	// 핵심 기능 수행  
     			Object obj = joinpoint.proceed();
     			return obj;
     		} finally {
                 // 핵심 기능 후에 공통 기능 수행  
     			long et = System.currentTimeMillis();
     			System.out.println(signatureStr + " is finished.");
     			System.out.println(signatureStr + " 경과시간 : " +(et - st));
     		}
     	}

     }
     ```

  3. XML 설정 파일에 Aspect 설정 

     ```xml
     <bean id="logAop" class="spring_ex_9.LogAop" />

     <aop:config>
     	<aop:aspect id="logger" ref="logAop">
     		<aop:pointcut expression="within(spring_ex_9.*)" id="publicM" />
     		<aop:around method="loggerAop" pointcut-ref="publicM" />
     	</aop:aspect>
     </aop:config>
     ```

- #### Advice 종류

  - _aop:before_ : 메소드 실행 전에 advice 실행
  - _aop:after-returning_ : 정상적으로 메소드 실행 후에 advice 실행
  - _aop:after-throwing_ : 메소드 실행 중 exception 발생시 advice 실행
  - _aop:after_ : 메소드 실행 중 exception이 발생해도 advice 실행 
  - _aop:around_ : 메소드 실행 전/후 및 exception 발생시 advice 실행  

- #### @Aspect 를 이용한 AOP 구현

  1. 의존 설정 (pom.xml) : 위와 동일

  2. __@Aspect 어노테이션을 이용한__ Aspect 클래스 제작

     ```java
     @Aspect
     public class LogAop {
       
       @Pointcut("within(spring_ex_9.*)")
       private void pointcutMethod(){
       }
       
       @Around("pointcutMethod()")
       public Object loggerAop(ProceedingJoinPoint joinpoint) throws Throwable {
     		String signatureStr = joinpoint.getSignature().toShortString();
     		System.out.println(signatureStr + " is start.");
     		long st = System.currentTimeMillis();
     	
           	// 핵심 기능 전에 공통 기능 수행  
     		try {
               	// 핵심 기능 수행  
     			Object obj = joinpoint.proceed();
     			return obj;
     		} finally {
                 // 핵심 기능 후에 공통 기능 수행  
     			long et = System.currentTimeMillis();
     			System.out.println(signatureStr + " is finished.");
     			System.out.println(signatureStr + " 경과시간 : " +(et - st));
     		}
     	}
       
       @Before("within(spring_ex_9.*)")
       public void beforeAdvice(){
       }
       
     }
     ```

  3. XML 파일에 __aop:aspectj-autoproxy__ 설정 

     ```xml
     <aop:aspectj-autoproxy/>
     <bean id="logAop" class="spring_ex_9.LogAop" />

     ...
     ```

- #### AspectJ Pointcut 표현식

   