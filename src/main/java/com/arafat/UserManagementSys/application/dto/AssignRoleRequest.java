package com.arafat.UserManagementSys.application.dto;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record AssignRoleRequest(
        @NotNull UUID roleId
) {}
