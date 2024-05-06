package com.example.efabackend.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.efabackend.Repo.ChatRepository;
import com.example.efabackend.entity.Chat;
import com.example.efabackend.entity.Message;
import com.example.efabackend.exception.ChatAlreadyExistException;
import com.example.efabackend.exception.ChatNotFoundException;
import com.example.efabackend.exception.NoChatExistsInTheRepository;
import com.example.efabackend.service.impl.ChatService;

@Service
public class ChatServiceImpl implements ChatService{
  @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Override
    public Chat addChat(Chat chat) {
        chat.setChatId(sequenceGeneratorService.generateSequence(Chat.SEQUENCE_NAME));
        return chatRepository.save(chat);
    }

    @Override
    public List<Chat> findAllChats() throws NoChatExistsInTheRepository {
        List<Chat> chats = chatRepository.findAll();
        if (chats.isEmpty()) {
            throw new NoChatExistsInTheRepository();
        }
        return chats;
    }

    @Override
    public Chat getChatById(int id) throws ChatNotFoundException {
        return chatRepository.findById((long) id)
                .orElseThrow(ChatNotFoundException::new);
    }

    @Override
    public HashSet<Chat> getChatByFirstUserName(String username) throws ChatNotFoundException {
        HashSet<Chat> chats = chatRepository.findByFirstUserName(username);
        if (chats.isEmpty()) {
            throw new ChatNotFoundException();
        }
        return chats;
    }

    @Override
    public HashSet<Chat> getChatBySecondUserName(String username) throws ChatNotFoundException {
        HashSet<Chat> chats = chatRepository.findBySecondUserName(username);
        if (chats.isEmpty()) {
            throw new ChatNotFoundException();
        }
        return chats;
    }

    @Override
    public HashSet<Chat> getChatByFirstUserNameOrSecondUserName(String username) throws ChatNotFoundException {
        HashSet<Chat> chat = chatRepository.findByFirstUserName(username);
        HashSet<Chat> chat1 = chatRepository.findBySecondUserName(username);
        chat1.addAll(chat);
        if (chat.isEmpty() && chat1.isEmpty()) {
            throw new ChatNotFoundException();
        } else if (chat1.isEmpty()) {
            return chat;
        } else {
            return chat1;
        }
    }

    @Override
    public HashSet<Chat> getChatByFirstUserNameAndSecondUserName(String firstUserName, String secondUserName) throws ChatNotFoundException {
        HashSet<Chat> chat = chatRepository.findByFirstUserNameAndSecondUserName(firstUserName,secondUserName);
        HashSet<Chat> chat1 = chatRepository.findBySecondUserNameAndFirstUserName(firstUserName,secondUserName);
        if (chat.isEmpty() && chat1.isEmpty()) {
            throw new ChatNotFoundException();
        } else if (chat.isEmpty()) {
            return chat1;
        } else {
            return chat;
        }
    }

    @Override
    public Chat addMessage(Message add, int chatId) throws ChatNotFoundException {
        Optional<Chat> chat = chatRepository.findById((long)chatId);
        Chat abc = chat.orElseThrow(ChatNotFoundException::new);

        if (abc.getMessageList() == null) {
            List<Message> msg = new ArrayList<>();
            msg.add(add);
            abc.setMessageList(msg);
        } else {
            List<Message> rates = abc.getMessageList();
            rates.add(add);
            abc.setMessageList(rates);
        }
        return chatRepository.save(abc);
    }

  

}
