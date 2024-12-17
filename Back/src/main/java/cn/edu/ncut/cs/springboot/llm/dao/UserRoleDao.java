package cn.edu.ncut.cs.springboot.llm.dao;

import cn.edu.ncut.cs.springboot.llm.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleDao extends JpaRepository<UserRole, Long> {
    List<UserRole> findByUserId(Long userId);
}


