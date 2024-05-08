package com.example.efabackend.fileController; 
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.efabackend.entity.Chat;
import com.example.efabackend.entity.Message;
import com.example.efabackend.exception.ChatAlreadyExistException;
import com.example.efabackend.exception.ChatNotFoundException;
import com.example.efabackend.exception.NoChatExistsInTheRepository;
import com.example.efabackend.service.SequenceGeneratorService;
import com.example.efabackend.service.impl.ChatService;

@RestController
@RequestMapping("/api/chats")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/add")
    public ResponseEntity<?> createChat(@RequestBody Chat chat) {
        try {
            Chat createdChat = chatService.addChat(chat);
            return new ResponseEntity<>(createdChat, HttpStatus.CREATED);
        } catch (ChatAlreadyExistException e) {
            return new ResponseEntity<>("Chat Already Exists", HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllChats() {
        try {
            List<Chat> chats = chatService.findAllChats();
            return new ResponseEntity<>(chats, HttpStatus.OK);
        } catch (NoChatExistsInTheRepository e) {
            return new ResponseEntity<>("No Chats Found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getChatById(@PathVariable int id) {
        try {
            Chat chat = chatService.getChatById(id);
            return new ResponseEntity<>(chat, HttpStatus.OK);
        } catch (ChatNotFoundException e) {
            return new ResponseEntity<>("Chat Not Found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/byUserName/{username}")
    public ResponseEntity<?> getChatByUserName(@PathVariable String username) {
        try {
            Set<Chat> chats = chatService.getChatsByFirstUserName(username);
            return new ResponseEntity<>(chats, HttpStatus.OK);
        } catch (ChatNotFoundException e) {
            return new ResponseEntity<>("Chat Not Exists", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/byUserNames")
    public ResponseEntity<?> getChatByUserNames(@RequestParam("firstUserName") String firstUserName,
                                                @RequestParam("secondUserName") String secondUserName) throws ChatNotFoundException {
        Set<Chat> chats = chatService.getChatsByFirstUserNameAndSecondUserName(firstUserName, secondUserName);
        return new ResponseEntity<>(chats, HttpStatus.OK);
    }

    /**
     * Add a message to a chat.
     * @param message The message to add.
     * @param chatId The ID of the chat.
     * @return The updated chat.
     */
    @PutMapping("/message/{chatId}")
    public ResponseEntity<?> addMessage(@RequestBody Message message, @PathVariable int chatId) {
        try {
            Chat updatedChat = chatService.addMessageToChat(message, chatId);
            return new ResponseEntity<>(updatedChat, HttpStatus.OK);
        } catch (ChatNotFoundException e) {
            return new ResponseEntity<>("Chat Not Found", HttpStatus.NOT_FOUND);
        }
    }
}
