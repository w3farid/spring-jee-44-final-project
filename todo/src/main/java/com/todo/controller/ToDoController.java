package com.todo.controller;

import java.sql.Timestamp;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.todo.auth.service.UserService;
import com.todo.model.ToDo;
import com.todo.service.IToDoService;

@Controller
@RequestMapping("/todo")
public class ToDoController {
	@Autowired
	IToDoService toDoService;
	
	@Autowired
	UserService userService;

	@GetMapping("")
	public ModelAndView index() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userService.getCurrentUsername();	

		Map<String, Object> map = toDoService.getByUsername(username);
		return new ModelAndView("todo/todo", map);
	}
	
	@GetMapping("/my-today")
	public ModelAndView myToday() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userService.getCurrentUsername();	

		Map<String, Object> map = toDoService.getByUsername(username);
		return new ModelAndView("todo/todo", map);
	}

	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable(value = "id") long id) {
		Map<String, Object> map = toDoService.getById(id);

		return new ModelAndView("todo/edit", map);
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable(value = "id") long id) {
		Map<String, Object> map = toDoService.delete(id);
		return "redirect:/todo";
	}
	
	@GetMapping("/done/{id}")
	public String changeStatus(@PathVariable(value = "id") long id) {
		Map<String, Object> map1 = toDoService.getById(id);
		ToDo todo = (ToDo) map1.get("entity");
		todo.setStatus("Done");
		Map<String, Object> map = toDoService.update(todo);

		return "redirect:/todo";
	}


	@PostMapping("/add")
	public ModelAndView add(HttpServletRequest req) {
		String username = userService.getCurrentUsername();	
		String content = req.getParameter("content");
//		content.equals("")
		ToDo todo = new ToDo();
		todo.setTitle(content);
		todo.setContent(content);
		todo.setCreatedBy(username);
		todo.setStatus("Pending");
		todo.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		todo.setDueDate(new Timestamp(System.currentTimeMillis()));
		Map<String, Object> map = toDoService.save(todo);

		return new ModelAndView("todo/todo", map);
	}
	
	@PostMapping("/update")
	public ModelAndView update(HttpServletRequest req) {
		String username = userService.getCurrentUsername();	
		String content = req.getParameter("content");
		String id = req.getParameter("id");
		
		ToDo todo = new ToDo();
		todo.setId(Long.parseLong(id));
		todo.setTitle(content);
		todo.setContent(content);
		todo.setCreatedBy(username);
		todo.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		Map<String, Object> map = toDoService.update(todo);
		
		return new ModelAndView("todo/edit", map);
	}

}
