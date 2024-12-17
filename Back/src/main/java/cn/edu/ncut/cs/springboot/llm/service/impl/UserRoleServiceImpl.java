package cn.edu.ncut.cs.springboot.llm.service.impl;

import cn.edu.ncut.cs.springboot.llm.entity.Role;
import cn.edu.ncut.cs.springboot.llm.entity.UserRole;
import cn.edu.ncut.cs.springboot.llm.dao.RoleDao;
import cn.edu.ncut.cs.springboot.llm.dao.UserRoleDao;
import cn.edu.ncut.cs.springboot.llm.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private RoleDao roleDao;

    @Override
    public List<UserRole> getUserRoles(@NonNull Long userId) {
        return userRoleDao.findByUserId(userId);
    }

    @Override
    public boolean addUserRole(@NonNull Long userId, @NonNull Long roleId) {
        try {
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            userRoleDao.save(userRole);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Role> getRolesByUserId(Long userId) {
        System.out.println("Getting roles for userId: " + userId);
        // 1. 获取用户角色关联
        List<UserRole> userRoles = userRoleDao.findByUserId(userId);
        if (userRoles.isEmpty()) {
            return Collections.emptyList();
        }

        // 2. 批量获取角色
        List<Long> roleIds = userRoles.stream()
                .map(UserRole::getRoleId)
                .toList();
        System.out.println("Found " + roleIds.size() + " roleIds for userId: " + userId);

        return roleDao.findAllById(roleIds);
    }
} 