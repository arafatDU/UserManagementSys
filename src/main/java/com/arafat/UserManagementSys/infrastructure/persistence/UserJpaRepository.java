package com.arafat.UserManagementSys.infrastructure.persistence;

import com.arafat.UserManagementSys.application.interfaces.UserRepository;
import com.arafat.UserManagementSys.domain.User;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserJpaRepository implements UserRepository {
    private final SpringUserRepository springRepo;

    public UserJpaRepository(SpringUserRepository springRepo) {
        this.springRepo = springRepo;
    }

    @Override
    public User save(User user) {
        var entity = new UserJpaEntity(
                user.getId(), user.getName(), user.getEmail(),
                user.getCreatedDate(), user.getUpdatedDate()
        );
        entity.getRoles().addAll(
                user.getRoles().stream()
                        .map(r -> new RoleJpaEntity(
                                r.getId(), r.getRoleName(),
                                r.getCreatedDate(), r.getUpdatedDate()
                        ))
                        .toList()
        );
        var saved = springRepo.save(entity);

        var domain = new User(saved.getName(), saved.getEmail());
        managedCopy(saved, domain);
        return domain;
    }

    @Override
    public Optional<User> findById(UUID id) {
        return springRepo.findById(id).map(e -> {
            var u = new User(e.getName(), e.getEmail());
            managedCopy(e, u);
            return u;
        });
    }

    private void managedCopy(UserJpaEntity e, User u) {
        e.getRoles().forEach(rEnt -> {
            var r = new com.arafat.UserManagementSys.domain.Role(rEnt.getRoleName());
            u.assignRole(r);
        });
    }
}
