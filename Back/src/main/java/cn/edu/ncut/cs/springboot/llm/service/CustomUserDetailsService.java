package cn.edu.ncut.cs.springboot.llm.service;


import cn.edu.ncut.cs.springboot.llm.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userOptional = userService.findByUsername(username);
        if (userOptional.isEmpty()) {
            System.out.println("User not found with username: " + username);
            throw new UsernameNotFoundException("User not found: " + username);
        }
        User user = userOptional.get();
        System.out.println("Found user: id=" + user.id + ", username=" + user.getUsername());

        // 直接获取用户的角色列表
        List<Role> roles = userRoleService.getRolesByUserId((long) user.id);
        System.out.println("Found " + roles.size() + " roles for user " + user.getUsername());
        roles.forEach(role -> System.out.println("Role: id=" + role.getId() + ", name=" + role.getName()));

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles.size());

        // 转换角色
        List<SimpleGrantedAuthority> roleAuthorities = roles.stream()
                .map(role -> {
                    String roleName = role.getName().startsWith("ROLE_") ?
                            role.getName() : "ROLE_" + role.getName();
                    System.out.println("Converting role: " + role.getName() + " -> " + roleName);
                    return roleName;
                })
                .map(SimpleGrantedAuthority::new)
                .toList();
        grantedAuthorities.addAll(roleAuthorities);

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(grantedAuthorities)
                .build();

    }
}
