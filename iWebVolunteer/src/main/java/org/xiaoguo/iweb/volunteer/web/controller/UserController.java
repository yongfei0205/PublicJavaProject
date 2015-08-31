package org.xiaoguo.iweb.volunteer.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.xiaoguo.iweb.volunteer.domain.LoginUser;
import org.xiaoguo.iweb.volunteer.domain.Permission;
import org.xiaoguo.iweb.volunteer.domain.User;
import org.xiaoguo.iweb.volunteer.service.OService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private OService oservice;

	@RequestMapping()
	public String index(HttpSession session, Model model) {
		String id= session.getAttribute("cur_user").toString();
		User user=oservice.getUserById(id);
		model.addAttribute("user", user);	
		List<User> users = oservice.getUserListByName(user.getName());
		model.addAttribute("users", users);	
		Integer[] permissions = users.stream().map(User::getPermission).sorted((a,b)->a-b).toArray(Integer[]::new);
		model.addAttribute("permissions", permissions);	
		model.addAttribute("Permission", Permission.head);		
		return "index";
	}

	@RequestMapping("main")
	public String main(HttpSession session, Model model) {
		return "redirect:/user";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(Model model) {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginAction(HttpSession session, LoginUser lu,Model model) {
		User user = oservice.getUserByName(lu.getUsername());
		if (user == null) {
			user = oservice.loginByTel(lu.username, lu.password);
		}
		if(user!=null){
			session.setAttribute("cur_user", user.getId());
			return "redirect:main";
		}
		model.addAttribute("errorMsg", "用户未登记/密码错误！");
		return "login";
	}

	@RequestMapping("/greeting")
	public String greeting(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}
}
