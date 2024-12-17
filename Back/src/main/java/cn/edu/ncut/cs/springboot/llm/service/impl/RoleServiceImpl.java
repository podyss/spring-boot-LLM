package cn.edu.ncut.cs.springboot.llm.service.impl;

import cn.edu.ncut.cs.springboot.llm.entity.Role;
import cn.edu.ncut.cs.springboot.llm.dao.RoleDao;
import cn.edu.ncut.cs.springboot.llm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> getRoles(@NonNull Long userId) {
        return roleDao.getRolesById(userId);
    }
}
