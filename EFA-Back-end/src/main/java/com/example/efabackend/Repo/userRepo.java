package com.example.efabackend.Repo;

import com.example.efabackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface userRepo extends JpaRepository<User, Long> {
    Optional<User> findOneByEmailAndPassword(String email, String password);
    User findByEmail(String Email);

    long countByRole(String role);
}
