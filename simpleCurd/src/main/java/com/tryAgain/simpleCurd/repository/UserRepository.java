package com.tryAgain.simpleCurd.repository;

import com.tryAgain.simpleCurd.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
