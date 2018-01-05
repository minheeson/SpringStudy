## Spring MVC

### 스프링 MVC 개요

<img src="https://github.com/minheeson/SpringStudy/blob/master/screenshots/11_mvc.png" width=450/>

1. 서블릿에게 URL로 접근하여 해당 정보를 요청
   - DispatcherServlet이 클라이언트의 요청을 제일 먼저 받음 
2. 핸들러 매핑에 해당 요청을 매핑한 컨트롤러가 있는지 검색 요청
3. 컨트롤러에 처리 요청
4. 컨트롤러가 클라이언트의 요청을 처리하고, 결과를 보여줄 View의 이름을 리턴
5. 컨트롤러에서 보내온 View의 이름을 서블릿이 viewResolver에 보내 해당 View를 검색
6. 검색한 결과를 View에 보냄
7. View의 처리 결과를 DispatcherServlet에 보냄 

### 스프링 MVC 구조

<img src="https://github.com/minheeson/SpringStudy/blob/master/screenshots/11_springmvc.png" width=370/>

## Controller

### Controller 클래스 제작

<img src="https://github.com/minheeson/SpringStudy/blob/master/screenshots/12_controllers.png" width=300/>

```java
@Controller
public class MyController {

	@RequestMapping("/view")
	public String view() {
            // @RequestMapping("/view") :: 요청 경로 지정 
            // "/view"로 들어오면 view(리턴값)페이지로 찾아가라
            /* 요청 처리 메소드 구현 */
            // return "view" :: 뷰 페이지 이름 
            return "view";
	}
```

### 뷰에 데이터 전달

- 컨트롤러에서 로직 수행 후 뷰페이지를 반환하는데, 이때 __뷰에서 사용하게 될 데이터를 객체로 전달할 수 있음__

- __Model 클래스__ 를 이용한 데이터 전달

  ```java
  @RequestMapping("/content/contentView")
  	public String contentView(Model model) {
  		// Model :: 컨트롤러에서 데이터를 줄 때, 데이터를 갖고 있는 객체

  		model.addAttribute("id", "minimini");
  		// 컨트롤러에서 뷰쪽으로 contentView를 찾으러 갈때, model도 데리고 감
  		return "content/contentView";
  	}
  ```

- __ModelAndView 클래스__ 를 이용한 데이터 전달  

  ```java
  @RequestMapping("/modeAndView/modelView")
  	public ModelAndView modelAndView() {
  		ModelAndView mv = new ModelAndView();
  		mv.addObject("id", "abcdes");
  		mv.setViewName("/modelAndView/modelView);");
  		return mv;
  	}
  ```

### 클래스에 @RequestMapping 적용

- 메소드에 @RequestMapping 을 적용하여 요청 경로를 획득함

- @RequestMapping 어노테이션을 클래스에도 적용할 수 있음 

  ```java
  @Controller
  @RequestMapping("/board")
  public class HomeController {
    
      @RequestMapping("/write")
      public String write(Model model) {
          model.addAttribute("id", 30);
          return "board/write";
      }
  }
  ```

  - 클래스 (/board) + 메소드 (/write) = 조합된 요청 경로 (/board/write)

