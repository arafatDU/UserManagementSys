package com.arafat.UserManagementSys.domain;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class User {
    private final UUID id;
    private String name;
    private String email;
    private final Instant createdDate;
    private Instant updatedDate;
    private final Set<Role> roles = new HashSet<>();

    public User(String name, String email) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.createdDate = Instant.now();
        this.updatedDate = Instant.now();
    }

    // getters...
    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public Instant getCreatedDate() { return createdDate; }
    public Instant getUpdatedDate() { return updatedDate; }
    public Set<Role> getRoles() { return Set.copyOf(roles); }

    // business methods
    public void assignRole(Role role) {
        roles.add(role);
        this.updatedDate = Instant.now();
    }

    public void removeRole(Role role) {
        roles.remove(role);
        this.updatedDate = Instant.now();
    }
}
