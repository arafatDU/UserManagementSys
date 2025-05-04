package com.arafat.UserManagementSys.infrastructure.controller;



import com.arafat.UserManagementSys.application.UserService;
import com.arafat.UserManagementSys.domain.User;
import com.arafat.UserManagementSys.infrastructure.controller.dto.CreateUserRequest;
import com.arafat.UserManagementSys.infrastructure.controller.dto.UserDTO;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Map<String, UUID>> createUser(@Valid @RequestBody CreateUserRequest request) {
        User createdUser = userService.createUser(request.getName(), request.getEmail());
        Map<String, UUID> response = new HashMap<>();
        response.put("id", createdUser.getId());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable UUID id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(UserDTO.fromDomain(user));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<UserDTO> users = userService.getAllUsers(page,size).stream()
                .map(UserDTO::fromDomain)
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    @PostMapping("/{userId}/assign-role/{roleId}")
    public ResponseEntity<Map<String, String>> assignRoleToUser(
            @PathVariable UUID userId,
            @PathVariable UUID roleId) {
        logger.info("Assigning role with ID: {} to user with ID: {}", roleId, userId);
        userService.assignRoleToUser(userId, roleId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Role assigned successfully");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{userId}/remove-role/{roleId}")
    public ResponseEntity<Map<String, String>> removeRoleFromUser(
            @PathVariable UUID userId,
            @PathVariable UUID roleId) {
        userService.removeRoleFromUser(userId, roleId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Role removed successfully");
        return ResponseEntity.ok(response);
    }
}