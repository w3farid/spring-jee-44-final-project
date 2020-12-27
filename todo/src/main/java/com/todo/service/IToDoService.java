package com.todo.service;

import java.util.Map;

import com.todo.model.ToDo;

public interface IToDoService {
	public Map<String, Object> getAll();
	public Map<String, Object> getById(long id);
	public Map<String, Object> save(ToDo entity);
	public Map<String, Object> update(ToDo entity);
	public Map<String, Object> delete(long id);
}
