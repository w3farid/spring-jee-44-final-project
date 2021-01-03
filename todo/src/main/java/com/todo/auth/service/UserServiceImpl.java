package com.todo.auth.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.todo.auth.model.User;
import com.todo.auth.repository.IRoleDao;
import com.todo.auth.repository.IUserDao;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private IUserDao userDao;
	@Autowired
	private IRoleDao roleDao;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(new HashSet<>(roleDao.findAll()));
		userDao.save(user);
	}

	@Override
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}
}