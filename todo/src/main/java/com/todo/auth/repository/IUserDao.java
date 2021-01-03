package com.todo.auth.repository;

import java.util.Map;

import com.todo.auth.model.User;
import com.todo.model.ToDo;

public interface IUserDao {

	public Map<String, Object> getAll();
	public Map<String, Object> getById(long id);
	public Map<String, Object> save(User entity);
	public Map<String, Object> update(User entity);
	public Map<String, Object> delete(long id);
	public Map<String, Object> getByUsername(String username);
	public User findByUsername(String username);
}
