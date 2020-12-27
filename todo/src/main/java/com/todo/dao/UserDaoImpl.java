package com.todo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.todo.model.ToDo;
import com.todo.model.ToDoUser;

@Repository
@Transactional
public class UserDaoImpl implements IUserDao{
	
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Map<String, Object> getAll() {
		Map<String, Object> map = new HashMap<>();
		try {			
			Session s = sessionFactory.openSession();
			List<ToDoUser> entityList = s.createQuery("FROM ToDo").list();
			map.put("entityList", entityList);
			return map;
		} catch (Exception e) {			
			throw new HibernateError(e.getLocalizedMessage());
		}
	}

	@Override
	public Map<String, Object> getById(long id) {
		Map<String, Object> map = new HashMap<>();
		try {
			Session s = sessionFactory.openSession();
			ToDoUser entity = s.get(ToDoUser.class, id);
			map.put("entity", entity);
			return map;
		} catch (Exception e) {
			throw new HibernateError(e.getLocalizedMessage());
		}
	}

	@Override
	public Map<String, Object> save(ToDoUser entity) {
		Map<String, Object> map = new HashMap<>();
		try {
			Session s = sessionFactory.openSession();
			s.save(entity);
			map.put("status", "success");
			return map;
		} catch (Exception e) {
			throw new HibernateError(e.getLocalizedMessage());
		}
	}

	@Override
	public Map<String, Object> update(ToDoUser entity) {
		Map<String, Object> map = new HashMap<>();
		try {
			Session s = sessionFactory.openSession();
			s.update(entity);
			map.put("status", "success");
			return map;
		} catch (Exception e) {
			throw new HibernateError(e.getLocalizedMessage());
		}
	}

	@Override
	public Map<String, Object> delete(long id) {
		Map<String, Object> map = new HashMap<>();
		try {
			Session s = sessionFactory.openSession();
			ToDoUser entity = s.get(ToDoUser.class, id);
			s.delete(entity);
			map.put("status", "success");
			return map;
		} catch (Exception e) {
			throw new HibernateError(e.getLocalizedMessage());
		}
	}

}
