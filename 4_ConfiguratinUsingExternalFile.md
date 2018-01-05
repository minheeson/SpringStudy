## 외부 파일을 이용한 설정

### Environment 객체를 이용해서 스프링 빈 설정

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

### 프로퍼티 파일을 이용한 설정

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

### 프로파일(profile) 속성을 이용한 설정

- 동일한 스프링 빈을 여러 개 만들어 놓고 상황(환경)에 따라서 적절한 스프링 빈 사용

- XML 설정 파일을 이용하는 경우 

  ```xml
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

- JAVA 설정 파일을 이용하는 경우 

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

