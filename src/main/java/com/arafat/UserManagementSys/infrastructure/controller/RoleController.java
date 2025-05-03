package com.arafat.UserManagementSys.infrastructure.controller;


import com.arafat.UserManagementSys.application.RoleService;
import com.arafat.UserManagementSys.application.dto.CreateRoleRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleService svc;
    public RoleController(RoleService svc) { this.svc = svc; }

    @PostMapping
    public ResponseEntity<UUID> create(@Valid @RequestBody CreateRoleRequest req) {
        return ResponseEntity.ok(svc.createRole(req));
    }
}
