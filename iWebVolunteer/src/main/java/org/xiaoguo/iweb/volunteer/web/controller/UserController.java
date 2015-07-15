package org.xiaoguo.iweb.volunteer.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.xiaoguo.iweb.volunteer.domain.LoginUser;

@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping()
	public String index(HttpSession session,Model model) {
		LoginUser user=(LoginUser)	session.getAttribute("cur_user");
		model.addAttribute("name", user.getUsername());
		return "index";
	}
	@RequestMapping("main")
	public String main(HttpSession session,Model model) {	
		return "redirect:/user";
	}

	@RequestMapping(value="/login",method=RequestMethod.GET)
	
	public String loginPage(Model model) {
		
		return "login";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	
	public String loginAction(HttpSession session,LoginUser user ) {
		session.setAttribute("cur_user", user);
		return "redirect:main";
	}

	@RequestMapping("/greeting")
	public String greeting(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}
}
