package com.arafat.UserManagementSys.application.dto;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String name,
        String email,
        Instant createdDate,
        Instant updatedDate,
        List<String> roles
) {}
