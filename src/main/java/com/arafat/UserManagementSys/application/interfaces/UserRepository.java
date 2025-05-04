package com.arafat.UserManagementSys.application.interfaces;


import com.arafat.UserManagementSys.domain.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(UUID id);
    List<User> findAll(int page, int size);
}