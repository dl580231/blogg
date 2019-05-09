package com.nuc.a4q.web.PersonInfo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("personInfo")
public class PresonInfoMappingController {
	@RequestMapping("demo")
	public String demo() {
		return "demo";
	}
}
