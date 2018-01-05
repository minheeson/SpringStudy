## DI 활용

### DI (Dependency Injection) :: 의존 주입

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

### XML 파일을 이용한 DI 설정 방법

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

### JAVA를 이용한 DI 설정 방법

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

### XML과 JAVA를 같이 사용

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

