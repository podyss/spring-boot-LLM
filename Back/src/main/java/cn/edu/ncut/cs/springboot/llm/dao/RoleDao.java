package cn.edu.ncut.cs.springboot.llm.dao;


import cn.edu.ncut.cs.springboot.llm.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleDao extends JpaRepository<Role, Long> {
    List<Role> getRolesById(Long id);
}
