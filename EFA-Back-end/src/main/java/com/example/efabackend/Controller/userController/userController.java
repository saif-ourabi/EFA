package com.example.efabackend.Controller.userController;
import com.example.efabackend.Dto.LoginDto;
import com.example.efabackend.Dto.RegisterDto;
import com.example.efabackend.response.LoginResponse;
import com.example.efabackend.response.RegisterResponse;
import com.example.efabackend.service.JwtService;
import com.example.efabackend.service.impl.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/user")
public class userController {
    @Autowired
    private userService userservice;

    @PostMapping(path = "/register")
    public RegisterResponse register(@RequestBody RegisterDto registerDTO){
        RegisterResponse RegisterResponse =userservice.addUser(registerDTO);
        return RegisterResponse;
    }

    @PostMapping(path = "/login")
    public ResponseEntity <?> login(@RequestBody LoginDto loginDto){
        LoginResponse loginMessage =userservice.loginUser(loginDto);
        return ResponseEntity.ok(loginMessage);
    }
    @GetMapping("/generateToken")
    public String generateToken() {
        String username = "exampleUser";
        return JwtService.generateToken(username);
    }


}
