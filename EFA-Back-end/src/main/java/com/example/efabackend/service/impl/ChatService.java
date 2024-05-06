package com.example.efabackend.service.impl;

import java.util.HashSet;
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

    HashSet<Chat> getChatByFirstUserName(String username) throws ChatNotFoundException;

    HashSet<Chat> getChatBySecondUserName(String username) throws ChatNotFoundException;

    HashSet<Chat> getChatByFirstUserNameOrSecondUserName(String username) throws ChatNotFoundException;

    HashSet<Chat> getChatByFirstUserNameAndSecondUserName(String firstUserName, String secondUserName) throws ChatNotFoundException;

    Chat addMessage(Message message, int chatId) throws ChatNotFoundException;

   
}
