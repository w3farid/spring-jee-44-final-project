package com.todo.auth.service;

import com.todo.auth.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);

	User getCurrentUser();

	String getCurrentUsername();
}