package com.arafat.UserManagementSys.infrastructure.persistence;


import com.arafat.UserManagementSys.application.interfaces.RoleRepository;
import com.arafat.UserManagementSys.domain.Role;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public class RoleJpaRepository implements RoleRepository {
    private final SpringRoleRepository springRepo;

    public RoleJpaRepository(SpringRoleRepository springRepo) {
        this.springRepo = springRepo;
    }

    @Override
    public Role save(Role role) {
        var entity = new RoleJpaEntity(
                role.getId(), role.getRoleName(),
                role.getCreatedDate(), role.getUpdatedDate()
        );
        var saved = springRepo.save(entity);
        return new Role(saved.getRoleName());
    }

    @Override
    public Optional<Role> findById(UUID id) {
        return springRepo.findById(id)
                .map(e -> new Role(e.getRoleName()));
    }
}
