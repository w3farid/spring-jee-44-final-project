package com.todo.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.todo.model.ToDo;

public class ToDoServiceImpl implements IToDoService {
	@Autowired
	IToDoService toDoDao;

	@Override
	public Map<String, Object> getAll() {
		return toDoDao.getAll();
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

}
