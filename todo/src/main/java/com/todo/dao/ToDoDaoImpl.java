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

@Repository
@Transactional
public class ToDoDaoImpl implements IToDoDao{
	
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Map<String, Object> getAll() {
		Map<String, Object> map = new HashMap<>();
		try {			
			Session s = sessionFactory.openSession();
			List<ToDo> entityList = s.createQuery("FROM ToDo").list();
			map.put("entityList", entityList);
			return map;
		} catch (Exception e) {			
			throw new HibernateError(e.getLocalizedMessage());
		}
	}
	
	@Override
	public Map<String, Object> getByUsername(String username) {
		Map<String, Object> map = new HashMap<>();
		try {			
			Session s = sessionFactory.openSession();
			List<ToDo> entityList = s.createQuery("FROM ToDo where createdBy=:username")
					.setParameter("username", username)
					.list();
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
			ToDo entity = s.get(ToDo.class, id);
			map.put("entity", entity);
			return map;
		} catch (Exception e) {
			throw new HibernateError(e.getLocalizedMessage());
		}
	}

	@Override
	public Map<String, Object> save(ToDo entity) {
		Map<String, Object> map = new HashMap<>();
		try {
			Session s = sessionFactory.getCurrentSession();
			s.save(entity);
			map.put("status", "success");
			map.put("message", "saved successfully");
			return map;
		} catch (Exception e) {
			map.put("status", "error");
			map.put("message", "save failed!");
			return map;
		}
	}

	@Override
	public Map<String, Object> update(ToDo entity) {
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
			ToDo entity = s.get(ToDo.class, id);
			s.delete(entity);
			map.put("status", "success");
			return map;
		} catch (Exception e) {
			throw new HibernateError(e.getLocalizedMessage());
		}
	}

}
