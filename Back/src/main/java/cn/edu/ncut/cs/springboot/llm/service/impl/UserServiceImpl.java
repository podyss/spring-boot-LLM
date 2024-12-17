package cn.edu.ncut.cs.springboot.llm.service.impl;

import cn.edu.ncut.cs.springboot.llm.dao.LogDao;
import cn.edu.ncut.cs.springboot.llm.dao.UserDao;
import cn.edu.ncut.cs.springboot.llm.entity.Log;
import cn.edu.ncut.cs.springboot.llm.entity.User;
import cn.edu.ncut.cs.springboot.llm.service.LogService;
import cn.edu.ncut.cs.springboot.llm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public Optional findById(Integer id) {
        return userDao.findById(id);
    }

    @Override
    public Optional findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public void saveUser(User user) {
        userDao.save(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userDao.deleteById(id);
    }
}
