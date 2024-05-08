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
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Override
    public Chat addChat(Chat chat) throws ChatAlreadyExistException {
        if (chatRepository.existsById(chat.getChatId())) {
            throw new ChatAlreadyExistException();
        }
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
    public Set<Chat> getChatsByFirstUserName(String username) throws ChatNotFoundException {
        Set<Chat> chats = chatRepository.findByFirstUserName(username);
        if (chats.isEmpty()) {
            throw new ChatNotFoundException();
        }
        return chats;
    }

    @Override
    public Set<Chat> getChatsBySecondUserName(String username) throws ChatNotFoundException {
        Set<Chat> chats = chatRepository.findBySecondUserName(username);
        if (chats.isEmpty()) {
            throw new ChatNotFoundException();
        }
        return chats;
    }
    

    @Override
public Set<Chat> getChatsByUserName(String username) throws ChatNotFoundException {
    Set<Chat> chatsByFirstUserName = chatRepository.findByFirstUserName(username);
    Set<Chat> chatsBySecondUserName = chatRepository.findBySecondUserName(username);
    Set<Chat> mergedChats = new HashSet<>(chatsByFirstUserName);
    mergedChats.addAll(chatsBySecondUserName);
    
   
    
    return mergedChats;
}

    @Override
    public Set<Chat> getChatsByFirstUserNameAndSecondUserName(String firstUserName, String secondUserName)
            throws ChatNotFoundException {
                Set<Chat> chat = chatRepository.findByFirstUserName(firstUserName);
                Set<Chat> chat1 = chatRepository.findBySecondUserName(secondUserName);
                chat1.addAll(chat);
                return chat1;
    }

    @Override
    public Chat addMessageToChat(Message message, int chatId) throws ChatNotFoundException {
        Optional<Chat> chatOptional = chatRepository.findById((long) chatId);
        Chat chat = chatOptional.orElseThrow(ChatNotFoundException::new);
    
        if (chat.getMessageList() == null) {
            chat.setMessageList(new ArrayList<>());
        }
    
        chat.getMessageList().add(message); // Ajouter le message à la liste de messages du chat
        return chatRepository.save(chat);
    }
}