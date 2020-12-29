package com.example.bolg.service;

import com.example.bolg.dao.UserDao;
import com.example.bolg.po.User;
import com.example.bolg.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements  UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User checkUser(String username, String password) {
        User user=userDao.findByUsernameAndPassword(username, MD5Util.code(password));
        return user;
    }
}
