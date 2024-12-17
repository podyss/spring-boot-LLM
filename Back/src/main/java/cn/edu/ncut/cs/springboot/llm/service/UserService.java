package cn.edu.ncut.cs.springboot.llm.service;

import cn.edu.ncut.cs.springboot.llm.entity.Log;
import cn.edu.ncut.cs.springboot.llm.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> findById(Integer id);
    Optional<User> findByUsername(String username);
    void saveUser(User user);
    void deleteUser(Integer id);
}
