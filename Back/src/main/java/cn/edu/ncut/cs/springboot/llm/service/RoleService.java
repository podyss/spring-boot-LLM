package cn.edu.ncut.cs.springboot.llm.service;


import cn.edu.ncut.cs.springboot.llm.entity.Role;
import org.springframework.lang.NonNull;

import java.util.List;

public interface RoleService {
    List<Role> getRoles(@NonNull Long userId);
}
