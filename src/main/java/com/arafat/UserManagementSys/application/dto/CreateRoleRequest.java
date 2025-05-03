package com.arafat.UserManagementSys.application.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateRoleRequest(
        @NotBlank String roleName
) {}
