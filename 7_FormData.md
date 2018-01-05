## Form 데이터

### HttpServletRequest 클래스

- HttpServletRequest 클래스를 이용해서 데이터를 전송 

  ```java
  @RequestMapping("/member/memberView")
  public String viewMember(HttpServletRequest httpServleRequest, Model model) {
            String id = httpServleRequest.getParameter("id");
            String pw = httpServleRequest.getParameter("pw");

            model.addAttribute("id", id);
            model.addAttribute("pw", pw);

            return "member/memberView";
  }
  ```

  - HttpServletRequest : 사용자가 요청한 데이터 객체
  - Model : 데이터를 담는 객체
  - 즉, __HttpServletRequest__ 는 데이터를 받을 때, __Model__ 은 데이터를 보낼 때 사용 

### @RequesetParam

- @RequestParam 어노테이션을 이용해서 데이터를 전송 

  ```java
   @RequestMapping("/join/formOk")
  public String join(@RequestParam("name") String name, @RequestParam("id") String id, @RequestParam("pw") String pw, @RequestParam("email") String email, Model model) {
  	
  	 Member member = new Member();
  	 member.setName(name);
  	 member.setId(id);
  	 member.setPw(pw);
  	 member.setEmail(email);
  	
  	 model.addAttribute("member", member);
  	
  	 return "join/formOk";
  }
  ```

  - 문제점 : 파라미터로 데이터의 개수만큼 받아야함 (코드양이 많아짐)

  - 개선 방법 : __데이터(커맨드) 객체 이용__

    ```java
    @RequestMapping("/join/formOk")
    public String join(Member member) {

    	return "join/formOk";
    }
    ```

### @PathVariable

- @PathVariable 어노테이션을 이용하면 __경로에 변수를 넣어__ 요청메소드에서 파라미터로 이용할 수 있음 

  ```java
  @RequestMapping("/student/{studentId}")
  public String getStudent(@PathVariable String studentId, Model model) {
  	model.addAttribute("studentId", studentId);
  	// studentId 는 경로에서 할당되는 변수 
  	return "student/studentView";
  }
  ```

