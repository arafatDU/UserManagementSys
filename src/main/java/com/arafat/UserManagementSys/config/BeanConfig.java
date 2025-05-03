package com.arafat.UserManagementSys.config;


import com.arafat.UserManagementSys.application.interfaces.RoleRepository;
import com.arafat.UserManagementSys.application.interfaces.UserRepository;
import com.arafat.UserManagementSys.infrastructure.persistence.RoleJpaRepository;
import com.arafat.UserManagementSys.infrastructure.persistence.UserJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
//    @Bean
//    public UserRepository userRepository(UserJpaRepository repo) {
//        return repo;
//    }

    // Remove this:
    // @Bean
    // public RoleRepository roleRepository(RoleJpaRepository repo) {
    //     return repo;
    // }
}
