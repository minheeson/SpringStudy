package com.javalec.spring_ex_12;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class SampleRequestMapping {

	@RequestMapping("/view")
	public String view() {
		// board + /view = "/board/view"  
		return "/board/view";
	}
}
