## AOP (Aspect Oriented Programming)

### AOP란

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

### XML 기반의 AOP 구현

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

### Advice 종류

- _aop:before_ : 메소드 실행 전에 advice 실행
- _aop:after-returning_ : 정상적으로 메소드 실행 후에 advice 실행
- _aop:after-throwing_ : 메소드 실행 중 exception 발생시 advice 실행
- _aop:after_ : 메소드 실행 중 exception이 발생해도 advice 실행 
- _aop:around_ : 메소드 실행 전/후 및 exception 발생시 advice 실행  

### @Aspect 를 이용한 AOP 구현

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

### AspectJ Pointcut 표현식

<img src="https://github.com/minheeson/SpringStudy/blob/master/screenshots/10_aop.png" width=150/>

- excution

  ```java
  @Pointcut("excution(public void get*(..))") // public void 인 모든 get 메소드
  @Pointcut("excution(*spring_ex_9.*.*())") // spring_ex_9 패키지에 파라미터가 없는 모든 메소드
  @Pointcut("excution(*spring_ex_9..*.*())") // spring_ex_9 패키지 & spring_ex_9 하위 패키지에 파라미터가 없는 모든 메소드 
  @Pointcut("excution(*spring_ex_9.Worker.*())") // spring_ex_9.Worker 모든 메소드 
  ```

- within

  ```java
  @Pointcut("within(spring_ex_9.*)") // spring_ex_9 패키지 안에 있는 모든 메소드
  @Pointcut("within(spring_ex_9..*)") // spring_ex_9 패키지 및 하위 패키지 안에 있는 모든 메소드
  @Pointcut("within(spring_ex_9.Worker)") // spring_ex_9.Worker 모든 메소드 
  ```

- bean

  ```java
  @Pointcut("bean(student)") // student 빈에만 적용
  @Pointcut("bean(*ker)") // ~ker로 끝나는 빈에만 적용 
  ```

