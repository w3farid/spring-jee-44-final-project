package com.todo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
		return new ModelAndView("index");
	}
	
	@PostMapping("/save")
	public ModelAndView save(HttpServletRequest req) {
		return new ModelAndView("index");
	}
	
	@PostMapping("/update")
	public ModelAndView update(HttpServletRequest req) {
		return new ModelAndView("index");
	}
	
}
