package com.todo.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.todo.model.ToDoUser;
import com.todo.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	IUserService userService;

	@GetMapping("/create")
	public ModelAndView create() {
		return new ModelAndView("user/create");
	}
	
	
	
	@GetMapping("/show/{id}")
	public ModelAndView show(@PathVariable(value = "id") long id) {
		return new ModelAndView("user/show");
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable(value = "id") long id) {
		return new ModelAndView("user/edit");
	}
	
	@PostMapping("/login")
	public ModelAndView login(HttpServletRequest req) {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		Map<String, Object> map = userService.getByUsername(username);
		ToDoUser user = (ToDoUser) map.get("user");
		
		if(user != null) {
			String status = user.getPassword().equals(password)? "success": "error";
			if(status.equals("success")) {
				return new ModelAndView("index");	
			}else {
				map.put("status", status);
				map.put("message", "Login failed! Please try again.");
				return new ModelAndView("user/login", map);
			}
		}else {
			map.put("status", "error");
			map.put("message", "Login failed! Please try again.");
			return new ModelAndView("user/login", map);
		}
		
		
	}	
	
	
	@PostMapping("/save")
	public ModelAndView save(HttpServletRequest req) {
		String name = req.getParameter("name");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String gender = req.getParameter("gender");
		String mobile = req.getParameter("mobile");
		
		ToDoUser user = new ToDoUser();
		user.setName(name);
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setGender(gender);
		user.setPhone(mobile);
		
		Map<String, Object> map = userService.save(user);
		String status = (String) map.get("status");
		
		if(status.equals("success")) {
			System.out.println(status);
		}else {
			String message = (String) map.get("message");
			System.out.println(status);
			System.out.println(message);
		}
		return new ModelAndView("user/registration", map);
	}
	
	@PostMapping("/update")
	public ModelAndView update(HttpServletRequest req) {
		return new ModelAndView("index");
	}
	
}
