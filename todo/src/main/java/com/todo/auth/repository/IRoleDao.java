package com.todo.auth.repository;

import java.util.Set;

import com.todo.auth.model.Role;

public interface IRoleDao {

	Set<Role> findAll();
}
