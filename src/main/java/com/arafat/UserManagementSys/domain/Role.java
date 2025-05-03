package com.arafat.UserManagementSys.domain;

import java.time.Instant;
import java.util.UUID;

public class Role {
    private final UUID id;
    private String roleName;
    private final Instant createdDate;
    private Instant updatedDate;

    public Role(String roleName) {
        this.id = UUID.randomUUID();
        this.roleName = roleName;
        this.createdDate = Instant.now();
        this.updatedDate = Instant.now();
    }

    // getters...
    public UUID getId() { return id; }
    public String getRoleName() { return roleName; }
    public Instant getCreatedDate() { return createdDate; }
    public Instant getUpdatedDate() { return updatedDate; }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
        this.updatedDate = Instant.now();
    }
}
