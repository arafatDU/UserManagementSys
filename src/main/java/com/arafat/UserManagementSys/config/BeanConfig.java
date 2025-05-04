package com.arafat.UserManagementSys.config;

import com.arafat.UserManagementSys.application.RoleService;
import com.arafat.UserManagementSys.application.UserService;
import com.arafat.UserManagementSys.application.interfaces.RoleRepository;
import com.arafat.UserManagementSys.application.interfaces.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public UserService userService(UserRepository userRepository, RoleRepository roleRepository) {
        return new UserService(userRepository, roleRepository);
    }

    @Bean
    public RoleService roleService(RoleRepository roleRepository) {
        return new RoleService(roleRepository);
    }
}