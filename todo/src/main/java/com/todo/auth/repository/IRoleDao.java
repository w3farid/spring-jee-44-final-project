package com.todo.auth.repository;

import java.util.List;

import com.todo.auth.model.Role;

public interface IRoleDao {

	List<Role> findAll();
}
