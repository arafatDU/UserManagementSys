package com.arafat.UserManagementSys.infrastructure.persistence;


import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "roles")
public class RoleJpaEntity {
    @Id private UUID id;
    private String roleName;
    private Instant createdDate;
    private Instant updatedDate;

    protected RoleJpaEntity() {}

    public RoleJpaEntity(UUID id, String roleName, Instant c, Instant u) {
        this.id = id; this.roleName = roleName;
        this.createdDate = c; this.updatedDate = u;
    }

    // << Add these getters >>
    public UUID getId() { return id; }
    public String getRoleName() { return roleName; }
    public Instant getCreatedDate() { return createdDate; }
    public Instant getUpdatedDate() { return updatedDate; }

    // And setters if needed:
    public void setRoleName(String roleName) { this.roleName = roleName; }
    public void setUpdatedDate(Instant updatedDate) { this.updatedDate = updatedDate; }
}

