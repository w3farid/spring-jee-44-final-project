package com.todo.dao;

import java.util.Map;

public interface IToDoDao {

	public Map<String, Object> getAll();
	public Map<String, Object> getById(long id);
	public Map<String, Object> save();
	public Map<String, Object> update();
	public Map<String, Object> delete();
}
