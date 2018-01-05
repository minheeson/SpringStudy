## @RequestMapping 파라미터

### @RequestMapping 에서 GET 방식과 POST 방식

```jsp
<form action="student" method="get">
	student ID : <input type="text" name="id"> 
  	<br /> <input type="submit" value="전송">
</form>  
```

```java
@RequestMapping(method = RequestMethod.GET, value = "/student")
public String goStudent(HttpServletRequest httpServletRequest, Model model) {
    String id = httpServletRequest.getParameter("id");
    model.addAttribute("studentId", id);
  
    return "student/studentId";
}
```

- __GET__ : URL에 데이터를 실어서 보내는 방식 따라서, URL에 정보가 드러남 
- __POST__ : 파일 헤더에 데이터를 실어서 보내는 방식 따라서, URL에 정보가 드러나지 않음 
- default 는 GET 방식
  - GET 이라고 명시되어 있으면, POST 방식으로 전송한건 오류남 (405 에러)

### @ModelAttribute

- @ModelAttribute 어노테이션을 이용해 __커맨드 객체의 이름을 변경할 수 있음__

  ```java
  @RequestMapping("/studentView")
  public String studentView(@ModelAttribute("studentInfo") StudentInformation studentInformation) {
  	return "studentView";
  }
  ```

  ```jsp
  <body>
  	이름 : ${studentInfo.name} <br />
    	나이 : ${studentInfo.age} <br />
  </body>
  ```

### Redirect

- 다른 페이지로 이동할 때 사용

  ```java
  @RequestMapping("/studentConfirm")
  public String studentRedirect(HttpServletRequest httpServletRequest, Model model) {
      String id = httpServletRequest.getParameter("id");
      if(id.equals("abc"))
      	return "redirect:studentOk";
      	
      return "redirect:studentNg";
  }
  ```

