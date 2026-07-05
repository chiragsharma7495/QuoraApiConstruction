package org.example.quoraapiconstruction.repository;

import org.example.quoraapiconstruction.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
}
