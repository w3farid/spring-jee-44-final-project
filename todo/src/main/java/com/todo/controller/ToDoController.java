package com.todo.controller;

import java.sql.Timestamp;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.todo.model.ToDo;
import com.todo.service.IToDoService;

@RestController
@RequestMapping("/todo")
public class ToDoController {
	@Autowired
	IToDoService toDoService;
	
	@GetMapping("")
	public ModelAndView index() {
		Map<String, Object> map = toDoService.getByUsername("altaf");
		return new ModelAndView("todo/todo", map);
	}
	
	@PostMapping("/add")
	public ModelAndView add(HttpServletRequest req) {
		
		String content = req.getParameter("content");
//		content.equals("")
		ToDo todo = new ToDo();
		todo.setTitle(content);
		todo.setContent(content);
		todo.setCreatedBy("Altaf");
		todo.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		Map<String, Object> map = toDoService.save(todo);
		
		return new ModelAndView("todo/todo", map);
	}
	
	
	
	

}
