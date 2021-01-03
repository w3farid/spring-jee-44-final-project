package com.todo.auth.repository;

import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.todo.auth.model.Role;

@Repository
public class RoleDaoImpl implements IRoleDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Set<Role> findAll() {
		Set<Role> roleList = (Set<Role>) sessionFactory.getCurrentSession()
		.createQuery("FROM Role")
		.list();
		return roleList;
	}

	

}
