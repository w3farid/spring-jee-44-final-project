package com.todo.dao;

import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ToDoDaoImpl implements IToDoDao{
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Map<String, Object> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> save() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> update() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> delete() {
		// TODO Auto-generated method stub
		return null;
	}

}
