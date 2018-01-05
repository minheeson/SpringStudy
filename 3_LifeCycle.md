## LifeCyle

### Container LifeCycle

<img src="https://github.com/minheeson/SpringStudy/blob/master/screenshots/7_containerLifecycle.png" width=500/>

### Spring Bean LifeCycle

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

### Spring Bean Scope

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