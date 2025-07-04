package com.tryAgain.simpleCurd.repository;

import com.tryAgain.simpleCurd.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

   Optional<UserEntity> findByUsername(String username);
}
