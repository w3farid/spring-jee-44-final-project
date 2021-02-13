package com.todo.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.todo.auth.service.UserService;
import com.todo.model.ToDo;
import com.todo.service.IToDoService;

@RestController
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

		Map<String, Object> map = toDoService.getMyToday(username);
		return new ModelAndView("todo/my_today", map);
	}
	
	@GetMapping("/todolist/{usrname}")
	public List<ToDo> todoList(@PathVariable(value = "usrname") String username1) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userService.getCurrentUsername();	
		if(username == null) username = "irfan";

		Map<String, Object> map = toDoService.getByUsername(username1);
		List<ToDo> entityList = (List<ToDo>) map.get("entityList");
		return entityList;
	}

	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable(value = "id") long id) {
		Map<String, Object> map = toDoService.getById(id);

		return new ModelAndView("todo/edit", map);
	}
	
	
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable(value = "id") long id) {
		Map<String, Object> map = toDoService.delete(id);
//		return "redirect:/todo";
		
		return new ModelAndView("redirect:/todo");
	}
	
	@GetMapping("/done/{id}")
	public ModelAndView changeStatus(@PathVariable(value = "id") long id) {
		Map<String, Object> map1 = toDoService.getById(id);
		ToDo todo = (ToDo) map1.get("entity");
		todo.setStatus("Done");
		Map<String, Object> map = toDoService.update(todo);

		return new ModelAndView("redirect:/todo");
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

		return new ModelAndView("redirect:/todo");	
	}
	
	@PostMapping("/api/add")
	public String save(@RequestBody ToDo toDo) {
		toDo.setCreatedBy("api");
		toDo.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		toDo.setDueDate(new Timestamp(System.currentTimeMillis()));
		Map<String, Object> map = toDoService.save(toDo);

		return "success";
	}
	
	@PostMapping("/update")
	public ModelAndView update(HttpServletRequest req) {
		String username = userService.getCurrentUsername();	
		String content = req.getParameter("content");
		String strDueDate = req.getParameter("due_date");
		String strDueTime = req.getParameter("due_time");
		String strDueDateTime = strDueDate.concat(" " +strDueTime);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		try {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		    Date parsedDate = dateFormat.parse(strDueDateTime);
		    timestamp = new java.sql.Timestamp(parsedDate.getTime());
		} catch(Exception e) { //this generic but you can control another types of exception
		    // look the origin of excption 
		}		String id = req.getParameter("id");
		
		ToDo todo = new ToDo();
		todo.setId(Long.parseLong(id));
		todo.setTitle(content);
		todo.setContent(content);
		todo.setCreatedBy(username);
		todo.setStatus("Pending");
		todo.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		todo.setDueDate(timestamp);
		Map<String, Object> map = toDoService.update(todo);
		
		return new ModelAndView("todo/edit", map);
	}

}
