package com.example.efabackend.Repo;

import java.util.HashSet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.efabackend.entity.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat,Long> {
    HashSet<Chat> findByFirstUserName(String username);

    HashSet<Chat> findBySecondUserName(String username);

    HashSet<Chat> findByFirstUserNameAndSecondUserName(String firstUserName, String secondUserName);

    HashSet<Chat> findBySecondUserNameAndFirstUserName(String firstUserName, String secondUserName);
}
