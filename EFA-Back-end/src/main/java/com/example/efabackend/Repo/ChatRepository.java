package com.example.efabackend.Repo;

import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.efabackend.entity.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    Set<Chat> findByFirstUserName(String username);

    Set<Chat> findBySecondUserName(String username);

    Set<Chat> findByFirstUserNameAndSecondUserName(String firstUserName, String secondUserName);

    Set<Chat> findBySecondUserNameAndFirstUserName(String firstUserName, String secondUserName);

    boolean existsById(Long chatId);

    boolean existsByFirstUserName(String username);
}