package com.todo.dao;

import java.util.Map;

import com.todo.model.ToDo;
import com.todo.model.ToDoUser;

public interface IUserDao {

	public Map<String, Object> getAll();
	public Map<String, Object> getById(long id);
	public Map<String, Object> save(ToDoUser entity);
	public Map<String, Object> update(ToDoUser entity);
	public Map<String, Object> delete(long id);
}
