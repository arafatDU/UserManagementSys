package com.arafat.UserManagementSys.application;


import com.arafat.UserManagementSys.application.dto.CreateUserRequest;
import com.arafat.UserManagementSys.application.dto.UserResponse;
import com.arafat.UserManagementSys.application.interfaces.RoleRepository;
import com.arafat.UserManagementSys.application.interfaces.UserRepository;
import com.arafat.UserManagementSys.domain.Role;
import com.arafat.UserManagementSys.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;

    public UserService(UserRepository userRepo, RoleRepository roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    @Transactional
    public UUID createUser(CreateUserRequest req) {
        var user = new User(req.name(), req.email());
        return userRepo.save(user).getId();
    }

    @Transactional
    public void assignRole(UUID userId, UUID roleId) {
        var user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        var role = roleRepo.findById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));
        user.assignRole(role);
        userRepo.save(user);
    }

    @Transactional
    public void removeRole(UUID userId, UUID roleId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Role role = roleRepo.findById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));
        user.removeRole(role);
        userRepo.save(user);
    }

    @Transactional(readOnly = true)
    public UserResponse getUser(UUID id) {
        var user = userRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        var roles = user.getRoles().stream()
                .map(Role::getRoleName)
                .collect(Collectors.toList());
        return new UserResponse(
                user.getId(), user.getName(), user.getEmail(),
                user.getCreatedDate(), user.getUpdatedDate(), roles
        );
    }
}
