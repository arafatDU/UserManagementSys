package com.arafat.UserManagementSys.application;


import com.arafat.UserManagementSys.application.dto.CreateRoleRequest;
import com.arafat.UserManagementSys.application.interfaces.RoleRepository;
import com.arafat.UserManagementSys.domain.Role;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
public class RoleService {
    private final RoleRepository roleRepo;

    public RoleService(@Qualifier("roleJpaRepository") RoleRepository roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Transactional
    public UUID createRole(CreateRoleRequest req) {
        var role = new Role(req.roleName());
        return roleRepo.save(role).getId();
    }
}
