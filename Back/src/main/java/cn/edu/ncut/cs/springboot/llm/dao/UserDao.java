package cn.edu.ncut.cs.springboot.llm.dao;

import cn.edu.ncut.cs.springboot.llm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserDao extends JpaRepository<User,Integer> {
    Optional findByUsername(String username);
}
