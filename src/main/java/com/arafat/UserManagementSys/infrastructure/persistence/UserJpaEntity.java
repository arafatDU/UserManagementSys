package com.arafat.UserManagementSys.infrastructure.persistence;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
public class UserJpaEntity {
    @Id private UUID id;
    private String name;
    private String email;
    private Instant createdDate;
    private Instant updatedDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleJpaEntity> roles = new HashSet<>();

    protected UserJpaEntity() {}

    public UserJpaEntity(UUID id, String name, String email, Instant c, Instant u) {
        this.id = id; this.name = name; this.email = email;
        this.createdDate = c; this.updatedDate = u;
    }

    // << Add these getters >>
    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public Instant getCreatedDate() { return createdDate; }
    public Instant getUpdatedDate() { return updatedDate; }
    public Set<RoleJpaEntity> getRoles() { return roles; }

    // If you ever need to modify, add setters too:
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setUpdatedDate(Instant updatedDate) { this.updatedDate = updatedDate; }
}
