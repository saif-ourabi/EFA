package com.example.efabackend.service.impl;

import java.util.List;
import java.util.Set;

import com.example.efabackend.entity.Chat;
import com.example.efabackend.entity.Message;
import com.example.efabackend.exception.ChatAlreadyExistException;
import com.example.efabackend.exception.NoChatExistsInTheRepository;
import com.example.efabackend.exception.ChatNotFoundException;

public interface ChatService {
    Chat addChat(Chat chat) throws ChatAlreadyExistException;

    List<Chat> findAllChats() throws NoChatExistsInTheRepository;

    Chat getChatById(int id) throws ChatNotFoundException;

    Set<Chat> getChatsByFirstUserName(String username) throws ChatNotFoundException;

    Set<Chat> getChatsBySecondUserName(String username) throws ChatNotFoundException;

    Set<Chat> getChatsByUserName(String username) throws ChatNotFoundException;

    Set<Chat> getChatsByFirstUserNameAndSecondUserName(String firstUserName, String secondUserName) throws ChatNotFoundException;

    Chat addMessageToChat(Message message, int chatId) throws ChatNotFoundException;
}
