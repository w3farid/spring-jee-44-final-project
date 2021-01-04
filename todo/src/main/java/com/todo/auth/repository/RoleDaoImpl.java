package com.todo.auth.repository;

import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.todo.auth.model.Role;

@Repository
@Transactional
public class RoleDaoImpl implements IRoleDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<Role> findAll() {
		List<Role> roleList = (List<Role>) sessionFactory.getCurrentSession()
		.createQuery("FROM Role")
		.list();
		
		return roleList;
	}

	

}
