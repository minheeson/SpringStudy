## Form 데이터 값 검증

### Validator 를 이용한 검증

​	<img src="https://github.com/minheeson/SpringStudy/blob/master/screenshots/15_validator.png" width="500"/>

- 폼에서 전달 되는 데이터를 커맨드 객체에 담아 컨트롤 객체에 전달하는데, 이때 __커맨드 객체의 유효성 검사를 함__

- __Validator__ 인터페이스를 이용하는 방법은 __서버에서 검사하는 방법__

  ```java
  @RequestMapping("/student/create")
  public String studentCreate(@ModelAttribute("student") Student student, BindingResult result) {
  		String page = "createDonePage";

  		StudentValidator validator = new StudentValidator();
  		validator.validate(student, result);
  		if (result.hasErrors()) {
  			page = "createPage";
  		}

  		return page;
  }
  ```

  - BindingResult : 에러의 결과를 담는 객체 (validate 후의 결과)

  ```java
  public class StudentValidator implements Validator {

  	@Override
  	public boolean supports(Class<?> arg0) {
  		// TODO Auto-generated method stub
  		return Student.class.isAssignableFrom(arg0); // 검증할 객체의 클래스 타입 정보
  	}

  	@Override
  	public void validate(Object obj, Errors errors) {
        	// 유효성 검증 
  		// TODO Auto-generated method stub
  		Student student = (Student) obj;

  		String studentName = student.getName();
  		if (studentName == null || studentName.trim().isEmpty()) {
  			errors.rejectValue("name", "trouble");
  		}

  		int studentId = student.getId();
  		if (studentId == 0) {
  			errors.rejectValue("id", "trouble");
  		}
  	}
    
  }
  ```

  - __ValidationUtils 클래스__ 사용 : ValidationUtils.rejectEmptyOrWhitespace(errors, "name", "trouble"); 

### @Valid 와 @InitBinder

- Validator 인터페이스를 구현한 클래스를 만들어 직접 호출하는 방식이 아니라, 어노테이션을 이용한 방법 

  - 의존 추가

    ```xml
    <dependency>
    	<groupId>org.hibernate</groupId>
      	<artifactId>hibernate-validator</artifactId>
      	<version>4.2.0.Final</version>
    </dependency>
    ```

  - @InitBinder 추가

    ```java
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
    	binder.setValidator(new StudentValidator());
    }
    ```

  - @Valid 추가

    ```java
    @RequestMapping("/student/create")
    public String studentCreate(@ModelAttributes("student") @Valid Student student, BindingResult result) {
    	String page = "createDonePage";
    	
    	if(result.hasErrors()) {
    		page = "createPage";
    	}
    	
    	return page;
    }
    ```

    ​