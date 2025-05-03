package com.arafat.UserManagementSys.infrastructure.persistence;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface SpringRoleRepository extends JpaRepository<RoleJpaEntity, UUID> {
}
