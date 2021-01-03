package com.todo.auth.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.todo.auth.model.User;

@Repository
public class UserDaoImpl implements IUserDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Map<String, Object> getAll() {
		Map<String, Object> map = new HashMap<>();
		try {
			Session s = sessionFactory.openSession();
			List<User> entityList = s.createQuery("FROM ToDo").list();
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
			Session s = sessionFactory.getCurrentSession();
			User entity = s.get(User.class, id);
			map.put("entity", entity);
			return map;
		} catch (Exception e) {
			throw new HibernateError(e.getLocalizedMessage());
		}
	}

	@Override
	public Map<String, Object> save(User entity) {
		Map<String, Object> map = new HashMap<>();
		try {
			Session s = sessionFactory.getCurrentSession();
			s.save(entity);
			map.put("status", "success");
			map.put("message", "Saved successfully");
			return map;
		} catch (Exception e) {
			map.put("status", "error");
			map.put("message", e.getLocalizedMessage());
			return null;
		}
	}

	@Override
	public Map<String, Object> update(User entity) {
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
			User entity = s.get(User.class, id);
			s.delete(entity);
			map.put("status", "success");
			return map;
		} catch (Exception e) {
			throw new HibernateError(e.getLocalizedMessage());
		}
	}

	@Override
	public Map<String, Object> getByUsername(String username) {
		Map<String, Object> map = new HashMap<>();
		try {
			Session s = sessionFactory.getCurrentSession();
			List<User> entityList = s.createQuery("From ToDoUser Where username=:username")
					.setParameter("username", username).list();
			if (entityList.size() > 0) {
				User tododUser = entityList.get(0);
				map.put("user", tododUser);
			} else {
				map.put("user", null);
			}
			return map;
		} catch (Exception e) {
			map.put("user", null);
			return map;
		}
	}

	@Override
	public User findByUsername(String username) {
		try {
			Session s = sessionFactory.getCurrentSession();
			List<User> entityList = s.createQuery("From User Where username=:username")
					.setParameter("username", username).list();
			if (entityList.size() > 0) {
				User tododUser = entityList.get(0);
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}

}
