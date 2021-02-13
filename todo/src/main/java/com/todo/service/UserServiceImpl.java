package com.todo.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo.auth.model.User;
import com.todo.auth.repository.IUserDao;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {
	@Autowired
	IUserDao userDao;

	@Override
	public Map<String, Object> getAll() {
		return userDao.getAll();
	}

	@Override
	public Map<String, Object> getById(long id) {
		return userDao.getById(id);
	}

	@Override
	public Map<String, Object> save(User entity) {
		return userDao.save(entity);
	}

	@Override
	public Map<String, Object> update(User entity) {
		return userDao.update(entity);
	}

	@Override
	public Map<String, Object> delete(long id) {
		return userDao.delete(id);
	}

	@Override
	public Map<String, Object> getByUsername(String username) {
		return userDao.getByUsername(username);
	}

}
