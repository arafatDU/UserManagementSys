package com.arafat.UserManagementSys.infrastructure.controller;


import com.arafat.UserManagementSys.application.UserService;
import com.arafat.UserManagementSys.application.dto.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService svc;
    public UserController(UserService svc) { this.svc = svc; }

    @PostMapping
    public ResponseEntity<UUID> create(@Valid @RequestBody CreateUserRequest req) {
        return ResponseEntity.ok(svc.createUser(req));
    }

    @PostMapping("/{userId}/assign-role")
    public ResponseEntity<Void> assign(
            @PathVariable UUID userId,
            @Valid @RequestBody AssignRoleRequest req) {
        svc.assignRole(userId, req.roleId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{userId}/remove-role")
    public ResponseEntity<Void> remove(
            @PathVariable UUID userId,
            @Valid @RequestBody AssignRoleRequest req) {
        svc.removeRole(userId, req.roleId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> get(@PathVariable UUID id) {
        return ResponseEntity.ok(svc.getUser(id));
    }
}
