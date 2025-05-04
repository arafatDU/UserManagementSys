package com.arafat.UserManagementSys.infrastructure.persistence;



import com.arafat.UserManagementSys.domain.Role;
import com.arafat.UserManagementSys.domain.User;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
public class UserJpaEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(36)")
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleJpaEntity> roles = new HashSet<>();

    public UserJpaEntity() {}

    public UserJpaEntity(String id, String name, String email) {
        this.id=id;
        this.name=name;
        this.email=email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setRoles(Set<RoleJpaEntity> roles) {
        this.roles = roles;
    }

    public User toDomainEntity() {
        User user = new User(
                UUID.fromString(this.id),
                this.name,
                this.email
        );

        Set<Role> domainRoles = this.roles.stream()
                .map(RoleJpaEntity::toDomainEntity)
                .collect(Collectors.toSet());

        domainRoles.forEach(user::assignRole);
        return user;
    }

    public static UserJpaEntity fromDomainEntity(User user) {
        UserJpaEntity entity = new UserJpaEntity(
                user.getId().toString(),
                user.getName(),
                user.getEmail()
        );

        Set<RoleJpaEntity> jpaRoles = user.getRoles().stream()
                .map(role -> new RoleJpaEntity(role.getId().toString(), role.getRoleName()))
                .collect(Collectors.toSet());

        entity.setRoles(jpaRoles);
        return entity;
    }
}