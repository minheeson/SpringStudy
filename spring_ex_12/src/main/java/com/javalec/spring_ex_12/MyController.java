package com.javalec.spring_ex_12;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {

	@RequestMapping("/view")
	public String view() {
		// "/view"로 들어오면 view(리턴값)페이지로 찾아가라

		return "view";
	}

	@RequestMapping("/content/contentView")
	public String contentView(Model model) {
		// Model :: 컨트롤러에서 데이터를 줄 때, 데이터를 갖고 있는 객체

		model.addAttribute("id", "minimini");
		// 컨트롤러에서 뷰쪽으로 contentView를 찾으러 갈때, model도 데리고 감
		return "content/contentView";
	}

	@RequestMapping("/model/modelEx")
	public String modelEx(Model model) {
		model.addAttribute("data", "13");

		return "/model/modelEx";
	}

	@RequestMapping("/modeAndView/modelView")
	public ModelAndView modelAndView() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("id", "abcdes");
		mv.setViewName("/modelAndView/modelView);");
		return mv;
	}

}
