package com.jee.bartersup.dao;


import com.jee.bartersup.entity.User;

import java.util.List;

public interface UserIDao {

    void AddUser(User user);
    boolean userExists( String email,String username);
    boolean authUser(String password, String email);
    User getUserById(Object userId);
    User getUserByCredential(String email, String password);
    void updateUser(User user);
    void deleteUser(User user);
    List<User> findAll();
}
