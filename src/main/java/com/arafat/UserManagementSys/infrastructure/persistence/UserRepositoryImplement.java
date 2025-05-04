package com.arafat.UserManagementSys.infrastructure.persistence;



import com.arafat.UserManagementSys.application.interfaces.UserRepository;
import com.arafat.UserManagementSys.domain.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UserRepositoryImplement implements UserRepository {
    private final UserJpaRepository userJpaRepository;

    public UserRepositoryImplement(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public User save(User user) {
        UserJpaEntity jpaEntity = UserJpaEntity.fromDomainEntity(user);
        UserJpaEntity savedEntity = userJpaRepository.save(jpaEntity);
        return savedEntity.toDomainEntity();
    }

    @Override
    public Optional<User> findById(UUID id) {
        return userJpaRepository.findById(id.toString()).map(UserJpaEntity::toDomainEntity);
    }

    @Override
    public List<User> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page,size);
        return userJpaRepository.findAll(pageRequest).stream().map(UserJpaEntity::toDomainEntity).collect(Collectors.toList());
    }
}