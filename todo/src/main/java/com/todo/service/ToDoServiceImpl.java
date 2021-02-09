package com.todo.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.dao.IToDoDao;
import com.todo.model.ToDo;

@Service
public class ToDoServiceImpl implements IToDoService {
	@Autowired
	IToDoDao toDoDao;

	@Override
	public Map<String, Object> getAll() {
		return toDoDao.getAll();
	}
	
	@Override
	public Map<String, Object> getByUsername(String username) {
		return toDoDao.getByUsername(username);
	}

	@Override
	public Map<String, Object> getById(long id) {
		return toDoDao.getById(id);
	}

	@Override
	public Map<String, Object> save(ToDo entity) {
		return toDoDao.save(entity);
	}

	@Override
	public Map<String, Object> update(ToDo entity) {
		return toDoDao.update(entity);
	}

	@Override
	public Map<String, Object> delete(long id) {
		return toDoDao.delete(id);
	}

	@Override
	public Map<String, Object> getMyToday(String username) {
		return toDoDao.getMyToday(username);
	}

}
